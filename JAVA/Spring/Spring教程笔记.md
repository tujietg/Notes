## Spring笔记

更新时间：2018.11.01  16:05:33

<font color=#00aacc >由浅到深，由深到广。</font>

<font color="red">结合开源项目，先熟练使用，再深入了解！</font>

<font color="#00d">理论很重要，实践更重要。学完之后，做一个小的开源项目。</font>

##### 醍醐灌顶

在 OOP 中模块化的关键单元是类，而在 AOP 中模块化的关键单元是方面。AOP 帮助你将横切关注点从它们所影响的对象中分离出来，然而依赖注入帮助你将你的应用程序对象从彼此中分离出来。

Spring框架的IOC实现了代码的松耦合。

##### 待解决问题

- 上下文？
- IOC与依赖注入的关系。
- 自动装配

-----

#### 概述

Spring 是最受欢迎的企业级 Java 应用程序开发框架。

- 框架优点
  - Spring 可以使开发人员使用 POJOs 开发企业级的应用程序。（POJO为Java类包含属性和getset方法）
  - 测试一个用 Spring 编写的应用程序很容易。
- 依赖注入
  - 依赖注入有助于将类粘合在一起，并且在同一时间让它们保持独立。
  - 可以通过构造方法传递参数，也可以通过使用setter方法注入。
- 面向方面编程
  - 一个程序中跨越多个点的功能被称为**横切关注点**，这些横切关注点在概念上独立于应用程序的业务逻辑。
    - 日志记录、声明性事务、安全性，和缓存等等。

------

#### 体系结构

Spring 有可能成为所有企业应用程序的一站式服务点，但是他是分模块的。

Spring可以分为大约20个模块。(如下图：)

<img src="http://wiki.jikexueyuan.com/project/spring/images/arch1.png"/>

##### 核心组件

- Core：封装了控制反转的IOC和依赖注入功能模块。
- Beans：提供BeanFactory，一个工厂模式的复杂实现。
- 上下文：<font color="#0ac">建立在由核心和 Bean 模块提供的坚实基础上，它是访问定义和配置的任何对象的媒介。</font>

- 表达式语言：查询和操作一个对象图的强大的表达式语言。

##### Web

- Web-MVC： Spring 的模型-视图-控制器（MVC），实现了 web 应用程序。

- Web：基本的面向 web 的集成功能。

------

#### 环境配置

略。

-------

#### Hello World实例

使用 Spring 框架开始实际的编程。

```java
package com.tutorialspoint;
public class HelloWorld {
   private String message;
   public void setMessage(String message){
      this.message  = message;
   }
   public void getMessage(){
      System.out.println("Your Message : " + message);
   }
}
```



```java
package com.tutorialspoint;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class MainApp {
   public static void main(String[] args) {
      ApplicationContext context = 
             new ClassPathXmlApplicationContext("Beans.xml");
      HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
      obj.getMessage();
   }
}
```

ClassPathXmlApplicationContext()来创建应用程序的上下文，加载beans的配置文件，处理创建并初始化所有的对象。

通过context()的方法获得ID为helloWorld的对象，有了对象就可以调用方法了。

```xml
<?xml version="1.0" encoding="UTF-8"?>

<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">

   <bean id="helloWorld" class="com.tutorialspoint.HelloWorld">
       <property name="message" value="Hello World!"/>
   </bean>
</beans>
```



-----

#### IOC容器

IOC容器是 Spring 框架的核心

容器将创建对象，把它们连接在一起，配置它们，并管理他们的整个生命周期从创建到销毁。

- BeanFactory

  - 这是一个最简单的容器，它主要的功能是为依赖注入 （DI） 提供支持。
  - XmlBeanFactory()API 负责创建并初始化所有的对象，即在配置文件中提到的 bean。

  ```java
        XmlBeanFactory factory = new XmlBeanFactory
                               (new ClassPathResource("Beans.xml"));
        HelloWorld obj = (HelloWorld) factory.getBean("helloWorld");
  ```

