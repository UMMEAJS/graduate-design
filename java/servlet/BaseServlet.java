package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        Method method = null;
        String methodName = request.getParameter("method");

        try {
            method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            String result = (String)method.invoke(this, request, response);
            if (result != null && !result.trim().isEmpty()) {
                int index = result.indexOf(':');
                if (index == -1) {
                    request.getRequestDispatcher(result).forward(request, response);
                } else {
                    String prefix = result.substring(0, index);
                    String path = result.substring(index);
                    if (prefix.equals("f")) {
                        request.getRequestDispatcher(path).forward(request, response);
                    } else if (prefix.equals("r")) {
                        response.sendRedirect(request.getContextPath() + path);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
