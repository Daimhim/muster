package org.daimhim.pluginmanager.ui;

import android.arch.paging.PagedList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.daimhim.pluginmanager.R;
import org.daimhim.pluginmanager.model.ApplicationBean;
import org.daimhim.rvadapter.RecyclerContract;
import org.daimhim.rvadapter.RecyclerViewEmpty;

import java.util.ArrayList;
import java.util.List;

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
        implements RecyclerContract.SimpleContract<List<ApplicationBean>,ApplicationBean> {
    private PagedList<ApplicationBean> mApplicationBeans;

    public ApplicationAdapter() {
//        mApplicationBeans = new PagedList<>();
    }

    @Override
    public AppViewHolder onCreateDataViewHolder(ViewGroup parent, int viewType) {
        return new AppViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_application,parent,false));
    }

    @Override
    public void onBindDataViewHolder(AppViewHolder holder, int position) {

    }

    @Override
    public int getDataItemCount() {
        return mApplicationBeans.size();
    }

    @Override
    public void onRefresh(List<ApplicationBean> pApplicationBeans) {
        mApplicationBeans.clear();
        mApplicationBeans.addAll(pApplicationBeans);
    }

    @Override
    public ApplicationBean getItem(int position) {
        return mApplicationBeans.get(position);
    }

    @Override
    public void onLoad(List<ApplicationBean> pApplicationBeans) {

    }

    static class AppViewHolder extends RecyclerViewEmpty.ClickViewHolder{

        AppViewHolder(View itemView) {
            super(itemView);
        }
    }
}
