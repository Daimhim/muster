package org.daimhim.ipcdemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * @author Daimhim
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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
}
