package gui;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;

import dao.DAOBaoCao;
import dao.DAO_CongTrinh;
import dao.DAO_NhanVien;
import database.DataBase;
import entity.BaoCao;
import entity.CongTrinh;
import entity.NhanVien;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.SimpleFormatter;

public class GUI_main extends JFrame  implements ActionListener, MouseListener{
	private static Scanner sc = new Scanner(System.in);
    JButton  b_them_ct, b_xoa_ct, b_capnhat_ct, b_lietke_ct, b_timkiem_ct,
    b_them_nv,b_xoa_nv,b_capnhat_nv,b_lietke_nv,b_timkiem_nv;
    JTabbedPane tabbedPane;
    JPanel congTrinhPanel, homePanel, nhanVienPanel, dangXuatPanel;
    JTextField t_congTrinhThamGia_nv,t_diaChiTamTru_nv,
    t_diaChi,t_gioiTinh_nv,t_ngaySinh_nv,t_hoTen_nv,
    t_maNV_nv,t_timkiem_ct,t_nght_ct,t_ngkc_ct,t_ngcap_ct,
	    t_diadiem_ct,t_ten_ct,t_id_ct;
	private JPanel baoCaoPanel;
	private JTextField t_ngay_cc;
	private JTextField txt_maNV_cc;
	private JTextField txt_maCT_cc;
	private JTextField txt_soNgayCong_cc;
	private JButton b_xacNhan;
	private DefaultTableModel model_baoCao;
    JComboBox<String> gioiTinhComboBox;
	private JTextField t_id_cc;
	private DefaultTableModel model_nv;
	private JTable table_nv;
	private JTextField t_phongban_nv;
	private JTextField t_chucvu_nv;
	private JTable table_ct;
	private DefaultTableModel model_ct;
	private DefaultTableModel model_nvct;
	private JTable table_nvct;
	private DefaultTableModel model_chamCong;
	private JTable table_chamCong;
	private JTable table_baoCao;
	
    public GUI_main() {
        setTitle("Thông tin Công trình");
        setSize(1000, 750); // Kích thước cửa sổ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        initComponents();
        centerFrame();
    }

