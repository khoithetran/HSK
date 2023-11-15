package entity;

public class ChamCong {
	private String maNV;
	private String maCT;
	private int soNgayCong;
	public ChamCong(String maNV, String maCT, int soNgayCong) {
		super();
		this.maNV = maNV;
		this.maCT = maCT;
		this.soNgayCong = soNgayCong;
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getMaCT() {
		return maCT;
	}
	public void setMaCT(String maCT) {
		this.maCT = maCT;
	}
	public int getSoNgayCong() {
		return soNgayCong;
	}
	public void setSoNgayCong(int soNgayCong) {
		this.soNgayCong = soNgayCong;
	}
}
