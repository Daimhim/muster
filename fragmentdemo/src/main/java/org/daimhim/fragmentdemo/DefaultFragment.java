package org.daimhim.fragmentdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 项目名称：org.daimhim.fragmentdemo
 * 项目版本：muster
 * 创建时间：2018/11/5 21:11  星期一
 * 创建人：Administrator
 * 修改时间：2018/11/5 21:11  星期一
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class DefaultFragment extends Fragment {

    public String getTags() {
        return "TAG:" + getClass().getSimpleName() + ":" + (null == getArguments() ? "" : getArguments().getString("value"));
    }

    private TextView mTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(getTags(), "onCreateView");
        mTextView = new TextView(getContext());
        LinearLayout.LayoutParams lLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mTextView.setLayoutParams(lLayoutParams);
        mTextView.setGravity(Gravity.CENTER);
        mTextView.setText(getClass().getSimpleName());
        mTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.cl_000000));
        return mTextView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle lArguments = getArguments();
        if (null != lArguments && !TextUtils.isEmpty(lArguments.getString("value"))) {
            mTextView.setText(lArguments.getString("value"));
        }
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainUtils.getI().finishFragment(DefaultFragment.this);
            }
        });
        Log.i(getTags(), "onViewCreated");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(getTags(), "onCreate");
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
        Log.i(getTags(), "setUserVisibleHint:"+isVisibleToUser);
    }
}
