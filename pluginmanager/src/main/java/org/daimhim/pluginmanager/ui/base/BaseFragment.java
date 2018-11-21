package org.daimhim.pluginmanager.ui.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.daimhim.pluginmanager.R;
import org.daimhim.pluginmanager.ui.main.MainUtils;
import org.daimhim.pluginmanager.ui.version.VersionListFragment;


/**
 * 项目名称：org.daimhim.pluginmanager.ui.base
 * 项目版本：muster
 * 创建时间：2018/10/29 16:14  星期一
 * 创建人：Administrator
 * 修改时间：2018/10/29 16:14  星期一
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public abstract class BaseFragment extends Fragment implements BackHandledInterface {
    protected final String TAG = getClass().getSimpleName();
    protected Context pContext;
    @Override
    public boolean onBackPressed() {
        MainUtils.getI().finishFragment(this);
        return false;
    }

    public String getTags() {
        return "TAG:" + getClass().getSimpleName();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(getTags(), "onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(getTags(), "onViewCreated");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context lContext = getContext();
        pContext = lContext;
        Log.i(getTags(), "onCreate");
    }
    @Override
    public Context getContext() {
        return pContext;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(getTags(), "onAttach");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(getTags(), "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(getTags(), "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(getTags(), "onResume");
    }

    public void finishFragment() {
        MainUtils.getI().finishFragment(this);
    }

    public void finishFragment(int resultCode) {
        MainUtils.getI().finishFragment(this, resultCode);
    }

    public void finishFragment(int resultCode, Intent pIntent) {
        MainUtils.getI().finishFragment(this, resultCode, pIntent);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(getTags(), "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(getTags(), "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(getTags(), "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(getTags(), "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(getTags(), "onDetach");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(getTags(), "onActivityResult");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.i(getTags(), "setUserVisibleHint:" + isVisibleToUser);
    }
}
