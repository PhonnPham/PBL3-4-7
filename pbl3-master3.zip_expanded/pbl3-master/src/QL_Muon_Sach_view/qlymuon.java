package QL_Muon_Sach_view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Menu.CapNhapTTSach;
import Menu.*;
import Menu.XemTTSach;
import Model.*;
import gdDN.DNhap;
import raven.cell.TableActionCellEditor;
import raven.cell.TableActionCellRender;
import raven.cell.TableActionEvent;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class qlymuon extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane,panelMenu;
	private JTable table;
	private qlymuon qlm = this;
	private PhieuDAO PhieuDAO;
	private ArrayList<phieumuon> listPhieu;
	private DefaultTableModel tableModel;
	private TableActionEvent event;
	private int edit = -1;
	private JTextField txtNhap;
	private JComboBox comboBox;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					qlymuon frame = new qlymuon();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public qlymuon()
	{
		 	initComponents();
		    PhieuDAO = new PhieuDAO();
		    listPhieu = new ArrayList<>();
		    tableModel = (DefaultTableModel) table.getModel();
			PhieuDAO.getInstance().selectAll(listPhieu);
		    showListPhieu();
	}
	private void showListPhieu() {
		event = new TableActionEvent() {
			
			@Override
			public void onView(int row) {
				if(row >= 0 && row < listPhieu.size())
				{
					phieumuon selectedPhieu =  listPhieu.get(row);
					//XemTTSach xem =  new XemTTSach(parentFrame,true,selectedBook,pn);
					//xem.setVisible(true);
				}
			}
			@Override
			public void onEdit(int row) {
				edit = row;
				if(row >= 0 && row < listPhieu.size())
				{
					phieumuon selectedPhieu =  listPhieu.get(row);
					//CapNhapTTSach up =  new CapNhapTTSach(parentFrame,true,selectedBook, pn);
					//up.setVisible(true);
				}
			}		
			@Override
			public void onDelete(int row) {
				phieumuon selectedPhieu =  listPhieu.get(row);
				 int result = JOptionPane.showConfirmDialog(qlm, "Bạn có chắc muốn xóa không?", "Thông Báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		            if (result == JOptionPane.YES_OPTION) {
		            	PhieuDAO.getInstance().Delete(selectedPhieu);
		                listPhieu.remove(selectedPhieu);
		                tableModel.setRowCount(0); // Xóa dữ liệu từ mô hình hiện tại
		                tableModel.fireTableDataChanged(); // Cập nhật hiển thị của bảng
		                JOptionPane.showMessageDialog(qlm, "Xóa thành công");
		                showListPhieu();
		            }
			}
		};
        tableModel.setRowCount(0);
        //int stt = 1;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for (phieumuon e : listPhieu) {
            Object[] row = new Object[]{ e.get_id_phieu(),e.get_id_docgia(),
                e.get_id_sach(), dateFormat.format(e.get_ngaymuon()), e.getGiveBookBack()
                };
            tableModel.addRow(row);
            tableModel.fireTableDataChanged();
            table.setRowHeight(45);
	        table.setModel(tableModel);
	        table.getColumnModel().getColumn(tableModel.getColumnCount() - 1).setCellRenderer(new TableActionCellRender());
	        table.getColumnModel().getColumn(tableModel.getColumnCount() - 1).setCellEditor(new TableActionCellEditor(event));
            
	        table.getColumnModel().getColumn(0).setPreferredWidth(50);
	        table.getColumnModel().getColumn(1).setPreferredWidth(80);
	        table.getColumnModel().getColumn(5).setPreferredWidth(100);
        }

    }
	
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 842, 614);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(69, 167, 157));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelMenu = new JPanel();
		panelMenu.setBackground(new Color(63, 133, 124));
		panelMenu.setBounds(0, 0, 245, 575);
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);
		
		JLabel lbl1 = new JLabel("Quản lý thư viện");
		lbl1.setForeground(new Color(255, 255, 255));
		
		lbl1.setIcon(new ImageIcon("C:\\Users\\hoang\\OneDrive\\Documents\\Pictures\\Ảnh cho pbl3\\manager man.png"));
		lbl1.setHorizontalTextPosition(SwingConstants.CENTER);
		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1.setVerticalTextPosition(SwingConstants.BOTTOM); // Hiển thị văn bản phía dưới icon
		lbl1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl1.setBounds(33, 23, 148, 124);
		panelMenu.add(lbl1);
		
		JLabel lbl4 = new JLabel("Quản lý mượn-trả sách");
		lbl4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl4.setForeground(new Color(255, 255, 255));
		lbl4.setIcon(new ImageIcon("C:\\Users\\hoang\\OneDrive\\Documents\\Pictures\\Ảnh cho pbl3\\icons8-list-48.png"));
		lbl4.setBounds(10, 348, 227, 41);
		panelMenu.add(lbl4);
		
		JLabel lbl2 = new JLabel("Trang chủ");
		lbl2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl2.setIcon(new ImageIcon("C:\\Users\\hoang\\OneDrive\\Documents\\Pictures\\Ảnh cho pbl3\\home.png"));
		lbl2.setForeground(new Color(255, 255, 255));
		lbl2.setBackground(new Color(255, 255, 255));
		lbl2.setBounds(10, 185, 227, 50);
		panelMenu.add(lbl2);
		
		JLabel lbl3 = new JLabel("Quản lý kho sách");
		lbl3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		lbl3.setForeground(new Color(255, 255, 255));
		lbl3.setIcon(new ImageIcon("C:\\Users\\hoang\\OneDrive\\Documents\\Pictures\\Ảnh cho pbl3\\book stack.png"));
		lbl3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl3.setBounds(10, 263, 207, 55);
		panelMenu.add(lbl3);
		
		JLabel lbl5 = new JLabel("Quản lý người mượn");
		lbl5.setIcon(new ImageIcon("C:\\Users\\hoang\\OneDrive\\Documents\\Pictures\\Ảnh cho pbl3\\people manage.png"));
		lbl5.setForeground(new Color(255, 255, 255));
		lbl5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl5.setBounds(10, 420, 227, 55);
		panelMenu.add(lbl5);
		
		JLabel lbl6 = new JLabel("Đăng  xuất");
		lbl6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				
			}
		});
		lbl6.setIcon(new ImageIcon("C:\\Users\\hoang\\OneDrive\\Documents\\Pictures\\Ảnh cho pbl3\\log out.png"));
		lbl6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl6.setForeground(new Color(255, 255, 255));
		lbl6.setBounds(10, 507, 207, 55);
		panelMenu.add(lbl6);
		
		JLabel lblNewLabel = new JLabel("Quản lý mượn sách");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(270, 33, 177, 38);
		contentPane.add(lblNewLabel);
		
		JButton btnGhiPhieuMuon = new JButton("Ghi phiếu mượn");
		btnGhiPhieuMuon.setBackground(new Color(192, 192, 192));
		btnGhiPhieuMuon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				qlm.setVisible(false);
