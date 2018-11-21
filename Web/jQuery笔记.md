## jQuery笔记

更新日期：2018.11.21  20:25

##### 是一个JS库

##### 简化了JS编程

遗留问题：如果存在名称冲突，则重命名 jQuery 库。

​	           设置HTML中的回掉函数。

### 简介

-----

- 增加jQuery库

  ```js
  <head>
  <script type="text/javascript" src="jquery.js"></script>
  </head>
  ```

- 记载jQuery

  - 下载jQuery库。

  - 使用CDN

    ```js
    <!--Google -->
    <head>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs
    /jquery/1.4.0/jquery.min.js"></script>
    </head>
    <!--Microsoft -->
    <head>
    <script type="text/javascript" src="http://ajax.microsoft.com/ajax/jquery
    /jquery-1.4.min.js"></script>
    </head>
    ```

### 语法

-----

##### 通过 jQuery，您可以选取（查询，query） HTML 元素，并对它们执行“操作”（actions）。

- 基础语法

  $(*selector*).action()

  - 美元符号定义 jQuery
  - 选择符（selector）“查询”和“查找” HTML 元素
  - jQuery 的 action() 执行对元素的操作

- 就绪函数

  为了防止文档加载完成之前运行jQuery

  ```js
  $(document).ready(function(){
  
  --- jQuery functions go here ----
  
  });
  
  ```


### 选择器

----

##### 选择器允许你对一组或单个元素进行操作

- 元素选择器
  - 选中p标签：$("p")
  - 选中所有类为intro下的p标签：$("p.intro")
  - 选中所有id为intro下的p标签：$("p#intro").

- CSS选择器

  ```js
  <!--把所有 p 元素的背景颜色更改为红色 -->
  $("p").css("background-color","red");
  ```

- 属性选择器

  - $("[href]") 选取所有带有 href 属性的元素。

    $("[href='#']") 选取所有带有 href 值等于 "#" 的元素。

    $("[href!='#']") 选取所有带有 href 值不等于 "#" 的元素。

    $("[href$='.jpg']")选取所有 href 值以 ".jpg" 结尾的元素。

- 实例

  | 语法                 | 描述                                                 |
  | -------------------- | ---------------------------------------------------- |
  | $(this)              | 当前 HTML 元素                                       |
  | $("p")               | 所有 <p> 元素                                        |
  | $("p.intro")         | 所有 class="intro" 的 <p> 元素                       |
  | $(".intro")          | 所有 class="intro" 的元素                            |
  | $("#intro")          | 所有 id="intro" 的元素                               |
  | $("ul li:first")     | 每个 <ul> 的第一个 <li> 元素                         |
  | $("[href$='.jpg']")  | 所有带有以 ".jpg" 结尾的属性值的 href 属性           |
  | $("div#intro .head") | id="intro" 的 <div> 元素中的所有 class="head" 的元素 |

### 事件

----

##### jQuery 是为事件处理特别设计的。

通常放在head中

```js
<html>
<head>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript">
$(document).ready(function(){
  $("button").click(function(){
    $("p").hide();
  });
});
</script>
</head>

<body>
<h2>This is a heading</h2>
<p>This is a paragraph.</p>
<p>This is another paragraph.</p>
<button>Click me</button>
</body>

</html>
```

- 规范（更易维护）
  - 把所有 jQuery 代码置于事件处理函数中
  - 把所有事件处理函数置于文档就绪事件处理器中
  - 把 jQuery 代码置于单独的 .js 文件中
  - 如果存在名称冲突，则重命名 jQuery 库

- 事件举例

  | Event 函数                      | 绑定函数至                                     |
  | ------------------------------- | ---------------------------------------------- |
  | $(document).ready(function)     | 将函数绑定到文档的就绪事件（当文档完成加载时） |
  | $(selector).click(function)     | 触发或将函数绑定到被选元素的点击事件           |
  | $(selector).dblclick(function)  | 触发或将函数绑定到被选元素的双击事件           |
  | $(selector).focus(function)     | 触发或将函数绑定到被选元素的获得焦点事件       |
  | $(selector).mouseover(function) | 触发或将函数绑定到被选元素的鼠标悬停事件       |

### jQqary效果

隐藏，显示，滑动，切换，淡入淡出，动画～

##### hide()/show()/toggle()

隐藏/显示/隐藏状态显示（显示状态隐藏）