- ApplicationContext

  - FileSystemXmlApplicationContext
    - 该容器从 XML 文件中加载已被定义的 bean。需要提供给构造器 XML 文件的完整路径。
  - ClassPathXmlApplicationContext
    - 无需完整的文件路径，只需正确配置 CLASSPATH 环境变量。
  - WebXmlApplicationContext
    - 该容器会在一个 web 应用程序的范围内加载在 XML 文件中已被定义的 bean。

  eg：

  ```java
  ApplicationContext context = new FileSystemXmlApplicationContext
              ("C:/Users/ZARA/workspace/HelloSpring/src/Beans.xml");
        HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
  ```

-----

####Bean定义

被称作 bean 的对象是构成应用程序的支柱也是由 Spring IoC 容器管理的。

bean 是由用容器提供的配置元数据创建的，例如XML。

|           属性           | 描述                                                         |
| :----------------------: | ------------------------------------------------------------ |
|          class           | 这个属性是强制性的，并且指定用来创建 bean 的 bean 类。       |
|           name           | 这个属性指定唯一的 bean 标识符。在基于 XML 的配置元数据中，你可以使用 ID 和/或 name 属性来指定 bean 标识符。 |
|          scope           | 这个属性指定由特定的 bean 定义创建的对象的作用域，它将会在 bean 作用域的章节中进行讨论。 |
|     constructor-arg      |                                                              |
|        properties        |                                                              |
|     autowiring mode      |                                                              |
| lazy-initialization mode | 延迟初始化的 bean 告诉 IoC 容器在它第一次被请求时，而不是在启动时去创建一个 bean 实例。 |
|      initialization      |                                                              |
|       destruction        |                                                              |

- 配置元数据

  Spring IoC 容器完全由实际编写的配置元数据的格式解耦。

  - 基于 XML 的配置文件。
  - 基于注解的配置
  - 基于 Java 的配置

```java
  <!-- A simple bean definition -->
   <bean id="..." class="...">
       <!-- collaborators and configuration for this bean go here -->
   </bean>

   <!-- A bean definition with lazy init set on 延时初始化 -->
   <bean id="..." class="..." lazy-init="true">
       <!-- collaborators and configuration for this bean go here -->
   </bean>

   <!-- A bean definition with initialization method  -->
   <bean id="..." class="..." init-method="...">
       <!-- collaborators and configuration for this bean go here -->
   </bean>

   <!-- A bean definition with destruction method -->
   <bean id="..." class="..." destroy-method="...">
       <!-- collaborators and configuration for this bean go here -->
   </bean>
```



------

#### Bean的作用域

Spring定义一个Bean时，必须声明该 bean 的作用域的选项。（类似全局变量，局部变量）

| 作用域         | 描述                                                         |
| -------------- | ------------------------------------------------------------ |
| singleton      | 该作用域将 bean 的定义的限制在每一个 Spring IoC 容器中的一个单一实例(默认)。 |
| prototype      | 在每次需要时都产生一个新的 bean 实例。                       |
| request        | 该作用域将 bean 的定义限制为 HTTP 请求。只在 web-aware Spring ApplicationContext 的上下文中有效。 |
| session        | 该作用域将 bean 的定义限制为 HTTP 会话。 只在web-aware Spring ApplicationContext的上下文中有效。 |
| global-session | 该作用域将 bean 的定义限制为全局 HTTP 会话。只在 web-aware Spring ApplicationContext 的上下文中有效。 |

- singleton作用域

  对象仅仅创建一次，类似于单例模式，针对该 bean 的所有后续的请求和引用同一个对象。

  ```java
  ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        HelloWorld objA = (HelloWorld) context.getBean("helloWorld");
        objA.setMessage("I'm object A");
        objA.getMessage();
        HelloWorld objB = (HelloWorld) context.getBean("helloWorld");
        objB.getMessage();
  
  //print
  Your Message : I'm object A
  Your Message : I'm object A
  //xml
  <bean id="helloWorld" class="com.tutorialspoint.HelloWorld" 
        scope="singleton">
  </bean>
  ```


- prototype作用域

  每次特定的 bean 发出请求时 Spring IoC 容器就创建对象的新的 Bean 实例。

  ````java
  //与singleton一样的Demo，print
  Your Message : I'm object A
  Your Message : null
  ````


------

#### Bean的生命周期

