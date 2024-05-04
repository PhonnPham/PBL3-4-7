package Menu;

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

import gdDN.*;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JDialog;

import Model.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class XemTTSach extends JDialog {

	private static final long serialVersionUID = 1L;
	private panelQlySach parentPn;
	private JPanel contentPane;
	private jT txtTieuDeSach;
	private jT txtTacGia;
	private jT txtNhaXB;
	private jT txtNamXB;
	private jT txtSoLuongSach;
	private Sach sach;
	private jT txtTheLoai;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					JFrame parent = new JFrame();
//					Sach sach = new Sach();
//					XemTTSach dialog = new XemTTSach(parent ,true,sach);
//					dialog.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public XemTTSach(JFrame parent, boolean modal, Sach sach, panelQlySach pn)
	{
		super(parent, modal);
		   this.sach = sach;
		   this.parentPn = pn;
		    initComponents();
		    txtTieuDeSach.setText(sach.get_tensach());
		    txtNhaXB.setText(sach.get_nhaxb());
		    txtNamXB.setText(sach.get_namxb());
		    txtTheLoai.setText(sach.get_theloai());
		    txtSoLuongSach.setText(String.valueOf(sach.get_soluong()));
		    txtTacGia.setText(sach.get_tacgia());
		    
		    JButton btnNewButton = new JButton("Thoát");
		    btnNewButton.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		dispose();
		    	}
		    });
		    btnNewButton.setBounds(628, 456, 137, 40);
		    contentPane.add(btnNewButton);
		    
		    
		   
	}
	
	private void initComponents() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 842, 614);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(89, 185, 175));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Thông tin chi tiết");
		lblNewLabel.setBackground(new Color(250, 250, 250));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setForeground(new Color(250, 250, 250));
		lblNewLabel.setBounds(294, 31, 239, 48);
		contentPane.add(lblNewLabel);
		
		JPanel panelThongTin = new JPanel();
		panelThongTin.setBackground(new Color(250, 250, 250));
		panelThongTin.setBounds(67, 106, 724, 293);
		contentPane.add(panelThongTin);
		panelThongTin.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Thông tin sách");
		lblNewLabel_1.setForeground(new Color(89, 185, 175));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(222, 10, 230, 38);
		panelThongTin.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Tác giả :");
		lblNewLabel_2.setForeground(new Color(89, 185, 175));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(32, 149, 111, 38);
		panelThongTin.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Số lượng :");
		lblNewLabel_3.setForeground(new Color(89, 185, 175));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(32, 216, 76, 38);
		panelThongTin.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Nhà xuất bản :");
		lblNewLabel_4.setForeground(new Color(89, 185, 175));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(400, 150, 111, 37);
		panelThongTin.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Tiêu đề sách :");
		lblNewLabel_5.setForeground(new Color(89, 185, 175));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(32, 76, 111, 25);
		panelThongTin.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Thể loại :");
		lblNewLabel_6.setForeground(new Color(89, 185, 175));
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_6.setBounds(400, 64, 76, 37);
		panelThongTin.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Năm xuất bản :");
		lblNewLabel_7.setForeground(new Color(89, 185, 175));
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_7.setBounds(400, 217, 111, 37);
		panelThongTin.add(lblNewLabel_7);
		
		txtTieuDeSach = new jT();
		txtTieuDeSach.setEditable(false);
		txtTieuDeSach.setForeground(new Color(110, 110, 110));
		txtTieuDeSach.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTieuDeSach.setBackground(new Color(250, 250, 250));
		txtTieuDeSach.setBorder(new EmptyBorder(20, 3, 5, 30));
		txtTieuDeSach.setBounds(131, 58, 243, 45);
		panelThongTin.add(txtTieuDeSach);
		txtTieuDeSach.setColumns(10);
		
		txtTacGia = new jT();
		txtTacGia.setEditable(false);
		txtTacGia.setForeground(new Color(110, 110, 110));
		txtTacGia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTacGia.setBackground(new Color(250, 250, 250));
		txtTacGia.setBorder(new EmptyBorder(20, 3, 5, 30));
		txtTacGia.setBounds(131, 138, 243, 48);
		panelThongTin.add(txtTacGia);
		txtTacGia.setColumns(10);
		
		txtNhaXB = new jT();
		txtNhaXB.setEditable(false);
		txtNhaXB.setForeground(new Color(110, 110, 110));
		txtNhaXB.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNhaXB.setBackground(new Color(250, 250, 250));
		txtNhaXB.setBorder(new EmptyBorder(20, 3, 5, 30));
		txtNhaXB.setBounds(510, 138, 200, 48);
		panelThongTin.add(txtNhaXB);
		txtNhaXB.setColumns(10);
		
		txtNamXB = new jT();
		txtNamXB.setEditable(false);
		txtNamXB.setForeground(new Color(110, 110, 110));
		txtNamXB.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNamXB.setBackground(new Color(250, 250, 250));
		txtNamXB.setBorder(new EmptyBorder(20, 3, 5, 30));
		txtNamXB.setBounds(510, 209, 200, 48);
		panelThongTin.add(txtNamXB);
		txtNamXB.setColumns(10);
		
		txtSoLuongSach = new jT();
		txtSoLuongSach.setEditable(false);
		txtSoLuongSach.setForeground(new Color(110, 110, 110));
		txtSoLuongSach.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSoLuongSach.setBackground(new Color(250, 250, 250));
		txtSoLuongSach.setBorder(new EmptyBorder(20, 3, 5, 30));
		txtSoLuongSach.setBounds(131, 209, 243, 48);
		panelThongTin.add(txtSoLuongSach);
		txtSoLuongSach.setColumns(10);
		
		txtTheLoai = new jT();
		txtTheLoai.setEditable(false);
		txtTheLoai.setForeground(new Color(110, 110, 110));
		txtTheLoai.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTheLoai.setBackground(new Color(250, 250, 250));
		txtTheLoai.setBorder(new EmptyBorder(20, 3, 5, 30));
		txtTheLoai.setBounds(510, 53, 200, 48);
		panelThongTin.add(txtTheLoai);
		txtTheLoai.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Danh sanh sách trong thư viện");
		lblNewLabel_8.setForeground(new Color(250, 250, 250));
		lblNewLabel_8.setBackground(new Color(250, 250, 250));
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_8.setBounds(294, 423, 281, 33);
		contentPane.add(lblNewLabel_8);
		
		JButton btnBack = new JButton("");
	    btnBack.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		dispose();
	    		//QlySach qlsach =  new  QlySach();
	    		//qlsach.setVisible(true);
	    		//qlsach.setLocationRelativeTo(null);
	    		
	    		
	    	}
	    });
	    btnBack.setIcon(new ImageIcon("C:\\Users\\hoang\\OneDrive\\Documents\\Pictures\\Ảnh cho pbl3\\arrow back.png"));
	    btnBack.setBackground(new Color(65, 141, 124));
	    btnBack.setBounds(922, 43, 52, 28);
	    contentPane.add(btnBack);
		
		
	}
}
