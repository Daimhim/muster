package org.daimhim.acdemo;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainViewModel lMainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
//        lMainViewModel.
    }
}
