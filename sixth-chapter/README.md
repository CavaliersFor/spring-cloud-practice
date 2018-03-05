# spring-cloud-exercise

  ## Spring Cloud与Netflix
  Spring Cloud Netflix模块，主要封装了Netflix的以下项目:
  - Eureka：基于REST服务的分布式中间件，主要用于服务管理
  - Hystrix：容错框架，通过添加延迟阈值以及容错的逻辑，帮助我们控制分布式系统间组件的交互
  - Feign：一个REST客户端，目的是简化Web Service客户端的开发
  - Ribbon：负载均衡框架，在微服务集群中为各个客户端的通信提供支持，它主要实现中间层应用程序的负载均衡
  - Zuul：为微服务集群提供代理、过滤、路由等功能



# 第六章Spring Cloud的保护机制

### 使用Hystrix

Hystrix的运作流程图:

![Hystrix的运作流程图](https://github.com/CavaliersFor/spring-cloud-practice/blob/master/image/sixth-chapter/Hystrix%E7%9A%84%E8%BF%90%E4%BD%9C%E6%B5%81%E7%A8%8B%E5%9B%BE.png)