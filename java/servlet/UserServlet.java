package servlet;

import domain.Admin;
import service.UserService;
import domain.User;
import domain.Page;
import utils.CommonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends BaseServlet {
    private UserService userService = new UserService();

    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = CommonUtils.toBean(request.getParameterMap(), User.class);
        userService.add(user);
        request.setAttribute("msg", "添加用户成功！");

        return getReferer(request) + "/msg.jsp";
    }

    public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        userService.delete(email);
        request.setAttribute("msg", "删除用户成功！");

        return getReferer(request) + "/msg.jsp";
    }

    public String preEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        User user = userService.find(email);
        request.setAttribute("user", user);
        return getReferer(request) + "/user/edit.jsp";
    }

    public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = CommonUtils.toBean(request.getParameterMap(), User.class);
        userService.edit(user);
        request.setAttribute("msg", "用户信息修改成功！");

        return getReferer(request) + "/msg.jsp";
    }

    public String query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = CommonUtils.toBean(request.getParameterMap(), User.class);
        int currPage = getCurrPage(request);
        int pageRecord = 10;
        Page<User> page = userService.query(user, currPage, pageRecord);
        page.setUrl(getUrl(request));
        request.setAttribute("page", page);

        return getReferer(request) + "/user/list.jsp";
    }

    public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = CommonUtils.toBean(request.getParameterMap(), User.class);
        boolean isExist = false;
        if (user.getEmail().equals(Admin.account) && user.getPassword().equals(Admin.password)) {
            isExist = true;
            Cookie cookie1 = new Cookie("isAdmin", "1");
            cookie1.setMaxAge(-1);
            cookie1.setPath("/");
            response.addCookie(cookie1);
            request.setAttribute("isAdmin", 1);
        }
        isExist |= userService.verify(user);
        if (isExist) {
            Cookie cookie2 = new Cookie("user", user.getEmail());
            cookie2.setMaxAge(-1);
            cookie2.setPath("/");
            response.addCookie(cookie2);
            request.setAttribute("msg", "登录成功！");
            request.setAttribute("isLogin", 1);
            request.setAttribute("loginUser", user.getEmail());
        } else {
            request.setAttribute("msg", "登录失败！");
        }

        return getReferer(request) + "/msg.jsp";
    }

    public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user")) {
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
                request.setAttribute("isLogin", 0);
            }

            if (cookie.getName().equals("isAdmin")) {
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
                request.setAttribute("isLogin", 0);
                request.setAttribute("isAdmin", 0);
            }
        }
        request.setAttribute("msg", "注销成功！");

        return getReferer(request) + "/msg.jsp";
    }

    public String signup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = CommonUtils.toBean(request.getParameterMap(), User.class);
        if (user.getEmail().equals(Admin.account) || userService.isExist(user)) {
            request.setAttribute("msg", "帐户已存在！");
        } else {
            userService.add(user);
            request.setAttribute("msg", "注册成功！");
        }

        return getReferer(request) + "/msg.jsp";
    }
}
