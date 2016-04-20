package com.changzheng.widsmbeijing.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: changzheng on 16/4/20 16 23.
 * 邮箱地址: 909459046@qq.com
 * 联系电话: 18098379524
 */
public class TopnewsData {
    public int retcode;
    public Data data = new Data();

    public class Data {

        public String countcommenturl;
        public List<TopNewItem> topnews = new ArrayList<TopNewItem>();
    }

    public static class TopNewItem {
        public String id;
        public String title;
        public String topimage;
        public String url;
        public String pubdate;
        public String comment;
        public String commenturl;
        public String type;
        public String commentlist;
    }
}
