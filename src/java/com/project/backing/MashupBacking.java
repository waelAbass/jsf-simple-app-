/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.backing;

import com.project.model.User;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author AFAQE3
 */
@Named(value = "mashupBacking")
@RequestScoped
public class MashupBacking extends GlobaBacking {

    private User curuser;
    private String addresee;

    public String getAddresee() {
        return addresee;
    }

    @PostConstruct
    public void init() {
        try {
            curuser = (User) getSession().getAttribute("usersession");
        } catch (NullPointerException exception) {
            getFacesContext().getApplication().getNavigationHandler().handleNavigation(getFacesContext(), null, "/index"
                    + "?faces-redirect=true");
        }
    }
}
