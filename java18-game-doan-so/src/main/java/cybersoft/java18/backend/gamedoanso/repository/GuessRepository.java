package cybersoft.java18.backend.gamedoanso.repository;

import cybersoft.java18.backend.gamedoanso.model.Guess;
import cybersoft.java18.backend.gamedoanso.store.GameStore;
import cybersoft.java18.backend.gamedoanso.store.GameStoreHolder;

import java.util.Collection;
import java.util.stream.Collectors;

public class GuessRepository {
    private final GameStore store;

    public GuessRepository() {
        store = GameStoreHolder.getStore();
    }


    public Collection<? extends Guess> findAllGuessByIdGame(String gameSessionId) {
        return store.getGuessList().stream()
                .filter(g -> g.getGameSessionId().equals(gameSessionId))
                .collect(Collectors.toList());
    }

}
