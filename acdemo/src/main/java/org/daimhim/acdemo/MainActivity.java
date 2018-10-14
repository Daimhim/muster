package org.daimhim.acdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = findViewById(R.id.tv_content);
        tv.setText(TextUtils.concat(
                getString(R.string.withdrawal_amount), "500.00", "\n",
                getString(R.string.withdrawal_fee), "500.00", "\n",
                getString(R.string.total_withdrawal), "500.00", "\n"
        ).toString());
//        MainViewModel lMainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
//        lMainViewModel.
    }
}
