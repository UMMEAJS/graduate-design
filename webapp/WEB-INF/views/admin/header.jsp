<%--
  Created by IntelliJ IDEA.
  User: oncb
  Date: 2020/2/4
  Time: 23:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>header</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/bootstrap/css/bootstrap.min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/statics/jQuery/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/statics/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
    <nav class="nav navbar-inverse navbar-static-top" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${pageContext.request.contextPath}/admin/index">管理员界面</a>
            </div>

            <div class="collapse navbar-collapse" id="navbar">
                <ul class="nav navbar-nav">
                    <li class="">
                        <a href="${pageContext.request.contextPath}/admin/index">主页</a>
                    </li>

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            用户中心
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="${pageContext.request.contextPath}/user/query?adminPage=1&currPage=1">查看用户</a></li>
                            <li><a href="${pageContext.request.contextPath}/user/preQuery?adminPage=1">搜索用户</a></li>
                        </ul>
                    </li>

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            图书中心
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="${pageContext.request.contextPath}/textbook/query?adminPage=1&currPage=1">查看图书</a></li>
                            <li><a href="${pageContext.request.contextPath}/textbook/preQuery?adminPage=1">搜索图书</a></li>
                            <li><a href="${pageContext.request.contextPath}/textbook/preAdd?adminPage=1">添加图书</a></li>
                        </ul>
                    </li>

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            评论中心
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="${pageContext.request.contextPath}/review/query?adminPage=1&currPage=1">查看评论</a></li>
                            <li><a href="${pageContext.request.contextPath}/review/preQuery?adminPage=1">搜索评论</a></li>
                        </ul>
                    </li>
                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <c:if test="${sessionScope.isAdmin eq 1}">
                        <li><a href="${pageContext.request.contextPath}/home/index"><span class="glyphicon glyphicon-home"></span> 图书评论网</a></li>
                    </c:if>

                    <c:if test="${sessionScope.isAdmin eq 1}">
                        <li><a href="#"><span class="glyphicon glyphicon-user"></span> ${sessionScope.loginUser}</a></li>
                    </c:if>


                    <c:if test="${sessionScope.isAdmin eq 1}">
                        <li>
                            <a href="${pageContext.request.contextPath}/user/logout">
                                <span class="glyphicon glyphicon-log-out"></span> Log out
                            </a>
                        </li>
                    </c:if>
                </ul>
            </div>
        </div>
    </nav>

    <script type="text/javascript">
        $(function () {
            $('.navbar-nav li').find('a').each(function () {
                if (this.href === document.location.href || document.location.href.search(this.href) > 0) {
                    $(this).parent().siblings('li').removeClass('active');
                    $(this).parent().addClass('active');
                }
            });
        });

        $(function () {
            const dropdown = $(".dropdown");
            dropdown.mouseover(function () {
                $(this).addClass("open");
            });
            dropdown.mouseleave(function(){
                $(this).removeClass("open");
            })
        })
    </script>
</body>
</html>
