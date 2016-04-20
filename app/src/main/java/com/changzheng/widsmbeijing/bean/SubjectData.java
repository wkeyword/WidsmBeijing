package com.changzheng.widsmbeijing.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: changzheng on 16/4/20 16 22.
 * 邮箱地址: 909459046@qq.com
 * 联系电话: 18098379524
 */
public class SubjectData {
    // "retcode":200,
    // "data":
    public int retcode;
    public Data data = new Data();

    public class NewItem {
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

    public class TopicItem {
        public String title;
        public String id;
        public String url;
        public String listimage;
        public String description;
        public String sort;
    }

    public class TopNewItem {
        public String id;
        public String title;
        public String topimage;
        public String url;
        public String pubdate;
        public boolean comment;
        public String commenturl;
        public String type;
        public String commentlist;
    }

    public class Data {
        public String title;
        public String countcommenturl;
        public String more;
        public List<TopNewItem> topnews = new ArrayList<TopNewItem>();
        public List<TopicItem> topic = new ArrayList<TopicItem>();
        public List<NewItem> news = new ArrayList<NewItem>();
    }

}
