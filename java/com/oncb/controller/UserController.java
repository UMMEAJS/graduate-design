package com.oncb.controller;

import com.oncb.pojo.Admin;
import com.oncb.pojo.Page;
import com.oncb.pojo.User;
import com.oncb.service.UserService;
import com.oncb.utils.CommonUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final UserService userService = (UserService)CommonUtils.getApplicationContext().getBean("userService");

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView add(HttpServletRequest request) throws ServletException, IOException {
        User user = CommonUtils.toBean(request.getParameterMap(), User.class);
        userService.add(user);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(CommonUtils.getReferer(request) + "/msg");
        modelAndView.addObject("msg", "添加用户成功！");

        return modelAndView;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        userService.delete(email);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(CommonUtils.getReferer(request) + "/msg");
        modelAndView.addObject("msg", "删除用户成功！");

        return modelAndView;
    }

    @RequestMapping(value = "/preEdit", method = RequestMethod.GET)
    public ModelAndView preEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        User user = userService.find(email);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(CommonUtils.getReferer(request) + "/edit");
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = CommonUtils.toBean(request.getParameterMap(), User.class);
        userService.edit(user);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(CommonUtils.getReferer(request) + "/msg");
        modelAndView.addObject("msg", "修改用户成功！");

        return modelAndView;
    }

    @RequestMapping(value = "/preQuery")
    public String preQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return CommonUtils.getReferer(request) + "/user/query";
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public ModelAndView query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = CommonUtils.toBean(request.getParameterMap(), User.class);
        int currPage = CommonUtils.getCurrPage(request);
        int pageRecord = 10;
        Page<User> page = userService.query(user, currPage, pageRecord);
        page.setUrl(CommonUtils.getUrl(request));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(CommonUtils.getReferer(request) + "/user/list");
        modelAndView.addObject("page", page);

        return modelAndView;
    }

    @RequestMapping(value = "/preLogin")
    public String preLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return CommonUtils.getReferer(request) + "/user/login";
    }

    @RequestMapping(value = "/verifyLogin", method = RequestMethod.POST)
    public ModelAndView verifyLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = CommonUtils.toBean(request.getParameterMap(), User.class);
        boolean isExist = false;

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(CommonUtils.getReferer(request) + "/msg");

        if (user.getEmail().equals(Admin.account) && user.getPassword().equals(Admin.password)) {
            isExist = true;
            request.getSession().setAttribute("isAdmin", 1);
        }
        isExist |= userService.verify(user);
        if (isExist) {
            modelAndView.addObject("msg", "登录成功！");
            request.getSession().setAttribute("isLogin", 1);
            request.getSession().setAttribute("loginUser", user.getEmail());
        } else {
            modelAndView.addObject("msg", "登录失败！");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(CommonUtils.getReferer(request) + "/msg");

        request.getSession().removeAttribute("isLogin");
        request.getSession().removeAttribute("isAdmin");
        request.getSession().removeAttribute("loginUser");
        modelAndView.addObject("msg", "注销成功！");

        return modelAndView;
    }

    @RequestMapping(value = "/preSignup")
    public String preSignup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return CommonUtils.getReferer(request) + "/user/signup";
    }

    @RequestMapping(value = "/verifySignup", method = RequestMethod.POST)
    public ModelAndView verifySignup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = CommonUtils.toBean(request.getParameterMap(), User.class);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(CommonUtils.getReferer(request) + "/msg");

        if (user.getEmail().equals(Admin.account) || userService.isExist(user)) {
            modelAndView.addObject("msg", "帐户已存在！");
        } else {
            userService.add(user);
            modelAndView.addObject("msg", "注册成功！");
        }

        return modelAndView;
    }
}
