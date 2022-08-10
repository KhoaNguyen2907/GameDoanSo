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

@WebServlet(urlPatterns = {UrlUtils.GAME, UrlUtils.XEP_HANG, UrlUtils.NEW_GAME},
        name = "gameServlet")
public class GameServlet extends HttpServlet {
    private GameService gameService;

    @Override
    public void init() throws ServletException {
        super.init();
        gameService = GameService.getINSTANCE();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case UrlUtils.GAME:
                Player currentUser = (Player) req.getSession().getAttribute("currentUser");
                GameSession currentGame = gameService.getCurrentGame(currentUser.getUserName());
                req.getSession().setAttribute("game", currentGame);
                req.setAttribute("guessList",gameService.getGuessListByGameId(currentGame.getId()));
                req.getRequestDispatcher(JspUtils.GAME).forward(req, resp);
                break;
            case UrlUtils.XEP_HANG:
                req.getRequestDispatcher(JspUtils.XEP_HANG).forward(req, resp);
                break;
            case UrlUtils.NEW_GAME:
                currentUser = (Player) req.getSession().getAttribute("currentUser");
                currentGame = gameService.createGame(currentUser.getUserName());
                req.getSession().setAttribute("game", currentGame);
                req.getRequestDispatcher(JspUtils.GAME).forward(req, resp);
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
                Player currentUser = (Player) req.getSession().getAttribute("currentUser");
                GameSession currentGame = (GameSession) req.getSession().getAttribute("game");
                if (currentGame.isCompleted()){
                    req.setAttribute("completedMess","Bạn đã hoàn thành trò chơi. Hãy tạo game mới.");
                    req.setAttribute("guessList",gameService.getGuessListByGameId(currentGame.getId()));
                } else {
                    processGame(req, resp, currentUser, currentGame);
                }
                req.getRequestDispatcher(JspUtils.GAME).forward(req, resp);
                break;
            default:
                req.getRequestDispatcher(JspUtils.NOT_FOUND).forward(req, resp);
        }
    }

    private void processGame(HttpServletRequest req, HttpServletResponse resp, Player currentUser, GameSession currentGame) {
        int number = Integer.parseInt(req.getParameter("number"));

        if (gameService.checkNumber(number,currentUser.getUserName()) == 1){
            currentGame = gameService.addGuess(number, 1,currentGame);

        } else if (gameService.checkNumber(number, currentUser.getUserName()) == -1){
            currentGame = gameService.addGuess(number,-1,currentGame);
        } else {
            currentGame = gameService.addGuess(number,0,currentGame);
            currentGame.setCompleted(true);
            currentGame.setActive(false);
        }
        req.getSession().setAttribute("game", currentGame);
        req.setAttribute("guessList",gameService.getGuessListByGameId(currentGame.getId()));
    }
}