```js
<!--语法 speed 是/隐藏和显示的速度 -->
$(selector).hide(speed,callback);
$(selector).show(speed,callback);
$(selector).toggle(speed,callback);

$("#hide").click(function(){
  $("p").hide();
});

$("#show").click(function(){
  $("p").show();
});

$("button").click(function(){
  $("p").toggle();
});
```



略。

----



### jQuaryHTML

#### 获取

拥有可以操作HTML和属性的方法，拥有操作DOM的元素。

- 获取内容

  text()、html()、以及val()

  - text() - 设置或返回所选元素的文本内容
  - html() - 设置或返回所选元素的内容（包括 HTML 标记）
  - val() - 设置或返回表单字段的值

- 获取属性

  - jQuery attr() 方法用于获取属性值。

    ```js
    <!--获取href的属性 -->
    $("button").click(function(){
      alert($("#w3s").attr("href"));
    });
    ```



#### 设置

即然可以获取，那就也可以设置了。

```js
<!--设置值 -->
$("#btn1").click(function(){
  $("#test1").text("Hello world!");
});
$("#btn2").click(function(){
  $("#test2").html("<b>Hello world!</b>");
});
$("#btn3").click(function(){
  $("#test3").val("Dolly Duck");
});
<!--attar 设置多个值-->
$("button").click(function(){
  $("#w3s").attr({
    "href" : "http://www.w3school.com.cn/jquery",
    "title" : "W3School jQuery Tutorial"
  });
});
```



#### 增加

增加新的元素

- 用法
  - append() - 在被选元素的结尾插入内容
  - prepend() - 在被选元素的开头插入内容
  - after() - 在被选元素之后插入内容
  - before() - 在被选元素之前插入内

  ```js
  $("p").prepend("Some prepended text.");
  ```


#### 删除

删除元素

- 方法
  - remove() - 删除被选元素（及其子元素）
  - empty() - 从被选元素中删除子元素

- 过滤

  根据自己指定条件删除元素

  ```
  <!--删除 class="italic" 的所有 <p> 元素 -->
  $("p").remove(".italic");
  ```



#### 操作CSS样式

- 对CSS进行样式的操作
  - addClass() - 向被选元素添加一个或多个类
  - removeClass() - 从被选元素删除一个或多个类
  - toggleClass() - 对被选元素进行添加/删除类的切换操作
  - css() - 设置或返回样式属性

```css
.important
{
font-weight:bold;
font-size:xx-large;
}
.blue
{
color:blue;
}

$("button").click(function(){
  $("h1,h2,p").addClass("blue");
  $("div").addClass("important");
});
```



### jQuary遍历

---

遍历方法中最大的种类是树遍历（tree-traversal）

#### 向上遍历

这些 jQuery 方法很有用，它们用于向上遍历 DOM 树：

（parent() parents()  parentsUntil()）

- parent()

  返回的元素的直属父元素。

  ```js
  $(document).ready(function(){
    $("span").parent();
  });
  ```

- parents()

  parents() 方法返回被选元素的所有祖先元素，它一路向上直到文档的根元素 (<html>)。

  ```js
  <!--返回所有的 -->
  $(document).ready(function(){
    $("span").parents();
  });
  <!--返回的所有ul父节点 -->
  $(document).ready(function(){
    $("span").parents("ul");
  });
  ```

- parentsUntil()

  parentsUntil() 方法返回介于两个给定元素之间的所有祖先元素。

  ```js
  <!--返回span和父div之间所有节点 -->
  $(document).ready(function(){
    $("span").parentsUntil("div");
  });
  ```



#### 向上遍历

通过 jQuery，向下遍历 DOM 树，以查找元素的后代（children()  find()）

- children()

  children() 方法返回被选元素的所有直接子元素。

  ```js
  $(document).ready(function(){
    $("div").children();
  });
  <!--筛选类名为 "1" 的所有 <p> 元素 并且为直接子元素-->
  $(document).ready(function(){
    $("div").children("p.1");
  });
  ```

- find()

  find() 方法返回被选元素的后代元素，一路向下直到最后一个后代。

  ```js
  <!--找到find下的所有的span/全部子元素 -->
  $(document).ready(function(){
    $("div").find("span");
    $("div").find("*");
  });
  ```

#### 同胞

同一个父元素的同等级元素

有许多有用的方法让我们在 DOM 树进行水平遍历：

- siblings()

  返回所有的同胞元素

  - ```js
    $(document).ready(function(){
      $("h2").siblings();
    });
    ```

- next()

  next() 方法返回被选元素的下一个同胞元素。

