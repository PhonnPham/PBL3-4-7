package QL_Muon_Sach_view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.border.LineBorder;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import gdDN.*;
public class ghi_nhan_muon_sach extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel(),panelMenu;
	private jT txtTieuDe,txtTacGia,txtTrangThaiSach,txtHoTen,txtEmail,txtSDT;
	private JDateChooser dateChooserMuon;
	private JComboBox comboBoxId;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ghi_nhan_muon_sach dialog = new ghi_nhan_muon_sach();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ghi_nhan_muon_sach() {
		setBounds(100, 100, 1006, 661);
		contentPanel.setBackground(new Color(69, 171, 148));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		panelMenu = new JPanel();
		panelMenu.setBackground(new Color(63, 133, 124));
		panelMenu.setBounds(0, 0, 246, 624);
		contentPanel.add(panelMenu);
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
	lbl6.setBounds(10, 559, 207, 55);
	panelMenu.add(lbl6);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		JLabel lblNewLabel = new JLabel("Ghi nhận mượn sách");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(296, 32, 224, 35);
		contentPanel.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(197, 197, 197));
		panel.setBounds(296, 130, 635, 412);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(250, 250, 250));
		panel_1.setBounds(0, 0, 316, 412);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Thông tin sách");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(86, 0, 128, 27);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Id sách");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(10, 41, 64, 27);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Tiêu đề sách");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(10, 106, 90, 27);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Số lượng");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(10, 174, 64, 27);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Tác giả");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(10, 248, 64, 27);
		panel_1.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Trạng thái sách");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_6.setBounds(10, 341, 111, 27);
		panel_1.add(lblNewLabel_6);
		
		txtTieuDe = new jT();
		txtTieuDe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTieuDe.setBorder(new EmptyBorder(10, 3, 5, 10));
		txtTieuDe.setBackground(new Color(250, 250, 250));
		txtTieuDe.setBounds(139, 92, 156, 40);
		panel_1.add(txtTieuDe);
		txtTieuDe.setColumns(10);
		
		JSpinner spinnerCount = new JSpinner();
		spinnerCount.setBounds(176, 176, 38, 27);
		panel_1.add(spinnerCount);
		
		txtTacGia = new jT();
		txtTacGia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTacGia.setBorder(new EmptyBorder(10, 3, 5, 10));
		txtTacGia.setBackground(new Color(250, 250, 250));
		txtTacGia.setBounds(139, 236, 156, 40);
		panel_1.add(txtTacGia);
		txtTacGia.setColumns(10);
		
		txtTrangThaiSach = new jT();
		txtTrangThaiSach.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTrangThaiSach.setBorder(new EmptyBorder(10, 3, 5, 10));
		txtTrangThaiSach.setBackground(new Color(250, 250, 250));
		txtTrangThaiSach.setBounds(139, 322, 156, 50);
		panel_1.add(txtTrangThaiSach);
		txtTrangThaiSach.setColumns(10);
		
		comboBoxId = new JComboBox();
		comboBoxId.setBounds(139, 44, 156, 25);
		panel_1.add(comboBoxId);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(250, 250, 250));
		panel_2.setBounds(319, 0, 316, 412);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_7 = new JLabel("Thông tin người mượn");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_7.setBounds(94, 0, 169, 27);
		panel_2.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Họ tên");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_8.setBounds(20, 41, 64, 27);
		panel_2.add(lblNewLabel_8);
		
		txtHoTen = new jT();
		txtHoTen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtHoTen.setBorder(new EmptyBorder(10, 3, 5, 10));
		txtHoTen.setBackground(new Color(250, 250, 250));
		txtHoTen.setBounds(115, 30, 156, 40);
		panel_2.add(txtHoTen);
		txtHoTen.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Email");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_9.setBounds(20, 106, 90, 27);
		panel_2.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Số điện thoại");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_10.setBounds(20, 174, 90, 27);
		panel_2.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("Ngày mượn");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_11.setBounds(20, 229, 90, 27);
		panel_2.add(lblNewLabel_11);
		
		txtEmail = new jT();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtEmail.setBorder(new EmptyBorder(10, 3, 5, 10));
		txtEmail.setBackground(new Color(250, 250, 250));
		txtEmail.setBounds(115, 90, 156, 50);
		panel_2.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtSDT = new jT();
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSDT.setBorder(new EmptyBorder(20, 3, 8, 30));
		txtSDT.setBackground(new Color(250, 250, 250));
		txtSDT.setBounds(115, 156, 156, 50);
		panel_2.add(txtSDT);
		txtSDT.setColumns(10);
		
		dateChooserMuon = new JDateChooser();
		dateChooserMuon.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dateChooserMuon.setDateFormatString("dd-MM-yyyy");
		dateChooserMuon.setBounds(20, 270, 287, 27);
		//Lấy ngày hiện tại
		Calendar cal  =  Calendar.getInstance();
		Date currentDate = cal.getTime();
		dateChooserMuon.setDate(currentDate);
		panel_2.add(dateChooserMuon);
		
		JLabel lblNewLabel_12 = new JLabel("Ngày trả");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_12.setBounds(20, 318, 90, 27);
		panel_2.add(lblNewLabel_12);
		
		JDateChooser dateChooserTra = new JDateChooser();
		dateChooserTra.setBounds(20, 360, 287, 27);
		panel_2.add(dateChooserTra);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(715, 557, 85, 21);
		contentPanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(838, 557, 85, 21);
		contentPanel.add(btnNewButton_1);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date ngayViDu = dateChooserMuon.getDate();
				JOptionPane.showMessageDialog(contentPanel, ngayViDu);
			}
		});
	}
}
