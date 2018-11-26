package org.daimhim.pagingdemo;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import timber.log.Timber;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRvRecyclerView;
    private MainAdapter mMainAdapter;
    private MainViewModel mMainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        initView();

    }

    private void initView() {
        mRvRecyclerView = (RecyclerView) findViewById(R.id.rv_RecyclerView);
        mMainAdapter = new MainAdapter();
        PagedList<JokeBean> lPagedList = mMainViewModel.loadData();
        Timber.i("lPagedList:"+(lPagedList == null));
        mMainAdapter.submitList(lPagedList);
        mRvRecyclerView.setAdapter(mMainAdapter);
        mRvRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }
}
