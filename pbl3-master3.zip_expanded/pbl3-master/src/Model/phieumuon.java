package Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class phieumuon {
	private int _id_phieu;
	private int _id_docgia;
	private int _id_sach;
	private int _soluong;
	private Date _ngaymuon;
	private Date _ngaytra;
	private int _phimuon;
	
	private String giveBookBack = "Chưa Trả";
	public phieumuon() {
		
	}
	
	public phieumuon(int _id_phieu, int _id_docgia, int _id_sach, int _soluong, Date _ngaymuon,
	        Date _ngaytra, int _phimuon) {
	    this(_id_phieu, _id_docgia, _id_sach, _soluong, _ngaymuon, _ngaytra, _phimuon, "Chưa Trả");
	}
	
	public phieumuon(int _id_phieu, int _id_docgia, int _id_sach, int _soluong, Date _ngaymuon,
			Date _ngaytra, int _phimuon, String giveBookBack) {
		super();
		this._id_phieu = _id_phieu;
		this._id_docgia = _id_docgia;
		this._id_sach = _id_sach;
		this._soluong = _soluong;
		this._ngaymuon = _ngaymuon;
		this._ngaytra = _ngaytra;
		this._phimuon = _phimuon;
		this.giveBookBack =  giveBookBack;
	}
	public phieumuon(int _id_docgia, int _id_sach) {
        this._id_docgia = _id_docgia;
        this._id_sach = _id_sach;
    }
	
	
	public int get_id_phieu() {
		return _id_phieu;
	}
	
	public void set_id_phieu(int _id_phieu) {
		this._id_phieu = _id_phieu;
	}
	public int get_id_docgia() {
		return _id_docgia;
	}
	public void set_id_docgia(int _id_docgia) {
		this._id_docgia = _id_docgia;
	}
	public int get_id_sach() {
		return _id_sach;
	}
	public void set_id_sach(int _id_sach) {
		this._id_sach = _id_sach;
	}

	public int get_soluong() {
		return _soluong;
	}
	public void set_soluong(int _soluong) {
		this._soluong = _soluong;
	}
	public Date get_ngaymuon() {
		return _ngaymuon;
	}
	public void set_ngaymuon(String borrowDate) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		try
		{
			this._ngaymuon = df.parse(borrowDate);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public Date get_ngaytra() {
		return _ngaytra;
	}
	public void set_ngayHenTra(Date appointDate) {
//		try {
//			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//			//this._ngaytra = df.parse(appointDate);
//			
//		}
//		catch(ParseException e)
//		{
//			e.printStackTrace();
//		}
		this._ngaytra = appointDate;
	}
	public int get_phimuon() {
		return _phimuon;
	}
	public void set_phimuon(int _phimuon) {
		this._phimuon = _phimuon;
	}
	
	public String getGiveBookBack() {
		return giveBookBack;
	}

	public void setGiveBookBack(String giveBookBack) {
		this.giveBookBack = giveBookBack;
	}
	
	
}
