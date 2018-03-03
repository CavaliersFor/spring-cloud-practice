# spring-cloud-exercise

  ## Spring Cloud与Netflix
  Spring Cloud Netflix模块，主要封装了Netflix的以下项目:
  - Eureka：基于REST服务的分布式中间件，主要用于服务管理
  - Hystrix：容错框架，通过添加延迟阈值以及容错的逻辑，帮助我们控制分布式系统间组件的交互
  - Feign：一个REST客户端，目的是简化Web Service客户端的开发
  - Ribbon：负载均衡框架，在微服务集群中为各个客户端的通信提供支持，它主要实现中间层应用程序的负载均衡
  - Zuul：为微服务集群提供代理、过滤、路由等功能

# 第三章学习-微服务器的发布与调用

### 第一个Eurka应用

- Eureka注册服务器
- 服务提供者
- 服务调用者

### Eureka集群搭建

### 服务实例的健康自检

- 使用Spring Boot Actuator实现系统监控
- 通过自定义`HealthIndicator`决定自身服务的健康
- 通过自定义`HealthCheckHandler`健康检查处理器，Eureka中会启动一个定时器(默认30秒执行一次)，定时刷新本地实例的信息，并执行处理器的`getStatus`方法
- 服务查询，可以使用Spring Cloud的`discoveryClient`或Eureka的`eurekaClient`查询服务实例

### Eureka的常用配置

- 心跳检测配置

  客户端实例会向服务器发送周期性的心跳，默认30秒一次，可用通过`eureka.instance.lease-renewal-interval-in-seconds`配置修改

- 注册表抓取间隔

  默认情况下客户端每隔30秒去服务器端抓取注册表(可用的服务列表)，并保存到本地缓存中。可用修改`**eureka.client.registry-fetch-interval-seconds`配置改变注册表抓取间隔

- 配置与使用元数据

- 自我保护模式