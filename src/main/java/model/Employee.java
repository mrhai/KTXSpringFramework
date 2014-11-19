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

    public Employee(int manv, String tennv, String ngaysinh, String diachi, int chucvu, double luong) {
        this.manv = manv;
        this.tennv = tennv;
        this.ngaysinh = ngaysinh;
        this.diachi = diachi;
        this.chucvu = chucvu;
        this.luong = luong;
    }

    public int add() {
        int leg = 0;
        String command = "INSERT INTO nhanvien VALUE(" + manv + ",'" + tennv + "','" + ngaysinh + "','" + diachi + "',0,"+chucvu+")";
        System.out.println(command);
        String command2 = "";
        if (chucvu == 2 || chucvu == 3) {
            command2 = "INSERT INTO admin VALUES(" + manv + ",'123456')";
        } else {
            command2 = "INSERT INTO taikhoannhanvien VALUES(" + manv + ",'123456')";
        }
        
        try {
            leg += db.update(command);
            leg += db.update(command2);
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return leg;
    }

    public int delete(){
        int leg = 0;
        String tkad = "delete from admin where msnv = "+manv;
        String tknv = "delete from taikhoannhanvien where manv = "+manv;
        String nv = "delete from nhanvien where manhanvien = "+manv;
        try {
            leg+=db.update(tkad);
            leg+=db.update(tknv);
            leg+=db.update(nv);
        } catch (Exception e) {
        }
        return  leg;
    }
    
    public int changeSalary(){
        int leg = 0;
        String command = "UPDATE nhanvien SET luong='"+luong+"' WHERE manhanvien="+manv;
        try {
            leg = db.update(command);
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return leg;
    }
    
    public int autoMNV(){
        int mnv = 0;
        String command = "select max(manhanvien) as mnv from nhanvien";
        try {
            ResultSet rs = db.execute(command);
            while(rs.next()){
                mnv = rs.getInt("mnv")+1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mnv;
    }
    
    public ArrayList<Employee> getMNV(){
        ArrayList<Employee> list = new ArrayList<Employee>();
        String command = "select * from nhanvien where manhanvien > 123";
         try {
            ResultSet rs = db.execute(command);
            while(rs.next()){
            	Employee employee = new Employee();
            	employee.setManv(rs.getInt("manhanvien"));
            	employee.setTennv(rs.getString("tennhanvien"));
                list.add(employee);
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public ArrayList<Position> getPosition(){
        ArrayList<Position> list = new ArrayList<Position>();
        String command = "select * from chucvu";
        try {
            ResultSet rs = db.execute(command);
            while(rs.next()){
                list.add(new Position(rs.getInt("machucvu"), rs.getString("tenchucvu")));
            }
        } catch (Exception e) {
        }
        return list;
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

