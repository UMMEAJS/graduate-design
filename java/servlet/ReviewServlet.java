package servlet;

import domain.Review;
import service.ReviewService;
import domain.Page;
import utils.CommonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/review")
public class ReviewServlet extends BaseServlet {
    private ReviewService reviewService = new ReviewService();

    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Review review = CommonUtils.toBean(request.getParameterMap(), Review.class);
        review.setRid(CommonUtils.getUUID());
        review.setDate(CommonUtils.getDate());
        reviewService.add(review);
        request.setAttribute("msg", "添加评论成功！");
        return "/jsp/review/msg.jsp";
    }

    public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        reviewService.delete(rid);
        request.setAttribute("msg", "删除评论成功！");
        return "/jsp/review/msg.jsp";
    }

    public String preEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        Review review = reviewService.find(rid);
        request.setAttribute("review", review);
        return "/jsp/review/edit.jsp";
    }

    public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Review review = CommonUtils.toBean(request.getParameterMap(), Review.class);
        reviewService.edit(review);
        request.setAttribute("msg", "评论信息修改成功！");
        return "/jsp/review/msg.jsp";
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
        Review review = CommonUtils.toBean(request.getParameterMap(), Review.class);
        int currPage = getCurrPage(request);
        int pageRecord = 10;

        Page<Review> page = reviewService.query(review, currPage, pageRecord);
        page.setUrl(getUrl(request));
        request.setAttribute("page", page);

        return "/jsp/review/list.jsp";
    }
}
