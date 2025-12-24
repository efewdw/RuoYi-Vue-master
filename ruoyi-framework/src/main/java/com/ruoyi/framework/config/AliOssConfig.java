package com.ruoyi.framework.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 阿里云OSS配置类
 */
@Configuration
public class AliOssConfig {


    private String endpoint="oss-cn-beijing.aliyuncs.com";


    private String accessKeyId="LTAI5tLbdBimV9YzNeAXbivv";


    private String accessKeySecret="ehZqBFH1BCSHFsznZ2THozAMteZtG6";

    /**
     * 初始化OSS客户端，注入Spring容器
     */
    @Bean
    public OSS ossClient() {
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }
}