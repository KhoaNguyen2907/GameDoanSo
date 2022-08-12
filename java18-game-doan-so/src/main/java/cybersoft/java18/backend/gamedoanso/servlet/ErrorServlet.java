package cybersoft.java18.backend.gamedoanso.servlet;

import cybersoft.java18.backend.gamedoanso.utils.JspUtils;
import cybersoft.java18.backend.gamedoanso.utils.UrlUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case UrlUtils.NOT_FOUND :
                req.getRequestDispatcher(JspUtils.NOT_FOUND).forward(req, resp);
            case UrlUtils.INTERNAL_ERROR:
                req.getRequestDispatcher(JspUtils.INTERNAL_ERROR).forward(req, resp);
        }

    }
}
