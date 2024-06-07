	package gdDN;
import Menu.*;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import gdDN.jP;
import gdDN.jT;

import java.awt.Component;
import Model.*;

public class DNhap extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblQL,lblTieuDe,lblLog,lblPass;
	private jT login;
	private jP pass;
	private JButton btnIconMat, btnDangNhap;
	private boolean isVisible = false;
	private char defaultEchoChar = '\u2022'; // '*' character
	private JFrame DN = this;
	public boolean check = false;
	private home parentpn;
	private	ThuthuDAO ttDAO;
	private panelMenu menu;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	
	
	public DNhap(home parentpn, panelMenu menu) {
		this.parentpn = parentpn;
		parentpn.setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 808, 549);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ttDAO = new ThuthuDAO();
		lblQL = new JLabel("Quản Lý Thư Viện");
		lblQL.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblQL.setHorizontalAlignment(SwingConstants.CENTER);
		//ImageIcon icon  = new ImageIcon("book.png");
		lblQL.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(DNhap.class.getResource("book.png"))));
		lblQL.setHorizontalTextPosition(SwingConstants.CENTER); // Căn icon theo chiều ngang
		lblQL.setVerticalTextPosition(SwingConstants.BOTTOM); // Hiển thị văn bản phía dưới icon
		lblQL.setOpaque(true); // Thêm dòng này để nhãn hiển thị màu nền
		lblQL.setForeground(new Color(255, 255, 255));
		lblQL.setBackground(new Color(91, 179, 137));
		lblQL.setBounds(0, 0, 313, 522);
		contentPane.add(lblQL);
		
		lblTieuDe = new JLabel("Đăng Nhập");
		lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblTieuDe.setForeground(new Color(49, 183, 102));
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setBounds(344, 65, 371, 60);
		contentPane.add(lblTieuDe);
		
		lblLog = new JLabel("Tên Đăng Nhập");
		lblLog.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLog.setForeground(new Color(49, 183, 102));
		lblLog.setBounds(342, 138, 129, 48);
		contentPane.add(lblLog);
		
		lblPass = new JLabel("Mật Khẩu");
		lblPass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPass.setForeground(new Color(49, 183, 102));
		lblPass.setBounds(344, 250, 140, 42);
		contentPane.add(lblPass);
		
		login = new jT();
		login.setFont(new Font("Tahoma", Font.PLAIN, 14));
		login.setBorder(new EmptyBorder(20, 3, 10,30));
		login.setBounds(344, 183, 345, 50);
		contentPane.add(login);
		login.setColumns(10);
		
		pass = new jP();
		pass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pass.setBorder(new EmptyBorder(20, 3, 10,30));
		pass.setBounds(344, 292, 345, 50);
		contentPane.add(pass);
		
		
		
		btnIconMat = new JButton("");
		btnIconMat.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		btnIconMat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isVisible = !isVisible;
				 if(isVisible)
				 {
					 pass.setEchoChar((char) 0);//Show password
					 btnIconMat.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(DNhap.class.getResource("icon_eye_off.png"))));
				 }
				 else
				 {
					 pass.setEchoChar(defaultEchoChar); // Hide password
					 btnIconMat.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(DNhap.class.getResource("icon_eye.png"))));
				 }
			}
		});
		
		btnIconMat.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnIconMat.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(DNhap.class.getResource("icon_eye.png"))));
		btnIconMat.setBackground(new Color(255, 255, 255));
		btnIconMat.setBounds(693, 307, 34, 30);
		contentPane.add(btnIconMat);
		
		btnDangNhap = new JButton("Đăng Nhập");
		btnDangNhap.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDangNhap.setForeground(new Color(255, 255, 255));
		btnDangNhap.setBackground(new Color(74, 196, 125));
		btnDangNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = login.getText();
                String password = new String(pass.getPassword());
                if (authenticate1(username, password) ) {
                    JOptionPane.showMessageDialog(contentPane, "Đăng Nhập thành công với vai trò thủ thư", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
                    DN.setVisible(false);
                    setCheck(true);
                    menu.check = isCheck();
                    Bientoancuc.tt=ttDAO.getInstance().selectByName(username);
                    parentpn.Flag = false;
                    parentpn.setVisible(true);
                    parentpn.panelMenu.lbName.setText(Bientoancuc.tt.get_hoten());
                } else if(authenticate2(username, password)) {
                	JOptionPane.showMessageDialog(contentPane, "Đăng Nhập thành công với vai trò ADMIN", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
                    DN.setVisible(false);
                    setCheck(true);
                    Bientoancuc.tt=ttDAO.getInstance().selectByName("ADMIN");
                    Bientoancuc.ad = new Admin(username,password);
                    setCheck(true);
                    menu.check = isCheck();
                    parentpn.Flag = true;
                    parentpn.AddPanel(new Dashboard(parentpn));
                    parentpn.setVisible(true);
                    parentpn.panelMenu.lbName.setText(Bientoancuc.tt.get_hoten());
                    }
                
                	else {
                
                    JOptionPane.showMessageDialog(contentPane, "Tên đăng nhập hoặc mật khẩu không đúng", "Thông Báo", JOptionPane.ERROR_MESSAGE);
                }
			}
		});
		btnDangNhap.setBounds(344, 377, 345, 80);
		contentPane.add(btnDangNhap);
	
		
	}
	public boolean authenticate1(String username, String password) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pbl3", "root", "");
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM thuthu WHERE Username_tt = ? AND Password_tt = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            return rs.next(); 
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
	public boolean authenticate2(String username, String password) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pbl3", "root", "");
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM admin WHERE Username_ad = ? AND Password_ad = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            return rs.next(); 
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
	public boolean isCheck() {
		return check;
	}
	public void setCheck(boolean check) {
		this.check = check;
	}
}
