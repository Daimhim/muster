package org.daimhim.pluginmanager.ui.app;

import android.arch.lifecycle.ViewModelProviders;
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

import org.daimhim.pluginmanager.R;
import org.daimhim.pluginmanager.ui.main.MainUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

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

    @OnClick({R.id.bt_upload_apk, R.id.bt_download_apk, R.id.bt_select_apk, R.id.iv_logo, R.id.bt_create_pm, R.id.bt_cancel_pm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_upload_apk:
                if (TextUtils.isEmpty(etAppDownloadLinkPm.getText().toString())){
                    Snackbar.make(view,"url can not be empty",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                mApplicationViewModel.upLoadApk(etAppDownloadLinkPm.getText().toString());
                break;
            case R.id.bt_download_apk:
                break;
            case R.id.bt_select_apk:
                break;
            case R.id.iv_logo:
                break;
            case R.id.bt_create_pm:
                break;
            case R.id.bt_cancel_pm:
                MainUtils.backFragment(getContext());
                break;
        }
    }
}
