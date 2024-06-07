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
import Model.Hoadon;

public class HoadonDAO implements DAOInterface<Hoadon> {
	public static HoadonDAO getInstance() {
		return new HoadonDAO();
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
	public int Insert(Hoadon t) {
		DataSource data = ketNoiSQL();
        try ( Connection conn = data.getConnection()) {
            String query = "INSERT INTO hoadon VALUES(?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, t.get_id_hoadon());
            ps.setInt(2, t.get_id_tt());
            ps.setInt(3, t.get_id_phieu());
            ps.setDate(4,new java.sql.Date(t.get_ngayttoan().getTime()));
            ps.setInt(5, t.get_tongtien());
            int result = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
	}

	@Override
	public int Update(Hoadon t) {
		DataSource data = ketNoiSQL();
        try ( Connection conn = data.getConnection()) {
            String sql = "UPDATE hoadon SET Id_tt = ?,"
                    + " Id_phieu = ?, Ngayttoan = ? , Total = ? WHERE Id_hoadon= ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, t.get_id_tt());
            ps.setInt(2, t.get_id_phieu());
            ps.setDate(3, (Date) t.get_ngayttoan());
            ps.setInt(4, t.get_tongtien());
            int result = ps.executeUpdate();

        
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
	}

	@Override
	public int Delete(Hoadon t) {
		DataSource data = ketNoiSQL();
        try ( Connection conn = data.getConnection()) {
            String query = "DELETE FROM hoadon WHERE Id_hoadon = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, t.get_id_hoadon());
            int result = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
	}


	@Override
	public Hoadon selectById(Hoadon t) {
		Hoadon result = null;
        String query = "SELECT * FROM hoadon WHERE Id_hoadon = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pbl3", "root", "");
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, t.get_id_hoadon());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
            	int id_hoadon = resultSet.getInt(1);
                int id_tt = resultSet.getInt(2);
                int id_phieu = resultSet.getInt(3);
                Date ngayttoan = resultSet.getDate(4);
                int total = resultSet.getInt(5);
                result = new Hoadon(id_hoadon, id_tt, id_phieu, ngayttoan, total);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
	}

	@Override
	public ArrayList<Hoadon> selectByCondition(String condition) {
		ArrayList<Hoadon> listHoadon = new ArrayList<>();
        String query = "SELECT * FROM hoadon WHERE " + condition;
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pbl3", "root", "");
            PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                    int id_hoadon = resultSet.getInt(1);
                    int id_tt = resultSet.getInt(2);
                    int id_phieu = resultSet.getInt(3);
                    Date ngayttoan = resultSet.getDate(4);
                    int total = resultSet.getInt(5);
                    Hoadon hoadon = new Hoadon(id_hoadon, id_tt, id_phieu, ngayttoan, total);
                    listHoadon.add(hoadon);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listHoadon;
	}

	

	@Override
	public void selectAll(ArrayList<Hoadon> listHoadon) {
		DataSource data = ketNoiSQL();
        try ( Connection conn = data.getConnection()) {
            String query = "SELECT * FROM hoadon";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id_hoadon = resultSet.getInt(1);
                int id_tt = resultSet.getInt(2);
                int id_phieu = resultSet.getInt(3);
                Date ngayttoan = resultSet.getDate(4);
                int total = resultSet.getInt(5);
                Hoadon hoadon = new Hoadon(id_hoadon, id_tt, id_phieu, ngayttoan, total);
                listHoadon.add(hoadon);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
	}
	public ArrayList<Hoadon> searchHD(ArrayList<Hoadon> listHD, String t, int dk)
	{
		ArrayList<Hoadon> listSearchHD =  new ArrayList<>();
		if(listHD.size() > 0)
		{
			for(Hoadon e : listHD)
			{	
				if(dk == 0 && String.valueOf(e.get_id_phieu()).toLowerCase().contains(t.toLowerCase()))
				{
					listSearchHD.add(e);
				}
				if(dk == 1 && String.valueOf(e.get_id_hoadon()).toLowerCase().contains(t.toLowerCase()))
				{
					listSearchHD.add(e);
				}
				if(dk == 2 && String.valueOf(e.get_id_tt()).toLowerCase().contains(t.toLowerCase()))
				{
					listSearchHD.add(e);
				}
			}
		}
		return listSearchHD;
	}
	public int getTotalUniqueHD() {
	    int t = 0;
	    DataSource data = ketNoiSQL();
	    String query = "SELECT COUNT(DISTINCT Id_hoadon) FROM hoadon";

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
	 public int getTotalPhi() {
		    int t = 0;
		    DataSource data = ketNoiSQL();
		    String query = "SELECT SUM(Total) FROM hoadon";

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
