package com.changzheng.widsmbeijing.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: changzheng on 16/4/20 16 15.
 * 邮箱地址: 909459046@qq.com
 * 联系电话: 18098379524
 */
public class CategoryData {
    public int retcode;
    public List<CategoryItem> data = new ArrayList<CategoryItem>();
    public List<String> extend = new ArrayList<String>();

    public static class CategoryItem {
        public String id;
        public String title;
        public String type;
        public String url;
    }
}
