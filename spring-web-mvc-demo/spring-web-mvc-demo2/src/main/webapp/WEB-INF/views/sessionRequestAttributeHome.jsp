<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<html>
<head>
    <!-- 将资源文件设置到变量使用 -->
    <spring:url value="/resources/test-main.css" var="testMainCss"/>
    <!-- rel: relationship between the current document and linked document -->
    <link href="${testMainCss}" rel="stylesheet"/>
    <%-- <link href="<c:url value="/resources/test-main.css" />" rel="stylesheet"> --%>
</head>
<body>
<div align="center">
    <h1>@Session Attribute Test</h1>
    <!-- Spring Form Tag表单提交默认的request是POST(前端页面会显示), 但可以设置method="GET" !!
         这里的POST Request不能提交给Controller中标记成method = RequestMethod.GET的handler method !!
         .
         modelAttribute="visitorStats" 提交到指定的Model attribute(VisitorData类型), 名称必须保持一致
         Model attribute对象中包含currentVisitorName和currentVisitorEmail两个属性 -->
    <form action="visitor" modelAttribute="visitorStats">
        <table>
            <tr>
                <td><form:label path="currentVisitorName">Name</form:label></td>
                <td><form:input path="currentVisitorName"/></td>
            </tr>
            <tr>
                <td><form:label path="currentVisitorEmail">Email</form:label></td>
                <td><form:input path="currentVisitorEmail"/></td>
            </tr>
            <tr>
                <td align="center">
                    <input type="submit" value="submit">
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
