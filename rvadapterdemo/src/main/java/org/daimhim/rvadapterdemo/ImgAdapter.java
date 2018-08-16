package org.daimhim.rvadapterdemo;

import android.content.Context;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.daimhim.rvadapter.RecyclerContract;
import org.daimhim.rvadapter.RecyclerViewEmpty;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：org.daimhim.rvadapterdemo
 * 项目版本：muster
 * 创建时间：2018.08.15 16:46  星期三
 * 创建人：Daimhim
 * 修改时间：2018.08.15 16:46  星期三
 * 类描述：Daimhim 太懒了，什么都没有留下
 * 修改备注：Daimhim 太懒了，什么都没有留下
 *
 * @author：Daimhim
 */
public class ImgAdapter extends RecyclerViewEmpty<ImgAdapter.ImgViewHolder> implements RecyclerContract.SimpleContract<List<Integer>, Integer> {
    private Context mContext;
    private List<Integer> mIntegers;

    public ImgAdapter(Context pContext) {
        mContext = pContext;
        mIntegers = new ArrayList<>();
    }

    @Override
    public ImgViewHolder onCreateDataViewHolder(ViewGroup parent, int viewType) {
        Log.e("ImgAdapter","viewType:"+viewType);
        return new ImgViewHolder(LayoutInflater.from(mContext).inflate(R.layout.viewholder_img, parent, false));
    }

    @Override
    public void onBindDataViewHolder(ImgViewHolder holder, int position) {
        holder.mIvImg.setImageResource(getItem(position));
    }

    @Override
    public int getDataItemCount() {
        return mIntegers.size();
    }

    @Override
    public void onRefresh(List<Integer> pIntegers) {
        mIntegers.clear();
        mIntegers.addAll(pIntegers);
        notifyDataSetChanged();
    }

    @Override
    public Integer getItem(int position) {
        return mIntegers.get(position);
    }

    @Override
    public void onLoad(List<Integer> pIntegers) {

    }

    @Override
    public int getDataItemViewType(int position) {
        return 4;
    }

    public static class ImgViewHolder extends MixingAdapter.MixingViewHolder {
        ImageView mIvImg;
        public ImgViewHolder(View itemView) {
            super(itemView);
            this.mIvImg = (ImageView) itemView.findViewById(R.id.iv_img);
        }

        @Override
        void onRefresh(Pair<String, Integer> pPair) {
            mIvImg.setImageResource(pPair.second);
        }


    }

}
