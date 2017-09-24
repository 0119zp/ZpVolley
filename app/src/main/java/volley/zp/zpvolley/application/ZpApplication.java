package volley.zp.zpvolley.application;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Administrator on 2016/12/10 0010.
 *
 * 注册请求队列
 *
 */

public class ZpApplication extends Application {

    // 请求队列
    public static RequestQueue queues;

    @Override
    public void onCreate() {
        super.onCreate();
        // 实例化请求队列
        queues = Volley.newRequestQueue(getApplicationContext());
    }

    // 暴露请求队列
    public static RequestQueue getRequestQueues(){
        return queues;
    }

}
