/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.servlet.http.HttpServletRequest;
import model.GeneralUser;
import model.Guest;

import model.Student;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author MrHai
 */
@Controller
@RequestMapping(value = "/guest")
public class GuestController {

    @RequestMapping(value = "/guestmanager", method = RequestMethod.GET)
    public String guestManager(HttpServletRequest request) {
        User user = new GeneralUser();
        if (user.checkSession(request.getSession())) {
             request.setAttribute("mssv", new Student().getMSSV());
              request.setAttribute("makhach", new Guest().getMaKhach());
               request.setAttribute("listGuest", new Guest().getListKhach());
            return "quanlykhach";
        } else {
            request.setAttribute("message", "Vui lòng đăng nhập!!");
            return "dangnhap";
        }
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(HttpServletRequest request){
        int makhach = Integer.parseInt(request.getParameter("makhach"));
        String tenkhach = request.getParameter("tenkhach");
        int cmnd = Integer.parseInt(request.getParameter("cmnd"));
        String ngayo = request.getParameter("ngayo");
        String ngaydi = request.getParameter("ngaydi");
        int mssv = Integer.parseInt(request.getParameter("mssv"));
        Student student = new Student();
        student.setMssv(mssv);
        Guest guest = new Guest(makhach, tenkhach, cmnd, ngayo, ngaydi, student);
        
        if(guest.add() != 0){
            request.setAttribute("message", "Đã thêm khách có mã số là: "+makhach+" - tên là: "+tenkhach);
        }else{
            request.setAttribute("message", "Không thể thêm khách này");
        }
        request.setAttribute("id", "add");
        return guestManager(request);
    }
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public String view(HttpServletRequest request){
        int mssv = 0;
        try {
            mssv = Integer.parseInt(request.getParameter("mssv"));
        } catch (NumberFormatException e) {
            mssv = 0;
        }
        
        Guest guest = new Guest();
        Student student = new Student();
        student.setMssv(mssv);
        guest.setStudent(student);
        request.setAttribute("list", guest.view());
        request.setAttribute("id", "view");
        return guestManager(request);
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(HttpServletRequest request){
        String type = request.getParameter("type");
        int makhach = 0;
        try {
             makhach = Integer.parseInt(request.getParameter("makhach"));
        } catch (Exception e) {
        }
       
        Guest guest = new Guest();
        guest.setMaso(makhach);
        guest.delete(type);
        request.setAttribute("message", "Xóa thành công!");
         request.setAttribute("id", "delete");
        return guestManager(request);
    }
}
