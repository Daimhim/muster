package org.daimhim.pagingdemo;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 项目名称：org.daimhim.pagingdemo
 * 项目版本：muster
 * 创建时间：2018.08.30 17:34  星期四
 * 创建人：Daimhim
 * 修改时间：2018.08.30 17:34  星期四
 * 类描述：Daimhim 太懒了，什么都没有留下
 * 修改备注：Daimhim 太懒了，什么都没有留下
 *
 * @author：Daimhim
 */
public class MainAdapter extends PagedListAdapter<JokeBean, MainAdapter.MainViewHolder> {

    protected MainAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_main, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        JokeBean lItem = getItem(position);
        if (lItem != null) {
            holder.mTvContent.setText(lItem.getContent());
            holder.mTvUpTime.setText(lItem.getUpdatetime());
        }

    }

    static class MainViewHolder extends RecyclerView.ViewHolder {
        TextView mTvContent;
        TextView mTvUpTime;

        public MainViewHolder(View itemView) {
            super(itemView);
            this.mTvContent = (TextView) itemView.findViewById(R.id.tv_content);
            this.mTvUpTime = (TextView) itemView.findViewById(R.id.tv_up_time);
        }
    }

    public static final DiffUtil.ItemCallback<JokeBean> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<JokeBean>() {
                @Override
                public boolean areItemsTheSame(
                        @NonNull JokeBean oldUser, @NonNull JokeBean newUser) {
                    // User properties may have changed if reloaded from the DB, but ID is fixed
                    return oldUser.getHashId() == newUser.getHashId();
                }

                @Override
                public boolean areContentsTheSame(
                        @NonNull JokeBean oldUser, @NonNull JokeBean newUser) {
                    // NOTE: if you use equals, your object must properly override Object#equals()
                    // Incorrectly returning false here will result in too many animations.
                    return oldUser.getHashId().equals(newUser.getHashId());
                }
            };
}
