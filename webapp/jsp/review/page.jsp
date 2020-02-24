<%--
  Created by IntelliJ IDEA.
  User: oncb
  Date: 2020/2/20
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>page</title>
    <link rel="stylesheet" href="<%= request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
    <script type="text/javascript" src="<%= request.getContextPath()%>/jQuery/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="<%= request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
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
</body>
</html>
