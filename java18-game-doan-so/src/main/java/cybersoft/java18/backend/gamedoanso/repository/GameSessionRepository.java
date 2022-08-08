package cybersoft.java18.backend.gamedoanso.repository;

import cybersoft.java18.backend.gamedoanso.model.GameSession;
import cybersoft.java18.backend.gamedoanso.store.GameStore;
import cybersoft.java18.backend.gamedoanso.store.GameStoreHolder;

import java.util.List;
import java.util.stream.Collectors;

public class GameSessionRepository {
    private final GameStore store;

    public GameSessionRepository() {
        store = GameStoreHolder.getStore();
    }

    public List<GameSession> findGamesByUserName(String userName) {
        return store.getGameSessionList().stream()
                .filter(gs -> gs.getUserName().equals(userName))
                .collect(Collectors.toList());
    }

    public void save(GameSession gameSession) {
        store.getGameSessionList().add(gameSession);
    }
}
