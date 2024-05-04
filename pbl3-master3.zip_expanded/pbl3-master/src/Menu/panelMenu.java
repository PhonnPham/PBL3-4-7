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
import Đoc_gia.ql_nguoi_muon;

public class panelMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	public home parentFrame;
	public panelQlySach pnQlySach = new panelQlySach(parentFrame);
	public panelQlymuon qlymuon = new panelQlymuon();
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
		setBounds(0, 0, 245, 575);
		JLabel lbl1 = new JLabel("Quản lý thư viện");	
		lbl1.setForeground(new Color(255, 255, 255));
		
		lbl1.setIcon(new ImageIcon("C:\\Users\\hoang\\OneDrive\\Documents\\Pictures\\Ảnh cho pbl3\\manager man.png"));
		lbl1.setHorizontalTextPosition(SwingConstants.CENTER);
		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1.setVerticalTextPosition(SwingConstants.BOTTOM); // Hiển thị văn bản phía dưới icon
		lbl1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl1.setBounds(33, 23, 148, 124);
		this.add(lbl1);
		//Quan lý mượn 
		JLabel lbl4 = new JLabel("Quản lý mượn-trả sách");
		lbl4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			  
				qlymuon.setVisible(true);
			  parentFrame.AddPanel(qlymuon);
			}
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
			updatehome(true);
			}
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
				pnQlySach.setVisible(true);
				parentFrame.AddPanel(pnQlySach);
			}
		});
		lbl3.setForeground(new Color(255, 255, 255));
		lbl3.setIcon(new ImageIcon("C:\\Users\\hoang\\OneDrive\\Documents\\Pictures\\Ảnh cho pbl3\\book stack.png"));
		lbl3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl3.setBounds(10, 263, 207, 55);
		this.add(lbl3);
		
		JLabel lbl5 = new JLabel("Quản lý người mượn");
		lbl5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				updatehome(false);
				ql_nguoi_muon qlynguoimuon = new ql_nguoi_muon();
				qlynguoimuon.setVisible(true);
			}
		});
		lbl5.setIcon(new ImageIcon("C:\\Users\\hoang\\OneDrive\\Documents\\Pictures\\Ảnh cho pbl3\\people manage.png"));
		lbl5.setForeground(new Color(255, 255, 255));
		lbl5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl5.setBounds(10, 420, 227, 55);
		this.add(lbl5);
		
		JLabel lbl6 = new JLabel("Đăng  xuất");
		lbl6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				updatehome(false);				
				DNhap DN = new DNhap();
				DN.setVisible(true);	
			}
	});
	lbl6.setIcon(new ImageIcon("C:\\Users\\hoang\\OneDrive\\Documents\\Pictures\\Ảnh cho pbl3\\log out.png"));
	lbl6.setFont(new Font("Tahoma", Font.PLAIN, 16));
	lbl6.setForeground(new Color(255, 255, 255));
	lbl6.setBounds(10, 507, 207, 55);
	this.add(lbl6);
}


}
