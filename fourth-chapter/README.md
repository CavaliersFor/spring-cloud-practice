# spring-cloud-exercise

  ## Spring Cloud与Netflix
  Spring Cloud Netflix模块，主要封装了Netflix的以下项目:
  - Eureka：基于REST服务的分布式中间件，主要用于服务管理
  - Hystrix：容错框架，通过添加延迟阈值以及容错的逻辑，帮助我们控制分布式系统间组件的交互
  - Feign：一个REST客户端，目的是简化Web Service客户端的开发
  - Ribbon：负载均衡框架，在微服务集群中为各个客户端的通信提供支持，它主要实现中间层应用程序的负载均衡
  - Zuul：为微服务集群提供代理、过滤、路由等功能



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
















