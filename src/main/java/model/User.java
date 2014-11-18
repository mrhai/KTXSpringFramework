/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.servlet.http.HttpSession;

/**
 *
 * @author MrHai
 */
public abstract class User {

    public abstract int login();

    public void logout(HttpSession session){
        session.removeAttribute("username");
        session.removeAttribute("actor");
        session.invalidate();
    }

    public abstract int doimatkhau();
    
    public boolean checkSession(HttpSession session){
        if(session.getAttribute("username") != null) return true;
        return false;
    }
    
    public boolean getActor(HttpSession session,String actor){
        if(session.getAttribute("actor").equals(actor)) return true;
        return false;
    }
}
