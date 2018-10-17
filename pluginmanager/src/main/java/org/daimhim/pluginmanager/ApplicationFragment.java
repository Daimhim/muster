package org.daimhim.pluginmanager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_application, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);


    }

    private void initView(@NonNull View view) {
        mRlRecyclerView = view.findViewById(R.id.rl_recycler_view);
        mSrlSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl_SwipeRefreshLayout);
        mSrlSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {

    }
}
