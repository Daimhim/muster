package org.daimhim.pluginmanager.ui.app;

import android.arch.lifecycle.ViewModelProviders;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.daimhim.afilechooser.ipaulpro.afilechooser.utils.FileUtils;
import org.daimhim.helpful.util.HLogUtil;
import org.daimhim.pluginmanager.R;
import org.daimhim.pluginmanager.model.ObserverCallBack;
import org.daimhim.pluginmanager.model.bean.AddAppMenuBean;
import org.daimhim.pluginmanager.model.bean.ApplicationBean;
import org.daimhim.pluginmanager.model.response.JavaResponse;
import org.daimhim.pluginmanager.ui.base.BaseFragment;
import org.daimhim.pluginmanager.ui.main.MainUtils;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;

/**
 * 项目名称：org.daimhim.pluginmanager.ui.app
 * 项目版本：muster
 * 创建时间：2018/10/26 10:54  星期五
 * 创建人：Administrator
 * 修改时间：2018/10/26 10:54  星期五
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class EditAppFragment extends BaseFragment {
    private static final int REQUEST_CODE = 6384;
    private static final int SELECT_FILE_REQUEST_CODE = 10;
    Unbinder unbinder;
    @BindView(R.id.rl_recycler_view_pm)
    RecyclerView rlRecyclerView;
    @BindView(R.id.bt_app_pm)
    Button btAppPm;
    private EditAppViewModel mEditAppViewModel;
    private EditAppAdapter mAddAppAdapter;
    private ApplicationBean mApplicationBean;

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
        MainUtils.upTitleAndIco(getContext(), "App编辑", R.drawable.ic_arrow_back_black_24dp, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainUtils.backFragment(getContext());
            }
        });
        mEditAppViewModel = ViewModelProviders.of(this).get(EditAppViewModel.class);
        mAddAppAdapter = new EditAppAdapter();
        rlRecyclerView.setAdapter(mAddAppAdapter);
        Bundle lArguments = getArguments();
        if (null != lArguments) {
            mApplicationBean = lArguments.getParcelable("value");
        }
        mEditAppViewModel.getInputMenu(mApplicationBean)
                .subscribe(new ObserverCallBack<List<AddAppMenuBean>>() {
                    @Override
                    public void onSuccess(List<AddAppMenuBean> pAddAppMenuBeans) {
                        mAddAppAdapter.onRefresh(pAddAppMenuBeans);
                    }

                    @Override
                    public void onFailure(JavaResponse pJavaResponse) {
                        Snackbar.make(view, pJavaResponse.getError_msg(), Snackbar.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.bt_upload_apk_pm, R.id.bt_download_apk_pm, R.id.bt_select_apk_pm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_upload_apk_pm:
                showChooser();
                break;
            case R.id.bt_download_apk_pm:
                String lApp_url = mAddAppAdapter.getValue("appUrl");
                if (TextUtils.isEmpty(lApp_url)) {
                    Snackbar.make(view, "url can not be empty", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (!lApp_url.startsWith("http")) {
                    Snackbar.make(view, "url not http startsWith", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                mEditAppViewModel.upLoadApk(lApp_url)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new ObserverCallBack<ArrayMap<String, String>>() {
                            @Override
                            public void onSuccess(ArrayMap<String, String> pStringStringArrayMap) {
                                List<AddAppMenuBean> lPairs = mAddAppAdapter.getPairs();
                                AddAppMenuBean lBean = null;
                                for (int i = 0; i < lPairs.size(); i++) {
                                    lBean = lPairs.get(i);
                                    lBean.setVaue(pStringStringArrayMap.get(lBean.getKey()));
                                }
                                mAddAppAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(JavaResponse pJavaResponse) {
                                Snackbar.make(view, pJavaResponse.getError_msg(), Snackbar.LENGTH_SHORT).show();
                            }
                        });

                break;
            case R.id.bt_select_apk_pm:
                MainUtils.superimposedFragment(getContext(),SELECT_FILE_REQUEST_CODE,SelectAppFragment.class);
                break;
        }
    }

    /**
     * 吊起
     */
    private void showChooser() {
        // Use the GET_CONTENT intent from the utility class
        Intent target = FileUtils.createGetContentIntent();
        // Create the chooser Intent
        Intent intent = Intent.createChooser(
                target, "上传APK");
        try {
            startActivityForResult(intent, REQUEST_CODE);
        } catch (ActivityNotFoundException e) {
            // The reason for the existence of aFileChooser
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case SELECT_FILE_REQUEST_CODE:
                mApplicationBean = data.getParcelableExtra("value");
                mAddAppAdapter.onRefresh(mEditAppViewModel.partConversion(mApplicationBean));
                break;
            case REQUEST_CODE:
                // If the file selection was successful
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        // Get the URI of the selected file
                        final Uri uri = data.getData();
                        mEditAppViewModel.analyzeLocal(new File(FileUtils.getPath(getContext(), uri)))
                                .subscribe(new ObserverCallBack<ArrayMap<String, String>>() {
                                    @Override
                                    public void onSuccess(ArrayMap<String, String> pStringStringArrayMap) {
                                        List<AddAppMenuBean> lPairs = mAddAppAdapter.getPairs();
                                        AddAppMenuBean lBean = null;
                                        for (int i = 0; i < lPairs.size(); i++) {
                                            lBean = lPairs.get(i);
                                            lBean.setVaue(pStringStringArrayMap.get(lBean.getKey()));
                                        }
                                        mAddAppAdapter.notifyDataSetChanged();
                                    }

                                    @Override
                                    public void onFailure(JavaResponse pJavaResponse) {
                                        Snackbar.make(rlRecyclerView, pJavaResponse.getError_msg(), Snackbar.LENGTH_SHORT).show();
                                    }
                                });
                    }
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onBackPressed() {
        MainUtils.startFragment(getContext(), ApplicationFragment.class);
        return true;
    }

    @OnClick(R.id.bt_app_pm)
    public void onViewClicked() {
        List<AddAppMenuBean> lPairs = mAddAppAdapter.getPairs();
        mEditAppViewModel.editApp(mEditAppViewModel.partConversion(lPairs))
                .subscribe(new ObserverCallBack<JavaResponse<Void>>() {
                    @Override
                    public void onSuccess(JavaResponse<Void> pVoidJavaResponse) {
                        Snackbar.make(rlRecyclerView, pVoidJavaResponse.getError_msg(), Snackbar.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(JavaResponse pJavaResponse) {
                        Snackbar.make(rlRecyclerView, pJavaResponse.getError_msg(), Snackbar.LENGTH_SHORT).show();
                    }
                });
    }
}
