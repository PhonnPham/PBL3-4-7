package QL_Muon_Sach_view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import Menu.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
public class CapNhatTTPhieu extends JDialog {

	private static final long serialVersionUID = 1L;
	private panelQlymuon parentPn;
	private JPanel contentPane;
	private jT txtTieuDeSach;
	private jT txtSoLuongSach;
	private jT txtTinhtrang;
	private jT txtIDphieu;
	private jT txtIDSach;
	private jT txtIDPhieu;
	private jT txtIDDocGia;
	private jT txtTenDocGia;
	JDateChooser dateChooserMuon;
	JDateChooser dateChooserTra;
	private Sach sach;
	private phieumuon phieu;
	private Docgia docgia;

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

	public CapNhatTTPhieu(JFrame parent, boolean modal, phieumuon phieu,Sach sach, Docgia docgia, panelQlymuon pn)
	{
		super(parent, modal);
		   this.sach = sach; this.phieu = phieu; this.docgia = docgia;
		   this.parentPn = pn;
		    initComponents();
		    txtTieuDeSach.setText(sach.get_tensach());
		    txtIDSach.setText(String.valueOf(sach.get_id_sach()));
		    txtSoLuongSach.setText(String.valueOf(phieu.get_soluong()));
		    
		    txtIDDocGia.setText(String.valueOf(docgia.get_id()));
		    txtTenDocGia.setText(docgia.get_hoten());
		    
		    txtIDphieu.setText(String.valueOf(phieu.get_id_phieu()));
		    dateChooserMuon.setDate(phieu.get_ngaymuon());
		    dateChooserTra.setDate(phieu.get_ngaytra());
		    txtTinhtrang.setText(String.valueOf(phieu.getGiveBookBack()));

		    
	}
	
