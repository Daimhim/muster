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
    private BannerAdapter mBannerAdapter;
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
        mBannerAdapter = new BannerAdapter(this);

        mAdapterManagement.addAdapter(mBannerAdapter);
        mAdapterManagement.addAdapter(mImgAdapter);
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
            pairs.add(new Pair<String, Integer>(String.format("item:%s",i),R.drawable.ic_launcher_background));
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
        mBannerAdapter.onRefresh(lStrings);

        mRvRecycler.setAdapter(mAdapterManagement);
    }
}
