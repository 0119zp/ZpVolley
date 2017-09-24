package volley.zp.zpvolley.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;

import volley.zp.zpvolley.R;
import volley.zp.zpvolley.application.ZpApplication;
import volley.zp.zpvolley.cache.ZpImageCache;

/**
 * Created by Administrator on 2016/12/10 0010.
 */

public class ZpVolleyLoadImageActivity extends Activity{

    private ImageView imageView1,imageView2;
    private NetworkImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_load_image);
        // 初始化控件
        initView();
        // 加载图片 1
        initLoadImage();
        // 加载图片 2
        initImageCache();
        // 加载图片 3
        initNetImageView();
    }

    private void initView() {
        imageView1 = (ImageView) findViewById(R.id.zp_iv_image1);
        imageView2 = (ImageView) findViewById(R.id.zp_iv_image2);
        image = (NetworkImageView) findViewById(R.id.iv_image);
    }

    /**
     * ImageRequest 加载图片
     */
    private void initLoadImage() {
        String url = "https://www.baidu.com/img/bd_logo1.png";
        /**
         * 01 - 图片的URL地址
         * 02 - 图片请求成功的回调
         * 03 - 图片最大的宽度
         * 04 - 图片最大的高度
         * 05 - 图片的颜色属性，Bitmap.Config下的几个常量都可以在这里使用，其中ARGB_8888可以展示最好的颜色属性，
         *      每个图片像素占据4个字节的大小，而RGB_565则表示每个图片像素占据2个字节大小
         * 06 - 图片请求失败的回调
         */
        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                imageView1.setImageBitmap(response);
            }
        }, 300, 300, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                imageView1.setBackgroundResource(R.mipmap.ic_launcher);
            }
        });
        imageRequest.setTag("image");
        ZpApplication.getRequestQueues().add(imageRequest);
    }

    /**
     * ImageLoader
     * 1. 创建一个RequestQueue对象。
     * 2. 创建一个ImageLoader对象。
     * 3. 获取一个ImageListener对象。
     * 4. 调用ImageLoader的get()方法加载网络上的图片。
     * 缓存图片
     */
    private void initImageCache(){
        String url = "https://www.baidu.com/img/bd_logo1.png";
        ImageLoader imageLoader = new ImageLoader(ZpApplication.getRequestQueues(),new ZpImageCache());
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView2,R.mipmap.ic_launcher,R.mipmap.ic_launcher);
//        imageLoader.get(url, listener);
        imageLoader.get(url, listener, 400, 400);
//        imageLoader.get(url, listener, 200, 200, ImageView.ScaleType.FIT_XY);
//        imageLoader.get(url, listener, 200, 200, ImageView.ScaleType.FIT_XY, new ImageRequest.Transformation(){
//            @Override
//            public Bitmap transform(Bitmap source, int maxWidth, int maxHeight) {
//                return null;
//            }
//
//            @Override
//            public String key() {
//                return null;
//            }
//        });
    }

    /**
     * NetworkImageView
     * 1. 创建一个RequestQueue对象。
     * 2. 创建一个ImageLoader对象。
     * 3. 在布局文件中添加一个NetworkImageView控件。
     * 4. 在代码中获取该控件的实例。
     * 5. 设置要加载的图片地址。
     */
    private void initNetImageView(){
        String url = "https://www.baidu.com/img/bd_logo1.png";
        ImageLoader imageLoader = new ImageLoader(ZpApplication.getRequestQueues(),new ZpImageCache());
        image.setDefaultImageResId(R.mipmap.ic_launcher);   // 加载中占位图
        image.setErrorImageResId(R.mipmap.ic_launcher);     // 加载失败占位图
        image.setImageUrl(url, imageLoader);
    }

}
