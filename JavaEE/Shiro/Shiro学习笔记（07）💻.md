### Shiro学习笔记（07）💻

---

⌚️ Created by tujietg on 2019/02/11.

🐘 *会话管理*

📚 [教程地址](https://jinnianshilongnian.iteye.com/blog/2018398)

##### 1，Shiro会话

Shiro提供了完整的企业级会话管理功能，不依赖于底层容器（如web容器tomcat），不管JavaSE还是JavaEE环境都可以使用，提供了会话管理、会话事件监听、会话存储/持久化、容器无关的集群、失效/过期支持、对Web的透明支持、SSO单点登录的支持等特性。即直接使用Shiro的会话管理可以直接替换如Web容器的会话管理。

- 获取会话以及相关信息

  ````java
  //获取会话
  login("classpath:shiro.ini", "zhang", "123");  
  Subject subject = SecurityUtils.getSubject();  
  Session session = subject.getSession();   
  //获取会话唯一标识
  session.getId();  
  //获取Subject主机ip
  session.getHost();  
  //。。。其他的API（查看文档）
  ````

- 会话管理器

会话管理器管理着应用中所有Subject的会话的创建、维护、删除、失效、验证等工作。

##### 2，Shiro会话实现。

- **DefaultSessionManager**
  - DefaultSecurityManager使用的默认实现，用于JavaSE环境
- **ServletContainerSessionManager**
  - DefaultWebSecurityManager，用于Web环境，其直接使用Servlet容器的会话。
- **DefaultWebSessionManager**
  - 用于Web环境的实现，可以替代ServletContainerSessionManager，自己维护着会话，直接废弃了Servlet容器的会话管理。

##### 3，会话有关API

- 设置会话全局过期时间

  ````java
  sessionManager. globalSessionTimeout=1800000 
  ````

- ServletContainerSessionManager进行会话管理

  - 依赖于Servlet容器

    - 设置过期时间在web.xml

      ```xml
      <session-config>  
        <session-timeout>30</session-timeout>  
      </session-config>  
      ```


##### 4，会话监听器

会话监听器用于监听会话创建、过期及停止事件。

- onStart
  - //会话创建时触发  
- onExpiration
  - 会话过期时触发  
- onStop
  - //退出/会话过期时触发  

##### 5，会话存储/持久化 

Shiro提供SessionDAO用于会话的CRUD

##### 6，会话验证

Shiro提供了会话验证调度器，用于定期的验证会话是否已过期，如果过期将停止会话；出于性能考虑，一般情况下都是获取会话时来验证会话是否过期并停止会话的

- SessionValidationScheduler实现

##### 7，sessionFactory

sessionFactory是创建会话的工厂，根据相应的Subject上下文信息来创建会话；默认提供了SimpleSessionFactory用来创建SimpleSession会话。







