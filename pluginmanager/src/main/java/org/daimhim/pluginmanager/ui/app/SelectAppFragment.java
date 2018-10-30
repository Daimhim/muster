package org.daimhim.pluginmanager.ui.app;

import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.daimhim.helpful.util.HImageUtil;
import org.daimhim.pluginmanager.R;
import org.daimhim.pluginmanager.StartApp;
import org.daimhim.pluginmanager.model.ObserverCallBack;
import org.daimhim.pluginmanager.model.bean.AddAppMenuBean;
import org.daimhim.pluginmanager.model.bean.ApplicationBean;
import org.daimhim.pluginmanager.model.response.JavaResponse;
import org.daimhim.pluginmanager.ui.main.MainUtils;
import org.daimhim.pluginmanager.utils.CacheFileUtils;
import org.daimhim.rvadapter.RecyclerContract;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * 项目名称：org.daimhim.pluginmanager.ui.app
 * 项目版本：muster
 * 创建时间：2018/10/29 13:55  星期一
 * 创建人：Administrator
 * 修改时间：2018/10/29 13:55  星期一
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class SelectAppFragment extends Fragment {

    @BindView(R.id.rl_recycler_view_pm)
    RecyclerView rlRecyclerViewPm;
    Unbinder unbinder;
    private EditAppViewModel mEditAppViewModel;
    private SelectAppAdapter mSelectAppAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_selectapp, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mEditAppViewModel = ViewModelProviders.of(this).get(EditAppViewModel.class);
        mSelectAppAdapter = new SelectAppAdapter();
        rlRecyclerViewPm.setAdapter(mSelectAppAdapter);
        rlRecyclerViewPm.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        mSelectAppAdapter.setOnItemClickListener(new RecyclerContract.OnItemClickListener() {
            @Override
            public void onItemClick(View pView, int pI) {
                Bundle lArgs = new Bundle();
                ApplicationBean lItem = mSelectAppAdapter.getItem(pI);
                lArgs.putParcelable("value",lItem);
                MainUtils.backFragment(getContext(),lArgs);
            }
        });
        getAppList();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void getAppList() {
        PackageManager pm = Objects.requireNonNull(getContext()).getPackageManager();
        // Return a List of all packages that are installed on the device.
        List<PackageInfo> packages = pm.getInstalledPackages(0);

        Observable.fromIterable(packages)
                .skipWhile(new Predicate<PackageInfo>() {
                    @Override
                    public boolean test(PackageInfo pPackageInfo) throws Exception {
                        // 非系统应用
                        return pPackageInfo != null && (pPackageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
                    }
                })
                .map(new Function<PackageInfo, ApplicationBean>() {
                    @Override
                    public ApplicationBean apply(PackageInfo pPackageInfo) throws Exception {
                        ApplicationBean lApplicationBean = new ApplicationBean();
                        CacheFileUtils lInstance = CacheFileUtils.getInstance();
                        PackageManager lPackageManager = StartApp.getInstance().getPackageManager();
                        ApplicationInfo appInfo = pPackageInfo.applicationInfo;
                        Drawable lDrawable = appInfo.loadIcon(lPackageManager);
                        Uri lPng = lInstance.saveBitmap(HImageUtil.drawableToBitmap(lDrawable),
                                lInstance.getDiskCacheDir(CacheFileUtils.CACHE_IMAGE_DIR).getAbsolutePath(),
                                lInstance.generateRandomFilename("png"));
                        lApplicationBean.setApp_logo(lPng.getPath());
                        lApplicationBean.setApp_name(lPackageManager.getApplicationLabel(appInfo).toString());
                        lApplicationBean.setApp_url("居无定所");
                        lApplicationBean.setPackage_name(appInfo.packageName);
                        lApplicationBean.setVersion_name(pPackageInfo.versionName);
                        if (Build.VERSION.SDK_INT >= 28) {
                            lApplicationBean.setVersion_code(String.valueOf(pPackageInfo.getLongVersionCode()));
                        } else {
                            lApplicationBean.setVersion_code(String.valueOf(pPackageInfo.versionCode));
                        }
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            lApplicationBean.setMin_sdk_version(String.valueOf(appInfo.minSdkVersion));
                        }
                        lApplicationBean.setTarget_sdk_version(String.valueOf(appInfo.targetSdkVersion));
                        return lApplicationBean;
                    }
                })
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverCallBack<List<ApplicationBean>>() {
                    @Override
                    public void onSuccess(List<ApplicationBean> pApplicationBeans) {
                        mSelectAppAdapter.onRefresh(pApplicationBeans);
                    }

                    @Override
                    public void onFailure(JavaResponse pJavaResponse) {
                        Snackbar.make(rlRecyclerViewPm, pJavaResponse.getError_msg(), Snackbar.LENGTH_SHORT).show();
                    }
                });

    }
}
