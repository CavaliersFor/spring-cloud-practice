package com.knight.cloud;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author knight
 * @Description: 测试controller
 * @date 2018/3/4 16:23
 */
@RestController
public class MyController {

    @RequestMapping("/normalHello")
    public String normalHello() {
        return "Hello World";
    }

    @RequestMapping("/errorHello")
    public String errorHello() throws Exception {
        // 模拟需要处理10秒
        Thread.sleep(10 * 1000);
        return "Error Hello World";
    }

}
