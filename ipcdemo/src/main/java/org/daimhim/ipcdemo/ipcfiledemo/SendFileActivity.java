package org.daimhim.ipcdemo.ipcfiledemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.daimhim.ipcdemo.R;
import org.daimhim.ipcdemo.StartApp;
import org.daimhim.ipcdemo.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * 项目名称：org.daimhim.ipcdemo.ipcfiledemo
 * 项目版本：muster
 * 创建时间：2018.08.14 11:10  星期二
 * 创建人：Daimhim
 * 修改时间：2018.08.14 11:10  星期二
 * 类描述：Daimhim 太懒了，什么都没有留下
 * 修改备注：Daimhim 太懒了，什么都没有留下
 *
 * @author：Daimhim
 */
public class SendFileActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = getClass().getSimpleName();
    public final static String IPC_FILE_PATH = getSystemFilePath(StartApp.getInstance()) + "/crash/";
    public final static String IPC_FILE_NAME = "ipcFile.txt";
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
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        File file = new File(IPC_FILE_PATH);
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        User user = new User();
                        user.setUserId("45612348");
                        user.setUserImg("https://img-blog.csdn.net/20150410145423096?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvQnVhYXJvaWQ=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center");
                        user.setUserName("name");
                        user.setUserSex("女");
                        ObjectOutputStream outputStream = null;
                        try {
                            file = new File(IPC_FILE_PATH+IPC_FILE_NAME);
                            file.createNewFile();
                            outputStream = new ObjectOutputStream(new FileOutputStream(file));
                            outputStream.writeObject(user);
                            Log.d(TAG,user.toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                if (outputStream != null) {
                                    outputStream.flush();
                                    outputStream.close();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
                startActivity(new Intent(this,ReceiveFileActivity.class));
                break;
        }
    }
    private static String getSystemFilePath(Context context) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return cachePath;
    }
}
