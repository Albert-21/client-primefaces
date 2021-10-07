/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faces.daos;

import com.faces.entity.Usuarios;
import com.faces.factory.IDAO;
import com.faces.utils.HibernateUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author alberto
 */
public class UsuariosDAO implements IDAO<Usuarios>{

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public boolean iniciarSesion(String user, String pass) {
        boolean result = false;
        Session session = null;
        Usuarios usuarioTem = null;
        try {
            session = sessionFactory.openSession();
            usuarioTem = (Usuarios) session.createQuery("SELECT user FROM Usuarios user WHERE  usuario = '" + user + "' AND contraseña = '" + pass + "'").uniqueResult();
           if (usuarioTem != null) {
                result = true;
            } 
        } catch (HibernateException ex) {
            result = false;
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            session.close();
        }
        return result;
    }

    public boolean resgistrar(String user, String pass) {
        boolean result = false;
        Usuarios pojo = null, usuario = null;
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            usuario = (Usuarios) session.createQuery("SELECT user FROM Usuarios user WHERE  usuario = '" + user + "'").uniqueResult();
            if (usuario == null) {
                transaction = session.beginTransaction();
                pojo = new Usuarios();
                pojo.setUsuario(user);
                pojo.setContraseña(pass);
                session.save(pojo);
                transaction.commit();
                result = true;
            }

        } catch (HibernateException ex) {
            result = false;
            if (transaction != null) {
                transaction.rollback();
            }
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public boolean insert(Usuarios pojo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Usuarios pojo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuarios searchById(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuarios> showAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
