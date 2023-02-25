package com.hnust.wx_ticket.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import com.hnust.wx_ticket.service.UploadService;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.CannedAccessControlList;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class UploadServiceImpl implements UploadService {

    private String secretId = "AKIDOgpnW6VgGgroWzBC2f4FDwZ0DC0bzA9J";

    private String secretKey = "2qTxFJH4mLkxxkKgplW4RaaQDnCUcWCj";


    private String region = "ap-chongqing";

    private String bucketName = "wx-ticket-1316999154";

    private String path = "https://wx-ticket-1316999154.cos.ap-chongqing.myqcloud.com";

    private String key = "";

    public COSClient initCOSClient() {
        System.out.println("执行了该方法");
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        Region region = new Region(this.region);
        ClientConfig clientConfig = new ClientConfig(region);
        // 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);
        return cosClient;
    }

    public String upload(MultipartFile picture) {

        try {
            String filePath = "D:\\img\\a.jpg";
            // 指定要上传的文件
            //File localFile = new File("D:\\img\\a.jpg");
            File localFile = transferToFile(picture);
            // File localFile =(File) picture;
            // 指定要上传到 COS 上对象键
            key = getFileKey(filePath);
            System.out.println(key + "key----------------------");
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
            COSClient cosClient1 = initCOSClient();
            PutObjectResult putObjectResult = cosClient1.putObject(putObjectRequest);
            // 设置权限(公开读)
            cosClient1.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            System.out.println("url------------" + path + "/" + key);
        } catch (CosServiceException serverException) {
            serverException.printStackTrace();
        } catch (CosClientException clientException) {
            clientException.printStackTrace();
        }
        return path + "/" + key;

    }


    private String getFileKey(String originalfileName) {
        String filePath = "picture/";
        //1.获取后缀名 2.去除文件后缀 替换所有特殊字符
        String fileType = originalfileName.substring(originalfileName.lastIndexOf("."));
        String fileStr = StrUtil.removeSuffix(originalfileName, fileType).replaceAll("[^0-9a-zA-Z\\u4e00-\\u9fa5]", "_");
        filePath += new DateTime().toString("yyyyMMddHHmmss") + "_" + fileStr + fileType;
        return filePath;
    }

    private File transferToFile(MultipartFile multipartFile) {
//        选择用缓冲区来实现这个转换即使用java 创建的临时文件 使用 MultipartFile.transferto()方法 。
        File file = null;
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            String[] filename = originalFilename.split("\\.");
            file = File.createTempFile(filename[0] + "aaa", filename[1]);
            multipartFile.transferTo(file);
            file.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
