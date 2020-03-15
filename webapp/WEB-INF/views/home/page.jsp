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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/bootstrap/css/bootstrap.min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/statics/jQuery/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/statics/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
    <div style="text-align: center">
        <ul class="pagination">
            <c:choose>
                <c:when test="${requestScope.page.currPage>1}">
                    <li><a href="${requestScope.page.url}&currPage=${requestScope.page.currPage-1}">&laquo;</a></li>
                </c:when>
                <c:otherwise>
                    <li class="disabled"><a href="#">&laquo;</a></li>
                </c:otherwise>
            </c:choose>

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
                        <li class="active"><a href="#">${i}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="${requestScope.page.url}&currPage=${i}">${i}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:choose>
                <c:when test="${requestScope.page.currPage<requestScope.page.totalPage}">
                    <li><a href="${requestScope.page.url}&currPage=${requestScope.page.currPage+1}">&raquo;</a></li>
                </c:when>
                <c:otherwise>
                    <li class="disabled"><a href="#">&raquo;</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</body>
</html>
