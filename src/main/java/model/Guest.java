/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MrHai
 */
public class Guest {

    private int maso;
    private String tenkhach;
    private int cmnd;
    private String ngayo;
    private String ngaydi;
    private Student student;
    private int leave;
    Database db = Database.create();

    public Guest(int maso, String tenkhach, int cmnd, String ngayo, String ngaydi, Student student) {
        this.maso = maso;
        this.tenkhach = tenkhach;
        this.cmnd = cmnd;
        this.ngayo = ngayo;
        this.ngaydi = ngaydi;
        this.student = student;
    }

    public Guest(int maso, String tenkhach, int cmnd, String ngayo, String ngaydi, Student student, int leave) {
        this.maso = maso;
        this.tenkhach = tenkhach;
        this.cmnd = cmnd;
        this.ngayo = ngayo;
        this.ngaydi = ngaydi;
        this.student = student;
        this.leave = leave;
    }

    public Guest() {
    }

    public int add() {
        int i = 0;
        String command = "INSERT INTO khach VALUES (" + maso + ", '" + tenkhach + "', " + cmnd + ", '" + ngayo + "', '" + ngaydi + "', " + student.getMssv() + ")";
        try {
            i = db.update(command);
        } catch (SQLException ex) {
            Logger.getLogger(Guest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }

    public ArrayList<Guest> view() {
        ArrayList<Guest> list = new ArrayList<Guest>();
        String command = "";
        if (student.getMssv() != 0) {
            command = "select * from khach WHERE masv = " + student.getMssv();
        } else {
            command = "select * from khach";
        }
        Date now = new Date();

        try {
            ResultSet rs = db.execute(command);
            while (rs.next()) {
                leave = 0;
                Date date = rs.getDate("ngaydi");

                if (date.before(now) == true) {
                    leave = 1;
                }
                student = new Student();
                student.setMssv(rs.getInt("masv"));
                list.add(new Guest(rs.getInt("makhach"), rs.getString("tenkhach"), rs.getInt("cmnd"), rs.getString("ngayo"), rs.getString("ngaydi"), student, leave));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int delete(String type) {
        int leg = 0;
        try {

            String command = "";
            if (type.equals("only")) {
                command = "delete from khach where makhach = " + maso;
            } else {
                command = "delete from khach where ngaydi < NOW()";
            }
            leg = db.update(command);
        } catch (SQLException ex) {
            Logger.getLogger(Guest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return leg;
    }

    public ArrayList<Guest> getListKhach() {
        ArrayList<Guest> list = new ArrayList<Guest>();
        String command = "select * from khach";
        try {
            ResultSet rs = db.execute(command);
            Guest guest;
            while (rs.next()) {
            	guest = new Guest();
            	guest.setMaso(rs.getInt("makhach"));
            	guest.setTenkhach(rs.getString("tenkhach"));
                list.add(guest);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public int getMaKhach() {
        int i = 1;
        String command = "select max(makhach) as ma from khach";
        try {
            ResultSet rs = db.execute(command);
            while (rs.next()) {
                i = rs.getInt("ma") + 1;
            }
        } catch (Exception e) {
        }
        return i;
    }

    public int getMaso() {
        return maso;
    }

    public void setMaso(int maso) {
        this.maso = maso;
    }

    public String getTenkhach() {
        return tenkhach;
    }

    public void setTenkhach(String tenkhach) {
        this.tenkhach = tenkhach;
    }

    public int getCmnd() {
        return cmnd;
    }

    public void setCmnd(int cmnd) {
        this.cmnd = cmnd;
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Database getDb() {
        return db;
    }

    public void setDb(Database db) {
        this.db = db;
    }

    public int getLeave() {
        return leave;
    }

    public void setLeave(int leave) {
        this.leave = leave;
    }

}
