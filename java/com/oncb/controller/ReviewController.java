package com.oncb.controller;

import com.oncb.pojo.Page;
import com.oncb.pojo.Review;
import com.oncb.pojo.Textbook;
import com.oncb.service.ReviewService;
import com.oncb.service.TextbookService;
import com.oncb.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/review")
public class ReviewController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    private static final ReviewService reviewService = new ReviewService();
    private static final TextbookService textbookService = new TextbookService();

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Review review = CommonUtils.toBean(request.getParameterMap(), Review.class);
        review.setId(CommonUtils.getUUID());
        review.setDate(CommonUtils.getDate());
        String[] strs = review.getReview().split("\n");
        String str = String.join("<br/>", strs);
        review.setReview(str);
        reviewService.add(review);
        Textbook textbook = textbookService.find(review.getIsbn());
        textbook.setCount(textbook.getCount() + 1);
        textbook.setStar(textbook.getStar() + review.getStar());
        textbookService.edit(textbook);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(CommonUtils.getReferer(request) + "/msg");
        modelAndView.addObject("msg", "添加评论成功！");

        return modelAndView;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Review review = reviewService.find(id);
        reviewService.deleteById(id);
        Textbook textbook = textbookService.find(review.getIsbn());
        textbook.setCount(textbook.getCount() - 1);
        textbook.setStar(textbook.getStar() - review.getStar());
        textbookService.edit(textbook);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(CommonUtils.getReferer(request) + "/msg");
        modelAndView.addObject("msg", "删除评论成功！");

        return modelAndView;
    }

    @RequestMapping(value = "/preEdit", method = RequestMethod.GET)
    public ModelAndView preEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Review review = reviewService.find(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(CommonUtils.getReferer(request) + "/review/edit");
        modelAndView.addObject("review", review);

        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Review review = CommonUtils.toBean(request.getParameterMap(), Review.class);
        review.setDate(CommonUtils.getDate());
        reviewService.edit(review);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(CommonUtils.getReferer(request) + "/msg");
        modelAndView.addObject("msg", "评论信息修改成功！");

        return modelAndView;
    }

    @RequestMapping(value = "/preQuery")
    public String preQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return CommonUtils.getReferer(request) + "/review/query";
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public ModelAndView query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Review review = CommonUtils.toBean(request.getParameterMap(), Review.class);
        int currPage = CommonUtils.getCurrPage(request);
        int pageRecord = 10;
        Page<Review> page = reviewService.query(review, currPage, pageRecord);
        page.setUrl(CommonUtils.getUrl(request));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(CommonUtils.getReferer(request) + "/review/list");
        modelAndView.addObject("page", page);

        return modelAndView;
    }
}
