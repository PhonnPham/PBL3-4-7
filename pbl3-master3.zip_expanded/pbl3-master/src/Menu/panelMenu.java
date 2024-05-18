package Menu;

import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;

import QL_Muon_Sach_view.*;
import gdDN.DNhap;
import doc_gia.*;
import Model.*;
	public class panelMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	public home parentFrame;
	public panelQlySach pnQlySach = new panelQlySach(parentFrame);
	public panelQlymuon qlymuon = new panelQlymuon(parentFrame);
	public panelQlydocgia qlydocgia = new panelQlydocgia(parentFrame);
	public panelHomeDN panelHomeDN = new panelHomeDN(parentFrame);
	public panelQlyhoadon qlyhoadon ;
	public boolean check = false;
	public panelMenu menu = this;
	private JLabel lblngNhp;
	private JLabel lbl6;
	/**
	 * Create the panel.
	 */
	 // Constructor để nhận tham chiếu của home
	private void updatehome(boolean visible) {
        if (parentFrame != null) {
            parentFrame.setVisible(visible);
        }
    }

	public panelMenu(home parentFrame) {
		   this.parentFrame = parentFrame;
		new JPanel();
		setLayout(null);
		setBackground(new Color(63, 133, 124));
		//add(panelMenu);
		setBounds(0, 0, 275, 725);
		JLabel lbl1 = new JLabel("Quản lý thư viện");	
		lbl1.setForeground(new Color(255, 255, 255));
		
		lbl1.setIcon(new ImageIcon("C:\\Users\\hoang\\OneDrive\\Documents\\Pictures\\Ảnh cho pbl3\\manager man.png"));
		lbl1.setHorizontalTextPosition(SwingConstants.CENTER);
		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1.setVerticalTextPosition(SwingConstants.BOTTOM); // Hiển thị văn bản phía dưới icon
		lbl1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl1.setBounds(38, 49, 148, 124);
		this.add(lbl1);
		//Quan lý mượn 
		JLabel lbl4 = new JLabel("Quản lý mượn-trả sách");
		lbl4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			  if(check) {
				qlymuon.setVisible(true);
			  parentFrame.AddPanel(qlymuon);
			}}
		});
		lbl4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl4.setForeground(new Color(255, 255, 255));
		lbl4.setIcon(new ImageIcon("C:\\Users\\hoang\\OneDrive\\Documents\\Pictures\\Ảnh cho pbl3\\icons8-list-48.png"));
		lbl4.setBounds(10, 348, 227, 41);
		this.add(lbl4);
	//Trang chủ(home) 
		JLabel lbl2 = new JLabel("Trang chủ");
		lbl2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(check) {
				panelHomeDN.setVisible(true);
				parentFrame.AddPanel(panelHomeDN);
			}}
		});
		lbl2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl2.setIcon(new ImageIcon("C:\\Users\\hoang\\OneDrive\\Documents\\Pictures\\Ảnh cho pbl3\\home.png"));
		lbl2.setForeground(new Color(255, 255, 255));
		lbl2.setBackground(new Color(255, 255, 255));
		lbl2.setBounds(10, 185, 227, 50);
		this.add(lbl2);
		//Quản lý Sách 
		JLabel lbl3 = new JLabel("Quản lý kho sách");
		//them mouseListener cho label Quan ly sach
		lbl3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//updatehome(false);
				//panelQlySach pnQlySach = new panelQlySach(parentFrame);
				if(check) {
				pnQlySach.setVisible(true);
				parentFrame.AddPanel(pnQlySach);
			}}
		});
		lbl3.setForeground(new Color(255, 255, 255));
		lbl3.setIcon(new ImageIcon("C:\\Users\\hoang\\OneDrive\\Documents\\Pictures\\Ảnh cho pbl3\\book stack.png"));
		lbl3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl3.setBounds(10, 263, 207, 55);
		this.add(lbl3);
		
		JLabel lbl5 = new JLabel("Quản lý độc giả");
		lbl5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			//	ql_nguoi_muon qlynguoimuon = new ql_nguoi_muon();
			//	qlynguoimuon.setVisible(true);
				if(check) {
				qlydocgia.setVisible(true);
				parentFrame.AddPanel(qlydocgia);
			}}
		});
		lbl5.setIcon(new ImageIcon("C:\\Users\\hoang\\OneDrive\\Documents\\Pictures\\Ảnh cho pbl3\\people manage.png"));
		lbl5.setForeground(new Color(255, 255, 255));
		lbl5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl5.setBounds(10, 420, 227, 55);
		this.add(lbl5);
		
		lbl6 = new JLabel("Đăng  xuất");
		lbl6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lbl6.setVisible(false);
				lblngNhp.setVisible(true);	
				Bientoancuc.tt = null;
				//DNhap DN = new DNhap(parentFrame, menu);
				//DN.setVisible(true);	
			}
	});
	lbl6.setIcon(new ImageIcon("C:\\Users\\hoang\\OneDrive\\Documents\\Pictures\\Ảnh cho pbl3\\log out.png"));
	lbl6.setFont(new Font("Tahoma", Font.PLAIN, 16));
	lbl6.setForeground(new Color(255, 255, 255));
	lbl6.setBounds(10, 567, 207, 55);
	lbl6.setVisible(false);
	this.add(lbl6);
	
	JLabel lblQunLHo = new JLabel("Quản lý Hoá đơn");
	lblQunLHo.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(check) {
			qlyhoadon = new panelQlyhoadon(parentFrame, parentFrame.Flag);
			parentFrame.AddPanel(qlyhoadon);
			qlyhoadon.setVisible(true);
			}
		}
	});
	lblQunLHo.setForeground(Color.WHITE);
	lblQunLHo.setFont(new Font("Tahoma", Font.PLAIN, 16));
	lblQunLHo.setBounds(10, 489, 227, 55);
	add(lblQunLHo);
	
	lblngNhp = new JLabel("Đăng  Nhập");
	lblngNhp.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			DNhap DN = new DNhap(parentFrame, menu);
			DN.setVisible(true);
			lbl6.setVisible(true);
			lblngNhp.setVisible(false);
		}
	});
	lblngNhp.setForeground(Color.WHITE);
	lblngNhp.setFont(new Font("Tahoma", Font.PLAIN, 16));
	lblngNhp.setBounds(10, 567, 207, 55);
	add(lblngNhp);
}
}
