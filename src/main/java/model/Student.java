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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

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
    private String avatar;
    Database db = Database.create();
    public Student() {
    }

    
    public Student(int mssv, String tensv, String ngaysinh, String quequan, String lop, String khoa, Room room, int sdt,String ngaydi,String avatar) {
        this.mssv = mssv;
        this.tensv = tensv;
        this.ngaysinh = ngaysinh;
        this.quequan = quequan;
        this.lop = lop;
        this.khoa = khoa;
        this.room = room;
        this.sdt = sdt;
        this.ngaydi = ngaydi;
        this.avatar = avatar;
    }

    public int add() throws SQLException{
        int leg = 0;
        SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
        ngayo = dmyFormat.format(new Date());

        
        String command = "INSERT INTO sinhvien VALUE("+mssv+",'"+tensv+"','"+ngaysinh+"','"+quequan+"','"+lop+"','"+khoa+"',"+sdt+",'"+ngayo+"','"+ngaydi+"','"+avatar+"')";
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
        String command5 = "delete from klkt where mssv = "+mssv;
        String command4 = "delete from sinhvien where masv = "+mssv;
        
        room.studentsDropped(mssv);
        try{
        leg += db.update(command);
        leg += db.update(command2);
        leg += db.update(command3);
        leg += db.update(command5);
        leg += db.update(command4);
       
        System.out.println(leg);
        }catch(SQLException e){
        	e.printStackTrace();
        }
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
    
    public ArrayList<Student> getStudentRoom(){
    	ArrayList<Student> list = new ArrayList<>();
    	RoomRegion region;
    	
    	String command = "SELECT maphong, makhu from chitietphong where masv = "+mssv;
    	ResultSet rs;
		try {
			rs = db.execute(command);
			while (rs.next()) {
				region = new RoomRegion();
				region.setMakhu(rs.getInt("makhu"));
				room = new Room();
				room.setMaphong(rs.getInt("maphong"));
				room.setRoomRegion(region);
			}
			String get = "SELECT sinhvien.masv, sinhvien.tensv from chitietphong INNER JOIN sinhvien on chitietphong.masv = sinhvien.masv where maphong = "+room.getMaphong()+" and makhu = "+room.getRoomRegion().getMakhu();
			System.out.println(get);
			rs = db.execute(get);
			Student student;
			while (rs.next()) {
				student = new Student();
				student.setMssv(rs.getInt("masv"));
				student.setTensv(rs.getString("tensv"));
				list.add(student);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return list;
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
				student.setAvatar(rs.getString("avatar"));
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
    
    public void in(String type){
    	File file = null;
    	try {
    	File dir = new File("C:/print");
		dir.mkdirs();
		file = new File("C:/print/"+type+".xls");
		WritableWorkbook workbook = Workbook.createWorkbook(file);
    	if(type.equals("dssv")){
				printSVList(workbook);
				
    	}else if(type.equals("dsnhd")){
    		printNHD(workbook);
    	}else if(type.equals("ktkl")){
    		printKTKL(workbook);
    	}
    	
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
    
    public void printSVList(WritableWorkbook workbook){
    	try {
    	WritableSheet sheet = workbook.createSheet("DS SINH VIÊN", 0);
    	sheet.addCell(new Label(0, 0, "DANH SÁCH SINH VIÊN"));
    	sheet.addCell(new Label(0, 2, "MSSV"));
    	sheet.addCell(new Label(1, 2, "Tên SV"));
    	sheet.addCell(new Label(2, 2, "Ngày sinh"));
    	sheet.addCell(new Label(3, 2, "Quê quán"));
    	sheet.addCell(new Label(4, 2, "Lớp"));
    	sheet.addCell(new Label(5, 2, "Khoa"));
    	sheet.addCell(new Label(6, 2, "SDT"));
    	sheet.addCell(new Label(7, 2, "Ngày ở"));
    	sheet.addCell(new Label(8, 2, "Ngày đi"));
    	int col = 0;
    	int row = 4;
    	String command = "select * from sinhvien";
    	
			ResultSet rs = db.execute(command);
			while (rs.next()) {
				col = 0;
				sheet.addCell(new Number(col++,row, rs.getInt("masv")));
				sheet.addCell(new Label(col++, row,rs.getString("tensv")));
				sheet.addCell(new Label(col++,row,rs.getString("ngaysinh")));
				sheet.addCell(new Label(col++,row,rs.getString("quequan")));
				sheet.addCell(new Label(col++,row,rs.getString("lop")));
				sheet.addCell(new Label(col++,row,rs.getString("khoa")));
				sheet.addCell(new Number(col++,row, rs.getInt("sdt")));
				sheet.addCell(new Label(col++,row,rs.getString("ngayo")));
				sheet.addCell(new Label(col++,row,rs.getString("ngaydi")));
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
    
    public void printNHD(WritableWorkbook workbook){
    	try {
    	WritableSheet sheet = workbook.createSheet("DS NỢ HÓA ĐƠN", 0);
    	sheet.addCell(new Label(0, 0, "DANH SÁCH SINH VIÊN NỢ HÓA ĐƠN"));
    	sheet.addCell(new Label(0, 2, "MSSV"));
    	sheet.addCell(new Label(1, 2, "Mã khu"));
    	sheet.addCell(new Label(2, 2, "Mã phòng"));
    	sheet.addCell(new Label(3, 2, "Tiền nợ"));
    	
    	int col = 0;
    	int row = 4;
    	String command = "select chitietphong.masv, chitietphong.makhu, chitietphong.maphong, sum(hoadon.tongtien) as tien from chitietphong INNER JOIN hoadon ON chitietphong.maphong = hoadon.maphong and chitietphong.makhu = hoadon.makhu WHERE hoadon.thanhtoan = 'chua' GROUP BY chitietphong.masv, chitietphong.makhu, chitietphong.maphong";
    	
			ResultSet rs = db.execute(command);
			while (rs.next()) {
				col = 0;
				sheet.addCell(new Number(col++,row, rs.getInt("masv")));
				sheet.addCell(new Number(col++,row, rs.getInt("makhu")));
				sheet.addCell(new Number(col++,row, rs.getInt("maphong")));
				sheet.addCell(new Number(col++,row, rs.getDouble("tien")));
				
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
    
    public void printKTKL(WritableWorkbook workbook){
    	try {
    	WritableSheet sheet = workbook.createSheet("DS KHEN THƯỞNG_KỶ LUẬT", 0);
    	sheet.addCell(new Label(0, 0, "DANH SÁCH SINH VIÊN ĐƯỢC KHEN THƯỞNG VÀ KỶ LUẬT"));
    	sheet.addCell(new Label(0, 2, "MSSV"));
    	sheet.addCell(new Label(1, 2, "Khen thưởng/ Kỷ luật"));
    	sheet.addCell(new Label(2, 2, "Nội dung"));
    	
    	int col = 0;
    	int row = 4;
    	String command = "SELECT * FROM klkt";
    	
			ResultSet rs = db.execute(command);
			while (rs.next()) {
				col = 0;
				sheet.addCell(new Number(col++,row, rs.getInt("mssv")));
				String loai = rs.getString("loai");
				if(loai.equals("kt")) sheet.addCell(new Label(col++,row, "Khen thưởng"));
				else sheet.addCell(new Label(col++,row,"Kỷ luật" ));
				sheet.addCell(new Label(col++,row, rs.getString("noidung")));
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


	public String getAvatar() {
		return avatar;
	}


	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
    
    
}
