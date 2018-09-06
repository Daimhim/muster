    package org.daimhim.homepage;

    import android.content.Context;
    import android.location.Location;
    import android.os.Bundle;
    import android.os.PersistableBundle;
    import android.support.annotation.Nullable;
    import android.support.v4.content.ContextCompat;
    import android.support.v7.app.AppCompatActivity;
    import android.util.Log;

    import com.amap.api.maps.AMap;
    import com.amap.api.maps.CameraUpdateFactory;
    import com.amap.api.maps.LocationSource;
    import com.amap.api.maps.MapView;
    import com.amap.api.maps.UiSettings;
    import com.amap.api.maps.model.CircleOptions;
    import com.amap.api.maps.model.LatLng;
    import com.amap.api.maps.model.MyLocationStyle;

public class MainActivity extends AppCompatActivity {
    private String TAG = "TAG:" + getClass().getSimpleName();
    private MapView mMvMap;
    private AMap mMMvMapMap;
    private Context context;
    private CircleOptions mCircleOptions;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_main);
         mMvMap = (MapView) findViewById(R.id.mv_map);
        mMvMap.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMvMap.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMvMap.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMvMap.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        mMvMap.onSaveInstanceState(outState);
    }

    private void initView() {

        mMMvMapMap = mMvMap.getMap();
        MyLocationStyle lMyLocationStyle = new MyLocationStyle();
        lMyLocationStyle.showMyLocation(true);
        lMyLocationStyle.interval(1000);
//        lMyLocationStyle.strokeWidth(500);
        lMyLocationStyle.strokeColor(ContextCompat.getColor(this,R.color.cl_000000));
        lMyLocationStyle.radiusFillColor(ContextCompat.getColor(this,R.color.cl_00000000));
        lMyLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);
//        lMyLocationStyle.myLocationIcon();
        mMMvMapMap.setMyLocationStyle(lMyLocationStyle);
        mMMvMapMap.setMyLocationEnabled(true);

        UiSettings lUiSettings = mMMvMapMap.getUiSettings();
        lUiSettings.setMyLocationButtonEnabled(true);
        lUiSettings.setTiltGesturesEnabled(false);
        lUiSettings.setRotateGesturesEnabled(false);

        //定义缩放级别
        mMMvMapMap.moveCamera(CameraUpdateFactory.zoomTo(15));
        mMMvMapMap.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location pLocation) {
                LatLng lLatLng = new LatLng(pLocation.getLatitude(),
                        pLocation.getLongitude());
                Log.i(TAG,pLocation.toString());
                if (mCircleOptions == null) {
                    mCircleOptions = new CircleOptions()
                            .radius(50)
                            .fillColor(ContextCompat.getColor(context, R.color.colorAccent))
                            .strokeColor(ContextCompat.getColor(context, R.color.colorAccent))
                            .strokeWidth(1)
                            .center(lLatLng);
                    mMMvMapMap.addCircle(mCircleOptions);
                }else {
                    mMMvMapMap.clear(true);
//                    mMvMap.invalidate();
                    mCircleOptions.center(lLatLng);
                    mMMvMapMap.addCircle(mCircleOptions);
                }
            }
        });
        mMMvMapMap.setLocationSource(new LocationSource() {
            @Override
            public void activate(OnLocationChangedListener pOnLocationChangedListener) {
//                pOnLocationChangedListener.onLocationChanged();
            }

            @Override
            public void deactivate() {

            }
        });
    }

}
