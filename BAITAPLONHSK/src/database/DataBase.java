package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {

	public static Connection con = null;
	private static DataBase instance = new DataBase();

	public static DataBase getInstance() {
		return instance;
	}
	
	public void connect() {
			String url = "jdbc:sqlserver://localhost:1433;databasename=qlld;encrypt=false";
			String user = "baitap";
			String passWordd = "pass";
			try {
				con = DriverManager.getConnection(url,user,passWordd);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	public static Connection getConnection() {
		return con;
	}	
}
