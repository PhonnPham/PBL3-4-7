package gdDN;
import Menu.*;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DNhap frame = new DNhap();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	
	public DNhap() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 808, 549);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
				String a = "abcde";
				String b = "12345678";
				
				String log = login.getText();
				String pas = new String( pass.getPassword());
				if(log.isEmpty() || pas.isEmpty())
				{
					JOptionPane.showMessageDialog(contentPane, "Chưa nhập mật khẩu hoặc tài khoản", "Lỗi",JOptionPane.ERROR_MESSAGE);

				}
				else if(pas.length() < 8)
				{
					JOptionPane.showMessageDialog(contentPane, "Độ dài tối thiểu của mật khẩu phải là 8 ký tự trở lên", "Thông Báo",JOptionPane.ERROR_MESSAGE);
				}
				else if(log.endsWith(a) && pas.endsWith(b))
				{
					JOptionPane.showMessageDialog(contentPane, "Đăng Nhập thành công", "Thông Báo",JOptionPane.INFORMATION_MESSAGE);
					DN.setVisible(false);
					
					home h =new home();
					h.setVisible(true);
					h.setLocationRelativeTo(null);
				}
				else
				{
					JOptionPane.showMessageDialog(contentPane, "TK hoặc MK chưa chính xác", "Thông Báo",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnDangNhap.setBounds(344, 377, 345, 80);
		contentPane.add(btnDangNhap);
	
		
	}
}
