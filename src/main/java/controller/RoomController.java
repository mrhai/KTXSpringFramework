package controller;

import javax.servlet.http.HttpServletRequest;

import model.GeneralUser;
import model.Room;
import model.RoomRegion;
import model.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/room")
public class RoomController {

	@RequestMapping(value = "/roommanager", method = RequestMethod.GET)
	public String roomManager(HttpServletRequest request) {
		User user = new GeneralUser();
		if (user.checkSession(request.getSession())) {
			if (user.getActor(request.getSession(), "admin")) {
				request.setAttribute("roomnum", new Room().roomList());
				request.setAttribute("roomregion",
						new RoomRegion().regionList());
				return "quanlyphong";
			} else {
				request.setAttribute("message",
						"Bạn không có quyền truy cập vào mục vừa rồi!");
				user.logout(request.getSession());
				return "dangnhap";
			}
		} else {
			request.setAttribute("message", "Vui lòng đăng nhập!");
			return "dangnhap";
		}

	}
}
