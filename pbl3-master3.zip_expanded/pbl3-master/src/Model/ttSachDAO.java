package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import DAO.*;

public class ttSachDAO implements DAOInterface<ttSach> {
    public static ttSachDAO getInstance() {
        return new ttSachDAO();
    }

    private DataSource dataSource;

    public ttSachDAO() {
        dataSource = ketNoiSQL();
    }

    @Override
    public int Insert(ttSach t) {
        String sql = "INSERT INTO ttSach (mucdo, phiphat) VALUES (?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, t.getMucdo());
            pstmt.setInt(2, t.getPhiphat());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int Update(ttSach t) {
        String sql = "UPDATE ttSach SET mucdo = ?, phiphat = ? WHERE mucdo = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, t.getMucdo());
            pstmt.setInt(2, t.getPhiphat());
            pstmt.setString(3, t.getMucdo());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int Delete(ttSach t) {
        String sql = "DELETE FROM ttSach WHERE mucdo = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, t.getMucdo());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public void selectAll(ArrayList<ttSach> list) {
        String sql = "SELECT * FROM ttsach";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String mucdo = rs.getString("mucdo");
                int phiphat = rs.getInt("phiphat");
                ttSach t = new ttSach(mucdo, phiphat);
                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ttSach selectById(ttSach t) {
        String sql = "SELECT * FROM ttSach WHERE mucdo = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, t.getMucdo());
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int phiphat = rs.getInt("phiphat");
                    return new ttSach(t.getMucdo(), phiphat);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<ttSach> selectByCondition(String condition) {
        String sql = "SELECT * FROM ttSach WHERE " + condition;
        ArrayList<ttSach> list = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String mucdo = rs.getString("mucdo");
                int phiphat = rs.getInt("phiphat");
                ttSach t = new ttSach(mucdo, phiphat);
                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
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
}
