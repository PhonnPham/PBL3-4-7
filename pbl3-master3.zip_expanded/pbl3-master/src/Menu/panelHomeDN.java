package Menu;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import gdDN.DNhap;

import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class panelHomeDN extends JPanel {

	private static final long serialVersionUID = 1L;
	private home parentFrame;
	/**
	 * Create the panel.
	 */
	private JPanel contentPane;
	private CheckboxGroup cg;
	private DNhap dn;
	//private JFrame home = this; // tham chiếu tới Fram hiện tại
	
	/**
	 * Create the frame.
	 */
	public panelHomeDN(home parentFrame) {
		new JPanel();
		this.setBounds(238, 0, 580, 575);
		this.parentFrame = parentFrame;
		this.setLayout(null);
		
		initComponets();
	}
	public void initComponets() {
		JLabel lblNewLabel = new JLabel("Thư viện Sách PBL3");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(16, 6, 295, 59);
		this.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Chào mừng bạn đến với thư viện, nơi kết nối con người với tri thức. \r\n");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(53, 105, 559, 23);
		this.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Với hàng nghìn cuốn sách đa dạng, chúng tôi mời bạn khám phá thế giới.");
		lblNewLabel_1_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(53, 131, 559, 23);
		this.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("Lời chào đến với bạn đọc");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(16, 56, 245, 39);
		this.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Một số tác phẩm tiêu biểu");
		lblNewLabel_2_1.setForeground(Color.RED);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2_1.setBounds(16, 164, 245, 39);
		this.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_3 = new JLabel("Dế Mèn Phiêu Lưu Ký - Tô Hoài");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_3.setBounds(16, 191, 563, 133);
		this.add(lblNewLabel_3);
		ImageIcon image1 = new ImageIcon("C:\\Users\\LENOVO\\Downloads\\demen.jpg");
		lblNewLabel_3.setIcon(image1);
		
		JLabel lblNewLabel_4 = new JLabel("Tắt Đèn - Ngô Tất Tố");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\LENOVO\\Downloads\\tatden.jpg"));
		lblNewLabel_4.setBounds(16, 327, 563, 114);
		this.add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("Truyện Kiều - Nguyễn Du");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_4_1.setIcon(new ImageIcon("C:\\Users\\LENOVO\\Downloads\\TruyenKieu.jpg"));
		lblNewLabel_4_1.setBounds(16, 447, 563, 114);
		this.add(lblNewLabel_4_1);
		
		JButton btnNewButton = new JButton("Tra cứu sách");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				home.setVisible(false);
//				timkiem_sach timkiem =  new timkiem_sach();
//				timkiem.setVisible(true);
//				timkiem.setLocationRelativeTo(null);
				
			}
		});
		btnNewButton.setBounds(292, 20, 160, 31);
		this.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Đăng nhập");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dn = new DNhap(parentFrame);
				dn.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(453, 20, 126, 31);
		this.add(btnNewButton_1);
		cg = new CheckboxGroup();
	}
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public panelHome() {
//		new JPanel();
//		this.setBackground(new Color(129, 203, 196));
//		this.setBounds(238, 0, 580, 575);
//		setLayout(null);
//		
//		JButton btnDangNhap = new JButton("Đăng Nhập");
//		btnDangNhap.setBounds(419, 19, 132, 45);
//		add(btnDangNhap);
//		
//
//	}

}
