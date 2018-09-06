package org.daimhim.muster;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mVpViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        mVpViewPager = (ViewPager) findViewById(R.id.vp_ViewPager);
        ArrayList<String> lStrings = new ArrayList<>();
        lStrings.add("http://www.jpgjpg.com/wp-content/uploads/2016/05/1-28.jpg");
        lStrings.add("https://res.cloudinary.com/jerrick/image/upload/f_auto,fl_progressive,q_auto,c_fit,w_1560/hiqdgblbaov3yojctij9");
        lStrings.add("https://thumbs.mic.com/M2JhNzNhOWRjNiMvVmtSNjlpWkhIQ2w1eldKRGdaaXd5SDR3bVhrPS8xMTN4MDoxNzg0eDg2NC8xNjAweDkwMC9maWx0ZXJzOmZvcm1hdChqcGVnKTpxdWFsaXR5KDgwKS9odHRwczovL3MzLmFtYXpvbmF3cy5jb20vcG9saWN5bWljLWltYWdlcy9uNHFra3pvcG4wZWo2emFtYmw4b3B1dmk1OTA3ZXpscHkwdGNrcWxtcnBjejVzZ3k1YTlwNGJ1M2FpeGloaWhoLmpwZw.jpg");
        lStrings.add("https://madisonmarquette.com/wp-content/uploads/2013/04/southwest-waterfront-02.jpg");
        lStrings.add("http://dpgzjp4u334u3.cloudfront.net/wp-content/uploads/2013/10/DC.jpg");
        mVpViewPager.setAdapter(new MainPagerAdapter(lStrings));
    }
    static class MainPagerAdapter extends PagerAdapter{
        private List<String> mStrings;

        public MainPagerAdapter(List<String> pStrings) {
            mStrings = pStrings;
        }

        @Override
        public int getCount() {
            return mStrings.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            ImageView lChild = new ImageView(container.getContext());
            ViewGroup.LayoutParams lLayoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            lChild.setLayoutParams(lLayoutParams);
            container.addView(lChild);
            return lChild;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
