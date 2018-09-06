package org.daimhim.pagingdemo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRvRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        MainViewModel lMainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        // Create the observer which updates the UI.
        final Observer<UserBean> nameObserver = new Observer<UserBean>() {
            @Override
            public void onChanged(@Nullable final UserBean newName) {
                // Update the UI, in this case, a TextView.

            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        lMainViewModel.getCurrentName().observe(this, nameObserver);
    }

    private void initView() {
        mRvRecyclerView = (RecyclerView) findViewById(R.id.rv_RecyclerView);
//        mRvRecyclerView.setAdapter();
    }
}
