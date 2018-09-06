package org.daimhim.hometabdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // 图片
    @DrawableRes
    private int mImages[] = {
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher
    };
    // 标题
    private String mFragmentTags[] = {
            "counter",
            "assistant",
            "contest",
            "center"
    };
    private Class mFragment[] = {
            FirstFragment.class,
            TowFragment.class,
            ThreeFragment.class,
            FourFragment.class
    };
    private FrameLayout mFlLayout;
    private FragmentTabHost mFtMeun;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mFlLayout = (FrameLayout) findViewById(R.id.fl_layout);
        mFtMeun = (FragmentTabHost) findViewById(R.id.ft_meun);
        mFtMeun.setup(this,getSupportFragmentManager(),R.id.fl_layout);
        mFtMeun.getTabWidget().setDividerDrawable(null);
        for (int i = 0; i < mImages.length; i++) {
            int finalI = i;
            View lInflate = LayoutInflater.from(mContext).inflate(R.layout.home_item, null);
            ImageView iv_ico = lInflate.findViewById(R.id.iv_ico);
            iv_ico.setImageResource(mImages[finalI]);
            TextView tv_name = lInflate.findViewById(R.id.tv_name);
            tv_name.setText(mFragmentTags[finalI]);

            Bundle lBundle = new Bundle();
            lBundle.putString("tag",mFragmentTags[i]);
            mFtMeun.addTab(mFtMeun.newTabSpec(mFragmentTags[i])
                    .setIndicator(lInflate),mFragment[i], lBundle);
        }
    }

}
