package cybersoft.java18.backend.gamedoanso.model;

import java.util.List;

public class Player {
	private String userName;
	private String password;
	private List<GameSession> gameSession;
	private String name;
	
	
	
	public Player(String userName, String password, String name) {
		this.userName = userName;
		this.password = password;
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public List<GameSession> getGameSessionList() {
		return gameSession;
	}
	
	public String getName() {
		return name;
	}
	
	
}
