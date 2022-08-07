package cybersoft.java18.backend.gamedoanso.store;

import java.util.ArrayList;
import java.util.List;

import cybersoft.java18.backend.gamedoanso.model.Guess;
import cybersoft.java18.backend.gamedoanso.model.GameSession;
import cybersoft.java18.backend.gamedoanso.model.Player;

public class GameStore {
private static List<Player> dsPlayer;
private static List<GameSession> dsGameSession;
private static List<Guess> dsLanDoan;

public GameStore() {
	dsGameSession = new ArrayList<GameSession>();
	dsPlayer = new ArrayList<Player>();
	dsLanDoan = new ArrayList<Guess>();

	dsPlayer.add(new Player("admin","admin","Admin"));
}

public List<Player> getDsNguoiChoi() {
	return dsPlayer;
}
public void setDsNguoiChoi(List<Player> dsPlayer) {
	this.dsPlayer = dsPlayer;
}
public List<GameSession> getDsManChoi() {
	return dsGameSession;
}
public void setDsManChoi(List<GameSession> dsGameSession) {
	this.dsGameSession = dsGameSession;
}
public List<Guess> getDsLanDoan() {
	return dsLanDoan;
}
public void setDsLanDoan(List<Guess> dsLanDoan) {
	this.dsLanDoan = dsLanDoan;
}


}
