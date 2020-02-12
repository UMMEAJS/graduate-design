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
    <title>用户信息中心</title>
    <link rel="stylesheet" href="<%= request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
    <script type="text/javascript" src="<%= request.getContextPath()%>/jQuery/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="<%= request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
    <%@ include file="header.jsp"%>
    <div>
        <h1 style="color:grey" align="center">查看学生信息</h1>
    </div>
    <div class="container-fluid">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th style='vertical-align: middle;text-align: center'>ID</th>
                    <th style='vertical-align: middle;text-align: center'>名字</th>
                    <th style='vertical-align: middle;text-align: center'>密码</th>
                    <th style='vertical-align: middle;text-align: center'>邮箱</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.page.beanList}" var="usr">
                    <tr>
                        <td style='vertical-align: middle;text-align: center'>${usr.getID()}</td>
                        <td style='vertical-align: middle;text-align: center'>${usr.name}</td>
                        <td style='vertical-align: middle;text-align: center'>${usr.password}</td>
                        <td style='vertical-align: middle;text-align: center'>${usr.email}</td>
                        <td style='vertical-align: middle;text-align: center'>
                            <a href="<c:url value='/user?method=preEdit&id=${usr.getID()}'/>">
                                <span class="glyphicon glyphicon-edit text-info"></span>
                            </a>
                            <a href="<c:url value='/user?method=delete&id=${usr.getID()}'/>">
                                <span class="glyphicon glyphicon-remove text-warning"></span>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <br/>

        <div style="text-align: center">
            第${requestScope.page.currPage}页/共${requestScope.page.totalPage}页
            <a href="${requestScope.page.url}&currPage=1">
                <button class="btn">
                    首页
                </button>
            </a>
            <c:if test="${requestScope.page.currPage>1}">
                <a href="${requestScope.page.url}&currPage=${requestScope.page.currPage-1}">
                    <span class="glyphicon glyphicon-step-backward"></span>
                </a>
            </c:if>

            <c:choose>
                <c:when test="${requestScope.page.totalPage<=10}">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="${requestScope.page.totalPage}"/>
                </c:when>

                <c:otherwise>
                    <c:set var="begin" value="${requestScope.page.currPage-5}"/>
                    <c:set var="end" value="${requestScope.page.currPage+4}"/>
                    <c:if test="${begin<1}">
                        <c:set var="begin" value="1"/>
                        <c:set var="end" value="10"/>
                    </c:if>
                    <c:if test="${end>requestScope.page.totalPage}">
                        <c:set var="begin" value="${requestScope.page.totalPage-9}"/>
                        <c:set var="end" value="${requestScope.page.totalPage}"/>
                    </c:if>
                </c:otherwise>
            </c:choose>

            <c:forEach var="i" begin="${begin}" end="${end}">
                <c:choose>
                    <c:when test="${i eq requestScope.page.currPage}">
                        <button class="btn">
                                ${i}
                        </button>
                    </c:when>
                    <c:otherwise>
                        <a href="${requestScope.page.url}&currPage=${i}">
                            <button class="btn">
                                    ${i}
                            </button>
                        </a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${requestScope.page.currPage<requestScope.page.totalPage}">
                <a href="${requestScope.page.url}&currPage=${requestScope.page.currPage+1}">
                    <span class="glyphicon glyphicon-step-forward"></span>
                </a>
            </c:if>
            <a href="${requestScope.page.url}&currPage=${requestScope.page.totalPage}">
                <button class="btn">
                    尾页
                </button>
            </a>
        </div>
    </div>
    <%@ include file="footer.jsp"%>
</body>
</html>
