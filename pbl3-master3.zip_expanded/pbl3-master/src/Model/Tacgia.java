package Model;

import java.util.Date;

public class Tacgia {
	private int _id;
	private String _hoten;
	private Date _ns;
	private String _theloai;
	
	public Tacgia() {
	}

	public Tacgia(int _id, String _hoten, Date _ns, String _theloai) {
		this._id = _id;
		this._hoten = _hoten;
		this._ns = _ns;
		this._theloai = _theloai;	
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

	public String get_theloai() {
		return _theloai;
	}

	public void set_theloai(String _theloai) {
		this._theloai = _theloai;
	}
	
	

}