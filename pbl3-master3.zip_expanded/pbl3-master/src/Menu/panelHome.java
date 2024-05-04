package Menu;

import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JButton;

public class panelHome extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public panelHome() {
		new JPanel();
		this.setBackground(new Color(129, 203, 196));
		this.setBounds(238, 0, 580, 575);
		setLayout(null);
		
		JButton btnDangNhap = new JButton("Đăng Nhập");
		btnDangNhap.setBounds(419, 19, 132, 45);
		add(btnDangNhap);
		

	}

}
