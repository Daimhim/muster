package org.daimhim.pluginmanager.ui.version;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.daimhim.pluginmanager.R;
import org.daimhim.pluginmanager.model.ObserverCallBack;
import org.daimhim.pluginmanager.model.response.ApkResponse;
import org.daimhim.pluginmanager.model.response.JavaResponse;
import org.daimhim.pluginmanager.ui.base.BaseFragment;
import org.daimhim.pluginmanager.ui.main.MainUtils;
import org.daimhim.pluginmanager.ui.plugin.PluginListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 项目名称：org.daimhim.pluginmanager.ui.version
 * 项目版本：muster
 * 创建时间：2018/11/13 17:29  星期二
 * 创建人：Administrator
 * 修改时间：2018/11/13 17:29  星期二
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class VersionListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rl_recycler_view_pm)
    RecyclerView rlRecyclerViewPm;
    @BindView(R.id.srl_SwipeRefreshLayout_pm)
    SwipeRefreshLayout srlSwipeRefreshLayoutPm;
    Unbinder unbinder;
    private String mPluginId;
    private VersionViewModel mVersionViewModel;
    private ObserverCallBack<JavaResponse<ApkResponse>> mObserver;
    private VersionAdapter mVersionAdapter;



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
        mVersionViewModel = ViewModelProviders.of(this).get(VersionViewModel.class);
        mObserver = new ObserverCallBack<JavaResponse<ApkResponse>>() {
            @Override
            public void onSuccess(JavaResponse<ApkResponse> pApkResponseJavaResponse) {
                mVersionAdapter.onRefresh(pApkResponseJavaResponse.getResult().getList());
            }
        };
        mVersionAdapter = new VersionAdapter();
        Bundle lArguments = getArguments();
        if (null != lArguments) {
            mPluginId = lArguments.getString("pluginId");
            mVersionViewModel.getAllVersion(mPluginId)
                    .subscribe(mObserver);
        }
        rlRecyclerViewPm.setAdapter(mVersionAdapter);
        srlSwipeRefreshLayoutPm.setOnRefreshListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onRefresh() {
        mVersionViewModel.getAllVersion(mPluginId)
                .subscribe(mObserver);
        srlSwipeRefreshLayoutPm.setRefreshing(false);
    }

    @OnClick(R.id.fab_fab_pm)
    public void onViewClicked() {
        Intent lIntent = new Intent(getContext(),VersionEditFragment.class);
        MainUtils.getI().starFragmentForResult(lIntent,50);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            switch (requestCode) {
                case 50:
                    break;
            }
        }
    }
}
