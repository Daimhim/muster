package org.daimhim.pluginmanager.ui.app;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.daimhim.pluginmanager.R;
import org.daimhim.pluginmanager.model.ObserverCallBack;
import org.daimhim.pluginmanager.model.response.ApplicationResponse;
import org.daimhim.pluginmanager.model.response.JavaResponse;
import org.daimhim.pluginmanager.ui.main.MainUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


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
public class ApplicationFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    RecyclerView mRlRecyclerView;
    SwipeRefreshLayout mSrlSwipeRefreshLayout;
    @BindView(R.id.fab_fab)
    FloatingActionButton fabFab;
    Unbinder unbinder;
    private ApplicationAdapter mApplicationAdapter;

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
        ApplicationViewModel lApplicationViewModel = ViewModelProviders.of(this).get(ApplicationViewModel.class);
        lApplicationViewModel.loadApplicationList()
                .subscribe(new ObserverCallBack<JavaResponse<ApplicationResponse>>() {
                    @Override
                    protected void onSuccess(JavaResponse<ApplicationResponse> pApplicationResponseJavaResponse) {
                        mApplicationAdapter.onRefresh(pApplicationResponseJavaResponse.getResult().getList());
                        mSrlSwipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    protected void onFailure(JavaResponse pJavaResponse) {
                        mSrlSwipeRefreshLayout.setRefreshing(false);
                    }
                });
    }

    private void initView(@NonNull View view) {
        mRlRecyclerView = view.findViewById(R.id.rl_recycler_view_pm);
        mSrlSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl_SwipeRefreshLayout_pm);
        mSrlSwipeRefreshLayout.setOnRefreshListener(this);
        mApplicationAdapter = new ApplicationAdapter();
        mRlRecyclerView.setAdapter(mApplicationAdapter);
        MainUtils.upTitleAndIco(getContext(), "我的App", R.drawable.ic_view_headline_black_24dp, v -> MainUtils.showUserInfo(getContext()));
        fabFab.setOnClickListener(v -> {
            MainUtils.superimposedFragment(getContext(),EditAppFragment.class);
        });
    }

    @Override
    public void onRefresh() {
        ViewModelProviders.of(this).get(ApplicationViewModel.class).loadApplicationList();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
