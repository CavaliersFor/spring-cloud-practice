package com.knight.cloud;

import java.util.ArrayList;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.Server;

public class TestPingUrl {
	public static void main(String[] args) throws Exception {
		BaseLoadBalancer lb = new BaseLoadBalancer();
		// 设置自定义的负载规则
		lb.setRule(new MyRule());

		// 添加服务器
		ArrayList<Server> servers = new ArrayList<>();
		servers.add(new Server("localhost", 8080));
		// 一个不存在的端口
		servers.add(new Server("localhost", 8088));
		lb.addServers(servers);

		// 设置IPing的实现类
		lb.setPing(new PingUrl());
		// 设置Ping的间隔时间为2秒
		lb.setPingInterval(2);
		Thread.sleep(6000);

		for (Server s : servers) {
			System.out.println(s.getHostPort() + " 状态：" + s.isAlive());
		}
	}
}
