package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

//import demo.Docgia;
//import demo.phieumuon;

public class PhieuDAO implements DAOInterface<phieumuon> {
	public static PhieuDAO getInstance() {
		return new PhieuDAO();
	}
	@Override
	public DataSource ketNoiSQL() {
		MysqlDataSource data = new MysqlDataSource();
        data.setUser(USER_NAME);
        data.setPassword(PASSWORD);
        data.setDatabaseName(DB_NAME);
        data.setPortNumber(PORT);
        data.setServerName(SERVER_NAME);	

        return data;
	}

	@Override
	public int Insert(phieumuon t) {
		DataSource data = ketNoiSQL();
        try ( Connection conn = data.getConnection()) {
            String query = "INSERT INTO phieumuon VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, t.get_id_phieu());
            ps.setInt(2, t.get_id_docgia());
            ps.setInt(3, t.get_id_sach());
           // ps.setString(4, t.get_tensach());
            ps.setInt(4, t.get_soluong());
            ps.setDate(5, (Date) t.get_ngaymuon());
            ps.setDate(6, (Date) t.get_ngaytra());
            ps.setInt(8, t.get_phimuon());
            ps.setString(7, t.getGiveBookBack());
            int result = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
	}

	@Override
	public int Update(phieumuon t) {
		DataSource data = ketNoiSQL();
        try ( Connection conn = data.getConnection()) {
            String sql = "UPDATE phieumuon SET Id_docgia = ?, Id_sach = ?, "
                    + "Soluong = ? , Ngaymuon = ?, Ngaytrehan = ?, Phimuon = ?, Tinhtrang = ? WHERE Id_phieu= ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, t.get_id_docgia());
            ps.setInt(2, t.get_id_sach());
           // ps.setString(3, t.get_tensach());
            ps.setInt(3, t.get_soluong());
            ps.setDate(4, new java.sql.Date(t.get_ngaymuon().getTime()));
            ps.setDate(5, new java.sql.Date(t.get_ngaytra().getTime()));
            ps.setInt(6, t.get_phimuon());
            //ps.setInt(7, t.get_phiphatsinh());
            ps.setString(7, t.getGiveBookBack());
            ps.setInt(8, t.get_id_phieu());
            int result = ps.executeUpdate();

        
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
	}

	@Override
	public int Delete(phieumuon t) {
		DataSource data = ketNoiSQL();
        try ( Connection conn = data.getConnection()) {
            String query = "DELETE FROM docgia WHERE Id_phieu = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, t.get_id_phieu());
            int result = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
	}

	@Override
	public phieumuon selectById(phieumuon t) {
		phieumuon result = null;
        String query = "SELECT * FROM phieumuon WHERE Id_phieu = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pbl3", "root", "");
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, t.get_id_phieu());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
            	int id_phieu = resultSet.getInt(1);
                int id_docgia = resultSet.getInt(2);
                int id_sach = resultSet.getInt(3);
               // String tensach = resultSet.getString(4);
                int soluong = resultSet.getInt(4);
                Date ngaymuon = resultSet.getDate(5);
                Date ngaytrehan = resultSet.getDate(6);
                int phimuon = resultSet.getInt(8);
                String giveBookBack = resultSet.getString(7);
                result = new phieumuon(id_phieu, id_docgia, id_sach, soluong, ngaymuon, ngaytrehan, phimuon, giveBookBack);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
	}

	@Override
	public ArrayList<phieumuon> selectByCondition(String condition) {
		ArrayList<phieumuon> listPhieumuon = new ArrayList<>();
        String query = "SELECT * FROM phieumuon WHERE " + condition;
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pbl3", "root", "");
            PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            	int id_phieu = resultSet.getInt(1);
                int id_docgia = resultSet.getInt(2);
                int id_sach = resultSet.getInt(3);
                //String tensach = resultSet.getString(4);
                int soluong = resultSet.getInt(4);
                Date ngaymuon = resultSet.getDate(5);
                Date ngaytrehan = resultSet.getDate(6);
                int phimuon = resultSet.getInt(8);
                String giveBookBack = resultSet.getString(7);	
                phieumuon phieu = new phieumuon(id_phieu, id_docgia, id_sach, soluong, ngaymuon, ngaytrehan, phimuon, giveBookBack);
                listPhieumuon.add(phieu);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listPhieumuon;
	}
	
	@Override
	public void selectAll(ArrayList<phieumuon> listPhieumuon) {
		DataSource data = ketNoiSQL();
        try ( Connection conn = data.getConnection()) {
            String query = "SELECT * FROM phieumuon";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id_phieu = resultSet.getInt(1);
                int id_docgia = resultSet.getInt(2);
                int id_sach = resultSet.getInt(3);
                //String tensach = resultSet.getString(4);
                int soluong = resultSet.getInt(4);
                Date ngaymuon = resultSet.getDate(5);
                Date ngaytrehan = resultSet.getDate(6);
                int phimuon = resultSet.getInt(8);
                String giveBookBack = resultSet.getString(7);
                phieumuon phieu = new phieumuon(id_phieu, id_docgia, id_sach, soluong, ngaymuon, ngaytrehan, phimuon, giveBookBack);
                listPhieumuon.add(phieu);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
		
	}

}
