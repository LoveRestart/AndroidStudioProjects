package com.example.administrator.myapplication.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Poi;
import com.amap.api.navi.AmapNaviPage;
import com.amap.api.navi.AmapNaviParams;
import com.amap.api.navi.AmapNaviType;
import com.amap.api.navi.INaviInfoCallback;
import com.amap.api.navi.model.AMapNaviLocation;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.utils.AmapTTSController;

public class NaviActivity extends Activity implements INaviInfoCallback {
    //语音播报
    private AmapTTSController amapTTSController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navi);
        init();
        // SpeechUtils.getInstance(this).speakText();系统自带的语音播报
        amapTTSController = AmapTTSController.getInstance(getApplicationContext());
        amapTTSController.init();
    }
    void init(){
        Intent intent = getIntent();
        String point = intent.getStringExtra("point");
        String name = intent.getStringExtra("name");
        String[] points = point.split(",");
        //单点导航--起点
        LatLng p0 = new LatLng(30.215,120.022);//浙江科技学院
        //单点导航--终点
        LatLng p1 = new LatLng(Double.valueOf(points[0]), Double.valueOf(points[1]));
        //drive--------ride---------walk三种方式
        AmapNaviPage.getInstance().showRouteActivity(getApplicationContext(), new AmapNaviParams(new Poi("我的位置",p0,""), null, new Poi(name, p1, ""), AmapNaviType.WALK), NaviActivity.this);
    }
    /**
     * 导航初始化失败时的回调函数
     **/
    @Override
    public void onInitNaviFailure() {
        Log.d("failure","aa");
    }
    /**
     * 当GPS位置有更新时的回调函数。
     *@param location 当前自车坐标位置
     **/
    @Override
    public void onLocationChange(AMapNaviLocation aMapNaviLocation) {

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
    /**
     * 算路成功回调
     * @param routeIds 路线id数组
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

    @Override
    public void onStopSpeaking() {
        amapTTSController.stopSpeaking();
    }
}
