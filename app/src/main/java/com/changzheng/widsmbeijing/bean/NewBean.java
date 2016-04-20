package com.changzheng.widsmbeijing.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: changzheng on 16/4/20 16 19.
 * 邮箱地址: 909459046@qq.com
 * 联系电话: 18098379524
 */
public class NewBean {
    public class NewItem {

        // 由于使用gson 代码只有两行

        // 取出变量值 要求知道 存放在哪个变量上
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

    public class TopicItem {

        public String title;
        public String id;
        public String url;
        public String listimage;
        public String description;
        public String sort;
    }

    public class ListNewItem {

        public String id;
        public String title;
        public String url;
        public String listimage;
        public String pubdate;
        public boolean comment;
        public String commenturl;
        public String type;
        public String commentlist;
    }

    public class Data {
        public String title;
        public List<NewItem> topnews = new ArrayList<NewItem>();
        public List<TopicItem> topic = new ArrayList<TopicItem>();
        public List<ListNewItem> news = new ArrayList<ListNewItem>();
        public String countcommenturl;
        public String more;

    }

    public int retcode = 0;
    public Data data=new Data();
}
