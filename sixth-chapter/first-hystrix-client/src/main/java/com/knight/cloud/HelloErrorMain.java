package com.knight.cloud;

/**
 * 请求故障的服务
 * @author knight
 */
public class HelloErrorMain {
    public static void main(String[] args) {
        // 请求正常的服务
        String errorlUrl = "http://localhost:8080/normalHello";
        HelloCommand command = new HelloCommand(errorlUrl);
        String result = command.execute();
        System.out.println("请求故障的服务，结果：" + result);
    }
}
