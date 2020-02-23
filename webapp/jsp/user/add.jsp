<%--
  Created by IntelliJ IDEA.
  User: oncb
  Date: 2020/2/1
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>添加用户信息</title>
    <link rel="stylesheet" href="<%= request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
    <script type="text/javascript" src="<%= request.getContextPath()%>/jQuery/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="<%= request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
    <%@ include file="header.jsp" %>
    <div>
        <h1 style="color:grey" align="center">添加学生信息</h1>
    </div>
    <div class="container-fluid">
        <form class="form-horizontal" role="form" action="<c:url value='/user'/>" method="post">
            <input type="hidden" name="method" value="add">

            <div class="form-group">
                <label class="col-sm-2 control-label">名字</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="name" placeholder="请输入名字">
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label">密码</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" name="password" placeholder="请输入密码">
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label">邮箱</label>
                <div class="col-sm-10">
                    <input type="email" class="form-control" name="email" placeholder="请输入邮箱">
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-submit">提交</button>
                    <button type="reset" class="btn btn-reset">重置</button>
                </div>
            </div>
        </form>
    </div>
    <%@ include file="footer.jsp" %>
</body>
</html>
