package com.shijiu.utils;


import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.model.UploadResult;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.transfer.TransferManager;
import com.qcloud.cos.transfer.Upload;
import org.apache.logging.log4j.core.util.FileUtils;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class CosUploadUtils {

    public static final String url = "http://food-1309527051.cos.ap-guangzhou.myqcloud.com/";

    //在配置文件中读取值
    @Value("${cos_SecretId}")
    private String cosSecretId;

    @Value("${cos_SecretKey}")
    private String cosSecretKey;

    @Value("${cos_APPID}")
    private String cosAPPID;

    @Value("${cos_Bucket}")
    private String cosBucket;

    @Value("${cos_Region}")
    private String cosRegion;

    public boolean upload(MultipartFile multipartFile) {
        // 使用高级接口必须先保证本进程存在一个 TransferManager 实例，如果没有则创建
// 详细代码参见本页：高级接口 -> 创建 TransferManager
        TransferManager transferManager = createTransferManager();

        ObjectMetadata objectMetadata = new ObjectMetadata();
// 上传的流如果能够获取准确的流长度，则推荐一定填写 content-length
// 如果确实没办法获取到，则下面这行可以省略，但同时高级接口也没办法使用分块上传了
//        objectMetadata.setContentLength(inputStreamLength);
// 对象键(Key)是对象在存储桶中的唯一标识。
        String key = multipartFile.getOriginalFilename();

        try {
            // 高级接口会返回一个异步结果Upload
            // 可同步地调用 waitForUploadResult 方法等待上传完成，成功返回UploadResult, 失败抛出异常
            PutObjectRequest putObjectRequest = new PutObjectRequest(cosBucket, key, multipartFile.getInputStream(), objectMetadata);
            Upload upload = transferManager.upload(putObjectRequest);
            UploadResult uploadResult = upload.waitForUploadResult();
            shutdownTransferManager(transferManager);
            return true;
        } catch (CosServiceException e) {
            e.printStackTrace();
        } catch (CosClientException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

// 确定本进程不再使用 transferManager 实例之后，关闭之
// 详细代码参见本页：高级接口 -> 关闭 TransferManager

        return false;
    }

    TransferManager createTransferManager() {
        // 创建一个 COSClient 实例，这是访问 COS 服务的基础实例。
        // 详细代码参见本页: 简单操作 -> 创建 COSClient
        COSClient cosClient = createCOSClient();

        // 自定义线程池大小，建议在客户端与 COS 网络充足（例如使用腾讯云的 CVM，同地域上传 COS）的情况下，设置成16或32即可，可较充分的利用网络资源
        // 对于使用公网传输且网络带宽质量不高的情况，建议减小该值，避免因网速过慢，造成请求超时。
        ExecutorService threadPool = Executors.newFixedThreadPool(32);

        // 传入一个 threadpool, 若不传入线程池，默认 TransferManager 中会生成一个单线程的线程池。
        TransferManager transferManager = new TransferManager(cosClient, threadPool);

//        // 设置高级接口的配置项
//        // 分块上传阈值和分块大小分别为 5MB 和 1MB
//        TransferManagerConfiguration transferManagerConfiguration = new TransferManagerConfiguration();
//        transferManagerConfiguration.setMultipartUploadThreshold(5*1024*1024);
//        transferManagerConfiguration.setMinimumUploadPartSize(1*1024*1024);
//        transferManager.setConfiguration(transferManagerConfiguration);

        return transferManager;
    }

    // 创建 COSClient 实例，这个实例用来后续调用请求
    COSClient createCOSClient() {
        // 设置用户身份信息。
        // SECRETID 和 SECRETKEY 请登录访问管理控制台 https://console.cloud.tencent.com/cam/capi 进行查看和管理
        String secretId = cosSecretId;
        String secretKey = cosSecretKey;
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);

        // ClientConfig 中包含了后续请求 COS 的客户端设置：
        ClientConfig clientConfig = new ClientConfig();

        // 设置 bucket 的地域
        // COS_REGION 请参照 https://cloud.tencent.com/document/product/436/6224
        clientConfig.setRegion(new Region(cosRegion));

        // 设置请求协议, http 或者 https
        // 5.6.53 及更低的版本，建议设置使用 https 协议
        // 5.6.54 及更高版本，默认使用了 https
        clientConfig.setHttpProtocol(HttpProtocol.http);

        // 以下的设置，是可选的：

        // 设置 socket 读取超时，默认 30s
        clientConfig.setSocketTimeout(30 * 1000);
        // 设置建立连接超时，默认 30s
        clientConfig.setConnectionTimeout(30 * 1000);

        // 如果需要的话，设置 http 代理，ip 以及 port
//        clientConfig.setHttpProxyIp("httpProxyIp");
//        clientConfig.setHttpProxyPort(80);

        // 生成 cos 客户端。
        return new COSClient(cred, clientConfig);
    }

    void shutdownTransferManager(TransferManager transferManager) {
        // 指定参数为 true, 则同时会关闭 transferManager 内部的 COSClient 实例。
        // 指定参数为 false, 则不会关闭 transferManager 内部的 COSClient 实例。
        transferManager.shutdownNow(true);
    }
}
