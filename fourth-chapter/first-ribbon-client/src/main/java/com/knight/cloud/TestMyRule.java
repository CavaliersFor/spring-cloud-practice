package com.knight.cloud;

import java.util.ArrayList;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.Server;

public class TestMyRule {
	public static void main(String[] args) {
		BaseLoadBalancer lb = new BaseLoadBalancer();
		// 设置自定义的负载规则
		lb.setRule(new MyRule());

		// 添加服务器
		ArrayList<Server> servers = new ArrayList<>();
		servers.add(new Server("localhost", 8080));
		servers.add(new Server("localhost", 8081));
		lb.addServers(servers);
		// 进行6次服务器选择
		for (int i = 0; i < 6; i++) {
			Server server = lb.chooseServer(null);
			System.out.println(server);
		}
	}
}
