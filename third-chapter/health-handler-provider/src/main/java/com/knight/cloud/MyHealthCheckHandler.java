package com.knight.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import com.netflix.appinfo.HealthCheckHandler;
import com.netflix.appinfo.InstanceInfo.InstanceStatus;

/**
 * 该类可用将该服务状况发送给注册服务，Eureka中会启动一个定时器，定时刷新本地实例的信息
 * 并且执行'getStatus'方法，再将服务实例的状态更新到注册服务器中
 * 定时器默认30秒执行一次，可用通过修改eureka.client.instance-info-replication-interval-seconds属性
 * @author knight
 *
 */
@Component
public class MyHealthCheckHandler implements HealthCheckHandler {

	@Autowired
	private MyHealthIndicator indicator;

	@Override
	public InstanceStatus getStatus(InstanceStatus currentStatus) {
		Status s = indicator.health().getStatus();
		if (s.equals(Status.UP)) {
			System.out.println("数据库正常连接  "+System.currentTimeMillis());
			return InstanceStatus.UP;
		} else {
			System.out.println("数据库无法连接  "+System.currentTimeMillis());
			return InstanceStatus.DOWN;
		}
	}
}
