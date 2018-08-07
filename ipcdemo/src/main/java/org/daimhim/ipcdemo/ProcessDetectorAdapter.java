package org.daimhim.ipcdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：org.daimhim.ipcdemo
 * 项目版本：muster
 * 创建时间：2018.08.07 15:26
 * 修改人：Daimhim
 * 修改时间：2018.08.07 15:26
 * 类描述：
 * 修改备注：
 *
 * @author：Daimhim
 */
public class ProcessDetectorAdapter extends BaseAdapter {
    List<TaskInfo> mTaskInfos;

    public ProcessDetectorAdapter() {
        mTaskInfos = new ArrayList<>();
    }
    public void onRefresh(List<TaskInfo> taskInfos){
        mTaskInfos.clear();
        mTaskInfos.addAll(taskInfos);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return mTaskInfos.size();
    }

    @Override
    public TaskInfo getItem(int position) {
        return mTaskInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder lViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_process_detector_item, parent, false);
            lViewHolder = new ViewHolder(convertView);
            convertView.setTag(lViewHolder);
        } else {
            lViewHolder = (ViewHolder) convertView.getTag();
        }
        TaskInfo lItem = getItem(position);
        lViewHolder.mIvIco.setImageDrawable(lItem.getIcon());
        lViewHolder.mTvAppName.setText(lItem.getAppName());
        lViewHolder.mTvPackagename.setText(lItem.getPackageName());
        lViewHolder.mTvProcessName.setText(lItem.getProcessName());
        lViewHolder.mTvUserApp.setText(lItem.isUserApp()?"用户":"系统");
        return convertView;
    }

    static class ViewHolder {
        View view;
        ImageView mIvIco;
        TextView mTvAppName;
        TextView mTvUserApp;
        TextView mTvPackagename;
        TextView mTvMemorySize;
        TextView mTvProcessName;

        ViewHolder(View pView) {
            this.view = pView;
            this.mIvIco = (ImageView) view.findViewById(R.id.iv_ico);
            this.mTvAppName = (TextView) view.findViewById(R.id.tv_app_name);
            this.mTvUserApp = (TextView) view.findViewById(R.id.tv_user_app);
            this.mTvPackagename = (TextView) view.findViewById(R.id.tv_packagename);
            this.mTvMemorySize = (TextView) view.findViewById(R.id.tv_memory_size);
            this.mTvProcessName = (TextView) view.findViewById(R.id.tv_process_name);
        }
    }
}
