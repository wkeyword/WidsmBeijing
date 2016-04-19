package com.changzheng.widsmbeijing.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 作者: changzheng on 16/4/19 22 33.
 * 邮箱地址: 909459046@qq.com
 * 联系电话: 18098379524
 */
public class Sputils {
    /**
     * 保存变量
     * @author changzheng
     * @time 16/4/19 下午10:51
     * email: 909459046@qq.com
     */
    public static void save(Context context,String key,Object object){
        SharedPreferences sp=context.getSharedPreferences("config",Context.MODE_PRIVATE);
        Editor editor=sp.edit();
        if (object instanceof String ){
            editor.putString(key, (String) object);
        }else {
            editor.putBoolean(key, (Boolean) object);
        }
        
        editor.commit();
    }

    public static SharedPreferences get(Context context){
        SharedPreferences sp=context.getSharedPreferences("config",Context.MODE_PRIVATE);
        return sp;
    }
}
