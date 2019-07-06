/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.backing;

import com.project.model.User;
import com.project.model.UserExixtException;
import com.project.model.UserFacade;
import com.project.model.User_;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;

/**
 *
 * @author AFAQE3
 */
@Named(value = "registrationBacking")
@RequestScoped
public class RegistrationBacking extends GlobaBacking {

    @EJB
    UserFacade userservice;

    public String registerUser() {
        try {
            Map<Object, Object> flowmap = getFlowScope();
            String fullname = (String) flowmap.get("fullname");
            String pwd = (String) flowmap.get("pwd");
            String pwdconfirm = (String) flowmap.get("pwdconfirm");
            String email = (String) flowmap.get("email");
            String location = (String) flowmap.get("location");
            if(!pwd.equals(pwdconfirm)){
                 sendMsg(null, FacesMessage.SEVERITY_ERROR, "passwords are not the same", "pw are not the same");
            }
            
            User newuser = new User();
            newuser.setFullName(fullname);
            newuser.setLocation(location);
            newuser.setPwd(pwd);
            newuser.setEmail(email);
            userservice.registerUser(newuser);
            return "/index?faces-redirect=true";
        } catch (UserExixtException ex) {
            //  Logger.getLogger(RegistrationBacking.class.getName()).log(Level.SEVERE, null, ex);
            sendMsg(null, FacesMessage.SEVERITY_ERROR, "user already exists", "userexists");
        }
        return "";
    }
}
