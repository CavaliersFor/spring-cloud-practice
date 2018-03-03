package com.knight.cloud;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

/**
 * 自身服务健康决定器，对自身服务的健康状况的检测
 * @author knight
 *
 */
@Component
public class MyHealthIndicator implements HealthIndicator{

	@Override
	public Health health() {
		if(HealthController.canVisitDb) {
			//成功连接数据库
			return new Health.Builder(Status.UP).build();
		}else {
			//数据库连接失败 
			return new Health.Builder(Status.DOWN).build();
		}
	}

}
