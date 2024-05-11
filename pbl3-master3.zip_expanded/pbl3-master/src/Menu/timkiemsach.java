package Menu;

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
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.*;
import DAO.InValidAuthorException;
import gdDN.*;
import raven.cell.TableActionCellEditor;
import raven.cell.TableActionCellRender;
import raven.cell.TableActionEvent;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import javax.swing.JTable;

public class timkiemsach extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane,panelMenu;
	private ArrayList<Sach> listSach;
	private SachDAO SachDao;
	private CheckboxGroup cg;
	private JTextField textField;
	private JTable table;
	private Sach sach;
	private DefaultTableModel tableModel;
	private String tentt;

	/**
	 * Launch the application.
	 */	
	public   timkiemsach() {
	    initComponents();
	    setLocationRelativeTo(null);
	    SachDao = new SachDAO();
	    listSach = new ArrayList<>();
	    tableModel = (DefaultTableModel) table.getModel();
	    SachDAO.getInstance().selectAll(listSach);
	    showListBook(); // Gọi phương thức showListBook() để hiển thị dữ liệu lên bảng
	}

	
	private void showListBook() {
		 tableModel.setRowCount(0);
	    for (Sach e : listSach) {
	        Object[] row = new Object[]{ 
	            e.get_tensach(), 
	            e.get_tacgia(),
	            e.get_theloai(),
	            e.get_nhaxb(),	            
	            e.get_namxb()
	        };	        
	        tableModel.addRow(row);
	    }
	    table.setRowHeight(45);
	}





	
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1016, 686);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(73, 167, 151));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelMenu = new JPanel();
		panelMenu.setBackground(new Color(63, 133, 124));
		panelMenu.setBounds(0, 10, 248, 649);
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);
		
		JLabel lbl1 = new JLabel("Quản lý thư viện");
		lbl1.setForeground(new Color(255, 255, 255));
		
		lbl1.setIcon(new ImageIcon("C:\\Users\\LENOVO\\OneDrive\\Pictures\\Saved Pictures\\manager man.png"));
		lbl1.setHorizontalTextPosition(SwingConstants.CENTER);
		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1.setVerticalTextPosition(SwingConstants.BOTTOM); // Hiển thị văn bản phía dưới icon
		lbl1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl1.setBounds(33, 23, 148, 124);
		panelMenu.add(lbl1);
		
		JLabel lbl4 = new JLabel("Quản lý mượn-trả sách");
		lbl4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl4.setForeground(new Color(255, 255, 255));
		lbl4.setIcon(new ImageIcon("C:\\Users\\LENOVO\\OneDrive\\Pictures\\Saved Pictures\\icons8-list-48.png"));
		lbl4.setBounds(10, 348, 227, 41);
		panelMenu.add(lbl4);
		
		JLabel lbl2 = new JLabel("Trang chủ");
		lbl2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl2.setIcon(new ImageIcon("C:\\Users\\LENOVO\\OneDrive\\Pictures\\Saved Pictures\\home.png"));
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
		lbl3.setIcon(new ImageIcon("C:\\Users\\LENOVO\\OneDrive\\Pictures\\Saved Pictures\\book stack.png"));
		lbl3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl3.setBounds(10, 263, 207, 55);
		panelMenu.add(lbl3);
		
		JLabel lbl5 = new JLabel("Quản lý người mượn");
		lbl5.setIcon(new ImageIcon("C:\\Users\\LENOVO\\OneDrive\\Pictures\\Saved Pictures\\people manage.png"));
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
	lbl6.setIcon(new ImageIcon("C:\\Users\\LENOVO\\OneDrive\\\\Pictures\\Saved Pictures\\log out.png"));
	lbl6.setFont(new Font("Tahoma", Font.PLAIN, 16));
	lbl6.setForeground(new Color(255, 255, 255));
	lbl6.setBounds(10, 584, 207, 55);
	panelMenu.add(lbl6);
	
	JLabel lblNewLabel = new JLabel("Danh mục sách");
	lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
	lblNewLabel.setBounds(269, 30, 210, 39);
	contentPane.add(lblNewLabel);
	JPanel pn1 = new JPanel();
	pn1.setBackground(new Color(73, 167, 151));
	pn1.setBounds(291, 152, 628, 39);
	contentPane.add(pn1);
	cg = new CheckboxGroup();
    pn1.add(new Checkbox("Theo Tên Sách", cg, false));
    pn1.add(new Checkbox("Theo Tên Tác Giả", cg, false));
    pn1.add(new Checkbox("Theo Thể Loại", cg, false));
    
    textField = new JTextField();
    textField.setBounds(436, 90, 412, 39);
    contentPane.add(textField);
    textField.setColumns(10);
    
    JLabel lblNewLabel_1 = new JLabel("Tìm kiếm");
    lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
    lblNewLabel_1.setBounds(323, 99, 92, 29);
    contentPane.add(lblNewLabel_1);
    
    JButton btnOK = new JButton("OK");
    btnOK.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		if (e.getSource() == btnOK) {
	            if (!textField.getText().isEmpty() && cg.getSelectedCheckbox() != null) {
	                SearchQuery();
	            } else {
	                JOptionPane.showMessageDialog(null, "Vui lòng điền và chọn thông tin cần", "Warning", JOptionPane.WARNING_MESSAGE);
	            }
	        }
    	}
    });
    btnOK.setFont(new Font("Tahoma", Font.PLAIN, 16));
    btnOK.setBounds(873, 90, 92, 39);
    contentPane.add(btnOK);
    
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(301, 201, 666, 412);
    contentPane.add(scrollPane);
    
    table = new JTable();
    scrollPane.setViewportView(table);
    table.setModel(new DefaultTableModel(
    	    new Object[][] {},
    	    new String[] {
    	        "Tên sách", "Tác giả", "Thể loại", "Nhà xuất bản", "Năm xuất bản"
    	    }
    	) {
    	    boolean[] canEdit = new boolean[] { false, false, false, false, false };

    	    @Override
    	    public boolean isCellEditable(int rowIndex, int columnIndex) {
    	        return canEdit[columnIndex];
    	    }
    	});
    	table.getColumnModel().getColumn(0).setPreferredWidth(80);
    	table.getColumnModel().getColumn(1).setPreferredWidth(80);
	}
	public void SearchQuery() {
	    try {
	        String column = cg.getSelectedCheckbox().getLabel();
	        String value = textField.getText();
	        String query = "";

	        
	        if(column.equals("Theo Tên Sách")) query = "select Name_sach,Tac_gia,Theloai_sach,NhaXB,NamXB from book where Name_sach LIKE '%" + value + "%'";
	        else if(column.equals("Theo Tên Tác Giả")) query = "select Name_sach,Tac_gia,Theloai_sach,NhaXB,NamXB from book where Tac_gia LIKE '%" + value + "%'";
	        else if(column.equals("Theo Thể Loại")) query = "select Name_sach,Tac_gia,Theloai_sach,NhaXB,NamXB from book where Theloai_sach LIKE '%" + value + "%'";

	        

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
}

