/**   
 * Copyright © 2018 公司名. All rights reserved.
 * 
 * @Title: RSClient.java 
 * @Prject: feign-use
 * @Package: com.knight.feign 
 * @Description: TODO
 * @author: YQ   
 * @date: 2018年3月1日 下午9:04:21 
 * @version: V1.0   
 */
package com.knight.feign;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/** 
 * @ClassName: RSClient 
 * @author: knight
 * @date: 2018年3月1日 下午9:04:21  
 */
public interface RSClient {
	// 等价于@RequestLine("GET /hello")
	@GET @Path("/hello")
	String rsHello();
}
