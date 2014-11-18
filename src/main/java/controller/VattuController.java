/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import javax.servlet.http.HttpServletRequest;
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
    public String login(ModelMap mm, HttpServletRequest request) {
        user = new GeneralUser();
        if(user.checkSession(request.getSession())){
            return "quanlyvattu";
         } else {
            return "dangnhap";
        }

    }
}
