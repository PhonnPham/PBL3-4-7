package Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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
import raven.cell.TableActionCellEditor;
import raven.cell.TableActionCellRender;
import raven.cell.TableActionEvent;

public class panelQlySach extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;//,panelMenu,pnqlkhosach;
	private JTextField txtNhap;
	private JComboBox comboBox;
	private JTable table;
	private int edit = -1;
	private ArrayList<Sach> listSach;
	private SachDAO SachDao;
	private DefaultTableModel tableModel;
	private TableActionEvent event;
	private home parentFrame;
	private panelQlySach pn = this;
	/**
	 * Create the panel.
	 */
	public panelQlySach(home parentFrame)
	{	this.parentFrame = parentFrame;
		initComponents();
		//setLocationRelativeTo(null);
		SachDao = new SachDAO();
		listSach = new ArrayList<>();
		tableModel = (DefaultTableModel) table.getModel();
		SachDao.getInstance().selectAll(listSach);
		showListBook();
		
	}

	private void showListBook() {
		event = new TableActionEvent() {
			
			@Override
			public void onView(int row) {
				if(row >= 0 && row < listSach.size())
				{
					Sach selectedBook =  listSach.get(row);
					XemTTSach xem =  new XemTTSach(parentFrame,true,selectedBook,pn);
					xem.setVisible(true);
				}
			}
			@Override
			public void onEdit(int row) {
				edit = row;
				if(row >= 0 && row < listSach.size())
				{
					Sach selectedBook =  listSach.get(row);
					CapNhapTTSach up =  new CapNhapTTSach(parentFrame,true,selectedBook, pn);
					up.setVisible(true);
				}
			}
			
			@Override
			public void onDelete(int row) {
				Sach selectedBook =  listSach.get(row);
				 int result = JOptionPane.showConfirmDialog(contentPane, "Bạn có chắc muốn xóa không?", "Thông Báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		            if (result == JOptionPane.YES_OPTION) {
		                SachDao.getInstance().Delete(selectedBook);
		                listSach.remove(selectedBook);
		                tableModel.setRowCount(0); // Xóa dữ liệu từ mô hình hiện tại

		                

		                tableModel.fireTableDataChanged(); // Cập nhật hiển thị của bảng
		                JOptionPane.showMessageDialog(contentPane, "Xóa thành công");
		                showListBook();
		            }
			}
		};
        tableModel.setRowCount(0);
        //int stt = 1;
        for (Sach e : listSach) {
            Object[] row = new Object[]{ e.get_id_sach(),
                e.get_tensach(), e.get_namxb()
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
	
	 public void editBook(Sach b) {
	        listSach.set(edit, b);
	        SachDAO.getInstance().Update(b);
	        tableModel.removeRow(edit);
	        Object[] row = new Object[]{ b.get_id_sach(),
	                b.get_tensach(), b.get_namxb()
	                };
	        tableModel.insertRow(edit, row);
	        tableModel.fireTableDataChanged();
	    }
	 private void showSeachBook(Sach b) {
		    TableActionEvent event2 = new TableActionEvent() {
		        @Override
		        public void onView(int row) {
		            if(row >= 0 && row < listSach.size()) {
		                Sach selectedBook = b;
		                XemTTSach xem =  new XemTTSach(parentFrame,true,selectedBook,pn);
		            
		                xem.setVisible(true);
		            }
		        }

		        @Override
		        public void onEdit(int row) {
		            edit = row;
		            if(row >= 0 && row < listSach.size()) {
		                Sach selectedBook = b;
		            	CapNhapTTSach up =  new CapNhapTTSach(parentFrame,true,selectedBook, pn);
		           
		                up.setVisible(true);
		            }
		        }

		        @Override
		        public void onDelete(int row) {
		            int result = JOptionPane.showConfirmDialog(contentPane, "Bạn có chắc muốn xóa không?", "Thông Báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		            if (result == JOptionPane.YES_OPTION) {
		                SachDao.getInstance().Delete(b);
		                listSach.remove(b);
		                tableModel.setRowCount(0); // Xóa dữ liệu từ mô hình hiện tại

		                

		                tableModel.fireTableDataChanged(); // Cập nhật hiển thị của bảng
		                JOptionPane.showMessageDialog(contentPane, "Xóa thành công");
		                showListBook();
		            }
		        }
		    };

		    tableModel.setRowCount(0);
		    Object[] row = new Object[]{ b.get_id_sach(), b.get_tensach(), b.get_namxb() };
		    tableModel.addRow(row);
		    tableModel.fireTableDataChanged();
		    table.setRowHeight(45);
		    table.setModel(tableModel);
		    table.getColumnModel().getColumn(tableModel.getColumnCount() - 1).setCellRenderer(new TableActionCellRender());
		    table.getColumnModel().getColumn(tableModel.getColumnCount() - 1).setCellEditor(new TableActionCellEditor(event2));
		    table.getColumnModel().getColumn(0).setPreferredWidth(15);
		    table.getColumnModel().getColumn(1).setPreferredWidth(80);
		}

	 private void showListAuthor(ArrayList<Sach> listSeach) {
			TableActionEvent event3 = new TableActionEvent() {
				
				@Override
				public void onView(int row) {
					if(row >= 0 && row < listSeach.size())
					{
						Sach selectedBook = listSeach.get(row);
						XemTTSach xem =  new XemTTSach(parentFrame,true,selectedBook,pn);
						xem.setVisible(true);
					}
					
				}
				
				@Override
				public void onEdit(int row) {
					edit = row;
					if (row >= 0 && row < listSeach.size()) {
						Sach selectedBook = listSeach.get(row);
						CapNhapTTSach up =  new CapNhapTTSach(parentFrame,true,selectedBook, pn);

					
						up.setVisible(true);
					}
					
				}
				
				@Override
				public void onDelete(int row) {
					int result = JOptionPane.showConfirmDialog(contentPane, "Bạn có chắc muốn xóa không?", "Thông Báo",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (result == JOptionPane.YES_OPTION) {
						SachDao.getInstance().Delete(listSeach.get(row));
						listSeach.remove(row);

		                tableModel.fireTableDataChanged(); 
		                tableModel.setRowCount(0);
		                listSach.clear();
		                SachDao.getInstance().selectAll(listSach);
		                showListBook();
		                
		                
		                tableModel.fireTableDataChanged(); 
		                JOptionPane.showMessageDialog(contentPane, "Xóa thành công");
					}
					
				}
			};
			tableModel.setRowCount(0);
			for (Sach e : listSeach) {
				Object[] row = new Object[] { e.get_id_sach(), e.get_tensach(), e.get_namxb()

				};
				tableModel.addRow(row);
				tableModel.fireTableDataChanged();
				table.setRowHeight(45);
				table.setModel(tableModel);
				table.getColumnModel().getColumn(tableModel.getColumnCount() - 1)
						.setCellRenderer(new TableActionCellRender());
				
				table.getColumnModel().getColumn(tableModel.getColumnCount() - 1)
						.setCellEditor(new TableActionCellEditor(event3));

				table.getColumnModel().getColumn(0).setPreferredWidth(15);
				table.getColumnModel().getColumn(1).setPreferredWidth(80);

			}

		}
	public void initComponents(){
		 new JPanel();
		this.setBackground(new Color(129, 203, 196));
		this.setBounds(238, 0, 580, 575);
		this.setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel("Quản lý kho sách");
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_6.setBackground(new Color(255, 255, 255));
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_6.setBounds(27, 35, 249, 61);
		this.add(lblNewLabel_6);
		
		txtNhap = new JTextField();
		txtNhap.setBackground(new Color(250, 250, 250));
		txtNhap.setBounds(100, 113, 249, 21);
		this.add(txtNhap);
		txtNhap.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Tìm kiếm:");
		lblNewLabel_7.setForeground(new Color(255, 255, 255));
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_7.setBounds(27, 106, 86, 38);
		this.add(lblNewLabel_7);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"ID", "Tên tác giả"}));
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 10));
		comboBox.setBackground(new Color(255, 255, 255));
		comboBox.setBounds(359, 113, 110, 21);
		this.add(comboBox);
		
		JLabel lblSearch = new JLabel("");
		lblSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String a = (String) comboBox.getItemAt(comboBox.getSelectedIndex());
				//JOptionPane.showMessageDialog(lblSearch, a);
				if(comboBox.getItemAt(comboBox.getSelectedIndex()).equals("Tên")) {
					JOptionPane.showMessageDialog(lblSearch, a);
				}
			}
		});
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblSearch.setIcon(new ImageIcon("C:\\Users\\hoang\\OneDrive\\Documents\\Pictures\\Ảnh cho pbl3\\Search.png"));
		lblSearch.setBounds(471, 143, 45, 29);
		this.add(lblSearch);
		
		JScrollPane scrollPaneTable = new JScrollPane();
		scrollPaneTable.setBounds(27, 184, 543, 291);
		this.add(scrollPaneTable);
		
		table = new JTable();
		table.setFont(new java.awt.Font("Times New Roman", 0, 14));
		table.setModel(new DefaultTableModel(
	            new Object [][] {},
	            new String [] {"ID Sách",  "Tên Sách", "Năm xuất bản","Hành động"}
	        ) 	{
	            boolean[] canEdit = new boolean [] {
	                false, false, false,true
	            };

	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit [columnIndex];
	            }
	        });
		scrollPaneTable.setViewportView(table);
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String t = txtNhap.getText();
				try {
					if (!t.isEmpty()) {
						if (comboBox.getSelectedItem().equals("ID")) {
							Sach s = SachDao.getInstance().seachBookId(listSach, t);
							if (s != null) {
								 showSeachBook(s);
							} 
							else
							{
						JOptionPane.showMessageDialog(contentPane, "Không tìm thấy mã sách phù hợp","Thông Báo",JOptionPane.ERROR_MESSAGE);
										 
							}
						}
						if (comboBox.getSelectedItem().equals("Tên tác giả")) {
							ArrayList<Sach> listauthor = SachDao.getInstance().searchByAuthor(listSach, t);
							if (listauthor.size() > 0)
								showListAuthor(listauthor);
							else
								JOptionPane.showMessageDialog(contentPane, "Không tìm thấy tên tác giả", "Thông Báo",
										JOptionPane.ERROR_MESSAGE);

						}
						if (comboBox.getSelectedItem().equals("Tên sách")) {
							ArrayList<Sach> listtitle = SachDao.getInstance().searchByTitle(listSach, t);
							if (listtitle.size() > 0)
								showListAuthor(listtitle);
							else
								JOptionPane.showMessageDialog(contentPane, "Không tìm thấy tên sách", "Thông Báo",
										JOptionPane.ERROR_MESSAGE);

						}
						txtNhap.setText(null);
					} else
						JOptionPane.showMessageDialog(contentPane, "Bạn chưa nhập dữ liệu tìm kiếm");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(contentPane,
							"Dữ liệu tìm kiếm không phù hợp với phương thức tìm kiếm", "Thông Báo",
							JOptionPane.ERROR_MESSAGE);
					txtNhap.setText(null);
				}

			}
		});
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\hoang\\OneDrive\\Documents\\Pictures\\Ảnh cho pbl3\\Search.png"));
		btnNewButton.setBackground(new Color(69, 167, 157));
		btnNewButton.setBounds(482, 113, 66, 21);
		this.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Resest");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showListBook();
			}
		});
		btnNewButton_1.setBackground(new Color(69, 167, 157));
		btnNewButton_1.setBounds(327, 500, 81, 41);
		this.add(btnNewButton_1);
		
		JButton btnThemSach = new JButton("Thêm sách mới");
		btnThemSach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//qlsach.setVisible(false);
				ThemSach them =  new ThemSach(parentFrame,true);
				them.setVisible(true);
				//them.setLocationRelativeTo(null);
			}
		});
		btnThemSach.setForeground(Color.BLACK);
		btnThemSach.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThemSach.setBackground(new Color(69, 167, 157));
		btnThemSach.setBounds(438, 500, 132, 41);
		this.add(btnThemSach);
	}
	}

