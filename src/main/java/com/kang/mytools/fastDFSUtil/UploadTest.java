package com.kang.mytools.fastDFSUtil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UploadTest {

    @Value("${IMAGE_SERVER_URL}")
    private String imageServerUrl;

    @RequestMapping(value="/pic/upload",produces= MediaType.TEXT_PLAIN_VALUE + ";charset=utf-8")
    public String fileUpload(MultipartFile uploadFile){
        Map<String,Object> result_map = new HashMap<>();
        try {
            //1.获取文件名
            String fileName = uploadFile.getOriginalFilename();
            //2.获取文件后缀
            String extName = fileName.substring(fileName.lastIndexOf(".") + 1);
            //3.调用工具类 上传文件
            FastDFSUtil fastDFSClient = new FastDFSUtil("classpath:resource/client.conf");
            String path = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
            //4.拼接url http://192.168.25.133/ + path
            String url = imageServerUrl + path;
            //5.封装map
            result_map.put("error",0);
            result_map.put("url",url);

        } catch (Exception e) {
            e.printStackTrace();
            //5.封装map
            result_map.put("error",1);
            result_map.put("message","文件上传失败！");
        }
        String objectToJson = JsonUtils.objectToJson(result_map);
        return objectToJson;
    }
}
