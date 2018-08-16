package org.daimhim.rvadapterdemo;

import android.content.Context;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.daimhim.rvadapter.RecyclerContract;
import org.daimhim.rvadapter.RecyclerViewEmpty;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：org.daimhim.rvadapterdemo
 * 项目版本：muster
 * 创建时间：2018.08.15 16:52  星期三
 * 创建人：Daimhim
 * 修改时间：2018.08.15 16:52  星期三
 * 类描述：Daimhim 太懒了，什么都没有留下
 * 修改备注：Daimhim 太懒了，什么都没有留下
 *
 * @author：Daimhim
 */
public class StringAdapter extends RecyclerViewEmpty<StringAdapter.StringViewHolder> implements RecyclerContract.SimpleContract<List<String>, String> {
    private Context mContext;
    private List<String> mStrings;

    public StringAdapter(Context pContext) {
        mContext = pContext;
        mStrings = new ArrayList<>();
    }

    @Override
    public StringViewHolder onCreateDataViewHolder(ViewGroup parent, int viewType) {
        Log.e("StringAdapter","viewType:"+viewType);
        return new StringViewHolder(LayoutInflater.from(mContext).inflate(R.layout.viewholder_string, parent, false));
    }

    @Override
    public void onBindDataViewHolder(StringViewHolder holder, int position) {
        holder.mTvContent.setText(getItem(position));
    }

    @Override
    public int getDataItemCount() {
        return mStrings.size();
    }

    @Override
    public void onRefresh(List<String> pStrings) {
        mStrings.clear();
        mStrings.addAll(pStrings);
        notifyDataSetChanged();
    }

    @Override
    public int getDataItemViewType(int position) {
        return 3;
    }

    @Override
    public String getItem(int position) {
        return mStrings.get(position);
    }

    @Override
    public void onLoad(List<String> pStrings) {

    }

    public static class StringViewHolder extends MixingAdapter.MixingViewHolder {
        TextView mTvContent;
        public StringViewHolder(View itemView) {
            super(itemView);
            this.mTvContent = (TextView) itemView.findViewById(R.id.tv_content);
        }

        @Override
        void onRefresh(Pair<String, Integer> pPair) {
            mTvContent.setText(pPair.first);
        }

    }

}
