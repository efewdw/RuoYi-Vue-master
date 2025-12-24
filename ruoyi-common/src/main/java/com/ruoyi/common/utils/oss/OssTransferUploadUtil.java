package com.ruoyi.common.utils.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * OSS中转上传工具类（前端→服务端→OSS）
 */
@Slf4j
@Component
public class OssTransferUploadUtil {

    @Autowired
    private OSS ossClient;

    // OSS配置

    private String bucketName="zty2206267352";

    private String folderPrefix="";

    private String accessDomain="";

    // 本地临时存储配置

    private String tempPath="D:/file-temp/";

    private String allowedTypes="jpg,jpeg,png,gif,pdf,doc,docx,xls,xlsx,txt";

    /**
     * 校验文件是否合法
     */
    public void validateFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("上传文件不能为空");
        }
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || !originalFilename.contains(".")) {
            throw new RuntimeException("文件名称不合法");
        }
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
        List<String> allowedTypeList = Arrays.asList(allowedTypes.split(","));
        if (!allowedTypeList.contains(suffix)) {
            throw new RuntimeException("不支持该文件类型，允许类型：" + allowedTypes);
        }
    }

    /**
     * 生成OSS文件存储路径（按日期分目录，避免文件重名）
     * 格式：folderPrefix/年/月/日/UUID.后缀
     */
    public String generateOssFilePath(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        // 按日期分目录（如upload/2025/12/22/）
        String datePath = new org.joda.time.DateTime().toString("yyyy/MM/dd/");
        // 唯一文件名
        String uniqueFileName = UUID.randomUUID().toString() + suffix;
        // 完整OSS文件路径
        return folderPrefix + datePath + uniqueFileName;
    }

    /**
     * 保存文件到本地临时目录
     */
    public File saveToTemp(MultipartFile file) throws IOException {
        // 校验文件
        validateFile(file);
        // 确保临时目录存在
        File tempDir = new File(tempPath);
        if (!tempDir.exists()) {
            tempDir.mkdirs();
            log.info("创建本地临时目录成功：{}", tempPath);
        }
        // 生成临时文件名
        String tempFileName = UUID.randomUUID().toString() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String tempFilePath = tempPath + File.separator + tempFileName;
        // 保存到本地
        File tempFile = new File(tempFilePath);
        file.transferTo(tempFile);
        log.info("文件保存到本地临时目录：{}", tempFilePath);
        return tempFile;
    }

    /**
     * 本地文件上传到OSS
     */
    public String uploadTempFileToOss(File tempFile, String ossFilePath) {
        try {
            // 创建OSS上传请求
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, ossFilePath, tempFile);
            // 上传文件
            ossClient.putObject(putObjectRequest);
            log.info("文件上传OSS成功：Bucket={}, 路径={}", bucketName, ossFilePath);
            // 返回OSS文件访问URL
            return accessDomain + ossFilePath;
        } finally {
            // 上传完成后删除本地临时文件
            if (tempFile.exists()) {
                boolean deleted = tempFile.delete();
                if (deleted) {
                    log.info("本地临时文件已删除：{}", tempFile.getPath());
                } else {
                    log.warn("本地临时文件删除失败：{}", tempFile.getPath());
                }
            }
        }
    }

    /**
     * 直接将MultipartFile上传到OSS（无需本地留存，简化版）
     */
    public String uploadMultipartFileToOss(MultipartFile file) throws IOException {
        validateFile(file);
        String ossFilePath = generateOssFilePath(file);
        // 3. 创建ObjectMetadata，配置预览参数（核心步骤）
        ObjectMetadata metadata = new ObjectMetadata();
        // 设置文件MIME类型，让浏览器识别可预览格式
        String contentType = getFileContentType(ossFilePath);
        metadata.setContentType(contentType);
        // 设置为内联预览（inline），避免下载；若要强制下载可设为attachment
        metadata.setContentDisposition("inline");
        // 可选：设置文件缓存时间（3600秒=1小时）
        metadata.setCacheControl("max-age=3600");
        // 直接通过文件流上传（无需本地临时文件）
         ossClient.putObject(bucketName, ossFilePath, file.getInputStream(),metadata);
        log.info("MultipartFile直接上传OSS成功：{}", ossFilePath);
        return accessDomain + ossFilePath;
    }
    /**
     * 获取文件对应的MIME类型（关键：确保浏览器识别文件类型）
     */
    private String getFileContentType(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            return "application/octet-stream"; // 默认二进制流
        }
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        switch (suffix) {
            // 图片类型
            case "jpg":
            case "jpeg":
                return "image/jpeg";
            case "png":
                return "image/png";
            case "gif":
                return "image/gif";
            case "bmp":
                return "image/bmp";
            case "webp":
                return "image/webp";
            // 文档类型（支持浏览器预览的格式）
            case "pdf":
                return "application/pdf";
            case "txt":
                return "text/plain";
            case "html":
                return "text/html";
            // 其他格式默认二进制流（会触发下载）
            default:
                return "application/octet-stream";
        }
    }

}