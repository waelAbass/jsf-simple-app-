/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author AFAQE3
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "FinalProjectJsfPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    public User getUser(String email, String passsword) {
        try {
            return (User) em.createNamedQuery("User.getUser").setParameter("email", email).setParameter("pwd", passsword).getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

    public void registerUser(User u) throws UserExixtException {
        try {
            em.createNamedQuery("User.findByEmail").setParameter("email", u.getEmail()).getSingleResult();
            throw new UserExixtException();
        } catch (NoResultException exception) {
            em.persist(u);
        }
    }

}
