### Shiro学习笔记（06）💻

------

⌚️ Created by tujietg on 2019/02/25.

🐘 *web集成 拦截器机制*

📚 [教程地址](https://jinnianshilongnian.iteye.com/blog/2018398)

#### web集成 

Shiro提供了与Web集成的支持，其通过一个ShiroFilter入口来拦截需要安全控制的URL，然后进行相应的控制，ShiroFilter类似于如Strut2/SpringMVC这种web框架的前端控制器，其是安全控制的入口点，其负责读取配置（如ini配置文件），然后判断URL是否需要登录/权限等工作。

##### 1，项目搭建

- 创建**webapp**应用

- shiro-web的引入

  ````xml
  <dependency>  
      <groupId>org.apache.shiro</groupId>  
      <artifactId>shiro-web</artifactId>  
      <version>1.2.2</version>  
  </dependency>   
  ````

##### 2，ShiroFilter入口

- **Shiro 1.1配置**

  ```xml
  <filter>  
      <filter-name>iniShiroFilter</filter-name>  
      <filter-class>org.apache.shiro.web.servlet.IniShiroFilter</filter-class>  
      <init-param>  
          <param-name>configPath</param-name>  
          <param-value>classpath:shiro.ini</param-value>  
      </init-param>  
  </filter>  
  <filter-mapping>  
      <filter-name>iniShiroFilter</filter-name>  
      <url-pattern>/*</url-pattern>  
  </filter-mapping>   
  ```
  - iniShiroFilter
    - Shiro的安全控制入口点，通过url-pattern指定需要安全的URL。
  - configPath
    - 通过configPath指定ini配置文件位置

- **Shiro 1.2**及以后版本的配置方式

从Shiro 1.2开始引入了Environment/WebEnvironment的概念，即由它们的实现提供相应的SecurityManager及其相应的依赖。ShiroFilter会自动找到Environment然后获取相应的依赖。

- 与Spring集成

````xml
<filter>  
    <filter-name>shiroFilter</filter-name>  
    <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>  
    <init-param>  
        <param-name>targetFilterLifecycle</param-name>  
        <param-value>true</param-value>  
    </init-param>  
</filter>  
<filter-mapping>  
    <filter-name>shiroFilter</filter-name>  
    <url-pattern>/*</url-pattern>  
</filter-mapping>   
````

DelegatingFilterProxy作用是自动到spring容器查找名字为shiroFilter（filter-name）的bean并把所有Filter的操作委托给它。然后将ShiroFilter配置到spring容器即可:

````xml
<bean id="shiroFilter" class="org.apache.shiro.spring.web.ShiroFilterFactoryBean">  
<property name="securityManager" ref="securityManager"/>  
<!—忽略其他，详见与Spring集成部分 -->  
</bean>   
````

最后：

最后使用org.springframework.web.context.ContextLoaderListener加载这个spring配置文件即可。

##### 3，web ini配置

- **url**模式使用Ant风格模式

  - Ant路径通配符支持?、*、**，注意通配符匹配不包括目录分隔符“/”：

    ?：匹配一个字符，如”/admin?”将匹配/admin1，但不匹配/admin或/admin2；

    *****：匹配零个或多个字符串，如/admin*将匹配/admin、/admin123，但不匹配/admin/1；

    ***\*****：匹配路径中的零个或多个路径**，如/admin/**将匹配/admin/a或/admin/a/b。

url模式匹配顺序是按照在配置中的声明顺序匹配，即从头开始使用第一个匹配的url模式对应的拦截器链。

##### 4，web中实现

- 身份验证（登陆）

- 登陆Servlet

  ```java
  @WebServlet(name = "loginServlet", urlPatterns = "/login")  
  public class LoginServlet extends HttpServlet {  
      @Override  
      protected void doGet(HttpServletRequest req, HttpServletResponse resp)  
        throws ServletException, IOException {  
          req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);  
      }  
      @Override  
      protected void doPost(HttpServletRequest req, HttpServletResponse resp)  
        throws ServletException, IOException {  
          String error = null;  
          String username = req.getParameter("username");  
          String password = req.getParameter("password");  
          Subject subject = SecurityUtils.getSubject();  
          UsernamePasswordToken token = new UsernamePasswordToken(username, password);  
          try {  
              subject.login(token);  
          } catch (UnknownAccountException e) {  
              error = "用户名/密码错误";  
          } catch (IncorrectCredentialsException e) {  
              error = "用户名/密码错误";  
          } catch (AuthenticationException e) {  
              //其他错误，比如锁定，如果想单独处理请单独catch处理  
              error = "其他错误：" + e.getMessage();  
          }  
          if(error != null) {//出错了，返回登录页面  
              req.setAttribute("error", error);  
              req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);  
          } else {//登录成功  
              req.getRequestDispatcher("/WEB-INF/jsp/loginSuccess.jsp").forward(req, resp);  
          }  
      }  
  }   
  ```

#### 拦截器机制

Shiro使用了与Servlet一样的Filter接口进行扩展。

![](http://dl2.iteye.com/upload/attachment/0094/3897/b910abb9-2ef0-33b7-af4d-4c645263ed54.png)



##### 1，注解

- **NameableFilter**

  - NameableFilter给Filter起个名字，如果没有设置默认就是**FilterName**

- **OncePerRequestFilter**

  - OncePerRequestFilter用于防止多次执行Filter的；也就是说一次请求只会走一次拦截器链

- **ShiroFilter**

  - ShiroFilter是整个Shiro的入口点，用于拦截需要安全控制的请求进行处理

- **AdviceFilter**

  - AdviceFilter提供了AOP风格的支持，类似于SpringMVC中的Interceptor：

    ````java
    //类似于AOP中的前置增强；在拦截器链执行之前执行；如果返回true则继续拦截器链
    boolean preHandle(ServletRequest request, ServletResponse response) throws Exception  
    //类似于AOP中的后置返回增强；在拦截器链执行完成后执行；进行后处理（如记录执行时间之类的）
    void postHandle(ServletRequest request, ServletResponse response) throws Exception  
    //afterCompletion：类似于AOP中的后置最终增强；即不管有没有异常都会执行；可以进行清理资源（如接触Subject与线程的绑定之类的）
    void afterCompletion(ServletRequest request, ServletResponse response, Exception exception) throws Exception;   
    ````

- **PathMatchingFilter**

  - 提供了基于Ant风格的请求路径匹配功能及拦截器参数解析的功能，如“roles[admin,user]”自动根据“，”分割解析到一个路径参数配置并绑定到相应的路径。

- **AccessControlFilter**

  AccessControlFilter提供了访问控制的基础功能；比如是否允许访问/当访问拒绝时如何处理等

  ```java
  abstract boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception;  
  boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception;  
  abstract boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception;   
  ```

  - isAccessAllowed：表示是否允许访问；mappedValue就是[urls]配置中拦截器参数部分，如果允许访问返回true，否则false；

  - onAccessDenied：表示当访问拒绝时是否已经处理了；如果返回true表示需要继续处理；如果返回false表示该拦截器实例已经处理了，将直接返回即可。

##### 2，拦截器链

先执行Shiro自己的Filter链；再执行Servlet容器的Filter链（即原始的Filter）。

