package controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import model.Bill;
import model.GeneralUser;
import model.Room;
import model.RoomRegion;
import model.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/accounting")
public class AccountingController {

	@RequestMapping(value = "/accountingmanager", method = RequestMethod.GET)
	public String accountingManager(HttpServletRequest request) {
		User user = new GeneralUser();
		if (user.checkSession(request.getSession())) {
			request.setAttribute("roomnum", new Room().roomList());
			request.setAttribute("roomregion", new RoomRegion().regionList());
			SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
	       
			request.setAttribute("date",  dmyFormat.format(new Date()));
			return "ketoan";
		} else {
			request.setAttribute("message", "Vui lòng đăng nhập!");
			return "dangnhap";
		}

	}
	
	@RequestMapping(value = "/bill", method = RequestMethod.POST)
	public String createBill(HttpServletRequest request){
		int maphong = Integer.parseInt(request.getParameter("roomnum"));
		int makhu = Integer.parseInt(request.getParameter("roomregion"));
		int sodien = Integer.parseInt(request.getParameter("sodien"));
		int sonuoc = Integer.parseInt(request.getParameter("sonuoc"));
		String thang = request.getParameter("thang");
		
		RoomRegion region = new RoomRegion();
		region.setMakhu(makhu);
		Room room = new Room();
		room.setMaphong(maphong);
		room.setRoomRegion(region);
		Bill bill = new Bill();
		bill.setSodien(sodien);
		bill.setSonuoc(sonuoc);
		bill.setThoigian(thang);
		bill.setRoom(room);
		if(bill.createBill() != 0){
			request.setAttribute("message", "Đã tạo hóa đơn phòng "+maphong+" khu "+makhu);
		}
		
		
		return accountingManager(request);
	}
	@RequestMapping(value = "out", method = RequestMethod.GET)
	public String out(HttpServletRequest request){
		int maphong = Integer.parseInt(request.getParameter("roomnum"));
		int makhu = Integer.parseInt(request.getParameter("roomregion"));
		String thang = request.getParameter("thang");
		RoomRegion region = new RoomRegion();
		region.setMakhu(makhu);
		Room room = new Room();
		room.setMaphong(maphong);
		room.setRoomRegion(region);
		Bill bill = new Bill();
		bill.setThoigian(thang);
		bill.setRoom(room);
		bill.outBill();
		return accountingManager(request);
	}
}
