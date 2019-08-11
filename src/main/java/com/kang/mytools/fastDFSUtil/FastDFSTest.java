package com.kang.mytools.fastDFSUtil;

import org.junit.Test;

public class FastDFSTest {

    //测试上传
    @Test
    public void test2() throws Exception{
        FastDFSUtil dfsClient = new FastDFSUtil("E:\\software\\idea\\mytools\\src\\main\\resources\\client.conf");
        String path = dfsClient.uploadFile("C:\\Users\\Dell\\Desktop\\小图标\\a1.png", "png", null);
        System.out.println(path);
    }
}
