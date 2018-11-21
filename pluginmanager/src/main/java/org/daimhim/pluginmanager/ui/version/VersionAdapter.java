package org.daimhim.pluginmanager.ui.version;

import android.view.View;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import org.daimhim.pluginmanager.R;
import org.daimhim.pluginmanager.model.bean.ApkBean;
import org.daimhim.pluginmanager.ui.base.BaseAdapter;
import org.daimhim.rvadapter.RecyclerViewEmpty;

import butterknife.BindView;
import butterknife.ButterKnife;

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
public class VersionAdapter extends BaseAdapter<VersionAdapter.VersionViewHolder, ApkBean, ApkBean> {
    @Override
    public int getView() {
        return R.layout.viewholder_version;
    }

    @Override
    public void onBindDataViewHolder(VersionViewHolder pVersionViewHolder, int pI) {
        ApkBean lItem = getItem(pI);
        pVersionViewHolder.tvAppNamePm.setText(lItem.getApk_name());
        pVersionViewHolder.tvDescriptionPm.setText(lItem.getApk_description());
    }

    static class VersionViewHolder extends BaseAdapter.ClickViewHolder<ApkBean> {
        @BindView(R.id.tv_app_name_pm)
        TextView tvAppNamePm;
        @BindView(R.id.tv_description_pm)
        TextView tvDescriptionPm;
        @BindView(R.id.fab_fab_pm)
        FlexboxLayout fabFabPm;
        public VersionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
