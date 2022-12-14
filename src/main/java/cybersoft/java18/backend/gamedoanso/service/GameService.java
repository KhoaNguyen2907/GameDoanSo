package cybersoft.java18.backend.gamedoanso.service;

import cybersoft.java18.backend.gamedoanso.Game;
import cybersoft.java18.backend.gamedoanso.model.GameSession;
import cybersoft.java18.backend.gamedoanso.model.Guess;
import cybersoft.java18.backend.gamedoanso.model.Player;
import cybersoft.java18.backend.gamedoanso.model.TopRank;
import cybersoft.java18.backend.gamedoanso.repository.GameSessionRepository;
import cybersoft.java18.backend.gamedoanso.repository.GuessRepository;
import cybersoft.java18.backend.gamedoanso.repository.PlayerRepository;
import cybersoft.java18.backend.gamedoanso.repository.TopRankRepository;
import cybersoft.java18.backend.gamedoanso.store.GameStore;
import cybersoft.java18.backend.gamedoanso.store.GameStoreHolder;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public class GameService {
    private static GameService INSTANCE;
    private final GameStore store = GameStoreHolder.getStore();
    PlayerRepository playerRepository = new PlayerRepository();
    GameSessionRepository gameSessionRepository = new GameSessionRepository();
    GuessRepository guessRepository = new GuessRepository();
    TopRankRepository topRankRepository = new TopRankRepository();

    public static GameService getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new GameService();
        }
        return INSTANCE;
    }

    public GameSession getCurrentGame(String userName) {
        //Get list of game session by username.
        List<GameSession> gameList = gameSessionRepository.findGamesByUserName(userName);

        // Return current active game, if there's no game, create new one.
        GameSession activeGame = null;
        if (gameList.isEmpty()) {
            activeGame = createGame(userName);
        } else {
            activeGame = gameList.stream()
                    .filter(g -> g.isActive())
                    .findFirst().orElse(null);
            if (activeGame == null) {
                activeGame = createGame(userName);
            }
        }
        //Get guess list and add to game.
        List<Guess> guessList =  guessRepository.findGuessListByIdGame(activeGame.getId());

        if (guessList.isEmpty()) {
            return activeGame;
        }
        activeGame.getGuessList().addAll(guessList);

        return activeGame;
    }

    public Player login(String userName, String password) {
        Player player = playerRepository.findByUserName(userName);
        if (player == null) {
            return null;
        }
        if (player.getPassword().equals(password)) {
            return player;
        }
        return null;
    }

    public Player register(String userName, String password, String name) {
        playerRepository.save(userName, password, name);
        return playerRepository.findByUserName(userName);
    }

    public GameSession createGame(String userName) {
        // Deactive other games first;
        deactiveOtherGames(userName);
        // Crate game;
        GameSession gameSession = new GameSession(userName);
        // Then active current game.
        gameSession.setActive(true);
        gameSessionRepository.save(gameSession);
        return gameSession;
    }

    private void deactiveOtherGames(String userName) {
        List<GameSession> gameList = gameSessionRepository.findGamesByUserName(userName);
        gameList.forEach(gs -> gs.setActive(false));
        gameList.forEach(gs -> gameSessionRepository.update(gs));
    }

    public void setActiveGameById(String id, String username){
        deactiveOtherGames(username);
        GameSession game = gameSessionRepository.findGameById(id);
        game.setActive(true);
        gameSessionRepository.update(game);
    }

    public GameSession addGuess(int number, String result, GameSession currentGame) {
        // Create guess (timestamp's already init in constructor).
        Guess guess = new Guess(number, result, currentGame);
        guessRepository.addGuess(guess);
        return currentGame;
    }

    public List<Guess> getGuessListByGameId(String gameId) {
        return guessRepository.findGuessListByIdGame(gameId);
    }

    public void setCompletedGame(GameSession currentGame) {
        currentGame.setCompleted(true);
        currentGame.setEndTime(LocalDateTime.now());
        gameSessionRepository.update(currentGame);
    }

    public List<TopRank> getTopList(int amount) {
        return topRankRepository.getTopRankList(amount);
    }

    public List<GameSession> getGameListByUsername(String userName) {
        return gameSessionRepository.findGamesByUserName(userName);
    }

    public List<GameSession> getGameList() {
        return gameSessionRepository.findAllGames();
    }
}
