package volley.zp.zpvolley.base;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

import volley.zp.zpvolley.application.ZpApplication;

/**
 * Created by Administrator on 2016/12/10 0010.
 */

public class ZpVolleyRequest {

    private static StringRequest stringRequest;
    private Context mContext;

    /**
     * get请求
     * @param context
     * @param url
     * @param tag
     * @param zpVolley
     */
    public static void getVolley(Context context, String url, String tag, ZpVolley zpVolley){
        ZpApplication.getRequestQueues().cancelAll(tag);
        stringRequest = new StringRequest(Request.Method.GET,url,zpVolley.getListener(),zpVolley.getErrorListener());
        stringRequest.setTag(tag);
        ZpApplication.getRequestQueues().add(stringRequest);
        ZpApplication.getRequestQueues().start();
    }

    /**
     * post请求
     * @param context
     * @param url
     * @param tag
     * @param params
     * @param zpVolley
     */
    public static void postVolley(Context context, String url, String tag, final Map<String, String> params, ZpVolley zpVolley){
        ZpApplication.getRequestQueues().cancelAll(tag);
        stringRequest = new StringRequest(Request.Method.POST, url, zpVolley.getListener(), zpVolley.getErrorListener()){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        stringRequest.setTag(tag);
        ZpApplication.getRequestQueues().add(stringRequest);
        ZpApplication.getRequestQueues().start();
    }

}
