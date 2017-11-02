package me.microcool.bmobdemo;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import cn.bmob.push.PushConstants;

/**
 * @author gaoshiwei
 * @date 2017/11/2
 * TODO 集成：1.3、创建自定义的推送消息接收器，并在清单文件中注册
 */

public class MyPushMessageReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        if (intent.getAction().equals(PushConstants.ACTION_MESSAGE)) {
            Toast.makeText(context, intent.getStringExtra("msg"), Toast.LENGTH_LONG).show();
        }
    }

}