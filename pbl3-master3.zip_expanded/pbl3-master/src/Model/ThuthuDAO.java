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

import Model.Docgia;
import Model.Thuthu;

public class ThuthuDAO implements DAOInterface<Thuthu> {
	public static ThuthuDAO getInstance() {
		return new ThuthuDAO();
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
	public int Insert(Thuthu t) {
		DataSource data = ketNoiSQL();
        try ( Connection conn = data.getConnection()) {
            String query = "INSERT INTO thuthu VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, t.get_id());
            ps.setString(2, t.get_hoten());
            ps.setDate(3, new java.sql.Date(t.get_ns().getTime()));
            ps.setString(4, t.get_diachi());
            ps.setString(5, t.get_cccd());
            ps.setString(6, t.get_sdt());
            ps.setString(7, t.get_email());
            ps.setString(8, t.get_username());
            ps.setString(9, t.get_password());
            int result = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
	}

	@Override
	public int Update(Thuthu t) {
		DataSource data = ketNoiSQL();
        try ( Connection conn = data.getConnection()) {
            String sql = "UPDATE thuthu SET Name_tt = ?, Ns_tt = ?, Diachi_tt = ?, Cccd_tt = ?,"
                    + "Sdt_tt = ?, Email_tt = ?, Username_tt = ?, Password_tt = ? WHERE Id_tt= ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, t.get_hoten());
            ps.setDate(2, new java.sql.Date(t.get_ns().getTime()));
            ps.setString(3, t.get_diachi());
            ps.setString(4, t.get_cccd());
            ps.setString(5, t.get_sdt());
            ps.setString(6, t.get_email());
            ps.setString(7, t.get_username());
            ps.setString(8, t.get_password());
            int result = ps.executeUpdate();

        
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
	}

	@Override
	public int Delete(Thuthu t) {
		DataSource data = ketNoiSQL();
        try ( Connection conn = data.getConnection()) {
            String query = "DELETE FROM thuthu WHERE Id_tt = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, t.get_id());
            int result = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
	}


	@Override
	public Thuthu selectById(Thuthu t) {
		Thuthu result = null;
        String query = "SELECT * FROM thuthu WHERE Id_thuthu = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pbl3", "root", "");
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, t.get_id());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
            	int id = resultSet.getInt(1);
                String hoten = resultSet.getString(2);
                Date ns = resultSet.getDate(3);
                String diachi = resultSet.getString(4);
                String cccd = resultSet.getString(5);
                String sdt = resultSet.getString(6);
                String email = resultSet.getString(7);
                String username = resultSet.getString(8);
                String password = resultSet.getString(9); 
                result = new Thuthu(id, hoten, ns, diachi, cccd, sdt, email, username, password);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
	}
	public Thuthu selectByName(String s) {
		Thuthu result = null;
        String query = "SELECT * FROM thuthu WHERE Username_tt = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pbl3", "root", "");
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, s);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
            	int id = resultSet.getInt(1);
                String hoten = resultSet.getString(2);
                Date ns = resultSet.getDate(3);
                String diachi = resultSet.getString(4);
                String cccd = resultSet.getString(5);
                String sdt = resultSet.getString(6);
                String email = resultSet.getString(7);
                String username = resultSet.getString(8);
                String password = resultSet.getString(9); 
                result = new Thuthu(id, hoten, ns, diachi, cccd, sdt, email, username, password);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
	}
	@Override
	public ArrayList<Thuthu> selectByCondition(String condition) {
		ArrayList<Thuthu> listThuthu = new ArrayList<>();
        String query = "SELECT * FROM thuthu WHERE " + condition;
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pbl3", "root", "");
            PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            	int id = resultSet.getInt(1);
                String hoten = resultSet.getString(2);
                Date ns = resultSet.getDate(3);
                String diachi = resultSet.getString(4);
                String cccd = resultSet.getString(5);
                String sdt = resultSet.getString(6);
                String email = resultSet.getString(7);
                String username = resultSet.getString(8);
                String password = resultSet.getString(9);
                Thuthu thuthu = new Thuthu(id, hoten, ns, diachi, cccd, sdt, email, username, password);
                listThuthu.add(thuthu);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listThuthu;
	}	
	

	@Override
	public void selectAll(ArrayList<Thuthu> listThuthu) {
		DataSource data = ketNoiSQL();
        try ( Connection conn = data.getConnection()) {
            String query = "SELECT * FROM thuthu";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String hoten = resultSet.getString(2);
                Date ns = resultSet.getDate(3);
                String diachi = resultSet.getString(4);
                String cccd = resultSet.getString(5);
                String sdt = resultSet.getString(6);
                String email = resultSet.getString(7);
                String username = resultSet.getString(8);
                String password = resultSet.getString(9);
                Thuthu thuthu = new Thuthu(id, hoten, ns, diachi, cccd, sdt, email, username, password);
                listThuthu.add(thuthu);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
	
	}
	public ArrayList<Thuthu> searchTT(ArrayList<Thuthu> listTT, String t, int dk)
	{
		ArrayList<Thuthu> listSearchTT =  new ArrayList<>();
		if(listTT.size() > 0)
		{
			for(Thuthu e : listTT)
			{	
				if(dk == 0 && String.valueOf(e.get_id()).toLowerCase().contains(t.toLowerCase()))
				{
					listSearchTT.add(e);
				}
				if(dk == 1 && String.valueOf(e.get_cccd()).toLowerCase().contains(t.toLowerCase()))
				{
					listSearchTT.add(e);
				}
				if(dk == 2 && String.valueOf(e.get_hoten()).toLowerCase().contains(t.toLowerCase()))
				{
					listSearchTT.add(e);
				}
			}
		}
		return listSearchTT;
	}
	public int getTotalUniqueTT() {
	    int t = 0;
	    DataSource data = ketNoiSQL();
	    String query = "SELECT COUNT(DISTINCT Id_tt) FROM thuthu";

	    try (Connection conn = data.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query);
	         ResultSet resultSet = ps.executeQuery()) {
	         
	        if (resultSet.next()) {
	            t = resultSet.getInt(1);
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return t;
	}

}