    private void initComponents() {
    	EmptyBorder empty = new EmptyBorder(20, 0, 20, 0);

        tabbedPane = new JTabbedPane();
        // Trang chủ
        homePanel = new JPanel(new BorderLayout());

        // Đường dẫn tới tệp tin hình ảnh
//<<<<<<< HEAD:Bai_Tap_Nhom_2/src/gui/GUI_main.java
        String imagePath = "src\\IMAGE\\OIG.69.jpg";
//=======
//        String imagePath = "src\\IMAGE\\OIG.69.jpg";
//>>>>>>> e641b906c4cb81e2634603a1b9a01dafbc11a8f1:Bai_Tap_Nhom_2/src/GUI/GUI_main.java

        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(800, 500, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(newImage);
        JLabel imageLabel = new JLabel(scaledImageIcon, JLabel.CENTER);
        homePanel.add(imageLabel, BorderLayout.CENTER);
        tabbedPane.addTab("Home", homePanel);
        getContentPane().add(tabbedPane);
        
//        Quản lý công trình        
        congTrinhPanel = new JPanel(new BorderLayout());
        Box box_quanLyCongTrinhPanel = Box.createVerticalBox();
        Box box_input_ct = Box.createVerticalBox();
        JLabel title = new JLabel("Quản lý công trình");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);        
        box_quanLyCongTrinhPanel.add(title, BorderLayout.NORTH);
        Box box_ten = Box.createHorizontalBox();
        JLabel l_id_ct = new JLabel("Nhập mã:");
        box_ten.add(l_id_ct);
        t_id_ct = new JTextField();
        l_id_ct.setPreferredSize(new Dimension(180, 25));
        box_ten.add(t_id_ct);        
        JLabel l_ten_ct = new JLabel("Nhập tên:");
        l_ten_ct.setPreferredSize(new Dimension(180, 25));
        box_ten.add(l_ten_ct);
        t_ten_ct = new JTextField();
        box_ten.add(t_ten_ct);
        Box box_diadiem = Box.createHorizontalBox();
        JLabel l_diadiem_ct = new JLabel("Nhập địa điểm:");
        l_diadiem_ct.setPreferredSize(new Dimension(180, 25));
        box_diadiem.add(l_diadiem_ct);
        t_diadiem_ct = new JTextField();
        box_diadiem.add(t_diadiem_ct);
        JLabel l_ngcap_ct = new JLabel("Nhập ngày cấp phép:");
        l_ngcap_ct.setPreferredSize(new Dimension(180, 25));
        box_diadiem.add(l_ngcap_ct);
        t_ngcap_ct = new JTextField();
        box_diadiem.add(t_ngcap_ct);
        Box box_ngay = Box.createHorizontalBox();
        JLabel l_ngkc_ct = new JLabel("Nhập ngày khởi công:");
        l_ngkc_ct.setPreferredSize(new Dimension(180, 25));
        box_ngay.add(l_ngkc_ct);
        t_ngkc_ct = new JTextField();
        box_ngay.add(t_ngkc_ct);
        JLabel l_nght_ct = new JLabel("Nhập ngày hoàn thành(DK):");
        l_nght_ct.setPreferredSize(new Dimension(180, 25));
        box_ngay.add(l_nght_ct);
        t_nght_ct = new JTextField();
        box_ngay.add(t_nght_ct);
        Box box_chucnang_ct = Box.createVerticalBox();
        
        box_chucnang_ct.add(box_ten);
        box_chucnang_ct.add(Box.createVerticalStrut(10));
        box_chucnang_ct.add(box_diadiem);
        box_chucnang_ct.add(Box.createVerticalStrut(10));
        box_chucnang_ct.add(box_ngay);
        
        box_chucnang_ct.setBorder(BorderFactory.createTitledBorder("Nhập thông tin Công trình"));
        
        Box box_btn = Box.createHorizontalBox();
        b_them_ct = new JButton("Thêm");
        b_xoa_ct = new JButton("Xoá");
        b_capnhat_ct = new JButton("Cập nhật");
        b_lietke_ct = new JButton("Liệt kê");
        
        JLabel l_timkiem_ct = new JLabel("Tìm theo mã công trình: ");
        b_timkiem_ct = new JButton("Tìm kiếm");
        t_timkiem_ct = new JTextField(10);
        t_timkiem_ct.setPreferredSize(new Dimension(100, 25));
        Box box_timkiem_ct = Box.createHorizontalBox();
        box_timkiem_ct.add(l_timkiem_ct);
        box_btn.add(Box.createHorizontalStrut(10));
        box_timkiem_ct.add(t_timkiem_ct);

        box_btn.add(Box.createHorizontalStrut(10));
        box_timkiem_ct.add(b_timkiem_ct);

        
        box_btn.add(b_them_ct);
        box_btn.add(Box.createHorizontalStrut(10));
        box_btn.add(b_xoa_ct);
        box_btn.add(Box.createHorizontalStrut(10));
        box_btn.add(b_capnhat_ct);
        box_btn.add(Box.createHorizontalStrut(10));
        box_btn.add(b_lietke_ct);
        box_btn.add(Box.createHorizontalStrut(10));
        box_btn.add(box_timkiem_ct);
        box_btn.add(Box.createHorizontalStrut(10));
        box_btn.setMaximumSize(new Dimension(700, 100));
   
        

        box_input_ct.add(box_chucnang_ct);
        box_input_ct.add(Box.createVerticalStrut(20));
        box_input_ct.add(box_btn);
        box_input_ct.setBorder(empty);
        box_quanLyCongTrinhPanel.add(box_input_ct, BorderLayout.CENTER);

        
        
//        table
        Box box_table_ct = Box.createVerticalBox();
        String[] headers_ct = "Mã Công Trình;Tên Công Trình;Địa điểm;Ngày cấp phép;Ngày khởi công;Ngày hoàn thành dự kiến".split(";");
		model_ct = new DefaultTableModel(headers_ct, 0);
		table_ct = new JTable(model_ct);
		
//		table_ct.setRowHeight(50);
        table_ct.setPreferredScrollableViewportSize(new Dimension(500, 200));

		box_table_ct.add(new JScrollPane(table_ct));
		
		
		
		
        String[] headers_nvct = "Mã nhân viên;Tên nhân viên;Số điện thoại;Mã phòng ban;Tên phòng ban".split(";");
		model_nvct = new DefaultTableModel(headers_nvct, 0);
		table_nvct = new JTable(model_nvct);
        table_nvct.setPreferredScrollableViewportSize(new Dimension(500, 200));

		box_table_ct.add(new JScrollPane(table_nvct));
		
		
		box_quanLyCongTrinhPanel.add(box_table_ct, BorderLayout.SOUTH);
        
        congTrinhPanel.add(box_quanLyCongTrinhPanel, BorderLayout.CENTER);

        tabbedPane.addTab("Home", homePanel);
        tabbedPane.addTab("Quản lý công trình", congTrinhPanel);

     // Quản lý nhân viên===========================++++++++++++++++++++++_--------------------------------------------
        nhanVienPanel = new JPanel(new BorderLayout());

        // Panel chứa các thành phần quản lý nhân viên
        Box box_quanLyNhanVienPanel = Box.createVerticalBox();
        Box box_input_nv = Box.createVerticalBox();

        // Tiêu đề "Quản lý nhân viên"
        JLabel titleNV = new JLabel("Quản lý nhân viên");
        titleNV.setFont(new Font("Arial", Font.BOLD, 24));
        titleNV.setAlignmentX(Component.CENTER_ALIGNMENT);

        box_quanLyNhanVienPanel.add(titleNV);
        Box box_maNV = Box.createHorizontalBox();

        // Nhập mã nhân viên
        JLabel l_maNV_nv = new JLabel("Nhập mã:");
        box_maNV.add(l_maNV_nv);
        t_maNV_nv = new JTextField();
        l_maNV_nv.setPreferredSize(new Dimension(180, 25));
        box_maNV.add(t_maNV_nv);
        JLabel l_hoTen_nv = new JLabel("Nhập họ tên:");
        l_hoTen_nv.setPreferredSize(new Dimension(180, 25));
        box_maNV.add(l_hoTen_nv);
        t_hoTen_nv = new JTextField();
        box_maNV.add(t_hoTen_nv);
        Box box_ngaySinh_GioiTinh = Box.createHorizontalBox();
        JLabel l_ngaySinh_nv = new JLabel("Nhập ngày sinh:");
        l_ngaySinh_nv.setPreferredSize(new Dimension(180, 25));
        box_ngaySinh_GioiTinh.add(l_ngaySinh_nv);
        t_ngaySinh_nv = new JTextField();
        box_ngaySinh_GioiTinh.add(t_ngaySinh_nv);
        JLabel l_gioiTinh_nv = new JLabel("Giới tính:");
        l_gioiTinh_nv.setPreferredSize(new Dimension(180, 25));
        box_ngaySinh_GioiTinh.add(l_gioiTinh_nv);
        String[] gioiTinhList = {"Nam", "Nữ", "Khác"};
        gioiTinhComboBox = new JComboBox<>(gioiTinhList);
        gioiTinhComboBox.setPreferredSize(new Dimension(310, 25));
        box_ngaySinh_GioiTinh.add(gioiTinhComboBox);
        
        Box box_diaChi = Box.createHorizontalBox();
        JLabel l_diaChiThuongChu_nv = new JLabel("Nhập địa chỉ:");
        l_diaChiThuongChu_nv.setPreferredSize(new Dimension(180, 25));
        box_diaChi.add(l_diaChiThuongChu_nv);
        t_diaChi = new JTextField();
        box_diaChi.add(t_diaChi);
        Box box_congTrinh = Box.createHorizontalBox();
        JLabel l_phongban_nv = new JLabel("Nhập mã phòng ban:");
        l_phongban_nv.setPreferredSize(new Dimension(180, 25));
        box_congTrinh.add(l_phongban_nv);
        t_phongban_nv = new JTextField();
        box_congTrinh.add(t_phongban_nv);
        
        JLabel l_chucvu_nv = new JLabel("Chức vụ:");
        l_chucvu_nv.setPreferredSize(new Dimension(180, 25));
        box_congTrinh.add(l_chucvu_nv);
        t_chucvu_nv = new JTextField();
        box_congTrinh.add(t_chucvu_nv);

        box_input_nv.add(box_maNV);
        box_input_nv.add(Box.createVerticalStrut(10));
        box_input_nv.add(box_ngaySinh_GioiTinh);
        box_input_nv.add(Box.createVerticalStrut(10));
        box_input_nv.add(box_congTrinh);
        box_input_nv.add(Box.createVerticalStrut(10));
        box_input_nv.add(box_diaChi);

        // Tạo Border với TitledBorder
        box_input_nv.setBorder(BorderFactory.createTitledBorder("Nhập thông tin Nhân viên"));

        Box box_btn_NV = Box.createHorizontalBox();
        b_them_nv = new JButton("Thêm");
        b_xoa_nv = new JButton("Xoá");
        b_capnhat_nv = new JButton("Cập nhật");
        b_lietke_nv = new JButton("Liệt kê");

        JLabel l_timkiem_nv = new JLabel("Tìm theo mã nhân viên: ");
        b_timkiem_nv = new JButton("Tìm kiếm");
        Component t_timkiem_nv = new JTextField(10);
        t_timkiem_nv.setPreferredSize(new Dimension(100, 25));
        Box box_timkiem_nv = Box.createHorizontalBox();
        box_timkiem_nv.add(l_timkiem_nv);
        box_btn_NV.add(Box.createHorizontalStrut(10));
        box_timkiem_nv.add(t_timkiem_nv);

        box_btn_NV.add(Box.createHorizontalStrut(10));
        box_timkiem_nv.add(b_timkiem_nv);

        box_btn_NV.add(b_them_nv);
        box_btn_NV.add(Box.createHorizontalStrut(10));
        box_btn_NV.add(b_xoa_nv);
        box_btn_NV.add(Box.createHorizontalStrut(10));
        box_btn_NV.add(b_capnhat_nv);
        box_btn_NV.add(Box.createHorizontalStrut(10));
        box_btn_NV.add(b_lietke_nv);
        box_btn_NV.add(Box.createHorizontalStrut(10));
        box_btn_NV.add(box_timkiem_nv);
        box_btn_NV.add(Box.createHorizontalStrut(10));

        box_btn_NV.setMaximumSize(new Dimension(700, 100));

        box_input_nv.add(Box.createVerticalStrut(20));
        box_input_nv.add(box_btn_NV);
        box_input_nv.setBorder(empty);
        box_quanLyNhanVienPanel.add(box_input_nv, BorderLayout.CENTER);

        // Table Nhân viên
        Box box_table_nv = Box.createVerticalBox();
        String[] headers_nv = "Mã nhân viên;Họ tên;Ngày sinh;Giới tính;Mã phòng ban;Chức vụ;Địa chỉ".split(";");
        model_nv = new DefaultTableModel(headers_nv, 0);
        table_nv = new JTable(model_nv);
        table_nv.setPreferredScrollableViewportSize(new Dimension(500, 700));

        box_table_nv.add(new JScrollPane(table_nv));

        box_quanLyNhanVienPanel.add(box_table_nv, BorderLayout.SOUTH);

        nhanVienPanel.add(box_quanLyNhanVienPanel);

        tabbedPane.addTab("Quản lý nhân viên", nhanVienPanel);

//      Quản lí chấm công
     // Thêm nút Quản lý chấm công vào tabbedPane
        JPanel chamCongPanel = new JPanel(new BorderLayout());
        Box box_inputChamCong = Box.createVerticalBox();

        // Tiêu đề "Quản lý chấm công"
        JLabel titleChamCong = new JLabel("Quản lý chấm công");
        titleChamCong.setFont(new Font("Arial", Font.BOLD, 24));
        titleChamCong.setAlignmentX(Component.CENTER_ALIGNMENT);
        box_inputChamCong.add(Box.createVerticalStrut(10));
        box_inputChamCong.add(titleChamCong);
        box_inputChamCong.add(Box.createVerticalStrut(10));

        // Box horizontal chứa label và textfield mã nhân viên
        Box box_maNV_chamCong = Box.createHorizontalBox();
        JLabel l_maNV_chamCong = new JLabel("Nhập mã nhân viên:");
        box_maNV_chamCong.add(l_maNV_chamCong);
        box_maNV_chamCong.add(Box.createHorizontalStrut(20)); // Khoảng cách 20 giữa label và textfield
        JTextField t_maNV_chamCong = new JTextField();
        box_maNV_chamCong.add(t_maNV_chamCong);
        box_inputChamCong.add(box_maNV_chamCong);
        box_inputChamCong.add(Box.createVerticalStrut(10));

        // Box horizontal chứa label và textfield mã công trình
        Box box_maCT_chamCong = Box.createHorizontalBox();
        JLabel l_maCT_chamCong = new JLabel("Nhập mã công trình:");
        box_maCT_chamCong.add(l_maCT_chamCong);
        box_maCT_chamCong.add(Box.createHorizontalStrut(20)); // Khoảng cách 20 giữa label và textfield
        JTextField t_maCT_chamCong = new JTextField();
        box_maCT_chamCong.add(t_maCT_chamCong);
        box_inputChamCong.add(box_maCT_chamCong);
        box_inputChamCong.add(Box.createVerticalStrut(10));

        // Box horizontal chứa label và textfield số ngày công
        Box box_soNgayCong_chamCong = Box.createHorizontalBox();
        JLabel l_soNgayCong_chamCong = new JLabel("Nhập số ngày công:");
        box_soNgayCong_chamCong.add(l_soNgayCong_chamCong);
        box_soNgayCong_chamCong.add(Box.createHorizontalStrut(20)); // Khoảng cách 20 giữa label và textfield
        JTextField t_soNgayCong_chamCong = new JTextField();
        box_soNgayCong_chamCong.add(t_soNgayCong_chamCong);
        box_inputChamCong.add(box_soNgayCong_chamCong);
        box_inputChamCong.add(Box.createVerticalStrut(10));

        // Tạo Border với TitledBorder
        box_inputChamCong.setBorder(BorderFactory.createTitledBorder("Nhập thông tin chấm công"));

        // Thêm nút "Xác nhận"
        JButton b_xacNhanChamCong = new JButton("Xác nhận");
        box_inputChamCong.add(b_xacNhanChamCong);

        // Thêm khoảng cách 20 giữa box chứa textfield và nút "Xác nhận"
        box_inputChamCong.add(Box.createVerticalStrut(20));

        // Box vertical chứa bảng chấm công
        Box box_tableChamCong = Box.createVerticalBox();
        String[] headers_chamCong = {"Mã nhân viên", "Tên nhân viên", "Công trình tham gia", "Ngày chấm công"};
        model_chamCong = new DefaultTableModel(headers_chamCong, 0);
        table_chamCong = new JTable(model_chamCong);
        table_chamCong.setPreferredScrollableViewportSize(new Dimension(500, 400));
        box_tableChamCong.add(new JScrollPane(table_chamCong));

        // Thêm các box vào chamCongPanel
        chamCongPanel.add(titleChamCong, BorderLayout.NORTH);
        chamCongPanel.add(box_inputChamCong, BorderLayout.CENTER);
        chamCongPanel.add(box_tableChamCong, BorderLayout.SOUTH);

        // Thêm tab Quản lý chấm công vào tabbedPane
        tabbedPane.addTab("Quản lý chấm công", chamCongPanel);




//        Báo cáo
        baoCaoPanel = new JPanel(new BorderLayout());
        
	    Box box_baoCaoPanel = Box.createVerticalBox();
	    Box box_input_baoCao = Box.createVerticalBox();
	    

	    // Tiêu đề "Báo cáo"
	    JLabel titleBaoCao = new JLabel("Báo cáo");
	    titleBaoCao.setFont(new Font("Arial", Font.BOLD, 24));
	    titleBaoCao.setAlignmentX(Component.CENTER_ALIGNMENT);
	
	    box_baoCaoPanel.add(titleBaoCao);
	
	    // Box horizontal chứa label và combobox Tháng và Năm
	    Box box_thangNam = Box.createHorizontalBox();
	    JLabel l_thang = new JLabel("Tháng:");
	    String[] thangArray = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
	    JComboBox<String> comboBoxThang = new JComboBox<>(thangArray);
	
	    // Thêm khoảng trắng giữa Tháng và Năm
	    box_thangNam.add(l_thang);
	    box_thangNam.add(Box.createHorizontalStrut(10));
	

	    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
	    String[] namArray = new String[10];
	    for (int i = 0; i < 10; i++) {
	        namArray[i] = Integer.toString(currentYear + i);
	    }
	    JComboBox<String> comboBoxNam = new JComboBox<>(namArray);
	
	    box_thangNam.add(comboBoxThang);
	    
	    box_thangNam.add(Box.createHorizontalStrut(10));
	    JLabel l_nam = new JLabel("Năm:");
	    box_thangNam.add(l_nam);
	    box_thangNam.add(Box.createHorizontalStrut(10));
	    
	    box_thangNam.add(comboBoxNam);
	
	    box_input_baoCao.add(box_thangNam);
	    box_input_baoCao.setBorder(BorderFactory.createTitledBorder("Chọn Tháng và Năm"));
	
	     // Table Báo cáo
	    Box box_table_baoCao = Box.createVerticalBox();
	    String[] headers_baoCao = {"Mã nhân viên", "Tên nhân viên", "Công trình tham gia", "Số ngày công"};
	    model_baoCao = new DefaultTableModel(headers_baoCao, 0);
	    table_baoCao = new JTable(model_baoCao);
	    table_baoCao.setPreferredScrollableViewportSize(new Dimension(500, 700));

	    box_table_baoCao.add(new JScrollPane(table_baoCao));

	    box_baoCaoPanel.add(box_input_baoCao);
	    box_baoCaoPanel.add(Box.createVerticalStrut(20));
	    box_baoCaoPanel.add(box_table_baoCao);
	    
	    Box box_xuatPDF = Box.createHorizontalBox();
	    box_xuatPDF.add(Box.createHorizontalGlue()); 
	    JButton btnXuatPDF = new JButton("Xuất PDF");
	    box_xuatPDF.add(btnXuatPDF);

	    box_baoCaoPanel.add(Box.createVerticalStrut(20));
	    box_baoCaoPanel.add(box_xuatPDF);
	    box_baoCaoPanel.add(Box.createVerticalStrut(20));

	    baoCaoPanel.add(box_baoCaoPanel);
	    
	    tabbedPane.addTab("Báo cáo", baoCaoPanel);
	    
	    Box box_dangXuat = Box.createVerticalBox();
        JButton dangXuatButton = new JButton("Đăng xuất");
        
        dangXuatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Đăng xuất khỏi ứng dụng ?");
                System.exit(0);
            }
        });

        // Create horizontal glue to push the button to the center
        Component horizontalGlue = Box.createHorizontalGlue();

        // Add components with glue to center the button
        box_dangXuat.add(Box.createVerticalStrut(10));
        box_dangXuat.add(horizontalGlue);
        box_dangXuat.add(dangXuatButton);
        box_dangXuat.add(Box.createHorizontalStrut(150)); // Add some horizontal space if needed
        box_dangXuat.add(Box.createVerticalStrut(10));
        
        add(box_dangXuat, BorderLayout.SOUTH);


        getContentPane().add(tabbedPane);
        
        themdulieuCacBang();
        
        b_them_nv.addActionListener(this);
        b_xoa_nv.addActionListener(this);
        b_capnhat_nv.addActionListener(this);
        b_lietke_nv.addActionListener(this);
        b_timkiem_nv.addActionListener(this);
        b_them_ct.addActionListener(this);
        b_xoa_ct.addActionListener(this);
        b_capnhat_ct.addActionListener(this);
        b_lietke_ct.addActionListener(this);
        b_timkiem_ct.addActionListener(this);
    }

    private void themdulieuCacBang() {
//		Bang cong trinh 1  	
    	DAO_CongTrinh dao = new DAO_CongTrinh();
	    ArrayList<CongTrinh> danhSachCongTrinh = dao.lietKe(null);
	    if (!danhSachCongTrinh.isEmpty()) {   
	        for (CongTrinh ct : danhSachCongTrinh) {
	        	Object[] rowData = {
	        			ct.getMaCT(),
	        			ct.getTenCT(),
	        			ct.getDiaDiem(),
	        			ct.getNgayCap(),
	        			ct.getNgayKhoiCong(),
	        			ct.getNgayHTDK()
	            };
	        	model_ct.addRow(rowData);
	        }        
	    } 
    	
//    	Bang cong trinh 2 
    	
//    	Bang nhan vien 
	    DAO_NhanVien daonvv11 = new DAO_NhanVien();
	    ArrayList<NhanVien> dsachNhanVien = daonvv11.lietKe(null);
	    if (!dsachNhanVien.isEmpty()) {   
	        for (NhanVien nv : dsachNhanVien) {
	        	Object[] rowData = {
	        			nv.getMaNV(),
	        			nv.getHoTen(),
	        			nv.getNgaySinh(),
	        			nv.getGioiTinh(),
	        			nv.getmaPB(),
	        			nv.getChucVu(),
	        			nv.getDiaChi()
	            };
	        	model_nv.addRow(rowData);
	        }        
	    } 
//    	bang cham cong
		
	}

	private void centerFrame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        int frameWidth = getWidth();
        int frameHeight = getHeight();

        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;

        setLocation(x, y);
    }

   

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		
		Object o = e.getSource();
		if(o.equals(b_them_nv)) {
			themAction();
		} 
		if(o.equals(b_xoa_nv)) {
			xoaActions();
		}
		if(o.equals(b_capnhat_nv)) {
			capNhapAction();
		}
		if(o.equals(b_lietke_nv)) {
			lietKeAction();
		}
		if(o.equals(b_timkiem_nv)) {
			timKiemAction();
		}
		if(o.equals(b_them_ct)) {
			themCTACTION();
		}
		if(o.equals(b_xoa_ct)) {
			xoaCTACTION();
		}
		if(o.equals(b_capnhat_ct)) {
			capNhapCTACTION();
		}
		if(o.equals(b_lietke_ct)) {
			lietKeCTACTION();
		}
		if(o.equals(b_timkiem_ct)) {
			timKiemCTACTION();
		}
	}
	
	private void timKiemCTACTION() {
	    String maCT = JOptionPane.showInputDialog(null, "Nhập mã công trình cần tìm kiếm:", "Tìm kiếm công trình", JOptionPane.QUESTION_MESSAGE);

	    if (maCT != null && !maCT.isEmpty()) {
	        DAO_CongTrinh dao = new DAO_CongTrinh();
	        CongTrinh ct = new CongTrinh(maCT, null, null, null, null, null);
	        CongTrinh ketQua = dao.timKiem(ct);

	        if (ketQua != null) {
	            StringBuilder message = new StringBuilder("Thông tin công trình:\n\n");
	            message.append("Mã CT: ").append(ketQua.getMaCT()).append("\n");
	            message.append("Tên CT: ").append(ketQua.getTenCT()).append("\n");
	            message.append("Địa điểm: ").append(ketQua.getDiaDiem()).append("\n");
	            message.append("Ngày cấp: ").append(new SimpleDateFormat("dd/MM/yyyy").format(ketQua.getNgayCap())).append("\n");
	            message.append("Ngày khởi công: ").append(new SimpleDateFormat("dd/MM/yyyy").format(ketQua.getNgayKhoiCong())).append("\n");
	            message.append("Ngày hoàn thành dự kiến: ").append(new SimpleDateFormat("dd/MM/yyyy").format(ketQua.getNgayHTDK())).append("\n");

	            JOptionPane.showMessageDialog(null, message.toString(), "Thông tin công trình", JOptionPane.INFORMATION_MESSAGE);
	        } else {
	            JOptionPane.showMessageDialog(null, "Không tìm thấy công trình có mã: " + maCT, "Thông báo", JOptionPane.WARNING_MESSAGE);
	        }
	    } else {
	        JOptionPane.showMessageDialog(null, "Vui lòng nhập mã công trình cần tìm kiếm", "Thông báo", JOptionPane.WARNING_MESSAGE);
	    }
	}

	
	private void lietKeCTACTION() {
	    DAO_CongTrinh dao = new DAO_CongTrinh();
	    ArrayList<CongTrinh> danhSachCongTrinh = dao.lietKe(null);

	    if (!danhSachCongTrinh.isEmpty()) {
	        StringBuilder message = new StringBuilder("Danh sách công trình:\n\n");

	        for (CongTrinh ct : danhSachCongTrinh) {
	            message.append("Mã CT: ").append(ct.getMaCT()).append("\n");
	            message.append("Tên CT: ").append(ct.getTenCT()).append("\n");
	            message.append("Địa điểm: ").append(ct.getDiaDiem()).append("\n");
	            message.append("Ngày cấp: ").append(new SimpleDateFormat("dd/MM/yyyy").format(ct.getNgayCap())).append("\n");
	            message.append("Ngày khởi công: ").append(new SimpleDateFormat("dd/MM/yyyy").format(ct.getNgayKhoiCong())).append("\n");
	            message.append("Ngày hoàn thành dự kiến: ").append(new SimpleDateFormat("dd/MM/yyyy").format(ct.getNgayHTDK())).append("\n");
	            message.append("------------------------------\n");
	        }

	        JOptionPane.showMessageDialog(null, message.toString(), "Danh sách công trình", JOptionPane.INFORMATION_MESSAGE);
	    } else {
	        JOptionPane.showMessageDialog(null, "Không có công trình nào.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	    }
	}

	
	private void capNhapCTACTION() {
	    String maCT = t_id_ct.getText();
	    String tenCT = t_ten_ct.getText();
	    String diaDiem = t_diadiem_ct.getText();
	    Date ngayCap, ngayKhoiCong, ngayHTDK;

	    try {
	        ngayCap = new SimpleDateFormat("dd/MM/yyyy").parse(t_ngcap_ct.getText());
	        ngayKhoiCong = new SimpleDateFormat("dd/MM/yyyy").parse(t_ngkc_ct.getText());
	        ngayHTDK = new SimpleDateFormat("dd/MM/yyyy").parse(t_nght_ct.getText());
	    } catch (ParseException e) {
	        JOptionPane.showMessageDialog(null, "Lỗi định dạng ngày tháng năm. Vui lòng kiểm tra lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    if (maCT.isEmpty() || tenCT.isEmpty() || diaDiem.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin công trình.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    CongTrinh ct = new CongTrinh(maCT, tenCT, diaDiem, ngayCap, ngayKhoiCong, ngayHTDK);
	    DAO_CongTrinh dao = new DAO_CongTrinh();
	    int result = dao.sua(ct);

	    if (result != 0) {
	        JOptionPane.showMessageDialog(null, "Đã cập nhật thông tin công trình");
	        int selectedRow = table_ct.getSelectedRow();
	        if (selectedRow != -1) {
	            // Cập nhật dòng trong bảng
	            table_ct.setValueAt(maCT, selectedRow, 0);
	            table_ct.setValueAt(tenCT, selectedRow, 1);
	            table_ct.setValueAt(diaDiem, selectedRow, 2);
	            table_ct.setValueAt(new SimpleDateFormat("dd/MM/yyyy").format(ngayCap), selectedRow, 3);
	            table_ct.setValueAt(new SimpleDateFormat("dd/MM/yyyy").format(ngayKhoiCong), selectedRow, 4);
	            table_ct.setValueAt(new SimpleDateFormat("dd/MM/yyyy").format(ngayHTDK), selectedRow, 5);
	        }
	    } else {
	        JOptionPane.showMessageDialog(null, "Lỗi cập nhật thông tin công trình", "Lỗi", JOptionPane.ERROR_MESSAGE);
	    }
	}

	
	private void xoaCTACTION() {
	    String maCT = JOptionPane.showInputDialog(null, "Nhập mã công trình cần xóa:", "Xóa công trình", JOptionPane.QUESTION_MESSAGE);

	    if (maCT != null && !maCT.isEmpty()) {
	        DAO_CongTrinh dao = new DAO_CongTrinh();
	        CongTrinh ct = new CongTrinh(maCT, null, null, null, null, null);

	        int result = dao.xoa(ct);

	        if (result != 0) {
	            JOptionPane.showMessageDialog(null, "Đã xóa công trình thành công.");
	        } else {
	            JOptionPane.showMessageDialog(null, "Không tìm thấy công trình có mã: " + maCT, "Lỗi", JOptionPane.ERROR_MESSAGE);
	        }
	    } else {
	        JOptionPane.showMessageDialog(null, "Vui lòng nhập mã công trình cần xóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	    }
	}

	
	private void themCTACTION() {
	    String maCT = t_id_ct.getText();
	    String tenCT = t_ten_ct.getText();
	    String diaDiem = t_diadiem_ct.getText();
	    Date ngayCap, ngayKhoiCong, ngayHTDK;

	    try {
	    	ngayCap = new SimpleDateFormat("dd/MM/yyyy").parse(t_ngcap_ct.getText());
	        ngayKhoiCong = new SimpleDateFormat("dd/MM/yyyy").parse(t_ngkc_ct.getText());
	        ngayHTDK = new SimpleDateFormat("dd/MM/yyyy").parse(t_nght_ct.getText());
	    } catch (ParseException e) {
	        JOptionPane.showMessageDialog(null, "Lỗi định dạng ngày tháng năm. Vui lòng kiểm tra lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    if (maCT.isEmpty() || tenCT.isEmpty() || diaDiem.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin công trình.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    CongTrinh ct = new CongTrinh(maCT, tenCT, diaDiem, ngayCap, ngayKhoiCong, ngayHTDK);
	    DAO_CongTrinh dao = new DAO_CongTrinh();
	    int result = dao.them(ct);

	    if (result != 0) {
	        JOptionPane.showMessageDialog(null, "Đã thêm công trình thành công.");
	    } else {
	        JOptionPane.showMessageDialog(null, "Lỗi khi thêm công trình. Vui lòng kiểm tra lại dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	    }
	}




	
	private void timKiemAction() {
	    String maNV = JOptionPane.showInputDialog(null, "Nhập mã nhân viên cần tìm kiếm:", "Tìm kiếm nhân viên", JOptionPane.QUESTION_MESSAGE);

	    if (maNV != null && !maNV.isEmpty()) {
	        NhanVien nv = new NhanVien(maNV, null, null, null, null, null, null);
	        DAO_NhanVien dao = new DAO_NhanVien();
	        NhanVien ketQua = dao.timKiem(nv);

	        if (ketQua != null) {
	            JOptionPane.showMessageDialog(null, ketQua.toString(), "Thông tin nhân viên", JOptionPane.INFORMATION_MESSAGE);
	        } else {
	            JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên có mã: " + maNV, "Thông báo", JOptionPane.WARNING_MESSAGE);
	        }
	    } else {
	        JOptionPane.showMessageDialog(null, "Vui lòng nhập mã nhân viên cần tìm kiếm", "Thông báo", JOptionPane.WARNING_MESSAGE);
	    }
	}


	private void lietKeAction() {
	    DAO_NhanVien dao = new DAO_NhanVien();
	    ArrayList<NhanVien> danhSachNhanVien = dao.lietKe(null);

	    StringBuilder message = new StringBuilder("Danh sách nhân viên:\n\n");
	    for (NhanVien nv : danhSachNhanVien) {
	        message.append(nv.toString()).append("\n");
	    }

	    JOptionPane.showMessageDialog(null, message.toString(), "Danh sách nhân viên", JOptionPane.INFORMATION_MESSAGE);
	}


	private void capNhapAction() {
	    String maNV = t_maNV_nv.getText();
	    String hoTen = t_hoTen_nv.getText();
	    String dobString = t_ngaySinh_nv.getText();
	    Date dob = new Date();
	    String gioiTinh = gioiTinhComboBox.getSelectedItem().toString();
	    String diaChi = t_diaChi.getText();
	    String maPB = t_phongban_nv.getText();
	    String chucVu = t_chucvu_nv.getText();

	    try {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	        dob = dateFormat.parse(dobString);
	    } catch (ParseException e) {
	        JOptionPane.showMessageDialog(null, "Lỗi định dạng ngày tháng năm");
	        return;
	    }

	    NhanVien nv = new NhanVien(maNV, hoTen, dob, gioiTinh, diaChi, maPB, chucVu);
	    DAO_NhanVien dao = new DAO_NhanVien();
	    int result = dao.sua(nv);

	    if (result != 0) {
	        JOptionPane.showMessageDialog(null, "Đã cập nhật thông tin nhân viên");
	        int selectedRow = table_nv.getSelectedRow();
	        if (selectedRow != -1) {
	            // Cập nhật dòng trong bảng
	            table_nv.setValueAt(maNV, selectedRow, 0);
	            table_nv.setValueAt(hoTen, selectedRow, 1);
	            table_nv.setValueAt(dobString, selectedRow, 2);
	            table_nv.setValueAt(gioiTinh, selectedRow, 3);
	            table_nv.setValueAt(chucVu, selectedRow, 4);
	            table_nv.setValueAt(diaChi, selectedRow, 5);
	        }
	    } else {
	        JOptionPane.showMessageDialog(null, "Lỗi cập nhật thông tin nhân viên");
	    }
	}


	private void xoaActions() {
	    String maNV = t_maNV_nv.getText();
	    DAO_NhanVien dao = new DAO_NhanVien();
	    NhanVien nv = new NhanVien(maNV, null, null, null, null, null, null);

	    int result = dao.xoa(nv);

	    if (result != 0) {
	        JOptionPane.showMessageDialog(null, "Đã xóa nhân viên");
	        int selectedRow = table_nv.getSelectedRow();
	        if (selectedRow != -1) {
	            model_nv.removeRow(selectedRow);
	        }
	    } else {
	        JOptionPane.showMessageDialog(null, "Lỗi xóa nhân viên");
	    }
	}


	private void themAction() {
		 String dobString = "";
		 Date dob = new Date();
		 String maNV = t_maNV_nv.getText();
		 String hoTen = t_hoTen_nv.getText();
		 try {
			dobString = t_ngaySinh_nv.getText();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			dob = dateFormat.parse(dobString);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Lỗi định dạng ngày tháng năm");
		} catch (Exception e2) {
			e2.printStackTrace();
		}				
	    String gioiTinh = gioiTinhComboBox.getSelectedItem().toString();
		String diaChi= t_diaChi.getText();
		String maPB = t_phongban_nv.getText();
		String chucVu = t_diaChi.getText();
		NhanVien nv = new NhanVien(maNV, hoTen, dob, gioiTinh, diaChi, maPB, chucVu);		
		DAO_NhanVien dao = new DAO_NhanVien();
		int result = dao.them(nv);
		if(result!=0) {
			JOptionPane.showMessageDialog(null, "Đã thêm nhân viên");
			String dataRow[] = {t_maNV_nv.getText(), t_hoTen_nv.getText(),
					dobString,  gioiTinhComboBox.getSelectedItem().toString() , t_phongban_nv.getText()
					, t_chucvu_nv.getText() , t_diaChi.getText() };
			model_nv.addRow(dataRow);
	    } else {
	    	JOptionPane.showMessageDialog(null, "Lỗi dữ liệu");
	    }
	}

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
//	        hienThiBaoCao(1,2000);
	  }
	
	
//	private static void hienThiBaoCao(int thang, int nam) { 
//	    DAOBaoCao daoBaoCao = new DAOBaoCao();
//	    ArrayList<BaoCao> danhSachBaoCao = daoBaoCao.layDuLieuBaoCao(thang, nam);
//
//	    StringBuilder message = new StringBuilder("Báo cáo tháng " + thang + " năm " + nam + ":\n\n");
//	    for (BaoCao baoCao : danhSachBaoCao) {
//	        message.append("Mã NV: ").append(baoCao.getMaNV()).append("\n");
//	        message.append("Họ và tên: ").append(baoCao.getHoTen()).append("\n");
//	        message.append("Tên công trình: ").append(baoCao.getTenCT()).append("\n");
//	        message.append("Số ngày công: ").append(baoCao.getSoNgayCong()).append("\n");
//	        message.append("------------------------------\n");
//	    }
//
//	    JOptionPane.showMessageDialog(null, message.toString(), "Báo cáo tháng " + thang + " năm " + nam, JOptionPane.INFORMATION_MESSAGE);
//	}

}
