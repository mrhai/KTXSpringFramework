/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

/**
 *
 * @author MrHai
 */
public class Device {

	private int maso;
	private String tenvattu;
	private int soluong;
	private double gia;
	private String ngaysua;
	Database db = Database.create();

	public Device() {

	}

	public Device(int maso, String tenvattu, int soluong, double gia) {
		this.maso = maso;
		this.tenvattu = tenvattu;
		this.soluong = soluong;
		this.gia = gia;
	}

	public int nhapkho(int soluong) {
		int update = 0;
		String command = "UPDATE vattu SET soluong  = soluong + " + soluong
				+ " WHERE maso = " + maso;
		try {
			update = db.update(command);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return update;
	}

	public ArrayList<Device> getDevice() {
		ArrayList<Device> list = new ArrayList<Device>();
		String command = "select * from vattu";
		try {
			ResultSet rs = db.execute(command);
			Device device;
			while (rs.next()) {
				device = new Device(rs.getInt("maso"),
						rs.getString("tenvattu"), rs.getInt("soluong"), 0.0);
				list.add(device);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public int repair(int maphong, int makhu) {
		int leg = 0;
		try {
			if(checkHong(maphong, makhu) == false){
				return 0;
			}else{
				SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
				String date = dmyFormat.format(new Date());
				String command = "INSERT INTO quanlysuachua VALUE(" + getMSC()
						+ ",'" + date + "'," + gia + ")";
				String command2 = "INSERT INTO chitietsuachua VALUE(" + maphong
						+ "," + makhu + "," + maso + "," + getMSC() + ")";
				String repair = "UPDATE chitietvattupphong SET hong=0 WHERE maphong="
						+ maphong + " AND makhu=" + makhu + " AND mavattu=" + maso;
				leg = db.update(command);
				leg += db.update(command2);
				leg +=db.update(repair);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return leg;
	}

	private boolean checkHong(int maphong, int makhu) throws SQLException{
		String command = "select * from chitietvattupphong WHERE maphong="
					+ maphong
					+ " AND makhu="
					+ makhu
					+ " AND mavattu="
					+ maso+" and hong = 1";
		ResultSet rs = db.execute(command);
		System.out.println(command);
		while (rs.next()) {
			return true;
		}
		return false;
	}

	public int getMSC() {
		String command = "SELECT MAX(maso) as max FROM quanlysuachua";
		try {
			ResultSet rs = db.execute(command);
			while (rs.next()) {
				return rs.getInt("max") + 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public ArrayList<Device> getbaohong(int maphong, int makhu) {
		ArrayList<Device> list = new ArrayList<>();
		String command = "select vattu.maso, vattu.tenvattu from chitietvattupphong INNER JOIN vattu on chitietvattupphong.mavattu = vattu.maso where maphong = "
				+ maphong
				+ " and makhu = "
				+ makhu
				+ " and chitietvattupphong.hong = 0";
		ResultSet rs;
		try {
			rs = db.execute(command);
			while (rs.next()) {
				list.add(new Device(rs.getInt("maso"),
						rs.getString("tenvattu"), 0, 0.0));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}

	public void baohong(int maphong, int makhu) {
		String command = "UPDATE chitietvattupphong SET hong=1 WHERE maphong="
				+ maphong + " AND makhu=" + makhu + " AND mavattu=" + maso;
		try {
			db.update(command);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void printDeviceList(WritableWorkbook workbook, int maphong, int makhu){
    	try {
    	WritableSheet sheet = workbook.createSheet("DS VAT TU", 0);
    	sheet.addCell(new Label(0, 0, "DANH SÁCH VẬT TƯ PHÒNG "+maphong+" KHU "+makhu));
    	sheet.addCell(new Label(0, 2, "Mã phòng"));
    	sheet.addCell(new Label(1, 2, "Mã khu"));
    	sheet.addCell(new Label(2, 2, "Mã vật tư"));
    	sheet.addCell(new Label(3, 2, "Tên vật tư"));
    	sheet.addCell(new Label(4, 2, "Số lượng"));
    	
    	int col = 0;
    	int row = 4;
    	String command = "select maphong, makhu, mavattu, tenvattu, chitietvattupphong.soluong from chitietvattupphong INNER JOIN vattu on chitietvattupphong.mavattu = vattu.maso where maphong = "+maphong+" and makhu ="+makhu;
    	
			ResultSet rs = db.execute(command);
			while (rs.next()) {
				col = 0;
				sheet.addCell(new Number(col++,row, rs.getInt("maphong")));
				sheet.addCell(new Number(col++,row, rs.getInt("makhu")));
				sheet.addCell(new Number(col++,row, rs.getInt("mavattu")));
				sheet.addCell(new Label(col++, row,rs.getString("tenvattu")));
				sheet.addCell(new Number(col++,row, rs.getInt("soluong")));
				row++;
			}
			workbook.write();
			workbook.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
	
	public int getMaso() {
		return maso;
	}

	public void setMaso(int maso) {
		this.maso = maso;
	}

	public String getTenvattu() {
		return tenvattu;
	}

	public void setTenvattu(String tenvattu) {
		this.tenvattu = tenvattu;
	}

	public int getSoluong() {
		return soluong;
	}

	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}

	public double getGia() {
		return gia;
	}

	public void setGia(double gia) {
		this.gia = gia;
	}

	public String getNgaysua() {
		return ngaysua;
	}

	public void setNgaysua(String ngaysua) {
		this.ngaysua = ngaysua;
	}

}
