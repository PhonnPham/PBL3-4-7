package Menu;
import gdDN.*;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelHometest extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CheckboxGroup cg;
	private DNhap dn;
	private JFrame home = this; // tham chiếu tới Fram hiện tại
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					home frame = new home();
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
	public PanelHometest() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 842, 614);
			contentPane = new JPanel();
	
			setContentPane(contentPane);
			contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Thư viện Sách PBL3");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(255, 0, 295, 59);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Chào mừng bạn đến với thư viện, nơi kết nối con người với tri thức. \r\n");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(292, 99, 559, 23);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Với hàng nghìn cuốn sách đa dạng, chúng tôi mời bạn khám phá thế giới.");
		lblNewLabel_1_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(292, 125, 559, 23);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("Lời chào đến với bạn đọc");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(255, 50, 245, 39);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Một số tác phẩm tiêu biểu");
		lblNewLabel_2_1.setForeground(Color.RED);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2_1.setBounds(255, 158, 245, 39);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_3 = new JLabel("Dế Mèn Phiêu Lưu Ký - Tô Hoài");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_3.setBounds(255, 185, 563, 133);
		contentPane.add(lblNewLabel_3);
		ImageIcon image1 = new ImageIcon("C:\\Users\\LENOVO\\Downloads\\demen.jpg");
		lblNewLabel_3.setIcon(image1);
		
		JLabel lblNewLabel_4 = new JLabel("Tắt Đèn - Ngô Tất Tố");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\LENOVO\\Downloads\\tatden.jpg"));
		lblNewLabel_4.setBounds(255, 321, 563, 114);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("Truyện Kiều - Nguyễn Du");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_4_1.setIcon(new ImageIcon("C:\\Users\\LENOVO\\Downloads\\TruyenKieu.jpg"));
		lblNewLabel_4_1.setBounds(255, 441, 563, 114);
		contentPane.add(lblNewLabel_4_1);
		
		JButton btnNewButton = new JButton("Tra cứu sách");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				home.setVisible(false);
//				timkiem_sach timkiem =  new timkiem_sach();
//				timkiem.setVisible(true);
//				timkiem.setLocationRelativeTo(null);
				
			}
		});
		btnNewButton.setBounds(522, 20, 160, 31);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Đăng nhập");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//	dn = new DNhap(parentFrame);
			//	dn.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(692, 20, 126, 31);
		contentPane.add(btnNewButton_1);
		cg = new CheckboxGroup();
	}
	
}
