<%--
  Created by IntelliJ IDEA.
  User: oncb
  Date: 2020/2/4
  Time: 23:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>header</title>
    <link rel="stylesheet" href="<%= request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
    <script type="text/javascript" src="<%= request.getContextPath()%>/jQuery/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="<%= request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
    <nav class="nav navbar-inverse navbar-static-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" onclick="window.location.href='/TextbookReview/jsp/textbook/index.jsp'">图书信息系统</a>
            </div>
            <div class="collapse navbar-collapse" id="navbar">
                <ul class="nav navbar-nav">
                    <li><a data-toggle="tab" onclick="window.location.href='/TextbookReview/jsp/textbook/index.jsp'">主页</a></li>
                    <li><a data-toggle="tab" onclick="window.location.href='/TextbookReview/jsp/textbook/add.jsp'">添加信息</a></li>
                    <li><a data-toggle="tab" onclick="window.location.href='/TextbookReview/jsp/textbook/query.jsp'">搜索信息</a></li>
                    <li><a data-toggle="tab" onclick="window.location.href='/TextbookReview/textbook?method=query&currPage=1'">查看信息</a></li>
                    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Dropdown<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">First</a></li>
                            <li><a href="#">Second</a></li>
                            <li><a href="#">Third</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a onclick="window.location.href='login.jsp'"><span class="glyphicon glyphicon-log-in"></span> Log in</a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-log-out"></span> Log out</a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-user"></span> Admin</a></li>
                </ul>
            </div>
        </div>
    </nav>
</body>
</html>
