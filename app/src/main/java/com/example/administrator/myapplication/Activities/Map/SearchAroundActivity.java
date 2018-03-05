package com.example.administrator.myapplication.Activities.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.services.poisearch.PoiSearch;
import com.example.administrator.myapplication.Activities.Map2DActivity;
import com.example.administrator.myapplication.R;

public class SearchAroundActivity extends Activity implements View.OnClickListener{

    private LinearLayout SA_search_text;
    private ImageView SA_search_back;
    private ImageView foodPic,hotelPic,spotPic,busPic,parkPic;

    private TextView poiName;
    private String poiNameGet;
    private PoiSearch.Query query;// Poi查询条件类
    private int currentPage = 0;// 当前页面，从0开始计数


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_around2);
        init();
    }

    private void init(){
        SA_search_text = findViewById(R.id.SA_search_text);
        SA_search_text.setOnClickListener(this);
        SA_search_back = findViewById(R.id.SA_search_back);
        SA_search_back.setOnClickListener(this);
        foodPic = findViewById(R.id.foodPic);
        foodPic.setOnClickListener(this);
        poiName = findViewById(R.id.poiName);

        Intent intent = getIntent();
        poiNameGet = intent.getStringExtra("poiName");
        poiName.setText(poiNameGet);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.SA_search_text :
                startActivity(new Intent(SearchAroundActivity.this, InputtipsActivity.class));
                break;
            case R.id.SA_search_back :
                finish();
                break;
            case R.id.foodPic :
                Intent intent = new Intent(SearchAroundActivity.this, Map2DActivity.class);
                intent.putExtra("food","美食");
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.hasExtra("poiName")){
            poiNameGet = intent.getStringExtra("poiName");
            Log.d("name",poiNameGet);
        }
    }

}
