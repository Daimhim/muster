package org.daimhim.ipcdemo;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * @author Daimhim
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener, ServiceConnection {
    private IBookManager mBookManager;
    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            if (null == mBookManager){
                return;
            }
            mBookManager.asBinder().unlinkToDeath(mDeathRecipient,0);
            mBookManager = null;
            //重新绑定Service
        }
    };

   public static void start(Context context) {
       Intent starter = new Intent(context, MainActivity.class);
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

        bindService(new Intent(this,BookManagerService.class),this,Context.BIND_AUTO_CREATE);



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
                MainActivity2.start(this);
                break;
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        mBookManager = (IBookManager) service;

        IBookManager iBookManager = IBookManager.Stub.asInterface(service);
        try {
            service.linkToDeath(mDeathRecipient,0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }

}
