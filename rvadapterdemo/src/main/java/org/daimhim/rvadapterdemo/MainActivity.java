package org.daimhim.rvadapterdemo;

import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import org.daimhim.rvadapter.AdapterManagement;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRvRecycler;
    private AdapterManagement mAdapterManagement;
    private ImgAdapter mImgAdapter;
    private StringAdapter mStringAdapter;
    private MixingAdapter mMixingAdapter;
    private BannerDemoAdapter mBannerDemoAdapter;
    private Banner2Adapter mBanner2Adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mRvRecycler = (RecyclerView) findViewById(R.id.rv_recycler);
        mAdapterManagement = new AdapterManagement();

        mImgAdapter = new ImgAdapter(this);
        mStringAdapter = new StringAdapter(this);
        mMixingAdapter = new MixingAdapter(this);
        mBannerDemoAdapter = new BannerDemoAdapter(this);
        mBanner2Adapter = new Banner2Adapter(this);

        mAdapterManagement.addAdapter(mBannerDemoAdapter);
        mAdapterManagement.addAdapter(mImgAdapter);
        mAdapterManagement.addAdapter(mBanner2Adapter);
        mAdapterManagement.addAdapter(mStringAdapter);
        mAdapterManagement.addAdapter(mMixingAdapter);

        List<Integer> lIntegers = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            lIntegers.add(R.drawable.ic_launcher_background);
        }
        mImgAdapter.onRefresh(lIntegers);

        List<String> lStrings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            lStrings.add(String.format("item:%s",i));
        }
        mStringAdapter.onRefresh(lStrings);

        List<Pair<String,Integer>> pairs = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            pairs.add(new Pair<>(String.format("item:%s",i),R.drawable.ic_launcher_background));
        }
        mMixingAdapter.onRefresh(pairs);

        lStrings = new ArrayList<>();
        lStrings.add("http://i2.17173cdn.com/i7mz64/YWxqaGBf/tu17173com/20150906/PYKLGXbjEqfasyf.jpg");
        lStrings.add("http://www.k73.com/up/allimg/121005/22-121005113T9404.jpg");
        lStrings.add("http://pic.962.net/up/2013-5/2013052710010969092.jpg");
        lStrings.add("http://ossweb-img.qq.com/upload/webplat/info/mh/20150514/1431575482647908.jpg");
        lStrings.add("https://image.g-cores.com/28e7e731-a7c4-4558-9898-d27df63c0e05.jpg");
        lStrings.add("http://img.cbigame.com/upload/images/2016/0909/1473388434985279.jpg");
        lStrings.add("http://pc.xiaopi.com/uploadfile/2017/0324/20170324102538121.jpg");
        mBannerDemoAdapter.onRefresh(lStrings);

        lStrings = new ArrayList<>();
        lStrings.add("http://pic1.win4000.com/wallpaper/8/58f5a79da3b60.jpg");
        lStrings.add("http://himg2.huanqiu.com/attachment2010/2017/0503/14/47/20170503024755104.jpg");
        lStrings.add("https://image.tmdb.org/t/p/original/wdxWpq6lzgWxH8N8YgqQmLPvgn5.jpg");
        lStrings.add("http://image.jisuxz.com/desktop/1834/jisuxz_wangzhe_yadianna_1_05.jpg");
        lStrings.add("http://i.imgur.com/R0ySZg5.jpg");
        lStrings.add("http://pic1.win4000.com/wallpaper/7/5902e207bc663.jpg");
        lStrings.add("https://cn.best-wallpaper.net/wallpaper/1920x1080/1608/Gal-Gadot-as-Wonder-Woman_1920x1080.jpg");
        mBanner2Adapter.onRefresh(lStrings);

        mRvRecycler.setAdapter(mAdapterManagement);
    }
}
