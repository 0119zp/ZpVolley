package volley.zp.zpvolley.base;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by Administrator on 2016/12/10 0010.
 */

public abstract class ZpVolley {

    private Context mContext;
    public static Response.Listener<String> mListener;
    public static Response.ErrorListener mErrorListener;

    public ZpVolley(Context context, Response.Listener<String> listener, Response.ErrorListener errorListener){
        this.mContext = context;
        this.mListener = listener;
        this.mErrorListener = errorListener;
    }

    /**
     * 请求成功的回调
     * @param sueccess
     */
    public abstract void onZpSueccess(String sueccess);

    /**
     * 请求失败的回调
     * @param error
     */
    public abstract void onZpError(VolleyError error);

    public Response.Listener<String> getListener(){
        mListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onZpSueccess(response);
            }
        };
        return mListener;
    }

    public Response.ErrorListener getErrorListener(){
        mErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onZpError(error);
            }
        };
        return mErrorListener;
    }

}
