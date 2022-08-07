package cybersoft.java18.backend.gamedoanso.servlet;

import cybersoft.java18.backend.gamedoanso.model.GameSession;
import cybersoft.java18.backend.gamedoanso.model.Player;
import cybersoft.java18.backend.gamedoanso.service.GameService;
import cybersoft.java18.backend.gamedoanso.store.GameStoreHolder;
import cybersoft.java18.backend.gamedoanso.utils.JspUtils;
import cybersoft.java18.backend.gamedoanso.utils.UrlUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {UrlUtils.GAME, UrlUtils.XEP_HANG}, name = "gameServlet")
public class GameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case UrlUtils.GAME:
                Player currentUser = (Player) req.getSession().getAttribute("currentUser");
                GameSession currentGame = (GameSession) req.getSession().getAttribute("currentGame");
                if ( currentGame == null){
                    currentGame = GameService.getINSTANCE().createGame(currentUser.getUserName());
                }

                req.getRequestDispatcher(JspUtils.GAME).forward(req, resp);
                break;
            case UrlUtils.XEP_HANG:
                req.getRequestDispatcher(JspUtils.XEP_HANG).forward(req, resp);
                break;
            default:
                req.getRequestDispatcher(JspUtils.NOT_FOUND).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var soDoan = req.getParameter("number");
        switch (req.getServletPath()) {
            case UrlUtils.GAME:
                processGame(req,resp);
                break;
            default:
                req.getRequestDispatcher(JspUtils.NOT_FOUND).forward(req, resp);
        }
    }

    private void processGame(HttpServletRequest req, HttpServletResponse resp) {

    }
}
