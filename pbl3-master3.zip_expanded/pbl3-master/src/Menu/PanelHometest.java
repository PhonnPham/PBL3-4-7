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
import gdDN.DNhap;
import raven.cell.TableActionCellEditor;
import raven.cell.TableActionCellRender;
import raven.cell.TableActionEvent;

public class PanelHometest extends JPanel {

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
	private PanelHometest pn = this;
	private JComboBox cBTacgia;
	private JComboBox cBTheloai;
	private JComboBox cBnxb;
	private DNhap dn;
	/**
	 * Create the panel.
	 */
	public PanelHometest(home parentFrame)
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
	 private void showSeachBook(Sach e) {
		  

		    tableModel.setRowCount(0);
		    Object[] row = new Object[]{ e.get_id_sach(),
	                e.get_tensach(),e.get_tacgia(),e.get_theloai(),e.get_soluong(), e.get_namxb()
	                };
		    tableModel.addRow(row);
		    tableModel.fireTableDataChanged();
		    table.setRowHeight(45);
		    table.setModel(tableModel);
		    table.getColumnModel().getColumn(0).setPreferredWidth(15);
		    table.getColumnModel().getColumn(1).setPreferredWidth(80);
		}

	 private void showListBook(ArrayList<Sach> listSach) {
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
		
		txtNhap = new JTextField();
		txtNhap.setBackground(new Color(250, 250, 250));
		txtNhap.setBounds(100, 193, 249, 21);
		this.add(txtNhap);
		txtNhap.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Tìm kiếm:");
		lblNewLabel_7.setForeground(new Color(255, 255, 255));
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_7.setBounds(27, 186, 86, 38);
		this.add(lblNewLabel_7);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"ID", "Tên tác giả"}));
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 10));
		comboBox.setBackground(new Color(255, 255, 255));
		comboBox.setBounds(359, 193, 110, 21);
		this.add(comboBox);
		
		JScrollPane scrollPaneTable = new JScrollPane();
		scrollPaneTable.setBounds(27, 276, 725, 428);
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
		btnsearch.setBounds(482, 193, 101, 21);
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
		btnNewButton_1.setBounds(653, 224, 81, 27);
		this.add(btnNewButton_1);
		
		cBTacgia = new JComboBox();
		cBTacgia.setBounds(100, 225, 132, 27);
		add(cBTacgia);
		
		cBnxb = new JComboBox();
		cBnxb.setBounds(411, 225, 132, 27);
		add(cBnxb);
		
		cBTheloai = new JComboBox();
		cBTheloai.setBounds(253, 225, 132, 27);
		add(cBTheloai);
		
		JLabel lblNewLabel_7_1 = new JLabel("Lọc:");
		lblNewLabel_7_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_7_1.setForeground(Color.WHITE);
		lblNewLabel_7_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_7_1.setBounds(27, 223, 59, 27);
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
		btn.setBounds(565, 231, 66, 21);
		add(btn);
		
		JLabel lblNewLabel = new JLabel("Thư viện Sách PBL3");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(27, 6, 295, 59);
		add(lblNewLabel);
		
		JButton btnNewButton_1_1 = new JButton("Đăng nhập");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//dn = new DNhap(parentFrame);
				//dn.setVisible(true);
			}
		});
		btnNewButton_1_1.setBounds(608, 21, 126, 31);
		add(btnNewButton_1_1);
	}
	}

