package com.oncb.controller;

import com.oncb.pojo.Page;
import com.oncb.pojo.Textbook;
import com.oncb.service.ReviewService;
import com.oncb.service.TextbookService;
import com.oncb.utils.CommonUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/textbook")
public class TextbookController {
    private static final ReviewService reviewService = (ReviewService)CommonUtils.getApplicationContext().getBean("reviewService");
    private static final TextbookService textbookService = (TextbookService)CommonUtils.getApplicationContext().getBean("textbookService");

    @RequestMapping(value = "/preAdd")
    public String preAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return CommonUtils.getReferer(request) + "/textbook/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Textbook textbook = CommonUtils.toBean(request.getParameterMap(), Textbook.class);
        textbookService.add(textbook);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(CommonUtils.getReferer(request) + "/msg");
        modelAndView.addObject("msg", "添加书籍成功！");

        return modelAndView;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("isbn");
        reviewService.deleteByIsbn(isbn);
        textbookService.delete(isbn);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(CommonUtils.getReferer(request) + "/msg");
        modelAndView.addObject("msg", "删除书籍成功！");

        return modelAndView;
    }

    @RequestMapping(value = "/preEdit", method = RequestMethod.GET)
    public ModelAndView preEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("isbn");
        Textbook textbook = textbookService.find(isbn);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(CommonUtils.getReferer(request) + "/textbook/edit");
        modelAndView.addObject("textbook", textbook);

        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Textbook textbook = CommonUtils.toBean(request.getParameterMap(), Textbook.class);
        textbookService.edit(textbook);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(CommonUtils.getReferer(request) + "/msg");
        modelAndView.addObject("msg", "修改书籍成功！");

        return modelAndView;
    }

    @RequestMapping(value = "/preQuery")
    public String preQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return CommonUtils.getReferer(request) + "/textbook/query";
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public ModelAndView query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Textbook textbook = CommonUtils.toBean(request.getParameterMap(), Textbook.class);
        int currPage = CommonUtils.getCurrPage(request);
        int pageRecord = 10;
        Page<Textbook> page = textbookService.query(textbook, currPage, pageRecord);
        page.setUrl(CommonUtils.getUrl(request));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(CommonUtils.getReferer(request) + "/textbook/list");
        modelAndView.addObject("page", page);

        return modelAndView;
    }
}
