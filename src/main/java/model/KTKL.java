package model;

public class KTKL {

	private int id;
	private String mode;
	private String noidung;
	private int mssv;
	public KTKL(int id, String mode, String noidung, int mssv) {
		super();
		this.id = id;
		this.mode = mode;
		this.noidung = noidung;
		this.mssv = mssv;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getNoidung() {
		return noidung;
	}
	public void setNoidung(String noidung) {
		this.noidung = noidung;
	}
	public int getMssv() {
		return mssv;
	}
	public void setMssv(int mssv) {
		this.mssv = mssv;
	}
	
	
}
