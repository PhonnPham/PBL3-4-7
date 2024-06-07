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

public class DocgiaDAO implements DAOInterface<Docgia>{
	
	public static DocgiaDAO getInstance() {
		return new DocgiaDAO();
	}
	
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
	public int Insert(Docgia t) {
		DataSource data = ketNoiSQL();
        try ( Connection conn = data.getConnection()) {
            String query = "INSERT INTO docgia VALUES(?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, t.get_id());
            ps.setString(2, t.get_hoten());
            ps.setDate(3, new java.sql.Date(t.get_ns().getTime()));
            ps.setString(4, t.get_diachi());
            ps.setString(5, t.get_sdt());
            int result = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
	}

	@Override
	public int Update(Docgia t) {
		DataSource data = ketNoiSQL();
        try ( Connection conn = data.getConnection()) {
            String sql = "UPDATE docgia SET Name_docgia = ?,"
                    + " Ns_docgia = ?, Dc_docgia = ? , Sdt_docgia = ? WHERE Id_docgia= ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, t.get_hoten());
            ps.setDate(2, new java.sql.Date(t.get_ns().getTime()));
            ps.setString(3, t.get_diachi());
            ps.setString(4, t.get_sdt());
            ps.setInt(5, t.get_id());
            int result = ps.executeUpdate();

        
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
	}

	@Override
	public int Delete(Docgia t) {
		DataSource data = ketNoiSQL();
        try ( Connection conn = data.getConnection()) {
            String query = "DELETE FROM docgia WHERE Id_docgia = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, t.get_id());
            int result = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
	}
	
	@Override
	public void selectAll(ArrayList<Docgia> listDocgia) {
		DataSource data = ketNoiSQL();
        try ( Connection conn = data.getConnection()) {
            String query = "SELECT * FROM docgia";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String hoten = resultSet.getString(2);
                Date ns = resultSet.getDate(3);
                String diachi = resultSet.getString(4);
                String sdt = resultSet.getString(5);
                Docgia docgia = new Docgia(id, hoten, ns, diachi, sdt);
                listDocgia.add(docgia);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
		
	}


	@Override
	public Docgia selectById(Docgia t) {
		Docgia result = null;
        String query = "SELECT * FROM docgia WHERE Id_docgia = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pbl3", "root", "");
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, t.get_id());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("Id_docgia");
                String hoten = resultSet.getString("Name_docgia");
                Date ns = resultSet.getDate("Ns_docgia");
                String diachi = resultSet.getString("Dc_docgia");
                String sdt = resultSet.getString("Sdt_docgia"); 
                result = new Docgia(id, hoten, ns, diachi, sdt);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
	}

	@Override
	public ArrayList<Docgia> selectByCondition(String condition) {
		ArrayList<Docgia> listDocgia = new ArrayList<>();
        String query = "SELECT * FROM docgia WHERE " + condition;
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pbl3", "root", "");
            PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            	int id = resultSet.getInt("Id_docgia");
                String hoten = resultSet.getString("Name_docgia");
                Date ns = resultSet.getDate("Ns_docgia");
                String diachi = resultSet.getString("Dc_docgia");
                String sdt = resultSet.getString("Sdt_docgia"); 
                Docgia docgia = new Docgia(id, hoten, ns, diachi, sdt);
                listDocgia.add(docgia);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listDocgia;
	}
	
	public Docgia selectedId(int t) {
		Docgia result = null;
        String query = "SELECT * FROM docgia WHERE Id_docgia = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pbl3", "root", "");
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, t);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("Id_docgia");
                String hoten = resultSet.getString("Name_docgia");
                Date ns = resultSet.getDate("Ns_docgia");
                String diachi = resultSet.getString("Dc_docgia");
                String sdt = resultSet.getString("Sdt_docgia"); 
                result = new Docgia(id, hoten, ns, diachi, sdt);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
	}
	  public Docgia seachDGId(ArrayList<Docgia> listDG, String t) {
		  	int a = Integer.parseInt(t);
	    	if(a < 0) return null;
	        for (Docgia e : listDG) {
	            if (e.get_id() ==  a) {
	                return e;
	            }
	        }
	        return null;
	    }
	  //Ham tim kiem
	  public ArrayList<Docgia> searchDGID(ArrayList<Docgia> listDG, String t, int dk)
		{
			ArrayList<Docgia> listSearchDG =  new ArrayList<>();
			if(listDG.size() > 0)
			{
				for(Docgia e : listDG)
				{	
					if(dk == 1 && String.valueOf(e.get_id()).toLowerCase().contains(t.toLowerCase()))
					{
						listSearchDG.add(e);
					}
					if(dk == 0 && String.valueOf(e.get_hoten()).toLowerCase().contains(t.toLowerCase()))
					{
						listSearchDG.add(e);
					}
					if(dk == 2 && String.valueOf(e.get_sdt()).toLowerCase().contains(t.toLowerCase()))
					{
						listSearchDG.add(e);
					}
				}
			}
			return listSearchDG;
		}
	  public Docgia seachDGName(ArrayList<Docgia> listDG, String t) {
	    	//if(a < 0) return null;
	        for (Docgia e : listDG) {
	            if (e.get_hoten().toLowerCase().contains(t.toLowerCase())) {
	                return e;
	            }
	        }
	        return null;
	    }
	  public boolean checkDGSDT(String t) {
			DataSource data = ketNoiSQL();
		    boolean result = false;
		    try {Connection conn = data.getConnection();
		        String query = "SELECT * FROM docgia WHERE Sdt_docgia = ?";
		        PreparedStatement preparedStatement = conn.prepareStatement(query);
		        preparedStatement.setString(1, t);
		        ResultSet resultSet = preparedStatement.executeQuery();
		        if (resultSet.next()) {
		            result = true; // Sách tồn tại trong cơ sở dữ liệu
		        }
		        resultSet.close();
		        preparedStatement.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return result;
		}
	  public int getTotalUniqueDG() {
		    int t = 0;
		    DataSource data = ketNoiSQL();
		    String query = "SELECT COUNT(DISTINCT Id_docgia) FROM docgia";

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
