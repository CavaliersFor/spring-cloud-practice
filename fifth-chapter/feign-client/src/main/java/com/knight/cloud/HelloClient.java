package com.knight.cloud;

import feign.RequestLine;

/**
 * 表示一个服务接口
 * @author knight
 *
 */
public interface HelloClient {

    // @RequestLine接口表示使用GET方法向 /hello发送请求
    @RequestLine("GET /hello")
    String sayHello();

}
