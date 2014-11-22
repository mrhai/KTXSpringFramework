/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import javax.servlet.http.HttpServletRequest;

import model.Device;
import model.GeneralUser;
import model.Room;
import model.RoomRegion;
import model.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author MrHai
 */
@Controller
@RequestMapping(value = "/vattu")
public class VattuController {
    private User user;
    @RequestMapping(value = "/vattu", method = RequestMethod.GET)
    public String vattuManager( HttpServletRequest request) {
        user = new GeneralUser();
        if(user.checkSession(request.getSession())){
        	request.setAttribute("listVT", new Device().getDevice());
            request.setAttribute("roomnum", new Room().roomList());
            request.setAttribute("roomregion", new RoomRegion().regionList());
            return "quanlyvattu";
         } else {
            return "dangnhap";
        }

    }
    
    @RequestMapping(value="/nhap",method = RequestMethod.GET)
    public String nhapkho(HttpServletRequest request){
    	int vt = Integer.parseInt(request.getParameter("vt"));
    	int sl = Integer.parseInt(request.getParameter("sl"));
    	Device device = new Device();
    	device.setMaso(vt);
    	if(device.nhapkho(sl) != 0){
    		request.setAttribute("id", "add");
    		request.setAttribute("message", "Nhập kho thành công!");
    	}
    	
    	return vattuManager(request);
    }
    
    @RequestMapping(value ="/chuyen", method = RequestMethod.GET)
    public String chuyenVT(HttpServletRequest request){
    	int maphong = Integer.parseInt(request.getParameter("roomnum"));
    	int makhu = Integer.parseInt(request.getParameter("roomregion"));
    	int mavt = Integer.parseInt(request.getParameter("vt"));
    	int soluong = Integer.parseInt(request.getParameter("sl"));
    	
    	Device device = new Device();
    	device.setMaso(mavt);
    	device.setSoluong(soluong);
    	RoomRegion region = new RoomRegion();
    	region.setMakhu(makhu);
    	Room room = new Room();
    	room.setDevice(device);
    	room.setRoomRegion(region);
    	room.setMaphong(maphong);
    	if(room.chuyenVT() != 0){
    		request.setAttribute("message", "Đã chuyển vật tư đến phòng!");
    	}
    	
    	return vattuManager(request);
    }
    
    @RequestMapping(value = "suachua", method = RequestMethod.GET)
    public String suachua(HttpServletRequest request){
    	int maphong = Integer.parseInt(request.getParameter("roomnum"));
    	int makhu = Integer.parseInt(request.getParameter("roomregion"));
    	int vt = Integer.parseInt(request.getParameter("vt"));
    	int gia = Integer.parseInt(request.getParameter("gia"));
    	
    	Device device = new Device();
    	device.setMaso(vt);
    	device.setGia(gia);
    	RoomRegion region = new RoomRegion();
    	region.setMakhu(makhu);
    	Room room = new Room();
    	room.setMaphong(maphong);
    	room.setRoomRegion(region);
    	room.setDevice(device);
    	
    	if(room.repairDevice() >= 2){
    		request.setAttribute("message", "Đã thêm thông tin sửa chưa!");
    	}else{
    		request.setAttribute("message", "Vật tư này chưa có trong phòng!");
    	}
    	return vattuManager(request);
    	
    }
    
    @RequestMapping(value = "/lichsu", method = RequestMethod.GET)
    public String lichsu(HttpServletRequest request){
    	int maphong = Integer.parseInt(request.getParameter("roomnum"));
    	int makhu = Integer.parseInt(request.getParameter("roomregion"));
    	Room room = new Room();
    	room.setMaphong(maphong);
    	RoomRegion region = new RoomRegion();
    	region.setMakhu(makhu);
    	room.setRoomRegion(region);
    	
    	request.setAttribute("history", room.getDSRepair());
    	return vattuManager(request);
    }
}
