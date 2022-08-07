package cybersoft.java18.backend.gamedoanso.repository;

import cybersoft.java18.backend.gamedoanso.model.Player;
import cybersoft.java18.backend.gamedoanso.store.GameStore;
import cybersoft.java18.backend.gamedoanso.store.GameStoreHolder;

public class PlayerRepository {
    private final GameStore store = GameStoreHolder.getStore();

    public Player findByUserName(String userName) {
        return store.getDsNguoiChoi().stream().filter(
                player -> player.getUserName().equals(userName))
                .findFirst().orElse(null);
    }

    public Player save(String userName, String password, String name) {
        if (isValid(userName, password, name)) {
            boolean isExisted = store.getDsNguoiChoi().stream().anyMatch(player -> player.getUserName().equals(userName));
            if (!isExisted) {
                Player player = new Player(userName, password, name);
                store.getDsNguoiChoi().add(player);
                return player;
            }
        }
        return null;
    }

    private boolean isValid(String userName, String password, String name) {
        if (userName == null || password == null || password == null
                || "".equals(userName) || "".equals(password) || "".equals(name)) {
            return false;
        }
        return true;
    }
}
