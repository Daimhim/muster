package org.daimhim.pluginmanager.ui.app;

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
import org.daimhim.pluginmanager.model.bean.ApplicationBean;
import org.daimhim.pluginmanager.model.response.ApplicationResponse;
import org.daimhim.pluginmanager.model.response.JavaResponse;
import org.daimhim.pluginmanager.ui.base.BaseFragment;
import org.daimhim.pluginmanager.ui.main.MainUtils;
import org.daimhim.pluginmanager.ui.plugin.PluginEditFragment;
import org.daimhim.pluginmanager.ui.plugin.PluginListFragment;
import org.daimhim.rvadapter.RecyclerContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * 项目名称：org.daimhim.pluginmanager
 * 项目版本：muster
 * 创建时间：2018/10/17 14:22  星期三
 * 创建人：Administrator
 * 修改时间：2018/10/17 14:22  星期三
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class ApplicationFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, RecyclerContract.OnItemClickListener {
    RecyclerView mRlRecyclerView;
    SwipeRefreshLayout mSrlSwipeRefreshLayout;
    @BindView(R.id.fab_fab_pm)
    FloatingActionButton fabFab;
    Unbinder unbinder;
    private ApplicationAdapter mApplicationAdapter;
    private ApplicationViewModel mApplicationViewModel;
    private ObserverCallBack<JavaResponse<ApplicationResponse>> mObserver;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_application, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        mApplicationViewModel = ViewModelProviders.of(this).get(ApplicationViewModel.class);
        mObserver = new ObserverCallBack<JavaResponse<ApplicationResponse>>() {
            @Override
            public void onSuccess(JavaResponse<ApplicationResponse> pApplicationResponseJavaResponse) {
                mApplicationAdapter.onRefresh(pApplicationResponseJavaResponse.getResult().getList());
                mSrlSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(JavaResponse pJavaResponse) {
                mSrlSwipeRefreshLayout.setRefreshing(false);
            }
        };
        mApplicationViewModel.loadApplicationList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserver);
    }

    private void initView(@NonNull View view) {
        mRlRecyclerView = view.findViewById(R.id.rl_recycler_view_pm);
        mSrlSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl_SwipeRefreshLayout_pm);
        mSrlSwipeRefreshLayout.setOnRefreshListener(this);
        mApplicationAdapter = new ApplicationAdapter();
        mRlRecyclerView.setAdapter(mApplicationAdapter);
        mRlRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        MainUtils.upTitleAndIco(getContext(), "我的App", R.drawable.ic_view_headline_black_24dp, v -> MainUtils.showUserInfo(getContext()));
        fabFab.setOnClickListener(v -> MainUtils.getI().startFragment(new Intent(getContext(),EditAppFragment.class)));
        mApplicationAdapter.setOnItemClickListener(this);
    }


    @Override
    public void onRefresh() {
        mApplicationViewModel.loadApplicationList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserver);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MainUtils.upTitleAndIco(getContext(), "my app", R.drawable.ic_view_headline_black_24dp, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainUtils.showUserInfo(getContext());
            }
        });
        mApplicationViewModel.loadApplicationList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserver);
    }

    @Override
    public void onItemClick(View pView, int pI) {
        ApplicationBean lItem = mApplicationAdapter.getItem(pI);
        Intent lIntent = new Intent(getContext(), PluginListFragment.class);
        lIntent.putExtra("app_id",lItem.getApp_id());
        MainUtils.getI().startFragment(lIntent);
    }
}
