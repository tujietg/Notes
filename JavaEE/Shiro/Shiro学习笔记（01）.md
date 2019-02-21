### Shiro学习笔记（01）💻

---

⌚️  Created by tujietg on 2019/02/11.

📚  [教程地址](https://jinnianshilongnian.iteye.com/blog/2018398)

##### 1，简介

Apache Shiro是Java的一个安全框架。

Shiro不会去维护用户、维护权限；这些需要我们自己去设计提供；然后通过相应的接口注入给Shiro即可。

- 功能
  - 认证、授权、加密、会话管理、与Web集成、缓存等。
- 整体模块
  - **Authentication**
    - 身份认证/登录，验证用户是不是拥有相应的身份；
  - **Authorization**
    - 授权，即权限验证，验证某个已认证的用户是否拥有某个权限。
  - **Session Manager**
    - 会话管理，即用户登录后就是一次会话（JavaSE，web）
  - **Cryptography**
    - 加密。
  - **Web Support**
    - Web支持，可以非常容易的集成到Web环境；
  - **Caching**
    - 缓存，比如用户登录后，其用户信息、拥有的角色/权限不必每次去查，这样可以提高效率
  - **Concurrency**
    - shiro支持多线程应用的并发验证。
  - **Remember Me**
    - 记住我，这个是非常常见的功能，即一次登录后，下次再来的话不用登录了。

##### 2，外部架构

应用代码直接交互的对象是Subject，也就是说Shiro的对外API核心就是Subject。

Shiro不提供维护用户权限，而是通过Realm让开发人员自己注入。

- **Subject**
  - 主体，代表了当前“用户”，一个抽象概念。
- **SecurityManager**
  - Shiro的核心
  - 安全管理器；所有与安全有关的操作都会与SecurityManager交互；
  - 管理着所有Subject
- **Realm**
  - Shiro从从Realm获取安全数据（如用户、角色、权限）
  - SecurityManager从Realm中获取相应的用户来验证用户是否合法。

##### 3，内部架构

![](http://dl2.iteye.com/upload/attachment/0093/9792/9b959a65-799d-396e-b5f5-b4fcfe88f53c.png)



- **Subject**

  - 主体。主体可以是任何可以与应用交互的“用户”。

- **SecurityManager**

  - 所有具体的交互都通过SecurityManager进行控制。
  - 理着所有Subject、且负责进行认证和授权、及会话、缓存的管理。

- **Authenticator**

  - 认证器，负责主体认证的
  - 可以自定义实现

- **Authrizer**

  - 授权器，或者访问控制器，用来决定主体是否有权限进行相应的操作

- **Realm**

  - 可以有1个或多个Realm，可以认为是安全实体数据源，即用于获取安全实体的。
  - 由用户提供，需要自己实现自己的Realm。

- **SessionManager**

  - 管理生命周期的组件。

- **SessionDAO**

  - 管理数据库CRUD的会话。

- **CacheManager**

  - 缓存控制器，来管理如用户、角色、权限等的缓存的。

- **Cryptography**

  - 密码模块，Shiro提高了一些常见的加密组件用于如密码加密/解密的。












