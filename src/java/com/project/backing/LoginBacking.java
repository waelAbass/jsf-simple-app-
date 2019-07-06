/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.backing;

import com.project.model.User;
import com.project.model.UserFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;

@Named(value = "loginBacking")
@RequestScoped
public class LoginBacking extends GlobaBacking implements Serializable {

    @EJB
    private UserFacade userservice;

    public String login() {
        User currentuser = (User) getBean("#{user}", User.class);
        try {
            currentuser = userservice.getUser(currentuser.getEmail(), currentuser.getPwd());
            getSession().setAttribute("usersession", currentuser);
            return "/mashup/mashup?faces-reirect=true";
        } catch (NullPointerException npe) {
            sendMsg("login-msg", FacesMessage.SEVERITY_ERROR, "user or password", "");
            return null;
        }

    }
}
