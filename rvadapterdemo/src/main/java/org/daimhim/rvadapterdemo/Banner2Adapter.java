package org.daimhim.rvadapterdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.daimhim.banner.Banner;
import org.daimhim.banner.loader.ImageLoader;
import org.daimhim.rvadapter.RecyclerContract;
import org.daimhim.rvadapter.RecyclerViewEmpty;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：org.daimhim.rvadapterdemo
 * 项目版本：muster
 * 创建时间：2018.08.22 10:44  星期三
 * 创建人：Daimhim
 * 修改时间：2018.08.22 10:44  星期三
 * 类描述：Daimhim 太懒了，什么都没有留下
 * 修改备注：Daimhim 太懒了，什么都没有留下
 *
 * @author：Daimhim
 */
public class Banner2Adapter extends RecyclerViewEmpty<Banner2Adapter.Banner2ViewHolder> implements RecyclerContract.SimpleContract<List<String>, String> {
    private List<String> mStrings;
    private Context mContext;

    public Banner2Adapter(Context pContext) {
        mContext = pContext;
        mStrings = new ArrayList<>();
    }

    @Override
    public void onRefresh(List<String> pStrings) {
        mStrings.clear();
        mStrings.addAll(pStrings);
        notifyDataSetChanged();
    }

    @Override
    public String getItem(int position) {
        return mStrings.get(position);
    }

    @Override
    public void onLoad(List<String> pStrings) {

    }

    @Override
    public Banner2ViewHolder onCreateDataViewHolder(ViewGroup parent, int viewType) {
        View lInflate = LayoutInflater.from(mContext).inflate(R.layout.viewholder_banner2, parent, false);
        return new Banner2ViewHolder(lInflate);
    }

    @Override
    public void onBindDataViewHolder(Banner2ViewHolder holder, int position) {
        holder.mBBanner.stopAutoPlay();
        holder.mBBanner.setImages(mStrings);
        holder.mBBanner.start();
    }

    @Override
    public int getDataItemCount() {
        return 1;
    }

    static class Banner2ViewHolder extends RecyclerViewEmpty.ClickViewHolder {
        Banner mBBanner;
        public Banner2ViewHolder(View itemView) {
            super(itemView);
            mBBanner = itemView.findViewById(R.id.b_banner);
            mBBanner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context).load(path).into(imageView);
                }
            });
            mBBanner.isAutoPlay(true);
        }

    }

}
