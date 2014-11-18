/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import model.GeneralUser;
import model.Room;
import model.RoomRegion;
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
@RequestMapping(value = "/student")
public class StudentController {

    @RequestMapping(value = "/svmanager", method = RequestMethod.GET)
    public String SVManager(HttpServletRequest request) {
        User user = new GeneralUser();
        if (user.checkSession(request.getSession())) {
            if (user.getActor(request.getSession(), "admin")) {
            request.setAttribute("roomnum", new Room().roomList());
            request.setAttribute("roomregion", new RoomRegion().regionList());
            request.setAttribute("mssv", new Student().getMSSV());
            return "quanlysinhvien";
            }else{
                 request.setAttribute("message", "Bạn không có quyền truy cập vào mục vừa rồi!");
                 user.logout(request.getSession());
                return "dangnhap";
            }
        } else {
            request.setAttribute("message", "Vui lòng đăng nhập!");
            return "dangnhap";
        }

    }

    @RequestMapping(value = "/roommanager", method = RequestMethod.GET)
    public String roomManager(HttpServletRequest request) {
        User user = new GeneralUser();
        if (user.checkSession(request.getSession())) {
            request.setAttribute("roomnum", new Room().roomList());
            request.setAttribute("roomregion", new RoomRegion().regionList());
            return "quanlyphong";
        } else {
            request.setAttribute("message", "Vui lòng đăng nhập!");
            return "dangnhap";
        }

    }

    @RequestMapping(value = "/guestmanager", method = RequestMethod.GET)
    public String guestManager(HttpServletRequest request) {
        User user = new GeneralUser();
        if (user.checkSession(request.getSession())) {
            request.setAttribute("roomnum", new Room().roomList());
            request.setAttribute("roomregion", new RoomRegion().regionList());
            return "quanlykhach";
        } else {
            request.setAttribute("message", "Vui lòng đăng nhập!");
            return "dangnhap";
        }

    }

    @RequestMapping(value = "/accountingmanager", method = RequestMethod.GET)
    public String accountingManager(HttpServletRequest request) {
        User user = new GeneralUser();
        if (user.checkSession(request.getSession())) {
            request.setAttribute("roomnum", new Room().roomList());
            request.setAttribute("roomregion", new RoomRegion().regionList());
            return "ketoan";
        } else {
            request.setAttribute("message", "Vui lòng đăng nhập!");
            return "dangnhap";
        }

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addorchange(HttpServletRequest request) {

        int mssv = Integer.parseInt(request.getParameter("mssv"));
        String tensv = request.getParameter("tensv");
        String ngaysinh = request.getParameter("ngaysinh");
        String que = request.getParameter("que");
        String lop = request.getParameter("lop");
        String khoa = request.getParameter("khoa");
        int sdt = Integer.parseInt(request.getParameter("sdt"));
        int maphong = Integer.parseInt(request.getParameter("roomnum"));
        int makhu = Integer.parseInt(request.getParameter("roomregion"));
        RoomRegion region = new RoomRegion(makhu, "");
        Room room = new Room(maphong, region, 0, 0, null, null);
        if (room.checkRoom()) {
            Student student = new Student(mssv, tensv, ngaysinh, que, lop, khoa, room, sdt);
            request.setAttribute("roomnum", new Room().roomList());
            request.setAttribute("roomregion", new RoomRegion().regionList());
            try {
                if (student.add() >= 3) {
                    
                    request.setAttribute("message", "Thêm thành công!");
                } else {
                    request.setAttribute("message", "Không thêm được, Sinh viên này đã tồn tại!");
                }
            } catch (SQLException ex) {
                Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            request.setAttribute("message", "Phòng này hiện đã đầy!");
        }
        request.setAttribute("id", "add");
        return SVManager(request);
    }

    @RequestMapping(value = "/deletesv", method = RequestMethod.POST)
    public String deleteSV(HttpServletRequest request) {
        int mssv = Integer.parseInt(request.getParameter("mssvdelete"));
        Student student = new Student();
        student.setMssv(mssv);
        student.setRoom(new Room());
        if (student.delete() >= 3) {
            request.setAttribute("message", "Xóa thành công!");
        } else {
            request.setAttribute("message", "Xóa không thành công!");
        }
         request.setAttribute("id", "delete");
        return SVManager(request);
    }

    @RequestMapping(value = "/changeroom", method = RequestMethod.POST)
    public String changeRoom(HttpServletRequest request) {
        int mssv = Integer.parseInt(request.getParameter("mssvchang"));
        int roomnum = Integer.parseInt(request.getParameter("roomchang"));
        int regionnum = Integer.parseInt(request.getParameter("regionchang"));

        RoomRegion region = new RoomRegion(regionnum, null);
        Room room = new Room();
        room.setMaphong(roomnum);
        room.setRoomRegion(region);
        if (room.checkRoom()) {
            Student student = new Student();
            student.setRoom(room);
            student.setMssv(mssv);
            if (student.changeRoom() != 0) {
                request.setAttribute("message", "Thay đổi thành công!");
            } else {
                request.setAttribute("message", "Sinh viên hiện đang ở phòng này!");
            }
        } else {
            request.setAttribute("message", "Phòng này hiện đã đầy!");
        }
         request.setAttribute("id", "change");
        return SVManager(request);
    }
}
