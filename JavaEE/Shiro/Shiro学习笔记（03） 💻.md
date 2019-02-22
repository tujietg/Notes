### Shiro学习笔记（03） 💻

-----

⌚️ Created by tujietg on 2019/02/12.

🐘 *授权*

📚 [教程地址](https://jinnianshilongnian.iteye.com/blog/2018398)

##### 1，介绍

授权，也叫访问控制，即在应用中控制谁能访问哪些资源（如访问页面/编辑数据/页面操作等）。在授权中需了解的几个关键对象：主体（Subject）、资源（Resource）、权限（Permission）、角色（Role）。

- **主体**

  主体，即访问应用的用户，在Shiro中使用Subject代表该用户。用户只有授权后才允许访问相应的资源。

- **资源**

  在应用中用户可以访问的任何东西，比如访问JSP页面、查看/编辑某些数据、访问某个业务方法、打印文本等等都是资源。用户只要授权后才能访问。

- **权限**

  安全策略中的原子授权单位，通过权限我们可以表示在应用中用户有没有操作某个资源的权力。

##### 2，角色分类

角色代表了操作集合，可以理解为权限的集合，一般情况下我们会赋予用户角色而不是权限，即这样用户可以拥有一组权限，赋予权限时比较方便。

- **隐式角色**
  - 即直接通过角色来验证用户有没有操作权限。
- **显示角色**
  - 在程序中通过权限控制谁能访问某个资源。

##### 3，授权方式

shiro支持三种授权方式。

- 编程式：通过写if/else授权代码块完成： 

  ````java
  Subject subject = SecurityUtils.getSubject();  
  if(subject.hasRole(“admin”)) {  
      //有权限  
  } else {  
      //无权限  
  }   
  ````

- 注解式

  ```java
  @RequiresRoles("admin")  
  public void hello() {  
      //有权限  
  }   
  ```

没有权限将抛出相应的异常；

- JSP标签

  ```jsp
  <shiro:hasRole name="admin">  
  <!— 有权限 —>  
  </shiro:hasRole>   
  ```

##### 4，授权

如果需要在应用中判断用户是否有相应角色，就需要在相应的Realm中返回角色信息。

- 隐式角色：

  给每个用户赋予角色。然后代码中做角色的判断工作。

  ```ini
  [users]  
  zhang=123,role1,role2  
  wang=123,role1   
  ```

  - hasRole/hasAllRoles
    - 用于判断用户是否拥有某个角色/某些权限
  - checkRole/checkRoles
    - 和hasRole/hasAllRoles不同的地方是它在判断为假的情况下会抛出UnauthorizedException异常。

- 显示角色

  在ini配置文件配置用户拥有的角色及角色-权限关系（shiro-permission.ini） 

  ```ini
  [users]  
  zhang=123,role1,role2  
  wang=123,role1  
  [roles]  
  role1=user:create,user:update  
  role2=user:create,user:delete   
  ```

  首先根据用户名找到角色，然后根据角色再找到权限；即角色是权限集合；Shiro同样不进行权限的维护，需要我们通过Realm返回相应的权限信息。只需要维护“用户——角色”之间的关系即可。

##### 5,Permission

字符串通配符权限

- 规则
  - “资源标识符：操作：对象实例ID”  即对哪个资源的哪个实例可以进行什么操作。其默认支持通配符权限字符串，“:”表示资源/操作/实例的分割；“,”表示操作的分割；“*”表示任意资源/操作/实例。

- 单个资源单个权限

  ```java
  subject().checkPermissions("system:user:update");  
  ```

- 单个资源多个权限

  ```java
  //ini配置文件
  role41=system:user:update,system:user:delete  
  //java判断是否拥有权限
  subject().checkPermissions("system:user:update,delete");  
  ```

- **单个资源全部权限**

  ````java
  role51="system:user:create,update,delete,view"  
  role52=system:user:*  
  ````

- **所有资源全部权限**

  ````
  //用户拥有所有资源的view权限。
  role61=*:view  
  ````

##### 6，性能问题

通配符匹配方式比字符串相等匹配来说是更复杂的，因此需要花费更长时间，但是一般系统的权限不会太多，且可以配合缓存来提供其性能。

#### 授权流程

![](http://dl2.iteye.com/upload/attachment/0094/0549/541e4da3-d1a5-3d13-83a6-b65c3596ee4e.png)

- 授权流程
  1、首先调用Subject.isPermitted*/hasRole*接口，其会委托给SecurityManager，而SecurityManager接着会委托给Authorizer；

  2、Authorizer是真正的授权者，如果我们调用如isPermitted(“user:view”)，其首先会通过PermissionResolver把字符串转换成相应的Permission实例；

  3、在进行授权之前，其会调用相应的Realm获取Subject相应的角色/权限用于匹配传入的角色/权限；

  4、Authorizer会判断Realm的角色/权限是否和传入的匹配，如果有多个Realm，会委托给ModularRealmAuthorizer进行循环判断，如果匹配如isPermitted*/hasRole*会返回true，否则返回false表示授权失败。

- Realm授权

  - 继承AuthorizingRealm

##### 7， Authorizer、PermissionResolver及RolePermissionResolver

Authorizer的职责是进行授权（访问控制），是Shiro API中授权核心的入口点。

PermissionResolver用于解析权限字符串到Permission实例，而RolePermissionResolver用于根据角色解析相应的权限集合。

##### 8，**自定义Realm**

```java

public class MyRealm extends AuthorizingRealm {  
    @Override  
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {  
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();  
        authorizationInfo.addRole("role1");  
        authorizationInfo.addRole("role2");  
        authorizationInfo.addObjectPermission(new BitPermission("+user1+10"));  
        authorizationInfo.addObjectPermission(new WildcardPermission("user1:*"));  
        authorizationInfo.addStringPermission("+user2+10");  
        authorizationInfo.addStringPermission("user2:*");  
        return authorizationInfo;  
    }  
    @Override  
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {  
        //和com.github.zhangkaitao.shiro.chapter2.realm.MyRealm1. getAuthenticationInfo代码一样，省略  
}  
}   
```

AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)：表示获取身份验证信息；

AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)：表示根据用户身份获取授权信息。