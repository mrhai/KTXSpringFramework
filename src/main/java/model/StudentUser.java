/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MrHai
 */
public class StudentUser extends User{
    private Student student;
    private String matkhau;
    Database db = Database.create();
    public StudentUser(Student student, String matkhau) {
        this.student = student;
        this.matkhau = matkhau;
    }

    @Override
    public int login() {
         int check = 0;
        String command = "select * from taikhoansv where mssv = "+student.getMssv()+" and matkhau = '"+matkhau+"'";
        try {
            ResultSet rs = db.execute(command);
            while(rs.next()){
                check++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }

    @Override
    public int doimatkhau() {
    	String command = "UPDATE taikhoansv set matkhau = '"+matkhau+"' where mssv = "+student.getMssv();
    	System.out.println(command);
    	try {
			db.update(command);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return 0;
    }
    
     public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }
}
