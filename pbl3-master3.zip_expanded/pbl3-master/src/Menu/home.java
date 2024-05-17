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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Model.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
public class home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panelTemp;
	private panelMenu panelMenu;
	public int maTT = 0;
	public boolean Flag;
	public Thuthu tt;
	
	//private ArrayList<Sach> listSach;
	//private panelQlySach panelQlySach;
	private home home = this; // tham chiếu tới Fram hiện tại
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

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
			setBounds(100, 100, 1100, 725);
			panelTemp = new JPanel();
			//panelTemp.setBounds(238, 0, 580, 575);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			//them Menu chinh
			panelMenu = new panelMenu(home);
			contentPane.setLayout(null);
			contentPane.add(panelMenu);
			this.AddPanel(new panelHomeDN(home));
			//contentPane.add(panelQlySach);
			//panelQlySach.setVisible(false);
			//them Menu Home	
			tt = new Thuthu();
	}
	//hien panel
	public void AddPanel(JPanel pn) {
		contentPane.remove(panelTemp);
		panelTemp=pn;
		//contentPane.add(panelMenu);
		contentPane.add(panelTemp);
		contentPane.revalidate();
		contentPane.repaint();
	}
	
	}

