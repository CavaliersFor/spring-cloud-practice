package com.knight.cloud.config;

import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * 使用注解@RibbonClient，配置了RibbonClient的名称为cloud-provider，使用的配置类是 MyConfig
 * 也就是cloud-provider的客户端使用MyRule与MyPing两个类
 * @author knight
 *
 */
//@RibbonClient(name = "cloud-provider", configuration = MyConfig.class)
public class CloudProviderConfig {

}
