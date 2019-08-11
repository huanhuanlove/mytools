package com.kang.mytools.imageNameFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * 处理大量的图片或视频上传时有同名的问题
 */
public class ImageName {
    static Logger log = LoggerFactory.getLogger(ImageName.class);

    public static String genImageName(String imgName) {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //long millis = System.nanoTime();
        //加上三位随机数
        Random random = new Random();
        int end3 = random.nextInt(999);
        //如果不足三位前面补0
        String str = millis + String.format("%03d", end3) + imgName;
log.debug("拼串前图片名为--{}", imgName);
log.debug("拼串后图片名为--{}", str);
    return str;
    }

    //测试
    public static void main(String[] args){
        ImageName.genImageName("aaa.png");
    }
}
