<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add Item Page</title>
</head>
<body>
<div align="center">
    <!-- 使用定义的prefix:form -->
    <form:form method="post" modelAttribute="todoItem">
        <table>
            <tr>
                <td><label>ID</label></td>
                <td>
                    <!-- id是背后自动生成的，不允许修改 -->
                    <form:input path="id" disabled="true"/>
                </td>
            </tr>
            <tr>
                <td><label>Title</label></td>
                <td><form:input path="title"/></td>
            </tr>
            <tr>
                <td><label>Deadline</label></td>
                <td><form:input path="deadline"/></td>
            </tr>
            <tr>
                <td><label>Details</label></td>
                <td><form:textarea path="details"/></td>
            </tr>
            <tr>
                <td><input type="submit" value="Submit"/></td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>

