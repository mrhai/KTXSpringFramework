/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

/**
 *
 * @author MrHai
 */
public class Bill {
    
    private int mahoadon;
    private double giadien;
    private int sodien;
    private double gianuoc;
    private int sonuoc;
    private Room room;
    private int tienphong;
    private String thoigian;
    private double tongtien;
    Database db = Database.create();
    public static final int GIADIEN = 2500;
    public static final int GIANUOC = 4000;
    
    
    public Bill() {
		
	}

	public Bill(int mahoadon, double giadien, int sodien, double gianuoc, int sonuoc, Room room, int tienphong, String thoigian, double tongtien) {
        this.mahoadon = mahoadon;
        this.giadien = giadien;
        this.sodien = sodien;
        this.gianuoc = gianuoc;
        this.sonuoc = sonuoc;
        this.room = room;
        this.tienphong = tienphong;
        this.thoigian = thoigian;
        this.tongtien = tongtien;
    }

	public int createBill(){
		int leg = 0;
		String command = "insert into hoadon value("+getID()+","+GIADIEN+","+sodien+","+GIANUOC+","+sonuoc+","+room.getMaphong()+","+room.getRoomRegion().getMakhu()+",'"+thoigian+"',"+tongtien()+",'chua')";
		System.out.println(command);
		try {
			leg = db.update(command);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return leg;
	}
	
	public int getID(){
		int id = 0;
		String command = "select max(mahoadon) as max from hoadon ";
		ResultSet rs;
		try {
			rs = db.execute(command);
			while (rs.next()) {
				id = rs.getInt("max");
			}
			return id+1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	public boolean outBill(){
		int i = 0;
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		String ngaytao = format.format(date);
		String command = "select * from hoadon where maphong="+room.getMaphong()+" and makhu = "+room.getRoomRegion().getMakhu()+" and MONTH(thoigian) = "+thoigian;
		
		try {
			ResultSet rs = db.execute(command);
			File dir = new File("C:/bill");
			dir.mkdirs();
			File file = new File("C:/bill/"+thoigian+room.getMaphong()+room.getRoomRegion().getMakhu()+".txt");
			PrintWriter pw = new PrintWriter(file,"UTF-8");
			while (rs.next()) {
				i++;
				pw.println("Hóa đơn tháng "+thoigian);
				pw.println("--");
				pw.println("Ngày tạo "+ngaytao);
				pw.println("Tổng số điện "+rs.getInt("sodien")+" kw");
				pw.println("Giá điện "+rs.getInt("giadien")+" VND");
				pw.println("Tổng số nước "+rs.getInt("sonuoc")+" khối");
				pw.println("Giá nước "+rs.getInt("gianuoc")+" VND");
				pw.println("--");
				pw.println("Tổng tiền "+rs.getInt("tongtien")+" VND");
				pw.flush();
			}
			if(i == 0){
				pw.println("Tháng này hiện chưa tạo phiếu tính tiền!");
				pw.flush();
			}else{
				command = "UPDATE hoadon SET thanhtoan = 'x' WHERE maphong ="+room.getMaphong()+" and makhu = "+room.getRoomRegion().getMakhu();
				db.update(command);
			}
			Desktop.getDesktop().open(new File("C:/bill/"+thoigian+room.getMaphong()+room.getRoomRegion().getMakhu()+".txt"));
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public int tongtien(){
		return sodien*GIADIEN + sonuoc+GIANUOC;
	}
    public int getMahoadon() {
        return mahoadon;
    }

    public void setMahoadon(int mahoadon) {
        this.mahoadon = mahoadon;
    }

    public double getGiadien() {
        return giadien;
    }

    public void setGiadien(double giadien) {
        this.giadien = giadien;
    }

    public int getSodien() {
        return sodien;
    }

    public void setSodien(int sodien) {
        this.sodien = sodien;
    }

    public double getGianuoc() {
        return gianuoc;
    }

    public void setGianuoc(double gianuoc) {
        this.gianuoc = gianuoc;
    }

    public int getSonuoc() {
        return sonuoc;
    }

    public void setSonuoc(int sonuoc) {
        this.sonuoc = sonuoc;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getTienphong() {
        return tienphong;
    }

    public void setTienphong(int tienphong) {
        this.tienphong = tienphong;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public double getTongtien() {
        return tongtien;
    }

    public void setTongtien(double tongtien) {
        this.tongtien = tongtien;
    }
    
    
}
