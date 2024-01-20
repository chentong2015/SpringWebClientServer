<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <spring:url value="/resources/test-main.css" var="testMainCss"/>
    <link href="${testMainCss}" rel="stylesheet"/>
    <title>Result Page</title>
</head>
<body>
<div align="center">
    <h1>@Session Attribute Result</h1>
    <ul>
        <!-- visitorData: 从Session Attribute中取的数据值 !!
             visitorData.visitors 直接使用对象中的属性 -->
        <c:forEach var="visitor" item="${visitorData.visitors}">
            <i>
                <!-- 这的Visitor Object必须包含属性的Getter方法 -->
                <c:out value="${visitor.name}"/>
                <c:out value="${visitor.email}"/>
            </i>
        </c:forEach>
    </ul>
    <br>
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <a href="${contextPath/visitorRegister/home}">Generate Another Visit</a>
</div>
</body>
</html>
