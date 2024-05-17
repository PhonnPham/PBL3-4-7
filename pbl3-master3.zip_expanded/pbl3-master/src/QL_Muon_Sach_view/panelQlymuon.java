package QL_Muon_Sach_view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

//import DAO.SachDAO;
import Menu.*;
import Model.*;
import raven.cell.TableActionCellEditor;
import raven.cell.TableActionCellRender;
import raven.cell.TableActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class panelQlymuon extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel tableModel;
	private int edit = -1;
	private ArrayList<phieumuon> listPhieu;
	private PhieuDAO PhieuDAO;
	private SachDAO SachDAO; 	private Sach selectedsach;
	private DocgiaDAO DocgiaDAO;private Docgia selectedDocgia;
	private TableActionEvent event;
	public home parentFrame;
	private panelQlymuon qlm = this;
	private JTextField txtNhap;
	private JComboBox comboBox;
	private ghi_nhan_tra_sach ghi_nhan_tra_sach;
	 String tenTT;
/**
	 * Create the panel.
	 */
	void TableEvent() {
		event = new TableActionEvent() {
			
			@Override
			public void onView(int row) {
				if(row >= 0 && row < listPhieu.size())
				{
					phieumuon selectedPhieu =  listPhieu.get(row);
					selectedPhieu.get_id_sach();
					XemTTPhieu xem =  new XemTTPhieu(parentFrame,true,selectedPhieu,selectedsach,selectedDocgia,qlm);
					xem.setVisible(true);
				}
			}
			@Override
			public void onEdit(int row) {
				edit = row;
				if(row >= 0 && row < listPhieu.size())
				{
					phieumuon selectedPhieu =  listPhieu.get(row);
					
					 selectedsach = SachDAO.selectedId(selectedPhieu.get_id_sach());
					 selectedDocgia = DocgiaDAO.selectedId(selectedPhieu.get_id_docgia());
					selectedPhieu.get_id_sach();
					CapNhatTTPhieu xem =  new CapNhatTTPhieu(parentFrame,true,selectedPhieu,selectedsach,selectedDocgia,qlm);
					xem.setVisible(true);
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
	}
	void tableData() {
		tableModel.setRowCount(0);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for (phieumuon e : listPhieu) {
        	selectedsach = SachDAO.selectedId(e.get_id_sach());
			 selectedDocgia = DocgiaDAO.selectedId(e.get_id_docgia());
            Object[] row = new Object[]{ e.get_id_phieu(),selectedDocgia.get_hoten(),
                e.get_id_sach(),selectedsach.get_tensach(),e.get_soluong(), dateFormat.format(e.get_ngaymuon()), e.getGiveBookBack()
                };
            tableModel.addRow(row);
            tableModel.fireTableDataChanged();
            table.setRowHeight(45);
	        table.setModel(tableModel);
	        table.getColumnModel().getColumn(tableModel.getColumnCount() - 1).setCellRenderer(new TableActionCellRender());
	        table.getColumnModel().getColumn(tableModel.getColumnCount() - 1).setCellEditor(new TableActionCellEditor(event));
            
	        table.getColumnModel().getColumn(0).setPreferredWidth(15);
	        table.getColumnModel().getColumn(1).setPreferredWidth(80);
	        table.getColumnModel().getColumn(0).setPreferredWidth(15);
	        table.getColumnModel().getColumn(0).setPreferredWidth(15);
        }
	}
	 void showListPhieu() {
		 TableEvent();
		 tableData();
    }
	
	 public void editPhieu(phieumuon b) {
	        listPhieu.set(edit, b);
	        PhieuDAO.getInstance().Update(b);
	        tableModel.removeRow(edit);
	        
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	        for (phieumuon e : listPhieu) {
	        	selectedsach = SachDAO.selectedId(e.get_id_sach());
				 selectedDocgia = DocgiaDAO.selectedId(e.get_id_docgia());
	            Object[] row = new Object[]{ e.get_id_phieu(),selectedDocgia.get_hoten(),
	                e.get_id_sach(),selectedsach.get_tensach(),e.get_soluong(), dateFormat.format(e.get_ngaymuon()), e.getGiveBookBack()
	                };
	        tableModel.insertRow(edit, row);
	        tableModel.fireTableDataChanged();
	        }
	}
	 public panelQlymuon(home parentFrame) {
		 	this.parentFrame = parentFrame;
		    initComponents();
		    PhieuDAO = new PhieuDAO();
		    listPhieu = new ArrayList<>();
		    SachDAO = new SachDAO();
			DocgiaDAO = new DocgiaDAO();
		    tableModel = (DefaultTableModel) table.getModel();
			PhieuDAO.getInstance().selectAll(listPhieu);
			
		    showListPhieu();
		}

		private void initComponents() {
			this.setBounds(275, 0, 825, 725);
			    setLayout(null);
			    setBackground(new Color(129, 203, 196));
			    JLabel lblNewLabel = new JLabel("Quản lý mượn sách");
			    lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
			    lblNewLabel.setBounds(37, 29, 177, 38);
			    add(lblNewLabel);

			    // Khởi tạo tableModel
			    tableModel = new DefaultTableModel(
			        new Object[][] {},
			        new String[] {"ID Phiếu", "Tên độc giả","ID Sách","Tên Sách","Số lượng", "Ngày Mượn", "Tình Trạng", "Hành Động"}
			    ) {
			        boolean[] canEdit = {false, false, false,false,false,false,false, true};

			        @Override
			        public boolean isCellEditable(int rowIndex, int columnIndex) {
			            return canEdit[columnIndex];
			        }
			    };
		    JButton btnGhiPhieuMuon = new JButton("Ghi phiếu mượn");
		    btnGhiPhieuMuon.setBackground(new Color(250, 250, 250));
		    btnGhiPhieuMuon.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	
		            ghi_nhan_muon_sach ghi = new ghi_nhan_muon_sach(qlm);
		            ghi.setVisible(true);
		            //ghi.setLocationRelativeTo(null);
		        }
		    });
		    btnGhiPhieuMuon.setFont(new Font("Tahoma", Font.PLAIN, 20));
		    btnGhiPhieuMuon.setBounds(448, 618, 177, 38);
		    add(btnGhiPhieuMuon);
		    
		    JScrollPane scrollPane = new JScrollPane();
		    scrollPane.setBounds(37, 165, 761, 425);
		    add(scrollPane);
		     
		    table = new JTable(tableModel);
		    table.setFont(new java.awt.Font("Times New Roman", 0, 14));
		    scrollPane.setViewportView(table);
		    
		    JLabel lblNewLabel_1 = new JLabel("Tìm kiếm : ");
		    lblNewLabel_1.setBounds(37, 119, 83, 16);
		    add(lblNewLabel_1);
		    
		    txtNhap = new JTextField();
			txtNhap.setBounds(112, 117, 206, 19);
			this.add(txtNhap);
			txtNhap.setColumns(10);
			
			comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"ID Phiếu", "ID Độc Giả"}));
			comboBox.setBounds(330, 118, 85, 21);
			this.add(comboBox);
			
			JButton btnTim = new JButton("Tim");
			btnTim.setBackground(new Color(128, 128, 128));
			btnTim.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String t =  txtNhap.getText();
					try {
						if(!t.isEmpty())
						{
							if(comboBox.getSelectedItem().equals("ID Phiếu"))
							{
								phieumuon  rb =  PhieuDAO.getInstance().seachRegisterId(listPhieu, t);
								if(rb != null)
								showSeachPhieuID(rb);
								else
								{
									JOptionPane.showMessageDialog(qlm, "Mã tìm thấy ID phiếu phù hợp");
									txtNhap.setText(null);
								}
							}
							if(comboBox.getSelectedItem().equals("ID Độc Giả"))
							{
								ArrayList<phieumuon> listSearchRB =  PhieuDAO.getInstance().searchDGID(listPhieu, t);
								if(listSearchRB.size() > 0 )
									showSearchDG(listSearchRB);
								else
								{
									JOptionPane.showMessageDialog(qlm, "Không tìm thấy mã độc giả phù hợp");
									txtNhap.setText(null);
								}
							}
						}
						else
						JOptionPane.showMessageDialog(qlm, "Bạn chưa nhập dữ liệu tìm kiếm","Thông Báo",JOptionPane.OK_OPTION);
						
						
					} catch (Exception e2) {
						
					}
				}
			});
			btnTim.setBounds(427, 116, 78, 25);
			this.add(btnTim);
			//button tra
			JButton btnGhiNhanTr = new JButton("Ghi Nhận Trả");
			btnGhiNhanTr.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int selectedRowIndex = table.getSelectedRow();

				    if (selectedRowIndex != -1) {
				    	if("Chưa Trả".equals(String.valueOf(table.getValueAt(selectedRowIndex, 6)))) {
				    		ghi_nhan_tra_sach = new ghi_nhan_tra_sach(parentFrame, qlm,(int)table.getValueAt(selectedRowIndex, 0));
				    	  ghi_nhan_tra_sach.setVisible(true);}
				    	else {
				    	
				    		JOptionPane.showMessageDialog(qlm,"Phiếu mượn đã được trả","Thông Báo",JOptionPane.OK_OPTION);
				    	}
				    } else {ghi_nhan_tra_sach = new ghi_nhan_tra_sach(qlm);
				    	ghi_nhan_tra_sach.setVisible(true);
				    }
				  
					//parentFrame.setVisible(false);
				}
			});
			btnGhiNhanTr.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnGhiNhanTr.setBackground(new Color(250, 250, 250));
			btnGhiNhanTr.setBounds(637, 618, 177, 38);
			add(btnGhiNhanTr);
		}
		private void showSeachPhieuID(phieumuon e) {
		    TableEvent();
		    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		    tableModel.setRowCount(0);
		    Object[] row = new Object[]{ e.get_id_phieu(),selectedDocgia.get_hoten(),
	                e.get_id_sach(),selectedsach.get_tensach(),e.get_soluong(), dateFormat.format(e.get_ngaymuon()), e.getGiveBookBack()
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
		private void showSearchDG(ArrayList<phieumuon> listSearchRb)
		{
			
				TableEvent();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				tableModel.setRowCount(0);
				for (phieumuon e : listSearchRb) {Object[] row = new Object[]{ e.get_id_phieu(),selectedDocgia.get_hoten(),
		                e.get_id_sach(),selectedsach.get_tensach(),e.get_soluong(), dateFormat.format(e.get_ngaymuon()), e.getGiveBookBack()
		                };
					tableModel.addRow(row);
					tableModel.fireTableDataChanged();
					table.setRowHeight(45);
					table.setModel(tableModel);
					table.getColumnModel().getColumn(tableModel.getColumnCount() - 1)
							.setCellRenderer(new TableActionCellRender());
					
					table.getColumnModel().getColumn(tableModel.getColumnCount() - 1)
							.setCellEditor(new TableActionCellEditor(event));

					 table.getColumnModel().getColumn(0).setPreferredWidth(50);
				     table.getColumnModel().getColumn(1).setPreferredWidth(80);
				     table.getColumnModel().getColumn(5).setPreferredWidth(100);

				}

		}
		public void reloadDataAndRefreshPanel() {
		    // Cập nhật dữ liệu
		    listPhieu.clear();
		    PhieuDAO.getInstance().selectAll(listPhieu);
		    
		    // Hiển thị lại panel
		    showListPhieu();
		}
}
