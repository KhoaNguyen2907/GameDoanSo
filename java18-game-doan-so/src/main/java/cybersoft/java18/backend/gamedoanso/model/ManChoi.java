package cybersoft.java18.backend.gamedoanso.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class ManChoi {
	private static int soId = 1;
	private static Random random = null;
	private String id;
	private List<LanDoan> lanDoan;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private int soNgauNhien;
	private boolean hoanThanh;
	private NguoiChoi name;
	
	public ManChoi(String nguoiChoi) {
		this.id = "Game " + String.format("%05d", soId++);
		this.soNgauNhien = getRandomInt();
	}
	
	private int getRandomInt() {
		if (random == null) {
			random = new Random();
		}
		return random.nextInt(1000 - 1) +1;
	}
	
	public List<LanDoan> getLanDoan() {
		return lanDoan;
	}
	public void setLanDoan(List<LanDoan> lanDoan) {
		this.lanDoan = lanDoan;
	}
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	public LocalDateTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
	public int getSoNgauNhien() {
		return soNgauNhien;
	}
	public void setSoNgauNhien(int soNgauNhien) {
		this.soNgauNhien = soNgauNhien;
	}
	public boolean isHoanThanh() {
		return hoanThanh;
	}
	public void setHoanThanh(boolean hoanThanh) {
		this.hoanThanh = hoanThanh;
	}
	
	
}
