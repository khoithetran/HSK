package entity;

import java.util.Date;
import java.util.Objects;

public class CongTrinh {
	private String maCT;
	private String tenCT;
	private String diaDiem;
	private Date ngayCap;
	private Date ngayKhoiCong;
	private Date ngayHTDK;
	public CongTrinh(String maCT, String tenCT, String diaDiem, Date ngayCap, Date ngayKhoiCong, Date ngayHTDK) {
		super();
		this.maCT = maCT;
		this.tenCT = tenCT;
		this.diaDiem = diaDiem;
		this.ngayCap = ngayCap;
		this.ngayKhoiCong = ngayKhoiCong;
		this.ngayHTDK = ngayHTDK;
	}
	public CongTrinh() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		return Objects.hash(maCT);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CongTrinh other = (CongTrinh) obj;
		return Objects.equals(maCT, other.maCT);
	}
	public String getMaCT() {
		return maCT;
	}
	public void setMaCT(String maCT) {
		this.maCT = maCT;
	}
	public String getTenCT() {
		return tenCT;
	}
	public void setTenCT(String tenCT) {
		this.tenCT = tenCT;
	}
	public String getDiaDiem() {
		return diaDiem;
	}
	public void setDiaDiem(String diaDiem) {
		this.diaDiem = diaDiem;
	}
	public Date getNgayCap() {
		return ngayCap;
	}
	public void setNgayCap(Date ngayCap) {
		this.ngayCap = ngayCap;
	}
	public Date getNgayKhoiCong() {
		return ngayKhoiCong;
	}
	public void setNgayKhoiCong(Date ngayKhoiCong) {
		this.ngayKhoiCong = ngayKhoiCong;
	}
	public Date getNgayHTDK() {
		return ngayHTDK;
	}
	public void setNgayHTDK(Date ngayHTDK) {
		this.ngayHTDK = ngayHTDK;
	}

	
}
