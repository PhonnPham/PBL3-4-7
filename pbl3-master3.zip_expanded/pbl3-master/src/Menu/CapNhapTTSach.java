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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.DefaultComboBoxModel;
import Model.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import DAO.*;
public class CapNhapTTSach extends JDialog {
	private panelQlySach parentPn;
	private Sach sach;
	private home home;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private jT txtTenTacGia,txtTheLoai,txtTieudeSach,txtNamxb;
	private JSpinner spinnerCount;
	private JComboBox comboBox;
	PhieuDAO phieuDao;
	private int ids;
	private int idt;	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {	
//					home parent = new home();
//					Sach sach = new Sach();
//					CapNhapTTSach frame = new CapNhapTTSach(parent,true,sach);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	public CapNhapTTSach(home parent, boolean modal, Sach sach, panelQlySach qlysach)
	{	//this.parentPn = pn;
		super(parent, modal);
		   this.sach = sach;
		   
		   this.parentPn = qlysach;
		    initComponents();
		    home = (home)parent;
		    txtTieudeSach.setText(sach.get_tensach());
		    setComBoBox(sach);
		    txtNamxb.setText(sach.get_namxb());
		    txtTheLoai.setText(sach.get_theloai());
		    spinnerCount.setValue(sach.get_soluong());
		    txtTenTacGia.setText(sach.get_tacgia());
		    ids = sach.get_id_sach();
		    idt = sach.get_id_tacgia();
		   
	}
	private void setComBoBox(Sach b) {
        int  size = comboBox.getItemCount();
        for (int i = 0; i < size; i++) {
            if (b.get_nhaxb().equals(comboBox.getItemAt(i))) {
                comboBox.setSelectedIndex(i);
                break;
            }

        }

    }
	private void initComponents() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 978, 657);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(89, 185, 175));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(250, 250, 250));
		panel.setBounds(167, 94, 292, 198);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Xuất bản");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(67, 10, 126, 26);
		panel.add(lblNewLabel_1);
		
		JLabel lblTIeuDe = new JLabel("Tiêu đề sách :");
		lblTIeuDe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTIeuDe.setBounds(10, 46, 95, 34);
		panel.add(lblTIeuDe);
		
		JLabel lblNewLabel_3 = new JLabel("Nhà xuất bản ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(10, 94, 95, 34);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Năm xuất bản :");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(10, 148, 98, 20);
		panel.add(lblNewLabel_4);
		
		txtTieudeSach = new jT();
		txtTieudeSach.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTieudeSach.setBackground(new Color(250, 250, 250));
		txtTieudeSach.setForeground(new Color(0, 0, 0));
		txtTieudeSach.setBorder(new EmptyBorder(20,3,10,30));
		txtTieudeSach.setBounds(105, 33, 177, 50);
		panel.add(txtTieudeSach);
		txtTieudeSach.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Nhà xuất bản Trẻ", "Nhà xuất bản Kim Đồng.", "Nhà xuất bản Tổng hợp thành phố Hồ Chí Minh.", "Nhà xuất bản Hội Nhà văn Việt Nam.", "Nhà xuất bản chính trị quốc gia sự thật.", "Nhà xuất bản Phụ nữ", "Nhà xuất bản Lao Động.", "Nhà xuất bản tư nhân Nhã Nam.", "Nhà xuất bản Văn Học"}));
		comboBox.setBounds(105, 99, 177, 28);
		panel.add(comboBox);
		
		txtNamxb = new jT();
		txtNamxb.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNamxb.setBackground(new Color(250, 250, 250));
		txtNamxb.setForeground(new Color(0, 0, 0));
		txtNamxb.setBorder(new EmptyBorder(20,3,10,30));
		txtNamxb.setBounds(105, 128, 177, 50);
		panel.add(txtNamxb);
		txtNamxb.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(250, 250, 250));
		panel_1.setBounds(552, 94, 292, 198);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Số lượng ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(70, 10, 126, 26);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_5 = new JLabel("Số lượng sách");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(10, 48, 92, 32);
		panel_1.add(lblNewLabel_5);
		
		spinnerCount = new JSpinner();
		spinnerCount.setModel(new SpinnerNumberModel(Integer.valueOf(1), null, null, Integer.valueOf(1)));
		spinnerCount.setBounds(134, 57, 48, 23);
		panel_1.add(spinnerCount);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(250, 250, 250));
		panel_2.setBounds(167, 319, 292, 198);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel("Tác giả");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_6.setBounds(67, 10, 126, 26);
		panel_2.add(lblNewLabel_6);
		
		txtTenTacGia = new jT();
		txtTenTacGia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTenTacGia.setBackground(new Color(250, 250, 250));
		txtTenTacGia.setForeground(new Color(0, 0, 0));
		txtTenTacGia.setBorder(new EmptyBorder(20,3,10,30));
		txtTenTacGia.setBounds(105, 62, 177, 50);
		panel_2.add(txtTenTacGia);
		txtTenTacGia.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Tên tác giả :");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_8.setBounds(10, 82, 95, 20);
		panel_2.add(lblNewLabel_8);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(250, 250, 250));
		panel_3.setBounds(552, 319, 292, 198);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_7 = new JLabel("Thể loại");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(80, 10, 126, 26);
		panel_3.add(lblNewLabel_7);
		
		JLabel lblNewLabel_9 = new JLabel("Thể loại sách :");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_9.setBounds(10, 82, 95, 20);
		panel_3.add(lblNewLabel_9);
		
		txtTheLoai = new jT();
		txtTheLoai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTheLoai.setBackground(new Color(250, 250, 250));
		txtTheLoai.setForeground(new Color(0, 0, 0));
		txtTheLoai.setBorder(new EmptyBorder(20,3,10,30));
		txtTheLoai.setBounds(99, 62, 171, 50);
		panel_3.add(txtTheLoai);
		txtTheLoai.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Cập nhật thông tin");
		lblNewLabel.setForeground(new Color(250, 250, 250));
		lblNewLabel.setBackground(new Color(250, 250, 250));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(291, 30, 304, 40);
		contentPane.add(lblNewLabel);
		
		 JButton btnUpdate = new JButton("Cập nhật");
		 btnUpdate.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		//var bookid = txtbookId.getText();
		        String tensach = txtTieudeSach.getText();
		        String tacgia = txtTenTacGia.getText();
		        String  nhaXB = comboBox.getSelectedItem().toString();
		        
		        String theloai = txtTheLoai.getText();
		        String namXB = txtNamxb.getText();
		        int soluong = (int) spinnerCount.getValue();
		        if ( !tensach.isEmpty() && !tacgia.isEmpty() && !nhaXB.isEmpty()
		                && !theloai.isEmpty() && !namXB.isEmpty() && soluong > 0) {
		            try {
		                sach = new Sach(ids, idt, tacgia, tensach ,theloai , nhaXB,namXB, soluong);
		                parentPn.editBook(sach);
		                
		               JOptionPane.showMessageDialog(rootPane, "sửa thành công");
		               dispose();
		               
		               
		            }  catch (InValidAuthorException ex) {
		               JOptionPane.showMessageDialog(rootPane, "tên không hợp lệ");
		            }
		           
		        }else{
		        JOptionPane.showMessageDialog(rootPane, "thông tin không hợp lệ");
		        }
		 		
		 	}
		 });
		    btnUpdate.setBounds(510, 563, 85, 21);
		    contentPane.add(btnUpdate);
		    
		    JButton btnExit = new JButton("Thoát");
		    btnExit.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		int result = JOptionPane.showConfirmDialog(contentPane, "Bạn có chắc muốn thoát không","Thông Báo",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
		    		if(result ==  JOptionPane.YES_OPTION)
		    		{
		    			dispose();
		    			//QlySach ql = new QlySach();
			           // ql.setVisible(true);
			           // ql.setLocationRelativeTo(null);
		    		}
		    	}
		    });
		    btnExit.setBounds(714, 563, 85, 21);
		    contentPane.add(btnExit);
		
	}
}