	private void initComponents() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 842, 614);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(89, 185, 175));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cập Nhật Thông tin phiếu");
		lblNewLabel.setBackground(new Color(250, 250, 250));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setForeground(new Color(250, 250, 250));
		lblNewLabel.setBounds(39, 31, 369, 48);
		contentPane.add(lblNewLabel);
		
		JPanel panelThongTin = new JPanel();
		panelThongTin.setBackground(new Color(250, 250, 250));
		panelThongTin.setBounds(24, 91, 746, 390);
		contentPane.add(panelThongTin);
		panelThongTin.setLayout(null);
		
		JPanel panelSach = new JPanel();
		panelSach.setBounds(6, 6, 382, 378);
		panelThongTin.add(panelSach);
		panelSach.setLayout(null);
		 
		panelThongTin.add(panelSach);
		JLabel lblNewLabel_1 = new JLabel("Thông tin Sách Mượn");
		lblNewLabel_1.setForeground(new Color(89, 185, 175));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(85, 6, 230, 38);
		panelSach.add(lblNewLabel_1);
		
		JLabel lblNewLabel_6 = new JLabel("ID Độc giả :");
		lblNewLabel_6.setForeground(new Color(89, 185, 175));
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_6.setBounds(400, 64, 98, 37);
		panelSach.add(lblNewLabel_6);
		
		txtTieuDeSach = new jT();
		txtTieuDeSach.setEditable(false);
		txtTieuDeSach.setForeground(new Color(110, 110, 110));
		txtTieuDeSach.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTieuDeSach.setBackground(new Color(250, 250, 250));
		txtTieuDeSach.setBorder(new EmptyBorder(20, 3, 5, 30));
		txtTieuDeSach.setBounds(129, 100, 230, 43);
		panelSach.add(txtTieuDeSach);
		txtTieuDeSach.setColumns(10);
		
		
		JLabel lblNewLabel_5_1 = new JLabel("Tiêu đề sách :");
		lblNewLabel_5_1.setForeground(new Color(89, 185, 175));
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5_1.setBounds(20, 106, 111, 25);
		panelSach.add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_5_1_1 = new JLabel("ID Sách :");
		lblNewLabel_5_1_1.setForeground(new Color(89, 185, 175));
		lblNewLabel_5_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5_1_1.setBounds(20, 56, 111, 25);
		panelSach.add(lblNewLabel_5_1_1);
		
		txtIDSach = new jT();
		txtIDSach.setEditable(false);
		txtIDSach.setText((String) null);
		txtIDSach.setForeground(new Color(110, 110, 110));
		txtIDSach.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtIDSach.setColumns(10);
		txtIDSach.setBorder(new EmptyBorder(20, 3, 5, 30));
		txtIDSach.setBackground(new Color(250, 250, 250));
		txtIDSach.setBounds(129, 45, 230, 43);
		panelSach.add(txtIDSach);
		
		JLabel lblNewLabel_1_1 = new JLabel("Thông tin Độc giả");
		lblNewLabel_1_1.setBounds(59, 190, 230, 38);
		panelSach.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(new Color(89, 185, 175));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblNewLabel_6_1 = new JLabel("ID Độc giả :");
		lblNewLabel_6_1.setBounds(30, 240, 98, 37);
		panelSach.add(lblNewLabel_6_1);
		lblNewLabel_6_1.setForeground(new Color(89, 185, 175));
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		txtIDDocGia = new jT();
		txtIDDocGia.setBounds(129, 240, 230, 43);
		panelSach.add(txtIDDocGia);
		txtIDDocGia.setText((String) null);
		txtIDDocGia.setForeground(new Color(110, 110, 110));
		txtIDDocGia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtIDDocGia.setEditable(false);
		txtIDDocGia.setColumns(10);
		txtIDDocGia.setBorder(new EmptyBorder(20, 3, 5, 30));
		txtIDDocGia.setBackground(new Color(250, 250, 250));
		
		txtTenDocGia = new jT();
		txtTenDocGia.setBounds(129, 295, 230, 43);
		panelSach.add(txtTenDocGia);
		txtTenDocGia.setText((String) null);
		txtTenDocGia.setForeground(new Color(110, 110, 110));
		txtTenDocGia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTenDocGia.setEditable(false);
		txtTenDocGia.setColumns(10);
		txtTenDocGia.setBorder(new EmptyBorder(20, 3, 5, 30));
		txtTenDocGia.setBackground(new Color(250, 250, 250));
		
		JLabel lblNewLabel_6_1_1 = new JLabel("Tên Đọc giả :");
		lblNewLabel_6_1_1.setBounds(30, 298, 98, 37);
		panelSach.add(lblNewLabel_6_1_1);
		lblNewLabel_6_1_1.setForeground(new Color(89, 185, 175));
		lblNewLabel_6_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		txtIDPhieu = new jT();
		txtIDPhieu.setEditable(false);
		txtIDPhieu.setText((String) null);
		txtIDPhieu.setForeground(new Color(110, 110, 110));
		txtIDPhieu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtIDPhieu.setColumns(10);
		txtIDPhieu.setBorder(new EmptyBorder(20, 3, 5, 30));
		txtIDPhieu.setBackground(new Color(250, 250, 250));
		txtIDPhieu.setBounds(129, 38, 230, 45);
		panelThongTin.add(txtIDPhieu);
		
		JPanel panel = new JPanel();
		panel.setBounds(391, 6, 349, 378);
		panelThongTin.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Thông tin Phiếu Mượn");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setForeground(new Color(89, 185, 175));
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1_1.setBounds(51, 6, 230, 38);
		panel.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_6_1_2 = new JLabel("Ngày Mượn :");
		lblNewLabel_6_1_2.setForeground(new Color(89, 185, 175));
		lblNewLabel_6_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_6_1_2.setBounds(6, 90, 98, 37);
		panel.add(lblNewLabel_6_1_2);
		
		JLabel lblNewLabel_6_1_2_1 = new JLabel("Ngày hẹn Trả :");
		lblNewLabel_6_1_2_1.setForeground(new Color(89, 185, 175));
		lblNewLabel_6_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_6_1_2_1.setBounds(6, 139, 123, 37);
		panel.add(lblNewLabel_6_1_2_1);
		
		dateChooserMuon = new JDateChooser();
		dateChooserMuon.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dateChooserMuon.setDateFormatString("dd-MM-yyyy");
		dateChooserMuon.setBounds(116, 90, 211, 27);
		panel.add(dateChooserMuon);
		
	    dateChooserTra = new JDateChooser();
		dateChooserTra.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dateChooserTra.setDateFormatString("dd-MM-yyyy");
		dateChooserTra.setBounds(116, 139, 211, 27);
		panel.add(dateChooserTra);
		
		JLabel lblNewLabel_5 = new JLabel("ID Phiếu :");
		lblNewLabel_5.setForeground(new Color(89, 185, 175));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(6, 46, 98, 25);
		panel.add(lblNewLabel_5);
		
		txtIDphieu = new jT();
		txtIDphieu.setEnabled(false);
		txtIDphieu.setText((String) null);
		txtIDphieu.setForeground(new Color(110, 110, 110));
		txtIDphieu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtIDphieu.setColumns(10);
		txtIDphieu.setBorder(new EmptyBorder(20, 3, 5, 30));
		txtIDphieu.setBackground(new Color(250, 250, 250));
		txtIDphieu.setBounds(116, 40, 230, 43);
		panel.add(txtIDphieu);
		
		JLabel lblNewLabel_6_1_2_1_1_1 = new JLabel("Tình Trạng :");
		lblNewLabel_6_1_2_1_1_1.setForeground(new Color(89, 185, 175));
		lblNewLabel_6_1_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_6_1_2_1_1_1.setBounds(6, 278, 123, 37);
		panel.add(lblNewLabel_6_1_2_1_1_1);
		
		txtTinhtrang = new jT();
		txtTinhtrang.setText((String) null);
		txtTinhtrang.setForeground(new Color(110, 110, 110));
		txtTinhtrang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTinhtrang.setColumns(10);
		txtTinhtrang.setBorder(new EmptyBorder(20, 3, 5, 30));
		txtTinhtrang.setBackground(new Color(250, 250, 250));
		txtTinhtrang.setBounds(116, 273, 230, 43);
		panel.add(txtTinhtrang);
		
		JLabel lblNewLabel_3 = new JLabel("Số lượng :");
		lblNewLabel_3.setBounds(6, 181, 76, 38);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setForeground(new Color(89, 185, 175));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		txtSoLuongSach = new jT();
		txtSoLuongSach.setBounds(116, 188, 230, 43);
		panel.add(txtSoLuongSach);
		txtSoLuongSach.setEditable(true);
		txtSoLuongSach.setForeground(new Color(110, 110, 110));
		txtSoLuongSach.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSoLuongSach.setBackground(new Color(250, 250, 250));
		txtSoLuongSach.setBorder(new EmptyBorder(20, 3, 5, 30));
		txtSoLuongSach.setColumns(10);
		JButton btnUpdate = new JButton("Cập Nhật");
	    btnUpdate.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		phieu.set_ngayHenTra(dateChooserTra.getDate());
	    		phieu.set_soluong(Integer.parseInt(txtSoLuongSach.getText()));
	    		PhieuDAO.getInstance().Update(phieu);
	    		int updated = PhieuDAO.getInstance().Update(phieu);
	    	        	    	        
	    		JOptionPane.showMessageDialog(contentPane, "Cập nhật phiếu mượn thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	    	    dispose();
	    	    parentPn.reloadDataAndRefreshPanel();
	    	}
	    });
	    btnUpdate.setBounds(451, 493, 136, 36);
	    contentPane.add(btnUpdate);
		 JButton btnThoat = new JButton("Thoát");
		    btnThoat.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		dispose();
		    	}
		    });
		    btnThoat.setBounds(599, 489, 137, 40);
		    contentPane.add(btnThoat);
		
		
	}
}
