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
public class RoomRegion {
    
    private int makhu;
    private String tenkhu;
    Database db = Database.create();
    public RoomRegion() {
    }

    public RoomRegion(int makhu, String tenkhu) {
        this.makhu = makhu;
        this.tenkhu = tenkhu;
    }

    public ArrayList<RoomRegion> regionList(){
        ArrayList<RoomRegion> list = new ArrayList<RoomRegion>();
        String command = "select * from khuphong";
        try {
            ResultSet rs = db.execute(command);
            while(rs.next()){
                list.add(new RoomRegion(rs.getInt("makhu"), rs.getString("tenkhu")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public int getMakhu() {
        return makhu;
    }

    public void setMakhu(int makhu) {
        this.makhu = makhu;
    }

    public String getTenkhu() {
        return tenkhu;
    }

    public void setTenkhu(String tenkhu) {
        this.tenkhu = tenkhu;
    }
    
    
}
