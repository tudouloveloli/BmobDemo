package me.microcool.bmobdemo;

import android.app.Application;
import android.util.Log;


import cn.bmob.push.BmobPush;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.BmobInstallationManager;
import cn.bmob.v3.InstallationListener;
import cn.bmob.v3.exception.BmobException;

/**
 * App
 * @author gaoshiwei
 * @date 2017/11/2
 */

public class App extends Application {
    private static final String TAG = "App";

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化数据服务SDK
        Bmob.initialize(this, "45401b924f7f4b7b33d8ce70609d92b7");

        //保存设备信息，用于推送功能
        BmobInstallationManager.getInstance().initialize(new InstallationListener<BmobInstallation>() {
            @Override
            public void done(BmobInstallation bmobInstallation, BmobException e) {
                if (e == null) {
                    Log.d(TAG, bmobInstallation.getObjectId() + "-" + bmobInstallation.getInstallationId());
                } else {
                    Log.d(TAG, e.getMessage());
                }
            }
        });
        /**
         * 启动推送服务
         */
        BmobPush.startWork(this);
    }
}