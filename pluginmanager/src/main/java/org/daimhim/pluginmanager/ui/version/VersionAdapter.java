package org.daimhim.pluginmanager.ui.version;

import android.view.View;
import android.view.ViewGroup;

import org.daimhim.pluginmanager.model.bean.ApkBean;
import org.daimhim.pluginmanager.ui.base.BaseAdapter;
import org.daimhim.rvadapter.RecyclerViewEmpty;

/**
 * 项目名称：org.daimhim.pluginmanager.ui.version
 * 项目版本：muster
 * 创建时间：2018/11/13 17:33  星期二
 * 创建人：Administrator
 * 修改时间：2018/11/13 17:33  星期二
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class VersionAdapter extends BaseAdapter<VersionAdapter.VersionViewHolder,ApkBean,ApkBean> {
    @Override
    public int getView() {
        return 0;
    }

    @Override
    public void onBindDataViewHolder(VersionAdapter.VersionViewHolder pVersionViewHolder, int pI) {

    }

    static class VersionViewHolder extends RecyclerViewEmpty.ClickViewHolder<ApkBean>{
        public VersionViewHolder(View itemView) {
            super(itemView);
        }
    }
}
