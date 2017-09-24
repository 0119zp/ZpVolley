package volley.zp.zpvolley;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import volley.zp.zpvolley.activity.ZpVolleyGetActivity;
import volley.zp.zpvolley.activity.ZpVolleyLoadImageActivity;
import volley.zp.zpvolley.activity.ZpVolleyPostActivity;

/**
 * Volley提供了非常强的扩展机制
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void volleyget(View view){
        Intent intent = new Intent(MainActivity.this, ZpVolleyGetActivity.class);
        startActivity(intent);
    }

    public void volleyPost(View view){
        Intent intent = new Intent(MainActivity.this, ZpVolleyPostActivity.class);
        startActivity(intent);
    }

    public void volleyImage(View view){
        Intent intent = new Intent(MainActivity.this, ZpVolleyLoadImageActivity.class);
        startActivity(intent);
    }

    /**
     * 01、RequestQueue 请求队列对象 -- 适合高并发请求
     * 02、设置请求对象
     * 03、将请求对象add到请求队列中
     */

    /**
     * 01、StringRequest
     *      -- 用于请求普通文本信息
     * 02、JsonRequest -- 抽象类
     *      子类 a、JsonObjectRequest
     *           b、JsonArrayRequest
     *      -- 用于请求json数据
     */

    /**
     * 加载图片
     * 01、ImageRequest
     * 02、ImageLoader
     * 03、NetworkImageView
     */

}
