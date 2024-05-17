package doc_gia;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Menu.home;
import Model.*;
import raven.cell.TableActionCellEditor;
import raven.cell.TableActionCellRender;
import raven.cell.TableActionEvent;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class panelQlydocgia extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private CheckboxGroup cg;
	private home parentFrame;
	private int edit = -1;
	private ArrayList<Docgia> listDocgia;
	private DocgiaDAO DocgiaDao;
	private DefaultTableModel tableModel;
	private TableActionEvent event;
	
	
	//private qlthemdocgia qlthem;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ql_nguoi_muon frame = new ql_nguoi_muon();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public panelQlydocgia(home parentFrame) {
		this.parentFrame = parentFrame;
		initComponents();
		//setLocationRelativeTo(null);
		DocgiaDao = new DocgiaDAO();
		listDocgia = new ArrayList<>();
		tableModel = (DefaultTableModel) table.getModel();
		DocgiaDao.getInstance().selectAll(listDocgia);
		showListDocgia();
	}
	
	private void showListDocgia() {
		event = new TableActionEvent() {
			
			@Override
			public void onView(int row) {
				if(row >= 0 && row < listDocgia.size())
				{
					Docgia selectedDocgia =  listDocgia.get(row);
					//qldocgia xem =  new qldocgia(qlnm,true,selectedDocgia);					
					//qldocgia.setVisible(false);					
					//xem.setVisible(true);
				}
			}
			
			@Override
			public void onEdit(int row) {
				edit = row;
				if(row >= 0 && row < listDocgia.size())
				{
					Docgia selecteddocgia =  listDocgia.get(row);
					UpdateDocgia up =  new UpdateDocgia(parentFrame,true,selecteddocgia);
					
					up.setVisible(true);
					
					//up.setVisible(true);
				}
			}
			
			@Override
			public void onDelete(int row) {
			    if (row >= 0 && row < listDocgia.size()) {
			        Docgia selectedDocgia = listDocgia.get(row);
			        
			        int option = JOptionPane.showConfirmDialog(parentFrame, "Bạn có chắc chắn muốn xóa?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
			        
			        if (option == JOptionPane.YES_OPTION) {
			            int result = DocgiaDAO.getInstance().Delete(selectedDocgia);
						if (result > 0) {
						    listDocgia.remove(row);
						    tableModel.removeRow(row);
						    tableModel.fireTableDataChanged();
						    JOptionPane.showMessageDialog(parentFrame, "Xóa thành công");
						} else {
						    JOptionPane.showMessageDialog(parentFrame, "Xóa không thành công");
						}
			        }
			    } else {
			        JOptionPane.showMessageDialog(parentFrame, "Hàng không hợp lệ");
			    }
			}


		};
        tableModel.setRowCount(0);
        //int stt = 1;
        for (Docgia e : listDocgia) {
            Object[] row = new Object[]{ e.get_id(),
                e.get_hoten(),e.get_ns(),e.get_diachi(), e.get_sdt()
                };
            tableModel.addRow(row);
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

    }
	public void SearchQuery() {
	    try {
	        String column = cg.getSelectedCheckbox().getLabel();
	        String value = textField.getText();
	        String query = "";

	        if(column.equals("Theo ID")) query = "select * from docgia where Id_docgia ='" + value + "'";
	        else if(column.equals("Theo Tên")) query = "select * from docgia where Name_docgia LIKE '%" + value + "%'";
	        else if(column.equals("Theo Ngày sinh")) query = "select * from docgia where Ns_docgia LIKE '%" + value + "%'";
	        else if(column.equals("Theo Địa chỉ")) query = "select * from docgia where Dc_docgia LIKE '%" + value + "%'";
	        else if(column.equals("Theo Số điện thoại")) query = "select * from docgia where Sdt_docgia LIKE '%" + value + "%'";

	        

	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pbl3", "root", "");
	        PreparedStatement stmt = conn.prepareStatement(query);

	        ResultSet rs = stmt.executeQuery();
	        DefaultTableModel model = (DefaultTableModel) table.getModel();
	        model.setRowCount(0);
	        ResultSetMetaData metadata = rs.getMetaData();
	        int columnCount = metadata.getColumnCount();
	        while (rs.next()) {
	            Object[] row = new Object[columnCount];
	            for (int i = 1; i <= columnCount; i++) {
	                row[i - 1] = rs.getObject(i);
	            }
	            model.addRow(row);
	        }
	        conn.close();
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}


	
	 public void editDocgia(Docgia b) {
	        listDocgia.set(edit, b);
	        DocgiaDAO.getInstance().Update(b);
	        tableModel.removeRow(edit);
	        Object[] row = new Object[]{ b.get_id(),
	                b.get_hoten(),b.get_ns(),b.get_diachi(), b.get_sdt()
	                };
	        tableModel.insertRow(edit, row);
	        tableModel.fireTableDataChanged();
	    }
	 
	public void initComponents() {
		new JPanel();
		//setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		 setBackground(new Color(129, 203, 196));
		this.setLayout(null);
		setBounds(275, 0, 975, 725);
	
	JLabel lblNewLabel = new JLabel("Quản lý Độc giả");
	lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
	lblNewLabel.setBounds(48, 6, 197, 41);
	this.add(lblNewLabel);
	
	JLabel lblNewLabel_1 = new JLabel("Tìm kiếm");
	lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
	lblNewLabel_1.setBounds(48, 104, 71, 21);
	this.add(lblNewLabel_1);
	
	textField = new JTextField();
	textField.setBounds(151, 102, 217, 27);
	this.add(textField);
	textField.setColumns(10);
	
	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(21, 225, 768, 305);
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
	btnNew.setBounds(433, 45, 141, 41);
	this.add(btnNew);
	
	JButton btnSearch = new JButton("Tìm");
	btnSearch.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnSearch) {
	            if (!textField.getText().isEmpty() && cg.getSelectedCheckbox() != null) {
	                SearchQuery();
	            } else {
	                JOptionPane.showMessageDialog(null, "Vui lòng điền thông tin cần", "Warning", JOptionPane.WARNING_MESSAGE);
	            }
	        }
			
		}
	});
	btnSearch.setBounds(470, 98, 103, 35);
	this.add(btnSearch);
	
	JPanel pn = new JPanel();
	pn.setBounds(48, 146, 476, 67);
	this.add(pn);
	cg = new CheckboxGroup();
    pn.add(new Checkbox("Theo ID", cg, false));
    pn.add(new Checkbox("Theo Tên", cg, false));
    pn.add(new Checkbox("Theo Ngày sinh", cg, false));
    pn.add(new Checkbox("Theo Địa chỉ", cg, false));
    pn.add(new Checkbox("Theo Số điện thoại", cg, false));
	
	}
}
