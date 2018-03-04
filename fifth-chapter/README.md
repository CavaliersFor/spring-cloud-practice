# spring-cloud-exercise

  ## Spring Cloud与Netflix
  Spring Cloud Netflix模块，主要封装了Netflix的以下项目:
  - Eureka：基于REST服务的分布式中间件，主要用于服务管理
  - Hystrix：容错框架，通过添加延迟阈值以及容错的逻辑，帮助我们控制分布式系统间组件的交互
  - Feign：一个REST客户端，目的是简化Web Service客户端的开发
  - Ribbon：负载均衡框架，在微服务集群中为各个客户端的通信提供支持，它主要实现中间层应用程序的负载均衡
  - Zuul：为微服务集群提供代理、过滤、路由等功能


# 第五章学习-REST客户端Feign

### REST客户端
- CXF调用REST服务
- Restlet调用REST服务

### Feign框架的使用
   Feign是Github上的一个开源项目，目的是简化Web Service客户端的开发.

- 编码器

  请求的过程中，有些情况需要对请求的内容进行处理。例如服务器发布的服务接收的是JSON格式，而客户端使用的是对象，这种情况就可以使用编码器，将对象转化为JSON字符串

- 解码器

  编码器是对请求内容进行处理，解码器则会对服务响应的内容进行处理；例如将解析响应的JSON或者XML字符串，转化为我们所需要的对象。

- 自定义编码器与解码器

  自定义编码器可以实现`feign.codec.Encoder`接口；

  自定义解码器可以实现`feign.codec.Decoder`接口；

- 自定义Feign客户端

  通过实现`feign.Client`接口，主要的过程是将Feign的Request实例转化为HttpClient的request，再将HttpClient响应的Response转化为Feign的Response；这里使用HttpClient作为客户端，默认情况下Feign使用的是HttpURLConnection连接HTTP服务

- 使用第三方注解

  除了可以使用Feign自带的注解，还可以使用第三方注解，如下使用JAXRS规范的注解

  ```java
  // 等价于@RequestLine("GET /hello")
  @GET @Path("/hello")
  ```

- Feign解析第三方注解

  通过实现`feign.Contract.BaseContract` 类来自定义`Contract`，它的作用就相当于一个翻译器

- 请求拦截器

  Feign支持请求拦截器，在发送请求前，可以对发送的模板进行操作，例如设置请求头的属性

- 接口日志

  默认情况下，不会记录接口的日志

### 在Spring Cloud中使用Feign

- Spring Cloud整合Feign
- Feign负载均衡