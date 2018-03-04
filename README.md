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

  默认情况下客户端每隔30秒去服务器端抓取注册表(可用的服务列表)，并保存到本地缓存中。可用修改`eureka.client.registry-fetch-interval-seconds`配置改变注册表抓取间隔

- 配置与使用元数据

- 自我保护模式



# 第四章学习-负载均衡

### 认识Ribbon ['rɪbən] 

- Ribbon简介

  Ribbon是Netflix下的负载均衡项目，提供以下特性：

  - 负载均衡器，可支持插拔式的负载均衡规则
  - 对多个协议提供支持
  - 集成了负载均衡的客户端

  Ribbon主要有以下三大子模块：

  - ribbon-core：该模块为Ribbon项目的核心，主要包括负载均衡器接口定义、客户端接口定义、内置负载均衡实现

  - ribbon-eurka：为Eureka客户端提供的负载均衡器

  - ribbon-httpclient：对Apache的HttpClient进行封装，提供了具有负载均衡器功能的REST客户端



### Ribbon的负载均衡机制

- 负载均衡器

  ribbon的负载均衡器接口定义了服务器的操作，主要用于进行服务器选择。当RestClient类发送请求时，会使用负载均衡器(`ILoadBalancer`)接口的`choseServer`方法，根据特定的逻辑选择服务器。服务器列表可以通过`listOfServers`配置。

- 自定义负载规则

  因为选择服务器是通过负载均衡器(`ILoadBalancer`)接口的`choseServer`方法。而在`BaseLoadBalancer`类中，则使用`IRule`接口的`choose`方法决定选择哪个服务器；我们可以自定义一个负载规则，具体如下：

  ```java
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
  //对自定义规则测试
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
  //结果返回都是8080
  ```



- Ribbon自带的负载规则

  Ribbon提供了若干个负载规则，具体如下

  - RoundRobinRule：系统默认规则，通过简单的轮询选择服务器
  - AvailabilityFilteringRule：
  - WeightedResponseTimeRule：为每个服务器赋予一个权重值，服务器响应时间越长，该权重值越小
  - ZoneAvoidanceRule：以区域、可用服务器为基础进行服务器选择
  - BestAvailableRule：忽略“短路”的服务器，并选择并发数较低的服务器
  - RandomRule：随机选择可用的服务器
  - RetryRule：重试的选择逻辑


### RestTemplate负载均衡

RestTemplate本是spring-web项目中的一个REST客户端，本身并不具备负载均衡的功能；加入`@LoadBalanced`注解则RestTemplate就具有负载均衡功能。这是因为添加该注解后，在Spring容器启动时，会为这些修饰过的RestTemplate添加拦截器，拦截器中使用了`LoadBalancerClient`来处理请求。`LoadBalancerClient`本来就是Spring封装的负载均衡客户端，通过间接处理，使得RestTemplate具有负载均衡的能力



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



### 在Spring Cloud中使用Feign
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





# 第六章Spring Cloud的保护机制

### 使用Hystrix
- Hystrix的运作流程图
<br/>
![Hystrix的运作流程图](https://github.com/CavaliersFor/spring-cloud-practice/blob/master/image/sixth-chapter/Hystrix%E7%9A%84%E8%BF%90%E4%BD%9C%E6%B5%81%E7%A8%8B%E5%9B%BE.png)
