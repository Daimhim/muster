package org.daimhim.pluginmanager.ui.plugin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.daimhim.pluginmanager.R;
import org.daimhim.pluginmanager.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 项目名称：org.daimhim.pluginmanager.ui.plugin
 * 项目版本：muster
 * 创建时间：2018/11/3 11:43  星期六
 * 创建人：Administrator
 * 修改时间：2018/11/3 11:43  星期六
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class PluginEditFragment extends BaseFragment {

    @BindView(R.id.et_plugin_name_input_pm)
    TextInputEditText etPluginNameInputPm;
    @BindView(R.id.et_plugin_url_input_pm)
    TextInputEditText etPluginUrlInputPm;
    @BindView(R.id.et_package_name_input_pm)
    TextInputEditText etPackageNameInputPm;
    @BindView(R.id.et_plugin_description_input_pm)
    TextInputEditText etPluginDescriptionInputPm;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plugin_edit, container, false);
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

    @OnClick({R.id.bt_upload_apk_pm, R.id.bt_download_apk_pm, R.id.bt_app_pm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_upload_apk_pm:
                break;
            case R.id.bt_download_apk_pm:
                break;
            case R.id.bt_app_pm:
                break;
        }
    }
}
