package QL_Muon_Sach_view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;

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

public class panelQlymuon extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel tableModel;
	private int edit = -1;
	private ArrayList<phieumuon> listPhieu;
	private PhieuDAO PhieuDAO;
	
	private TableActionEvent event;
	private home parentFrame;
	private panelQlymuon qlm = this;
	/**
	 * Create the panel.
	 */
	
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
                e.get_id_sach(), dateFormat.format(e.get_ngaymuon()), dateFormat.format(e.get_ngaytra())
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
	
	 public void editBook(phieumuon b) {
	        listPhieu.set(edit, b);
	        PhieuDAO.getInstance().Update(b);
	        tableModel.removeRow(edit);
	        
	        for (phieumuon e : listPhieu) {
	            Object[] row = new Object[]{ e.get_id_phieu(),e.get_id_docgia(),
	                e.get_id_sach(), e.get_ngaymuon(),e.get_ngaytra()
	                };
	        tableModel.insertRow(edit, row);
	        tableModel.fireTableDataChanged();
	        }
	}
	 public panelQlymuon() {
		    initComponents();
		    PhieuDAO = new PhieuDAO();
		    listPhieu = new ArrayList<>();
		    tableModel = (DefaultTableModel) table.getModel();
			PhieuDAO.getInstance().selectAll(listPhieu);
		    showListPhieu();
		}

		private void initComponents() {
			 setBounds(238, 0, 580, 575);
			    setLayout(null);
			    setBackground(new Color(129, 203, 196));
			    JLabel lblNewLabel = new JLabel("Quản lý mượn sách");
			    lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
			    lblNewLabel.setBounds(37, 29, 177, 38);
			    add(lblNewLabel);

			    // Khởi tạo tableModel
			    tableModel = new DefaultTableModel(
			        new Object[][] {},
			        new String[] {"ID Phiếu", "ID Độc Giả", "ID Sách", "Ngày Mượn", "Ngày hẹn trả", "Hành Động"}
			    ) {
			        boolean[] canEdit = {false, false, false, false, false, true};

			        @Override
			        public boolean isCellEditable(int rowIndex, int columnIndex) {
			            return canEdit[columnIndex];
			        }
			    };
		    JButton btnGhiPhieuMuon = new JButton("Ghi phiếu mượn");
		    btnGhiPhieuMuon.setBackground(new Color(250, 250, 250));
		    btnGhiPhieuMuon.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		            qlm.setVisible(false);
		            ghi_nhan_muon_sach ghi = new ghi_nhan_muon_sach();
		            ghi.setVisible(true);
		            ghi.setLocationRelativeTo(null);
		        }
		    });
		    btnGhiPhieuMuon.setFont(new Font("Tahoma", Font.PLAIN, 20));
		    btnGhiPhieuMuon.setBounds(354, 115, 177, 38);
		    add(btnGhiPhieuMuon);
		    
		    JScrollPane scrollPane = new JScrollPane();
		    scrollPane.setBounds(37, 165, 499, 308);
		    add(scrollPane);
		     
		    table = new JTable(tableModel);
		    table.setFont(new java.awt.Font("Times New Roman", 0, 14));
		    scrollPane.setViewportView(table);
		}
}
