/**
 * 
 */
package com.knight.cloud;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
 * @Description: 自定义拦截器，使用该拦截器可以对请求url进行修改
 * @author knight
 * @date: 2018年2月25日 下午2:57:34
 */
public class MyInterceptor implements ClientHttpRequestInterceptor {

	/* (non Javadoc) 
	 * @Title: intercept
	 * @param request
	 * @param body
	 * @param execution
	 * @return
	 * @throws IOException 
	 * @see org.springframework.http.client.ClientHttpRequestInterceptor#intercept(org.springframework.http.HttpRequest, byte[], org.springframework.http.client.ClientHttpRequestExecution) 
	 */
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		System.out.println("=========== 这是自定义拦截器实现");
		System.out.println("            原来的URI: "+request.getURI());
     	MyRequest newRequest = new MyRequest(request);
     	System.out.println("            拦截后最新的URI: "+newRequest.getURI());
		return execution.execute(newRequest, body);
	}

}
