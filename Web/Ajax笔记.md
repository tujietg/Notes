## Ajax

AJAX = Asynchronous JavaScript and XML（异步的 JavaScript 和 XML）。

AJAX 是与服务器交换数据并更新部分网页的艺术，在不重新加载整个页面的情况下。

#### 简介

---

无需重新加载界面，通过异步请求可以局部更新界面。

- 什么是AJAX
  - AJAX 是一种用于创建快速动态网页的技术。
  - 通过在后台与服务器进行少量数据交换，AJAX 可以使网页实现异步更新。这意味着可以在不重新加载整个网页的情况下，对网页的某部分进行更新。
  - 传统的网页（不使用 AJAX）如果需要更新内容，必需重载整个网页面。

#### XMLHttpRequest对象

---

所有浏览器均支持次请求对象。

与服务器端交换数据，局部更新界面。

- 语法

  ```js
  <!--浏览器的支持 -->
  var xmlhttp;
  if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
    xmlhttp=new XMLHttpRequest();
    }
  else
    {// code for IE6, IE5
    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
  ```



#### 向服务器发送请求

----

XMLHttpRequest对象用于与服务器端交换数据。

```js
<!--Demo -->
xmlhttp.open("GET","test1.txt",true);
xmlhttp.send();
<!--语法 -->
open(method,url,async)
send（String）
```

- 方法详解

  method : 请求方法，GET或者POST。

  URL：文件的位置。

  async：异步还是同步。true（同步）|| false（异步）

  send（String）：利用post方法把请求发送给服务器。

- post详解

  ```js
  xmlhttp.open("POST","demo_post.asp",true);
  xmlhttp.send();
  <!--发送表单数据 -->
  xmlhttp.open("POST","ajax_test.asp",true);
  xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
  xmlhttp.send("fname=Bill&lname=Gates");
  ```

  setRequestHeader

  - 向请求中增加HTTP请求头。

- url

  - 服务器的文件。

- 异步or同步

  - XMLHttpRequest 对象如果要用于 AJAX 的话，其 open() 方法的 async 参数必须设置为 true。




#### 服务器响应

----

XMLHttpRequest 对象的 responseText 或 responseXML 属性来获取服务器端的响应。

- responseText

  - 获取字符串的响应格式。

    ```js
    document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
    ```

- responseXML

  - 获取XML响应格式。

    ```js
    xmlDoc=xmlhttp.responseXML;
    txt="";
    x=xmlDoc.getElementsByTagName("ARTIST");
    for (i=0;i<x.length;i++)
      {
      txt=txt + x[i].childNodes[0].nodeValue + "<br />";
      }
    document.getElementById("myDiv").innerHTML=txt;
    ```


#### onreadystatechange 事件

----

当请求被发送到服务器时，我们需要执行一些基于响应的任务。

readyState（存储XMLHttpRequest的状态信息）发生改变的时候会触发onreadystatechange事件。

- XMLHttpRequest属性

  - onreadystatechange
    - 存储函数（或函数名），每当 readyState 属性改变时，就会调用该函数。
  - readyState
    - 存有 XMLHttpRequest 的状态。从 0 到 4 发生变化。
      - 0: 请求未初始化
      - 1: 服务器连接已建立
      - 2: 请求已接收
      - 3: 请求处理中
      - 4: 请求已完成，且响应已就绪
  - status
    - 200 “OK”
    - 404 页面未找到

  ```js
  <!-- onreadystatechange被触发了五次0-4 -->
  xmlhttp.onreadystatechange=function()
    {
    if (xmlhttp.readyState==4 && xmlhttp.status==200)
      {
      document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
      }
    }
  ```



#### 请求实例

---

强大的动态应用程序

Demo:

```js
<html>
<head>
<script type="text/javascript">
function showHint(str)
{
var xmlhttp;
if (str.length==0)
  { 
  document.getElementById("txtHint").innerHTML="";
  return;
  }
if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xmlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
    document.getElementById("txtHint").innerHTML=xmlhttp.responseText;
    }
  }
xmlhttp.open("GET","/ajax/gethint.asp?q="+str,true);
xmlhttp.send();
}
</script>
</head>
<body>

<h3>请在下面的输入框中键入字母（A - Z）：</h3>
<form action=""> 
姓氏：<input type="text" id="txt1" onkeyup="showHint(this.value)" />
</form>
<p>建议：<span id="txtHint"></span></p> 

</body>
</html>
```

- 源码详解：

  如果输入框为空 (str.length==0)，则该函数清空 txtHint 占位符的内容，并退出函数。

  如果输入框不为空，showHint() 函数执行以下任务：

  - 创建 XMLHttpRequest 对象
  - 当服务器响应就绪时执行函数
  - 把请求发送到服务器上的文件
  - 请注意我们向 URL 添加了一个参数 q （带有输入框的内容）



#### AJAX数据库实例

---

AJAX与数据库进行动态通信。

Demo：

```js
<html>
<head>
<script type="text/javascript">
function showCustomer(str)
{
var xmlhttp;    
if (str=="")
  {
  document.getElementById("txtHint").innerHTML="";
  return;
  }
if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xmlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
    document.getElementById("txtHint").innerHTML=xmlhttp.responseText;
    }
  }
xmlhttp.open("GET","/ajax/getcustomer.asp?q="+str,true);
xmlhttp.send();
}
</script>
</head>
<body>

<form action="" style="margin-top:15px;"> 
<label>请选择一位客户：
<select name="customers" onchange="showCustomer(this.value)" style="font-family:Verdana, Arial, Helvetica, sans-serif;">
<option value="APPLE">Apple Computer, Inc.</option>
<option value="BAIDU ">BAIDU, Inc</option>
<option value="Canon">Canon USA, Inc.</option>
<option value="Google">Google, Inc.</option>
<option value="Nokia">Nokia Corporation</option>
<option value="SONY">Sony Corporation of America</option>
</select>
</label>
</form>
<br />
<div id="txtHint">客户信息将在此处列出 ...</div>

</body>
</html>
```



#### XML实例

----

 AJAX 可以用来与XML文件进行交互式通信。

```js
<html>
<head>
<script type="text/javascript">
function loadXMLDoc(url)
{
var xmlhttp;
var txt,x,xx,i;
if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xmlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
    txt="<table border='1'><tr><th>Title</th><th>Artist</th></tr>";
    x=xmlhttp.responseXML.documentElement.getElementsByTagName("CD");
    for (i=0;i<x.length;i++)
      {
      txt=txt + "<tr>";
      xx=x[i].getElementsByTagName("TITLE");
        {
        try
          {
          txt=txt + "<td>" + xx[0].firstChild.nodeValue + "</td>";
          }
        catch (er)
          {
          txt=txt + "<td> </td>";
          }
        }
      xx=x[i].getElementsByTagName("ARTIST");
        {
        try
          {
          txt=txt + "<td>" + xx[0].firstChild.nodeValue + "</td>";
          }
        catch (er)
          {
          txt=txt + "<td> </td>";
          }
        }
      txt=txt + "</tr>";
      }
    txt=txt + "</table>";
    document.getElementById('txtCDInfo').innerHTML=txt;
    }
  }
xmlhttp.open("GET",url,true);
xmlhttp.send();
}
</script>
</head>
<body>

<div id="txtCDInfo">
<button onclick="loadXMLDoc('/example/xmle/cd_catalog.xml')">获得 CD 信息</button>
</div>

</body>
</html>

```











