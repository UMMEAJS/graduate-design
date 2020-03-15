<%--
  Created by IntelliJ IDEA.
  User: oncb
  Date: 2020/2/1
  Time: 12:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>评论信息中心</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/bootstrap/css/bootstrap.min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/statics/jQuery/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/statics/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
    <%@ include file="../header.jsp" %>
    <div>
        <h1 style="color:grey" align="center">搜索评论信息</h1>
    </div>
    <div class="container-fluid">
        <form class="form-horizontal" role="form" action="<c:url value='/review/query'/>" method="get">
            <input type="hidden" name="adminPage" value="1">

            <div class="form-group">
                <label class="col-sm-2 control-label">评论</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="review" placeholder="请输入评论">
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">查询信息</button>
                    <button type="reset" class="btn btn-reset">重置</button>
                </div>
            </div>
        </form>
    </div>
    <%@ include file="../footer.jsp" %>
</body>
</html>
