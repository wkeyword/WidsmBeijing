package com.changzheng.widsmbeijing.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**字符流的读写
 * 作者: changzheng on 16/4/20 15 33.
 * 邮箱地址: 909459046@qq.com
 * 联系电话: 18098379524
 */
public class StreamUtils {
    public static String readStream(InputStream is){
        try {
            byte[] bytes=readInputStream(is);
            return new String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static byte[] readInputStream(InputStream is) throws IOException{
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        byte[] buffer=new byte[1024];
        int len=-1;
        while ((len=is.read(buffer))!=-1){
            baos.write(buffer,0,len);
        }
        is.close();
        return baos.toByteArray();

    }
}
