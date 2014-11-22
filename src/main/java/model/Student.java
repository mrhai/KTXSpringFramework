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
    private String ngayo;
    private String ngaydi;
    private int leave;
    private double tienthieu;
    Database db = Database.create();
    public Student() {
    }

    
    public Student(int mssv, String tensv, String ngaysinh, String quequan, String lop, String khoa, Room room, int sdt,String ngaydi) {
        this.mssv = mssv;
        this.tensv = tensv;
        this.ngaysinh = ngaysinh;
        this.quequan = quequan;
        this.lop = lop;
        this.khoa = khoa;
        this.room = room;
        this.sdt = sdt;
        this.ngaydi = ngaydi;
    }

    public int add() throws SQLException{
        int leg = 0;
        SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
        ngayo = dmyFormat.format(new Date());

        
        String command = "INSERT INTO sinhvien VALUE("+mssv+",'"+tensv+"','"+ngaysinh+"','"+quequan+"','"+lop+"','"+khoa+"',"+sdt+",'"+ngayo+"','"+ngaydi+"')";
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
    
    public ArrayList<Student> getNoList(){
    	ArrayList<Student> list = new ArrayList<Student>();
    	String command = "select chitietphong.masv, chitietphong.makhu, chitietphong.maphong, sum(hoadon.tongtien) as tien from chitietphong INNER JOIN hoadon ON chitietphong.maphong = hoadon.maphong and chitietphong.makhu = hoadon.makhu WHERE hoadon.thanhtoan = 'chua' GROUP BY chitietphong.masv, chitietphong.makhu, chitietphong.maphong";
    	try {
			ResultSet rs = db.execute(command);
			
			Student student;
			Room room;
			RoomRegion roomRegion = null;
			while (rs.next()) {
				student = new Student();
				room = new Room();
				roomRegion = new RoomRegion();
				student.setMssv(rs.getInt("masv"));
				room.setMaphong(rs.getInt("maphong"));
				roomRegion.setMakhu(rs.getInt("makhu"));
				student.setTienthieu(rs.getInt("tien"));
				room.setRoomRegion(roomRegion);
				student.setRoom(room);
				list.add(student);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return list;
    }
    
    public int checkBill(){
    	int leg = 0;
    	String command = "select masv from chitietphong INNER JOIN hoadon ON chitietphong.maphong = hoadon.maphong and chitietphong.makhu = hoadon.makhu WHERE chitietphong.masv = "+mssv+" and hoadon.thanhtoan = 'chua'";
    	try {
			ResultSet rs = db.execute(command);
			while (rs.next()) {
				leg++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
    		Date now = new Date();
    		Student student;
			ResultSet rs = db.execute(command);
			while (rs.next()) {
				 leave = 0;
	                Date date = rs.getDate("ngaydi");
	                if (date.before(now) == true) {
	                    leave = 1;
	                }
				student = new Student();
				student.setMssv(rs.getInt("masv"));
				student.setTensv(rs.getString("tensv"));
				student.setNgaysinh(rs.getString("ngaysinh"));
				student.setQuequan(rs.getString("quequan"));
				student.setLop(rs.getString("lop"));
				student.setKhoa(rs.getString("khoa"));
				student.setSdt(rs.getInt("sdt"));
				student.setNgayo(rs.getString("ngayo"));
				student.setNgaydi(rs.getString("ngaydi"));
				student.setLeave(leave);
				list.add(student);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return list;
    }
    
    public int klkt(String mode, String content){

    	int id = 0;
    	int leg = 0;
    	String command = "SELECT MAX(id) as max FROM klkt";
    	try {
			ResultSet rs = db.execute(command);
			while (rs.next()) {
				id = rs.getInt("max")+1;	
			}
			command = "insert INTO klkt VALUE("+id+",'"+mode+"','"+content+"',"+mssv+")";
			leg = db.update(command);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return leg;
    }
    
    public ArrayList<KTKL> getKTKL(){
    	ArrayList<KTKL> list = new ArrayList<KTKL>();
    	String command;
    	if(mssv ==0){
    		command = "select * from klkt";
    	}else{
    		command = "select * from klkt where mssv = "+mssv;
    	}
    	
    	try {
			ResultSet rs = db.execute(command);
			while (rs.next()) {
				list.add(new KTKL(rs.getInt("id"), rs.getString("loai"), rs.getString("noidung"), rs.getInt("mssv")));
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


	public String getNgayo() {
		return ngayo;
	}


	public void setNgayo(String ngayo) {
		this.ngayo = ngayo;
	}


	public String getNgaydi() {
		return ngaydi;
	}


	public void setNgaydi(String ngaydi) {
		this.ngaydi = ngaydi;
	}


	public int getLeave() {
		return leave;
	}


	public void setLeave(int leave) {
		this.leave = leave;
	}


	public double getTienthieu() {
		return tienthieu;
	}


	public void setTienthieu(double tienthieu) {
		this.tienthieu = tienthieu;
	}
    
    
}
