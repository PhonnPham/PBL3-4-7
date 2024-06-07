package Menu;

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
import java.util.ArrayList;
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

import Model.*;
import Menu.home;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import gdDN.*;
import javax.swing.JTextField;
import java.awt.Choice;
public class XemTT extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private jT txtHoten,txtAddress,txtSDT;
	private XemTT qlthem = this;
	private int id;
	private home home;
	private Dashboard pnd = new Dashboard(home);
	//private ql_nguoi_muon qlnguoimuon;
	private ThuthuDAO ttDao;
	private ArrayList<Thuthu> listTT;
	private Thuthu tt;
	/**
	 * @wbp.nonvisual location=-39,214
	 */
	private final JTextField textField = new JTextField();
	private JDateChooser dateNS;
	private jT txtID;
	private jT txtpassword;
	private jT txtusername;
	private jT txtCCCD;
	private jT txtEmail;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */

	public XemTT(home parent, boolean modal, Thuthu tt) {
		super(parent, modal);
		this.tt = tt;
		this.home = parent;
		initComponents();
		txtID.setText(String.valueOf(tt.get_id()));
		txtAddress.setText(tt.get_diachi());
		txtCCCD.setText(tt.get_cccd());
		dateNS.setDate(tt.get_ns());
		txtHoten.setText(tt.get_hoten());
		txtusername.setText(tt.get_username());
		txtpassword.setText(tt.get_password());
		txtEmail.setText(tt.get_email());
		
		
	}
	private int setID(ArrayList<Thuthu> list)
	{
		int max = list.get(0).get_id();
		if(list.size() == 0)
			max = 1;
		else
		{
			for(int i = 1;i<(list.size() - 1); i++)
			{
				if(max < list.get(i).get_id())
				{
					max = list.get(i).get_id();
				}
			}
		}
		return max;
	}


	public void initComponents() {
		
		
		setBounds(100, 100, 848, 494);
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
		
		JLabel lblNewLabel = new JLabel("Thêm mới thủ thư");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(137, 0, 205, 57);
		contentPanel.add(lblNewLabel);
		
		
		JButton btnCancel = new JButton("Thoát");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int r = JOptionPane.showConfirmDialog(contentPanel, "Bạn có chắc muốn thoát không?", "Thông Báo", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if(r == JOptionPane.YES_OPTION)
				dispose();
	
				
			}
			
		});
		btnCancel.setBounds(658, 426, 85, 21);
		contentPanel.add(btnCancel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(137, 61, 620, 357);
		contentPanel.add(panel_2);
		panel_2.setBackground(new Color(250, 250, 250));
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_8 = new JLabel("Họ tên");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_8.setBounds(3, 102, 64, 27);
		panel_2.add(lblNewLabel_8);
		
		txtHoten = new jT();
		txtHoten.setEditable(false);
		txtHoten.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtHoten.setBorder(new EmptyBorder(10, 3, 5, 10));
		txtHoten.setBackground(new Color(250, 250, 250));
		txtHoten.setBounds(119, 92, 160, 40);
		panel_2.add(txtHoten);
		txtHoten.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Ngày sinh");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_9.setBounds(3, 170, 90, 27);
		panel_2.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Số điện thoại");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_10.setBounds(313, 40, 90, 27);
		panel_2.add(lblNewLabel_10);
		
		txtAddress = new jT();
		txtAddress.setEditable(false);
		txtAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtAddress.setBorder(new EmptyBorder(10, 3, 5, 10));
		txtAddress.setBackground(new Color(250, 250, 250));
		txtAddress.setBounds(119, 219, 160, 50);
		panel_2.add(txtAddress);
		txtAddress.setColumns(10);
		
		txtSDT = new jT();
		txtSDT.setEditable(false);
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSDT.setBorder(new EmptyBorder(20, 3, 8, 30));
		txtSDT.setBackground(new Color(250, 250, 250));
		txtSDT.setBounds(429, 23, 160, 50);
		panel_2.add(txtSDT);
		txtSDT.setColumns(10);
		
		JLabel lblNewLabel_10_1 = new JLabel("Địa chỉ");
		lblNewLabel_10_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_10_1.setBounds(3, 233, 90, 27);
		panel_2.add(lblNewLabel_10_1);
		
		JLabel lblNewLabel_8_1 = new JLabel("ID Thủ thư");
		lblNewLabel_8_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_8_1.setBounds(3, 40, 104, 27);
		panel_2.add(lblNewLabel_8_1);
		
		txtID = new jT();
		txtID.setEditable(false);
		txtID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtID.setColumns(10);
		txtID.setBorder(new EmptyBorder(10, 3, 5, 10));
		txtID.setBackground(new Color(250, 250, 250));
		txtID.setBounds(119, 40, 160, 40);
		panel_2.add(txtID);
		
		dateNS = new JDateChooser();
		dateNS.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dateNS.setDateFormatString("dd-MM-yyyy");
		dateNS.setBounds(122, 169, 160, 27);
		panel_2.add(dateNS);
		
		JLabel lblNewLabel_10_2 = new JLabel("CCCD");
		lblNewLabel_10_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_10_2.setBounds(313, 106, 90, 27);
		panel_2.add(lblNewLabel_10_2);
		
		txtCCCD = new jT();
		txtCCCD.setEditable(false);
		txtCCCD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCCCD.setColumns(10);
		txtCCCD.setBorder(new EmptyBorder(20, 3, 8, 30));
		txtCCCD.setBackground(new Color(250, 250, 250));
		txtCCCD.setBounds(429, 93, 160, 50);
		panel_2.add(txtCCCD);
		
		JLabel lblNewLabel_10_2_1 = new JLabel("UserName");
		lblNewLabel_10_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_10_2_1.setBounds(313, 199, 90, 27);
		panel_2.add(lblNewLabel_10_2_1);
		
		txtusername = new jT();
		txtusername.setEditable(false);
		txtusername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtusername.setColumns(10);
		txtusername.setBorder(new EmptyBorder(20, 3, 8, 30));
		txtusername.setBackground(new Color(250, 250, 250));
		txtusername.setBounds(429, 186, 160, 50);
		panel_2.add(txtusername);
		
		JLabel lblNewLabel_10_2_2 = new JLabel("PassWord");
		lblNewLabel_10_2_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_10_2_2.setBounds(313, 268, 90, 27);
		panel_2.add(lblNewLabel_10_2_2);
		
		txtpassword = new jT();
		txtpassword.setEditable(false);
		txtpassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpassword.setColumns(10);
		txtpassword.setBorder(new EmptyBorder(20, 3, 8, 30));
		txtpassword.setBackground(new Color(250, 250, 250));
		txtpassword.setBounds(429, 254, 160, 50);
		panel_2.add(txtpassword);
		
		JLabel lblNewLabel_10_1_1 = new JLabel("Email");
		lblNewLabel_10_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_10_1_1.setBounds(3, 295, 90, 27);
		panel_2.add(lblNewLabel_10_1_1);
		
		txtEmail = new jT();
		txtEmail.setEditable(false);
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtEmail.setColumns(10);
		txtEmail.setBorder(new EmptyBorder(10, 3, 5, 10));
		txtEmail.setBackground(new Color(250, 250, 250));
		txtEmail.setBounds(119, 281, 160, 50);
		panel_2.add(txtEmail);

	}
}
