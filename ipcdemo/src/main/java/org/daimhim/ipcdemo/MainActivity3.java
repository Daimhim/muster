package org.daimhim.ipcdemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * 项目名称：org.daimhim.ipcdemo
 * 项目版本：muster
 * 创建时间：2018.08.07 10:46
 * 修改人：Daimhim
 * 修改时间：2018.08.07 10:46
 * 类描述：
 * 修改备注：
 *
 * @author：Daimhim
 */
public class MainActivity3 extends AppCompatActivity implements View.OnClickListener{
    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity3.class);
        context.startActivity(starter);
    }

    /**
     * Hello World!
     */
    private Button mTvTag;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mTvTag.setText(getClass().getSimpleName()+" : "+IPCHelp.getCurProcessName(this));
    }

    private void initView() {
        mTvTag = (Button) findViewById(R.id.tv_tag);
        mTvTag.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_tag:
                break;
        }
    }
}