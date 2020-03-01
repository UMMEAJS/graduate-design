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

        return getReferer(request) + "/msg.jsp";
    }

    public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("isbn");
        textbookService.delete(isbn);
        request.setAttribute("msg", "删除书籍成功！");

        return getReferer(request) + "/msg.jsp";
    }

    public String preEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("isbn");
        Textbook textbook = textbookService.find(isbn);
        request.setAttribute("textbook", textbook);

        return getReferer(request) + "/textbook/edit.jsp";
    }

    public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Textbook textbook = CommonUtils.toBean(request.getParameterMap(), Textbook.class);
        textbookService.edit(textbook);
        request.setAttribute("msg", "书籍信息修改成功！");

        return getReferer(request) + "/msg.jsp";
    }

    public String query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Textbook textbook = CommonUtils.toBean(request.getParameterMap(), Textbook.class);
        int currPage = getCurrPage(request);
        int pageRecord = 10;

        Page<Textbook> page = textbookService.query(textbook, currPage, pageRecord);
        page.setUrl(getUrl(request));
        request.setAttribute("page", page);

        return getReferer(request) + "/textbook/list.jsp";
    }

    public String addReview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("isbn");
        request.setAttribute("isbn", isbn);

        return getReferer(request) + "/review/add.jsp";
    }
}
