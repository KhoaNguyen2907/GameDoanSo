package cybersoft.java18.backend.gamedoanso.model;

import java.util.List;

public class NguoiChoi {
	private String userName;
	private String password;
	private List<ManChoi> manChoi;
	private String name;
	
	
	
	public NguoiChoi(String userName, String password, String name) {
		super();
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
	
	public List<ManChoi> getManChoi() {
		return manChoi;
	}
	
	public String getName() {
		return name;
	}
	
	
}
