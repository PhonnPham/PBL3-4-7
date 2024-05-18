package QL_Muon_Sach_view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Menu.*;
import Model.*;
import gdDN.DNhap;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.toedter.calendar.IDateEditor;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import gdDN.*;
import javax.swing.JTextArea;
public class ghi_nhan_tra_sach extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPhatTre;
	private jT txtTieuDe,txtSoLuong,txtTenNM,txtThuThu,txtIDsach,txtIDDocgia, txtTinhrrang;
	private JTextField txtTong;
	private JComboBox comboBoxIDPhieu,comboBox ;
	private panelQlymuon qlm;
	
	private home home;
	private Sach Sach;
	private Docgia Docgia;
	private phieumuon phieu;
	private ArrayList<phieumuon> listphieu;
	private ArrayList<Hoadon> listhoadon;	
	private PhieuDAO PhieuDAO;
	private SachDAO SachDao;
	private DocgiaDAO DGDao;
	private HoadonDAO hoadonDAO;
	private JDateChooser dateChooserNM,dateChooserNhT,dateChooserNT_1;
	private int idp,idhd;
	private int phat =  0, phattinhtrang = 0, tongphat = 0 ;
	private JComboBox comboBoxTTSach;
	private ttSachDAO ttDAO;
	private ArrayList<ttSach> listTT;
	private ThuthuDAO thuthuDAO; 
	private JTextField txtTinhtrang;
    
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ghi_nhan_tra_sach frame = new ghi_nhan_tra_sach();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * @wbp.parser.constructor
	 */
	public ghi_nhan_tra_sach(panelQlymuon qlm) {
		
		this.qlm = qlm;
		this.home = qlm.parentFrame;
		initComponents();
		Sach = new Sach();
		Docgia = new Docgia();
		listphieu = new ArrayList<>();
		listhoadon = new ArrayList<>();
		 DGDao = new DocgiaDAO();
		 SachDao = new SachDAO();	
		 PhieuDAO = new PhieuDAO();
		 hoadonDAO = new HoadonDAO();
		 PhieuDAO.getInstance().selectAll(listphieu);
		 hoadonDAO.getInstance().selectAll(listhoadon);
		 setccbIDPhieu();
		 setccbTinhtrang();
		 idp = setIDPhieu(listphieu);
		 idhd = setIDHoadon(listhoadon);
		 thuthuDAO = new ThuthuDAO();
	}
