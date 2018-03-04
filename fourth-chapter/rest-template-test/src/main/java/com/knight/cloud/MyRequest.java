/**   
 * Copyright © 2018 公司名. All rights reserved.
 * 
 * @Title: MyRequest.java 
 * @Prject: rest-template-test
 * @Package: com.knight.cloud 
 * @Description: TODO
 * @author: YQ   
 * @date: 2018年2月25日 下午3:33:37 
 * @version: V1.0   
 */
package com.knight.cloud;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;

/** 
 * 在该类中，会将原来请求的url进行改写，只要使用这个对象将所有的请求转发到http://localhost:8080/
 * Spring Cloud在对RestTemplate进行拦截的时候也做了同样的事
 * @ClassName: MyRequest 
 * @author: knight
 * @date: 2018年2月25日 下午3:33:37  
 */
public class MyRequest implements HttpRequest{

	private HttpRequest sourceRequest;
	
	
	 /** 
	 * @Title:MyRequest
	 */
	public MyRequest(HttpRequest sourceRequest) {
		this.sourceRequest = sourceRequest;
	}
	
	/* (non Javadoc) 
	 * @return
	 * @Title: getHeaders 
	 * @see org.springframework.http.HttpMessage#getHeaders() 
	 */
	@Override
	public HttpHeaders getHeaders() {
		return sourceRequest.getHeaders();
	}

	/* (non Javadoc) 
	 * @return
	 * @Title: getMethod 
	 * @see org.springframework.http.HttpRequest#getMethod() 
	 */
	@Override
	public HttpMethod getMethod() {
		return sourceRequest.getMethod();
	}

	/* (non Javadoc) 
	 * @return
	 * @Title: getURI 
	 * @see org.springframework.http.HttpRequest#getURI() 
	 */
	@Override
	public URI getURI() {
		URI oldUri = sourceRequest.getURI();
		try {
			URI newUri = new URI("http://localhost:8080/");
			return newUri;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return oldUri;
	}

}
