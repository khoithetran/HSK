package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GUI_DangNhapHT extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public GUI_DangNhapHT() {
        setTitle("Đăng nhập");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        initComponents();
        centerFrame();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));

        panel.add(new JLabel("Tên đăng nhập:"));
        usernameField = new JTextField();
//        usernameField.setPreferredSize(new Dimension(200, 25));
        panel.add(usernameField);

        panel.add(new JLabel("Mật khẩu:"));
        passwordField = new JPasswordField();
//        passwordField.setPreferredSize(new Dimension(200, 25));

        panel.add(passwordField);

        JButton loginButton = new JButton("Đăng nhập");
        panel.add(loginButton);

        // Thêm ActionListener cho nút đăng nhập và xử lý sự kiện Enter
        ActionListener loginAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        };
        loginButton.addActionListener(loginAction);

        // Xử lý sự kiện Enter
        panel.registerKeyboardAction(loginAction,
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        getContentPane().add(panel);
        setPreferredSize(new Dimension(400, 150));
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

    private void handleLogin() {
        if (isValidLogin()) {
            GUI_main quanLyCongTrinh = new GUI_main();
            quanLyCongTrinh.setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Đăng nhập không thành công. Thử lại!");
        }
    }

    private boolean isValidLogin() {
        String username = usernameField.getText();
        char[] password = passwordField.getPassword();
        // Thực hiện kiểm tra thông tin đăng nhập ở đây, bạn có thể thay thế bằng logic kiểm tra của bạn
        return "admin".equals(username) && "password".equals(new String(password));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI_DangNhap().setVisible(true);
            }
        });
    }
}
