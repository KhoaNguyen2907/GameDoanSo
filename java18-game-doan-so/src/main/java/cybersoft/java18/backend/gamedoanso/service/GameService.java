package cybersoft.java18.backend.gamedoanso.service;

import java.util.List;

import cybersoft.java18.backend.gamedoanso.model.GameSession;
import cybersoft.java18.backend.gamedoanso.model.Guess;
import cybersoft.java18.backend.gamedoanso.model.Player;
import cybersoft.java18.backend.gamedoanso.repository.GameSessionRepository;
import cybersoft.java18.backend.gamedoanso.repository.GuessRepository;
import cybersoft.java18.backend.gamedoanso.repository.PlayerRepository;
import cybersoft.java18.backend.gamedoanso.store.GameStore;
import cybersoft.java18.backend.gamedoanso.store.GameStoreHolder;

public class GameService {
    private static GameService INSTANCE;
    PlayerRepository playerRepository = new PlayerRepository();
    GameSessionRepository gameSessionRepository = new GameSessionRepository();
    GuessRepository guessRepository = new GuessRepository();

    public static GameService getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new GameService();
        }
        return INSTANCE;
    }

    private final GameStore store = GameStoreHolder.getStore();

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
        List<Guess> guessList = (List<Guess>) guessRepository.findGuessListByIdGame(activeGame.getId());

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
        return playerRepository.save(userName, password, name);
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
        gameSessionRepository.findGamesByUserName(userName).stream()
                .filter(gs -> gs.isActive())
                .forEach(gs -> gs.setActive(false));
    }

    public List<GameSession> xepHang() {
        return null;

    }

    public GameSession doanSo(GameSession gameSession, int so) {

        return gameSession;
    }


    public int checkNumber(int number, String userName) {
       int targetNumber = gameSessionRepository.findGamesByUserName(userName)
                .stream().filter(gs -> gs.isActive())
                .findFirst().orElseThrow(NullPointerException::new).getTargetNumber();
        if (number > targetNumber ){
            return 1;
        } else if (number < targetNumber) {
            return -1;
        } else {
            return 0;
        }
    }

    public GameSession addGuess(int number, int resultId, GameSession currentGame) {
        guessRepository.getGuessList().add(new Guess(number,resultId,currentGame));
        return currentGame;
    }

    public List<Guess> getGuessListByGameId(String gameId){
        return (List<Guess>) guessRepository.findGuessListByIdGame(gameId);
    }
}
