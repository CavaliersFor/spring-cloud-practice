package com.knight.cloud;

/**
 * @author knight
 */
public class HelloMain {
    public static void main(String[] args) {
        // 请求正常的服务
        String normalUrl = "http://localhost:8080/normalHello";
        HelloCommand command = new HelloCommand(normalUrl);
        String result = command.execute();
        System.out.println("请求正常的服务，结果：" + result);
    }
}
