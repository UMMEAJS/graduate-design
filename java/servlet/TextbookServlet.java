package servlet;

import domain.Textbook;
import service.TextbookService;
import domain.Page;
import utils.CommonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/textbook")
public class TextbookServlet extends BaseServlet {
    private TextbookService textbookService = new TextbookService();

    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Textbook textbook = CommonUtils.toBean(request.getParameterMap(), Textbook.class);
        textbookService.add(textbook);
        request.setAttribute("msg", "添加书籍成功！");
        return "/msg.jsp";
    }

    public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("isbn");
        textbookService.delete(isbn);
        request.setAttribute("msg", "删除书籍成功！");
        return "/msg.jsp";
    }

    public String preEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("isbn");
        Textbook textbook = textbookService.find(isbn);
        request.setAttribute("textbook", textbook);
        return "/edit.jsp";
    }

    public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Textbook textbook = CommonUtils.toBean(request.getParameterMap(), Textbook.class);
        textbookService.edit(textbook);
        request.setAttribute("msg", "书籍信息修改成功！");
        return "/msg.jsp";
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
        Textbook textbook = CommonUtils.toBean(request.getParameterMap(), Textbook.class);
        int currPage = getCurrPage(request);
        int pageRecord = 10;

        Page<Textbook> page = textbookService.query(textbook, currPage, pageRecord);
        page.setUrl(getUrl(request));
        request.setAttribute("page", page);

        return "/list.jsp";
    }
}
