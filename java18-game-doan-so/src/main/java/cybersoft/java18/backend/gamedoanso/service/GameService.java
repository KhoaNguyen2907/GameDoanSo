package cybersoft.java18.backend.gamedoanso.service;

import java.util.List;

import cybersoft.java18.backend.gamedoanso.model.GameSession;
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
	public static GameService getINSTANCE(){
		if (INSTANCE == null){
			INSTANCE = new GameService();
		}
		return INSTANCE;
	}

	private final GameStore store = GameStoreHolder.getStore();

	public Player dangNhap(String userName, String password) {
		Player player = playerRepository.findByUserName(userName);
		if (player == null){
			return null;
		}
		if (player.getPassword().equals(password)){
			return player;
		}
		return null;
	}
	
	public Player dangKy(String userName, String password, String name) {
		return playerRepository.save(userName, password, name);
	}

	public GameSession createGame(String userName) {
		var gameSession = new GameSession(userName);
		store.getDsManChoi().add(gameSession);
		return gameSession;
	}

	public List<GameSession> xepHang() {
		return null;

	}

	public GameSession doanSo(GameSession gameSession, int so) {

		return gameSession;
	}



}
