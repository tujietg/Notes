## Jsp知识点总结

### Jsp基础知识

- jsp的组成
  1. html静态页面（css、javascript）
  2. java代码 <%  %>   (_jspService方法中)
  3. 内置对象 out request
  4. 表达式 <%=  %>
  5. 声明方法和成员变量 <%!  %>
  6. 指令   <%@page>    <%@include> 
  7. 动作  <jsp:include>   <jsp:forward>  
  8. 注释 <%-- --%>
- 包含关系
  - 静态包含 <%@include file="footer.jsp" %>
  - 动态包含 <jsp:includ e page="header.jsp"> 

- 静态包含和动态包含的区别

  |          | 原理       | 是否生产java文件 | 是否生可以有同名变量 | 包含的时机    |
  | -------- | ---------- | ---------------- | -------------------- | ------------- |
  | 动态包含 | 方法的调用 | 生成             | 可以                 | 执行class文件 |
  | 静态包含 | 内容嵌套   | 不生成           | 不可以               | 转译          |

### EL表达式

- jar的引用

  ```jsp
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
  ```

- 常用语法

  - for循环

    ```jsp
    <c:forEach items="${recommendList}" var="item">
    	${item.desc}
    </c:forEach>
    <!--利用下标取出前四个元素 status-->
    <c:forEach items="${split}" var="subString" varStatus="status">
    	<c:if test="${status.index < 4}">
    		<li class=""><img src="${baseUrl}${subString}" alt="用户配图丢失">
        </c:if>
    </c:forEach>
    <!-- 取出123 -->
    <c:forEach var="x" begin="1" end="${pageProductHot.extra.listPageCount}">
        <a href="${baseUrl}product/jsp/indexForH5.jsp">${x}</a>
    </c:forEach>
    ```

  - if判断

    ```jsp
    <!--取出前五个元素 -->
    <c:forEach items="${pageProductHot.list}" var="item" varStatus="status">
    <c:if test="${(status.index) < 5}">
        ${item.name}
    </c:if>   
    </c:forEach>
    <!--判断字符串是否为“” 判断是否为null-->
    <c:if test="${not empty item.coverImgUrl || item.coverImgUrl eq null}">
    
    </c:if>
    <!--分割字符串 并且判断字符串长度-->
    <c:set value="${fn:split(item.imgUrls, ',') }" var="split" />
    	<c:if test="${fn:length('${split}') == 1}">
        <img src="${baseUrl}${split[0]}">
        
        <c:if test="${fn:length(item.textContentShort) > 100}">
        </c:if>
    </c:if>
    ```

  - split分割字符串

    ```jsp
    <!--分割字符串 得到字符串数组-->
    <c:set value="${fn:split(item.imgUrls, ',') }" var="split" />
    ```

  - replace替换字符串中的内容

    ```jsp
    <c:set var="newLine" value="\r\n" />
    ${fn:replace(product.loanRequire, newLine, "<br />")}
    ```