当一个 bean 被实例化时，它可能需要执行一些初始化使它转换成可用状态。同样，当 bean 不再需要，并且从容器中移除时，可能需要做一些清除工作。

- init-method 

  初始化该方法，当实例化的时候立即调用该方法。

  org.springframework.beans.factory.InitializingBean 接口指定一个单一的方法

  ```java
  void afterPropertiesSet() throws Exception;
  //实现接口，重写afterPropertiesSet()方法，执行自己的逻辑
  public class ExampleBean implements InitializingBean {
     public void afterPropertiesSet() {
        // do some initialization work
     }
  }
  ```

  基于XML的情况下

  ````java
  //XML
  <bean id="exampleBean" 
           class="examples.ExampleBean" init-method="init"/>
  //类
  public class ExampleBean {
     public void init() {
        // do some initialization work
     }
  }
  ````

- destroy-method

  destroy-method 指定一个方法，只有从容器中移除 bean 之后，才能调用该方法。

  *org.springframework.beans.factory.DisposableBean* 接口指定一个单一的方法：

  ```java
  void destroy() throws Exception;
  //重写destroy方法
  public class ExampleBean implements DisposableBean {
     public void destroy() {
        // do some destruction work
     }
  }
  ```

  基于XML的情况下

  ```java
  <bean id = "destroyBean" calss="examples.ExampleBean" destroy-method="destroy" />
  
  public class ExampleBean {
     public void destroy() {
        // do some destruction work
     }
  }
  ```

注：XML 配置在命名方法上提供了极大的灵活性。建议使用XML方法。

- default-init-method 和 default-destroy-method

  具有太多的相同名称的初始化或者销毁方法的 Bean时候使用。

  ````xml
  <beans xmlns="http://www.springframework.org/schema/beans"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://www.springframework.org/schema/beans
      http://www.springframework.org/schema/beans/spring-beans-3.0.xsd"
      default-init-method="insit" 
      default-destroy-method="destroy">
  
     <bean id="..." class="...">
         <!-- collaborators and configuration for this bean go here -->
     </bean>
  
  </beans>
  ````

-----

#### 后置处理器

**BeanPostProcessor** 接口定义回调方法，你可以实现该方法来提供自己的实例化逻辑，依赖解析逻辑等。

**ApplicationContext** 会自动检测由 **BeanPostProcessor** 接口的实现定义的 bean，注册这些 bean 为后置处理器，然后通过在容器中创建 bean，在适当的时候调用它。

eg：(比较之前的例子)

```java
package com.tutorialspoint;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.BeansException;
//实现了接口，实现了方法，context会自动在合适的位置调用方法。
public class InitHelloWorld implements BeanPostProcessor {
   public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
      System.out.println("BeforeInitialization : " + beanName);
      return bean;  // you can return any other object as well
   }
   public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
      System.out.println("AfterInitialization : " + beanName);
      return bean;  // you can return any other object as well
   }
}

//XML配置多了一个bean，上下文会自动加载。
 <bean class="com.tutorialspoint.InitHelloWorld" />

```

----

#### 定义继承

子 bean 的定义继承父定义的配置数据。

Spring Bean 定义的继承与 Java 类的继承无关，但是继承的概念是一样的。

- parent

  parent属性选择继承的父类

  ```xml
  <bean id="helloIndia" class="com.tutorialspoint.HelloIndia" parent="helloWorld">
      <property name="message1" value="Hello India!"/>
      <property name="message3" value="Namaste India!"/>
  </bean>
  ```

  Demo 略。

- 定义模版

  abstract="true"

  定义一个 Bean 定义模板时，不应该指定**类**的属性，而应该指定带 **true** 值的**抽象**属性。

  ```xml
  <bean id="beanTeamplate" abstract="true">
        <property name="message1" value="Hello World!"/>
        <property name="message2" value="Hello Second World!"/>
        <property name="message3" value="Namaste India!"/>
  </bean>
  <bean id="helloIndia" class="com.tutorialspoint.HelloIndia" parent="beanTeamplate">
      <property name="message1" value="Hello India!"/>
      <property name="message3" value="Namaste India!"/>
  </bean>
  ```

  注：父 bean 自身不能被实例化，因为它是不完整的，而且它也被明确地标记为抽象的。当一个定义是抽象的，它仅仅作为一个纯粹的模板 bean 定义来使用的，充当子定义的父定义使用。（类似于Java的抽象类）

