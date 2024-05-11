package QL_Muon_Sach_view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.sql.DataSource;
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

import Model.*;
//import DG_controller.DocgiaDAO;
//import DG_model.Docgia;
//import book_model.Sach;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import gdDN.*;
//import registerBook_controller.RegisterBookDAO;
//import registerBook_model.RegisterBook;

import javax.swing.SpinnerNumberModel;
public class ghi_nhan_muon_sach extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private jT txtTieuDe,txtTacGia,txtTrangThaiSach,txtHoTen,txtSDT;
	private JDateChooser dateChooserMuon,dateChooserTra;
	private JComboBox cbbIdSach,cbbIdDG;
	private ArrayList<Sach> listSach;
	private ArrayList<Docgia> listDG;
	private ArrayList<phieumuon> listphieu;
	private PhieuDAO PhieuDAO;
	private SachDAO SachDao;
	private DocgiaDAO DGDao;
	private int idp = -1;
	private panelQlymuon qlm;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			ghi_nhan_muon_sach dialog = new ghi_nhan_muon_sach();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	 public ghi_nhan_muon_sach(panelQlymuon qlm)
	 {	 this.qlm = qlm;
		 initComponents();
		 setLocationRelativeTo(null);
		 listSach = new ArrayList<>();
		 listDG = new ArrayList<>();
		 listphieu = new ArrayList<>();
		 DGDao = new DocgiaDAO();
		 SachDao = new SachDAO();	
		 PhieuDAO = new PhieuDAO();
		 SachDao.getInstance().selectAll(listSach);
		 DGDao.getInstance().selectAll(listDG);
		 PhieuDAO.getInstance().selectAll(listphieu);
		 setcbbIDSach();
		 setcbbIDDG();
		 idp = setIDPhieu(listphieu);
		 txtTrangThaiSach.setText("Chưa trả");
		 
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
	 private void setcbbIDSach()
		{
			for(Sach s : listSach)
			{
				cbbIdSach.addItem(s.get_id_sach());
			}
			//lấy id sách được chọn
			String selectID =  String.valueOf(cbbIdSach.getSelectedItem()); 
			
			Sach selectBook = SachDao.getInstance().seachBookId(listSach, selectID);
			if(selectBook != null)
			{
				txtTieuDe.setText(selectBook.get_tensach());
				txtTacGia.setText(selectBook.get_tacgia());
			}
			//Thêm sự kiện cho cbb
			cbbIdSach.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					//lấy id sách được chọn
					String selectID =  String.valueOf(cbbIdSach.getSelectedItem()); 
					
					Sach selectBook = SachDao.getInstance().seachBookId(listSach, selectID);
					if(selectBook != null)
					{
						txtTieuDe.setText(selectBook.get_tensach());
						txtTacGia.setText(selectBook.get_tacgia());
					}
				}
			});
			
		}
	private void setcbbIDDG()
	{
		for(Docgia d : listDG)
		{
			cbbIdDG.addItem(d.get_id());
		}
		String selectID =  String.valueOf(cbbIdDG.getSelectedItem()); 
		
		Docgia selectDG = DocgiaDAO.getInstance().seachDGId(listDG, selectID);
		if(selectDG != null)
		{
			txtHoTen.setText(selectDG.get_hoten());
			txtSDT.setText(selectDG.get_sdt());
		}
		//Thêm sự kiện cho cbb
		cbbIdDG.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//lấy id sách được chọn
				String selectID =  String.valueOf(cbbIdDG.getSelectedItem()); 
				
				Docgia selectDG = DGDao.getInstance().seachDGId(listDG, selectID);
				if(selectDG != null)
				{
					txtHoTen.setText(selectDG.get_hoten());
					txtSDT.setText(selectDG.get_sdt());
				}
			}
		});
	}
	private void initComponents() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setBounds(238, 0, 700, 620);
		contentPanel = new JPanel();
		contentPanel.setBackground(new Color(69, 171, 148));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPanel);
		contentPanel.setLayout(null);
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
		lblNewLabel.setBounds(237, 38, 224, 35);
		contentPanel.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(197, 197, 197));
		panel.setBounds(30, 130, 635, 412);
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
		txtTieuDe.setEditable(false);
		txtTieuDe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTieuDe.setBorder(new EmptyBorder(10, 3, 5, 10));
		txtTieuDe.setBackground(new Color(250, 250, 250));
		txtTieuDe.setBounds(139, 92, 156, 40);
		panel_1.add(txtTieuDe);
		txtTieuDe.setColumns(10);
		
		JSpinner spinnerCount = new JSpinner();
		spinnerCount.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		spinnerCount.setBounds(176, 176, 38, 27);
		panel_1.add(spinnerCount);
		
		txtTacGia = new jT();
		txtTacGia.setEditable(false);
		txtTacGia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTacGia.setBorder(new EmptyBorder(10, 3, 5, 10));
		txtTacGia.setBackground(new Color(250, 250, 250));
		txtTacGia.setBounds(139, 236, 156, 40);
		panel_1.add(txtTacGia);
		txtTacGia.setColumns(10);
		
		txtTrangThaiSach = new jT();
		txtTrangThaiSach.setEditable(false);
		txtTrangThaiSach.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTrangThaiSach.setBorder(new EmptyBorder(10, 3, 5, 10));
		txtTrangThaiSach.setBackground(new Color(250, 250, 250));
		txtTrangThaiSach.setBounds(139, 322, 156, 50);
		panel_1.add(txtTrangThaiSach);
		txtTrangThaiSach.setColumns(10);
		
		cbbIdSach = new JComboBox();
		cbbIdSach.setBounds(139, 44, 156, 25);
		panel_1.add(cbbIdSach);
		
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
		
		JLabel lblNewLabel_8 = new JLabel("Id độc giả");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_8.setBounds(20, 41, 73, 27);
		panel_2.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Họ tên");
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
		
		txtHoTen = new jT();
		txtHoTen.setEditable(false);
		txtHoTen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtHoTen.setBorder(new EmptyBorder(10, 3, 5, 10));
		txtHoTen.setBackground(new Color(250, 250, 250));
		txtHoTen.setBounds(115, 92, 156, 50);
		panel_2.add(txtHoTen);
		txtHoTen.setColumns(10);
		
		txtSDT = new jT();
		txtSDT.setEditable(false);
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
		
		dateChooserTra = new JDateChooser();
		dateChooserTra.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dateChooserTra.setDateFormatString("dd-MM-yyyy");
		dateChooserTra.setBounds(20, 360, 287, 27);
		panel_2.add(dateChooserTra);
		
		cbbIdDG = new JComboBox();
		cbbIdDG.setBounds(104, 44, 159, 25);
		panel_2.add(cbbIdDG);
		
		
		
		JButton btnThoat = new JButton("Thoát");
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				qlm.reloadDataAndRefreshPanel();
			}
		});
		btnThoat.setBounds(546, 557, 85, 21);
		contentPanel.add(btnThoat);
		
		JButton btnThem = new JButton("Ghi Nhận");
		btnThem.setBounds(404, 557, 85, 21);
		contentPanel.add(btnThem);
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idsach = (int) cbbIdSach.getSelectedItem();
				int iddg = (int) cbbIdDG.getSelectedItem();
				
				Date ngaymuon = dateChooserMuon.getDate();
				Date ngaytra = dateChooserTra.getDate();
				//JOptionPane.showMessageDialog(contentPanel, ngaytra.getTime());
				//Date ngaytra =  dateChooserTra.getDate();
				//if(ngaytra.getTime() < ngayViDu.getTime())
					//JOptionPane.showMessageDialog(contentPanel, "sai");
				int sl = (int) spinnerCount.getValue();
				if(ngaytra != null)
				{
					try {
						
						Sach book = checkBookId(idsach);
						if(sl < book.get_soluong())
						{
							if(sl<=3)
							{
								if(ngaytra.getTime() > ngaymuon.getTime())
								{
									
									if(!isRegisterBookExist(idsach, iddg))
									{
										int scl = book.get_soluong() - sl;
										book.set_soluong(scl);
										SachDao.getInstance().Update(book);
										phieumuon addphieu =  new phieumuon(++idp,  iddg,idsach, sl, ngaymuon, ngaytra, 0);
										PhieuDAO.getInstance().Insert(addphieu);
										JOptionPane.showMessageDialog(contentPanel, "thêm thành công");
										cbbIdSach.setSelectedIndex(0);
										cbbIdDG.setSelectedIndex(0);
										setcbbIDDG();
										setcbbIDSach();
									}else
									{
										JOptionPane.showMessageDialog(contentPanel, "mã độc giả: "+ iddg + " đã mượn sách với "+ "mã sách:" + idsach);
									}
								}
								else
								{
									JOptionPane.showMessageDialog(contentPanel, "ngày hẹn trả phải "+ "lớn hơn ngày đang mượn");
								}
							}
							else
							{
								JOptionPane.showMessageDialog(contentPanel, " chỉ được phép mượn tối đa 3 quyển");
							}
						}
						else
						{
							JOptionPane.showMessageDialog(contentPanel, "số lượng sách trong "
	                                + "thư viên không đủ cho "
	                                + "bạn mượn(số sách còn lại là: "
	                                + book.get_soluong() + ")");
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
				else
				{
					JOptionPane.showMessageDialog(contentPanel, "Không được để trống thông tin ngày trả");
				}
				
				
			}
		});
		dispose();
		qlm.reloadDataAndRefreshPanel();
	}
	private Sach checkBookId(int bookId) {
		
        for (Sach e : listSach) {

            if (e.get_id_sach() == bookId) {

                return e;
            }
        }
        return null;
    }
	private boolean isRegisterBookExist(int bookId, int readerId) {
	    for (phieumuon rb : listphieu) {
	        if (rb.get_id_sach() == bookId && rb.get_id_docgia() == readerId) {
	            return true; // Đã tồn tại trong danh sách
	        }
	    }
	    return false; // Không tồn tại trong danh sách
	}

}