public ghi_nhan_tra_sach(home frame,panelQlymuon qlm, int id) {
		super(frame, true);
		this.qlm = qlm;
		this.home = frame;
		//parentFrame = qlm.parentFrame;	
		initComponents();
		Sach = new Sach();
		Docgia = new Docgia();
		 listphieu = new ArrayList<>();
		 listhoadon = new ArrayList<>();
		 DGDao = new DocgiaDAO();
		 SachDao = new SachDAO();	
		 PhieuDAO = new PhieuDAO();
		 hoadonDAO = new HoadonDAO();
		 PhieuDAO.getInstance().selectAll(listphieu);
		 hoadonDAO.getInstance().selectAll(listhoadon);
		 setccbIDPhieu(id);
		 setccbTinhtrang();
		 idp = setIDPhieu(listphieu);
		 idhd = setIDHoadon(listhoadon);
		 thuthuDAO = new ThuthuDAO();
		 
	}
	private int setIDPhieu(ArrayList<phieumuon> listRB)
	{
		int max = listRB.get(0).get_id_phieu();
		if(listRB.size() == 0)
			max = 0;
		else
		{
			for(int i = 1;i<listRB.size(); i++)
			{
				if(max < listRB.get(i).get_id_phieu())
				{
					max = listRB.get(i).get_id_phieu();
				}
			}
		}
		return max;
	}
	private int setIDHoadon(ArrayList<Hoadon> listRB)
	{
		int max = listRB.get(0).get_id_hoadon();
		if(listRB.size() == 0)
			max = 0;
		else
		{
			for(int i = 1;i<listRB.size(); i++)
			{
				if(max < listRB.get(i).get_id_hoadon())
				{
					max = listRB.get(i).get_id_hoadon();
				}
			}
		}
		return max;
	}
	private void setccbIDPhieu() {
		for(phieumuon p :listphieu) {
			if(p.getGiveBookBack()== "Chưa Trả") {
			comboBoxIDPhieu.addItem(p.get_id_phieu());
					//if(p.get_id_phieu() == id) comboBoxIDPhieu.setSelectedItem(id);
			}
		}
		
	}
	private void setccbIDPhieu(int id) {
		for(phieumuon p :listphieu) {	
			if("Chưa Trả".equals(p.getGiveBookBack())){
			comboBoxIDPhieu.addItem(p.get_id_phieu());
			if(p.get_id_phieu() == id) comboBoxIDPhieu.setSelectedItem(id);
			}
		}
	}
	private void setccbTinhtrang() {
		 ArrayList<String> mucDoList = new ArrayList<>();
		    mucDoList.add("0%");
		    ttDAO = new ttSachDAO();
		    listTT = new ArrayList<ttSach>();
		     ttDAO.getInstance().selectAll(listTT);
		     for(ttSach e: listTT) {
		    	 mucDoList.add(e.getMucdo());
		     }
		    
		     comboBoxTTSach.setModel(new DefaultComboBoxModel<>(mucDoList.toArray(new String[0])));
	}

	private void LoadData() {
		String selectIDphieu = String.valueOf(comboBoxIDPhieu.getSelectedItem());
		for(phieumuon p :listphieu) {
			if(p.get_id_phieu() == Integer.valueOf(selectIDphieu)) {
				this.phieu = p;
				break;
			}
		}
		Sach = SachDAO.getInstance().selectedId(phieu.get_id_sach());
		Docgia = DocgiaDAO.getInstance().selectedId(phieu.get_id_docgia());
		txtTieuDe.setText(Sach.get_tensach());
		txtSoLuong.setText(String.valueOf(phieu.get_soluong()));
		txtTenNM.setText(Docgia.get_hoten());
		txtIDsach.setText(String.valueOf(phieu.get_id_sach()));
		txtIDDocgia.setText(String.valueOf(Docgia.get_id()));
		dateChooserNM.setDate(phieu.get_ngaymuon());
		dateChooserNhT.setDate(phieu.get_ngaytra());
		
		//txtThuThu.setText(thuthuDAO.getInstance().selectByName("thanh").get_hoten());
		//if()
		txtThuThu.setText(Bientoancuc.tt.get_hoten());
		
	}

	private void initComponents() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1027, 660);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(84, 186, 163));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
	
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
	
	JLabel lblNewLabel_4 = new JLabel("ID Độc giả");
	lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
	lblNewLabel_4.setBounds(10, 74, 107, 32);
	panelTTTra.add(lblNewLabel_4);
	
	JLabel lblNewLabel_5 = new JLabel("Tình trạng sách (%)");
	lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
	lblNewLabel_5.setBounds(10, 183, 142, 32);
	panelTTTra.add(lblNewLabel_5);
	
