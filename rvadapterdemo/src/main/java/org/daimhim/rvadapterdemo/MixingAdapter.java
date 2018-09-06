package org.daimhim.rvadapterdemo;

import android.content.Context;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.daimhim.rvadapter.RecyclerContract;
import org.daimhim.rvadapter.RecyclerViewEmpty;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：org.daimhim.rvadapterdemo
 * 项目版本：muster
 * 创建时间：2018.08.15 17:04  星期三
 * 创建人：Daimhim
 * 修改时间：2018.08.15 17:04  星期三
 * 类描述：Daimhim 太懒了，什么都没有留下
 * 修改备注：Daimhim 太懒了，什么都没有留下
 *
 * @author：Daimhim
 */
public class MixingAdapter extends RecyclerViewEmpty<MixingAdapter.MixingViewHolder> implements RecyclerContract.SimpleContract<List<Pair<String,Integer>>,Pair<String,Integer>>{
    private Context mContext;
    private List<Pair<String,Integer>> mPairs;
    public MixingAdapter(Context pContext) {
        mContext = pContext;
        mPairs = new ArrayList<>();
    }

    @Override
    public MixingViewHolder onCreateDataViewHolder(ViewGroup parent, int viewType) {
        MixingViewHolder lHolder = null;
        Log.e("MixingAdapter","viewType:"+viewType);
        switch (viewType){
            case 11:
                lHolder = new ImgAdapter.ImgViewHolder(LayoutInflater.from(mContext).inflate(R.layout.viewholder_img, parent, false));
                break;
            case 12:
                lHolder = new StringAdapter.StringViewHolder(LayoutInflater.from(mContext).inflate(R.layout.viewholder_string, parent, false));
                break;
        }
        return lHolder;
    }

    @Override
    public void onBindDataViewHolder(MixingViewHolder holder, int position) {
        holder.onRefresh(getItem(position));
    }

    @Override
    public int getDataItemCount() {
        return mPairs.size();
    }

    @Override
    public int getDataItemViewType(int position) {
//        Log.e("MixingAdapter","getDataItemViewType:"+position);
        int viewType = 0;
        if (position%2 == 0){
            viewType = 11;
        }else {
            viewType = 12;
        }
        return viewType;
    }

    @Override
    public void onRefresh(List<Pair<String, Integer>> pPairs) {
        mPairs.clear();
        mPairs.addAll(pPairs);
        notifyDataSetChanged();
    }

    @Override
    public Pair<String, Integer> getItem(int position) {
        return mPairs.get(position);
    }

    @Override
    public void onLoad(List<Pair<String, Integer>> pPairs) {

    }

    public abstract static class MixingViewHolder extends RecyclerViewEmpty.ClickViewHolder{
        public MixingViewHolder(View itemView) {
            super(itemView);
        }
        abstract void onRefresh(Pair<String, Integer> pPair);

    }


}
