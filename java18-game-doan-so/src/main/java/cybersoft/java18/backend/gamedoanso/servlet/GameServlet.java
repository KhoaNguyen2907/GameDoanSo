package cybersoft.java18.backend.gamedoanso.servlet;

import cybersoft.java18.backend.gamedoanso.utils.JspUtils;
import cybersoft.java18.backend.gamedoanso.utils.UrlUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {UrlUtils.GAME}, name = "gameServlet")
public class GameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       req.getRequestDispatcher(JspUtils.GAME).forward(req,resp);
    }
}
