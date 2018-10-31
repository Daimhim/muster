package org.daimhim.pluginmanager.ui.app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.daimhim.pictureload.ImgLoadingUtil;
import org.daimhim.pluginmanager.R;
import org.daimhim.pluginmanager.model.UserHelp;
import org.daimhim.pluginmanager.model.bean.ApplicationBean;
import org.daimhim.pluginmanager.utils.AddressMachining;
import org.daimhim.rvadapter.RecyclerContract;
import org.daimhim.rvadapter.RecyclerViewEmpty;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 项目名称：org.daimhim.pluginmanager.ui
 * 项目版本：muster
 * 创建时间：2018/10/18 17:08  星期四
 * 创建人：Administrator
 * 修改时间：2018/10/18 17:08  星期四
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class ApplicationAdapter extends RecyclerViewEmpty<ApplicationAdapter.AppViewHolder>
        implements RecyclerContract.SimpleContract<List<ApplicationBean>, ApplicationBean> {
    private List<ApplicationBean> mApplicationBeans;

    public ApplicationAdapter() {
        mApplicationBeans = new ArrayList<>();
    }

    @Override
    public AppViewHolder onCreateDataViewHolder(ViewGroup parent, int viewType) {
        return new AppViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_application, parent, false));
    }

    @Override
    public void onBindDataViewHolder(AppViewHolder holder, int position) {
        ApplicationBean lItem = getItem(position);
        ImgLoadingUtil.loadImage(holder.ivLogoPm, AddressMachining.fileIdToImageUrl(lItem.getApp_logo()));
        holder.tvAppNamePm.setText(lItem.getApp_name());
        holder.performItemClick(holder.itemView,this);
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
    public ApplicationBean getItem(int position) {
        return mApplicationBeans.get(position);
    }

    @Override
    public void onLoad(List<ApplicationBean> pApplicationBeans) {

    }

    static class AppViewHolder extends RecyclerViewEmpty.ClickViewHolder {
        @BindView(R.id.iv_logo_pm)
        ImageView ivLogoPm;
        @BindView(R.id.tv_app_name_pm)
        TextView tvAppNamePm;
        AppViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
