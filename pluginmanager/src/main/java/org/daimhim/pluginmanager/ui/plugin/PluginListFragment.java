package org.daimhim.pluginmanager.ui.plugin;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PluginListFragment extends BaseFragment {

    @BindView(R.id.rl_recycler_view_pm)
    RecyclerView rlRecyclerViewPm;
    @BindView(R.id.srl_SwipeRefreshLayout_pm)
    SwipeRefreshLayout srlSwipeRefreshLayoutPm;
    @BindView(R.id.fab_fab_pm)
    FloatingActionButton fabFabPm;
    Unbinder unbinder;
    private PluginViewModel mPluginViewModel;
    private String mPluginId;

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
        MainUtils.upTitleAndIco(getContext(), "插件管理", R.drawable.ic_view_headline_black_24dp, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainUtils.backFragment(getContext());
            }
        });
        Bundle lArguments = getArguments();
        if (lArguments!=null) {
            mPluginId = lArguments.getString("pluginId");
            mPluginViewModel.getPluginList(mPluginId)
                    .subscribe(new ObserverCallBack<JavaResponse<PluginResponse>>() {
                        @Override
                        public void onSuccess(JavaResponse<PluginResponse> pPluginResponseJavaResponse) {

                        }

                        @Override
                        public void onFailure(JavaResponse pJavaResponse) {

                        }
                    });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}