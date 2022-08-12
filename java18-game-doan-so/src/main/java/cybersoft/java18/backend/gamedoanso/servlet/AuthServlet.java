package cybersoft.java18.backend.gamedoanso.servlet;

import cybersoft.java18.backend.gamedoanso.model.Player;
import cybersoft.java18.backend.gamedoanso.service.GameService;
import cybersoft.java18.backend.gamedoanso.utils.JspUtils;
import cybersoft.java18.backend.gamedoanso.utils.UrlUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {UrlUtils.DANG_NHAP, UrlUtils.DANG_KY, UrlUtils.DANG_XUAT}, name = "authServlet")
public class AuthServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case UrlUtils.DANG_NHAP:
                var error = req.getParameter("error");
                if (error != null && error.equals("not_login")){
                    req.setAttribute("error", "Please login");
                }
                req.getRequestDispatcher(JspUtils.DANG_NHAP).forward(req, resp);
                break;
            case UrlUtils.DANG_KY:
                req.getRequestDispatcher(JspUtils.DANG_KY).forward(req, resp);
                break;
            case UrlUtils.DANG_XUAT:
                req.getSession().removeAttribute("currentUser");
                resp.sendRedirect(req.getContextPath() + UrlUtils.ROOT);
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case UrlUtils.DANG_NHAP:
                processLogin(req, resp);
                break;

            case UrlUtils.DANG_KY:
                processRegister(req, resp);
                break;
            default:
                break;
        }
    }

    private void processLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        Player newPlayer = GameService.getINSTANCE().login(userName, password);
        if (newPlayer != null) {
            req.getSession().setAttribute("currentUser", newPlayer);
            resp.sendRedirect(req.getContextPath() + UrlUtils.GAME);
        } else {
                req.setAttribute("error", "Wrong password");
                doGet(req,resp);
        }
    }

    private void processRegister(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        String fullName = req.getParameter("fullname");
        Player newPlayer = GameService.getINSTANCE().register(userName, password, fullName);
        if (newPlayer != null) {
            req.getSession().setAttribute("currentUser", newPlayer);
            resp.sendRedirect(req.getContextPath() + UrlUtils.GAME);
        } else {
            req.setAttribute("error", "Invalid Information");
            req.getRequestDispatcher(JspUtils.DANG_KY).forward(req, resp);
        }
    }

}
