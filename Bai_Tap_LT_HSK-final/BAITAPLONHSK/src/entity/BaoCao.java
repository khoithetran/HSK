package entity;

public class BaoCao {
    private String maNV;
    private String hoTen;
    private String tenCT;
    private int soNgayCong;

    public BaoCao(String maNV, String hoTen, String tenCT, int soNgayCong) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.tenCT = tenCT;
        this.soNgayCong = soNgayCong;
    }

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getTenCT() {
		return tenCT;
	}

	public void setTenCT(String tenCT) {
		this.tenCT = tenCT;
	}

	public int getSoNgayCong() {
		return soNgayCong;
	}

	public void setSoNgayCong(int soNgayCong) {
		this.soNgayCong = soNgayCong;
	}
    
}
