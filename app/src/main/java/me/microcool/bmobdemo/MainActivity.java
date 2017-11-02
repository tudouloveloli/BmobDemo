package me.microcool.bmobdemo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * @author gaoshiwei
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    EditText mName;
    EditText mFeedback;
    Button mSubmit;
    Button mQueryAll;
    Button mQueryOne;
    EditText mQueryname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindView();
        initView();
    }

    /**
     * findViewById
     */
    private void bindView() {
        mName = (EditText) findViewById(R.id.name);
        mFeedback = (EditText) findViewById(R.id.feedback);
        mSubmit = (Button) findViewById(R.id.button);
        mQueryAll = (Button) findViewById(R.id.button2);
        mQueryOne = (Button) findViewById(R.id.button3);
        mQueryname = (EditText) findViewById(R.id.query_et);

    }

    /**
     * 监听事件
     */
    private void initView() {
        // 向服务器提交数据（保存）
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = mName.getText().toString();
                String feedbackMessage = mFeedback.getText().toString();
                if (userName.equals("") || feedbackMessage.equals("")) {
                    return;
                }
                // create Bmob Object
                Feedback feedbackObject = new Feedback();
                feedbackObject.setName(userName);
                feedbackObject.setFaceback(feedbackMessage);
                feedbackObject.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e == null) {
                            Toast.makeText(MainActivity.this, "添加数据成功，返回objectId为：" + s, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "添加数据失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        // find all data
        mQueryAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobQuery<Feedback> query = new BmobQuery<Feedback>();
                query.findObjects(new FindListener<Feedback>() {
                    @Override
                    public void done(List<Feedback> list, BmobException e) {
                        StringBuilder sb = new StringBuilder();
                        for (Feedback feed : list) {
                            sb.append(feed.getName()).append(":").append(feed.getFaceback()).append("\n");
                        }
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("获得到云端数据")
                                .setMessage(sb)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                })
                                .setCancelable(false)
                                .show();
                    }
                });
            }
        });

        //query someone data
        // 获取单条数据一般是通过id，但是id是后台生成的，用户怎么会记住？
        mQueryOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobQuery<Feedback> queryOne = new BmobQuery<Feedback>();
                final String str = mQueryname.getText().toString();
                // 添加筛选条件
                ////查询name叫str的数据
                queryOne.addWhereEqualTo("name", str);
                queryOne.findObjects(new FindListener<Feedback>() {
                    @Override
                    public void done(List<Feedback> list, BmobException e) {
                        StringBuilder sb = new StringBuilder();
                        for (Feedback feed : list) {
                            sb.append(feed.getName()).append(":").append(feed.getFaceback()).append("\n");
                        }
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle(str + "反馈的信息")
                                .setMessage(sb)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .setCancelable(false)
                                .show();
                    }
                });
            }
        });

    }


}
