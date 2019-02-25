### Shiro学习笔记（05）💻

------

⌚️ Created by tujietg on 2019/02/25.

🐘 *Realm*

📚 [教程地址](https://jinnianshilongnian.iteye.com/blog/2018398)

##### 1，定义实体以及关系

![](http://dl2.iteye.com/upload/attachment/0094/1329/dd9f6a00-f6bc-3563-8afd-0c11048060b8.png)

用户-角色之间是多对多关系，角色-权限之间是多对多关系，用户和权限之间通过角色建立关系。

项目中，我们需要先实现三个表，实现其中的关联关系。之后实现，Dao层和Service层。

##### 2，定义Realm

实现UserRealm：

```java
public class UserRealm extends AuthorizingRealm {  
    private UserService userService = new UserServiceImpl();  
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {  
        String username = (String)principals.getPrimaryPrincipal();  
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();  
        authorizationInfo.setRoles(userService.findRoles(username));  
        authorizationInfo.setStringPermissions(userService.findPermissions(username));  
        return authorizationInfo;  
    }  
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {  
        String username = (String)token.getPrincipal();  
        User user = userService.findByUsername(username);  
        if(user == null) {  
            throw new UnknownAccountException();//没找到帐号  
        }  
        if(Boolean.TRUE.equals(user.getLocked())) {  
            throw new LockedAccountException(); //帐号锁定  
        }  
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以在此判断或自定义实现  
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(  
                user.getUsername(), //用户名  
                user.getPassword(), //密码  
                ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt  
                getName()  //realm name  
        );  
        return authenticationInfo;  
    }  
}   
```

详解：

- AuthorizingRealm

  AuthorizingRealm将获取Subject相关信息分成两步：

  - 获取身份验证信息（doGetAuthenticationInfo）

  - 授权信息（doGetAuthorizationInfo）

- 获取身份验证信息（doGetAuthenticationInfo）
  - 首先根据传入的用户名获取User信息；
    - 如果user为空，那么抛出没找到帐号异常UnknownAccountException；
    - 如果user找到但锁定了抛出锁定异常LockedAccountException；
    - 最后生成AuthenticationInfo信息，交给间接父类AuthenticatingRealm使用CredentialsMatcher进行判断密码是否匹配。
    - 不匹配将抛出密码错误异常IncorrectCredentialsException；
    - 密码重试此处太多将抛出超出重试次数异常ExcessiveAttemptsException
    - 组装SimpleAuthenticationInfo信息时，需要传入：身份信息（用户名）、凭据（密文密码）、盐（username+salt）
    - CredentialsMatcher使用盐加密传入的明文密码和此处的密文密码进行匹配。
- doGetAuthorizationInfo获取授权信息
  - PrincipalCollection是一个身份集合，因为我们现在就一个Realm，所以直接调用getPrimaryPrincipal得到之前传入的用户名即可；然后根据用户名调用UserService接口获取角色及权限信息。

##### 3，AuthenticationToken

![](http://dl2.iteye.com/upload/attachment/0094/1333/6c026012-2583-3a26-af70-bb1b0eae491b.png)

AuthenticationToken用于收集用户提交的身份（如用户名）及凭据（如密码）。

```java
public interface AuthenticationToken extends Serializable {  
    Object getPrincipal(); //身份  
    Object getCredentials(); //凭据  
}   
```

UsernamePasswordToken 可以拿来直接使用。

##### 4，AuthenticationInfo

作用：

- 如果Realm是AuthenticatingRealm子类，则提供给AuthenticatingRealm内部使用的CredentialsMatcher进行凭据验证。
- 提供给SecurityManager来创建Subject（提供身份信息）

##### 5，PrincipalCollection

在配置多个Realm时候，PrincipalCollection用于聚合这些身份信息。

##### 6，AuthorizationInfo

AuthorizationInfo用于聚合授权信息。

```java
public interface AuthorizationInfo extends Serializable {  
    Collection<String> getRoles(); //获取角色字符串信息  
    Collection<String> getStringPermissions(); //获取权限字符串信息  
    Collection<Permission> getObjectPermissions(); //获取Permission对象信息  
}   
```

我们使用AuthorizingRealm时，如果身份验证成功，在进行授权时就通过doGetAuthorizationInfo方法获取角色/权限信息用于授权验证。

Shiro提供了一个实现SimpleAuthorizationInfo，大多数时候使用这个。

##### 7，Subject

Subject是Shiro的核心对象，基本所有身份验证、授权都是通过Subject完成。

- 身份信息获取

  ```java
  Object getPrincipal(); //Primary Principal  
  PrincipalCollection getPrincipals(); // PrincipalCollection  
  ```

- **身份验证**

  ```java
  void login(AuthenticationToken token) throws AuthenticationException;  
  boolean isAuthenticated();  
  boolean isRemembered();  
  ```

- **角色授权验证** 

  ````java
  boolean hasRole(String roleIdentifier);  
  boolean[] hasRoles(List<String> roleIdentifiers);  
  boolean hasAllRoles(Collection<String> roleIdentifiers);  
  void checkRole(String roleIdentifier) throws AuthorizationException;  
  void checkRoles(Collection<String> roleIdentifiers) throws AuthorizationException;  
  void checkRoles(String... roleIdentifiers) throws AuthorizationException;   
  ````

  hasRole*进行角色验证，验证后返回true/false；而checkRole*验证失败时抛出AuthorizationException异常。 

- **权限授权验证**

  ```java
  boolean isPermitted(String permission);  
  boolean isPermitted(Permission permission);  
  boolean[] isPermitted(String... permissions);  
  boolean[] isPermitted(List<Permission> permissions);  
  boolean isPermittedAll(String... permissions);  
  boolean isPermittedAll(Collection<Permission> permissions);  
  void checkPermission(String permission) throws AuthorizationException;  
  void checkPermission(Permission permission) throws AuthorizationException;  
  void checkPermissions(String... permissions) throws AuthorizationException;  
  void checkPermissions(Collection<Permission> permissions) throws AuthorizationException;  
  ```

- **退出** 

  ```java
  void logout();  
  ```












