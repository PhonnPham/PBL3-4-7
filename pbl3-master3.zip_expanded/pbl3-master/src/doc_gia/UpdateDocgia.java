package doc_gia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.border.LineBorder;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import DAO.InValidAuthorException;
import Model.Docgia;
import Model.Sach;
import Menu.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import gdDN.*;
import javax.swing.JTextField;
import java.awt.Choice;
public class UpdateDocgia extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private jT txtHoten,txtAddress,txtSDT;
	private Docgia docgia;
	private UpdateDocgia up;
	private int id;
	private home home;
	private panelQlydocgia qld = new panelQlydocgia(home);
	/**
	 * @wbp.nonvisual location=-39,214
	 */
	private final JTextField textField = new JTextField();
	private JDateChooser dateNS;
	private JLabel lblNewLabel_8_1;
	private jT txtID;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */

	public UpdateDocgia(home parent, boolean modal, Docgia docgia ) {
	    super(parent, modal);
	    this.docgia = docgia;
	    this.home = parent;
	    initComponents();
	    txtHoten.setText(docgia.get_hoten());
	    txtAddress.setText(docgia.get_diachi());
	    dateNS.setDate(docgia.get_ns());
	    txtSDT.setText(docgia.get_sdt());
	    id = docgia.get_id();
	    txtID.setText(String.valueOf(id));
	}


	public void initComponents() {
		setBounds(100, 100, 1006, 661);
		contentPanel.setBackground(new Color(69, 171, 148));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		JLabel lblNewLabel = new JLabel("Cập Nhật thông tin độc giả");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(305, 75, 251, 35);
		contentPanel.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(197, 197, 197));
		panel.setBounds(306, 135, 617, 412);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(250, 250, 250));
		panel_2.setBounds(0, 0, 620, 412);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_8 = new JLabel("Họ tên");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_8.setBounds(46, 59, 64, 27);
		panel_2.add(lblNewLabel_8);
		
		txtHoten = new jT();
		txtHoten.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtHoten.setBorder(new EmptyBorder(10, 3, 5, 10));
		txtHoten.setBackground(new Color(250, 250, 250));
		txtHoten.setBounds(206, 50, 308, 40);
		panel_2.add(txtHoten);
		txtHoten.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Ngày sinh");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_9.setBounds(46, 132, 90, 27);
		panel_2.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Số điện thoại");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_10.setBounds(46, 299, 90, 27);
		panel_2.add(lblNewLabel_10);
		
		txtAddress = new jT();
		txtAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtAddress.setBorder(new EmptyBorder(10, 3, 5, 10));
		txtAddress.setBackground(new Color(250, 250, 250));
		txtAddress.setBounds(206, 198, 308, 50);
		panel_2.add(txtAddress);
		txtAddress.setColumns(10);
		
		txtSDT = new jT();
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSDT.setBorder(new EmptyBorder(20, 3, 8, 30));
		txtSDT.setBackground(new Color(250, 250, 250));
		txtSDT.setBounds(206, 276, 308, 50);
		panel_2.add(txtSDT);
		txtSDT.setColumns(10);
		
		JLabel lblNewLabel_10_1 = new JLabel("Địa chỉ");
		lblNewLabel_10_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_10_1.setBounds(46, 221, 90, 27);
		panel_2.add(lblNewLabel_10_1);
		
		dateNS = new JDateChooser();
		dateNS.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dateNS.setDateFormatString("dd-MM-yyyy");
		dateNS.setBounds(206, 132, 257, 27);
		panel_2.add(dateNS);
		
		lblNewLabel_8_1 = new JLabel("ID Độc giả");
		lblNewLabel_8_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_8_1.setBounds(46, 20, 127, 27);
		panel_2.add(lblNewLabel_8_1);
		
		txtID = new jT();
		txtID.setText((String) null);
		txtID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtID.setEditable(false);
		txtID.setColumns(10);
		txtID.setBorder(new EmptyBorder(10, 3, 5, 10));
		txtID.setBackground(new Color(250, 250, 250));
		txtID.setBounds(201, 6, 313, 40);
		panel_2.add(txtID);

		
		JButton btnUpdate = new JButton("Cập nhật");
		btnUpdate.setBounds(715, 557, 85, 21);
		contentPanel.add(btnUpdate);
		
		
		JButton btnExit = new JButton("Thoát");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBounds(838, 557, 85, 21);
		contentPanel.add(btnExit);
		btnUpdate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String name = txtHoten.getText();
				Date date = dateNS.getDate();
			    String address = txtAddress.getText();
			    String phone = txtSDT.getText();

			    if (!name.isEmpty() && date!= null && !address.isEmpty() && !phone.isEmpty()) {
			        docgia = new Docgia(id, name, date, address, phone);
			        
			        qld.editDocgia(docgia);
			        
			        
					JOptionPane.showMessageDialog(rootPane, "Sửa thành công");
					dispose();
			    } else {
			        JOptionPane.showMessageDialog(rootPane, "Thông tin không hợp lệ. Vui lòng điền đầy đủ thông tin");
			    }
			}

		});

	}
}
