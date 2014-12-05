/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AdminUser;
import model.Employee;
import model.EmployeeUser;
import model.GeneralUser;
import model.Student;
import model.StudentUser;
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
@RequestMapping(value = "/user")
public class UserController {

	private User user;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap mm, HttpServletRequest request) {
		user = new GeneralUser();
		if (user.checkSession(request.getSession())) {
			return "adminpage";
		} else {
			return "dangnhap";
		}

	}
	
	@RequestMapping(value = "/pw", method = RequestMethod.GET)
	public String pw(HttpServletRequest request) {
		user = new GeneralUser();
		if (user.checkSession(request.getSession())) {
			return "password";
		} else {
			return "dangnhap";
		}

	}

	@RequestMapping(value = "/xulidangnhap", method = RequestMethod.POST)
	public String welcome(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		int username = Integer.parseInt(request.getParameter("username"));
		String password = request.getParameter("password");

		Employee employee = new Employee();
		employee.setManv(username);
		user = new AdminUser(employee, password);
		// admin
		if (user.login() != 0) {
			session.setAttribute("username", username);
			session.setAttribute("actor", "admin");
			return "adminpage";
		} else {
			// student
			Student student = new Student();
			student.setMssv(username);
			user = new StudentUser(student, password);
			if (user.login() != 0) {
				session.setAttribute("username", username);
				session.setAttribute("actor", "sv");
				
				request.setAttribute("list", student.getStudentRoom());
				return "sinhvien";
			} else {
				// nvs
				employee = new Employee();
				employee.setManv(username);
				user = new EmployeeUser(employee, password);
				if (user.login() != 0) {
					session.setAttribute("username", username);
					session.setAttribute("actor", "nv");
					return "nhanvien";// tam thoi
				} else {
					request.setAttribute("message",
							"Đăng nhập thất bại, vui lòng kiểm tra lại thông tin đăng nhập!");
					return "dangnhap";
				}
			}
		}

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		user = new GeneralUser();
		user.logout(session);
		return "dangnhap";
	}

	@RequestMapping(value = "/password", method = RequestMethod.POST)
	public String password(HttpSession session, HttpServletRequest request) {
		String type = (String) session.getAttribute("actor");
		String newpw = request.getParameter("newpw");
		String username = request.getParameter("username");
		System.out.println(username);
		String confirm = request.getParameter("confirm");
		User user = null;
		if (newpw.equals(confirm)) {
			if (type.equals("admin")) {
				Employee employee = new Employee();
				employee.setManv(Integer.parseInt(username));
				user = new AdminUser(employee, newpw);
			} else if (type.equals("sv")) {
				Student student = new Student();
				student.setMssv(Integer.parseInt(username));
				user = new StudentUser(student, newpw);
			} else {
				Employee employee = new Employee();
				employee.setManv(Integer.parseInt(username));
				user = new EmployeeUser(employee, newpw);
			}
		}
		try {
			user.doimatkhau();
			return logout(session);
		} catch (Exception e) {
			request.setAttribute("message", "Xác nhận mật khẩu không chính xác!");
			return pw(request);
		}
		
		
	}
}