//				ghi_nhan_muon_sach ghi = new ghi_nhan_muon_sach(qlm);
//				ghi.setVisible(true);
//				ghi.setLocationRelativeTo(null);
//				
			}
		});
		btnGhiPhieuMuon.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnGhiPhieuMuon.setBounds(616, 33, 177, 38);
		contentPane.add(btnGhiPhieuMuon);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(289, 189, 499, 308);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new java.awt.Font("Times New Roman", 0, 14));
		table.setModel(new DefaultTableModel(new Object[][] {

		}, new String[] {"ID Phiếu", "ID Độc Giả", "ID Sách", "Ngày Mượn", "Tình Trạng", "Hành Động"}) {
			boolean[] canEdit = new boolean[] { false, false, false,false,false, true };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		scrollPane.setViewportView(table);
		
		txtNhap = new JTextField();
		txtNhap.setBounds(365, 114, 206, 19);
		contentPane.add(txtNhap);
		txtNhap.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"ID Phiếu", "ID Độc Giả"}));
		comboBox.setBounds(576, 115, 85, 21);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("Tim");
		btnNewButton.setBackground(new Color(128, 128, 128));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String t =  txtNhap.getText();
				try {
					if(!t.isEmpty())
					{
						if(comboBox.getSelectedItem().equals("ID Phiếu"))
						{
							phieumuon  rb =  PhieuDAO.getInstance().seachRegisterId(listPhieu, t);
//							if(rb != null)
//						//	showSeachPhieuID(rb);
//							else
//							{
								JOptionPane.showMessageDialog(contentPane, "Mã tìm thấy ID phiếu phù hợp");
								txtNhap.setText(null);
							//}
						}
						if(comboBox.getSelectedItem().equals("ID Độc Giả"))
						{
						//	ArrayList<RegisterBook> listSearchRB =  PhieuDAO.getInstance().searchDGID(listPhieu, t);
//							if(listSearchRB.size() > 0 )
//								showSearchDG(listSearchRB);
//							else
//							{
								JOptionPane.showMessageDialog(contentPane, "Không tìm thấy mã độc giả phù hợp");
								txtNhap.setText(null);
							}
						}
					//}
					else
					JOptionPane.showMessageDialog(contentPane, "Bạn chưa nhập dữ liệu tìm kiếm","Thông Báo",JOptionPane.OK_OPTION);
					
					
				} catch (Exception e2) {
					
				}
			}
		});
		btnNewButton.setBounds(661, 113, 78, 25);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Làm mới");
		btnNewButton_1.setBackground(new Color(113, 193, 165));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNhap.setText(null);
				listPhieu.clear();
				PhieuDAO.getInstance().selectAll(listPhieu);
				showListPhieu();
			}
		});
		btnNewButton_1.setBounds(661, 513, 82, 38);
		contentPane.add(btnNewButton_1);
		
	}
	private void showSeachPhieuID(phieumuon b) {
	    TableActionEvent event2 = new TableActionEvent() {
	        @Override
	        public void onView(int row) {
	            if(row >= 0 && row < listPhieu.size()) {

	            }
	        }

	        @Override
	        public void onEdit(int row) {
	            edit = row;
	            if(row >= 0 && row < listPhieu.size()) {

	            }
	        }

	        @Override
	        public void onDelete(int row) {
	            int result = JOptionPane.showConfirmDialog(contentPane, "Bạn có chắc muốn xóa không?", "Thông Báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	            if (result == JOptionPane.YES_OPTION) {
	                PhieuDAO.getInstance().Delete(b);
	                listPhieu.remove(b);
	                tableModel.setRowCount(0);
	                tableModel.fireTableDataChanged();
	                JOptionPane.showMessageDialog(contentPane, "Xóa thành công");
	                showListPhieu();
	            }
	        }
	    };
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    tableModel.setRowCount(0);
	    Object[] row = new Object[]{ b.get_id_phieu(), b.get_id_docgia(), b.get_id_sach(),dateFormat.format(b.get_ngaymuon()),b.getGiveBookBack() };
	    tableModel.addRow(row);
	    tableModel.fireTableDataChanged();
	    table.setRowHeight(45);
	    table.setModel(tableModel);
	    table.getColumnModel().getColumn(tableModel.getColumnCount() - 1).setCellRenderer(new TableActionCellRender());
	    table.getColumnModel().getColumn(tableModel.getColumnCount() - 1).setCellEditor(new TableActionCellEditor(event2));
	    table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(80);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);
	}
	private void showSearchDG(ArrayList<phieumuon> listSearchRb)
	{
		
			TableActionEvent event3 = new TableActionEvent() {
				
				@Override
				public void onView(int row) {
					if(row >= 0 && row < listSearchRb.size())
					{

					}
					
				}
				
				@Override
				public void onEdit(int row) {
					edit = row;
					if (row >= 0 && row < listSearchRb.size()) {

					}
					
				}
				
				@Override
				public void onDelete(int row) {
					int result = JOptionPane.showConfirmDialog(contentPane, "Bạn có chắc muốn xóa không?", "Thông Báo",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (result == JOptionPane.YES_OPTION) {
						PhieuDAO.getInstance().Delete(listSearchRb.get(row));
						listSearchRb.remove(row);

		                tableModel.fireTableDataChanged(); 
		                tableModel.setRowCount(0);
		                listPhieu.clear();
		                PhieuDAO.getInstance().selectAll(listPhieu);
		                showListPhieu();
		                
		                
		                tableModel.fireTableDataChanged(); 
		                JOptionPane.showMessageDialog(contentPane, "Xóa thành công");
		                txtNhap.setText(null);
					}
					
				}
			};
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			tableModel.setRowCount(0);
			for (phieumuon e : listSearchRb) {
				Object[] row = new Object[]{ e.get_id_phieu(),e.get_id_docgia(),
		                e.get_id_sach(), dateFormat.format(e.get_ngaymuon()), e.getGiveBookBack()
		                };
				tableModel.addRow(row);
				tableModel.fireTableDataChanged();
				table.setRowHeight(45);
				table.setModel(tableModel);
				table.getColumnModel().getColumn(tableModel.getColumnCount() - 1)
						.setCellRenderer(new TableActionCellRender());
				
				table.getColumnModel().getColumn(tableModel.getColumnCount() - 1)
						.setCellEditor(new TableActionCellEditor(event3));

				 table.getColumnModel().getColumn(0).setPreferredWidth(50);
			     table.getColumnModel().getColumn(1).setPreferredWidth(80);
			     table.getColumnModel().getColumn(5).setPreferredWidth(100);

			}

	}
}
