<%--
  Created by IntelliJ IDEA.
  User: oncb
  Date: 2020/2/4
  Time: 23:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    if (request.getAttribute("isLogin") == null) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user")) {
                request.setAttribute("isLogin", 1);
                request.setAttribute("loginUser", cookie.getValue());
                break;
            }

            if (cookie.getName().equals("isAdmin")) {
                request.setAttribute("isAdmin", 1);
                break;
            }
        }
    }
%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>header</title>
    <link rel="stylesheet" href="<%= request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
    <script type="text/javascript" src="<%= request.getContextPath()%>/jQuery/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="<%= request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
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
                <a class="navbar-brand" href="/TextbookReview/admin/index.jsp">管理员界面</a>
            </div>

            <div class="collapse navbar-collapse" id="navbar">
                <ul class="nav navbar-nav">
                    <li class="">
                        <a href="/TextbookReview/admin/index.jsp">主页</a>
                    </li>

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            用户中心
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="/TextbookReview/user?method=query&currPage=1">查看用户</a></li>
                            <li><a href="/TextbookReview/admin/user/query.jsp">搜索用户</a></li>
                        </ul>
                    </li>

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            图书中心
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="/TextbookReview/textbook?method=query&currPage=1">查看图书</a></li>
                            <li><a href="/TextbookReview/admin/textbook/query.jsp">搜索图书</a></li>
                            <li><a href="/TextbookReview/admin/textbook/add.jsp">添加图书</a></li>
                        </ul>
                    </li>

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            评论中心
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="/TextbookReview/review?method=query&currPage=1">查看评论</a></li>
                            <li><a href="/TextbookReview/admin/review/query.jsp">搜索评论</a></li>
                        </ul>
                    </li>
                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <c:if test="${requestScope.isLogin eq 1}">
                        <li><a href="#"><span class="glyphicon glyphicon-user"></span> ${requestScope.loginUser}</a></li>
                    </c:if>

                    <c:choose>
                        <c:when test="${requestScope.isLogin eq 1}">
                            <li>
                                <a href="/TextbookReview/user?method=logout">
                                    <span class="glyphicon glyphicon-log-out"></span> Log out
                                </a>
                            </li>
                        </c:when>

                        <c:otherwise>
                            <li>
                                <a href="/TextbookReview/user/login.jsp">
                                    <span class="glyphicon glyphicon-log-in"></span> Log in
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
