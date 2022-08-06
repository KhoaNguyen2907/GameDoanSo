package cybersoft.java18.backend.gamedoanso.store;

import java.util.ArrayList;
import java.util.List;

import cybersoft.java18.backend.gamedoanso.model.LanDoan;
import cybersoft.java18.backend.gamedoanso.model.ManChoi;
import cybersoft.java18.backend.gamedoanso.model.NguoiChoi;

public class GameStore {
private static List<NguoiChoi> dsNguoiChoi;
private static List<ManChoi> dsManChoi;
private static List<LanDoan> dsLanDoan;

public GameStore() {
	dsManChoi = new ArrayList<ManChoi>();
	dsNguoiChoi = new ArrayList<NguoiChoi>();
	dsLanDoan = new ArrayList<LanDoan>();
}

public List<NguoiChoi> getDsNguoiChoi() {
	return dsNguoiChoi;
}
public void setDsNguoiChoi(List<NguoiChoi> dsNguoiChoi) {
	this.dsNguoiChoi = dsNguoiChoi;
}
public List<ManChoi> getDsManChoi() {
	return dsManChoi;
}
public void setDsManChoi(List<ManChoi> dsManChoi) {
	this.dsManChoi = dsManChoi;
}
public List<LanDoan> getDsLanDoan() {
	return dsLanDoan;
}
public void setDsLanDoan(List<LanDoan> dsLanDoan) {
	this.dsLanDoan = dsLanDoan;
}


}
