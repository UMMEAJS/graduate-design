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
    <title>评论信息中心</title>
    <link rel="stylesheet" href="<%= request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
    <script type="text/javascript" src="<%= request.getContextPath()%>/jQuery/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="<%= request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
    <%@ include file="../header.jsp" %>
    <div>
        <h1 style="color:grey" align="center">查看评论信息</h1>
    </div>
    <div class="container-fluid">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th style='vertical-align: middle;text-align: center'>RID</th>
                    <th style='vertical-align: middle;text-align: center'>UID</th>
                    <th style='vertical-align: middle;text-align: center'>ISBN</th>
                    <th style='vertical-align: middle;text-align: center'>日期</th>
                    <th style='vertical-align: middle;text-align: center'>评论</th>
                    <th style='vertical-align: middle;text-align: center'>评分</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.page.beanList}" var="review">
                    <tr>
                        <td style='vertical-align: middle;text-align: center'>${review.rid}</td>
                        <td style='vertical-align: middle;text-align: center'>${review.uid}</td>
                        <td style='vertical-align: middle;text-align: center'>${review.isbn}</td>
                        <td style='vertical-align: middle;text-align: center'>${review.date}</td>
                        <td style='vertical-align: middle;text-align: center'>${review.review}</td>
                        <td style='vertical-align: middle;text-align: center'>${review.star}</td>
                        <td style='vertical-align: middle;text-align: center'>
                            <a href="<c:url value='/review?method=delete&rid=${review.rid}&addminPage=1'/>">
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
