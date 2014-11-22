/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
    public Device(){
        
    }
    public Device(int maso, String tenvattu, int soluong,double gia) {
        this.maso = maso;
        this.tenvattu = tenvattu;
        this.soluong = soluong;
        this.gia = gia;
    }
    
    public int nhapkho(int soluong){
    	int update = 0;
    	String command = "UPDATE vattu SET soluong  = soluong + "+soluong+" WHERE maso = "+maso;
    	 try {
			update = db.update(command);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return update;
    }
    
    
    public ArrayList<Device> getDevice(){
    	ArrayList<Device> list = new ArrayList<Device>();
    	String command = "select * from vattu";
    	try {
			ResultSet rs = db.execute(command);
			Device device;
			while (rs.next()) {
				device = new Device(rs.getInt("maso"), rs.getString("tenvattu"), rs.getInt("soluong"),0.0);
				list.add(device);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return list;
    }

    public int repair(int maphong, int makhu){
    	int leg = 0;
    	  SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
          String date = dmyFormat.format(new Date());
    	String command = "INSERT INTO quanlysuachua VALUE("+getMSC()+",'"+date+"',"+gia+")";
    	String command2 = "INSERT INTO chitietsuachua VALUE("+maphong+","+makhu+","+maso+","+getMSC()+")";
    	
    	try {
			leg = db.update(command);
			leg += db.update(command2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return leg;
    }
    
    public int getMSC(){
    	String command = "SELECT MAX(maso) as max FROM quanlysuachua";
    	try {
			ResultSet rs = db.execute(command);
			while (rs.next()) {
				return rs.getInt("max") +1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return 0;
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
