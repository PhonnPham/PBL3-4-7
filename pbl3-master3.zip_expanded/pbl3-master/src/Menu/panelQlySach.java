package Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
	private JComboBox cBTacgia;
	private JComboBox cBTheloai;
	private JComboBox cBnxb;
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
		showListBook(listSach);
		populateComboBoxes();
		
	}
	void tableevent() {
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
		                showListBook(listSach);
		            }
			}
		};
	}
//	private void showListBook() {
//		tableevent();
//        tableModel.setRowCount(0);
//        //int stt = 1;
//        for (Sach e : listSach) {
//            Object[] row = new Object[]{ e.get_id_sach(),
//                e.get_tensach(),e.get_id_tacgia(),e.get_theloai(),e.get_soluong(), e.get_namxb()
//                };
//            tableModel.addRow(row);
//            tableModel.fireTableDataChanged();
//            table.setRowHeight(45);
//	        table.setModel(tableModel);
//	        table.getColumnModel().getColumn(tableModel.getColumnCount() - 1).setCellRenderer(new TableActionCellRender());
//	        table.getColumnModel().getColumn(tableModel.getColumnCount() - 1).setCellEditor(new TableActionCellEditor(event));
//            
//	        table.getColumnModel().getColumn(0).setPreferredWidth(15);
//	        table.getColumnModel().getColumn(1).setPreferredWidth(80);
//	        table.getColumnModel().getColumn(0).setPreferredWidth(15);
//	        table.getColumnModel().getColumn(0).setPreferredWidth(15);
//        }
//
//    }
	
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
	 private void showSeachBook(Sach e) {
		    tableevent();

		    tableModel.setRowCount(0);
		    Object[] row = new Object[]{ e.get_id_sach(),
	                e.get_tensach(),e.get_tacgia(),e.get_theloai(),e.get_soluong(), e.get_namxb()
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

	 private void showListBook(ArrayList<Sach> listSach) {
			tableevent();
			tableModel.setRowCount(0);
			for (Sach e : listSach) {
				Object[] row = new Object[]{ e.get_id_sach(),
		                e.get_tensach(),e.get_tacgia(),e.get_theloai(),e.get_soluong(), e.get_namxb()
				};
				tableModel.addRow(row);
				tableModel.fireTableDataChanged();
				table.setRowHeight(45);
				table.setModel(tableModel);
				table.getColumnModel().getColumn(tableModel.getColumnCount() - 1)
						.setCellRenderer(new TableActionCellRender());
				
				table.getColumnModel().getColumn(tableModel.getColumnCount() - 1)
						.setCellEditor(new TableActionCellEditor(event));

				table.getColumnModel().getColumn(0).setPreferredWidth(15);
				table.getColumnModel().getColumn(1).setPreferredWidth(80);

			}

		}
	 //ham them thong tin comboBox
	 private void populateComboBoxes() {
			ArrayList<String> authors = listSach.stream().map(Sach::get_tacgia).distinct().collect(Collectors.toCollection(ArrayList::new));
			ArrayList<String> genres = listSach.stream().map(Sach::get_theloai).distinct().collect(Collectors.toCollection(ArrayList::new));
			ArrayList<String> publishers = listSach.stream().map(Sach::get_namxb).distinct().collect(Collectors.toCollection(ArrayList::new));
			authors.add(0, "All");
		    genres.add(0, "All");
		    publishers.add(0, "All");
			cBTacgia.setModel(new DefaultComboBoxModel<>(authors.toArray(new String[0])));
			cBTheloai.setModel(new DefaultComboBoxModel<>(genres.toArray(new String[0])));
			cBnxb.setModel(new DefaultComboBoxModel<>(publishers.toArray(new String[0])));
		}
	 //ham loc du lieu
	 private void filterTable(String tacgia, String theloai, String nxb) {
			ArrayList<Sach> filteredList = (ArrayList<Sach>) listSach.stream()
					.filter(s -> (tacgia.equals("All") || tacgia.isEmpty() || s.get_tacgia().equals(tacgia)))
					.filter(s -> (theloai.equals("All")  || theloai.isEmpty() || s.get_theloai().equals(theloai)))
					.filter(s -> (nxb.equals("All")  || nxb.isEmpty() || s.get_namxb().equals(nxb)))
					.collect(Collectors.toList());

			showListBook(filteredList);
		}
	public void initComponents(){
		 new JPanel();
		this.setBackground(new Color(129, 203, 196));
		this.setBounds(275, 0, 825, 725);
		this.setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel("Quản lý kho sách");
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_6.setBackground(new Color(255, 255, 255));
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 24));
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
		scrollPaneTable.setBounds(27, 184, 725, 291);
		this.add(scrollPaneTable);
		
		table = new JTable();
		table.setFont(new java.awt.Font("Times New Roman", 0, 14));
		table.setModel(new DefaultTableModel(
	            new Object [][] {},
	            new String [] {"ID Sách","Tên Sách","Tác giả","Thể loại","Nhà xuất bản", "Năm xuất bản","Hành động"}
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
								showListBook(listauthor);
							else
								JOptionPane.showMessageDialog(contentPane, "Không tìm thấy tên tác giả", "Thông Báo",
										JOptionPane.ERROR_MESSAGE);

						}
						if (comboBox.getSelectedItem().equals("Tên sách")) {
							ArrayList<Sach> listtitle = SachDao.getInstance().searchByTitle(listSach, t);
							if (listtitle.size() > 0)
								showListBook(listtitle);
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
		btnsearch.setIcon(new ImageIcon("C:\\Users\\hoang\\OneDrive\\Documents\\Pictures\\Ảnh cho pbl3\\Search.png"));
		btnsearch.setBackground(new Color(69, 167, 157));
		btnsearch.setBounds(482, 113, 101, 21);
		this.add(btnsearch);

		JButton btnNewButton_1 = new JButton("Resest");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cBnxb.setSelectedIndex(0);
				cBTacgia.setSelectedIndex(0);
				cBTheloai.setSelectedIndex(0);
				showListBook(listSach);
			}
		});
		btnNewButton_1.setBackground(new Color(69, 167, 157));
		btnNewButton_1.setBounds(451, 514, 81, 41);
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
		btnThemSach.setBounds(606, 514, 132, 41);
		this.add(btnThemSach);
		
		cBTacgia = new JComboBox();
		cBTacgia.setBounds(100, 145, 132, 27);
		add(cBTacgia);
		
		cBnxb = new JComboBox();
		cBnxb.setBounds(421, 145, 132, 27);
		add(cBnxb);
		
		cBTheloai = new JComboBox();
		cBTheloai.setBounds(263, 145, 132, 27);
		add(cBTheloai);
		
		JLabel lblNewLabel_7_1 = new JLabel("Lọc:");
		lblNewLabel_7_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_7_1.setForeground(Color.WHITE);
		lblNewLabel_7_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_7_1.setBounds(37, 143, 86, 38);
		add(lblNewLabel_7_1);
		
		JButton btn = new JButton("Lọc");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tacgia = (String) cBTacgia.getSelectedItem();
				String theloai = (String) cBTheloai.getSelectedItem();
				String nxb = (String) cBnxb.getSelectedItem();
				filterTable(tacgia,theloai,nxb);
			}
		});
		btn.setBackground(new Color(69, 167, 157));
		btn.setBounds(575, 151, 66, 21);
		add(btn);
	}
	}

