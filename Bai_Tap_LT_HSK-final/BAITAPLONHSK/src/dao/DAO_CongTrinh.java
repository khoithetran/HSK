package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import database.DataBase;
import entity.CongTrinh;

public class DAO_CongTrinh implements DAO_Interface<CongTrinh> {

	@Override
	public int them(CongTrinh congTrinh) {
	    int result = 0;

	    try (Connection connection = DataBase.getInstance().getConnection();
	         CallableStatement callableStatement = connection.prepareCall("{call ThemCongTrinh(?, ?, ?, ?, ?, ?)}")) {

	        callableStatement.setString(1, congTrinh.getMaCT());
	        callableStatement.setString(2, congTrinh.getTenCT());
	        callableStatement.setString(3, congTrinh.getDiaDiem());
	        callableStatement.setDate(4, new java.sql.Date(congTrinh.getNgayCap().getTime()));
	        callableStatement.setDate(5, new java.sql.Date(congTrinh.getNgayKhoiCong().getTime()));
	        callableStatement.setDate(6, new java.sql.Date(congTrinh.getNgayHTDK().getTime()));

	        result = callableStatement.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return result;
	}


	@Override
	public int sua(CongTrinh congTrinh) {
	    int result = 0;

	    try (Connection connection = DataBase.getInstance().getConnection();
	         CallableStatement callableStatement = connection.prepareCall("{call SuaCongTrinh(?, ?, ?, ?, ?, ?)}")) {

	        callableStatement.setString(1, congTrinh.getMaCT());
	        callableStatement.setString(2, congTrinh.getTenCT());
	        callableStatement.setString(3, congTrinh.getDiaDiem());
	        callableStatement.setDate(4, new java.sql.Date(congTrinh.getNgayCap().getTime()));
	        callableStatement.setDate(5, new java.sql.Date(congTrinh.getNgayKhoiCong().getTime()));
	        callableStatement.setDate(6, new java.sql.Date(congTrinh.getNgayHTDK().getTime()));

	        result = callableStatement.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return result;
	}


	@Override
	public int xoa(CongTrinh congTrinh) {
	    int result = 0;

	    try (Connection connection = DataBase.getInstance().getConnection();
	         CallableStatement callableStatement = connection.prepareCall("{call XoaCongTrinh(?)}")) {

	        callableStatement.setString(1, congTrinh.getMaCT());

	        result = callableStatement.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return result;
	}


	@Override
	public ArrayList<CongTrinh> lietKe(CongTrinh t) {
	    ArrayList<CongTrinh> danhSachCongTrinh = new ArrayList<>();

	    try (Connection connection = DataBase.getInstance().getConnection();
	         CallableStatement callableStatement = connection.prepareCall("{call LietKeCongTrinh}")) {

	        ResultSet resultSet = callableStatement.executeQuery();

	        while (resultSet.next()) {
	            String maCT = resultSet.getString("maCT");
	            String tenCT = resultSet.getString("tenCT");
	            String diaDiem = resultSet.getString("diaDiem");
	            Date ngayCap = resultSet.getDate("ngayCap");
	            Date ngayKhoiCong = resultSet.getDate("ngayKhoiCong");
	            Date ngayHTDK = resultSet.getDate("ngayHTDK");

	            CongTrinh congTrinh = new CongTrinh(maCT, tenCT, diaDiem, ngayCap, ngayKhoiCong, ngayHTDK);
	            danhSachCongTrinh.add(congTrinh);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return danhSachCongTrinh;
	}

	@Override
	public CongTrinh timKiem(CongTrinh congTrinh) {
	    CongTrinh ketQua = null;

	    try (Connection connection = DataBase.getInstance().getConnection();
	         CallableStatement callableStatement = connection.prepareCall("{call TimKiemCongTrinh(?)}")) {

	        callableStatement.setString(1, congTrinh.getMaCT());
	        ResultSet resultSet = callableStatement.executeQuery();

	        if (resultSet.next()) {
	            String maCT = resultSet.getString("maCT");
	            String tenCT = resultSet.getString("tenCT");
	            String diaDiem = resultSet.getString("diaDiem");
	            Date ngayCap = resultSet.getDate("ngayCap");
	            Date ngayKhoiCong = resultSet.getDate("ngayKhoiCong");
	            Date ngayHTDK = resultSet.getDate("ngayHTDK");

	            ketQua = new CongTrinh(maCT, tenCT, diaDiem, ngayCap, ngayKhoiCong, ngayHTDK);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return ketQua;
	}


}
