package org.daimhim.pluginmanager.ui.plugin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.daimhim.pluginmanager.R;
import org.daimhim.pluginmanager.model.bean.PluginBean;
import org.daimhim.rvadapter.RecyclerContract;
import org.daimhim.rvadapter.RecyclerViewEmpty;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 项目名称：org.daimhim.pluginmanager.ui.plugin
 * 项目版本：muster
 * 创建时间：2018/11/1 19:46  星期四
 * 创建人：Administrator
 * 修改时间：2018/11/1 19:46  星期四
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class PluginAdapter extends RecyclerViewEmpty<PluginAdapter.PluginViewHolder>
        implements RecyclerContract.SimpleContract<List<PluginBean>, PluginBean> {
    private List<PluginBean> mPluginBeans;

    public PluginAdapter() {
        mPluginBeans = new ArrayList<>();
    }

    @Override
    public PluginViewHolder onCreateDataViewHolder(ViewGroup pViewGroup, int pI) {
        return new PluginViewHolder(LayoutInflater.from(pViewGroup.getContext()).inflate(R.layout.viewholder_plugin, pViewGroup, false));
    }

    @Override
    public void onBindDataViewHolder(PluginViewHolder pPluginViewHolder, int pI) {
        PluginBean lItem = getItem(pI);
        pPluginViewHolder.tvPluginNamePm.setText(lItem.getPlugin_name());
        pPluginViewHolder.tvPackageNamePm.setText(lItem.getPackage_name());
        pPluginViewHolder.tvDescriptionPm.setText(lItem.getPlugin_description());
        pPluginViewHolder.tvNewVersionPm.setText(lItem.getLast_version_code());
        pPluginViewHolder.tvUpTimePm.setText(lItem.getLast_version_upTime());
    }

    @Override
    public int getDataItemCount() {
        return mPluginBeans.size();
    }

    @Override
    public void onRefresh(List<PluginBean> pPluginBeans) {
        mPluginBeans.clear();
        mPluginBeans.addAll(pPluginBeans);
        notifyDataSetChanged();
    }

    @Override
    public PluginBean getItem(int pI) {
        return mPluginBeans.get(pI);
    }

    @Override
    public void onLoad(List<PluginBean> pPluginBeans) {
        mPluginBeans.addAll(pPluginBeans);
        notifyDataSetChanged();
    }

    class PluginViewHolder extends RecyclerViewEmpty.ClickViewHolder {
        @BindView(R.id.tv_plugin_name_pm)
        TextView tvPluginNamePm;
        @BindView(R.id.tv_package_name_pm)
        TextView tvPackageNamePm;
        @BindView(R.id.tv_description_pm)
        TextView tvDescriptionPm;
        @BindView(R.id.tv_new_version_pm)
        TextView tvNewVersionPm;
        @BindView(R.id.tv_up_time_pm)
        TextView tvUpTimePm;
        public PluginViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
