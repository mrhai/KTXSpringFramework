/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Employee;
import model.GeneralUser;
import model.Room;
import model.RoomRegion;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {

    @RequestMapping(value = "/employeemanager", method = RequestMethod.GET)
    public String employeeManager(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = new GeneralUser();
        if (user.checkSession(session)) {
            if (user.getActor(session, "admin")) {
                request.setAttribute("manv", new Employee().autoMNV());
                request.setAttribute("chucvu", new Employee().getPosition());
                request.setAttribute("mnv", new Employee().getMNV());
                return "quanlynhanvien";
            } else {
                request.setAttribute("message", "Bạn không có quyền truy cập vào mục vừa rồi!");
                 user.logout(request.getSession());
                return "dangnhap";
            }
        } else {
            request.setAttribute("message", "Vui lòng đăng nhập!");
            return "dangnhap";
        }

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(HttpServletRequest request) {
        String mnv = request.getParameter("mnv");
        String tennv = request.getParameter("tennv");
        String ngaysinh = request.getParameter("ngaysinh");
        String diachi = request.getParameter("diachi");
        String chucvu = request.getParameter("chucvu");

        Employee employee = new Employee(Integer.parseInt(mnv), tennv, ngaysinh, diachi, Integer.parseInt(chucvu), 0);
        if (employee.add() >= 2) {
            request.setAttribute("message", "Thêm nhân viên thành công!");
        } else {
            request.setAttribute("message", "Không thể thêm nhân viên!");
        }
        request.setAttribute("id", "add");
        return employeeManager(request);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(HttpServletRequest request) {
        int mnv = Integer.parseInt(request.getParameter("mnv"));
        Employee employee = new Employee();
        employee.setManv(mnv);
        if (employee.delete() >= 2) {
            request.setAttribute("message", "Xóa nhân viên thành công!");
        } else {
            request.setAttribute("message", "Nhân viên này hiện đang quản lý phòng!");
        }
         request.setAttribute("id", "delete");
        return employeeManager(request);
    }

    @RequestMapping(value = "/changesalary", method = RequestMethod.POST)
    public String changeSalary(HttpServletRequest request) {
        int manv = Integer.parseInt(request.getParameter("mnv"));
        int salary = Integer.parseInt(request.getParameter("luong"));
        Employee employee = new Employee();
        employee.setManv(manv);
        employee.setLuong(salary);
        if (employee.changeSalary() != 0) {
            request.setAttribute("message", "Thay đôi lương nhân viên " + manv + " thành " + salary + "!");
        }
         request.setAttribute("id", "change");
        return employeeManager(request);
    }
}
