package Menu;
import gdDN.*;
import QL_Muon_Sach_view.*;
import doc_gia.*;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Model.Sach;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panelTemp;
	private panelMenu panelMenu;
	
	//private ArrayList<Sach> listSach;
	//private panelQlySach panelQlySach;
	private JFrame home = this; // tham chiếu tới Fram hiện tại
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					home frame = new home();
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
	public home() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 842, 614);
			panelTemp = new JPanel();
			panelTemp.setBounds(238, 0, 580, 575);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			//them Menu chinh
			panelMenu = new panelMenu(this);
			contentPane.setLayout(null);
			contentPane.add(panelMenu);
			this.AddPanel(new panelHome(this));
			//contentPane.add(panelQlySach);
			//panelQlySach.setVisible(false);
			//them Menu Home	
	}
	//hien Quanlysach
	public void AddPanel(JPanel pn) {
		contentPane.remove(panelTemp);
		panelTemp=pn;
		//contentPane.add(panelMenu);
		contentPane.add(panelTemp);
		contentPane.revalidate();
		contentPane.repaint();
	}
	
	}

