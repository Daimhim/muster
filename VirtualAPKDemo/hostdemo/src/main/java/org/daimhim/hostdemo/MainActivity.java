package org.daimhim.hostdemo;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.didi.virtualapk.PluginManager;
import com.didi.virtualapk.internal.LoadedPlugin;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = getClass().getSimpleName();
    /**
     * 加载插件1
     */
    private TextView mTvPlugin1;
    /**
     * 加载插件
     */
    private TextView mTvPlugin2;
    private PluginManager mPluginManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void loadPlugin(Context base) {
        if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            Toast.makeText(this, "sdcard was NOT MOUNTED!", Toast.LENGTH_SHORT).show();
        }
        mPluginManager = PluginManager.getInstance(base);
        try {
            String lChild = "org.daimhim.plugin1_20181229103206.apk";
            File file = new File(base.getFilesDir(), lChild);
            InputStream inputStream = base.getAssets().open(lChild, 2);
            FileOutputStream outputStream = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;

            while ((len = inputStream.read(buf)) > 0) {
                outputStream.write(buf, 0, len);
            }

            outputStream.close();
            inputStream.close();
            Log.i(TAG, "Loaded plugin from assets: " + file.getName());
            mPluginManager.loadPlugin(file);
            Log.i(TAG, "Loaded plugin from assets: " + file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        mTvPlugin1 = (TextView) findViewById(R.id.tv_plugin1);
        mTvPlugin1.setOnClickListener(this);
        mTvPlugin2 = (TextView) findViewById(R.id.tv_plugin2);
        mTvPlugin2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_plugin1:
                // 显式指定包名的方式
                Intent intent = new Intent();
                intent.setClassName("org.daimhim.plugin1", "org.daimhim.plugin1.myweixin.MainActivity");
                startActivity(intent);
                break;
            case R.id.tv_plugin2:
                loadPlugin(v.getContext());
                List<LoadedPlugin> lAllLoadedPlugins = mPluginManager.getAllLoadedPlugins();
                for (int i = 0; i < lAllLoadedPlugins.size(); i++) {
                    Log.i("TAG:"+TAG,lAllLoadedPlugins.get(i).getPackageName());
                }
                break;
        }
    }
}
