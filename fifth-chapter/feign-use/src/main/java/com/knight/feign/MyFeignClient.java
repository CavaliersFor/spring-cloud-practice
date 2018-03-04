/**   
 * Copyright © 2018 公司名. All rights reserved.
 * 
 * @Title: MyFeignClient.java 
 * @Prject: feign-use
 * @Package: com.knight.feign 
 * @Description: TODO
 * @author: YQ   
 * @date: 2018年2月28日 下午9:35:26 
 * @version: V1.0   
 */
package com.knight.feign;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import feign.Client;
import feign.Request;
import feign.Request.Options;
import feign.Response;

/**
 * 自定义Feign客户端，通过HttpClient创建
 * 
 * @ClassName: MyFeignClient
 * @author: knight
 * @date: 2018年2月28日 下午9:35:26
 */
public class MyFeignClient implements Client {

	/*
	 * 主要内容就是将Feign的request转化为HttpClient的request，
	 * 然后再将HttpClient的Response转化为Feign的response
	 * 
	 * @param arg0
	 * 
	 * @param arg1
	 * 
	 * @return
	 * 
	 * @throws IOException
	 * 
	 * @Title: execute
	 * 
	 * @see feign.Client#execute(feign.Request, feign.Request.Options)
	 */
	@Override
	public Response execute(Request request, Options options) throws IOException {
		System.out.println("-----这是自定义的Feign客户端-----");

		// 创建一个默认的HttpClient
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 获取调用Http请求的方法
		String method = request.method();
		// 创建一个HttpClient的HttpRequest
		HttpRequestBase httpRequest = new HttpRequestBase() {

			@Override
			public String getMethod() {
				return method;
			}
		};

		try {
			// 设置请求地址
			httpRequest.setURI(new URI(request.url()));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		// 执行请求，获取响应
		HttpResponse httpResponse = httpClient.execute(httpRequest);
		// 获取主体信息
		byte[] body = EntityUtils.toByteArray(httpResponse.getEntity());

		// 将httpClient响应的对象转化为Feign的Response
		Response response = Response.builder().body(body).headers(new HashMap<String, Collection<String>>())
				.status(httpResponse.getStatusLine().getStatusCode()).build();

		return response;
	}

}
