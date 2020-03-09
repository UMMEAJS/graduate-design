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
        <c:forEach items="${requestScope.page.beanList}" var="review">
            <div class="container-fluid" style="text-align: center">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-sm-4" style="text-align: left">
                                用户:${review.email}
                            </div>
                            <div class="col-sm-4" style="text-align: center">
                                日期:${review.date}
                            </div>
                            <div class="col-sm-4" style="text-align: right">
                                评分:${review.star}
                            </div>
                        </div>

                    </div>
                    <div class="panel-body">
                        <p class="card-text">${review.review}</p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <%@ include file="../page.jsp" %>
    <%@ include file="../footer.jsp" %>
</body>
</html>
