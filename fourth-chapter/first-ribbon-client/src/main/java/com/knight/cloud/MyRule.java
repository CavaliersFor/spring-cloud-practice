package com.knight.cloud;

import java.util.List;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;
/**
 * 自定义负载规则
 * @author knight
 *
 */
public class MyRule implements IRule {

	private ILoadBalancer lb;

	public MyRule() {
	}

	@Override
	public Server choose(Object key) {
		// 获取所有的服务器
		List<Server> servers = lb.getAllServers();
		// 只返回第一个
		return servers.get(0);
	}

	@Override
	public void setLoadBalancer(ILoadBalancer lb) {
		this.lb = lb;
	}

	@Override
	public ILoadBalancer getLoadBalancer() {
		return this.lb;
	}

}
