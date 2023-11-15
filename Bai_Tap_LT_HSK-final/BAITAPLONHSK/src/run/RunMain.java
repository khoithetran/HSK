package run;

import java.sql.Connection;

import javax.swing.SwingUtilities;

import database.DataBase;
import gui.GUI_main;

public class RunMain {
	public static void main(String[] args) {
	 	System.out.println("2");
		DataBase.getInstance().connect();
		Connection con = DataBase.getInstance().getConnection();
		System.out.println(con);
	 
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI_main().setVisible(true);
            }
        });
    }
}