- nextAll()

  nextAll() 方法返回被选元素的所有跟随的同胞元素。

- nextUntil()

  nextUntil() 方法返回介于两个给定参数之间的所有跟随的同胞元素。

  ```js
  $(document).ready(function(){
    $("h2").nextUntil("h6");
  });
  ```

- prev()

- prevAll()

- prevUntil()

  prev(), prevAll() 以及 prevUntil() 方法的工作方式与上面的方法类似，只不过方向相反而已：它们返回的是前面的同胞元素（在 DOM 树中沿着同胞元素向后遍历，而不是向前）。

#### 过滤

---

未完待续…….



### jQuary与AJAX

-----

​	够使用 HTTP Get 和 HTTP Post 从远程服务器上请求文本、HTML、XML 或 JSON - 同时把这些外部数据直接载入网页的被选元素中。如果没有 jQuery，AJAX 编程还是有些难度的。jQuery简化了AJAX代码。

- jQuery load()

  load() 方法从服务器加载数据，并把返回的数据放入被选元素中。

  ```js
  $(selector).load(URL,data,callback);
  <!--URL下面的全部内容 -->
  $("#div1").load("demo_test.txt");
  <!--url下面 ID 为p1的内容-->
  $("#div1").load("demo_test.txt #p1");
  
  <!DOCTYPE html>
  <html>
  <head>
  <script src="/jquery/jquery-1.11.1.min.js">
  </script>
  <script>
  $(document).ready(function(){
    $("#btn1").click(function(){
      $('#test').load('/example/jquery/demo_test.txt');
    })
  })
  </script>
  </head>
  
  <body>
  
  <h3 id="test">请点击下面的按钮，通过 jQuery AJAX 改变这段文本。</h3>
  <button id="btn1" type="button">获得外部的内容</button>
  
  </body>
  </html>
  
  ```

-  callback参数

  回调参数可以设置不同的参数。

  - *responseTxt* - 包含调用成功时的结果内容
  - *statusTXT* - 包含调用的状态
  - *xhr* - 包含 XMLHttpRequest 对象

  ```js
  <!DOCTYPE html>
  <html>
  <head>
  <script src="/jquery/jquery-1.11.1.min.js"></script>
  <script>
  $(document).ready(function(){
    $("button").click(function(){
      $("#div1").load("/example/jquery/demo_test.txt",function(responseTxt,statusTxt,xhr){
        if(statusTxt=="success")
          alert("外部内容加载成功！");
        if(statusTxt=="error")
          alert("Error: "+xhr.status+": "+xhr.statusText);
      });
    });
  });
  </script>
  </head>
  <body>
  
  <div id="div1"><h2>使用 jQuery AJAX 来改变文本</h2></div>
  <button>获得外部内容</button>
  
  </body>
  </html>
  ```


#### JQ&AJ get()和post()方法

----

两种方法通过HTTP 的get和post方法从服务器请求数据。

#### jQuary $.get()方法

语法：

```js
$.get(URL,callback);
$("button").click(function(){
  $.get("demo_test.asp",function(data,status){
    alert("Data: " + data + "\nStatus: " + status);
  });
});
```

必需的 *URL* 参数规定您希望请求的 URL。

可选的 *callback* 参数是请求成功后所执行的函数名。

#### jQuary $.post()方法

```js
$.post(URL,data,callback);
```

必需的 *URL* 参数规定您希望请求的 URL。

可选的 *data* 参数规定连同请求发送的数据。

可选的 *callback* 参数是请求成功后所执行的函数名。

Demo:

```js
$("button").click(function(){
  $.post("demo_test_post.asp",
  {
    name:"Donald Duck",
    city:"Duckburg"
  },
  function(data,status){
    alert("Data: " + data + "\nStatus: " + status);
  });
});
```



#### jQuary noConflict

---

为了解决与其他的js框架$冲突。

noConflict() 方法会释放会 $ 标识符的控制(用jQuary替换)，这样其他脚本就可以使用它了。

Demo：

```js
<!-- D1 -->
$.noConflict();
jQuery(document).ready(function(){
  jQuery("button").click(function(){
    jQuery("p").text("jQuery 仍在运行！");
  });
});

<!-- D2 -->
var jq = $.noConflict();
jq(document).ready(function(){
  jq("button").click(function(){
    jq("p").text("jQuery 仍在运行！");
  });
});
```



####  练习实例

url：http://www.w3school.com.cn/jquery/jquery_examples.asp

















