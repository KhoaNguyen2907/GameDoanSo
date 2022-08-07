package cybersoft.java18.backend.gamedoanso.servlet;

import cybersoft.java18.backend.gamedoanso.model.NguoiChoi;
import cybersoft.java18.backend.gamedoanso.service.GameService;
import cybersoft.java18.backend.gamedoanso.utils.JspUtils;
import cybersoft.java18.backend.gamedoanso.utils.UrlUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {UrlUtils.DANG_NHAP, UrlUtils.DANG_KY}, name = "authServlet")
public class AuthServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case UrlUtils.DANG_NHAP:
                req.getRequestDispatcher(JspUtils.DANG_NHAP).forward(req, resp);
                break;

            case UrlUtils.DANG_KY:
                req.getRequestDispatcher(JspUtils.DANG_KY).forward(req, resp);
                break;
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
        NguoiChoi newPlayer = GameService.getINSTANCE().dangNhap(userName, password);
        if (newPlayer != null) {
            resp.sendRedirect(req.getContextPath() + UrlUtils.GAME);
        } else {
            req.setAttribute("error", "Wrong password");
            req.getRequestDispatcher(JspUtils.DANG_NHAP).forward(req, resp);
        }
    }

    private void processRegister(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        String fullName = req.getParameter("fullname");
        NguoiChoi newPlayer = GameService.getINSTANCE().dangKy(userName, password, fullName);
        if (newPlayer != null) {
            req.getSession().setAttribute("currentUser", newPlayer);
            resp.sendRedirect(req.getContextPath() + UrlUtils.GAME);
        } else {
            req.setAttribute("error", "Invalid Information");
            req.getRequestDispatcher(JspUtils.DANG_KY).forward(req, resp);
        }
    }

}
