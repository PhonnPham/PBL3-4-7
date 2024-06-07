package QL_Muon_Sach_view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Menu.*;
import Model.*;
import raven.cell.TableActionCellEditor;
import raven.cell.TableActionCellRender;
import raven.cell.TableActionEvent;

public class panelQlyhoadon extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private DefaultTableModel tableModel;
    private int edit = -1;
    private ArrayList<Hoadon> listHoadon;
    private HoadonDAO HoadonDAO;
    private PhieuDAO PhieuDAO;
    private phieumuon selectedPhieu;
    private TableActionEvent event;
    home parentFrame;
    private panelQlyhoadon qlh = this;
    private JTextField txtNhap;
    private JComboBox comboBox;
    String tenTT;
    
    private boolean flag;
    public panelQlyhoadon(home parentFrame, boolean Flag) {
    	
        this.parentFrame = parentFrame;
        this.flag = Flag;
        initComponents();
        HoadonDAO = new HoadonDAO();
        listHoadon = new ArrayList<>();
        PhieuDAO = new PhieuDAO();
        tableModel = (DefaultTableModel) table.getModel();
        HoadonDAO.getInstance().selectAll(listHoadon);
        showListHoadon(listHoadon);
    }

    private void initComponents() {
        setBounds(275, 0, 825, 725);
        setLayout(null);
        setBackground(new Color(129, 203, 196));

        JLabel lblNewLabel = new JLabel("Quản lý hóa đơn");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel.setBounds(37, 29, 177, 38);
        add(lblNewLabel);

        tableModel = new DefaultTableModel(
            new Object[][] {},
            new String[] {"ID Hóa Đơn", "ID Thủ Thư", "ID Phiếu", "Ngày Thanh Toán", "Tổng Tiền", "Hành Động"}
        ) {
            boolean[] canEdit = {false, false, false, false, false, flag};

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(37, 165, 761, 425);
        add(scrollPane);

        table = new JTable(tableModel);
        table.setFont(new Font("Times New Roman", 0, 14));
        scrollPane.setViewportView(table);

        JLabel lblNewLabel_1 = new JLabel("Tìm kiếm : ");
        lblNewLabel_1.setBounds(37, 119, 83, 16);
        add(lblNewLabel_1);

        txtNhap = new JTextField();
        txtNhap.setBounds(112, 117, 206, 19);
        add(txtNhap);
        txtNhap.setColumns(10);

        comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"ID Phiếu", "ID Hóa đơn", "Mã Thủ thư"}));
        comboBox.setBounds(330, 118, 116, 21);
        add(comboBox);

        JButton btnTim = new JButton("Tìm");
        btnTim.setBackground(new Color(128, 128, 128));
        btnTim.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
            	if (!txtNhap.getText().isEmpty() ) {
            		SearchQuery();
            	} else {
            		JOptionPane.showMessageDialog(null, "Vui lòng điền thông tin cần", "Warning", JOptionPane.WARNING_MESSAGE);
            }
            }
        });
        btnTim.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnTim.setBounds(495, 117, 96, 21);
        add(btnTim);
    }

    void TableEvent(ArrayList<Hoadon> listHD) {
        event = new TableActionEvent() {
            @Override
            public void onView(int row) {
                if (row >= 0 && row < listHD.size()) {
                    Hoadon selectedHoadon = listHD.get(row);
                    // selectedHoadon.get_id_phieu();
                    // XemTTHoadon xem = new XemTTHoadon(parentFrame, true, selectedHoadon, selectedPhieu, qlh);
                    // xem.setVisible(true);
                }
            }

            @Override
            public void onEdit(int row) {
                edit = row;
                if (row >= 0 && row < listHD.size()) {
                    Hoadon selectedHoadon = listHD.get(row);
                    // selectedPhieu = PhieuDAO.selectedId(selectedHoadon.get_id_phieu());
                    // CapNhatTTHoadon xem = new CapNhatTTHoadon(parentFrame, true, selectedHoadon, selectedPhieu, qlh);
                    // xem.setVisible(true);
                }
            }

            @Override
            public void onDelete(int row) {
                Hoadon selectedHoadon = listHD.get(row);
                int result = JOptionPane.showConfirmDialog(qlh, "Bạn có chắc muốn xóa không?", "Thông Báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION && !Bientoancuc.ad.getUserName().isEmpty()) {
                    HoadonDAO.getInstance().Delete(selectedHoadon);
                    listHoadon.remove(selectedHoadon);
                    tableModel.setRowCount(0);
                    tableModel.fireTableDataChanged();
                    JOptionPane.showMessageDialog(qlh, "Xóa thành công");
                    showListHoadon(listHoadon);
                }
            }
        };
    }

   

    void showListHoadon(ArrayList<Hoadon> listHD) {
        TableEvent(listHD);
        tableModel.setRowCount(0);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for (Hoadon e : listHD) {
            // selectedPhieu = PhieuDAO.selectedId(e.get_id_phieu());
            Object[] row = new Object[]{e.get_id_hoadon(), e.get_id_tt(), e.get_id_phieu(), dateFormat.format(e.get_ngayttoan()), e.get_tongtien()};
            tableModel.addRow(row);
            tableModel.fireTableDataChanged();
            table.setRowHeight(45);
            table.setModel(tableModel);
            table.getColumnModel().getColumn(tableModel.getColumnCount() - 1).setCellRenderer(new TableActionCellRender());
            table.getColumnModel().getColumn(tableModel.getColumnCount() - 1).setCellEditor(new TableActionCellEditor(event));

            table.getColumnModel().getColumn(0).setPreferredWidth(15);
            table.getColumnModel().getColumn(1).setPreferredWidth(80);
            table.getColumnModel().getColumn(2).setPreferredWidth(15);
            table.getColumnModel().getColumn(3).setPreferredWidth(100);
            table.getColumnModel().getColumn(4).setPreferredWidth(80);
        }
    }

    public void editHoadon(Hoadon b) {
        listHoadon.set(edit, b);
        HoadonDAO.getInstance().Update(b);
        tableModel.removeRow(edit);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for (Hoadon e : listHoadon) {
            // selectedPhieu = PhieuDAO.selectedId(e.get_id_phieu());
            Object[] row = new Object[]{e.get_id_hoadon(), e.get_id_tt(), e.get_id_phieu(), dateFormat.format(e.get_ngayttoan()), e.get_tongtien()};
            tableModel.insertRow(edit, row);
            tableModel.fireTableDataChanged();
        }
    }
    public void SearchQuery() {
		String t =  txtNhap.getText();
		try {
			if(!t.isEmpty())
			{
				if(comboBox.getSelectedItem().equals("ID Phiếu"))
				{
					ArrayList<Hoadon> arr = HoadonDAO.getInstance().searchHD(listHoadon,t, 0);
					if(arr.size() > 0)
						showListHoadon(arr);
					else
					{
						JOptionPane.showMessageDialog(qlh, "Không tìm thấy ID Phiếu phù hợp");
						txtNhap.setText(null);
					}
				}
				if(comboBox.getSelectedItem().equals("ID Hoá đơn"))
				{
					ArrayList<Hoadon> arr = HoadonDAO.getInstance().searchHD(listHoadon,t,1);
					if(arr.size() > 0)
						showListHoadon(arr);
					else
					{
						JOptionPane.showMessageDialog(qlh, "Không tìm thấy ID phù hợp");
						txtNhap.setText(null);
					}
				}
				if(comboBox.getSelectedItem().equals("Mã Thủ thư"))
				{
					ArrayList<Hoadon> arr = HoadonDAO.getInstance().searchHD(listHoadon,t, 2);
					if(arr.size() > 0)
						showListHoadon(arr);
					else
					{
						JOptionPane.showMessageDialog(qlh, "Không tìm thấy Mã Thủ thư phù hợp");
						txtNhap.setText(null);
					}
				}
			}
			else
			JOptionPane.showMessageDialog(qlh, "Bạn chưa nhập dữ liệu tìm kiếm","Thông Báo",JOptionPane.OK_OPTION);
			
			
			} catch (Exception e2) {
			
		}
	}
}
