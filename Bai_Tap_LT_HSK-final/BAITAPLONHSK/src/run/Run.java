package run;

import java.util.Scanner;

import javax.swing.SwingUtilities;

import dao.DAO_NhanVien;
import gui.GUI_DangNhap;

public class Run {
	private static Scanner sc = new Scanner(System.in);
	private DAO_NhanVien daoNV = new DAO_NhanVien();
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI_DangNhap().setVisible(true);
            }
        });
    }
}
