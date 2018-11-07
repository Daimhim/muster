package org.daimhim.fragmentdemo;

import android.content.Context;
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
    private String TAG = "TAG:"+getClass().getSimpleName();
    public static DefaultFragment newInstance(String value) {

        Bundle args = new Bundle();
        args.putString("value",value);
        DefaultFragment fragment = new DefaultFragment();
        fragment.setArguments(args);
        return fragment;
    }
    private TextView mTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (Integer.valueOf(getArguments().getString("value"))%2==0) {
            Log.i(TAG, "onCreateView");
        }else {
            Log.d(TAG, "onCreateView");
        }
        mTextView = new TextView(getContext());
        LinearLayout.LayoutParams lLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mTextView.setLayoutParams(lLayoutParams);
        mTextView.setGravity(Gravity.CENTER);
        mTextView.setText(getClass().getSimpleName());
        mTextView.setTextColor(ContextCompat.getColor(getContext(),R.color.cl_000000));
        return mTextView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle lArguments = getArguments();
        if (null!=lArguments && !TextUtils.isEmpty(lArguments.getString("value"))) {
            mTextView.setText(lArguments.getString("value"));
        }
        if (Integer.valueOf(getArguments().getString("value"))%2==0) {
            Log.i(TAG, "onViewCreated");
        }else {
            Log.d(TAG, "onViewCreated");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Integer.valueOf(getArguments().getString("value"))%2==0) {
            Log.i(TAG, "onCreate");
        }else {
            Log.d(TAG, "onCreate");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (Integer.valueOf(getArguments().getString("value"))%2==0) {
            Log.i(TAG, "onAttach");
        }else {
            Log.d(TAG, "onAttach");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (Integer.valueOf(getArguments().getString("value"))%2==0) {
            Log.i(TAG, "onActivityCreated");
        }else {
            Log.d(TAG, "onActivityCreated");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Integer.valueOf(getArguments().getString("value"))%2==0) {
            Log.i(TAG, "onStart");
        }else {
            Log.d(TAG, "onStart");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Integer.valueOf(getArguments().getString("value"))%2==0) {
            Log.i(TAG, "onResume");
        }else {
            Log.d(TAG, "onResume");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Integer.valueOf(getArguments().getString("value"))%2==0) {
            Log.i(TAG, "onPause");
        }else {
            Log.d(TAG, "onPause");
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Integer.valueOf(getArguments().getString("value"))%2==0) {
            Log.i(TAG, "onStop");
        }else {
            Log.d(TAG, "onStop");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (Integer.valueOf(getArguments().getString("value"))%2==0) {
            Log.i(TAG, "onDestroyView");
        }else {
            Log.d(TAG, "onDestroyView");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (Integer.valueOf(getArguments().getString("value"))%2==0) {
            Log.i(TAG, "onDestroy");
        }else {
            Log.d(TAG, "onDestroy");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (Integer.valueOf(getArguments().getString("value"))%2==0) {
            Log.i(TAG, "onDetach");
        }else {
            Log.d(TAG, "onDetach");
        }
    }
}
