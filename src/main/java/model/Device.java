/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author MrHai
 */
public class Device {
    
    private int maso;
    private String tenvattu;
    private int soluong;
    Database db = Database.create();
    public Device(){
        
    }
    public Device(int maso, String tenvattu, int soluong) {
        this.maso = maso;
        this.tenvattu = tenvattu;
        this.soluong = soluong;
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
				device = new Device(rs.getInt("maso"), rs.getString("tenvattu"), rs.getInt("soluong"));
				list.add(device);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return list;
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
    
    
}
