package com.examenes.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.examenes.modelo.Tema;
import com.examenes.dao.util.HibernateUtil;

public class TemaHibernateDAO implements TemaDAO {

    @Override
    public Tema findById(int id) {
        try (Session session = HibernateUtil.getFactoria().openSession()) {
            return session.get(Tema.class, id);
        }
    }

    
    
    
    @Override
    public List<Tema> findAll() {
        try (Session session = HibernateUtil.getFactoria().openSession()) {
            Query<Tema> query = session.createQuery("FROM Tema", Tema.class);
            return query.list();
        }
    }





    @Override
    public Tema save(Tema tema) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactoria().openSession()) {
            transaction = session.beginTransaction();
            session.save(tema);
            transaction.commit();
            return tema;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(Tema tema) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactoria().openSession()) {
            transaction = session.beginTransaction();
            session.update(tema);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactoria().openSession()) {
            Tema tema = session.get(Tema.class, id);
            if (tema != null) {
                transaction = session.beginTransaction();
                session.delete(tema);
                transaction.commit();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
}
