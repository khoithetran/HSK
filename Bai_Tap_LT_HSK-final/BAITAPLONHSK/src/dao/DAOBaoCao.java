package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import entity.BaoCao;

public class DAOBaoCao {
    // Phương thức lấy dữ liệu báo cáo từ cơ sở dữ liệu
    public ArrayList<BaoCao> layDuLieuBaoCao(int thang, int nam) {
        ArrayList<BaoCao> danhSachBaoCao = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Kết nối cơ sở dữ liệu (connection code ở đây)

            // SQL truy vấn để lấy thông tin báo cáo
            String sql = "SELECT nv.maNV, nv.hoTen, ct.tenCT, nc.soNgayCong " +
                         "FROM NgayCong nc " +
                         "JOIN NhanVien nv ON nc.maNV = nv.maNV " +
                         "JOIN CongTrinh ct ON nc.maCT = ct.maCT " +
                         "WHERE MONTH(nc.ngayCong) = ? AND YEAR(nc.ngayCong) = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, thang);
            preparedStatement.setInt(2, nam);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String maNV = resultSet.getString("maNV");
                String hoTen = resultSet.getString("hoTen");
                String tenCT = resultSet.getString("tenCT");
                int soNgayCong = resultSet.getInt("soNgayCong");

                // Tạo đối tượng BaoCao và thêm vào danh sách
                BaoCao baoCao = new BaoCao(maNV, hoTen, tenCT, soNgayCong);
                danhSachBaoCao.add(baoCao);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng các tài nguyên (resultSet, preparedStatement, connection) ở đây
        }

        return danhSachBaoCao;
    }
}