package QL_Muon_Sach_view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Menu.*;
import gdDN.DNhap;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class hoa_don extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					hoa_don frame = new hoa_don();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public hoa_don() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 842, 614);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
	
	JLabel lblNewLabel = new JLabel("Tìm kiếm ");
	lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
	lblNewLabel.setBounds(291, 98, 71, 36);
	contentPane.add(lblNewLabel);
	
	textField = new JTextField();
	textField.setBounds(383, 98, 206, 36);
	contentPane.add(textField);
	textField.setColumns(10);
	
	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(289, 193, 521, 328);
	contentPane.add(scrollPane);
	
	table = new JTable();
	table.setFont(new Font("Times New Roman", Font.PLAIN, 15));
	table.setModel(new DefaultTableModel(
		new Object[][] {
		},
		new String[] {
			"Id Hoá đơn","Tên người mượn", "Thủ thư ghi nhận", "Ngày trả", "Trạng thái sách", "Khoản phạt"
		}
		)
			{
		  boolean[] canEdit = new boolean [] {
	                false, false, false,false,false,false
	            };
		
		 public boolean isCellEditable(int rowIndex, int columnIndex) {
             return canEdit [columnIndex];
         }
		
	});
	table.getColumnModel().getColumn(1).setPreferredWidth(90);
	table.getColumnModel().getColumn(2).setPreferredWidth(90);
	table.getColumnModel().getColumn(4).setPreferredWidth(82);
	scrollPane.setViewportView(table);
	}
}
