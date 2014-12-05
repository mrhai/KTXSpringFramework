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
public class AdminUser extends User{

    private Employee employee;
    private String password;
    Database db = Database.create();
    public AdminUser(Employee employee, String password) {
        this.employee = employee;
        this.password = password;
    }
    
    @Override
    public int login() {
        int check = 0;
        String command = "select * from admin where msnv = "+employee.getManv()+" and matkhau = '"+password+"'";
       
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
    	String command = "UPDATE admin set matkhau = '"+password+"' where msnv = "+employee.getManv();
    	System.out.println(command);
    	try {
			db.update(command);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return 0;
    }
    
}
