package com.example.administrator.myapplication.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class AndroidClientActivity extends Activity {

    private TextView Username,Email,Phone,Password;
    private RadioGroup RadioGroupID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_client);
        Username = (TextView) findViewById(R.id.username);
        Email = (TextView)findViewById(R.id.email);
        Phone = (TextView)findViewById(R.id.phone);
        Password = (TextView)findViewById(R.id.password);
        RadioGroupID = (RadioGroup)findViewById(R.id.radioGroupID);

         final Handler handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        break;
                    default:
                        JSONObject json = (JSONObject) msg.obj;
                try{
                        Username.setText(json.getString("username"));
                        Email.setText(json.getString("email"));
                        Phone.setText(json.getString("username"));
                        Password.setText(json.getString("username"));
                }
                    catch (JSONException e) {
                }
                        break;
                }
            }
        };

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                URL url;
                String temp;
                int iduser;
                String username,password,email,phone,sex,headpic,groups;
                StringBuffer sb = new StringBuffer();
                try {
                    url = new URL("http://141.76.121.206:8000/SpotChatBS/Android/Test1");// 根据自己的服务器地址填写
                    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));// 获取输入流
                    while ((temp = in.readLine()) != null) {
                        sb.append(temp);
                    }
                    try
                    {
                        JSONObject jsonObject=new   JSONObject(String.valueOf(sb));
                        iduser = jsonObject.getInt("iduser");
                        username = jsonObject.getString("username");
                        password = jsonObject.getString("password");
                        email = jsonObject.getString("email");
                        phone = jsonObject.getString("phone");
                        sex = jsonObject.getString("sex");
                        headpic = jsonObject.getString("headpic");
                        groups = jsonObject.getString("groups");
/*                        Message msg =new Message();
                        msg.obj = jsonObject;//可以是基本类型，可以是对象，可以是List、map等；
                        handler.sendMessage(msg);*/
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                    System.out.println(sb.toString());
                    in.close();
                } catch (MalformedURLException me) {
                    me.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();


    }
}

