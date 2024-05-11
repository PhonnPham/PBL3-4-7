package DAO;

import java.util.ArrayList;

import javax.sql.DataSource;


public interface DAOInterface<T> {
	public int Insert(T t);
	
	public int Update(T t);
	
	public int Delete(T t);
	
	public void selectAll(ArrayList<T> t);
	
	public T selectById(T t);
	
	public ArrayList<T> selectByCondition(String condition);
	String USER_NAME = "root";
    String PASSWD = "";
    String DB_NAME = "pbl3";
    int PORT = 3306;
    String SERVER_NAME = "localhost";
	DataSource ketNoiSQL();


}
