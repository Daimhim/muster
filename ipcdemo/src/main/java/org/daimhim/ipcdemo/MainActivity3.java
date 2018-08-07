package org.daimhim.ipcdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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
public class MainActivity3 extends AppCompatActivity {

    /**
     * Hello World!
     */
    private TextView mTvTag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mTvTag = (TextView) findViewById(R.id.tv_tag);
        mTvTag.setText(getClass().getSimpleName());
        mTvTag.append("/n");
        IPCHelp.getProcessInfo(this);
        mTvTag.append("/n");
    }
}
