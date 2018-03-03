package com.knight.cloud;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 模拟数据库是否连接成功
 * 
 * @author knight
 *
 */
@RestController
public class HealthController {

	public static Boolean canVisitDb = false;

	@RequestMapping(value = "/db/{canVisitDb}", method = RequestMethod.GET)
	public String setConnectState(@PathVariable("canVisitDb") Boolean canVisitDb) {
		this.canVisitDb = canVisitDb;
		return "当前数据库是否正常: " + canVisitDb;
	}
}
