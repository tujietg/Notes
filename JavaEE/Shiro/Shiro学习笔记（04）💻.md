### Shiro学习笔记（04）💻

---

⌚️ Created by tujietg on 2019/02/11.

🐘 *编码/加密*

📚 [教程地址](https://jinnianshilongnian.iteye.com/blog/2018398)

##### 1，编码/解码

Shiro提供了base64和16进制字符串编码/解码的API支持，方便一些编码解码操作

##### 2，散列算法

散列算法一般用于生成数据的摘要信息，是一种不可逆的算法，一般适合存储密码之类的数据，常见的散列算法如MD5、SHA等。一般进行散列时最好提供一个salt（盐），比如加密密码“admin”，产生的散列值是“21232f297a57a5a743894a0e4a801fc3”，可以到一些md5解密网站很容易的通过散列值得到密码“admin”，即如果直接对密码进行散列相对来说破解更容易，此时我们可以加一些只有系统知道的干扰数据，如用户名和ID（即盐）；这样散列的对象是“密码+用户名+ID”，这样生成的散列值相对来说更难破解。

- Shiro对散列的支持

  ```java
  String str = "hello";  
  String salt = "123";  
  //内部使用MessageDigest  
  String simpleHash = new SimpleHash("SHA-1", str, salt).toString();   
  ```

  - Shiro提供的HashService

    ```java
    DefaultHashService hashService = new DefaultHashService(); //默认算法SHA-512  
    hashService.setHashAlgorithmName("SHA-512");  
    hashService.setPrivateSalt(new SimpleByteSource("123")); //私盐，默认无  
    hashService.setGeneratePublicSalt(true);//是否生成公盐，默认false  
    hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());//用于生成公盐。默认就这个  
    hashService.setHashIterations(1); //生成Hash值的迭代次数  
      
    HashRequest request = new HashRequest.Builder()  
                .setAlgorithmName("MD5").setSource(ByteSource.Util.bytes("hello"))  
                .setSalt(ByteSource.Util.bytes("123")).setIterations(2).build();  
    String hex = hashService.computeHash(request).toHex();   
    ```

##### 3,加密解密

Shiro还提供对称式加密/解密算法的支持

##### 4，PasswordService/CredentialsMatcher

Shiro提供了PasswordService及CredentialsMatcher用于提供加密密码及验证密码服务。

DefaultPasswordService配合PasswordMatcher实现简单的密码加密与验证服务

````java
public class MyRealm extends AuthorizingRealm {  
    private PasswordService passwordService;  
    public void setPasswordService(PasswordService passwordService) {  
        this.passwordService = passwordService;  
    }  
     //省略doGetAuthorizationInfo，具体看代码   
    @Override  
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {  
        return new SimpleAuthenticationInfo(  
                "wu",  
                passwordService.encryptPassword("123"),  
                getName());  
    }  
}   
````

##### 5，**HashedCredentialsMatcher实现密码验证服务**

Shiro提供了CredentialsMatcher的散列实现HashedCredentialsMatcher，和之前的PasswordMatcher不同的是，它只用于密码验证，且可以提供自己的盐，而不是随机生成盐，且生成密码散列值的算法需要自己写，因为能提供自己的盐。