------

#### 依赖注入

依赖注入有助于把Java类粘合在一起，同时保持他们独立。

- 基于构造函数的依赖注入

  当容器调用带有一组参数的类构造函数时，基于构造函数的 DI 就完成了，其中每个参数代表一个对其他类的依赖。

  ```xml
  <bean id="textEditor" class="com.tutorialspoint.TextEditor">
        <constructor-arg ref="spellChecker"/>
  </bean>
  ```

  - 多个参数

    - 按照构造函数的顺序

      ```java
      package x.y;
      public class Foo {
         public Foo(Bar bar, Baz baz) {
            // ...
         }
      }
      	
      <beans>
         <bean id="foo" class="x.y.Foo">
            <constructor-arg ref="bar"/>
            <constructor-arg ref="baz"/>
         </bean>
      
         <bean id="bar" class="x.y.Bar"/>
         <bean id="baz" class="x.y.Baz"/>
      </beans>
      ```

    - 不同类型的参数

      ```java
      package x.y;
      public class Foo {
         public Foo(int year, String name) {
            // ...
         }
      }
      <beans>
      
         <bean id="exampleBean" class="examples.ExampleBean">
            <constructor-arg type="int" value="2001"/>
            <constructor-arg type="java.lang.String" value="Zara"/>
         </bean>
      
      </beans>
      ```

    - index属性

      ````xml
      <beans>
      
         <bean id="exampleBean" class="examples.ExampleBean">
            <constructor-arg index="0" value="2001"/>
            <constructor-arg index="1" value="Zara"/>
         </bean>
      
      </beans>
      ````

    注：如果你想要向一个对象传递一个引用，你需要使用 标签的 **ref** 属性，如果你想要直接传递值，那么你应该使用如上所示的 **value** 属性。

- 设置函数的依赖注入（get与set）

  当容器调用一个无参的构造函数或一个无参的静态 factory 方法来初始化你的 bean 后，通过容器在你的 bean 上调用设值函数，基于设值函数的 DI 就完成了

  ```xml
  <bean id="textEditor" class="com.tutorialspoint.TextEditor">
        <property name="spellChecker" ref="spellChecker"/>
  </bean>
  ```

- p-namespace设置函数

  ```xml
  <!--普通形式 -->
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://www.springframework.org/schema/beans
      http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">
  
     <bean id="john-classic" class="com.example.Person">
        <property name="name" value="John Doe"/>
        <property name="spouse" ref="jane"/>
     </bean>
  
     <bean name="jane" class="com.example.Person">
        <property name="name" value="John Doe"/>
     </bean>
  
  </beans>
  <!-- p-namespace 的形式-->
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xmlns:p="http://www.springframework.org/schema/p"
      xsi:schemaLocation="http://www.springframework.org/schema/beans
      http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">
  
     <bean id="john-classic" class="com.example.Person"
        p:name="John Doe"
        p:spouse-ref="jane"/>
     </bean>
  
     <bean name="jane" class="com.example.Person"
        p:name="John Doe"/>
     </bean>
  
  </beans>
  ```

  注：**-ref** 部分表明这不是一个直接的值，而是对另一个 bean 的引用。

----

#### 注入内部类

在bean范围内定义bean，类似于java的内部类。

```xml
<bean id="outerBean" class="...">
      <property name="target">
         <bean id="innerBean" class="..."/>
      </property>
</bean>
<!--例子参考设置值的Demo -->
<bean id="textEditor" class="com.tutorialspoint.TextEditor">
      <!--内部类的形式-->
      <property name="spellChecker">
          <!--基于 setter 注入 -->
         <bean id="spellChecker" class="com.tutorialspoint.SpellChecker"/>
       </property>
    
       <!--外部类形式 -->
       <property name="spellChecker" ref="spellChecker"/>
       <bean id="spellChecker" class="com.tutorialspoint.SpellChecker"/>
</bean>
```

----

#### 注入集合

Java Collection 类型 List、Set、Map 和 Properties的传入。

