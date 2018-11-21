## JSON

*更新时间：2018.11.21  20:36*

JavaScript Object Nation ,JSON 使用 JavaScript 语法来描述数据对象.

存储和交换信息的语法

#### 实例

```
{
    "employees": [
        { "firstName":"Bill" , "lastName":"Gates" },
        { "firstName":"George" , "lastName":"Bush" },
        { "firstName":"Thomas" , "lastName":"Carter" }
    ]
}
```



#### 简介

利用JS实现JSON

```js
<html>
<body>
<h2>在 JavaScript 中创建 JSON 对象</h2>

<p>
Name: <span id="jname"></span><br />
Age: <span id="jage"></span><br />
Address: <span id="jstreet"></span><br />
Phone: <span id="jphone"></span><br />
</p>

<script type="text/javascript">
var JSONObject= {
"name":"Bill Gates",
"street":"Fifth Avenue New York 666",
"age":56,
"phone":"555 1234567"};
document.getElementById("jname").innerHTML=JSONObject.name
document.getElementById("jage").innerHTML=JSONObject.age
document.getElementById("jstreet").innerHTML=JSONObject.street
document.getElementById("jphone").innerHTML=JSONObject.phone
</script>

</body>
</html>
```

- 类似XML
  - JSON 是纯文本
  - JSON 具有“自我描述性”（人类可读）
  - JSON 具有层级结构（值中存在值）
  - JSON 可通过 JavaScript 进行解析
  - JSON 数据可使用 AJAX 进行传输

- 与XML不同
  - 没有结束标签
  - 更短
  - 读写的速度更快
  - 能够使用内建的 JavaScript eval() 方法进行解析
  - 使用数组
  - 不使用保留字

AJAX 应用程序来说，JSON 比 XML 更快更易使用，使用函数eval()函数来处理字符串。



#### JSON 语法

---

JSON语法是JS语法的子集

- 规则：
  - 数据在名称/值对中
    - "firstName" : "John"
    - 值的类型：
      - 数字（整数或浮点数）
      - 字符串（在双引号中）
      - 逻辑值（true 或 false）
      - 数组（在方括号中）
      - 对象（在花括号中）
      - null
  - 数据由逗号分隔
  - 花括号保存对象
  - 方括号保存数组

- 对象

  花括号中书写，对象可以包含多个名称/值对

  ```js
  { "firstName":"John" , "lastName":"Doe" }
  ```

- 数组

  数组在方括号中书写，可以包含多个对象。

  ```js
  {
      "employees": [
          { "firstName":"John" , "lastName":"Doe" },
          { "firstName":"Anna" , "lastName":"Smith" },
          { "firstName":"Peter" , "lastName":"Jones" }
      ]
  }
  ```

- JSON使用JS语法

  使用JS来进行解析JSON的时候直接解析。

  ```js
  var employees = [
      { "firstName":"Bill" , "lastName":"Gates" },
      { "firstName":"George" , "lastName":"Bush" },
      { "firstName":"Thomas" , "lastName": "Carter" }
  ];
  <!--访问第一项 -->
  employees[0].lastName;
  <!--修改数据 -->
  employees[0].lastName = "Jobs";
  ```



#### JSON使用

----

JSON转化为JavaScript对象，从web服务器读取JSON数据，然后转化为JS对象，之后在网页中使用。

JavaScript 函数 eval() 可用于将 JSON 文本转换为 JavaScript 对象

- eval

```js
var obj = eval ("(" + txt + ")");
```

- 网页中的使用

  ```js
  <p>
  First Name: <span id="fname"></span><br />
  Last Name: <span id="lname"></span><br />
  </p>
  
  <script type="text/javascript">
  document.getElementById("fname").innerHTML = obj.employees[1].firstName
  document.getElementById("lname").innerHTML = obj.employees[1].lastName
  </script>
  ```



















