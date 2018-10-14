package org.daimhim.tochdirection;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private String TAG = "TAG:" + getClass().getSimpleName();
    private int startX;
    private int startY;
    private int offsetsByX;
    private int offsetsByY;
    private int movedX;
    private int movedY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        findViewById(R.id.v_test).setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        Log.d(TAG, "落下");
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        Log.d(TAG, "MOVE");
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        Log.d(TAG, "抬起");
//                        break;
//                }
//                return true;
//            }
//        });
//        final View lViewById = findViewById(R.id.tv_content);
//        lViewById.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View pView, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        Log.d(TAG, "落下");
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        Log.d(TAG, "MOVE");
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        Log.d(TAG, "抬起");
//                        break;
//                }
//                return false;
//            }
//        });

        findViewById(R.id.rl_layout).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d(TAG, "落下");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.d(TAG, "MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d(TAG, "抬起");
                        break;
                }
                return true;
            }
        });

//        findViewById(R.id.vl_layout).setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        Log.d(TAG, "落下");
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        Log.d(TAG, "MOVE");
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        Log.d(TAG, "抬起");
//                        break;
//                }
////                lViewById.dispatchTouchEvent(event);
//                return true;
//            }
//        });
    }
}
