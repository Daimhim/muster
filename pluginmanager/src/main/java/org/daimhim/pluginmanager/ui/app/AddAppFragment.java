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
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.daimhim.helpful.util.HFileUtil;
import org.daimhim.helpful.util.HImageUtil;
import org.daimhim.pluginmanager.R;
import org.daimhim.pluginmanager.model.bean.ApplicationBean;
import org.daimhim.pluginmanager.model.response.JavaResponse;
import org.daimhim.pluginmanager.ui.main.MainUtils;
import org.daimhim.pluginmanager.utils.CacheFileUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 项目名称：org.daimhim.pluginmanager.ui.app
 * 项目版本：muster
 * 创建时间：2018/10/22 16:11  星期一
 * 创建人：Administrator
 * 修改时间：2018/10/22 16:11  星期一
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class AddAppFragment extends Fragment {


    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    @BindView(R.id.et_app_name_pm)
    TextInputEditText etAppNamePm;
    @BindView(R.id.et_app_download_link_pm)
    TextInputEditText etAppDownloadLinkPm;
    @BindView(R.id.et_package_name_pm)
    TextInputEditText etPackageNamePm;
    @BindView(R.id.et_app_versionCode_pm)
    TextInputEditText etAppVersionCodePm;
    Unbinder unbinder;
    private ApplicationViewModel mApplicationViewModel;

    public void upUI(ApplicationBean pApplicationBean){
        etAppNamePm.setText(pApplicationBean.getApp_name());
        etPackageNamePm.setText(pApplicationBean.getPackage_name());
        etAppVersionCodePm.setText(pApplicationBean.getVersion_code());
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_app, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mApplicationViewModel = ViewModelProviders.of(this).get(ApplicationViewModel.class);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.bt_upload_apk, R.id.bt_download_apk, R.id.bt_select_apk, R.id.iv_logo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_upload_apk:
                if (TextUtils.isEmpty(etAppDownloadLinkPm.getText().toString())){
                    Snackbar.make(view,"url can not be empty",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                mApplicationViewModel.upLoadApk(etAppDownloadLinkPm.getText().toString())
                        .map(new Function<JavaResponse<Uri>, ApplicationBean>() {
                            @Override
                            public ApplicationBean apply(JavaResponse<Uri> pUriJavaResponse) throws Exception {
                                ApplicationBean lBean = new ApplicationBean();
                                Uri lResult = pUriJavaResponse.getResult();
                                PackageManager lPackageManager = getContext().getPackageManager();
                                PackageInfo lPackageArchiveInfo = lPackageManager.getPackageArchiveInfo(lResult.getPath(), PackageManager.GET_ACTIVITIES);
                                if (lPackageArchiveInfo != null){
                                    ApplicationInfo appInfo = lPackageArchiveInfo.applicationInfo;
                                    appInfo.sourceDir = lResult.getPath();
                                    appInfo.publicSourceDir = lResult.getPath();
                                    lBean.setApp_name(lPackageManager.getApplicationLabel(appInfo).toString());// 得到应用名
                                    lBean.setPackage_name(appInfo.packageName);
                                    if (Build.VERSION.SDK_INT >= 28) {
                                        lBean.setVersion_code(String.valueOf(lPackageArchiveInfo.getLongVersionCode()));
                                    }else {
                                        lBean.setVersion_code(String.valueOf(lPackageArchiveInfo.versionCode));
                                    }
                                    lBean.setVersion_name(lPackageArchiveInfo.versionName);
                                    Drawable lDrawable = appInfo.loadIcon(lPackageManager);
                                    CacheFileUtils lInstance = CacheFileUtils.getInstance();
                                    Uri lPng = lInstance.saveBitmap(HImageUtil.drawableToBitmap(lDrawable),
                                            lInstance.getDiskCacheDir(CacheFileUtils.CACHE_IMAGE_DIR).getPath(),
                                            lInstance.generateRandomFilename("png"));
                                    lBean.setApp_logo(lPng.getPath());
                                }
                                return lBean;
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<ApplicationBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(ApplicationBean pApplicationBean) {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });

                break;
            case R.id.bt_download_apk:
                break;
            case R.id.bt_select_apk:
                break;
            case R.id.iv_logo:
                break;
        }
    }

    public void addApp(){

    }
}
