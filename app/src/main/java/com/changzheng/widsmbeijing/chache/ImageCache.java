package com.changzheng.widsmbeijing.chache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v4.media.session.IMediaControllerCallback;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.ImageView;

import com.changzheng.widsmbeijing.R;
import com.changzheng.widsmbeijing.utils.StreamUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 作者: changzheng on 16/4/19 23 34.
 * 邮箱地址: 909459046@qq.com
 * 联系电话: 18098379524
 */
public class ImageCache {
    private LruCache<String, Bitmap> cache = null;
    private ExecutorService threadPool = null;
    private File localDir = null;

    public ImageCache(Context context) {
        threadPool = Executors.newFixedThreadPool(5);
        localDir = context.getCacheDir();
        long maxSize = Runtime.getRuntime().maxMemory() / 8;
        cache = new LruCache<String, Bitmap>((int) maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                int byteRows = value.getRowBytes();
                int rowCount = value.getHeight();
                return byteRows * rowCount;
            }
        };

    }

    public void displayImage(ImageView imageView, String url) {
        Bitmap bitmap = getFromCache(url);
        if (bitmap != null) {
            Log.i("wcz", "从内存获取图片");
            imageView.setImageBitmap(bitmap);
            return;
        }
        bitmap = getFromLocal(url);
        if (bitmap != null) {
            Log.i("wcz", "从本地文件中获取的");
            imageView.setImageBitmap(bitmap);
            return;
        }

        getFromNet(imageView, url);

    }

    //从网络上拉取图片
    private void getFromNet(ImageView imageView, String url) {
//        ImageRunable r = new ImageRunable(imageView, url);
//        threadPool.execute(r);
    }

    /**
     * 从集合中取
     *
     * @author changzheng
     * @time 16/4/19 下午11:46
     * email: 909459046@qq.com
     */
    private Bitmap getFromCache(String url) {
        return cache.get(url);
    }

    /**
     * 从本地文件中获取图片
     *
     * @author changzheng
     * @time 16/4/19 下午11:45
     * email: 909459046@qq.com
     */
    private Bitmap getFromLocal(String url) {
        try {
            String rightFileName = URLEncoder.encode(url, "utf-8");
            File imageFile = new File(localDir.getAbsolutePath() + "/" + rightFileName);
            Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
            cache.put(url, bitmap);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private class ImageRunable implements Runnable {
        private String url;
        private ImageView imageView;

        public ImageRunable(String url, ImageView imageView) {
            super();
            this.url = url;
            this.imageView = imageView;
        }

        @Override
        public void run() {
            try {
                URL urlObj = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(5000);
                if (conn.getResponseCode() == 200) {
                    InputStream input = conn.getInputStream();

                    byte[] bytes = StreamUtils.readInputStream(input);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

                    if (bitmap != null) {
                        Message msg = new Message();
                        msg.what = 200;
                        HashMap<String, Object> data = new HashMap<String, Object>();
                        data.put("imageview", imageView);
                        data.put("bitmap", bitmap);
                        msg.obj = data;
                        handle.sendMessage(msg);
                        cache.put(url, bitmap);
                        writeToLocal(url,bitmap);


                    }else {
                        Message msg=new Message();
                        HashMap<String,Object> data=new HashMap<String, Object>();
                        data.put("imageview", imageView);
                        data.put("bitmap", null);
                        msg.what=404;
                        msg.obj=data;
                        handle.sendMessage(msg);
                    }
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private Handler handle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 200) {
                HashMap<String, Object> data = new HashMap<String, Object>();
                ImageView imageView = (ImageView) data.get("imageview");
                Bitmap bitmap = (Bitmap) data.get("bitmap");
                imageView.setImageBitmap(bitmap);
            } else if (msg.what == 400) {
                HashMap<String, Object> data = (HashMap<String, Object>) msg.obj;
                ImageView imageView = (ImageView) data.get("imageview");
                imageView.setImageResource(R.mipmap.news_pic_default);
            }
        }

        ;
    };
/**
 * 把图片保存成文件
 * @author changzheng
 * @time 16/4/20 下午4:09
 * email: 909459046@qq.com
 */
    private void writeToLocal(String url, Bitmap bitmap) {
        try {
            String rightFileName=URLEncoder.encode(url,"utf-8");
            File imageFile=new File(localDir.getAbsolutePath()+"/"+rightFileName);
            if (!imageFile.exists()){
                imageFile.createNewFile();
            }

            FileOutputStream fos=new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG,80,fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
