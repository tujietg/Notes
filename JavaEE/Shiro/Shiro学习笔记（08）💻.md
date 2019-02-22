### Shiro学习笔记（08）💻

----

⌚️ Created by tujietg on 2019/02/22.

🐘 *缓存机制 & 与Sping集成*

📚 [教程地址](https://jinnianshilongnian.iteye.com/blog/2018398)

#### Cache

##### 1.Cache机制

Shiro提供了类似于Spring的Cache抽象，即Shiro本身不实现Cache，但是对Cache进行了又抽象，方便更换不同的底层Cache实现。

- Cache接口

- **CacheManager**

  ```java
  public interface CacheManager {  
      //根据缓存名字获取一个Cache  
      public <K, V> Cache<K, V> getCache(String name) throws CacheException;  
  }  
  ```

- **CacheManagerAware**

  ```java
  public interface CacheManagerAware {  
      //注入CacheManager  
      void setCacheManager(CacheManager cacheManager);  
  }  
  ```

Shiro内部相应的组件（DefaultSecurityManager）会自动检测相应的对象（如Realm）是否实现了CacheManagerAware并自动注入相应的CacheManager。

##### 2，Realm缓存

Shiro提供了CachingRealm，其实现了CacheManagerAware接口，提供了缓存的一些基础实现；另外AuthenticatingRealm及AuthorizingRealm分别提供了对AuthenticationInfo 和AuthorizationInfo信息的缓存。

#### Spring集成

Shiro的组件都是JavaBean/POJO式的组件，所以非常容易使用Spring进行组件管理，可以非常方便的从ini配置迁移到Spring进行管理，且支持JavaSE应用及Web应用的集成。

##### 1，javaSE(看看就好了)

```xml
<!-- 缓存管理器 使用Ehcache实现 -->  
<bean id="cacheManager" class="org.apache.shiro.cache.ehcache.EhCacheManager">  
    <property name="cacheManagerConfigFile" value="classpath:ehcache.xml"/>  
</bean>  
  
<!-- 凭证匹配器 -->  
<bean id="credentialsMatcher" class="  
com.github.zhangkaitao.shiro.chapter12.credentials.RetryLimitHashedCredentialsMatcher">  
    <constructor-arg ref="cacheManager"/>  
    <property name="hashAlgorithmName" value="md5"/>  
    <property name="hashIterations" value="2"/>  
    <property name="storedCredentialsHexEncoded" value="true"/>  
</bean>  
  
<!-- Realm实现 -->  
<bean id="userRealm" class="com.github.zhangkaitao.shiro.chapter12.realm.UserRealm">  
    <property name="userService" ref="userService"/>  
    <property name="credentialsMatcher" ref="credentialsMatcher"/>  
    <property name="cachingEnabled" value="true"/>  
    <property name="authenticationCachingEnabled" value="true"/>  
    <property name="authenticationCacheName" value="authenticationCache"/>  
    <property name="authorizationCachingEnabled" value="true"/>  
    <property name="authorizationCacheName" value="authorizationCache"/>  
</bean>  
<!-- 会话ID生成器 -->  
<bean id="sessionIdGenerator"   
class="org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator"/>  
<!-- 会话DAO -->  
<bean id="sessionDAO"   
class="org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO">  
    <property name="activeSessionsCacheName" value="shiro-activeSessionCache"/>  
    <property name="sessionIdGenerator" ref="sessionIdGenerator"/>  
</bean>  
<!-- 会话验证调度器 -->  
<bean id="sessionValidationScheduler"   
class="org.apache.shiro.session.mgt.quartz.QuartzSessionValidationScheduler">  
    <property name="sessionValidationInterval" value="1800000"/>  
    <property name="sessionManager" ref="sessionManager"/>  
</bean>  
<!-- 会话管理器 -->  
<bean id="sessionManager" class="org.apache.shiro.session.mgt.DefaultSessionManager">  
    <property name="globalSessionTimeout" value="1800000"/>  
    <property name="deleteInvalidSessions" value="true"/>  
    <property name="sessionValidationSchedulerEnabled" value="true"/>  
   <property name="sessionValidationScheduler" ref="sessionValidationScheduler"/>  
    <property name="sessionDAO" ref="sessionDAO"/>  
</bean>  
<!-- 安全管理器 -->  
<bean id="securityManager" class="org.apache.shiro.mgt.DefaultSecurityManager">  
    <property name="realms">  
        <list><ref bean="userRealm"/></list>  
    </property>  
    <property name="sessionManager" ref="sessionManager"/>  
    <property name="cacheManager" ref="cacheManager"/>  
</bean>  
<!-- 相当于调用SecurityUtils.setSecurityManager(securityManager) -->  
<bean class="org.springframework.beans.factory.config.MethodInvokingFactoryBean">  
<property name="staticMethod"   
value="org.apache.shiro.SecurityUtils.setSecurityManager"/>  
    <property name="arguments" ref="securityManager"/>  
</bean>  
<!-- Shiro生命周期处理器-->  
<bean id="lifecycleBeanPostProcessor"   
class="org.apache.shiro.spring.LifecycleBeanPostProcessor"/> 
```

##### 2，Web

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:util="http://www.springframework.org/schema/util"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="
       http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util.xsd">

    <!-- 缓存管理器 使用Ehcache实现 -->
    <bean id="cacheManager" class="org.apache.shiro.cache.ehcache.EhCacheManager">
        <property name="cacheManagerConfigFile" value="classpath:ehcache.xml"/>
    </bean>

    <!-- 凭证匹配器 -->
    <bean id="credentialsMatcher" class="com.github.zhangkaitao.shiro.chapter12.credentials.RetryLimitHashedCredentialsMatcher">
        <constructor-arg ref="cacheManager"/>
        <property name="hashAlgorithmName" value="md5"/>
        <property name="hashIterations" value="2"/>
        <property name="storedCredentialsHexEncoded" value="true"/>
    </bean>

    <!-- Realm实现 -->
    <bean id="userRealm" class="com.github.zhangkaitao.shiro.chapter12.realm.UserRealm">
        <property name="userService" ref="userService"/>
        <property name="credentialsMatcher" ref="credentialsMatcher"/>
        <property name="cachingEnabled" value="true"/>
        <property name="authenticationCachingEnabled" value="true"/>
        <property name="authenticationCacheName" value="authenticationCache"/>
        <property name="authorizationCachingEnabled" value="true"/>
        <property name="authorizationCacheName" value="authorizationCache"/>
    </bean>

    <!-- 会话ID生成器 -->
    <bean id="sessionIdGenerator" class="org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator"/>

    <!-- 会话Cookie模板 -->
    <bean id="sessionIdCookie" class="org.apache.shiro.web.servlet.SimpleCookie">
        <constructor-arg value="sid"/>
        <property name="httpOnly" value="true"/>
        <property name="maxAge" value="180000"/>
    </bean>

    <!-- 会话DAO -->
    <bean id="sessionDAO" class="org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO">
        <property name="activeSessionsCacheName" value="shiro-activeSessionCache"/>
        <property name="sessionIdGenerator" ref="sessionIdGenerator"/>
    </bean>

    <!-- 会话验证调度器 -->
    <bean id="sessionValidationScheduler" class="org.apache.shiro.session.mgt.quartz.QuartzSessionValidationScheduler">
        <property name="sessionValidationInterval" value="1800000"/>
        <property name="sessionManager" ref="sessionManager"/>
    </bean>

    <!-- 会话管理器 -->
    <bean id="sessionManager" class="org.apache.shiro.web.session.mgt.DefaultWebSessionManager">
        <property name="globalSessionTimeout" value="1800000"/>
        <property name="deleteInvalidSessions" value="true"/>
        <property name="sessionValidationSchedulerEnabled" value="true"/>
        <property name="sessionValidationScheduler" ref="sessionValidationScheduler"/>
        <property name="sessionDAO" ref="sessionDAO"/>
        <property name="sessionIdCookieEnabled" value="true"/>
        <property name="sessionIdCookie" ref="sessionIdCookie"/>
    </bean>

    <!-- 安全管理器 -->
    <bean id="securityManager" class="org.apache.shiro.web.mgt.DefaultWebSecurityManager">
        <property name="realm" ref="userRealm"/>
        <property name="sessionManager" ref="sessionManager"/>
        <property name="cacheManager" ref="cacheManager"/>
    </bean>

    <!-- 相当于调用SecurityUtils.setSecurityManager(securityManager) -->
    <bean class="org.springframework.beans.factory.config.MethodInvokingFactoryBean">
        <property name="staticMethod" value="org.apache.shiro.SecurityUtils.setSecurityManager"/>
        <property name="arguments" ref="securityManager"/>
    </bean>

    <!-- 基于Form表单的身份验证过滤器 -->
    <bean id="formAuthenticationFilter" class="org.apache.shiro.web.filter.authc.FormAuthenticationFilter">
        <property name="usernameParam" value="username"/>
        <property name="passwordParam" value="password"/>
        <property name="loginUrl" value="/login.jsp"/>
    </bean>

    <!-- Shiro的Web过滤器 -->
    <bean id="shiroFilter" class="org.apache.shiro.spring.web.ShiroFilterFactoryBean">
        <property name="securityManager" ref="securityManager"/>
        <property name="loginUrl" value="/login.jsp"/>
        <property name="unauthorizedUrl" value="/unauthorized.jsp"/>
        <property name="filters">
            <util:map>
                <entry key="authc" value-ref="formAuthenticationFilter"/>
            </util:map>
        </property>
        <property name="filterChainDefinitions">
            <value>
                /index.jsp = anon
                /unauthorized.jsp = anon
                /login.jsp = authc
                /logout = logout
                /** = user
            </value>
        </property>
    </bean>

    <!-- Shiro生命周期处理器-->
    <bean id="lifecycleBeanPostProcessor" class="org.apache.shiro.spring.LifecycleBeanPostProcessor"/>

</beans>

```

##### Shiro权限注解

Shiro提供了相应的注解用于权限控制，如果使用这些注解就需要使用AOP的功能来进行判断，如Spring AOP；Shiro提供了Spring AOP集成用于权限注解的解析和验证。

```xml
<!--提供注解的AOP 代理 -->
<aop:config proxy-target-class="true"></aop:config>  
<bean class="  
org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor">  
    <property name="securityManager" ref="securityManager"/>  
</bean>   
```

```java
@RequiresRoles("admin")  
@RequestMapping("/hello2")  
public String hello2() {  
    return "success";  
}   
```











