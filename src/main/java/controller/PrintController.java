package controller;

import javax.servlet.http.HttpServletRequest;

import model.Device;
import model.Employee;
import model.GeneralUser;
import model.Room;
import model.RoomRegion;
import model.Student;
import model.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/print")
public class PrintController {

	private GeneralUser user;

	@RequestMapping(value = "print", method = RequestMethod.GET)
	public String print(HttpServletRequest request) {
		User user = new GeneralUser();
		if (user.checkSession(request.getSession())) {
			if (user.getActor(request.getSession(), "admin")) {
				request.setAttribute("roomregion",
						new RoomRegion().regionList());
				request.setAttribute("roomnum", new Room().roomList());
				return "in";
			} else {
				request.setAttribute("message",
						"Bạn không có quyền truy cập vào mục vừa rồi!");
				user.logout(request.getSession());
				return "dangnhap";
			}
		} else {
			return "dangnhap";
		}
	}

	@RequestMapping(value = "/insv", method = RequestMethod.GET)
	public String inSV(HttpServletRequest request) {
		String type = request.getParameter("type");
		Student student = new Student();
		student.in(type);
		return print(request);
	}

	@RequestMapping(value = "/innv", method = RequestMethod.GET)
	public String inNV(HttpServletRequest request) {
		String type = request.getParameter("type");
		Employee employee = new Employee();
		employee.in(type);
		return print(request);
	}

	@RequestMapping(value = "/invt", method = RequestMethod.GET)
	public String inVT(HttpServletRequest request) {
		int maphong = Integer.parseInt(request.getParameter("maphong"));
		int makhu = Integer.parseInt(request.getParameter("makhu"));
		RoomRegion region = new RoomRegion();
		region.setMakhu(makhu);
		Room room = new Room();
		room.setMaphong(maphong);
		room.setRoomRegion(region);
		room.setDevice(new Device());
		room.in("DSVT");
		request.setAttribute("id", "vt");
		return print(request);
	}
}
