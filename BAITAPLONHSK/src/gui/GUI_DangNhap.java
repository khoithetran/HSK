package gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI_DangNhap extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public GUI_DangNhap() {
        setTitle("Đăng nhập");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        initComponents();
        centerFrame();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel usernameLabel = new JLabel("Tên đăng nhập:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(usernameLabel, gbc);

        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(200, 25));
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("Mật khẩu:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passwordLabel, gbc);

        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(200, 25));
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(passwordField, gbc);

        JButton loginButton = new JButton("Đăng nhập");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(loginButton, gbc);

        // Thêm ActionListener cho nút đăng nhập
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Kiểm tra thông tin đăng nhập ở đây
                if (isValidLogin()) {
                    // Nếu thông tin hợp lệ, chuyển đến trang Quản lý công trình
                    GUI_main quanLyCongTrinh = new GUI_main();
                    quanLyCongTrinh.setVisible(true);
                    dispose(); // Đóng cửa sổ đăng nhập sau khi chuyển trang
                } else {
                    JOptionPane.showMessageDialog(GUI_DangNhap.this, "Đăng nhập không thành công. Thử lại!");
                }
            }
        });

        getContentPane().add(panel);
        pack();
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

    private boolean isValidLogin() {
        // Thực hiện kiểm tra thông tin đăng nhập ở đây, bạn có thể thay thế bằng logic kiểm tra của bạn
        // Ví dụ đơn giản: Nếu username là "admin" và password là "password", coi như đăng nhập thành công
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        return "admin".equals(username) && "password".equals(password);
    }
    
    public static void main(String[] args) {
		new GUI_DangNhap();
	}
    
}
