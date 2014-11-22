/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
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
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author MrHai
 */
@Controller
@RequestMapping(value = "/student")
public class StudentController {
	
	 private final String UPLOAD_DIRECTORY = "C:/uploads";

	
    @RequestMapping(value = "/svmanager", method = RequestMethod.GET)
    public String SVManager(HttpServletRequest request) {
        User user = new GeneralUser();
        if (user.checkSession(request.getSession())) {
            if (user.getActor(request.getSession(), "admin")) {
            request.setAttribute("roomnum", new Room().roomList());
            request.setAttribute("roomregion", new RoomRegion().regionList());
            request.setAttribute("mssv", new Student().getMSSV());
            request.setAttribute("bill", new Student().getNoList());
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

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addorchange(HttpServletRequest request) {
        int mssv = Integer.parseInt(request.getParameter("mssv"));
        String tensv = request.getParameter("tensv");
        String ngaysinh = request.getParameter("ngaysinh");
        String ngaydi = request.getParameter("ngaydi");
        String que = request.getParameter("que");
        String lop = request.getParameter("lop");
        String khoa = request.getParameter("khoa");
        int sdt = Integer.parseInt(request.getParameter("sdt"));
        int maphong = Integer.parseInt(request.getParameter("roomnum"));
        int makhu = Integer.parseInt(request.getParameter("roomregion"));
        RoomRegion region = new RoomRegion(makhu, "");
        Room room = new Room(maphong, region, 0, 0, null, null);
        if (room.checkRoom()) {
            Student student = new Student(mssv, tensv, ngaysinh, que, lop, khoa, room, sdt,ngaydi);
            request.setAttribute("roomnum", new Room().roomList());
            request.setAttribute("roomregion", new RoomRegion().regionList());
            try {
                if (student.add() >= 3) {
//                	// upfile
//                	if(ServletFileUpload.isMultipartContent(request)){
//                        try {
//                            List<FileItem> multiparts = new ServletFileUpload(
//                                                     new DiskFileItemFactory()).parseRequest(request);
//                          
//                            for(FileItem item : multiparts){
//                                if(!item.isFormField()){
//                                    String name = new File(item.getName()).getName();
//                                    item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
//                                }
//                            }
//                       
//                           //File uploaded successfully
//                           request.setAttribute("message", "File Uploaded Successfully");
//                        } catch (Exception ex) {
//                           request.setAttribute("message", "File Upload Failed due to " + ex);
//                        }          
//                     
//                    }else{
//                        request.setAttribute("message",
//                                             "Sorry this Servlet only handles file upload request");
//                    }
//                	//het up
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
            if(student.checkBill() ==0){
            if (student.changeRoom() != 0) {
                request.setAttribute("message", "Thay đổi thành công!");
            } else {
                request.setAttribute("message", "Sinh viên hiện đang ở phòng này!");
            }
            }else{
            	 request.setAttribute("message", "Sinh viên này hiện đang nợ tiền phòng, không thể đổi phòng lúc này!");
            }
        } else {
            request.setAttribute("message", "Phòng này hiện đã đầy!");
        }
         request.setAttribute("id", "change");
        return SVManager(request);
    }
    
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String find(HttpServletRequest request){
    	String mssv = request.getParameter("mssv");
    	request.setAttribute("id", "list");
    	request.setAttribute("studentlist", new Student().getStudentList(mssv));
    	return SVManager(request);
    }
    
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(HttpServletRequest request){
    	String mssv = request.getParameter("mssv");
    	request.setAttribute("student", new Student().getStudentList(mssv));
    	return "view";
    }
    @RequestMapping(value = "/ktkl", method = RequestMethod.POST)
    public String ktkl(HttpServletRequest request){
    	String mode = request.getParameter("mode");
    	int mssv = Integer.parseInt(request.getParameter("mssv"));
    	String content = request.getParameter("nd");
    	Student student = new Student();
    	student.setMssv(mssv);
    	if(student.klkt(mode, content) != 0){
    		request.setAttribute("message", "Lưu thành công!");
    		request.setAttribute("id", "ktkl");
    	}
    	return SVManager(request);
    }
    
    @RequestMapping(value = "/timktkl", method = RequestMethod.GET)
    public String timktkl(HttpServletRequest request){
    	int mssv;
    	try {
    		mssv = Integer.parseInt(request.getParameter("mssv"));
		} catch (NumberFormatException e) {
			mssv = 0;
		}
    	
    	Student st = new Student();
    	st.setMssv(mssv);
    	request.setAttribute("listktkl", st.getKTKL());
    	request.setAttribute("id", "ktkl");
    	return SVManager(request);
    }
    
}
