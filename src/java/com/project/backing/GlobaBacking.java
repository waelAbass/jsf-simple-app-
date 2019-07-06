/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.backing;

import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class GlobaBacking {

    protected FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    protected Map getRequestMap() {
        return getFacesContext().getExternalContext().getRequestMap();
    }

    protected HttpSession getSession() {
        return (HttpSession) getFacesContext().getExternalContext().getSession(false);
    }

    protected Object getBean(String el, Class clas) {
        return getFacesContext().getApplication().evaluateExpressionGet(getFacesContext(), el, clas);
    }

    protected void sendMsg(String compId, FacesMessage.Severity severity, String summary , String Detail) {
     getFacesContext().addMessage(compId, new FacesMessage(severity, summary,Detail));
    }
    protected Map<Object,Object> getFlowScope(){
    return getFacesContext().getApplication().getFlowHandler().getCurrentFlowScope();
    }
}
