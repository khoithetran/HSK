package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DataBase;
import entity.BaoCao;

public class DAOBaoCao {
    public ArrayList<BaoCao> layDuLieuBaoCao(int thang, int nam) {
        ArrayList<BaoCao> danhSachBaoCao = new ArrayList<>();

        try (Connection connection = DataBase.getInstance().getConnection();
             CallableStatement callableStatement = connection.prepareCall("{call LayDuLieuBaoCao(?, ?)}")) {

            callableStatement.setInt(1, thang);
            callableStatement.setInt(2, nam);

            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                String maNV = resultSet.getString("maNV");
                String hoTen = resultSet.getString("hoTen");
                String tenCT = resultSet.getString("tenCT");
                int soNgayCong = resultSet.getInt("soNgayCong");

                BaoCao baoCao = new BaoCao(maNV, hoTen, tenCT, soNgayCong);
                danhSachBaoCao.add(baoCao);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return danhSachBaoCao;
    }
}
