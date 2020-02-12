<%--
  Created by IntelliJ IDEA.
  User: oncb
  Date: 2020/1/10
  Time: 10:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>用户信息中心</title>
    <link rel="stylesheet" href="<%= request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
    <script type="text/javascript" src="<%= request.getContextPath()%>/jQuery/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="<%= request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
    <%@ include file="header.jsp"%>
    <div class="container-fluid">
        <h1 style="color:grey" align="center">欢迎来到用户信息中心</h1>
    </div>
    <%@ include file="footer.jsp"%>
</body>
</html>
