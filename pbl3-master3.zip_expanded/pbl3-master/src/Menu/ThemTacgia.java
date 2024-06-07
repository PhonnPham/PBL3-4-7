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
public class ThemTacgia extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private jT txtHoten,txtAddress;
	private ThemTacgia qlthem = this;

	private home home;
	private ThemSach themSach;
	private TacgiaDAO tgDao;
	private ArrayList<Tacgia> listTG;
	/**
	 * @wbp.nonvisual location=-39,214
	 */
	private final JTextField textField = new JTextField();
	private JDateChooser dateNS;
	private jT txtID;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */

	public ThemTacgia(ThemSach them) {
		this.themSach = them;
		tgDao = new TacgiaDAO();
		listTG = new ArrayList<Tacgia>();
		
		initComponents();
		setLocationRelativeTo(null);
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
		
		JLabel lblNewLabel = new JLabel("Thêm mới độc giả");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(175, 3, 260, 63);
		contentPanel.add(lblNewLabel);

		
		JButton btnAdd = new JButton("Thêm");
		btnAdd.setBounds(538, 426, 85, 21);
		contentPanel.add(btnAdd);
		
		
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
		panel_2.setBounds(185, 61, 558, 338);
		contentPanel.add(panel_2);
		panel_2.setBackground(new Color(250, 250, 250));
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_8 = new JLabel("Họ tên");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_8.setBounds(46, 88, 64, 27);
		panel_2.add(lblNewLabel_8);
		
		txtHoten = new jT();
		txtHoten.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtHoten.setBorder(new EmptyBorder(10, 3, 5, 10));
		txtHoten.setBackground(new Color(250, 250, 250));
		txtHoten.setBounds(206, 79, 308, 40);
		panel_2.add(txtHoten);
		txtHoten.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Năm Sinh");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_9.setBounds(46, 160, 90, 27);
		panel_2.add(lblNewLabel_9);
		
		txtAddress = new jT();
		txtAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtAddress.setBorder(new EmptyBorder(10, 3, 5, 10));
		txtAddress.setBackground(new Color(250, 250, 250));
		txtAddress.setBounds(206, 198, 308, 50);
		panel_2.add(txtAddress);
		txtAddress.setColumns(10);
		
		JLabel lblNewLabel_10_1 = new JLabel("Thể loại");
		lblNewLabel_10_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_10_1.setBounds(46, 231, 90, 27);
		panel_2.add(lblNewLabel_10_1);
		
		JLabel lblNewLabel_8_1 = new JLabel("ID Tác giả");
		lblNewLabel_8_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_8_1.setBounds(46, 18, 105, 27);
		panel_2.add(lblNewLabel_8_1);
		
		txtID = new jT();
		txtID.setEditable(true);
		txtID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtID.setColumns(10);
		txtID.setBorder(new EmptyBorder(10, 3, 5, 10));
		txtID.setBackground(new Color(250, 250, 250));
		txtID.setBounds(206, 19, 308, 40);
		panel_2.add(txtID);
		
		dateNS = new JDateChooser();
		dateNS.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dateNS.setDateFormatString("yyyy");
		dateNS.setBounds(209, 160, 257, 27);
		panel_2.add(dateNS);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(txtID.getText());
				String name = txtHoten.getText();
				Date date = dateNS.getDate();
				String address = txtAddress.getText();
				if(!name.isEmpty() && date != null && !address.isEmpty() && !txtID.getText().isEmpty())
				{
					
						Tacgia tg = new Tacgia(id, name, date, address);
						if(!CheckID(tg)) {
						tgDao.getInstance().Insert(tg);
						JOptionPane.showMessageDialog(rootPane, "Thêm tác giả thành công");
//						txtHoten.setText("");
//						txtNs.setText("");
//						txtAddress.setText("");
//						txtSDT.setText("");
						
						ThemSach them = new ThemSach(home, true);
						dispose();
						them.setVisible(true);

						
						}
				}
				
			}

		});

	}
	public boolean CheckID(Tacgia tg) {
		TacgiaDAO.getInstance().selectAll(listTG);
		for(Tacgia t:listTG ) {
			if(t.get_id()== tg.get_id()) return true;
		}
		return false;
	}
}