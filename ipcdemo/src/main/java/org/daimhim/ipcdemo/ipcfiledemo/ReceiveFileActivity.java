package org.daimhim.ipcdemo.ipcfiledemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.daimhim.ipcdemo.R;
import org.daimhim.ipcdemo.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * 项目名称：org.daimhim.ipcdemo.ipcfiledemo
 * 项目版本：muster
 * 创建时间：2018.08.14 11:12  星期二
 * 创建人：Daimhim
 * 修改时间：2018.08.14 11:12  星期二
 * 类描述：Daimhim 太懒了，什么都没有留下
 * 修改备注：Daimhim 太懒了，什么都没有留下
 *
 * @author：Daimhim
 */
public class ReceiveFileActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = getClass().getSimpleName();
    /**
     * Hello World!
     */
    private Button mTvTag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
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
                File file = new File(SendFileActivity.IPC_FILE_PATH + SendFileActivity.IPC_FILE_NAME);
                ObjectInputStream inputStream = null;
                User user = null;
                try {
                    inputStream = new ObjectInputStream(new FileInputStream(file));
                    user = (User) inputStream.readObject();
                    Log.d(TAG,user.toString());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        if (inputStream != null) {
                            inputStream.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}