//	 comboBox = new JComboBox();
//	comboBox.setBounds(160, 189, 135, 26);
//	panelTTTra.add(comboBox);
	
	JLabel lblNewLabel_6 = new JLabel("Phạt trình trạng(nếu có)");
	lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
	lblNewLabel_6.setBounds(10, 227, 165, 26);
	panelTTTra.add(lblNewLabel_6);
	
	txtPhatTre = new JTextField();
	txtPhatTre.setBounds(111, 390, 206, 39);
	panelTTTra.add(txtPhatTre);
	txtPhatTre.setColumns(10);
	
	txtTinhtrang = new JTextField();
	txtTinhtrang.setBounds(187, 222, 120, 39);
	panelTTTra.add(txtTinhtrang);
	txtTinhtrang.setColumns(10);
	
	JLabel lblNewLabel_3_1 = new JLabel("Id Phiếu");
	lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
	lblNewLabel_3_1.setBounds(16, 37, 60, 32);
	panelTTTra.add(lblNewLabel_3_1);
	
	JLabel lblNewLabel_6_1 = new JLabel("Phạt trễ hạn:");
	lblNewLabel_6_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
	lblNewLabel_6_1.setBounds(6, 395, 93, 26);
	panelTTTra.add(lblNewLabel_6_1);
	
	txtTong = new JTextField();
	txtTong.setColumns(10);
	txtTong.setBounds(111, 441, 184, 39);
	panelTTTra.add(txtTong);
	
	JLabel lblNewLabel_6_1_1 = new JLabel("Tổng Phạt");
	lblNewLabel_6_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
	lblNewLabel_6_1_1.setBounds(10, 454, 93, 26);
	panelTTTra.add(lblNewLabel_6_1_1);
	
	txtIDDocgia = new jT();
	txtIDDocgia.setFont(new Font("Tahoma", Font.PLAIN, 15));
	txtIDDocgia.setColumns(10);
	txtIDDocgia.setBorder(new EmptyBorder(20, 3, 5, 10));
	txtIDDocgia.setBackground(new Color(250, 250, 250));
	txtIDDocgia.setBounds(105, 70, 212, 43);
	panelTTTra.add(txtIDDocgia);
	
	JLabel lblNewLabel_10 = new JLabel("Tên độc giả");
	lblNewLabel_10.setBounds(10, 125, 113, 32);
	panelTTTra.add(lblNewLabel_10);
	lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 15));
	
	txtTenNM = new jT();
	txtTenNM.setBounds(105, 125, 212, 43);
	panelTTTra.add(txtTenNM);
	txtTenNM.setFont(new Font("Tahoma", Font.PLAIN, 15));
	txtTenNM.setBackground(new Color(250, 250, 250));
	txtTenNM.setBorder(new EmptyBorder(20, 3, 5, 10));
	txtTenNM.setColumns(10);
	
	JPanel panelTTNguoiMuon = new JPanel();
	panelTTNguoiMuon.setBackground(new Color(250, 250, 250));
	panelTTNguoiMuon.setBounds(357, 0, 368, 486);
	panel.add(panelTTNguoiMuon);
	panelTTNguoiMuon.setLayout(null);
	
	JLabel lblNewLabel_2 = new JLabel("Thông tin sách mượn");
	lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
	lblNewLabel_2.setBounds(115, 0, 156, 32);
	panelTTNguoiMuon.add(lblNewLabel_2);
	
	JLabel lblNewLabel_8 = new JLabel("Tiêu đề sách");
	lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
	lblNewLabel_8.setBounds(20, 100, 86, 42);
	panelTTNguoiMuon.add(lblNewLabel_8);
	
	txtTieuDe = new jT();
	txtTieuDe.setFont(new Font("Tahoma", Font.PLAIN, 15));
	txtTieuDe.setBackground(new Color(250, 250, 250));
	txtTieuDe.setBorder(new EmptyBorder(20, 3, 5, 10));
	txtTieuDe.setBounds(125, 98, 170, 43);
	panelTTNguoiMuon.add(txtTieuDe);
	txtTieuDe.setColumns(10);
	
	JLabel lblNewLabel_9 = new JLabel("Số lượng");
	lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 15));
	lblNewLabel_9.setBounds(20, 154, 73, 37);
	panelTTNguoiMuon.add(lblNewLabel_9);
	
	txtSoLuong = new jT();
	txtSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 15));
	txtSoLuong.setBackground(new Color(250, 250, 250));
	txtSoLuong.setBorder(new EmptyBorder(20, 3, 5, 10));
	txtSoLuong.setBounds(125, 148, 170, 43);
	panelTTNguoiMuon.add(txtSoLuong);
	txtSoLuong.setColumns(10);
	
	JLabel lblNewLabel_11 = new JLabel("Thủ thư xác nhận");
	lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 15));
	lblNewLabel_11.setBounds(20, 220, 121, 32);
	panelTTNguoiMuon.add(lblNewLabel_11);
	
	txtThuThu = new jT();
	txtThuThu.setFont(new Font("Tahoma", Font.PLAIN, 15));
	txtThuThu.setBackground(new Color(250, 250, 250));
	txtThuThu.setBorder(new EmptyBorder(20, 3, 5, 10));
	txtThuThu.setBounds(149, 207, 170, 43);
	panelTTNguoiMuon.add(txtThuThu);
	txtThuThu.setColumns(10);
	
	JLabel lblNewLabel_12 = new JLabel("Ngày mượn");
	lblNewLabel_12.setFont(new Font("Tahoma", Font.PLAIN, 15));
	lblNewLabel_12.setBounds(20, 251, 86, 43);
	panelTTNguoiMuon.add(lblNewLabel_12);
	
	dateChooserNM = new JDateChooser();
	dateChooserNM.setFont(new Font("Tahoma", Font.PLAIN, 15));
	dateChooserNM.setBounds(20, 295, 244, 31);
	panelTTNguoiMuon.add(dateChooserNM);
	
	JLabel lblNewLabel_13 = new JLabel("Ngày hẹn trả");
	lblNewLabel_13.setFont(new Font("Tahoma", Font.PLAIN, 15));
	lblNewLabel_13.setBounds(20, 338, 86, 32);
	panelTTNguoiMuon.add(lblNewLabel_13);
	
	 dateChooserNhT = new JDateChooser();
	dateChooserNhT.setFont(new Font("Tahoma", Font.PLAIN, 15));
	dateChooserNhT.setBounds(20, 382, 244, 31);
	panelTTNguoiMuon.add(dateChooserNhT);
	
	dateChooserNT_1 = new JDateChooser();
	dateChooserNT_1.setBounds(20, 449, 244, 31);
	panelTTNguoiMuon.add(dateChooserNT_1);
	dateChooserNT_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
	dateChooserNT_1.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
		@Override
		public void propertyChange(PropertyChangeEvent e) {
			// TODO Auto-generated method stub
			 if ("date".equals(e.getPropertyName())) {
		            Date date1 = dateChooserNhT.getDate();
		            Date date2 = dateChooserNT_1.getDate();
		            if (date1 != null && date2 != null) {
		                // Tính toán khoảng cách giữa hai ngày và hiển thị nó
		                int diffInDays = (int)Math.abs((date2.getTime() - date1.getTime()) / (1000 * 60 * 60 * 24));
		                phat = diffInDays*2000;
		                // lấy phạt trình trạng
		                
		          
		                //txtNgayTre.setText(String.valueOf(diffInDays +"ngày"));
		                //txtPhatTre.setText(String.valueOf(diffInDays * 2000));
		            }
		        }	
			 	tongphat = phat+ phattinhtrang;
			 	txtPhatTre.setText(String.valueOf(phat));
		        txtTong.setText(String.valueOf(tongphat));
		     
		}
	});
	
	JLabel lblNewLabel_13_1 = new JLabel("Ngày trả");
	lblNewLabel_13_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
	lblNewLabel_13_1.setBounds(20, 413, 86, 32);
	panelTTNguoiMuon.add(lblNewLabel_13_1);
	
	JLabel lblNewLabel_3 = new JLabel("Id sách");
	lblNewLabel_3.setBounds(20, 56, 60, 32);
	panelTTNguoiMuon.add(lblNewLabel_3);
	lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
	
	txtIDsach = new jT();
	txtIDsach.setBounds(125, 44, 170, 43);
	panelTTNguoiMuon.add(txtIDsach);
	txtIDsach.setFont(new Font("Tahoma", Font.PLAIN, 15));
	txtIDsach.setColumns(10);
	txtIDsach.setBorder(new EmptyBorder(20, 3, 5, 10));
	txtIDsach.setBackground(new Color(250, 250, 250));
	
	comboBoxIDPhieu = new JComboBox();
	comboBoxIDPhieu.setBounds(111, 42, 135, 26);
	panelTTTra.add(comboBoxIDPhieu);
	//trang thai sach
	comboBoxTTSach = new JComboBox();
	comboBoxTTSach.addItemListener(new ItemListener() {
		public void itemStateChanged(ItemEvent e) {
			 ttDAO.getInstance().selectAll(listTT);
			 for(ttSach b: listTT ) {
				 if (b.getMucdo().equals(comboBoxTTSach.getSelectedItem())){
					 txtTinhtrang.setText(String.valueOf(b.getPhiphat()));
					 phattinhtrang = b.getPhiphat();
					 break;
				 }
			 }
			
		}
	});
	comboBoxTTSach.setBounds(158, 183, 159, 31);
	panelTTTra.add(comboBoxTTSach);
	
	JTextArea textArea = new JTextArea();
	textArea.setBounds(10, 308, 297, 70);
	panelTTTra.add(textArea);
	
	JLabel lblNewLabel_6_1_1_1 = new JLabel("Mô tả tình trạng");
	lblNewLabel_6_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
	lblNewLabel_6_1_1_1.setBounds(10, 265, 113, 26);
	panelTTTra.add(lblNewLabel_6_1_1_1);
	comboBoxIDPhieu.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	      LoadData();
	    }
	});
	JButton btnNewButton = new JButton("Huỷ");
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	});
	btnNewButton.setBounds(864, 586, 117, 29);
	contentPane.add(btnNewButton);
	
	JButton btnNewButton_1 = new JButton("Xác Nhận");
	btnNewButton_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			phieu.set_ngayHenTra(dateChooserNT_1.getDate());
			phieu.set_phimuon(phat);
			phieu.setGiveBookBack("Đã trả");
			PhieuDAO.Update(phieu);
			// update so luong sach
			Sach sach = SachDAO.getInstance().selectedId(phieu.get_id_sach());
			sach.set_soluong(sach.get_soluong()+ phieu.get_soluong());
			SachDao.UpdateSL(sach);
			
			 if(tongphat > 0) {
		    	  //Hoadon(int _id_hoadon,int _id_tt,int _id_phieu, Date _ngayttoan, int _tongtien)
		    	  Hoadon h = new Hoadon(++idhd,Bientoancuc.tt.get_id(),phieu.get_id_phieu(),dateChooserNT_1.getDate(),tongphat);
		    	  hoadonDAO.Insert(h);
		      }
			dispose();
			qlm.reloadDataAndRefreshPanel();
		}
	});
	btnNewButton_1.setBounds(724, 586, 117, 29);
	contentPane.add(btnNewButton_1);
	}
	}
