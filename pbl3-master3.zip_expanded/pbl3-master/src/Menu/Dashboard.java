package Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

//import DAO.SachDAO;
import Model.*;
import gdDN.DNhap;
import raven.cell.TableActionCellEditor;
import raven.cell.TableActionCellRender;
import raven.cell.TableActionEvent;

public class Dashboard extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;//,panelMenu,pnqlkhosach;
	private JTextField txtNhap;
	private JComboBox comboBox;
	private JTable table;
	private int edit = -1;
	private PhieuDAO PhieuDao;
	private SachDAO SachDao;
	private HoadonDAO HoadonDao;
	private DocgiaDAO DocgiaDao;
	private ArrayList<Thuthu> listTT;
	private ThuthuDAO ttDao;
	
	private DefaultTableModel tableModel;
	private TableActionEvent event;
	private home parentFrame;
	private Dashboard pn = this;
	private DNhap dn;
	
	private JTextField txtSach;
	private JTextField txtSachmuon;
	private JTextField txtSachconlai;
	private JTextField txtdocgia;
	private JTextField txtphieu;
	private JTextField txtthuthu;
	private JTextField txthoadon;
	private JTextField txtPhat;
	/**
	 * Create the panel.
	 */
	public Dashboard(home parentFrame)
	{	this.parentFrame = parentFrame;
		initComponents();
		//setLocationRelativeTo(null);
		listTT = new ArrayList<>();
		ttDao = new ThuthuDAO();
		ttDao.getInstance().selectAll(listTT);
		SachDao = new SachDAO();
		DocgiaDao = new DocgiaDAO();
		HoadonDao = new HoadonDAO();
		ttDao = new ThuthuDAO();
		PhieuDao = new PhieuDAO();
		tableModel = (DefaultTableModel) table.getModel();
		LoadData();
		showListTT(listTT);
	}
	void LoadData() {
		txtSach.setText(String.valueOf(SachDao.getTotalBooks()));
		txtSachmuon.setText(String.valueOf(PhieuDao.getTotalBookMuon()));
		txtSachconlai.setText(String.valueOf(SachDao.getTotalBooks()- PhieuDao.getTotalBookMuon()));
		txtdocgia.setText(String.valueOf(DocgiaDao.getTotalUniqueDG()));
		txthoadon.setText(String.valueOf(HoadonDao.getTotalUniqueHD()));
		txtphieu.setText(String.valueOf(PhieuDao.getTotalUniquePhieu()));
		txtthuthu.setText(String.valueOf(ttDao.getTotalUniqueTT()));
		txtPhat.setText(String.valueOf(HoadonDao.getTotalPhi()));
	}
	void tableevent(ArrayList<Thuthu> listTT) {
		event = new TableActionEvent() {
			
			@Override
			public void onView(int row) {
				if(row >= 0 && row < listTT.size())
				{
					Thuthu selectedTT =  listTT.get(row);
					XemTT xem =  new XemTT(parentFrame, true, selectedTT);
					xem.setVisible(true);
				}
			}
			@Override
			public void onEdit(int row) {
				edit = row;
				if(row >= 0 && row < listTT.size())
				{
					Thuthu selectedTT =  listTT.get(row);
					UpdateTT up =  new UpdateTT(parentFrame, true, selectedTT);
					up.setVisible(true);
				}
			}
			
			@Override
			public void onDelete(int row) {
				Thuthu selectedTT =  listTT.get(row);
				 int result = JOptionPane.showConfirmDialog(contentPane, "Bạn có chắc muốn xóa không?", "Thông Báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		            if (result == JOptionPane.YES_OPTION) {
		                ttDao.getInstance().Delete(selectedTT);
		                listTT.remove(selectedTT);
		                tableModel.setRowCount(0); // Xóa dữ liệu từ mô hình hiện tại

		                

		                tableModel.fireTableDataChanged(); // Cập nhật hiển thị của bảng
		                JOptionPane.showMessageDialog(contentPane, "Xóa thành công");
		                //showListBook(listSach);
		            }
			}
		};
	}
	 private void showListTT(ArrayList<Thuthu> listTT) {
		 tableevent(listTT);
			tableModel.setRowCount(0);
			for (Thuthu e : listTT) {
				Object[] row = new Object[]{ e.get_id(),
		                e.get_hoten(),e.get_ns(),e.get_diachi(),e.get_sdt(), e.get_email()
				};
				tableModel.addRow(row);
				tableModel.fireTableDataChanged();
				table.setRowHeight(45);
				table.setModel(tableModel);
				table.getColumnModel().getColumn(tableModel.getColumnCount() - 1).setCellRenderer(new TableActionCellRender());
			    table.getColumnModel().getColumn(tableModel.getColumnCount() - 1).setCellEditor(new TableActionCellEditor(event));
				table.getColumnModel().getColumn(0).setPreferredWidth(15);
				table.getColumnModel().getColumn(1).setPreferredWidth(80);
			}
		}
	 public void editTT(Thuthu b) {
	        ttDao.getInstance().Update(b);
	        reloadDataAndRefreshPanel();
	    }
	 public void reloadDataAndRefreshPanel() {
		    // Cập nhật dữ liệu
		    listTT.clear();
		    ttDao.getInstance().selectAll(listTT);
		    
		    showListTT(listTT);
		}
	 public void SearchQuery() {
			String t =  txtNhap.getText();
			try {
				if(!t.isEmpty())
				{
					if(comboBox.getSelectedItem().equals("ID"))
					{
						ArrayList<Thuthu> arr = ttDao.getInstance().searchTT(listTT,t, 0);
						if(arr.size() > 0)
							showListTT(arr);
						else
						{
							JOptionPane.showMessageDialog(pn, "Không tìm thấy ID Phiếu phù hợp");
							txtNhap.setText(null);
						}
					}
					if(comboBox.getSelectedItem().equals("CCCD"))
					{
						ArrayList<Thuthu> arr = ttDao.getInstance().searchTT(listTT,t, 1);
						if(arr.size() > 0)
							showListTT(arr);
						else
						{
							JOptionPane.showMessageDialog(pn, "Không tìm thấy ID phù hợp");
							txtNhap.setText(null);
						}
					}
					if(comboBox.getSelectedItem().equals("Họ tên"))
					{
						ArrayList<Thuthu> arr = ttDao.getInstance().searchTT(listTT,t, 2);
						if(arr.size() > 0)
							showListTT(arr);
						else
						{
							JOptionPane.showMessageDialog(pn, "Không tìm thấy Mã Thủ thư phù hợp");
							txtNhap.setText(null);
						}
					}
				}
				else
				JOptionPane.showMessageDialog(pn, "Bạn chưa nhập dữ liệu tìm kiếm","Thông Báo",JOptionPane.OK_OPTION);
				
				
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(contentPane,
							"Dữ liệu tìm kiếm không phù hợp với phương thức tìm kiếm", "Thông Báo",
							JOptionPane.ERROR_MESSAGE);
					txtNhap.setText(null);
			}
		}
	 //ham them thong tin comboBox
	 //ham loc du lieu
	public void initComponents(){
		 new JPanel();
		this.setBackground(new Color(129, 203, 196));
		this.setBounds(275, 0, 825, 725);
		this.setLayout(null);
		
		txtNhap = new JTextField();
		txtNhap.setBackground(new Color(250, 250, 250));
		txtNhap.setBounds(115, 372, 249, 21);
		this.add(txtNhap);
		txtNhap.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Tìm kiếm:");
		lblNewLabel_7.setForeground(new Color(255, 255, 255));
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_7.setBounds(42, 365, 86, 38);
		this.add(lblNewLabel_7);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"ID", "CCCD", "Họ tên"}));
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 10));
		comboBox.setBackground(new Color(255, 255, 255));
		comboBox.setBounds(374, 372, 110, 21);
		this.add(comboBox);
		
		JScrollPane scrollPaneTable = new JScrollPane();
		scrollPaneTable.setBounds(27, 415, 725, 289);
		this.add(scrollPaneTable);
		
		table = new JTable();
		table.setFont(new java.awt.Font("Times New Roman", 0, 14));
		table.setModel(new DefaultTableModel(
	            new Object [][] {},
	            new String [] {"ID","Họ tên","Năm sinh","Địa chỉ","Sđt","Email", "Hành động"}
	        ) 	{
	            boolean[] canEdit = new boolean [] {
	                false, false, false,false,false,false,true
	            };

	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit [columnIndex];
	            }
	        });
		scrollPaneTable.setViewportView(table);
		JButton btnsearch = new JButton("Tìm kiếm");
		btnsearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtNhap.getText().isEmpty() ) {
            		SearchQuery();
            	} else {
            		JOptionPane.showMessageDialog(null, "Vui lòng điền thông tin cần", "Warning", JOptionPane.WARNING_MESSAGE);
            }
			}
			
		});
		btnsearch.setIcon(new ImageIcon("C:\\Users\\hoang\\OneDrive\\Documents\\Pictures\\Ảnh cho pbl3\\Search.png"));
		btnsearch.setBackground(new Color(69, 167, 157));
		btnsearch.setBounds(482, 374, 94, 21);
		this.add(btnsearch);

		JButton btnNewButton_1 = new JButton("Resest");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNhap.setText("");
				ttDao.getInstance().selectAll(listTT);
				 reloadDataAndRefreshPanel();
				
			}
		});
		btnNewButton_1.setBackground(new Color(69, 167, 157));
		btnNewButton_1.setBounds(580, 373, 86, 21);
		this.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Thư viện Sách PBL3");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(27, 6, 295, 59);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Quản lý thủ thư");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(42, 349, 115, 16);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Thống kê");
		lblNewLabel_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(27, 73, 86, 16);
		add(lblNewLabel_1_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(42, 101, 710, 236);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Tổng số sách");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(16, 19, 128, 36);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Sách mượn");
		lblNewLabel_2_1.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblNewLabel_2_1.setBounds(16, 85, 92, 36);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Sách còn lại ");
		lblNewLabel_2_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblNewLabel_2_1_1.setBounds(16, 148, 151, 36);
		panel.add(lblNewLabel_2_1_1);
		
		txtSach = new JTextField();
		txtSach.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		txtSach.setEditable(false);
		txtSach.setBounds(16, 51, 128, 26);
		panel.add(txtSach);
		txtSach.setColumns(10);
		
		txtSachmuon = new JTextField();
		txtSachmuon.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		txtSachmuon.setEditable(false);
		txtSachmuon.setColumns(10);
		txtSachmuon.setBounds(16, 120, 128, 26);
		panel.add(txtSachmuon);
		
		txtSachconlai = new JTextField();
		txtSachconlai.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		txtSachconlai.setEditable(false);
		txtSachconlai.setColumns(10);
		txtSachconlai.setBounds(16, 193, 128, 26);
		panel.add(txtSachconlai);
		
		JLabel lblNewLabel_2_2 = new JLabel("Tổng số độc giả");
		lblNewLabel_2_2.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblNewLabel_2_2.setBounds(171, 19, 139, 36);
		panel.add(lblNewLabel_2_2);
		
		txtdocgia = new JTextField();
		txtdocgia.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		txtdocgia.setEditable(false);
		txtdocgia.setColumns(10);
		txtdocgia.setBounds(171, 51, 111, 26);
		panel.add(txtdocgia);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Tổng phiếu mượn");
		lblNewLabel_2_2_1.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblNewLabel_2_2_1.setBounds(337, 19, 153, 36);
		panel.add(lblNewLabel_2_2_1);
		
		txtphieu = new JTextField();
		txtphieu.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		txtphieu.setEditable(false);
		txtphieu.setColumns(10);
		txtphieu.setBounds(337, 51, 111, 26);
		panel.add(txtphieu);
		
		JLabel lblNewLabel_2_2_1_1 = new JLabel("Thủ thư");
		lblNewLabel_2_2_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblNewLabel_2_2_1_1.setBounds(502, 19, 128, 36);
		panel.add(lblNewLabel_2_2_1_1);
		
		txtthuthu = new JTextField();
		txtthuthu.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		txtthuthu.setEditable(false);
		txtthuthu.setColumns(10);
		txtthuthu.setBounds(502, 51, 128, 26);
		panel.add(txtthuthu);
		
		txthoadon = new JTextField();
		txthoadon.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		txthoadon.setEditable(false);
		txthoadon.setColumns(10);
		txthoadon.setBounds(337, 113, 151, 42);
		panel.add(txthoadon);
		
		JLabel lblNewLabel_2_2_1_2 = new JLabel("Tổng số hoá đơn ");
		lblNewLabel_2_2_1_2.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblNewLabel_2_2_1_2.setBounds(179, 114, 146, 36);
		panel.add(lblNewLabel_2_2_1_2);
		
		JLabel lblNewLabel_2_2_1_2_1 = new JLabel("Tổng Tiền Phạt");
		lblNewLabel_2_2_1_2_1.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblNewLabel_2_2_1_2_1.setBounds(179, 183, 146, 36);
		panel.add(lblNewLabel_2_2_1_2_1);
		
		txtPhat = new JTextField();
		txtPhat.setText("");
		txtPhat.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		txtPhat.setEditable(false);
		txtPhat.setColumns(10);
		txtPhat.setBounds(337, 177, 151, 42);
		panel.add(txtPhat);
		
		JButton btnadd = new JButton("Thêm");
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThemTT them = new ThemTT();
				them.setVisible(true);
			}
		});
		btnadd.setBackground(new Color(69, 167, 157));
		btnadd.setBounds(674, 374, 78, 21);
		add(btnadd);
		
	}
	}

