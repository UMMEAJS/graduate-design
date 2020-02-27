<%--
  Created by IntelliJ IDEA.
  User: oncb
  Date: 2020/2/1
  Time: 22:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>图书信息中心</title>
    <link rel="stylesheet" href="<%= request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
    <script type="text/javascript" src="<%= request.getContextPath()%>/jQuery/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="<%= request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
    <%@ include file="../header.jsp" %>
    <div>
        <h1 style="color:grey" align="center">查看图书信息</h1>
    </div>
    <div class="container-fluid">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th style='vertical-align: middle;text-align: center'>ISBN</th>
                    <th style='vertical-align: middle;text-align: center'>名字</th>
                    <th style='vertical-align: middle;text-align: center'>类型</th>
                    <th style='vertical-align: middle;text-align: center'>评分</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.page.beanList}" var="textbook">
                    <tr>
                        <td style='vertical-align: middle;text-align: center'>${textbook.isbn}</td>
                        <td style='vertical-align: middle;text-align: center'>${textbook.name}</td>
                        <td style='vertical-align: middle;text-align: center'>${textbook.genre}</td>
                        <td style='vertical-align: middle;text-align: center'>${textbook.star}</td>
                        <td style='vertical-align: middle;text-align: center'>
                            <a href="<c:url value='/textbook?method=preEdit&isbn=${textbook.isbn}'/>">
                                <span class="glyphicon glyphicon-edit text-info"></span>
                            </a>
                            <a href="<c:url value='/textbook?method=delete&isbn=${textbook.isbn}'/>">
                                <span class="glyphicon glyphicon-remove text-warning"></span>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <%@ include file="../page.jsp" %>
    <%@ include file="../footer.jsp" %>
</body>
</html>
