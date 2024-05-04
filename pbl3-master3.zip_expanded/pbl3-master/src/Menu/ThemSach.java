package Menu;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import DAO.InValidAuthorException;
//port DAO.SachDAO;
import Model.*;
import gdDN.*;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ThemSach extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private jT txtTieude,txtTacgia,txtNamXB;
	private ArrayList<Sach> listSach;
	private SachDAO SachDao;
	private int ids;
	private JComboBox comboBoxNhaXB,comboBoxTheLoai;
	private JSpinner spinnerCount;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ThemSach frame = new ThemSach();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	
	public   ThemSach(home parent, boolean modal) {
		super(parent,modal);
		initComponents();
		setLocationRelativeTo(null);
		SachDao = new SachDAO();
		listSach = new ArrayList<>();
		SachDao.getInstance().selectAll(listSach);
		ids = setBookID(listSach);
	}
	private int setBookID(ArrayList<Sach> listSach)
	{
		int max = listSach.get(0).get_id_sach();
		if(listSach.size() == 0)
			max = 1;
		else
		{
			for(int i = 1;i<listSach.size(); i++)
			{
				if(max < listSach.get(i).get_id_sach())
				{
					max = listSach.get(i).get_id_sach();
				}
			}
		}
		return max;
	}
	private void initComponents() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 842, 614);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(73, 167, 151));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
	
	JLabel lblNewLabel = new JLabel("Thêm sách mới");
	lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
	lblNewLabel.setBounds(351, 46, 210, 39);
	contentPane.add(lblNewLabel);
	
	JPanel panel = new JPanel();
	panel.setBackground(new Color(250, 250, 250));
	panel.setBounds(97, 113, 685, 368);
	contentPane.add(panel);
	panel.setLayout(null);
	
	JLabel lblNewLabel_1 = new JLabel("Thông tinh sách");
	lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
	lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel_1.setBounds(184, 10, 249, 42);
	panel.add(lblNewLabel_1);
	
	JLabel lblNewLabel_2 = new JLabel("Tiêu đề sách :");
	lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
	lblNewLabel_2.setBounds(16, 89, 106, 42);
	panel.add(lblNewLabel_2);
	
	JLabel lblNewLabel_3 = new JLabel("Tác giả :");
	lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
	lblNewLabel_3.setBounds(16, 163, 75, 42);
	panel.add(lblNewLabel_3);
	
	JLabel lblNewLabel_4 = new JLabel("Thể loại :");
	lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
	lblNewLabel_4.setBounds(16, 247, 80, 47);
	panel.add(lblNewLabel_4);
	
	JLabel lblNewLabel_5 = new JLabel("Số lượng :");
	lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
	lblNewLabel_5.setBounds(368, 97, 93, 27);
	panel.add(lblNewLabel_5);
	
	JLabel lblNewLabel_6 = new JLabel("Nhà xuất bản :");
	lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
	lblNewLabel_6.setBounds(368, 178, 111, 27);
	panel.add(lblNewLabel_6);
	
	JLabel lblNewLabel_7 = new JLabel("Năm xuất bản :");
	lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
	lblNewLabel_7.setBounds(368, 247, 111, 42);
	panel.add(lblNewLabel_7);
	
	txtTieude = new jT();
	txtTieude.setFont(new Font("Tahoma", Font.PLAIN, 16));
	txtTieude.setBackground(new Color(250, 250, 250));
	txtTieude.setBorder(new EmptyBorder(20, 3, 5, 30));
	txtTieude.setBounds(120, 79, 208, 45);
	panel.add(txtTieude);
	txtTieude.setColumns(10);
	
	txtTacgia = new jT();
	txtTacgia.setFont(new Font("Tahoma", Font.PLAIN, 16));
	txtTacgia.setBackground(new Color(250, 250, 250));
	txtTacgia.setBorder(new EmptyBorder(20, 3, 5, 30));
	txtTacgia.setBounds(120, 155, 208, 45);
	panel.add(txtTacgia);
	txtTacgia.setColumns(10);
	
	 comboBoxTheLoai = new JComboBox();
	comboBoxTheLoai.setFont(new Font("Tahoma", Font.PLAIN, 16));
	comboBoxTheLoai.setModel(new DefaultComboBoxModel(new String[] {"Tiểu thuyết", "Truyện đồng thoại", "Truyện thơ", "Kịch", "Nghiên cứu", "Khoa học", "Tâm lý, tình cảm", "Văn hóa xã hội", "Nấu ăn", "Khoa học công nghệ"}));
	comboBoxTheLoai.setBackground(new Color(250, 250, 250));
	comboBoxTheLoai.setBounds(120, 262, 208, 23);
	panel.add(comboBoxTheLoai);
	
	txtNamXB = new jT();
	txtNamXB.setFont(new Font("Tahoma", Font.PLAIN, 16));
	txtNamXB.setBackground(new Color(250, 250, 250));
	txtNamXB.setBorder(new EmptyBorder(20, 3, 5, 30));
	txtNamXB.setBounds(480, 236, 186, 45);
	panel.add(txtNamXB);
	txtNamXB.setColumns(10);
	
	comboBoxNhaXB = new JComboBox();
	comboBoxNhaXB.setFont(new Font("Tahoma", Font.PLAIN, 15));
	comboBoxNhaXB.setModel(new DefaultComboBoxModel(new String[] {"Nhà xuất bản Trẻ", "Nhà xuất bản Kim Đồng.", "Nhà xuất bản Tổng hợp thành phố Hồ Chí Minh.", "Nhà xuất bản Hội Nhà văn Việt Nam.", "Nhà xuất bản chính trị quốc gia sự thật.", "Nhà xuất bản Phụ nữ", "Nhà xuất bản Lao Động.", "Nhà xuất bản tư nhân Nhã Nam.", "Nhà xuất bản Văn Học"}));
	comboBoxNhaXB.setBackground(new Color(250, 250, 250));
	comboBoxNhaXB.setBounds(489, 180, 186, 25);
	panel.add(comboBoxNhaXB);
	
	spinnerCount = new JSpinner();
	spinnerCount.setFont(new Font("Tahoma", Font.PLAIN, 16));
	spinnerCount.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
	spinnerCount.setBounds(480, 99, 75, 28);
	panel.add(spinnerCount);
	
	JButton btnThem = new JButton("Thêm");
	btnThem.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String tenSach = txtTieude.getText();
			String tacGia = txtTacgia.getText();
			String theLoai = comboBoxTheLoai.getSelectedItem().toString();
			String nhaXB = comboBoxNhaXB.getSelectedItem().toString();
			String namXb = txtNamXB.getText();
			int soLuong = (int) spinnerCount.getValue();
			if(!tenSach.isEmpty() && !tacGia.isEmpty() && !theLoai.isEmpty() && !nhaXB.isEmpty()
					&& !namXb.isEmpty() && soLuong > 0)
			{
				if(SachDAO.getInstance().checkSachExist(tenSach))
					 JOptionPane.showMessageDialog(contentPane, "Tên sách đã tồn tại trong cơ sở dữ liệu.");
				else
				{
					try {
					Sach sach = new Sach(++ids,10,tacGia,tenSach,theLoai,nhaXB,namXb,soLuong);
					//SachDao.getInstance().Insert(sach);
					SachDao.getInstance().Insert(sach);
					JOptionPane.showMessageDialog(rootPane, "thêm sách thành công");
					txtTieude.setText("");
					txtTacgia.setText("");
					txtNamXB.setText("");
					comboBoxNhaXB.setSelectedIndex(0);
					comboBoxTheLoai.setSelectedIndex(0);
					spinnerCount.setValue(1);
					} 
					catch (InValidAuthorException ex) 
					{
					JOptionPane.showMessageDialog(contentPane, "vui lòng nhập đúng định dạng tên tác giả");
					}
					
				}
			}
			
			
		}
	});
	btnThem.setFont(new Font("Tahoma", Font.PLAIN, 18));
	btnThem.setBackground(new Color(250, 250, 250));
	btnThem.setBounds(518, 499, 105, 39);
	contentPane.add(btnThem);
	
	JButton btnNewButton_1 = new JButton("Hủy");
	btnNewButton_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			int r = JOptionPane.showConfirmDialog(contentPane, "Bạn có chắc muốn thoát không?", "Thông Báo", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if(r == JOptionPane.YES_OPTION)
				dispose(); 
		}
	});
	btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
	btnNewButton_1.setBackground(new Color(250, 250, 250));
	btnNewButton_1.setBounds(677, 499, 105, 39);
	contentPane.add(btnNewButton_1);

	}
}

