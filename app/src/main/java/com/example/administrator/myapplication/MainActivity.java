package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.myapplication.Activities.AndroidClientActivity;
import com.example.administrator.myapplication.Activities.LocationTest3Activity;
import com.example.administrator.myapplication.Activities.LocationTestActivity;
import com.example.administrator.myapplication.Activities.LoginRegister.LoginActivity;
import com.example.administrator.myapplication.Activities.LoginRegister.LoginByEmailActivity;
import com.example.administrator.myapplication.Activities.Map.FenceActivity;
import com.example.administrator.myapplication.Activities.Map2DActivity;
import com.example.administrator.myapplication.Activities.NaviActivity;
import com.example.administrator.myapplication.Activities.Map.InputtipsActivity;
import com.example.administrator.myapplication.Activities.WeatherActivity;


public class MainActivity extends Activity {

    private Button Test;
    private TextView Location,Location2,Location3,Map2D,Navi,Weather,login,registerByemail,fence,inputtips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //标题栏居中
        TextView view = findViewById(android.R.id.title);
        view.setGravity(Gravity.CENTER);
        setContentView(R.layout.activity_main);
        Test = findViewById(R.id.Test);
        Location = findViewById(R.id.Location);
        Location3 = findViewById(R.id.Location3);
        Map2D = findViewById(R.id.Map2D);
        Navi = findViewById(R.id.Navi);
        Weather = findViewById(R.id.weather);
        login = findViewById(R.id.login);
        registerByemail = findViewById(R.id.toEmailRegister);
        fence = findViewById(R.id.fence);
        inputtips = findViewById(R.id.inputtips);
        Test.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        AndroidClientActivity.class);
                startActivity(intent);
            }
        });

        Location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LocationTestActivity.class);
                startActivity(intent);
            }
        });

        Location3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LocationTest3Activity.class);
                startActivity(intent);
            }
        });
        Map2D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Map2DActivity.class);
                startActivity(intent);
            }
        });
        Navi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NaviActivity.class));
            }
        });
        Weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, WeatherActivity.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
        registerByemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginByEmailActivity.class));
            }
        });
        fence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FenceActivity.class));
            }
        });
        inputtips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, InputtipsActivity.class));
            }
        });

    }
}

