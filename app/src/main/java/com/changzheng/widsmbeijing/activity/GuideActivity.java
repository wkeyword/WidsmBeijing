package com.changzheng.widsmbeijing.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.changzheng.widsmbeijing.R;

/**
 * 引导界面
 *
 * @author changzheng
 * @time 16/4/19 下午10:14
 * email: 909459046@qq.com
 */
public class GuideActivity extends BaseFragmentActivity {
    private ViewPager vp_guide;
    private Button btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        vp_guide = (ViewPager) findViewById(R.id.vp_guide);
        btn_start = (Button) findViewById(R.id.btn_startgoHome);
        vp_guide.setAdapter(new vp_guideAdapter());

        vp_guide.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 2) {
                    btn_start.setVisibility(View.VISIBLE);
                } else {
                    btn_start.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPage(MainActivity.class);
                finish();
            }
        });

    }

    private class vp_guideAdapter extends PagerAdapter {
        private int[] imgId = new int[]{R.mipmap.guide_1, R.mipmap.guide_2, R.mipmap.guide_3};


        @Override
        public int getCount() {
            return imgId.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setBackgroundResource(imgId[position]);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }


}
