package com.faces.daos;

import com.faces.entity.Empleado;
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
public class EmpleadoDAO implements IDAO<Empleado> {
    private boolean result;
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public boolean insert(Empleado pojo) {
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
    public boolean update(Empleado pojo) {
        result = false;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(pojo);
            transaction.commit();
            result=true;
        } catch (HibernateException e) {
            result = false;
            if (transaction != null) {
                transaction.rollback();
            }
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public Empleado searchById(long id) {
        Session session = null;
        Empleado empl = null;
        try {
            session = sessionFactory.openSession();
            empl = (Empleado) session.createQuery("SELECT EMPL FROM Empleado empl WHERE id= " + id).uniqueResult();
        } catch (HibernateException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            session.close();
        }
        return empl;
    }

    @Override
    public List<Empleado> showAll() {
        Session session = null;
        List<Empleado> empleado = null;
        try {
            session = sessionFactory.openSession();
            Query query = session.createQuery("SELECT EMPL FROM Empleado empl");
            empleado = query.list();

        } catch (HibernateException ex) {
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return empleado;

    }

    @Override
    public boolean delete(long id) {
        result = false;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Empleado empleado = (Empleado) session.get(Empleado.class, id);
            session.delete(empleado);
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

    public void saludo(){
        System.out.println("hola");
    }
}
