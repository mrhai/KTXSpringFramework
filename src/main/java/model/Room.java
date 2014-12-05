/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import jxl.Workbook;
import jxl.write.WritableWorkbook;

/**
 *
 * @author MrHai
 */
public class Room {

    private int maphong;
    private RoomRegion roomRegion;
    private int svhientai;
    private int succhua;
    private Device device;
    private Employee employee;
    Database db = Database.create();

    public Room(int maphong, RoomRegion roomRegion, int svhientai, int succhua, Device device, Employee employee) {
        this.maphong = maphong;
        this.roomRegion = roomRegion;
        this.svhientai = svhientai;
        this.succhua = succhua;
        this.device = device;
        this.employee = employee;
    }

    public Room() {
    }

    public ArrayList<String> roomList() {
        ArrayList<String> list = new ArrayList<String>();
        String command = "select maphong from phong GROUP BY maphong";
        try {
            ResultSet rs = db.execute(command);
            while (rs.next()) {
                list.add(rs.getString("maphong"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean checkRoom() {
        String command = "select * from phong WHERE maphong = " + maphong + " and makhu = " + roomRegion.getMakhu() + " HAVING svhientai < succhua ";
        System.out.println(command);
        try {
            ResultSet rs = db.execute(command);
            while (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void studentsIncreased() {
        try {
            String present = "select svhientai from phong WHERE maphong = " + maphong + " and makhu=" + roomRegion.getMakhu();
            ResultSet rs = db.execute(present);
            rs.next();
            int presentInt = rs.getInt("svhientai");
            String update = "UPDATE phong SET svhientai = " + (presentInt + 1) + " WHERE makhu = " + roomRegion.getMakhu() + " and maphong=" + maphong;
            db.update(update);
        } catch (Exception e) {
        }

    }

    public ArrayList<Device> getbaohong(){
    	device = new Device();
    	return device.getbaohong(maphong, roomRegion.getMakhu());
    }
    
    public void baohong(){
    	device.baohong(maphong, roomRegion.getMakhu());
    }
    
    public void studentsDropped(int masv) {
        try {
            String chitiet = "select * from chitietphong where masv = " + masv;
            ResultSet rs = db.execute(chitiet);
            int phong = 0;
            int makhu = 0;
            while (rs.next()) {
                phong = rs.getInt("maphong");
                makhu = rs.getInt("makhu");
            }
            String present = "select svhientai from phong WHERE maphong = " + phong + " and makhu=" + makhu;
            ResultSet rspresent = db.execute(present);
            int presentInt = 0;
            while (rspresent.next()) {
                presentInt = rspresent.getInt("svhientai");
            }
            String update = "UPDATE phong SET svhientai = " + (presentInt - 1) + " WHERE makhu = " + makhu + " and maphong=" + phong;
            db.update(update);
        } catch (Exception e) {
        }

    }

    public int changeRoomManager(int mnv){
    	int leg = 0;
    	String command = "UPDATE phong SET manhanvien = "+mnv+" WHERE maphong = "+maphong+" and makhu = "+roomRegion.getMakhu();
    	System.out.println(command);
    	try {
			leg = db.update(command);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return leg;
    }
    
    public ArrayList<Room> listManager(){
    	ArrayList<Room> list = new ArrayList<Room>();
    	String command = "select nhanvien.manhanvien, nhanvien.tennhanvien, phong.maphong,phong.makhu from nhanvien INNER JOIN phong on nhanvien.manhanvien = phong.manhanvien ORDER BY nhanvien.manhanvien ASC";
    	try {
			ResultSet rs = db.execute(command);
			Employee employee;
			Room room;
			RoomRegion region;
			while (rs.next()) {
				employee = new Employee();
				employee.setManv(rs.getInt("manhanvien"));
				employee.setTennv(rs.getString("tennhanvien"));
				room = new Room();
				room.setMaphong(rs.getInt("maphong"));
				region = new RoomRegion();
				region.setMakhu(rs.getInt("makhu"));
				room.setRoomRegion(region);
				room.setEmployee(employee);
				list.add(room);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return list;
    }
    
    public int chuyenVT(){
    	int leg = 0;
    	String command = "INSERT INTO chitietvattupphong VALUE("+maphong+","+roomRegion.getMakhu()+","+device.getMaso()+","+device.getSoluong()+",0)";
    	System.out.println(command);
    	try {
			leg = db.update(command);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			if(leg == 0){
				command = "UPDATE chitietvattupphong SET soluong= (soluong + "+device.getSoluong()+") WHERE maphong="+maphong+" AND makhu="+roomRegion.getMakhu()+" AND mavattu="+device.getMaso()+"";
				System.out.println(command);
				try {
					leg = db.update(command);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
    	return leg;
    }
    
    
    public ArrayList<Room> getDSRepair(){
    	
    	String command = "select maphong, makhu,mavattu,tenvattu,ngaysua,giasuachua from chitietsuachua INNER JOIN vattu ON chitietsuachua.mavattu = vattu.maso INNER JOIN quanlysuachua ON chitietsuachua.masuachua = quanlysuachua.maso where maphong = "+maphong+" and makhu = "+roomRegion.getMakhu();
    	System.out.println(command);
    	ArrayList<Room> list = new ArrayList<Room>();
    	try {
			ResultSet rs = db.execute(command);
			RoomRegion region;
			Device device;
			Room room;
			while (rs.next()) {
				region = new RoomRegion();
				region.setMakhu(rs.getInt("makhu"));
				device = new Device();
				device.setMaso(rs.getInt("mavattu"));
				device.setTenvattu(rs.getString("tenvattu"));
				device.setGia(rs.getDouble("giasuachua"));
				device.setNgaysua(rs.getString("ngaysua"));
				room = new Room();
				room.setDevice(device);
				room.setRoomRegion(region);
				room.setMaphong(rs.getInt("maphong"));
				list.add(room);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return list;
    }
    
    public void in(String type){
    	File file = null;
    	try {
    	File dir = new File("C:/print");
		dir.mkdirs();
		file = new File("C:/print/"+type+".xls");
		WritableWorkbook workbook = Workbook.createWorkbook(file);
		device.printDeviceList(workbook, maphong, roomRegion.getMakhu());
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				Desktop.getDesktop().open(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
    
    
    public int repairDevice(){
    	return device.repair(maphong, roomRegion.getMakhu());
    }
    
    public int getMaphong() {
        return maphong;
    }

    public void setMaphong(int maphong) {
        this.maphong = maphong;
    }

    public RoomRegion getRoomRegion() {
        return roomRegion;
    }

    public void setRoomRegion(RoomRegion roomRegion) {
        this.roomRegion = roomRegion;
    }

    public int getSvhientai() {
        return svhientai;
    }

    public void setSvhientai(int svhientai) {
        this.svhientai = svhientai;
    }

    public int getSucchua() {
        return succhua;
    }

    public void setSucchua(int succhua) {
        this.succhua = succhua;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
