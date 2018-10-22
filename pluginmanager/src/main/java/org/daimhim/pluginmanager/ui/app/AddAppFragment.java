package org.daimhim.pluginmanager.ui.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.daimhim.pluginmanager.R;

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

    @BindView(R.id.et_app_name_pm)
    TextInputEditText etAppNamePm;
    @BindView(R.id.et_app_download_link_pm)
    TextInputEditText etAppDownloadLinkPm;
    @BindView(R.id.et_package_name_pm)
    TextInputEditText etPackageNamePm;
    @BindView(R.id.et_app_logo_pm)
    TextInputEditText etAppLogoPm;
    Unbinder unbinder;

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
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.bt_create_pm, R.id.bt_cancel_pm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_create_pm:
                break;
            case R.id.bt_cancel_pm:
                break;
        }
    }
}
