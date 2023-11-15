package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public ArrayList<NhanVien> docTuProcedure() {
        ArrayList<NhanVien> dsNV = new ArrayList<>();

        try (Connection connection = DataBase.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM NhanVien")) {

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String maNV = rs.getString("MaNV");
                String tenNV = rs.getString("HoTen");
                Date ngaySinh = rs.getDate("NgaySinh");
                String gioiTinh = rs.getString("GioiTinh");
                String diaChi = rs.getString("DiaChi");
                String maPB = rs.getString("PhongBan");
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
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO NhanVien (MaNV, HoTen, NgaySinh, GioiTinh, DiaChi, PhongBan, ChucVu) VALUES (?, ?, ?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, nhanVien.getMaNV());
            preparedStatement.setString(2, nhanVien.getHoTen());
            preparedStatement.setDate(3, new java.sql.Date(nhanVien.getNgaySinh().getTime()));
            preparedStatement.setString(4, nhanVien.getGioiTinh());
            preparedStatement.setString(5, nhanVien.getDiaChi());
            preparedStatement.setString(6, nhanVien.getmaPB());
            preparedStatement.setString(7, nhanVien.getChucVu());

            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int sua(NhanVien nhanVien) {
        int result = 0;

        try (Connection connection = DataBase.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "UPDATE NhanVien SET HoTen=?, NgaySinh=?, GioiTinh=?, DiaChi=?, PhongBan=?, ChucVu=? WHERE MaNV=?")) {

            preparedStatement.setString(1, nhanVien.getHoTen());
            preparedStatement.setDate(2, new java.sql.Date(nhanVien.getNgaySinh().getTime()));
            preparedStatement.setString(3, nhanVien.getGioiTinh());
            preparedStatement.setString(4, nhanVien.getDiaChi());
            preparedStatement.setString(5, nhanVien.getmaPB());
            preparedStatement.setString(6, nhanVien.getChucVu());
            preparedStatement.setString(7, nhanVien.getMaNV());

            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int xoa(NhanVien nhanVien) {
        int result = 0;

        try (Connection connection = DataBase.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM NhanVien WHERE MaNV=?")) {

            preparedStatement.setString(1, nhanVien.getMaNV());

            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<NhanVien> lietKe(NhanVien t) {
        ArrayList<NhanVien> danhSachNhanVien = new ArrayList<>();
        try (Connection connection = DataBase.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM NhanVien")) {

            ResultSet resultSet = preparedStatement.executeQuery();
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
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM NhanVien WHERE MaNV=?")) {

            preparedStatement.setString(1, t.getMaNV());
            ResultSet resultSet = preparedStatement.executeQuery();

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
