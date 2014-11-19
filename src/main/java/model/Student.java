/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MrHai
 */
public class Student {
    
    private int mssv;
    private String tensv;
    private String ngaysinh;
    private String quequan;
    private String lop;
    private String khoa;
    private Room room;
    private int sdt;
    Database db = Database.create();
    public Student() {
    }

    
    public Student(int mssv, String tensv, String ngaysinh, String quequan, String lop, String khoa, Room room, int sdt) {
        this.mssv = mssv;
        this.tensv = tensv;
        this.ngaysinh = ngaysinh;
        this.quequan = quequan;
        this.lop = lop;
        this.khoa = khoa;
        this.room = room;
        this.sdt = sdt;
    }

    public int add() throws SQLException{
        int leg = 0;
        String command = "INSERT INTO sinhvien VALUE("+mssv+",'"+tensv+"','"+ngaysinh+"','"+quequan+"','"+lop+"','"+khoa+"',"+sdt+")";
        String command2 = "INSERT INTO chitietphong value("+room.getMaphong()+","+room.getRoomRegion().getMakhu()+","+mssv+")";
        String command3 = "INSERT INTO taikhoansv value("+mssv+",'123456')";
        leg += db.update(command);
        leg += db.update(command2);
        leg += db.update(command3);
        room.studentsIncreased();
        return leg;
    }
    
    
    public int delete(){
        int leg = 0;
        String command = "delete from chitietphong where masv = "+mssv;
        String command2 = "delete from taikhoansv where mssv = "+mssv;
        String command3 = "delete from khach where masv = "+mssv;
        String command4 = "delete from sinhvien where masv = "+mssv;
        room.studentsDropped(mssv);
        try{
        leg += db.update(command);
        leg += db.update(command2);
        leg += db.update(command3);
        leg += db.update(command4);
        
        }catch(SQLException e){}
        return leg;
    }
    
    public ArrayList<String> getMSSV(){
        ArrayList<String> list = new ArrayList<String>();
        String command = "Select masv from sinhvien";
        try {
            ResultSet rs = db.execute(command);
            while(rs.next()){
                list.add(rs.getString("masv"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public int changeRoom(){
        int leg = 0;
        String command = "UPDATE chitietphong SET maphong = "+room.getMaphong()+" , makhu = "+room.getRoomRegion().getMakhu()+" WHERE masv = "+mssv;
        try {
            room.studentsDropped(mssv);
            room.studentsIncreased();
            leg += db.update(command);
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return leg;
    }
    
    public ArrayList<Student> getStudentList(String masv){
    	String command;
    	if(masv.equals("")){
    		command = "select * from sinhvien";
    	}else{
    		command = "select * from sinhvien where masv = "+masv;
    		System.out.println(command);
    	}
    	ArrayList<Student> list = new ArrayList<Student>();
    	
    	try {
    		Student student;
			ResultSet rs = db.execute(command);
			while (rs.next()) {
				student = new Student();
				student.setMssv(rs.getInt("masv"));
				student.setTensv(rs.getString("tensv"));
				student.setNgaysinh(rs.getString("ngaysinh"));
				student.setQuequan(rs.getString("quequan"));
				student.setLop(rs.getString("lop"));
				student.setKhoa(rs.getString("khoa"));
				student.setSdt(rs.getInt("sdt"));
				list.add(student);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return list;
    }
    
    public int getMssv() {
        return mssv;
    }

    public void setMssv(int mssv) {
        this.mssv = mssv;
    }

    public String getTensv() {
        return tensv;
    }

    public void setTensv(String tensv) {
        this.tensv = tensv;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getQuequan() {
        return quequan;
    }

    public void setQuequan(String quequan) {
        this.quequan = quequan;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public String getKhoa() {
        return khoa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getSdt() {
        return sdt;
    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }
    
    
}
