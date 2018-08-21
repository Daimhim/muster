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
 * 创建时间：2018.08.16 13:46  星期四
 * 创建人：Daimhim
 * 修改时间：2018.08.16 13:46  星期四
 * 类描述：Daimhim 太懒了，什么都没有留下
 * 修改备注：Daimhim 太懒了，什么都没有留下
 *
 * @author：Daimhim
 */
public class BannerAdapter extends RecyclerViewEmpty<BannerAdapter.BannerViewHolder>implements RecyclerContract.SimpleContract<List<String>,String> {
    private Context mContext;
    private List<String> mList;
    public BannerAdapter(Context pContext) {
        mContext = pContext;
        mList = new ArrayList<>();
    }

    @Override
    public BannerViewHolder onCreateDataViewHolder(ViewGroup parent, int viewType) {
        return new BannerViewHolder(LayoutInflater.from(mContext).inflate(R.layout.viewholder_banner, parent, false));
    }

    @Override
    public void onBindDataViewHolder(BannerViewHolder holder, int position) {
        holder.mBBanner.stopAutoPlay();
        holder.mBBanner.setImages(mList);
        holder.mBBanner.start();
    }

    @Override
    public int getDataItemCount() {
        return 1;
    }

    @Override
    public void onRefresh(List<String> pStrings) {
        mList.clear();
        mList.addAll(pStrings);
        notifyDataSetChanged();
    }

    @Override
    public String getItem(int position) {
        return mList.get(position);
    }

    @Override
    public void onLoad(List<String> pStrings) {

    }

    static class BannerViewHolder extends RecyclerViewEmpty.ClickViewHolder {
        Banner mBBanner;
        public BannerViewHolder(View itemView) {
            super(itemView);
            this.mBBanner = (Banner) itemView.findViewById(R.id.b_banner);
            mBBanner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context)
                            .load(path)
                            .into(imageView);
                }
            });
            mBBanner.isAutoPlay(true);
        }
    }

}
