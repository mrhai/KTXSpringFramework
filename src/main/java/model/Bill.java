/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author MrHai
 */
public class Bill {
    
    private int mahoadon;
    private double giadien;
    private int sodien;
    private double gianuoc;
    private int sonuoc;
    private Room room;
    private int tienphong;
    private String thoigian;
    private double tongtien;

    public Bill(int mahoadon, double giadien, int sodien, double gianuoc, int sonuoc, Room room, int tienphong, String thoigian, double tongtien) {
        this.mahoadon = mahoadon;
        this.giadien = giadien;
        this.sodien = sodien;
        this.gianuoc = gianuoc;
        this.sonuoc = sonuoc;
        this.room = room;
        this.tienphong = tienphong;
        this.thoigian = thoigian;
        this.tongtien = tongtien;
    }

    public int getMahoadon() {
        return mahoadon;
    }

    public void setMahoadon(int mahoadon) {
        this.mahoadon = mahoadon;
    }

    public double getGiadien() {
        return giadien;
    }

    public void setGiadien(double giadien) {
        this.giadien = giadien;
    }

    public int getSodien() {
        return sodien;
    }

    public void setSodien(int sodien) {
        this.sodien = sodien;
    }

    public double getGianuoc() {
        return gianuoc;
    }

    public void setGianuoc(double gianuoc) {
        this.gianuoc = gianuoc;
    }

    public int getSonuoc() {
        return sonuoc;
    }

    public void setSonuoc(int sonuoc) {
        this.sonuoc = sonuoc;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getTienphong() {
        return tienphong;
    }

    public void setTienphong(int tienphong) {
        this.tienphong = tienphong;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public double getTongtien() {
        return tongtien;
    }

    public void setTongtien(double tongtien) {
        this.tongtien = tongtien;
    }
    
    
}
