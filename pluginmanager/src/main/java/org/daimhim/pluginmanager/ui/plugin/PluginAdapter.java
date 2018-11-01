package org.daimhim.pluginmanager.ui.plugin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.daimhim.pluginmanager.R;
import org.daimhim.pluginmanager.model.bean.PluginBean;
import org.daimhim.rvadapter.RecyclerContract;
import org.daimhim.rvadapter.RecyclerViewEmpty;

import java.util.ArrayList;
import java.util.List;

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
        implements RecyclerContract.SimpleContract<List<PluginBean>,PluginBean> {
    private List<PluginBean> mPluginBeans;

    public PluginAdapter() {
        mPluginBeans = new ArrayList<>();
    }

    @Override
    public PluginViewHolder onCreateDataViewHolder(ViewGroup pViewGroup, int pI) {
        return new PluginViewHolder(LayoutInflater.from(pViewGroup.getContext()).inflate(R.layout.viewholder_plugin,pViewGroup,false));
    }

    @Override
    public void onBindDataViewHolder(PluginViewHolder pPluginViewHolder, int pI) {

    }

    @Override
    public int getDataItemCount() {
        return mPluginBeans.size();
    }

    @Override
    public void onRefresh(List<PluginBean> pPluginBeans) {

    }

    @Override
    public PluginBean getItem(int pI) {
        return null;
    }

    @Override
    public void onLoad(List<PluginBean> pPluginBeans) {

    }

    class PluginViewHolder extends RecyclerViewEmpty.ClickViewHolder{

        public PluginViewHolder(View itemView) {
            super(itemView);
        }
    }
}