创建包含List、Set、Map、Properties的pojo对象，	

```xml
<!-- results in a setAddressList(java.util.List) call -->
      <property name="addressList">
         <list>
            <value>INDIA</value>
            <value>Pakistan</value>
            <value>USA</value>
            <value>USA</value>
         </list>
      </property>

      <!-- results in a setAddressSet(java.util.Set) call -->
      <property name="addressSet">
         <set>
            <value>INDIA</value>
            <value>Pakistan</value>
            <value>USA</value>
            <value>USA</value>
        </set>
      </property>

      <!-- results in a setAddressMap(java.util.Map) call -->
      <property name="addressMap">
         <map>
            <entry key="1" value="INDIA"/>
            <entry key="2" value="Pakistan"/>
            <entry key="3" value="USA"/>
            <entry key="4" value="USA"/>
         </map>
      </property>

      <!-- results in a setAddressProp(java.util.Properties) call -->
      <property name="addressProp">
         <props>
            <prop key="one">INDIA</prop>
            <prop key="two">Pakistan</prop>
            <prop key="three">USA</prop>
            <prop key="four">USA</prop>
         </props>
      </property>
```

- 注入bean引用

  需要定义 setter 方法。

```xml
<!-- Passing bean reference  for java.util.List -->
      <property name="addressList">
         <list>
            <ref bean="address1"/>
            <ref bean="address2"/>
            <value>Pakistan</value>
         </list>
      </property>

      <!-- Passing bean reference  for java.util.Set -->
      <property name="addressSet">
         <set>
            <ref bean="address1"/>
            <ref bean="address2"/>
            <value>Pakistan</value>
         </set>
      </property>

      <!-- Passing bean reference  for java.util.Map -->
      <property name="addressMap">
         <map>
            <entry key="one" value="INDIA"/>
            <entry key ="two" value-ref="address1"/>
            <entry key ="three" value-ref="address2"/>
         </map>
      </property>
```

- 注入null与空的字付串

  ```xml
  <!--空字符串作为值 -->
  <bean id="..." class="exampleBean">
     <property name="email" value=""/>
  </bean>
  ```

  上面代码相当于：exampleBean.setEmail("")。

  ```xml
  <bean id="..." class="exampleBean">
     <property name="email"><null/></property>
  </bean>
  ```

  上面代码相当于：exampleBean.setEmail(null)。

------

#### 自动装配

不使用constructor-arg和property来进行自动装配bean

autowire属性定义自动装配

