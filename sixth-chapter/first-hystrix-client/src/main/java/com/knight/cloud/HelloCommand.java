package com.knight.cloud;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 命令类
 *
 * @author knight
 */
public class HelloCommand extends HystrixCommand<String> {

    private String url;
    private CloseableHttpClient httpClient;

    public HelloCommand(String url) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.url = url;
        this.httpClient = HttpClients.createDefault();
    }

    @Override
    protected String run() throws Exception {
        try {
            // 调用GET方法请求服务
            HttpGet httpGet = new HttpGet(url);
            // 得到响应服务
            HttpResponse response = httpClient.execute(httpGet);
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    protected String getFallback() {
        System.out.println("执行HelloCommand的回退方法");
        return "error";
    }
}
