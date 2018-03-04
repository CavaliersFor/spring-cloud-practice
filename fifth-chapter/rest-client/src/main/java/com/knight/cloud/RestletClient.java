package com.knight.cloud;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.restlet.data.MediaType;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

/**
 * 使用Restlet模拟客户端请求
 * 
 * @author knight
 *
 */
public class RestletClient {
	public static void main(String[] args) throws IOException {
		ClientResource client = new ClientResource("http://localhost:8080/person/1");
		// 调用get方法
		Representation response = client.get(MediaType.APPLICATION_JSON);
		// JacksonRepresentation实例，将响应信息转化成Map
		JacksonRepresentation jr = new JacksonRepresentation(response, HashMap.class);
		// 获取转化后的Map
		Map result = (HashMap) jr.getObject();

		System.out.println(
				result.get("id") + "-" + result.get("name") + "-" + result.get("age") + "-" + result.get("message"));
	}
}
