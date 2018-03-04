package com.knight.cloud;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.core.Response;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;

/**
 * 使用CXF框架调用REST服务
 * @author knight
 *
 */
public class CxfClient {
	public static void main(String[] args) throws IOException {
		// 创建WebClient对象
		WebClient client = WebClient.create("http://localhost:8080/person/1");
		//获取响应
		Response response = client.get();
		// 获取响应内容
		InputStream entity = (InputStream)response.getEntity();
		String content = IOUtils.readStringFromStream(entity);
		System.out.println(content);
	}
}
