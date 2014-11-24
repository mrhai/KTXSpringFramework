package controller;

import javax.servlet.http.HttpServletRequest;

import model.Device;
import model.Employee;
import model.GeneralUser;
import model.Room;
import model.RoomRegion;
import model.Student;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/print")
public class PrintController {
 
	private GeneralUser user;
	@RequestMapping(value = "print", method = RequestMethod.GET)
	public String print(HttpServletRequest request){
		 user = new GeneralUser();
	        if(user.checkSession(request.getSession())){
	        	
	            return "in";
	         } else {
	            return "dangnhap";
	        }
	}
	
	@RequestMapping(value = "/insv", method = RequestMethod.GET)
	public String inSV(HttpServletRequest request){
		String type = request.getParameter("type");
		Student student = new Student();
		student.in(type);
		return print(request);
	}
	
	@RequestMapping(value = "/innv", method = RequestMethod.GET)
	public String inNV(HttpServletRequest request){
		String type = request.getParameter("type");
		Employee employee = new Employee();
		employee.in(type);
		return print(request);
	}
}