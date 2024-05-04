package QL_Muon_Sach_view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Menu.QlySach;
import gdDN.DNhap;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;
import gdDN.*;
public class ghi_nhan_tra_sach extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane,panelMenu;
	private JTextField txtPhatTre,txtGCTre,txtPhatHong,txtGCHong;
	private jT txtTieuDe,txtSoLuong,txtTenNM,txtThuThu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ghi_nhan_tra_sach frame = new ghi_nhan_tra_sach();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ghi_nhan_tra_sach() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1027, 660);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(84, 186, 163));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelMenu = new JPanel();
		panelMenu.setBounds(0, 0, 249, 619);
		panelMenu.setBackground(new Color(63, 133, 124));
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);
		
		JLabel lbl1 = new JLabel("Quản lý thư viện");
		lbl1.setForeground(new Color(255, 255, 255));
		
		lbl1.setIcon(new ImageIcon("C:\\Users\\hoang\\OneDrive\\Documents\\Pictures\\Ảnh cho pbl3\\manager man.png"));
		lbl1.setHorizontalTextPosition(SwingConstants.CENTER);
		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1.setVerticalTextPosition(SwingConstants.BOTTOM); // Hiển thị văn bản phía dưới icon
		lbl1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl1.setBounds(33, 23, 148, 124);
		panelMenu.add(lbl1);
		
		JLabel lbl4 = new JLabel("Quản lý mượn-trả sách");
		lbl4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl4.setForeground(new Color(255, 255, 255));
		lbl4.setIcon(new ImageIcon("C:\\Users\\hoang\\OneDrive\\Documents\\Pictures\\Ảnh cho pbl3\\icons8-list-48.png"));
		lbl4.setBounds(10, 348, 227, 41);
		panelMenu.add(lbl4);
		
		JLabel lbl2 = new JLabel("Trang chủ");
		lbl2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl2.setIcon(new ImageIcon("C:\\Users\\hoang\\OneDrive\\Documents\\Pictures\\Ảnh cho pbl3\\home.png"));
		lbl2.setForeground(new Color(255, 255, 255));
		lbl2.setBackground(new Color(255, 255, 255));
		lbl2.setBounds(10, 185, 227, 50);
		panelMenu.add(lbl2);
		
		JLabel lbl3 = new JLabel("Quản lý kho sách");
		lbl3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		lbl3.setForeground(new Color(255, 255, 255));
		lbl3.setIcon(new ImageIcon("C:\\Users\\hoang\\OneDrive\\Documents\\Pictures\\Ảnh cho pbl3\\book stack.png"));
		lbl3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl3.setBounds(10, 263, 207, 55);
		panelMenu.add(lbl3);
		
		JLabel lbl5 = new JLabel("Quản lý người mượn");
		lbl5.setIcon(new ImageIcon("C:\\Users\\hoang\\OneDrive\\Documents\\Pictures\\Ảnh cho pbl3\\people manage.png"));
		lbl5.setForeground(new Color(255, 255, 255));
		lbl5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl5.setBounds(10, 420, 227, 55);
		panelMenu.add(lbl5);
		
		JLabel lbl6 = new JLabel("Đăng  xuất");
		lbl6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				
			}
	});
	lbl6.setIcon(new ImageIcon("C:\\Users\\hoang\\OneDrive\\Documents\\Pictures\\Ảnh cho pbl3\\log out.png"));
	lbl6.setFont(new Font("Tahoma", Font.PLAIN, 16));
	lbl6.setForeground(new Color(255, 255, 255));
	lbl6.setBounds(10, 554, 207, 55);
	panelMenu.add(lbl6);
	
	JLabel lblNewLabel = new JLabel("Ghi nhận trả sách");
	lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
	lblNewLabel.setBounds(268, 20, 208, 42);
	contentPane.add(lblNewLabel);
	
	JPanel panel = new JPanel();
	panel.setBounds(268, 79, 727, 487);
	panel.setBackground(new Color(191, 191, 191));
	contentPane.add(panel);
	panel.setLayout(null);
	
	JPanel panelTTTra = new JPanel();
	panelTTTra.setBackground(new Color(250, 250, 250));
	panelTTTra.setBounds(0, 0, 352, 486);
	panel.add(panelTTTra);
	panelTTTra.setLayout(null);
	
	JLabel lblNewLabel_1 = new JLabel("Thông tin trả sách");
	lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
	lblNewLabel_1.setBounds(111, 0, 135, 32);
	panelTTTra.add(lblNewLabel_1);
	
	JLabel lblNewLabel_3 = new JLabel("Id sách");
	lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
	lblNewLabel_3.setBounds(10, 45, 60, 32);
	panelTTTra.add(lblNewLabel_3);
	
	JComboBox comboBoxIDSach = new JComboBox();
	comboBoxIDSach.setBounds(160, 51, 135, 26);
	panelTTTra.add(comboBoxIDSach);
	
	JLabel lblNewLabel_4 = new JLabel("Mã người mượn");
	lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
	lblNewLabel_4.setBounds(10, 117, 107, 32);
	panelTTTra.add(lblNewLabel_4);
	
	JComboBox comboBoxIDNguoi = new JComboBox();
	comboBoxIDNguoi.setBounds(160, 123, 135, 26);
	panelTTTra.add(comboBoxIDNguoi);
	
	JLabel lblNewLabel_5 = new JLabel("Tình trạng sách (%)");
	lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
	lblNewLabel_5.setBounds(10, 183, 142, 32);
	panelTTTra.add(lblNewLabel_5);
	
	JComboBox comboBox = new JComboBox();
	comboBox.setBounds(160, 189, 135, 26);
	panelTTTra.add(comboBox);
	
	JLabel lblNewLabel_6 = new JLabel("Phạt (nếu có)");
	lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
	lblNewLabel_6.setBounds(10, 253, 99, 26);
	panelTTTra.add(lblNewLabel_6);
	
	JLabel lblNewLabel_7 = new JLabel("Ghi chú");
	lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
	lblNewLabel_7.setBounds(119, 253, 67, 26);
	panelTTTra.add(lblNewLabel_7);
	
	txtPhatTre = new JTextField();
	txtPhatTre.setBounds(10, 296, 93, 39);
	panelTTTra.add(txtPhatTre);
	txtPhatTre.setColumns(10);
	
	txtGCTre = new JTextField();
	txtGCTre.setBounds(111, 296, 184, 39);
	panelTTTra.add(txtGCTre);
	txtGCTre.setColumns(10);
	
	txtPhatHong = new JTextField();
	txtPhatHong.setBounds(10, 345, 93, 39);
	panelTTTra.add(txtPhatHong);
	txtPhatHong.setColumns(10);
	
	txtGCHong = new JTextField();
	txtGCHong.setBounds(111, 345, 184, 39);
	panelTTTra.add(txtGCHong);
	txtGCHong.setColumns(10);
	
	JButton btnNewButton = new JButton("Tìm kiếm");
	btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
	btnNewButton.setBackground(new Color(250, 250, 250));
	btnNewButton.setBounds(61, 429, 234, 32);
	panelTTTra.add(btnNewButton);
	
	JPanel panelTTNguoiMuon = new JPanel();
	panelTTNguoiMuon.setBackground(new Color(250, 250, 250));
	panelTTNguoiMuon.setBounds(357, 0, 368, 486);
	panel.add(panelTTNguoiMuon);
	panelTTNguoiMuon.setLayout(null);
	
	JLabel lblNewLabel_2 = new JLabel("Thông tin mượn sách");
	lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
	lblNewLabel_2.setBounds(115, 0, 156, 32);
	panelTTNguoiMuon.add(lblNewLabel_2);
	
	JLabel lblNewLabel_8 = new JLabel("Tiêu đề sách");
	lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
	lblNewLabel_8.setBounds(20, 45, 86, 42);
	panelTTNguoiMuon.add(lblNewLabel_8);
	
	txtTieuDe = new jT();
	txtTieuDe.setFont(new Font("Tahoma", Font.PLAIN, 15));
	txtTieuDe.setBackground(new Color(250, 250, 250));
	txtTieuDe.setBorder(new EmptyBorder(20, 3, 5, 10));
	txtTieuDe.setBounds(149, 39, 170, 44);
	panelTTNguoiMuon.add(txtTieuDe);
	txtTieuDe.setColumns(10);
	
	JLabel lblNewLabel_9 = new JLabel("Số lượng");
	lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 15));
	lblNewLabel_9.setBounds(20, 117, 73, 37);
	panelTTNguoiMuon.add(lblNewLabel_9);
	
	txtSoLuong = new jT();
	txtSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 15));
	txtSoLuong.setBackground(new Color(250, 250, 250));
	txtSoLuong.setBorder(new EmptyBorder(20, 3, 5, 10));
	txtSoLuong.setBounds(149, 107, 170, 44);
	panelTTNguoiMuon.add(txtSoLuong);
	txtSoLuong.setColumns(10);
	
	JLabel lblNewLabel_10 = new JLabel("Tên người mượn");
	lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 15));
	lblNewLabel_10.setBounds(20, 183, 113, 32);
	panelTTNguoiMuon.add(lblNewLabel_10);
	
	txtTenNM = new jT();
	txtTenNM.setFont(new Font("Tahoma", Font.PLAIN, 15));
	txtTenNM.setBackground(new Color(250, 250, 250));
	txtTenNM.setBorder(new EmptyBorder(20, 3, 5, 10));
	txtTenNM.setBounds(149, 171, 170, 44);
	panelTTNguoiMuon.add(txtTenNM);
	txtTenNM.setColumns(10);
	
	JLabel lblNewLabel_11 = new JLabel("Thủ thư xác nhận");
	lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 15));
	lblNewLabel_11.setBounds(20, 249, 121, 32);
	panelTTNguoiMuon.add(lblNewLabel_11);
	
	txtThuThu = new jT();
	txtThuThu.setFont(new Font("Tahoma", Font.PLAIN, 15));
	txtThuThu.setBackground(new Color(250, 250, 250));
	txtThuThu.setBorder(new EmptyBorder(20, 3, 5, 10));
	txtThuThu.setBounds(149, 236, 170, 44);
	panelTTNguoiMuon.add(txtThuThu);
	txtThuThu.setColumns(10);
	
	JLabel lblNewLabel_12 = new JLabel("Ngày mượn");
	lblNewLabel_12.setFont(new Font("Tahoma", Font.PLAIN, 15));
	lblNewLabel_12.setBounds(20, 292, 86, 32);
	panelTTNguoiMuon.add(lblNewLabel_12);
	
	JDateChooser dateChooserNM = new JDateChooser();
	dateChooserNM.setFont(new Font("Tahoma", Font.PLAIN, 15));
	dateChooserNM.setBounds(20, 334, 244, 31);
	panelTTNguoiMuon.add(dateChooserNM);
	
	JLabel lblNewLabel_13 = new JLabel("Ngày trả");
	lblNewLabel_13.setFont(new Font("Tahoma", Font.PLAIN, 15));
	lblNewLabel_13.setBounds(20, 375, 86, 32);
	panelTTNguoiMuon.add(lblNewLabel_13);
	
	JDateChooser dateChooserNT = new JDateChooser();
	dateChooserNT.setFont(new Font("Tahoma", Font.PLAIN, 15));
	dateChooserNT.setBounds(20, 422, 244, 31);
	panelTTNguoiMuon.add(dateChooserNT);
	}
}
