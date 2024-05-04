package Model;

import java.util.Date;

public class Thuthu {
	private int _id;
	private String _hoten;
	private Date _ns;
	private String _diachi;
	private String _cccd;
	private String _sdt;
	private String _email;
	private String _username;
	private String _password;
	
	public Thuthu() {
		
	}
	public Thuthu(int _id, String _hoten, Date _ns, String _diachi, String _cccd, String _sdt, String _email,
			String _username, String _password) {
		super();
		this._id = _id;
		this._hoten = _hoten;
		this._ns = _ns;
		this._diachi = _diachi;
		this._cccd = _cccd;
		this._sdt = _sdt;
		this._email = _email;
		this._username = _username;
		this._password = _password;
	}
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String get_hoten() {
		return _hoten;
	}
	public void set_hoten(String _hoten) {
		this._hoten = _hoten;
	}
	public Date get_ns() {
		return _ns;
	}
	public void set_ns(Date _ns) {
		this._ns = _ns;
	}
	public String get_diachi() {
		return _diachi;
	}
	public void set_diachi(String _diachi) {
		this._diachi = _diachi;
	}
	public String get_cccd() {
		return _cccd;
	}
	public void set_cccd(String _cccd) {
		this._cccd = _cccd;
	}
	public String get_sdt() {
		return _sdt;
	}
	public void set_sdt(String _sdt) {
		this._sdt = _sdt;
	}
	public String get_email() {
		return _email;
	}
	public void set_email(String _email) {
		this._email = _email;
	}
	public String get_username() {
		return _username;
	}
	public void set_username(String _username) {
		this._username = _username;
	}
	public String get_password() {
		return _password;
	}
	public void set_password(String _password) {
		this._password = _password;
	}

}
