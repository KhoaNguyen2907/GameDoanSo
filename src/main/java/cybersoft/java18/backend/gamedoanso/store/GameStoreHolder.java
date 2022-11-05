package cybersoft.java18.backend.gamedoanso.store;

public class GameStoreHolder {
    private static GameStore store;

    public static GameStore getStore() {
        if (store == null) {
            store = new GameStore();
        }
        return store;
    }
}
