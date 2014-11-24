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
public class Employee {

	private int manv;
	private String tennv;
	private String ngaysinh;
	private String diachi;
	private int chucvu;
	private double luong;
	Database db = Database.create();

	public Employee() {
	}

	public Employee(int manv, String tennv, String ngaysinh, String diachi,
			int chucvu, double luong) {
		this.manv = manv;
		this.tennv = tennv;
		this.ngaysinh = ngaysinh;
		this.diachi = diachi;
		this.chucvu = chucvu;
		this.luong = luong;
	}

	public int add() {
		int leg = 0;
		String command = "INSERT INTO nhanvien VALUE(" + manv + ",'" + tennv
				+ "','" + ngaysinh + "','" + diachi + "',0," + chucvu + ")";
		System.out.println(command);
		String command2 = "";
		if (chucvu == 2 || chucvu == 3) {
			command2 = "INSERT INTO admin VALUES(" + manv + ",'123456')";
		} else {
			command2 = "INSERT INTO taikhoannhanvien VALUES(" + manv
					+ ",'123456')";
		}

		try {
			leg += db.update(command);
			leg += db.update(command2);
		} catch (SQLException ex) {
			Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null,
					ex);
		}
		return leg;
	}

	public int delete() {
		int leg = 0;
		String tkad = "delete from admin where msnv = " + manv;
		String tknv = "delete from taikhoannhanvien where manv = " + manv;
		String nv = "delete from nhanvien where manhanvien = " + manv;
		try {
			leg += db.update(tkad);
			leg += db.update(tknv);
			leg += db.update(nv);
		} catch (Exception e) {
		}
		return leg;
	}

	public int changeSalary() {
		int leg = 0;
		String command = "UPDATE nhanvien SET luong='" + luong
				+ "' WHERE manhanvien=" + manv;
		try {
			leg = db.update(command);
		} catch (SQLException ex) {
			Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null,
					ex);
		}
		return leg;
	}

	public int autoMNV() {
		int mnv = 0;
		String command = "select max(manhanvien) as mnv from nhanvien";
		try {
			ResultSet rs = db.execute(command);
			while (rs.next()) {
				mnv = rs.getInt("mnv") + 1;
			}
		} catch (SQLException ex) {
			Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null,
					ex);
		}
		return mnv;
	}

	public ArrayList<Employee> getMNV() {
		ArrayList<Employee> list = new ArrayList<Employee>();
		String command = "select * from nhanvien where manhanvien > 123";
		try {
			ResultSet rs = db.execute(command);
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setManv(rs.getInt("manhanvien"));
				employee.setTennv(rs.getString("tennhanvien"));
				list.add(employee);
			}
		} catch (Exception e) {
		}
		return list;
	}

	public ArrayList<Position> getPosition() {
		ArrayList<Position> list = new ArrayList<Position>();
		String command = "select * from chucvu";
		try {
			ResultSet rs = db.execute(command);
			while (rs.next()) {
				list.add(new Position(rs.getInt("machucvu"), rs
						.getString("tenchucvu")));
			}
		} catch (Exception e) {
		}
		return list;
	}

	public void in(String type) {
		File file = null;
		try {
			File dir = new File("C:/print");
			dir.mkdirs();
			file = new File("C:/print/" + type + ".xls");
			WritableWorkbook workbook = Workbook.createWorkbook(file);
			if (type.equals("ttnv")) {
				printNVList(workbook);

			} else if (type.equals("nvql")) {
				printNVQL(workbook);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				Desktop.getDesktop().open(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void printNVList(WritableWorkbook workbook) {
		try {
			WritableSheet sheet = workbook.createSheet("DS NHÂN VIÊN", 0);
			sheet.addCell(new Label(0, 0, "DANH SÁCH NHÂN VIÊN"));
			sheet.addCell(new Label(0, 2, "MSNV"));
			sheet.addCell(new Label(1, 2, "Tên NV"));
			sheet.addCell(new Label(2, 2, "Ngày sinh"));
			sheet.addCell(new Label(3, 2, "Địa chỉ"));
			sheet.addCell(new Label(4, 2, "Chức vụ"));

			int col = 0;
			int row = 4;
			String command = "SELECT manhanvien,tennhanvien,ngaysinh,diachi,luong,tenchucvu FROM nhanvien INNER JOIN chucvu ON nhanvien.chucvu = chucvu.machucvu";

			ResultSet rs = db.execute(command);
			while (rs.next()) {
				col = 0;
				sheet.addCell(new Number(col++, row, rs.getInt("manhanvien")));
				sheet.addCell(new Label(col++, row, rs.getString("tennhanvien")));
				sheet.addCell(new Label(col++, row, rs.getString("ngaysinh")));
				sheet.addCell(new Label(col++, row, rs.getString("diachi")));
				sheet.addCell(new Number(col++, row, rs.getInt("luong")));
				sheet.addCell(new Label(col++, row, rs.getString("tenchucvu")));

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

	public void printNVQL(WritableWorkbook workbook) {
		try {
			WritableSheet sheet = workbook.createSheet("DS NHÂN VIÊN", 0);
			sheet.addCell(new Label(0, 0, "DANH SÁCH NHÂN VIÊN QUẢN LÝ PHÒNG"));
			sheet.addCell(new Label(0, 2, "MSNV"));
			sheet.addCell(new Label(1, 2, "Tên NV"));
			sheet.addCell(new Label(2, 2, "Mã phòng"));
			sheet.addCell(new Label(3, 2, "Mã khu"));

			int col = 0;
			int row = 4;
			Room room = new Room();
			for (Room r : room.listManager()) {
				col = 0;
				sheet.addCell(new Number(col++, row, r.getEmployee().getManv()));
				sheet.addCell(new Label(col++, row, r.getEmployee().getTennv()));
				sheet.addCell(new Number(col++, row, r.getMaphong()));
				sheet.addCell(new Number(col++, row, r.getRoomRegion().getMakhu()));

				row++;
			}
			
			workbook.write();
			workbook.close();
		}catch (RowsExceededException e) {
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

	public int themnv() {
		return 0;
	}

	public int doichucvu() {
		return 0;
	}

	public int getManv() {
		return manv;
	}

	public void setManv(int manv) {
		this.manv = manv;
	}

	public String getTennv() {
		return tennv;
	}

	public void setTennv(String tennv) {
		this.tennv = tennv;
	}

	public String getNgaysinh() {
		return ngaysinh;
	}

	public void setNgaysinh(String ngaysinh) {
		this.ngaysinh = ngaysinh;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public int getChucvu() {
		return chucvu;
	}

	public void setChucvu(int chucvu) {
		this.chucvu = chucvu;
	}

	public double getLuong() {
		return luong;
	}

	public void setLuong(double luong) {
		this.luong = luong;
	}

}
