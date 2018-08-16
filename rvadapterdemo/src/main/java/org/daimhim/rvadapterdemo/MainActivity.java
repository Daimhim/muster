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

        mRvRecycler.setAdapter(mAdapterManagement);
    }
}
