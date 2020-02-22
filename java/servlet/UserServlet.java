package servlet;

import service.UserService;
import domain.User;
import domain.Page;
import utils.CommonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends BaseServlet {
    private UserService userService = new UserService();

    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = CommonUtils.toBean(request.getParameterMap(), User.class);
        user.setId(CommonUtils.getUUID());
        userService.add(user);
        request.setAttribute("msg", "添加用户成功！");
        return "/jsp/user/msg.jsp";
    }

    public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        userService.delete(id);
        request.setAttribute("msg", "删除用户成功！");
        return "/jsp/user/msg.jsp";
    }

    public String preEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        User user = userService.find(id);
        request.setAttribute("user", user);
        return "/jsp/user/edit.jsp";
    }

    public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = CommonUtils.toBean(request.getParameterMap(), User.class);
        userService.edit(user);
        request.setAttribute("msg", "用户信息修改成功！");
        return "/jsp/user/msg.jsp";
    }

    public int getCurrPage(HttpServletRequest request) throws ServletException, IOException {
        String currPage = request.getParameter("currPage");
        if (currPage == null || currPage.trim().isEmpty()) {
            return 1;
        }
        return Integer.parseInt(currPage);
    }

    public String getUrl(HttpServletRequest request) {
        String contextPath =  request.getContextPath();
        String servletPath = request.getServletPath();
        String queryString = request.getQueryString();

        if (queryString.contains("&currPage=")) {
            int index = queryString.indexOf("&currPage=");
            queryString = queryString.substring(0, index);
        }

        return contextPath + servletPath + "?" + queryString;
    }

    public String query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = CommonUtils.toBean(request.getParameterMap(), User.class);
        int currPage = getCurrPage(request);
        int pageRecord = 10;

        Page<User> page = userService.query(user, currPage, pageRecord);
        page.setUrl(getUrl(request));
        request.setAttribute("page", page);

        return "/jsp/user/list.jsp";
    }

    public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = CommonUtils.toBean(request.getParameterMap(), User.class);
        boolean isExist = userService.isExist(user);
        if (isExist) {
            request.setAttribute("msg", "登录成功！");
        } else {
            request.setAttribute("msg", "登录失败！");
        }

        return "/jsp/user/msg.jsp";
    }
}
