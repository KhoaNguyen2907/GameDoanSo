package cybersoft.java18.backend.gamedoanso.model;

public class LanDoan {
	private final int soDoan;
	private String ketQua;

	public LanDoan(int soDoan) {
		this.soDoan = soDoan;
	}

	public String getKetQua() {
		return ketQua;
	}

	public void setKetQua(String ketQua) {
		this.ketQua = ketQua;
	}

	public int getSoDoan() {
		return soDoan;
	}

}
