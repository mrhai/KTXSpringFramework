/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import javax.servlet.http.HttpServletRequest;

import model.Device;
import model.GeneralUser;
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
}
