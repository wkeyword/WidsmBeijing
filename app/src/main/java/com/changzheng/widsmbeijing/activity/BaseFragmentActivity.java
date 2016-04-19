package com.changzheng.widsmbeijing.activity;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

/**
 * 作者: changzheng on 16/4/19 20 41.
 * 邮箱地址: 909459046@qq.com
 * 联系电话: 18098379524
 */
public class BaseFragmentActivity extends FragmentActivity {
    protected void startPage(Class<?> activity) {
        Intent intent = new Intent(getBaseContext(), activity);
        startActivity(intent);
    }

    /**
     * 添加片段
     *
     * @author changzheng
     * @time 16/4/19 下午8:57
     * email: 909459046@qq.com
     */
    protected void add(int layoutId, android.support.v4.app.Fragment f, String tag) {

//        获取管理者
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
//        打开事务
        FragmentTransaction ft = fm.beginTransaction();
//        增加
        ft.add(layoutId, f, tag);
//        提交
        ft.commit();
    }

    /**
     * 片段替换
     *
     * @author changzheng
     * @time 16/4/19 下午9:14
     * email: 909459046@qq.com
     */
    protected void replace(int layoutId, android.support.v4.app.Fragment f, String tag) {

        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(layoutId, f, tag);
    }

    /**
     * 移除片段
     *
     * @author changzheng
     * @time 16/4/19 下午9:15
     * email: 909459046@qq.com
     */
    protected void remove(android.support.v4.app.Fragment f) {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(f);
        ft.commit();
    }

    /**
     * 通过 tag 来查找片段
     *
     * @author changzheng
     * @time 16/4/19 下午9:17
     * email: 909459046@qq.com
     */
    protected android.support.v4.app.Fragment find(String tag) {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.Fragment f = fm.findFragmentByTag(tag);

        return f;
    }
}
