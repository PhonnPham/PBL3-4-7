package doc_gia;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;

import Menu.CapNhapTTSach;
import Menu.XemTTSach;
import Menu.home;
import Model.*;
import raven.cell.TableActionCellEditor;
import raven.cell.TableActionCellRender;
import raven.cell.TableActionEvent;

import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class panelQlydocgia extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	
	private home parentFrame;
	private int edit = -1;
	private ArrayList<Docgia> listDocgia;
	private DocgiaDAO DocgiaDao;
	private DefaultTableModel tableModel;
	private TableActionEvent event;
	private panelQlydocgia qld = this ;
	private JComboBox comboBox;
	
	
	//private qlthemdocgia qlthem;

	public panelQlydocgia(home parentFrame) {
		this.parentFrame = parentFrame;
		initComponents();
		//setLocationRelativeTo(null);
		DocgiaDao = new DocgiaDAO();
		listDocgia = new ArrayList<>();
		tableModel = (DefaultTableModel) table.getModel();
		DocgiaDao.getInstance().selectAll(listDocgia);
		showListDocgia(listDocgia);
	}
	void tableevent(ArrayList<Docgia> listDG ) {
		event = new TableActionEvent() {
			
			@Override
			public void onView(int row) {
				if(row >= 0 && row < listDG.size())
				{
					Docgia selectedDG =  listDG.get(row);
					XemDocgia xem =  new XemDocgia(parentFrame,true,selectedDG);
					xem.setVisible(true);
				}
			}
			@Override
			public void onEdit(int row) {
				edit = row;
				if(row >= 0 && row < listDG.size())
				{
					Docgia selectedDG =  listDG.get(row);
					UpdateDocgia up =  new UpdateDocgia(parentFrame,true,selectedDG);
					up.setVisible(true);
				}
			}
			
			@Override
			public void onDelete(int row) {
				Docgia selectedDG =  listDG.get(row);
				 int result = JOptionPane.showConfirmDialog(contentPane, "Bạn có chắc muốn xóa không?", "Thông Báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		            if (result == JOptionPane.YES_OPTION) {
////		            	
		            	DocgiaDao.getInstance().Delete(selectedDG);
		            	reloadDataAndRefreshPanel();
		               
		                JOptionPane.showMessageDialog(contentPane, "Xóa thành công");
		                //showListDocgia(listDocgia);
		            }
			}
		};
	}
	private void showListDocgia(ArrayList<Docgia> listDG ) {
		tableevent(listDG);
		tableModel.setRowCount(0);
        //int stt = 1;
        for (Docgia e : listDG) {
            Object[] row = new Object[]{ e.get_id(),
                e.get_hoten(),e.get_ns(),e.get_diachi(), e.get_sdt()
                };
            tableModel.addRow(row);
            }
        
            tableModel.fireTableDataChanged();
            table.setRowHeight(45);
	        table.setModel(tableModel);
	        table.getColumnModel().getColumn(tableModel.getColumnCount() - 1).setCellRenderer(new TableActionCellRender());
	        table.getColumnModel().getColumn(tableModel.getColumnCount() - 1).setCellEditor(new TableActionCellEditor(event));
            
	        table.getColumnModel().getColumn(0).setPreferredWidth(15);
	        table.getColumnModel().getColumn(1).setPreferredWidth(80);
	        //table.getColumnModel().getColumn(0).setPreferredWidth(15);
	        //table.getColumnModel().getColumn(0).setPreferredWidth(15);

    }
	private void SearchQuery() {
		String t =  textField.getText();
		try {
			if(!t.isEmpty())
			{
				if(comboBox.getSelectedItem().equals("Họ tên"))
				{
					ArrayList<Docgia> arr = DocgiaDao.getInstance().searchDGID(listDocgia,t, 0);
					if(arr.size() > 0)
						showListDocgia(arr);
					else
					{
						JOptionPane.showMessageDialog(qld, "Không tìm thấy Họ tên phù hợp");
						textField.setText(null);
					}
				}
				if(comboBox.getSelectedItem().equals("ID"))
				{
					ArrayList<Docgia> arr = DocgiaDao.getInstance().searchDGID(listDocgia,t, 1);
					if(arr.size() > 0)
						showListDocgia(arr);
					else
					{
						JOptionPane.showMessageDialog(qld, "Không tìm thấy ID phù hợp");
						textField.setText(null);
					}
				}
				if(comboBox.getSelectedItem().equals("SĐT"))
				{
					ArrayList<Docgia> arr = DocgiaDao.getInstance().searchDGID(listDocgia,t, 2);
					if(arr.size() > 0)
						showListDocgia(arr);
					else
					{
						JOptionPane.showMessageDialog(qld, "Không tìm thấy SĐT phù hợp");
						textField.setText(null);
					}
				}
			}
			else
			JOptionPane.showMessageDialog(qld, "Bạn chưa nhập dữ liệu tìm kiếm","Thông Báo",JOptionPane.OK_OPTION);
			
			
			} catch (Exception e2) {
			
		}
	}
	 public void editDocgia(Docgia b) {
	        DocgiaDAO.getInstance().Update(b);
	        reloadDataAndRefreshPanel();
	    }
	 public void reloadDataAndRefreshPanel() {
		    // Cập nhật dữ liệu
		    listDocgia.clear();
		    DocgiaDao.getInstance().selectAll(listDocgia);
		    
		    showListDocgia(listDocgia);
		}
	public void initComponents() {
		new JPanel();
		//setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		 setBackground(new Color(129, 203, 196));
		this.setLayout(null);
		this.setBounds(275, 0, 825, 725);
	
	JLabel lblNewLabel = new JLabel("Quản lý Độc giả");
	lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
	lblNewLabel.setBounds(48, 6, 197, 41);
	this.add(lblNewLabel);
	
	JLabel lblNewLabel_1 = new JLabel("Tìm kiếm");
	lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
	lblNewLabel_1.setBounds(214, 104, 71, 21);
	this.add(lblNewLabel_1);
	
	textField = new JTextField();
	textField.setBounds(297, 102, 256, 27);
	this.add(textField);
	textField.setColumns(10);
	
	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(22, 157, 786, 305);
	this.add(scrollPane);
	
	table = new JTable();
	table.setModel(new DefaultTableModel(
		new Object[][] {
		},
		new String[] {
			"ID", "Họ tên","Ngày sinh","Địa chỉ","Số điện thoại","Hành động"
		}
		)
			{
		boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
			});
	table.setFont(new Font("Times New Roman", Font.PLAIN, 15));
	scrollPane.setViewportView(table);
	
	JButton btnNew = new JButton("Thêm mới");
	btnNew.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			ThemDocgia them =  new ThemDocgia();
			them.setVisible(true);
			//them.setLocationRelativeTo(null);

		}
	});
	btnNew.setBounds(643, 478, 141, 41);
	this.add(btnNew);
	
	JButton btnSearch = new JButton("Tìm");
	btnSearch.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnSearch) {
	            if (!textField.getText().isEmpty() ) {
	                SearchQuery();
	            } else {
	                JOptionPane.showMessageDialog(null, "Vui lòng điền thông tin cần", "Warning", JOptionPane.WARNING_MESSAGE);
	            }
	        }
//			
		}
	});
	btnSearch.setBounds(575, 99, 103, 35);
	this.add(btnSearch);
	
	comboBox = new JComboBox();
	comboBox.setModel(new DefaultComboBoxModel(new String[] {"Họ tên", "ID", "SĐT"}));
	comboBox.setBounds(61, 103, 141, 27);
	add(comboBox);
	
	JButton btnReset = new JButton("Reset");
	btnReset.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			reloadDataAndRefreshPanel();
		}
	});
	btnReset.setBounds(702, 99, 103, 35);
	add(btnReset);
	}
}	

