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


public class TacgiaDAO implements DAOInterface<Tacgia> {
	public static TacgiaDAO getInstance() {
		return new TacgiaDAO();
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
	public int Insert(Tacgia t) {
		DataSource data = ketNoiSQL();
        try ( Connection conn = data.getConnection()) {
            String query = "INSERT INTO tacgia VALUES(?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, t.get_id());
            ps.setString(2, t.get_hoten());
            ps.setDate(3, new java.sql.Date(t.get_ns().getTime()));
            ps.setString(4, t.get_theloai());
            int result = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
	}

	@Override
	public int Update(Tacgia t) {
		DataSource data = ketNoiSQL();
        try ( Connection conn = data.getConnection()) {
            String sql = "UPDATE tacgia SET Name_tacgia = ?,"
                    + " Ns_tacgia = ?, Theloai = ? WHERE Id_tacgia= ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, t.get_hoten());
            ps.setDate(2, new java.sql.Date(t.get_ns().getTime()));
            ps.setString(3, t.get_theloai());
            int result = ps.executeUpdate();

        
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
	}

	@Override
	public int Delete(Tacgia t) {
		DataSource data = ketNoiSQL();
        try ( Connection conn = data.getConnection()) {
            String query = "DELETE FROM tacgia WHERE Id_tacgia = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, t.get_id());
            int result = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
	}


	@Override
	public Tacgia selectById(Tacgia t) {
		Tacgia result = null;
        String query = "SELECT * FROM tacgia WHERE Id_tacgia = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pbl3", "root", "");
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, t.get_id());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
            	int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                Date ns = resultSet.getDate(3);
                String theloai = resultSet.getString(4);
                result = new Tacgia(id, name, ns, theloai);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
	}
	public Tacgia selectByName(String t) {
		Tacgia result = null;
        String query = "SELECT * FROM tacgia WHERE Name_tacgia = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pbl3", "root", "");
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, t);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
            	int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                Date ns = resultSet.getDate(3);
                String theloai = resultSet.getString(4);
                result = new Tacgia(id, name, ns, theloai);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
	}

	@Override
	public ArrayList<Tacgia> selectByCondition(String condition) {
		ArrayList<Tacgia> listTacgia = new ArrayList<>();
        String query = "SELECT * FROM tacgia WHERE " + condition;
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pbl3", "root", "");
            PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            	int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                Date ns = resultSet.getDate(3);
                String theloai = resultSet.getString(4);
                Tacgia tacgia = new Tacgia(id, name, ns, theloai);
                listTacgia.add(tacgia);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listTacgia;
	}


	@Override
	public void selectAll(ArrayList<Tacgia> listTacgia) {
		DataSource data = ketNoiSQL();
        try ( Connection conn = data.getConnection()) {
            String query = "SELECT * FROM tacgia";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                Date ns = resultSet.getDate(3);
                String theloai = resultSet.getString(4);
                Tacgia tacgia = new Tacgia(id, name, ns, theloai);
                listTacgia.add(tacgia);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
	}
	

}
