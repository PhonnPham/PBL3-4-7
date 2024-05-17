package Model;
import DAO.*;
import DAO.DataInValidImplement;
public class ttSach {
	private String mucdo;

	public ttSach(String mucdo, int phiphat) {
		super();
		this.mucdo = mucdo;
		this.phiphat = phiphat;
	}


	private int phiphat;
	
	public String getMucdo() {
		return mucdo;
	}

	public void setMucdo(String mucdo) {
		this.mucdo = mucdo;
	}

	public int getPhiphat() {
		return phiphat;
	}

	public void setPhiphat(int phiphat) {
		this.phiphat = phiphat;
	}

	
public ttSach() {
		
	}
	
}
