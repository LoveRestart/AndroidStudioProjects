package com.example.administrator.myapplication.Activities.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Poi;
import com.amap.api.navi.AmapNaviPage;
import com.amap.api.navi.AmapNaviParams;
import com.amap.api.navi.AmapNaviType;
import com.amap.api.navi.INaviInfoCallback;
import com.amap.api.navi.model.AMapNaviLocation;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.utils.AmapTTSController;
import com.example.administrator.myapplication.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InputtipsActivity extends Activity implements TextWatcher, Inputtips.InputtipsListener,AdapterView.OnItemClickListener,INaviInfoCallback {

    //查找省级范围
    private String city = "浙江";
    private AutoCompleteTextView mKeywordText;
    //显示列表
    private ListView minputlist;
    private List<Tip> myTipList;

    //语音播报
    private AmapTTSController amapTTSController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputtips);
        init();
    }

    void init(){
        minputlist = findViewById(R.id.inputlist);
        mKeywordText = findViewById(R.id.input_edittext);
        mKeywordText.addTextChangedListener(this);
        // SpeechUtils.getInstance(this).speakText();系统自带的语音播报
        amapTTSController = AmapTTSController.getInstance(getApplicationContext());
        amapTTSController.init();

     }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String newText = s.toString().trim();
        InputtipsQuery inputquery = new InputtipsQuery(newText, city);
        //是否开启区域（省级）查找
        inputquery.setCityLimit(false);
        Inputtips inputTips = new Inputtips(InputtipsActivity.this, inputquery);
        inputTips.setInputtipsListener(this);
        inputTips.requestInputtipsAsyn();
    }

    @Override
    public void afterTextChanged(Editable s) {
        // TODO Auto-generated method stub
    }

    /**
     * 输入提示结果的回调
     * @param tipList
     * @param rCode
     */
    @Override
    public void onGetInputtips(final List<Tip> tipList, int rCode) {
        myTipList = tipList;
        if (rCode == AMapException.CODE_AMAP_SUCCESS) {
            List<HashMap<String, String>> listString = new ArrayList<HashMap<String, String>>();
            for (int i = 0; i < tipList.size(); i++) {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("name", tipList.get(i).getName());
                map.put("address", tipList.get(i).getDistrict());
                listString.add(map);
            }
            SimpleAdapter aAdapter = new SimpleAdapter(getApplicationContext(), listString, R.layout.item_layout,
                    new String[] {"name","address"}, new int[] {R.id.poi_field_id, R.id.poi_value_id});
            minputlist.setAdapter(aAdapter);
            minputlist.setOnItemClickListener(this);
            aAdapter.notifyDataSetChanged();
        } else {
            ToastUtil.showerror(this.getApplicationContext(), rCode);
        }

    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // TODO Auto-generated method stub
        int myid = (int) id;
        final String zz =  myTipList.get(myid).getPoint().toString();
        final String name = myTipList.get(myid).getName();
        String[] points = zz.split(",");
        //单点导航--起点
        Intent intent = getIntent();
        LatLng p0 = intent.getParcelableExtra("latLng");
        //单点导航--终点
        LatLng p1 = new LatLng(Double.valueOf(points[0]), Double.valueOf(points[1]));
        //drive--------ride---------walk三种方式
        AmapNaviPage.getInstance().showRouteActivity(getApplicationContext(), new AmapNaviParams(new Poi("我的位置",p0,""), null, new Poi(name, p1, ""), AmapNaviType.WALK), InputtipsActivity.this);
    }
    /**
     * 到达目的地后回调函数。
     **/
    @Override
    public void onArriveDestination(boolean b) {

    }
    /**
     * 启动导航后的回调函数
     **/
    @Override
    public void onStartNavi(int i) {

    }
    @Override
    public void onStopSpeaking() {
        amapTTSController.stopSpeaking();
    }
    /**
     * 导航初始化失败时的回调函数
     **/
    @Override
    public void onInitNaviFailure() {
        Log.d("failure","aa");
    }
    /**
     * 算路成功回调
     * @param ints 路线id数组
     */
    @Override
    public void onCalculateRouteSuccess(int[] ints) {

    }
    /**
     * 步行或者驾车路径规划失败后的回调函数
     **/
    @Override
    public void onCalculateRouteFailure(int i) {

    }
    @Override
    public void onGetNavigationText(String s) {
        amapTTSController.onGetNavigationText(s);
    }
    /**
     * 当GPS位置有更新时的回调函数。
     *@param aMapNaviLocation 当前自车坐标位置
     **/
    @Override
    public void onLocationChange(AMapNaviLocation aMapNaviLocation) {

    }
}
