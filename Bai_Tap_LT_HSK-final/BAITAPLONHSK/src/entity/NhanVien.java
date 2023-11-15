package entity;

import java.util.Date;
import java.util.Objects;

public class NhanVien {
	private String maNV;
	private String hoTen;
	private	Date ngaySinh;
	private String gioiTinh;
	private String diaChi;
	private String maPB;
	private String chucVu;
	@Override
	public int hashCode() {
		return Objects.hash(maNV);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(maNV, other.maNV);
	}
	public NhanVien(String maNV, String hoTen, Date ngaySinh, String gioiTinh, String diaChi, String maPB,
			String chucVu) {
		super();
		this.maNV = maNV;
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		this.maPB = maPB;
		this.chucVu = chucVu;
	}
	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
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
	public Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getmaPB() {
		return maPB;
	}
	public void setmaPB(String maPB) {
		this.maPB = maPB;
	}
	public String getChucVu() {
		return chucVu;
	}
	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}
	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", hoTen=" + hoTen + ", ngaySinh=" + ngaySinh + ", gioiTinh=" + gioiTinh
				+ ", diaChi=" + diaChi + ", maPB=" + maPB + ", chucVu=" + chucVu + "]";
	}
	
	
	
	
}
