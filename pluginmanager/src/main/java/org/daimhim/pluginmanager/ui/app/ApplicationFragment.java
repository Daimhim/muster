package org.daimhim.pluginmanager.ui.app;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;

import org.daimhim.pluginmanager.R;
import org.daimhim.pluginmanager.model.ObserverCallBack;
import org.daimhim.pluginmanager.model.bean.ApplicationBean;
import org.daimhim.pluginmanager.model.response.ApplicationResponse;
import org.daimhim.pluginmanager.model.response.JavaResponse;
import org.daimhim.pluginmanager.ui.base.BaseFragment;
import org.daimhim.pluginmanager.ui.main.MainUtils;
import org.daimhim.pluginmanager.ui.plugin.PluginListFragment;
import org.daimhim.rvadapter.RecyclerContract;

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
public class ApplicationFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
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
    }

    private void initView(@NonNull View view) {
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
        mRlRecyclerView = view.findViewById(R.id.rl_recycler_view_pm);
        mSrlSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl_SwipeRefreshLayout_pm);
        mSrlSwipeRefreshLayout.setOnRefreshListener(this);
        mApplicationAdapter = new ApplicationAdapter();
        mRlRecyclerView.setAdapter(mApplicationAdapter);
        mRlRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        fabFab.setOnClickListener(v -> MainUtils.getI().startFragment(new Intent(getContext(),EditAppFragment.class)));
        mApplicationAdapter.setOnItemClickListener(this::onItemClick);
        mApplicationAdapter.setpOnItemLongClickListener(this::onItemLongClick);
        GestureDetectorCompat lGestureDetectorCompat = new GestureDetectorCompat(getContext(),new GestureDetector.SimpleOnGestureListener(){
            //一次单独的轻触抬起手指操作，就是普通的点击事件
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                View childViewUnder = mRlRecyclerView.findChildViewUnder(e.getX(), e.getY());
                if (childViewUnder != null) {
                    RecyclerView.ViewHolder childViewHolder = mRlRecyclerView.getChildViewHolder(childViewUnder);
//                    onItemClick(childViewHolder);
                }
                return true;
            }

            //长按屏幕超过一定时长，就会触发，就是长按事件
            @Override
            public void onLongPress(MotionEvent e) {
                View childViewUnder = mRlRecyclerView.findChildViewUnder(e.getX(), e.getY());
                if (childViewUnder != null) {
                    RecyclerView.ViewHolder childViewHolder = mRlRecyclerView.getChildViewHolder(childViewUnder);
//                    onLongClick(childViewHolder);
                }
            }
        });
        mRlRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView pRecyclerView, @NonNull MotionEvent pMotionEvent) {
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView pRecyclerView, @NonNull MotionEvent pMotionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean pB) {

            }
        });
    }


    @Override
    public void onRefresh() {
        mApplicationViewModel.loadApplicationList()
                .subscribe(mObserver);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        initTitle();
        mApplicationViewModel.loadApplicationList()
                .subscribe(mObserver);
    }

    private void initTitle() {
        MainUtils.upTitleAndIco(getContext(), "我的App", R.drawable.ic_view_headline_black_24dp, v -> MainUtils.showUserInfo(getContext()));
    }

    public void onItemClick(View pView, int pI) {
        ApplicationBean lItem = mApplicationAdapter.getItem(pI);
        Intent lIntent = new Intent(getContext(), PluginListFragment.class);
        lIntent.putExtra("app_id",lItem.getApp_id());
        startFragment(lIntent);
    }

    public void onItemLongClick(View pView, int pI) {
        ApplicationBean lItem = mApplicationAdapter.getItem(pI);

    }
}
