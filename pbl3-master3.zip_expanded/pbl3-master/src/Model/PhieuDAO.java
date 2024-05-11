package Model;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;


public class PhieuDAO implements DAOInterface<phieumuon>
{
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
	
//	@Override
//	public void readRegisterBookSQL(ArrayList<RegisterBook> listRb) {
//		DataSource data = ketNoiSQL();
//        try ( Connection conn = data.getConnection()) {
//            String query = "SELECT * FROM phieumuon";
//            PreparedStatement ps = conn.prepareStatement(query);
//            ResultSet resultSet = ps.executeQuery();
//            while (resultSet.next()) {
//                int id_phieu = resultSet.getInt(1);
//                int id_docgia = resultSet.getInt(2);
//                int id_sach = resultSet.getInt(3);
//                int soluong = resultSet.getInt(4);
//                String ngaymuon = resultSet.getString(5);
//                String ngayhentra = resultSet.getString(6);
//                String giveBookBack = resultSet.getString(7);
//                RegisterBook phieu = new RegisterBook(id_phieu, id_docgia, id_sach,soluong, ngaymuon, ngayhentra,giveBookBack);
//                listRb.add(phieu);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//		
//	}
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
                int phimuon = resultSet.getInt(7);
                String giveBookBack = resultSet.getString(8);
                phieumuon phieu = new phieumuon(id_phieu, id_docgia, id_sach, soluong, ngaymuon, ngaytrehan, phimuon, giveBookBack);
                listPhieumuon.add(phieu);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
		
	}

//	@Override
//	public int updateRegisterBook(RegisterBook rb) {
//		DataSource data = ketNoiSQL();
//        try ( Connection conn = data.getConnection()) {
//        	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//            String sql = "UPDATE phieumuon SET Id_docgia = ?, Id_sach = ?,"
//                    + "Soluong = ? , Ngaymuon = ?, Ngayhentra = ?  WHERE Id_phieu= ?";
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setInt(1, rb.get_id_docgia());
//            ps.setInt(2, rb.get_id_sach());
//            ps.setInt(3, rb.get_soluong());
//            ps.setString(4, sdf.format(rb.get_ngaymuon()));
//            ps.setString(5, sdf.format(rb.get_ngayHenTra()));
//            int result = ps.executeUpdate();
//
//        
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return -1;
//	}

	@Override
	public int Update(phieumuon t) {
		DataSource data = ketNoiSQL();
	    try (Connection conn = data.getConnection()) {
	        String sql = "UPDATE phieumuon SET Soluong = ?, Ngayhentra = ?, Phiphat = ?, Tinh_trang = ? WHERE Id_phieu= ?";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setInt(1, t.get_soluong());
	        ps.setDate(2, new java.sql.Date(t.get_ngaytra().getTime()));
	        ps.setInt(3, t.get_phimuon());
	        ps.setString(4, t.getGiveBookBack());
	        ps.setInt(5, t.get_id_phieu());
	        int result = ps.executeUpdate();
	        return result;
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        return -1;
	    }
	}
	
	@Override
	public int Insert(phieumuon t) {
		DataSource data = ketNoiSQL();
		try ( Connection conn = data.getConnection())
		{
			String sql = "INSERT INTO phieumuon VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, t.get_id_phieu());
            ps.setInt(2, t.get_id_docgia());
            ps.setInt(3, t.get_id_sach());
            ps.setInt(4, t.get_soluong());
            ps.setDate(5, new java.sql.Date(t.get_ngaymuon().getTime()));
            ps.setDate(6, new java.sql.Date(t.get_ngaytra().getTime()));
            ps.setInt(7, t.get_phimuon());
            ps.setString(8, t.getGiveBookBack());
            return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int Delete(phieumuon t) {
		DataSource data = ketNoiSQL();
        try ( Connection conn = data.getConnection()) {
            String query = "DELETE FROM phieumuon WHERE Id_phieu = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, t.get_id_phieu());
            int result = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
	}
	
	
	public phieumuon seachRegisterId(ArrayList<phieumuon> listPhieu, String t) {
	  	int a = Integer.parseInt(t);
    	if(a < 0) return null;
        for (phieumuon e : listPhieu) {
            if (e.get_id_phieu() ==  a) {
                return e;
            }
        }
        return null;

    }
	public ArrayList<phieumuon> searchDGID(ArrayList<phieumuon> listPhieu, String t)
	{
		int a = Integer.parseInt(t);
		ArrayList<phieumuon> listSearchRB =  new ArrayList<>();
		if(listPhieu.size() > 0)
		{
			for(phieumuon e : listPhieu)
			{
				if(e.get_id_docgia() ==  a)
				{
					listSearchRB.add(e);
				}
			}
		}
		return listSearchRB;
	}


	@Override
	public phieumuon selectById(phieumuon t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<phieumuon> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}





	

	
	
}
