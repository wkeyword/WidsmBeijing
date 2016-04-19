package com.changzheng.widsmbeijing.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.changzheng.widsmbeijing.R;
/**
 * 进入app第一屏的activity
 * @author changzheng
 * @time 16/4/19 下午9:56
 * email: 909459046@qq.com
 */
public class SplashActivity extends BaseFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Message message=new Message();
        message.what=0;
        handler.sendMessageDelayed(message,3000);
    }

    private Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            startPage(GuideActivity.class);
            Log.i("wcz","--进入了下一页");
            finish();
        }
    };
}
