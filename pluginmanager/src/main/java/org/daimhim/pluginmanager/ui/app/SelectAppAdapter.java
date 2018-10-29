package org.daimhim.pluginmanager.ui.app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.daimhim.pictureload.ImgLoadingUtil;
import org.daimhim.pluginmanager.R;
import org.daimhim.pluginmanager.model.bean.ApplicationBean;
import org.daimhim.rvadapter.RecyclerContract;
import org.daimhim.rvadapter.RecyclerViewEmpty;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 项目名称：org.daimhim.pluginmanager.ui.app
 * 项目版本：muster
 * 创建时间：2018/10/29 16:27  星期一
 * 创建人：Administrator
 * 修改时间：2018/10/29 16:27  星期一
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class SelectAppAdapter extends RecyclerViewEmpty<SelectAppAdapter.SelectAppViewHolder>
        implements RecyclerContract.SimpleContract<List<ApplicationBean>,ApplicationBean> {
    private List<ApplicationBean> mApplicationBeans;

    public SelectAppAdapter() {
        mApplicationBeans = new ArrayList<>();
    }

    @Override
    public SelectAppViewHolder onCreateDataViewHolder(ViewGroup pViewGroup, int pI) {
        return new SelectAppViewHolder(LayoutInflater.from(pViewGroup.getContext()).inflate(R.layout.viewholder_selectapp, pViewGroup, false));
    }

    @Override
    public void onBindDataViewHolder(SelectAppViewHolder pSelectAppViewHolder, int pI) {
        ApplicationBean lItem = getItem(pI);
        ImgLoadingUtil.loadImage(pSelectAppViewHolder.ivLogoPm,lItem.getApp_logo());
        pSelectAppViewHolder.tvAppNamePm.setText(lItem.getApp_name());
        pSelectAppViewHolder.tvPackageNamePm.setText(lItem.getPackage_name());
        pSelectAppViewHolder.performItemClick(pSelectAppViewHolder.itemView,this);
    }

    @Override
    public int getDataItemCount() {
        return mApplicationBeans.size();
    }

    @Override
    public void onRefresh(List<ApplicationBean> pApplicationBeans) {
        mApplicationBeans.clear();
        mApplicationBeans.addAll(pApplicationBeans);
        notifyDataSetChanged();
    }

    @Override
    public ApplicationBean getItem(int pI) {
        return mApplicationBeans.get(pI);
    }

    @Override
    public void onLoad(List<ApplicationBean> pApplicationBeans) {
    }

    class SelectAppViewHolder extends   RecyclerViewEmpty.ClickViewHolder {
        @BindView(R.id.iv_logo_pm)
        ImageView ivLogoPm;
        @BindView(R.id.tv_app_name_pm)
        TextView tvAppNamePm;
        @BindView(R.id.tv_package_name_pm)
        TextView tvPackageNamePm;
        public SelectAppViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
