package com.kang.mytools.fastDFSUtil;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

/**
 * 图片视频上传下载服务
 * 1、确保服务器中fastdfs服务开启 ，
 *      启动tracker服务-- /usr/bin/fdfs_trackerd /etc/fdfs/tracker.conf restart
 *      启动storage服务-- /usr/bin/fdfs_storaged /etc/fdfs/storage.conf restart
 * 2、创建client.conf、server.properties文件在classpath下
 *      client.conf配置：tracker_server为--虚拟机IP：端口（默认22122），如：tracker_server=192.168.84.129:22122
 *      server.properties配置：上传方法中用到的常用变量统一配置在此文件中，方便管理
 * 3、Controller层的编写（整合到Spring中）
 */
public class FastDFSUtil {

    private TrackerClient trackerClient = null;
    private TrackerServer trackerServer = null;
    private StorageServer storageServer = null;
    private StorageClient1 storageClient = null;

    public FastDFSUtil(String conf) throws Exception {
        if (conf.contains("classpath:")) {
            conf = conf.replace("classpath:", this.getClass().getResource("/").getPath());
        }
        ClientGlobal.init(conf);
        trackerClient = new TrackerClient();
        trackerServer = trackerClient.getConnection();
        storageServer = null;
        storageClient = new StorageClient1(trackerServer, storageServer);
    }

    /**
     * 上传文件方法
     * <p>Title: uploadFile</p>
     * <p>Description: </p>
     * @param fileName 文件全路径
     * @param extName 文件扩展名，不包含（.）
     * @param metas 文件扩展信息
     * @return
     * @throws Exception
     */
    public String uploadFile(String fileName, String extName, NameValuePair[] metas) throws Exception {
        String result = storageClient.upload_file1(fileName, extName, metas);
        return result;
    }

    public String uploadFile(String fileName) throws Exception {
        return uploadFile(fileName, null, null);
    }

    public String uploadFile(String fileName, String extName) throws Exception {
        return uploadFile(fileName, extName, null);
    }

    /**
     * 上传文件方法
     * <p>Title: uploadFile</p>
     * <p>Description: </p>
     * @param fileContent 文件的内容，字节数组
     * @param extName 文件扩展名
     * @param metas 文件扩展信息
     * @return
     * @throws Exception
     */
    public String uploadFile(byte[] fileContent, String extName, NameValuePair[] metas) throws Exception {

        String result = storageClient.upload_file1(fileContent, extName, metas);
        return result;
    }

    public String uploadFile(byte[] fileContent) throws Exception {
        return uploadFile(fileContent, null, null);
    }

    public String uploadFile(byte[] fileContent, String extName) throws Exception {
        return uploadFile(fileContent, extName, null);
    }

}
