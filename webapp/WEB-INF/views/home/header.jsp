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
                <a class="navbar-brand" href="${pageContext.request.contextPath}/home/index">图书评论网</a>
            </div>

            <div class="collapse navbar-collapse" id="navbar">
                <ul class="nav navbar-nav">
                    <li class="">
                        <a href="${pageContext.request.contextPath}/home/index">主页</a>
                    </li>

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            查看图书
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="${pageContext.request.contextPath}/textbook/query?currPage=1">全部图书</a></li>
                            <li class="divider"></li>
                            <li><a href="${pageContext.request.contextPath}/textbook/query?genre=数学&currPage=1">数学类</a></li>
                            <li><a href="${pageContext.request.contextPath}/textbook/query?genre=英语&currPage=1">英语类</a></li>
                            <li><a href="${pageContext.request.contextPath}/textbook/query?genre=语文&currPage=1">语文类</a></li>
                            <li><a href="${pageContext.request.contextPath}/textbook/query?genre=计算机&currPage=1">计算机类</a></li>
                        </ul>
                    </li>
                </ul>

                <form  class="navbar-form navbar-left" role="search" action="<c:url value='/textbook/query'/>" method="get">
                    <div class="form-group-sm">
                        <input type="text" placeholder="请输入图书名字"  class="form-control" name="name"/>
                        <button type="submit" class="btn btn-default">
                            <i class="glyphicon glyphicon-search"></i>
                        </button>
                    </div>
                </form>

                <ul class="nav navbar-nav navbar-right">
                    <c:if test="${sessionScope.isAdmin eq 1}">
                        <li><a href="${pageContext.request.contextPath}/admin/index"><span class="glyphicon glyphicon-cog"></span> 管理员页面</a></li>
                    </c:if>

                    <c:if test="${sessionScope.isLogin eq 1}">
                        <li><a href="#"><span class="glyphicon glyphicon-user"></span> ${sessionScope.loginUser}</a></li>
                    </c:if>

                    <c:choose>
                        <c:when test="${sessionScope.isLogin eq 1}">
                            <li>
                                <a href="${pageContext.request.contextPath}/user/logout">
                                    <span class="glyphicon glyphicon-log-out"></span> Log out
                                </a>
                            </li>
                        </c:when>

                        <c:otherwise>
                            <li>
                                <a href="${pageContext.request.contextPath}/user/preLogin">
                                    <span class="glyphicon glyphicon-log-in"></span> Log in
                                </a>
                            </li>

                            <li>
                                <a href="${pageContext.request.contextPath}/user/preSignup">
                                    <span class="glyphicon glyphicon-plus-sign"></span> Sign up
                                </a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>
    </nav>

    <script type="text/javascript">
        $(function () {
            $('.navbar-nav li').find('a').each(function () {
                if (this.href === document.location.href || document.location.href.search(this.href) >= 0) {
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
