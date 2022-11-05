package cybersoft.java18.backend.gamedoanso.store;

import java.util.ArrayList;
import java.util.List;

import cybersoft.java18.backend.gamedoanso.model.Guess;
import cybersoft.java18.backend.gamedoanso.model.GameSession;
import cybersoft.java18.backend.gamedoanso.model.Player;

public class GameStore {
    private static List<Player> playerList;
    private static List<GameSession> gameSessionList;
    private static List<Guess> guessList;

    public GameStore() {
        gameSessionList = new ArrayList<GameSession>();
        playerList = new ArrayList<Player>();
        guessList = new ArrayList<Guess>();

        playerList.add(new Player("admin", "admin", "Admin"));
    }

    public List<Player> getPlayerList() {

        return playerList;
    }

    public List<GameSession> getGameSessionList() {
        return gameSessionList;
    }

    public List<Guess> getGuessList() {

        return guessList;
    }


}
