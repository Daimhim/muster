package org.daimhim.fragmentdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * 项目名称：org.daimhim.fragmentdemo
 * 项目版本：muster
 * 创建时间：2018/11/5 20:49  星期一
 * 创建人：Administrator
 * 修改时间：2018/11/5 20:49  星期一
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        MainUtils.getI().init(mContext,R.id.fl_layout);
        initView();
    }

    private void initView() {
//        startActivityForResult();
//        setResult();
        findViewById(R.id.bt_01).setOnClickListener(this);
        findViewById(R.id.bt_02).setOnClickListener(this);
        findViewById(R.id.bt_03).setOnClickListener(this);
        findViewById(R.id.bt_04).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt_01:
//                startActivity();
                Intent lIntent = new Intent(mContext, DefaultFragment.class);
                lIntent.putExtra("value","0");
                MainUtils.getI().startFragment(lIntent);
                break;
            case R.id.bt_02:

                break;
            case R.id.bt_03:
                break;
            case R.id.bt_04:
                break;
        }
    }
}
