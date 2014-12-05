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
public class EmployeeUser extends User{
    private Employee employee;
    private String matkhau;
    Database db = Database.create();
    public EmployeeUser(Employee employee, String matkhau) {
        this.employee = employee;
        this.matkhau = matkhau;
    }
    
    
    @Override
    public int login() {
         int check = 0;
        String command = "select * from taikhoannhanvien where manv = "+employee.getManv()+" and matkhau='"+matkhau+"'";
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
    	String command = "UPDATE taikhoannhanvien set matkhau = '"+matkhau+"' where manv = "+employee.getManv();
    	System.out.println(command);
    	try {
			db.update(command);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return 0;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }
    
}
