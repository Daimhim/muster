package org.daimhim.threechildren;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mVpViewPager;
    private FrameLayout mFlLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mFlLayout = (FrameLayout) findViewById(R.id.fl_layout);
        mVpViewPager = (ViewPager) findViewById(R.id.vp_ViewPager);
        ArrayList<String> lStrings = new ArrayList<>();
        lStrings.add("http://pic1.win4000.com/wallpaper/8/58f5a79da3b60.jpg");
        lStrings.add("http://himg2.huanqiu.com/attachment2010/2017/0503/14/47/20170503024755104.jpg");
        lStrings.add("https://image.tmdb.org/t/p/original/wdxWpq6lzgWxH8N8YgqQmLPvgn5.jpg");
        lStrings.add("http://image.jisuxz.com/desktop/1834/jisuxz_wangzhe_yadianna_1_05.jpg");
        lStrings.add("http://i.imgur.com/R0ySZg5.jpg");
        lStrings.add("http://pic1.win4000.com/wallpaper/7/5902e207bc663.jpg");
        lStrings.add("https://cn.best-wallpaper.net/wallpaper/1920x1080/1608/Gal-Gadot-as-Wonder-Woman_1920x1080.jpg");
        mVpViewPager.setAdapter(new MainPagerAdapter(lStrings));
        //缓存数
        mVpViewPager.setOffscreenPageLimit(3);
        //让ViewPager可以绘制到它最大边界之外
        mFlLayout.setClipChildren(false);
        //让ViewPager可以绘制到它的Padding
        mVpViewPager.setClipToPadding(false);
        //控制ViewPager分页之间宽度
        mVpViewPager.setPageMargin(30);
        //控制左右两边显示宽度
        mVpViewPager.setPadding(80,0,80,0);
        //让ViewPager代理FrameLayout的滑动事件
        mFlLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mVpViewPager.dispatchTouchEvent(event);
            }
        });
    }

    static class MainPagerAdapter extends PagerAdapter {
        private List<String> mStrings;

        public MainPagerAdapter(List<String> pStrings) {
            mStrings = pStrings;
        }

        @Override
        public int getCount() {
            return mStrings.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            ImageView lChild = new ImageView(container.getContext());
            FrameLayout.LayoutParams lLayoutParams = new FrameLayout.LayoutParams(160, 160);
            lChild.setLayoutParams(lLayoutParams);
            Glide.with(container.getContext()).load(mStrings.get(position)).into(lChild);
            container.addView(lChild);
            return lChild;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}