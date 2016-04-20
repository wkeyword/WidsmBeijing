package com.changzheng.widsmbeijing.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: changzheng on 16/4/20 16 18.
 * 邮箱地址: 909459046@qq.com
 * 联系电话: 18098379524
 */
public class ExResult {
    public String status;
    public String desc;
    public List<FunItem> detail = new ArrayList<FunItem>();

    public class FunItem {
        public String id;
        public String xhid;
        public String author;
        public String content;
        public String picUrl;
        public String status;
    }

}
