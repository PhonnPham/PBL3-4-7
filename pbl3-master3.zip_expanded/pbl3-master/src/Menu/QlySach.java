package Menu;

import java.awt.EventQueue;
import raven.cell.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.SachDAO;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import book_model.*;
import javax.swing.DefaultComboBoxModel;
public class QlySach extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panelMenu, pnqlkhosach;
	private JTextField txtNhap;
	private JFrame qlsach = this;
	private JComboBox comboBox;
	private JTable table;
	private int edit = -1;
	private ArrayList<Sach> listSach;
	private SachDAO SachDao;
	private DefaultTableModel tableModel;
	private TableActionEvent event;
	private JScrollPane scrollPaneTable;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QlySach frame = new QlySach();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public QlySach() {
		initComponents();
		setLocationRelativeTo(null);
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
				if (row >= 0 && row < listSach.size()) {
					Sach selectedBook = listSach.get(row);
					//XemTTSach xem = new XemTTSach(qlsach, true, selectedBook);
//
					qlsach.setVisible(false);

					//xem.setVisible(true);
				}

			}

			@Override
			public void onEdit(int row) {
				edit = row;
				if (row >= 0 && row < listSach.size()) {
					Sach selectedBook = listSach.get(row);
					//CapNhapTTSach up = new CapNhapTTSach(qlsach, true, selectedBook);

					qlsach.setVisible(false);

					//up.setVisible(true);
				}
			}

			// còn thiếu phần kiểm tra sách đó có đang được mượn hay kh
			@Override
			public void onDelete(int row) {
				int result = JOptionPane.showConfirmDialog(contentPane, "Bạn có chắc muốn xóa không?", "Thông Báo",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (result == JOptionPane.YES_OPTION) {
					SachDao.getInstance().Delete(listSach.get(row));
					//listSach.remove(row);
					//tableModel.removeRow(row);
					//tableModel.setRowCount(0); // Xóa dữ liệu từ mô hình hiện tại
	               //tableModel.fireTableDataChanged(); // Cập nhật hiển thị của bảng
	                JOptionPane.showMessageDialog(contentPane, "Xóa thành công");
	                listSach.clear();
	                SachDao.getInstance().selectAll(listSach);
	                showListBook();
	               
					

				}
			}
		};
		tableModel.setRowCount(0);
		// int stt = 1;
		for (Sach e : listSach) {
			Object[] row = new Object[] { e.get_id_sach(), e.get_tensach(), e.get_namxb() };
			tableModel.addRow(row);
			
		}
		tableModel.fireTableDataChanged();
		table.setRowHeight(45);
		table.setModel(tableModel);
		table.getColumnModel().getColumn(tableModel.getColumnCount() - 1)
				.setCellRenderer(new TableActionCellRender());

		table.getColumnModel().getColumn(tableModel.getColumnCount() - 1)
				.setCellEditor(new TableActionCellEditor(event));

		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		// table.getColumnModel().getColumn(0).setPreferredWidth(15);
		// table.getColumnModel().getColumn(0).setPreferredWidth(15);

	} 

	void editBook(Sach b) {
		listSach.set(edit, b);
		SachDAO.getInstance().Update(b);
		tableModel.removeRow(edit);
		Object[] row = new Object[] { b.get_id_sach(), b.get_tensach(), b.get_namxb() };
		tableModel.insertRow(edit, row);
		tableModel.fireTableDataChanged();
	}

	private void showSeachBook(Sach b) {
	    TableActionEvent event2 = new TableActionEvent() {
	        @Override
	        public void onView(int row) {
	            if(row >= 0 && row < listSach.size()) {
	                Sach selectedBook = b;
	              //  XemTTSach xem = new XemTTSach(qlsach,true,selectedBook);
	                qlsach.setVisible(false);
	               // xem.setVisible(true);
	            }
	        }

	        @Override
	        public void onEdit(int row) {
	            edit = row;
	            if(row >= 0 && row < listSach.size()) {
	                Sach selectedBook = b;
	               // CapNhapTTSach up = new CapNhapTTSach(qlsach,true,selectedBook);
	                qlsach.setVisible(false);
	               // up.setVisible(true);
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
					//Sach selectedBook = listSeach.get(row);
					//XemTTSach xem = new XemTTSach(qlsach, true, selectedBook);

					qlsach.setVisible(false);

					//xem.setVisible(true);
				}
				
			}
			
			@Override
			public void onEdit(int row) {
				edit = row;
				if (row >= 0 && row < listSeach.size()) {
					Sach selectedBook = listSeach.get(row);
					//CapNhapTTSach up = new CapNhapTTSach(qlsach, true, selectedBook);//

					qlsach.setVisible(false);

					//up.setVisible(true);
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
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 842, 614);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		panelMenu = new JPanel();
		panelMenu.setBackground(new Color(63, 133, 124));
		panelMenu.setBounds(0, 0, 245, 575);
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);

		JLabel lblNewLabel = new JLabel("Quản lý thư viện");
		lblNewLabel.setForeground(new Color(255, 255, 255));

		lblNewLabel.setIcon(
				new ImageIcon("C:\\Users\\hoang\\OneDrive\\Documents\\Pictures\\Ảnh cho pbl3\\manager man.png"));
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setVerticalTextPosition(SwingConstants.BOTTOM); // Hiển thị văn bản phía dưới icon
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(33, 23, 148, 124);
		panelMenu.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Quản lý mượn-trả sách");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setIcon(
				new ImageIcon("C:\\Users\\hoang\\OneDrive\\Documents\\Pictures\\Ảnh cho pbl3\\icons8-list-48.png"));
		lblNewLabel_1.setBounds(10, 348, 227, 41);
		panelMenu.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Trang chủ");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				qlsach.setVisible(false);

				home h = new home();
				h.setVisible(true);
			}
		});
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\hoang\\OneDrive\\Documents\\Pictures\\Ảnh cho pbl3\\home.png"));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBackground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(10, 185, 227, 50);
		panelMenu.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Quản lý kho sách");

		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setIcon(
				new ImageIcon("C:\\Users\\hoang\\OneDrive\\Documents\\Pictures\\Ảnh cho pbl3\\book stack.png"));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(10, 263, 207, 55);
		panelMenu.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Quản lý người mượn");
		lblNewLabel_4.setIcon(
				new ImageIcon("C:\\Users\\hoang\\OneDrive\\Documents\\Pictures\\Ảnh cho pbl3\\people manage.png"));
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(10, 420, 227, 55);
		panelMenu.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Đăng  xuất");
		lblNewLabel_5
				.setIcon(new ImageIcon("C:\\Users\\hoang\\OneDrive\\Documents\\Pictures\\Ảnh cho pbl3\\log out.png"));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setBounds(10, 507, 207, 55);
		panelMenu.add(lblNewLabel_5);

		pnqlkhosach = new JPanel();
		pnqlkhosach.setBackground(new Color(69, 167, 157));
		pnqlkhosach.setBounds(243, 0, 585, 577);
		contentPane.add(pnqlkhosach);
		pnqlkhosach.setLayout(null);
		pnqlkhosach.setVisible(true);

		JLabel lblNewLabel_6 = new JLabel("Quản lý kho sách");
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_6.setBackground(new Color(255, 255, 255));
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_6.setBounds(27, 25, 203, 61);
		pnqlkhosach.add(lblNewLabel_6);

		txtNhap = new JTextField();
		txtNhap.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNhap.setBackground(new Color(250, 250, 250));
		txtNhap.setBounds(100, 113, 249, 21);
		pnqlkhosach.add(txtNhap);
		txtNhap.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Tìm kiếm:");
		lblNewLabel_7.setForeground(new Color(255, 255, 255));
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_7.setBounds(27, 102, 72, 41);
		pnqlkhosach.add(lblNewLabel_7);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "ID", "Tên tác giả", "Tên sách" }));
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setBackground(new Color(255, 255, 255));
		comboBox.setBounds(359, 113, 110, 21);
		pnqlkhosach.add(comboBox);

		scrollPaneTable = new JScrollPane();
		scrollPaneTable.setBounds(27, 184, 543, 291);
		pnqlkhosach.add(scrollPaneTable);

		table = new JTable();
		table.setFont(new java.awt.Font("Times New Roman", 0, 14));
		table.setModel(new DefaultTableModel(new Object[][] {

		}, new String[] { "ID Sách", "Tên Sách", "Năm xuất bản", "Hành động" }) {
			boolean[] canEdit = new boolean[] { false, false, false, true };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		scrollPaneTable.setViewportView(table);

		JButton btnNewButton = new JButton("");
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
		btnNewButton
				.setIcon(new ImageIcon("C:\\Users\\hoang\\OneDrive\\Documents\\Pictures\\Ảnh cho pbl3\\Search.png"));
		btnNewButton.setBackground(new Color(69, 167, 157));
		btnNewButton.setBounds(482, 113, 31, 21);
		pnqlkhosach.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Resest");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setForeground(new Color(250, 250, 250));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showListBook();
			}
		});
		btnNewButton_1.setBackground(new Color(69, 167, 157));
		btnNewButton_1.setBounds(327, 500, 81, 41);
		pnqlkhosach.add(btnNewButton_1);
		
		JButton btnThemSach = new JButton("Thêm sách mới");
		btnThemSach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				qlsach.setVisible(false);
			//	ThemSach them =  new ThemSach();
				//them.setVisible(true);
				//them.setLocationRelativeTo(null);
			}
		});
		btnThemSach.setForeground(new Color(250, 250, 250));
		btnThemSach.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThemSach.setBackground(new Color(69, 167, 157));
		btnThemSach.setBounds(438, 500, 132, 41);
		pnqlkhosach.add(btnThemSach);

	}
}
