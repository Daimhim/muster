package org.daimhim.fragmentdemo;

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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
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
                //clear
                FragmentTransaction lFragmentTransaction = getSupportFragmentManager().beginTransaction();

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
