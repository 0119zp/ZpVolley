package volley.zp.zpvolley.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import volley.zp.zpvolley.R;
import volley.zp.zpvolley.application.ZpApplication;

/**
 * Created by Administrator on 2016/12/10 0010.
 */

public class ZpVolleyPostActivity extends Activity {

    private static final String POST_TEG = "zpPost";

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);

        initView();
//        initVolleyPost1();
        initVolleyPost2();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        textView = (TextView) findViewById(R.id.zp_tv_text);
    }

    /**
     * 初始化Post请求
     */
    private void initVolleyPost1() {
        String url = "http://apis.juhe.cn/mobile/get?";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                textView.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText(error.toString());
            }
        }){
            // post传的参数
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> hashMap = new HashMap<>();
                hashMap.put("phone","13429667914");
                hashMap.put("key","335abcc4e891ba4e4be6d7534fd54c5d");
                return hashMap;
            }
        };
        // 设置标记，在请求队列中方便寻找
        request.setTag(POST_TEG);
        // 加入队列
        ZpApplication.getRequestQueues().add(request);
    }

    // 利用JsonobjectRequest
    private void initVolleyPost2() {

        String url = "http://apis.juhe.cn/mobile/get?";
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("phone","13429667914");
        hashMap.put("key","335abcc4e891ba4e4be6d7534fd54c5d");
        JSONObject jsonObject = new JSONObject(hashMap);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                textView.setText(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText(error.toString());
            }
        });
        // 设置标记，在请求队列中方便寻找
        request.setTag(POST_TEG);
        // 加入队列
        ZpApplication.getRequestQueues().add(request);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // volley 与 activity生命周期联动
        ZpApplication.getRequestQueues().cancelAll(POST_TEG);
    }
}
