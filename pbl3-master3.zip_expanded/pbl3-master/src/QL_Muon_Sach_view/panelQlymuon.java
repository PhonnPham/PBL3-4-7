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
	void TableEvent(ArrayList<phieumuon> listP) {
		event = new TableActionEvent() {
			
			@Override
			public void onView(int row) {
				if(row >= 0 && row < listP.size())
				{
					phieumuon selectedPhieu =  listP.get(row);
					selectedPhieu.get_id_sach();
					XemTTPhieu xem =  new XemTTPhieu(parentFrame,true,selectedPhieu,selectedsach,selectedDocgia,qlm);
					xem.setVisible(true);
				}
			}
			@Override
			public void onEdit(int row) {
				edit = row;
				if(row >= 0 && row < listP.size())
				{
					phieumuon selectedPhieu =  listP.get(row);
					
					 selectedsach = SachDAO.selectedId(selectedPhieu.get_id_sach());
					 selectedDocgia = DocgiaDAO.selectedId(selectedPhieu.get_id_docgia());
					selectedPhieu.get_id_sach();
					CapNhatTTPhieu xem =  new CapNhatTTPhieu(parentFrame,true,selectedPhieu,selectedsach,selectedDocgia,qlm);
					xem.setVisible(true);
				}
			}
			@Override
			public void onDelete(int row) {
				phieumuon selectedPhieu =  listP.get(row);
				 int result = JOptionPane.showConfirmDialog(qlm, "Bạn có chắc muốn xóa không?", "Thông Báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		            if (result == JOptionPane.YES_OPTION) {
		            	if(selectedPhieu.getGiveBookBack().equals("Đã trả"))
		            	{	if(selectedPhieu.get_phimuon() == 0) {
		            	PhieuDAO.getInstance().Delete(selectedPhieu);
		            	reloadDataAndRefreshPanel();
		                JOptionPane.showMessageDialog(qlm, "Xóa thành công");
		                showlistPhieu(listPhieu);
		            	}else {
		            		JOptionPane.showMessageDialog(qlm, "Phiếu mượn có hoá đơn, không thể xoá");
		            	}
		            	} else {
		            		JOptionPane.showMessageDialog(qlm, "Phiếu mượn chưa ghi nhận trả");
		            	}
		            }
			} 
		};
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
			
			showlistPhieu(listPhieu);
		}	
		private void showlistPhieu(ArrayList<phieumuon> listPhieu)
		{
			
				TableEvent(listPhieu);
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				tableModel.setRowCount(0);
				for (phieumuon e : listPhieu) {
					selectedsach = SachDAO.selectedId(e.get_id_sach());
					 selectedDocgia = DocgiaDAO.selectedId(e.get_id_docgia());
					Object[] row = new Object[]{ e.get_id_phieu(),selectedDocgia.get_hoten(),
		                e.get_id_sach(),selectedsach.get_tensach(),e.get_soluong(), dateFormat.format(e.get_ngaymuon()), e.getGiveBookBack()
		                };
					tableModel.addRow(row);
				}
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
		public void reloadDataAndRefreshPanel() {
		    // Cập nhật dữ liệu
		    listPhieu.clear();
		    PhieuDAO.getInstance().selectAll(listPhieu);
		    
		    // Hiển thị lại panel
		    showlistPhieu(listPhieu);
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
			txtNhap.setBounds(246, 112, 206, 30);
			this.add(txtNhap);
			txtNhap.setColumns(10);
			
			comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"ID Phiếu", "ID Độc Giả"}));
			comboBox.setBounds(111, 118, 132, 25);
			this.add(comboBox);
			
			JButton btnTim = new JButton("Tim");
			btnTim.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
			btnTim.setBackground(new Color(128, 128, 128));
			btnTim.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String t =  txtNhap.getText();
					try {
						if(!t.isEmpty())
						{
							if(comboBox.getSelectedItem().equals("ID Phiếu"))
							{
								ArrayList<phieumuon> list =  PhieuDAO.getInstance().searchDGID(listPhieu, t, 0);
								if(list.size() > 0 )
									showlistPhieu(list);
								else
								{
									JOptionPane.showMessageDialog(qlm, "Không tìm ID Phiếu phù hợp");
									txtNhap.setText(null);
								}
							}
							if(comboBox.getSelectedItem().equals("ID Độc Giả"))
							{
								ArrayList<phieumuon> list =  PhieuDAO.getInstance().searchDGID(listPhieu, t , 1);
								if(list.size() > 0 )
									showlistPhieu(list);
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
			btnTim.setBounds(471, 116, 123, 25);
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
				    } else {JOptionPane.showMessageDialog(qlm,"Vui lòng chọn phiếu mượn ","Thông Báo",JOptionPane.OK_OPTION);
				    	
				    }
				  
					//parentFrame.setVisible(false);
				}
			});
			btnGhiNhanTr.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnGhiNhanTr.setBackground(new Color(250, 250, 250));
			btnGhiNhanTr.setBounds(637, 618, 177, 38);
			add(btnGhiNhanTr);
			
			JButton btnReset = new JButton("Reset");
			btnReset.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					reloadDataAndRefreshPanel();
					
				}
			});
			btnReset.setBackground(Color.GRAY);
			btnReset.setBounds(606, 116, 91, 25);
			add(btnReset);
		}

}
