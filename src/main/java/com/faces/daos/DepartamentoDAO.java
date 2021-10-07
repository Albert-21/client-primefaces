/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faces.daos;

import com.faces.entity.Departamento;
import com.faces.factory.IDAO;
import com.faces.utils.HibernateUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author alberto
 */
public class DepartamentoDAO implements IDAO<Departamento> {

    private boolean result;
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public boolean insert(Departamento pojo) {
        result = false;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(pojo);
            transaction.commit();
            result = true;
        } catch (HibernateException e) {
            result = false;
            if (transaction != null) {
                transaction.rollback();
            }
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            session.close();
        }
        return result;

    }

    @Override
    public boolean update(Departamento pojo) {
        result = false;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(pojo);
            transaction.commit();
            result = true;
        } catch (HibernateException e) {
            result = false;
            if (transaction != null) {
                transaction.rollback();
            }
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public Departamento searchById(long id) {
        Session session = null;
        Departamento dep = null;
        try {
            session = sessionFactory.openSession();
            dep = (Departamento) session.createQuery("SELECT dep FROM Departamento dep WHERE id= " + id).uniqueResult();
        } catch (HibernateException ex) {
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            session.close();
        }
        return dep;
    }

    @Override
    public List<Departamento> showAll() {
        Session session = null;
        List<Departamento> departamentos = null;
        try {
            session = sessionFactory.openSession();
            Query query = session.createQuery("SELECT dep FROM Departamento dep");
            departamentos = query.list();

        } catch (HibernateException ex) {
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return departamentos;

    }

    @Override
    public boolean delete(long id) {
        result = false;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Departamento departamento = (Departamento) session.get(Departamento.class, id);
            System.out.println(departamento.getNombre());
            session.delete(departamento);
            transaction.commit();
            result = true;
        } catch (HibernateException e) {
            result = false;
            if (transaction != null) {
                transaction.rollback();
            }
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            session.close();
        }
        return result;
    }

}
