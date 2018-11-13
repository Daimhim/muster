package org.daimhim.pluginmanager.ui.plugin;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.daimhim.pluginmanager.R;
import org.daimhim.pluginmanager.model.ObserverCallBack;
import org.daimhim.pluginmanager.model.response.JavaResponse;
import org.daimhim.pluginmanager.model.response.PluginResponse;
import org.daimhim.pluginmanager.ui.base.BaseFragment;
import org.daimhim.pluginmanager.ui.main.MainUtils;
import org.daimhim.rvadapter.RecyclerContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PluginListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener, RecyclerContract.OnItemClickListener {

    @BindView(R.id.rl_recycler_view_pm)
    RecyclerView rlRecyclerViewPm;
    @BindView(R.id.srl_SwipeRefreshLayout_pm)
    SwipeRefreshLayout srlSwipeRefreshLayoutPm;
    @BindView(R.id.fab_fab_pm)
    FloatingActionButton fabFabPm;
    Unbinder unbinder;
    private PluginViewModel mPluginViewModel;
    private String mAppId;
    private PluginAdapter mPluginAdapter;
    private ObserverCallBack<JavaResponse<PluginResponse>> mObserver;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_application, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPluginViewModel = ViewModelProviders.of(this).get(PluginViewModel.class);
        initTile();
        mPluginAdapter = new PluginAdapter();
        rlRecyclerViewPm.setAdapter(mPluginAdapter);
        srlSwipeRefreshLayoutPm.setOnRefreshListener(this);
        rlRecyclerViewPm.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        mPluginAdapter.setOnItemClickListener(this);
        Bundle lArguments = getArguments();
        if (lArguments!=null) {
            mAppId = lArguments.getString("app_id");
            mObserver = new ObserverCallBack<JavaResponse<PluginResponse>>() {
                @Override
                public void onSuccess(JavaResponse<PluginResponse> pPluginResponseJavaResponse) {
                    mPluginAdapter.onRefresh(pPluginResponseJavaResponse.getResult().getList());
                    srlSwipeRefreshLayoutPm.setRefreshing(false);
                }
            };
            mPluginViewModel.getPluginList(mAppId)
                    .subscribe(mObserver);
        }
        fabFabPm.setOnClickListener(this);
    }

    private void initTile() {
        MainUtils.upTitleAndIco(getContext(), "插件管理", R.drawable.ic_arrow_back_black_24dp, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainUtils.getI().finishFragment(PluginListFragment.this);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onRefresh() {
        mPluginViewModel.getPluginList(mAppId)
                .subscribe(mObserver);
    }

    @Override
    public void onClick(View v) {
        Intent lIntent = new Intent(getContext(), PluginEditFragment.class);
        lIntent.putExtra("appId",mAppId);
        MainUtils.getI().starFragmentForResult(lIntent,50);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        initTile();
        switch (requestCode){
            case 50:
                onRefresh();
                break;
        }
    }

    @Override
    public void onItemClick(View pView, int pI) {

    }
}
