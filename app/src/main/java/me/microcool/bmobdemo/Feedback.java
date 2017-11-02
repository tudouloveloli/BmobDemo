package me.microcool.bmobdemo;

import cn.bmob.v3.BmobObject;

/**
 * @author gaoshiwei
 * @date 2017/11/1
 * 反馈类
 * 用户名+反馈信息
 */

public class Feedback extends BmobObject {
    String name;
    String faceback;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFaceback() {
        return faceback;
    }

    public void setFaceback(String faceback) {
        this.faceback = faceback;
    }
}
