package com.changzheng.widsmbeijing.utils;

import android.util.Log;

/**项目是否上线去判断LOG是否输出信息
 * 作者: changzheng on 16/4/19 23 00.
 * 邮箱地址: 909459046@qq.com
 * 联系电话: 18098379524
 */

public class LogUtil {
    public static boolean DEBUG = true;//上线后会修改为false

    public static void i(String tag, Object obj) {
        if (DEBUG) {
            Log.i(tag, obj + "");
        }
    }
}
