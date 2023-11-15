package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DataBase;
import entity.ChamCong;

public class DAO_ChamCong {

    // Phương thức thêm thông tin chấm công vào cơ sở dữ liệu
    public int themChamCong(ChamCong chamCong) {
        int result = 0;

        try (Connection connection = DataBase.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO NgayCong (maNV, maCT, soNgayCong) VALUES (?, ?, ?)")) {

            preparedStatement.setString(1, chamCong.getMaNV());
            preparedStatement.setString(2, chamCong.getMaCT());
            preparedStatement.setInt(3, chamCong.getSoNgayCong());

            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    // Phương thức cập nhật thông tin chấm công trong cơ sở dữ liệu
    public int capNhatChamCong(ChamCong chamCong) {
        int result = 0;

        try (Connection connection = DataBase.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE NgayCong SET soNgayCong = ? WHERE maNV = ? AND maCT = ?")) {

            preparedStatement.setInt(1, chamCong.getSoNgayCong());
            preparedStatement.setString(2, chamCong.getMaNV());
            preparedStatement.setString(3, chamCong.getMaCT());

            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    // Phương thức xóa thông tin chấm công từ cơ sở dữ liệu
    public int xoaChamCong(String maNV, String maCT) {
        int result = 0;

        try (Connection connection = DataBase.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM NgayCong WHERE maNV = ? AND maCT = ?")) {

            preparedStatement.setString(1, maNV);
            preparedStatement.setString(2, maCT);

            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    // Phương thức lấy thông tin chấm công theo mã nhân viên và mã công trình
    public ChamCong layThongTinChamCong(String maNV, String maCT) {
        ChamCong chamCong = null;

        try (Connection connection = DataBase.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM NgayCong WHERE maNV = ? AND maCT = ?")) {

            preparedStatement.setString(1, maNV);
            preparedStatement.setString(2, maCT);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int soNgayCong = resultSet.getInt("soNgayCong");
                chamCong = new ChamCong(maNV, maCT, soNgayCong);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chamCong;
    }

    // Phương thức lấy danh sách chấm công cho một nhân viên
    public ArrayList<ChamCong> layDanhSachChamCongTheoNhanVien(String maNV) {
        ArrayList<ChamCong> danhSachChamCong = new ArrayList<>();

        try (Connection connection = DataBase.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM NgayCong WHERE maNV = ?")) {

            preparedStatement.setString(1, maNV);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String maCT = resultSet.getString("maCT");
                int soNgayCong = resultSet.getInt("soNgayCong");
                ChamCong chamCong = new ChamCong(maNV, maCT, soNgayCong);
                danhSachChamCong.add(chamCong);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return danhSachChamCong;
    }

    // Phương thức lấy danh sách chấm công cho một công trình
    public ArrayList<ChamCong> layDanhSachChamCongTheoCongTrinh(String maCT) {
        ArrayList<ChamCong> danhSachChamCong = new ArrayList<>();

        try (Connection connection = DataBase.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM NgayCong WHERE maCT = ?")) {

            preparedStatement.setString(1, maCT);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String maNV = resultSet.getString("maNV");
                int soNgayCong = resultSet.getInt("soNgayCong");
                ChamCong chamCong = new ChamCong(maNV, maCT, soNgayCong);
                danhSachChamCong.add(chamCong);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return danhSachChamCong;
    }
    
    
    
    
    
    
    
    
    public class Main {

        public static void main(String[] args) {
            // Test thêm chấm công
            testThemChamCong();

            // Test cập nhật chấm công
            testCapNhatChamCong();

            // Test xóa chấm công
            testXoaChamCong();

            // Test lấy thông tin chấm công
            testLayThongTinChamCong();

            // Test lấy danh sách chấm công theo nhân viên
            testLayDanhSachChamCongTheoNhanVien();

            // Test lấy danh sách chấm công theo công trình
            testLayDanhSachChamCongTheoCongTrinh();
        }

        private static void testThemChamCong() {
            DAO_ChamCong dao = new DAO_ChamCong();
            ChamCong chamCong = new ChamCong("MaNV123", "MaCT456", 5);

            int result = dao.themChamCong(chamCong);

            if (result > 0) {
                System.out.println("Thêm chấm công thành công");
            } else {
                System.out.println("Thêm chấm công thất bại");
            }
        }

        private static void testCapNhatChamCong() {
            DAO_ChamCong dao = new DAO_ChamCong();
            ChamCong chamCong = new ChamCong("MaNV123", "MaCT456", 8);

            int result = dao.capNhatChamCong(chamCong);

            if (result > 0) {
                System.out.println("Cập nhật chấm công thành công");
            } else {
                System.out.println("Cập nhật chấm công thất bại");
            }
        }

        private static void testXoaChamCong() {
            DAO_ChamCong dao = new DAO_ChamCong();
            String maNV = "MaNV123";
            String maCT = "MaCT456";

            int result = dao.xoaChamCong(maNV, maCT);

            if (result > 0) {
                System.out.println("Xóa chấm công thành công");
            } else {
                System.out.println("Xóa chấm công thất bại");
            }
        }

        private static void testLayThongTinChamCong() {
            DAO_ChamCong dao = new DAO_ChamCong();
            String maNV = "MaNV123";
            String maCT = "MaCT456";

            ChamCong chamCong = dao.layThongTinChamCong(maNV, maCT);

            if (chamCong != null) {
                System.out.println("Thông tin chấm công:");
                System.out.println("Mã NV: " + chamCong.getMaNV());
                System.out.println("Mã CT: " + chamCong.getMaCT());
                System.out.println("Số ngày công: " + chamCong.getSoNgayCong());
            } else {
                System.out.println("Không tìm thấy thông tin chấm công");
            }
        }

        private static void testLayDanhSachChamCongTheoNhanVien() {
            DAO_ChamCong dao = new DAO_ChamCong();
            String maNV = "MaNV123";

            ArrayList<ChamCong> danhSachChamCong = dao.layDanhSachChamCongTheoNhanVien(maNV);

            if (!danhSachChamCong.isEmpty()) {
                System.out.println("Danh sách chấm công cho nhân viên " + maNV + ":");
                for (ChamCong chamCong : danhSachChamCong) {
                    System.out.println("Mã CT: " + chamCong.getMaCT() + ", Số ngày công: " + chamCong.getSoNgayCong());
                }
            } else {
                System.out.println("Không có dữ liệu chấm công cho nhân viên " + maNV);
            }
        }

        private static void testLayDanhSachChamCongTheoCongTrinh() {
            DAO_ChamCong dao = new DAO_ChamCong();
            String maCT = "MaCT456";

            ArrayList<ChamCong> danhSachChamCong = dao.layDanhSachChamCongTheoCongTrinh(maCT);

            if (!danhSachChamCong.isEmpty()) {
                System.out.println("Danh sách chấm công cho công trình " + maCT + ":");
                for (ChamCong chamCong : danhSachChamCong) {
                    System.out.println("Mã NV: " + chamCong.getMaNV() + ", Số ngày công: " + chamCong.getSoNgayCong());
                }
            } else {
                System.out.println("Không có dữ liệu chấm công cho công trình " + maCT);
            }
        }
    }

}
