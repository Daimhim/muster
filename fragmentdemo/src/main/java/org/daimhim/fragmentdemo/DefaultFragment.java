package org.daimhim.fragmentdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
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
        mTextView = new TextView(getContext());
        LinearLayout.LayoutParams lLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mTextView.setLayoutParams(lLayoutParams);
        mTextView.setGravity(Gravity.CENTER);
        mTextView.setText(getClass().getSimpleName());
        return mTextView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle lArguments = getArguments();
        if (null!=lArguments && !TextUtils.isEmpty(lArguments.getString("value"))) {
            mTextView.setText(lArguments.getString("value"));
        }
    }
}
