### Shiro学习笔记（02）💻

---

⌚️ Created by tujietg on 2019/02/11.

🐘 *身份验证*

📚 [教程地址](https://jinnianshilongnian.iteye.com/blog/2018398)

##### 1，身份验证

应用中谁能证明他就是他本人。一般提供如他们的身份ID一些标识信息来表明他就是他本人，如提供身份证，用户名/密码来证明。

- **principals**
  - 身份，一般是用户名/密码/手机号。

- **credentials**
  - 证明/凭证，即只有主体知道的安全值，如密码/数字证书等。

##### 2，登陆/退出

使用.ini文件来存储Users的信息。

- 代码步骤
  - 略。

##### 3, 身份认证流程

![](http://dl2.iteye.com/upload/attachment/0094/0173/8d639160-cd3e-3b9c-8dd6-c7f9221827a5.png)

- 认证流程：
  - 首先调用Subject.login(token)进行登录，其会自动委托给Security Manager，调用之前必须通过SecurityUtils. setSecurityManager()设置；
  - SecurityManager负责真正的身份验证逻辑；它会委托给Authenticator进行身份验证；
  - Authenticator才是真正的身份验证者，Shiro API中核心的身份认证入口点，此处可以自定义插入自己的实现；
  - Authenticator可能会委托给相应的AuthenticationStrategy进行多Realm身份验证，默认ModularRealmAuthenticator会调用AuthenticationStrategy进行多Realm身份验证；
  - Authenticator会把相应的token传入Realm，从Realm获取身份验证信息，如果没有返回/抛出异常表示身份验证失败了。此处可以配置多个Realm，将按照相应的顺序及策略进行访问。

##### 4, Realm

SecurityManager要验证用户身份，那么它需要从Realm获取相应的用户进行比较以确定用户身份是否合法。

- 自定义实现Realm

  - 实现org.apache.shiro.realm.Realm接口
  - ini配文文件中需要指向MyRealm1。
    - 声明一个realm 。
    - 指定securityManager的realms实现  

- Shiro默认提供的Realm

  ![](http://dl2.iteye.com/upload/attachment/0094/0175/34062d4e-8ac5-378a-a9e2-4845f0828292.png)

##### 5,Authenticator  与 

Authenticator的职责是验证用户帐号，是Shiro API中身份验证核心的入口点。

````java
public AuthenticationInfo authenticate(AuthenticationToken authenticationToken)  throws AuthenticationException;   
````

验证成功，将返回AuthenticationInfo验证信息；此信息中包含了身份及凭证；如果验证失败将抛出相应的AuthenticationException实现。

- Authenticator
  - ModularRealmAuthenticator 是Authenticator的实现
    - 委托给多个Realm进行验证
  - Realm的验证规则
    - AuthenticationStrategy来指定验证规则。
    - **FirstSuccessfulStrategy**
      - 只要有一个Realm验证成功即可，只返回第一个Realm身份验证成功的认证信息，其他的忽略；
    - **AtLeastOneSuccessfulStrategy**
      - 只要有一个Realm验证成功即可，和FirstSuccessfulStrategy不同，返回所有Realm身份验证成功的认证信息；
    - **AllSuccessfulStrategy**
      - 所有Realm验证成功才算成功，且返回所有Realm身份验证成功的认证信息，如果有一个失败就失败了。

- 自定义AuthenticationStrategy
  - 一般继承org.apache.shiro.authc.pam.AbstractAuthenticationStrategy