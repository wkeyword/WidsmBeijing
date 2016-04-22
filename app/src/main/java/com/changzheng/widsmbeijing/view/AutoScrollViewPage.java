package com.changzheng.widsmbeijing.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.changzheng.widsmbeijing.R;
import com.changzheng.widsmbeijing.chache.ImageCache;

import org.apache.http.conn.scheme.HostNameResolver;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: changzheng on 16/4/21 20 33.
 * 邮箱地址: 909459046@qq.com
 * 联系电话: 18098379524
 */
public class AutoScrollViewPage extends ViewPager {


    public AutoScrollViewPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        imageCache = new ImageCache(getContext());
    }


    private ImageCache imageCache = null;
    private List<String> imageUrls = new ArrayList<String>();
    private List<String> titles;
    private TextView titleView;

    public void setTitles(List<String> titles, TextView titleView) {
        this.titles = titles;
        this.titleView = titleView;
        this.titleView.setText(titles.get(0));
    }

    private int pageCount = 2;
    private boolean isLooping = false;//是否支持无限滑动

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                int curr = getCurrentItem();
                ++curr;
                setCurrentItem(curr);

                Message msg2 = handler.obtainMessage();
                msg.what = 1;
                handler.sendMessageDelayed(msg2, 3000);
                //实现无限循环发送信息
            }
        }
    };


    public void startScroll() {
        Message msg = handler.obtainMessage();
        msg.what = 1;
        handler.sendMessageDelayed(msg, 3000);
    }

    //停止播放
    public void stopScroll() {
        handler.removeCallbacksAndMessages(null);
    }

    private List<ImageView> dotc = new ArrayList<ImageView>();

    public void init(int pageNumber, LinearLayout layout) {
        pageCount = pageNumber;
        for (int i = 0; i < pageNumber; i++) {
            ImageView img = new ImageView(getContext());
            img.setBackgroundResource(R.drawable.dot_selector);
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            p.rightMargin = 6;
            layout.addView(img, p);
            dotc.add(img);
        }
        dotc.get(0).setSelected(true);
        this.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                if (isLooping) {
                    return Integer.MAX_VALUE;
                } else {
                    return pageCount;
                }
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
//                return super.instantiateItem(container, position);
                ImageView img = new ImageView(getContext());
                img.setScaleType(ImageView.ScaleType.CENTER_CROP);
                img.setBackgroundResource(R.mipmap.home_scroll_default);
                LayoutParams p = new LayoutParams();
                p.width = LayoutParams.MATCH_PARENT;
                p.height = LayoutParams.MATCH_PARENT;
                container.addView(img, p);

                if (imageUrls != null && imageUrls.size() > 0) {
                    imageCache.displayImage(img, imageUrls.get(position % pageCount));
                }

                img.setOnTouchListener(new OnTouchListener() {
                    private int downX = 0;
                    private long downTime = 0;

                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                stopScroll();
                                downX = (int) event.getX();//获取按下位置
                                downTime = System.currentTimeMillis();//按下的时间
                                break;
                            case MotionEvent.ACTION_MOVE:
                                break;
                            case MotionEvent.ACTION_UP:
                                int upX = (int) event.getX();
                                if (downX == upX && System.currentTimeMillis() - downTime < 300) {
                                    if (listener!=null){
                                        listener.onItemClick();
                                    }
                                }
                                startScroll();
                                break;

                            case MotionEvent.ACTION_CANCEL:
                                startScroll();
                                break;
                        }
                        return true;
                    }
                });
                return img;
            }

            //移除显示
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
//                super.destroyItem(container, position, object);
                container.removeView((ImageView) object);
            }
        });

    }

    public static interface OnViewItemClickListener {
        public void onItemClick();

    }

    private OnViewItemClickListener listener;

}
