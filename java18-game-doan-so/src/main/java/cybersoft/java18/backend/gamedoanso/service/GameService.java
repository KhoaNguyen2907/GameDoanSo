package cybersoft.java18.backend.gamedoanso.service;

import java.util.List;

import cybersoft.java18.backend.gamedoanso.model.ManChoi;
import cybersoft.java18.backend.gamedoanso.model.NguoiChoi;
import cybersoft.java18.backend.gamedoanso.store.GameStore;
import cybersoft.java18.backend.gamedoanso.store.GameStoreHolder;

public class GameService {
	private static GameService INSTANCE;
	public static GameService getINSTANCE(){
		if (INSTANCE == null){
			INSTANCE = new GameService();
		}
		return INSTANCE;
	}

	GameStore store = GameStoreHolder.getStore();

	public NguoiChoi dangNhap(String userName, String password) {
		return store.getDsNguoiChoi().stream().filter(
				nguoiChoi -> nguoiChoi.getUserName().equals(userName) && nguoiChoi.getPassword().equals(password))
				.findFirst().orElse(null);

	}
	
	public NguoiChoi dangKy(String userName, String password, String name) {
		if (isValid(userName, password, name)) {
			boolean isExisted = store.getDsNguoiChoi().stream().anyMatch(nguoiChoi -> nguoiChoi.getUserName().equals(userName));
			if (!isExisted) {
				NguoiChoi nguoiChoi = new NguoiChoi(userName, password, name);
				store.getDsNguoiChoi().add(nguoiChoi);
				return nguoiChoi;
			}
		}
		return null;		
	}
	
	private boolean isValid(String userName, String password, String name) {
		if (userName == null || password == null|| password == null 
				|| "".equals(userName) || "".equals(password)|| "".equals(name)) {
			return false;
		}
		return true;
	}

	public ManChoi startGame(String userName) {
		return null;

	}

	public List<ManChoi> xepHang() {
		return null;

	}

	public ManChoi doanSo(ManChoi manChoi, int so) {
		return manChoi;

	}
}
