## HTML  DOM

**W3C文档对象模型，定义了访问和操作 HTML 文档的标准！**

- HTML 的标准对象模型
- HTML 的标准编程接口
- W3C 标准

### 节点

**所有事物都是节点。DOM 是被视为节点树的 HTML。**

- DOM节点Tree实例



  ![](http://www.w3school.com.cn/i/ct_htmltree.gif)

### 方法

- **节点（HTML 元素）上执行的动作。**

- 方法

  - 利用ID获取元素

    ```js
    var element=document.getElementById("intro");
    ```

### DOM属性

- 属性是节点（HTML 元素）的值，您能够获取或设置。

- innerHTML

  - 通过id获取内容，一般用于更换元素的内容。

- nodeName

  - 属性规定节点的名称。（仅可读）

- nodeValue

  - nodeValue 属性规定节点的值。

  ```js
  <html>
  <body>
  
  <p id="intro">Hello World!</p>
  
  <script type="text/javascript">
  x=document.getElementById("intro");
  document.write(x.firstChild.nodeValue);
  </script>
  
  </body>
  </html>
  ```

- nodeType

  - nodeType 属性返回节点的类型。nodeType 是只读的。

    | 元素类型 | NodeType |
    | -------- | -------- |
    | 元素     | 1        |
    | 属性     | 2        |
    | 文本     | 3        |
    | 注释     | 8        |
    | 文档     | 9        |

### 访问

**查找 HTML 元素。**

- 访问 HTML 元素等同于访问节点

- 访问方法

  - getElementById() 

    - getElementById() 方法返回带有指定 ID 的元素

      ```js
      node.getElementById("id");
      ```

  -  getElementsByTagName() 

    - getElementsByTagName() 返回带有指定标签名的所有元

    - ```js
      <!--拿到所有的P标签-->
      document.getElementsByTagName("p");
      <!--拿到main下的p标签-->
      document.getElementById("main").getElementsByTagName("p");
      ```

  -  getElementsByClassName()

    - 带有相同类名的所有 HTML 元素

      ````js
      document.getElementsByClassName("intro");
      ````

      **注释**：getElementsByClassName() 在 Internet Explorer 5,6,7,8 中无效。

### 修改

**改变元素、属性、样式和事件。**

- 改变HTML内容

  - innerHTML

    ```js
    <html>
    <body>
    
    <p id="p1">Hello World!</p>
    
    <script>
    document.getElementById("p1").innerHTML="New text!";
    </script>
    
    </body>
    </html>
    ```

- 改变样式

  - 通过HTML DOM访问HTML元素的样式对象

    ```js
    <html>
    
    <body>
    <p id="p2">Hello world!</p>
    
    <script>
    document.getElementById("p2").style.color="blue";
    </script>
    
    </body>
    </html>
    ```

- <span id= "HTMLEl">创建HTML元素</span>

  - 如需向 HTML DOM 添加新元素，您首先必须创建该元素（元素节点），然后把它追加到已有的元素上。

    ```js
    <div id="d1">
    <p id="p1">This is a paragraph.</p>
    <p id="p2">This is another paragraph.</p>
    </div>
    
    <script>
    var para=document.createElement("p");
    var node=document.createTextNode("This is new.");
    para.appendChild(node);
    
    var element=document.getElementById("d1");
    element.appendChild(para);
    </script>
    ```

### 元素

**添加、删除和替换 HTML 元素。**

- appendChild() （参考创建  [创建HTML元素](#HTMLEl)）

  - 将新元素作为父元素的最后一个子元素进行添加。

- insertBefore() 

  - 将新元素作为父元素的第一个子元素进行添加。

- removeChild()

  - 删除元素

    ```js
    <div id="div1">
    <p id="p1">This is a paragraph.</p>
    <p id="p2">This is another paragraph.</p>
    </div>
    <script>
    var parent=document.getElementById("div1");
    var child=document.getElementById("p1");
    parent.removeChild(child);
    </script>
    
    <!-- 必须找到父元素然后删除子元素 -->
    var child=document.getElementById("p1");
    child.parentNode.removeChild(child);
    ```

- replaceChild()

  **替换 HTML DOM 中的元素**

  ```js
  <div id="div1">
  <p id="p1">This is a paragraph.</p>
  <p id="p2">This is another paragraph.</p>
  </div>
  
  <script>
  <!--创建节点 -->
  var para=document.createElement("p");
  var node=document.createTextNode("This is new.");
  para.appendChild(node);
  
  var parent=document.getElementById("div1");
  var child=document.getElementById("p1");
  parent.replaceChild(para,child);
  </script>
  ```

### 事件

**HTML DOM 允许 JavaScript 对 HTML 事件作出反应。**

- 当事件发生的时候，可以执行JS代码

  ```js
  onclick=JavaScript
  <!--例子 -->
  <!DOCTYPE html>
  <html>
  <body>
  <h1 onclick="this.innerHTML='hello!'">请点击这段文本!</h1>
  </body>
  </html>
  <!--分开写function-->
  <!DOCTYPE html>
  <html>
  <head>
  <script>
  function changetext(id)
  {
  id.innerHTML="hello!";
  }
  </script>
  </head>
  <body>
  <h1 onclick="changetext(this)">请点击这段文本!</h1>
  </body>
  </html>
  ```

- 事件属性(按钮点击给出事件)

  ```js
  <!DOCTYPE html>
  <html>
  <body>
  
  <p>点击按钮来执行 <b>displayDate()</b> 函数。</p>
  
  <button onclick="displayDate()">试一试</button>
  
  <script>
  function displayDate()
  {
  document.getElementById("demo").innerHTML=Date();
  }
  </script>
  
  <p id="demo"></p>
  
  </body>
  </html>
  ```

- 分配事件

  **HTML DOM 允许您使用 JavaScript 向 HTML 元素分配事件**

  ```js
  
  <!DOCTYPE html>
  <html>
  <head>
  </head>
  <body>
  
  <p>点击按钮来执行 <b>displayDate()</b> 函数。</p>
  
  <button id="myBtn">试一试</button>
  
  <script>
  document.getElementById("myBtn").onclick=function(){displayDate()};
  function displayDate()
  {
  document.getElementById("demo").innerHTML=Date();
  }
  </script>
  
  <p id="demo"></p>
  
  </body>
  </html>
  ```

- onload 和 onunload 事件

  **onload 和 onunload 事件可用于处理 cookies。 **

  ```js
  <!DOCTYPE html>
  <html>
  <body onload="checkCookies()">
  <script>
  function checkCookies()
  {
  if (navigator.cookieEnabled==true)
  	{
  	alert("Cookies are enabled")
  	}
  else
  	{
  	alert("Cookies are not enabled")
  	}
  }
  </script>
  <p>弹出的提示框会告诉你浏览器是否已启用 cookie。</p>
  </body>
  </html>
  ```

- onchange

  **常用于输入字段的验证。**

  ````js
  <!DOCTYPE html>
  <html>
  <head>
  <script>
  function myFunction()
  {
  var x=document.getElementById("fname");
  x.value=x.value.toUpperCase();
  }
  </script>
  </head>
  <body>
  
  请输入你的英文名：<input type="text" id="fname" onchange="myFunction()">
  <p>当你离开输入框时，被触发的函数会把你输入的文本转换为大写字母。</p>
  
  </body>
  </html>
  
  ````


- onmouseover 和 onmouseout 事件

  ```js
  <!DOCTYPE html>
  <html>
  <body>
  
  <div 
  onmouseover="mOver(this)" 
  onmouseout="mOut(this)" 
  style="background-color:#D94A38;width:200px;height:50px;padding-top:25px;text-align:center;">
  Mouse Over Me
  </div>
  
  <script>
  function mOver(obj)
  {
  obj.innerHTML="谢谢你"
  }
  
  function mOut(obj)
  {
  obj.innerHTML="把鼠标指针移动到上面"
  }
  </script>
  
  </body>
  ```

### 导航

**HTML DOM，您能够使用节点关系在节点树中导航**

- getElementsByTagName() 方法返回*节点列表*.(节点数组)

  ```js
  <!DOCTYPE html>
  <html>
  <body>
  
  <p>Hello World!</p>
  <p>DOM 很有用！</p>
  
  <script>
  <!--获取第二个节点  -->
  x=document.getElementsByTagName("p");
  document.write("第二段的 innerHTML 是: " + x[1].innerHTML);
  <!--循环获取所有的P标签 -->
  for (i=0;i<x.length;i++)
    { 
    document.write(x[i].innerHTML);
    document.write("<br>");
    }
  </script>
  
  </body>
  </html>
  ```


### 实例练习

[练习](http://www.w3school.com.cn/example/hdom_examples.asp)

















