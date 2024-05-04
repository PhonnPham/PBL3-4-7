package book_model;
import DAO.*;
import DAO.DataInValidImplement;

public class Sach {
	private int _id_sach;
	private int _id_tacgia;
	private String _tacgia;
	private String _tensach;
	private String _nhaxb;
	private String _namxb;
	private int _soluong;
	private String _theloai;
	
	public Sach() {
		
	}
	public Sach(int _id_sach, int _id_tacgia,String _tacgia, String _tensach,String _theloai, String _nhaxb, String namxb, int _soluong) throws InValidAuthorException {
		super();
		this._id_sach = _id_sach;
		this._id_tacgia = _id_tacgia;
		set_tacgia(_tacgia);
		this._tensach = _tensach;
		this._nhaxb = _nhaxb;
		this._namxb = namxb;
		this._soluong = _soluong;
		this._theloai = _theloai;
	}
	public String get_tacgia() {
		return _tacgia;
	}
	public void set_tacgia(String _tacgia) throws InValidAuthorException {
	
		 DataInValidImplement datainvalid = new DataInValidImplement();
	        if(datainvalid.inValidAuthorException(_tacgia)){
	         this._tacgia = _tacgia;}
	}
	public int get_soluong() {
		return _soluong;
	}
	public void set_soluong(int _soluong) {
		this._soluong = _soluong;
	}
	public int get_id_sach() {
		return _id_sach;
	}
	public void set_id_sach(int _id_sach) {
		this._id_sach = _id_sach;
	}
	public int get_id_tacgia() {
		return _id_tacgia;
	}
	public void set_id_tacgia(int _id_tacgia) {
		this._id_tacgia = _id_tacgia;
	}
	public String get_tensach() {
		return _tensach;
	}
	public void set_tensach(String _tensach) {
		this._tensach = _tensach;
	}
	public String get_nhaxb() {
		return _nhaxb;
	}
	public void set_nhaxb(String _nhaxb) {
		this._nhaxb = _nhaxb;
	}
	public String get_namxb() {
		return _namxb;
	}
	public void set_namxb(String _namxb) {
		this._namxb = _namxb;
	}
	public String get_theloai() {
		return _theloai;
	}
	public void set_theloai(String _theloai) {
		this._theloai = _theloai;
	}

}