| 模式                                                         | 描述                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| no                                                           | 这是默认的设置，它意味着没有自动装配，你应该使用显式的bean引用来连线。你不用为了连线做特殊的事。在依赖注入章节你已经看到这个了。 |
| [byName](http://wiki.jikexueyuan.com/project/spring/beans-auto-wiring/spring-autowiring-byname.html) | 由属性名自动装配。Spring 容器看到在 XML 配置文件中 bean 的*自动装配*的属性设置为 *byName*。然后尝试匹配，并且将它的属性与在配置文件中被定义为相同名称的 beans 的属性进行连接。 |
| [byType](http://wiki.jikexueyuan.com/project/spring/beans-auto-wiring/spring-autowiring-byType.html) | 由属性数据类型自动装配。Spring 容器看到在 XML 配置文件中 bean 的*自动装配*的属性设置为 *byType*。然后如果它的**类型**匹配配置文件中的一个确切的 bean 名称，它将尝试匹配和连接属性的类型。如果存在不止一个这样的 bean，则一个致命的异常将会被抛出。 |
| [constructor](http://wiki.jikexueyuan.com/project/spring/beans-auto-wiring/spring-autowiring-by-Constructor.html) | 类似于 byType，但该类型适用于构造函数参数类型。如果在容器中没有一个构造函数参数类型的 bean，则一个致命错误将会发生。 |
| autodetect                                                   | Spring首先尝试通过 *constructor* 使用自动装配来连接，如果它不执行，Spring 尝试通过 *byType* 来自动装配。 |

- byname

  尝试将bean的属性与配置文件中定义为相同名称的 beans 进行匹配和连接。

  ```xml
  <!--两种配置文件一样 -->
  <bean id="textEditor" class="com.tutorialspoint.TextEditor" 
        autowire="byName">
        <property name="name" value="Generic Text Editor" />
   </bean>
   <bean id="textEditor" class="com.tutorialspoint.TextEditor">
         <property name="spellChecker" ref="spellChecker" />
         <property name="name" value="Generic Text Editor" />
   </bean>
  
  ```

- bytype

  如果它的 **type** 恰好与配置文件中 beans 名称中的一个相匹配，它将尝试匹配和连接它的属性。

  例子：类似于byname。但是bytype的调用是bean的id是大写的，意思是类型。

- 构造函数自动装配

  autowire="constructor"

  它尝试把它的构造函数的参数与配置文件中 beans 名称中的一个进行匹配和连线。

  ```xml
  <bean id="textEditor" class="com.tutorialspoint.TextEditor" 
        autowire="constructor">
        <constructor-arg value="Generic Text Editor"/>
     </bean>
  
     <!-- Definition for spellChecker bean -->
     <bean id="SpellChecker" class="com.tutorialspoint.SpellChecker">
     </bean>
  ```

----

#### 基于注解的配置

可以使用相关类，方法或字段声明的注解，将 bean 配置移动到组件类本身。

在 XML 注入之前进行注解注入，因此后者的配置将通过两种方式的属性连线被前者重写。

使用注解配置xml

```xml
<context:annotation-config/>
<!-- bean definitions go here -->
```

- @Required注解

  应用于 bean 属性的 setter 方法。

  ```java
   @Required
     public void setAge(Integer age) {
        this.age = age;
     }
  <!--XML -->
    <bean id="student" class="com.tutorialspoint.Student">
        <property name="name"  value="Zara" />
        <property name="age"  value="11"/>
     </bean>
    
  ```


- @Autowired注解

  Autowired 注释可以在 setter 方法中被用于自动连接 bean

  ```java
  //setter方法中的
  @Autowired
     public void setSpellChecker( SpellChecker spellChecker ){
        this.spellChecker = spellChecker;
   }
  //属性中的Autowired
  @Autowired
  private SpellChecker spellChecker;
  //构造函数的
  @Autowired
  public TextEditor(SpellChecker spellChecker){
      System.out.println("Inside TextEditor constructor." );
      this.spellChecker = spellChecker;
  }
  ```

- Qualifier注释

  创建多个具有相同类型的 bean 时，并且想要用一个属性只为它们其中的一个进行装配。

  ```java
  //对同一个POJO的创建两个bean
  @Autowired
  @Qualifier("student1")
  private Student student;
  ```

  Xml:

  ```xml
   <bean id="student1" class="com.tutorialspoint.Student">
        <property name="name"  value="Zara" />
        <property name="age"  value="11"/>
     </bean>
  
     <!-- Definition for student2 bean -->
     <bean id="student2" class="com.tutorialspoint.Student">
        <property name="name"  value="Nuha" />
        <property name="age"  value="2"/>
     </bean>
  ```



- JSR-250注释

  @PostConstruct equeal  init-method, @PreDestroy equeal destroy-method.

  - @PostConstruct ： 注释作为初始化回调函数的一个替代.
  - @PreDestroy:   注释作为销毁回调函数的一个替代.

- @Resource 注释

  在字段setter中使用

  遵循 by-name 自动连接语义

  ```java
  private SpellChecker spellChecker;
  @Resource(name= "spellChecker")
  public void setSpellChecker( SpellChecker spellChecker ){
      this.spellChecker = spellChecker;
  }
  ```

------

#### 基于Java配置

- @Configuration

  带有 **@Configuration** 的注解类表示这个类可以使用 Spring IoC 容器作为 bean 定义的来源。

- @Bean

  告诉Spring我这个对象需要被注册为 Spring 应用程序上下文中的 bean。

  ```java
  //@Bean 注解的方法名称作为 bean 的 ID
  @Configuration
  public class HelloWorldConfig {
     @Bean 
     public HelloWorld helloWorld(){
        return new HelloWorld();
     }
  }
  <beans>
     <bean id="helloWorld" class="com.tutorialspoint.HelloWorld" />
  </beans>
  ```

  注：两者相同。



未完待续…….



#### 















