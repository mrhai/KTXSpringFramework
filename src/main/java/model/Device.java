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
public class Device {
    
    private int maso;
    private String tenvattu;
    private int soluong;

    public Device(){
        
    }
    public Device(int maso, String tenvattu, int soluong) {
        this.maso = maso;
        this.tenvattu = tenvattu;
        this.soluong = soluong;
    }

    public int getMaso() {
        return maso;
    }

    public void setMaso(int maso) {
        this.maso = maso;
    }

    public String getTenvattu() {
        return tenvattu;
    }

    public void setTenvattu(String tenvattu) {
        this.tenvattu = tenvattu;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
    
    
}
