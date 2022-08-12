package cybersoft.java18.backend.gamedoanso.servlet;

import cybersoft.java18.backend.gamedoanso.Game;
import cybersoft.java18.backend.gamedoanso.model.GameSession;
import cybersoft.java18.backend.gamedoanso.model.Guess;
import cybersoft.java18.backend.gamedoanso.model.Player;
import cybersoft.java18.backend.gamedoanso.model.TopRank;
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
import java.util.List;

@WebServlet(urlPatterns = {UrlUtils.GAME, UrlUtils.XEP_HANG, UrlUtils.NEW_GAME, UrlUtils.DANH_SACH_GAME},
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
                String id = req.getParameter("id");
                if (id != null) {
                    gameService.setActiveGameById(id,currentUser.getUserName());
                }
                GameSession currentGame = gameService.getCurrentGame(currentUser.getUserName());
                req.getSession().setAttribute("game", currentGame);
                req.setAttribute("guessList",gameService.getGuessListByGameId(currentGame.getId()));
                req.getRequestDispatcher(JspUtils.GAME).forward(req, resp);
                break;
            case UrlUtils.XEP_HANG:
                List<TopRank> topList = gameService.getTopList(10);
                req.setAttribute("topList",topList);
                req.getRequestDispatcher(JspUtils.XEP_HANG).forward(req, resp);
                break;
            case UrlUtils.NEW_GAME:
                currentUser = (Player) req.getSession().getAttribute("currentUser");
                currentGame = gameService.createGame(currentUser.getUserName());
                req.getSession().setAttribute("game", currentGame);
                req.getRequestDispatcher(JspUtils.GAME).forward(req, resp);
                break;
            case UrlUtils.DANH_SACH_GAME:
                currentUser = (Player) req.getSession().getAttribute("currentUser");
                List<GameSession> gameList = gameService.getGameListByUsername(currentUser.getUserName());
                gameList.forEach(g->g.getGuessList()
                        .addAll(gameService.getGuessListByGameId(g.getId())));
                req.getSession().setAttribute("gameList", gameList);
                req.getRequestDispatcher(JspUtils.DANH_SACH_GAME).forward(req, resp);
                break;
            default:
                req.getRequestDispatcher(JspUtils.NOT_FOUND).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

        if (number > currentGame.getTargetNumber()){
            currentGame = gameService.addGuess(number, "greater",currentGame);

        } else if (number < currentGame.getTargetNumber()){
            currentGame = gameService.addGuess(number,"smaller",currentGame);
        } else {
            currentGame = gameService.addGuess(number,"correct",currentGame);
            gameService.setCompletedGame(currentGame);
        }
        req.getSession().setAttribute("game", currentGame);
        req.setAttribute("guessList",gameService.getGuessListByGameId(currentGame.getId()));
    }
}
