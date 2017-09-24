package volley.zp.zpvolley.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

import volley.zp.zpvolley.R;
import volley.zp.zpvolley.application.ZpApplication;
import volley.zp.zpvolley.base.ZpVolley;
import volley.zp.zpvolley.base.ZpVolleyRequest;
import volley.zp.zpvolley.bean.ZpBean;

/**
 * Created by Administrator on 2016/12/10 0010.
 */

public class ZpVolleyGetActivity extends Activity {

    private static final String GET_TEG = "zpGet";

    // 展示
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);

        // 初始化控件
        initView();
        // 初始化get请求
//        initVolleyGet();
        // 初始化get请求
        initVolleyGet2();
        //
//        initVolleyGet3();
    }

    private void initView() {
        textView = (TextView) findViewById(R.id.zp_tv_text);
    }

    private void initVolleyGet() {
        String url = "http://www.baidu.com";

        StringRequest request = new StringRequest(url, new Listener<String>() {
            // 请求成功回调
            @Override
            public void onResponse(String response) {
//                textView.setText(response);
                Log.e("zpan","=StringRequest==onResponse=" + response);
            }
        }, new Response.ErrorListener() {
            // 请求失败的回调
            @Override
            public void onErrorResponse(VolleyError error) {
//                textView.setText(error.toString());
                Log.e("zpan","=StringRequest=onErrorResponse=" + error.getMessage());
            }
        });
        // 设置标记，在请求队列中方便寻找
        request.setTag(GET_TEG);
        // 加入队列
        ZpApplication.getRequestQueues().add(request);
    }

    private void initVolleyGet2(){
        String url = "http://m.weather.com.cn/data/101010100.html";
        JsonObjectRequest request = new JsonObjectRequest(url,null,new Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
//                textView.setText(response.toString());
                Log.e("zpan", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                textView.setText("error:"+error.toString());
                Log.e("zpan", error.getMessage(), error);
            }
        });
        // 设置标记，在请求队列中方便寻找
        request.setTag(GET_TEG);
        // 加入队列
        ZpApplication.getRequestQueues().add(request);
    }

    /**
     * 简单的二次封装
     */
    private void initVolleyGet3() {
        String url = "http://apis.juhe.cn/mobile/get?phone=13429667914&key=335abcc4e891ba4e4be6d7534fd54c5d";

        ZpVolleyRequest.getVolley(ZpVolleyGetActivity.this, url, GET_TEG, new ZpVolley(this,ZpVolley.mListener,ZpVolley.mErrorListener) {
            @Override
            public void onZpSueccess(String sueccess) {
                textView.setText(sueccess);
                Log.e("zhang",sueccess);
                // Gson解析数据
                Gson gson = new Gson();
                ZpBean zpBean = gson.fromJson(sueccess, ZpBean.class);
                Log.e("zhang", "==========" + zpBean.getError_code());
            }

            @Override
            public void onZpError(VolleyError error) {
                textView.setText(error.toString());
                Log.e("zhang",error.toString());
            }
        });
    }

}
