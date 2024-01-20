package main.view;

/**
 * JSTL: Java ServerPage Standard Tag Library
 * 1. JSP页面便签的类库，支持页面标签的循环和判断等条件
 * -- <c:forEach> 循环集合中的元素
 * -- <c:out> 显示表达式的值
 * -- <c:utl> format a URL into a string and store it into a variable 然后在view页面使用变量
 */
public class BaseJSTL {

    /**
     * 添加JSTL Dependency
     * <!-- https://mvnrepository.com/artifact/javax.servlet/jstl -->
     * <dependency>
     *     <groupId>javax.servlet</groupId>
     *     <artifactId>jstl</artifactId>
     *     <version>1.2</version>
     * </dependency>
     */

    /**
     * 引入JSTL Tag library到JSP页面
     * <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     * .
     * <c:set var="contextPath" value="${pageContext.request.contextPath}" /> 拿到Web请求的URL的路径，设置成指定变量的值
     */

    /**
     * 在JSP页面中使用JSTL Tag: 使用定义的前缀
     * <!-- 从页面的Model中取出要使用的数据, 遍历集合 -->
     * <c:forEach var="item" items="${todoData.items}">
     *      <tr><c:out value="${item.title}"></c:out></tr>
     *      <tr><c:out value="${item.deadline}"></c:out></tr>
     * </c:forEach>
     * .
     * <c:url var="itemsLink" value="items" />
     * <a href="${itemLink}">Show Items</a>
     * .
     * <c:url var="deleteUrl" value="deleteItems"> 动态的创建URL变量, 包含参数追加在后面 ?id=1
     *     <c:param name="id" value="${item.id} />
     * </c:url>
     */

    // JSTL SQL: 前端VIEW直接使用SQL查询语句
    // <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    // <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
    // <sql:query var="resultSet" dataSource="jdbc/nameDataSource">
    //     SELECT * FROM information
    // </sql:query>
    // <c:forEach var="row" items="{resultSet}">
    //     Name: ${row.name} </br>  这里遍历使用的是数据库中列的名称
    // </c:forEach>
	 
    /**
     * JSP文件中可以引入jsp component组件
     * <head>
     * <%@ include file="common/commonStyle.jsp"%>
     * </head>
     */
}
