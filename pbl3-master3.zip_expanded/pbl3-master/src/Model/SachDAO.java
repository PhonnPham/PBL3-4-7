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
import book_model.*;

public class SachDAO implements DAOInterface<Sach> {
	
	public static SachDAO getInstance() {
		return new SachDAO();
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
	public int Insert(Sach t) {
	        DataSource data = ketNoiSQL();
	        try ( Connection conn = data.getConnection()) {
	            String query = "INSERT INTO BOOK VALUES(?,?,?,?,?,?,?,?)";
	            PreparedStatement ps = conn.prepareStatement(query);
	            ps.setInt(1, t.get_id_sach());
	            ps.setInt(2, t.get_id_tacgia());
	            ps.setString(3, t.get_tacgia());
	            ps.setString(4, t.get_tensach());
	            ps.setString(5, t.get_theloai());
	            ps.setString(6, t.get_nhaxb());
	            ps.setString(7, t.get_namxb());
	            ps.setInt(8, t.get_soluong());
	            int result = ps.executeUpdate();

	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        return -1;
	    }


	@Override
	public int Update(Sach t) {
		DataSource data = ketNoiSQL();
        try ( Connection conn = data.getConnection()) {
            String query = "UPDATE book SET Id_tacgia = ? ,Tac_gia = ?, Name_sach = ?, Theloai_sach = ? ,"
                    + " NhaXB = ?, NamXB = ? , Soluong_sach = ? WHERE Id_sach = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, t.get_id_tacgia());
            ps.setString(2, t.get_tacgia());
            ps.setString(3, t.get_tensach());
            ps.setString(4, t.get_theloai());
            ps.setString(5, t.get_nhaxb());
            ps.setString(6, t.get_namxb());
            ps.setInt(7, t.get_soluong());
            ps.setInt(8, t.get_id_sach());
            int result = ps.executeUpdate();

        
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }


	@Override
	public int Delete(Sach t) {
		DataSource data = ketNoiSQL();
        try ( Connection conn = data.getConnection()) {
            String query = "DELETE FROM book WHERE Id_sach = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, t.get_id_sach());
            int result = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

	@Override
	public void selectAll(ArrayList<Sach> listBook) {
		DataSource data = ketNoiSQL();
        try ( Connection conn = data.getConnection()) {
            String query = "SELECT * FROM book";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id_sach = resultSet.getInt(1);
                int id_tacgia = resultSet.getInt(2);
                String tacgia = resultSet.getString(3);
                String tensach = resultSet.getString(4);
                String theloai = resultSet.getString(5);
                String nhaxb = resultSet.getString(6);
                String namxb = resultSet.getString(7);
                int soluong = resultSet.getInt(8);
                Sach sach = new Sach(id_sach, id_tacgia,tacgia, tensach, theloai, nhaxb, namxb, soluong);
                listBook.add(sach);
           
        } 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }  catch (InValidAuthorException ex) {
           ex.printStackTrace();
        }
	}

	@Override
	public Sach selectById(Sach t) {
		Sach result = null;
        String query = "SELECT * FROM book WHERE Id_sach = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pbl3", "root", "");
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, t.get_id_sach());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id_sach = resultSet.getInt("Id_sach");
                int id_tacgia = resultSet.getInt("Id_tacgia");
                String tacgia = resultSet.getString("Tac_gia");
                String tensach = resultSet.getString("Name_sach");
                String theloai = resultSet.getString("Theloai_sach");
                String nhaxb = resultSet.getString("NhaXB");
                String namxb = resultSet.getString("NamXB");
                int soluong = resultSet.getInt("Soluong_sach"); // Assuming count is retrieved from the 7th column, adjust accordingly if needed
                result = new Sach(id_sach, id_tacgia,tacgia, tensach, theloai, nhaxb, namxb, soluong);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        catch (InValidAuthorException ex) {
            ex.printStackTrace();
         }
        return result;
	}

	@Override
	public ArrayList<Sach> selectByCondition(String condition) {
		ArrayList<Sach> listBook = new ArrayList<>();
        String query = "SELECT * FROM book WHERE " + condition;
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pbl3", "root", "");
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            	int id_sach = resultSet.getInt("Id_sach");
                int id_tacgia = resultSet.getInt("Id_tacgia");
                String tacgia = resultSet.getString("Tac_gia");
                String tensach = resultSet.getString("Name_sach");
                String theloai = resultSet.getString("Theloai_sach");
                String nhaxb = resultSet.getString("NhaXB");
                String namxb = resultSet.getString("NamXB");
                int soluong = resultSet.getInt("Soluong_sach");
                Sach sach = new Sach(id_sach, id_tacgia, tacgia, tensach, theloai, nhaxb, namxb, soluong);
                listBook.add(sach);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        catch (InValidAuthorException ex) {
            ex.printStackTrace();
         }
        return listBook;
	}

	public boolean checkSachExist(String tenSach) {
	    // Thực hiện truy vấn cơ sở dữ liệu để kiểm tra xem tên sách đã tồn tại hay chưa
	    // Trả về true nếu sách tồn tại, ngược lại trả về false
	    // Ví dụ: sử dụng PreparedStatement để thực hiện truy vấn SQL
	    // (Đây chỉ là một mô hình đơn giản, bạn cần thay đổi phù hợp với cấu trúc cơ sở dữ liệu của bạn)
	    boolean result = false;
	    try {Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pbl3", "root", "");
	        String query = "SELECT * FROM book WHERE Name_sach = ?";
	        PreparedStatement preparedStatement = conn.prepareStatement(query);
	        preparedStatement.setString(1, tenSach);
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
	  public Sach seachBookId(ArrayList<Sach> listSach, String t) {
		  	int a = Integer.parseInt(t);
	    	if(a < 0) return null;
	        for (Sach e : listSach) {
	            if (e.get_id_sach() ==  a) {
	                return e;
	            }
	        }
	        return null;

	    }
		
	  public ArrayList<Sach> searchByAuthor(ArrayList<Sach> bookList, String authorName) {
	      ArrayList<Sach> searchResult = new ArrayList<>();
	      for (Sach book : bookList) {
	          String[] fullName = book.get_tacgia().split("\\s+");
	          boolean found = false;
	          for (String namePart : fullName) {
	              if (namePart.toLowerCase().contains(authorName.toLowerCase())) {
	                  found = true;
	                  break;
	              }
	          }
	          if (found) {
	              searchResult.add(book);
	          }
	      }
	      return searchResult;
	  }

	  public ArrayList<Sach> searchByTitle(ArrayList<Sach> bookList, String title) {
	      ArrayList<Sach> searchResult = new ArrayList<>();
	      for (Sach book : bookList) {
	          if (book.get_tensach().toLowerCase().contains(title.toLowerCase())) {
	              searchResult.add(book);
	          }
	      } 
	      return searchResult;
	  }

}