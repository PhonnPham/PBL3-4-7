package Model;

import java.util.Date;

public class Hoadon {
	private int _id_hoadon;
	private int _id_tt;
	private int _id_phieu;
	private Date _ngayttoan;
	private int _tongtien;
	
	public Hoadon() {
	}

	public Hoadon(int _id_hoadon,int _id_tt,int _id_phieu, Date _ngayttoan, int _tongtien) {
		this._id_hoadon = _id_hoadon;
		this._id_tt = _id_tt;
		this._id_phieu = _id_phieu;
		this._ngayttoan = _ngayttoan;
		this._tongtien = _tongtien;	
	}
	public int get_id_hoadon() {
		return _id_hoadon;
	}
	public void set_id_hoadon(int _id_hoadon) {
		this._id_hoadon = _id_hoadon;
	}
	public int get_id_tt() {
		return _id_tt;
	}
	public void set_id_tt(int _id_tt) {
		this._id_tt = _id_tt;
	}
	public int get_id_phieu() {
		return _id_phieu;
	}
	public void set_id_phieu(int _id_phieu) {
		this._id_phieu = _id_phieu;
	}
	public Date get_ngayttoan() {
		return _ngayttoan;
	}
	public void set_ngayttoan(Date _ngayttoan) {
		this._ngayttoan = _ngayttoan;
	}
	public int get_tongtien() {
		return _tongtien;
	}
	public void set_tongtien(int _tongtien) {
		this._tongtien = _tongtien;
	}

}
