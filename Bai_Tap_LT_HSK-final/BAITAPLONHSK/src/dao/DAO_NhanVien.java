package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import database.DataBase;
import entity.NhanVien;

public class DAO_NhanVien implements DAO_Interface<NhanVien> {
	ArrayList<NhanVien> dsNV;
	NhanVien nv;
	
	public DAO_NhanVien() {
		dsNV = new ArrayList<NhanVien>();
		nv = new NhanVien();
	}

	public ArrayList<NhanVien> docTuBang(){
		try {
			Connection con = DataBase.getInstance().getConnection();
			String sql = "SELECT *  FROM NhanVien";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maNV = rs.getString("MaNV");
	            String tenNV = rs.getString("HoTen");
	            Date ngaySinh = rs.getDate("NgaySinh");
	            String gioiTinh = rs.getString("GioiTinh");
	            String maPB = rs.getString("MaPB");
	            String diaChi = rs.getString("DiaChi");
	            String chucVu = rs.getString("ChucVu");

	            NhanVien nv = new NhanVien(maNV, tenNV, ngaySinh, gioiTinh, diaChi, maPB, chucVu);
				dsNV.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsNV;
	}
	
	public ArrayList<NhanVien> docTuProcedure() {
	    ArrayList<NhanVien> dsNV = new ArrayList<>();

	    try (Connection connection = DataBase.getInstance().getConnection();
	         CallableStatement callableStatement = connection.prepareCall("{call DocNhanVien}");
	         ResultSet rs = callableStatement.executeQuery()) {

	        while (rs.next()) {
	            String maNV = rs.getString("MaNV");
	            String tenNV = rs.getString("HoTen");
	            Date ngaySinh = rs.getDate("NgaySinh");
	            String gioiTinh = rs.getString("GioiTinh");
	            String diaChi = rs.getString("DiaChi");
	            String maPB = rs.getString("MaPB");
	            String chucVu = rs.getString("ChucVu");

	            NhanVien nv = new NhanVien(maNV, tenNV, ngaySinh, gioiTinh, diaChi, maPB, chucVu);
				dsNV.add(nv);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dsNV;
	}
	
	@Override
	public int them(NhanVien nhanVien) {
	    int result = 0;

	    try (Connection connection = DataBase.getInstance().getConnection();
	         CallableStatement callableStatement = connection.prepareCall("{call ThemNhanVien(?, ?, ?, ?, ?, ?,?)}")) {

	        callableStatement.setString(1, nhanVien.getMaNV());
	        callableStatement.setString(2, nhanVien.getHoTen());
	        callableStatement.setDate(3, new java.sql.Date(nhanVien.getNgaySinh().getTime()));
	        callableStatement.setString(4, nhanVien.getGioiTinh());
	        callableStatement.setString(5, nhanVien.getDiaChi());
	        callableStatement.setString(6, nhanVien.getmaPB());
	        callableStatement.setString(7, nhanVien.getChucVu());

	        result = callableStatement.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return result;
	}


	@Override
	public int sua(NhanVien nhanVien) {
	    int result = 0;

	    try (Connection connection = DataBase.getInstance().getConnection();
	         CallableStatement callableStatement = connection.prepareCall("{call SuaNhanVien(?, ?, ?, ?, ?, ?, ?)}")) {

	        callableStatement.setString(1, nhanVien.getMaNV());
	        callableStatement.setString(2, nhanVien.getHoTen());
	        callableStatement.setDate(3, new java.sql.Date(nhanVien.getNgaySinh().getTime()));
	        callableStatement.setString(4, nhanVien.getGioiTinh());
	        callableStatement.setString(5, nhanVien.getDiaChi());
	        callableStatement.setString(6, nhanVien.getmaPB());
	        callableStatement.setString(7, nhanVien.getChucVu());

	        result = callableStatement.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return result;
	}


	@Override
	public int xoa(NhanVien nhanVien) {
	    int result = 0;

	    try (Connection connection = DataBase.getInstance().getConnection();
	         CallableStatement callableStatement = connection.prepareCall("{call XoaNhanVien(?)}")) {

	        callableStatement.setString(1, nhanVien.getMaNV());

	        result = callableStatement.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return result;
	}


	@Override
	public ArrayList<NhanVien> lietKe(NhanVien t) {
	    ArrayList<NhanVien> danhSachNhanVien = new ArrayList<>();

	    try (Connection connection = DataBase.getInstance().getConnection();
	         CallableStatement callableStatement = connection.prepareCall("{call LietKeNhanVien}")) {

	        ResultSet resultSet = callableStatement.executeQuery();

	        while (resultSet.next()) {
	            String maNV = resultSet.getString("maNV");
	            String hoTen = resultSet.getString("hoTen");
	            Date ngaySinh = resultSet.getDate("ngaySinh");
	            String gioiTinh = resultSet.getString("gioiTinh");
	            String diaChi = resultSet.getString("diaChi");
	            String maPB = resultSet.getString("maPB");
	            String chucVu = resultSet.getString("chucVu");

	            NhanVien nv = new NhanVien(maNV, hoTen, ngaySinh, gioiTinh, diaChi, maPB, chucVu);
	            danhSachNhanVien.add(nv);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return danhSachNhanVien;
	}


	@Override
	public NhanVien timKiem(NhanVien t) {
	    NhanVien nhanVien = null;

	    try (Connection connection = DataBase.getInstance().getConnection();
	         CallableStatement callableStatement = connection.prepareCall("{call TimKiemNhanVien(?)}")) {

	        callableStatement.setString(1, t.getMaNV());
	        ResultSet resultSet = callableStatement.executeQuery();

	        if (resultSet.next()) {
	            String maNV = resultSet.getString("maNV");
	            String hoTen = resultSet.getString("hoTen");
	            Date ngaySinh = resultSet.getDate("ngaySinh");
	            String gioiTinh = resultSet.getString("gioiTinh");
	            String diaChi = resultSet.getString("diaChi");
	            String maPB = resultSet.getString("maPB");
	            String chucVu = resultSet.getString("chucVu");

	            nhanVien = new NhanVien(maNV, hoTen, ngaySinh, gioiTinh, diaChi, maPB, chucVu);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return nhanVien;
	}

	
}
