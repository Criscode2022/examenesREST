package com.examenes.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.examenes.modelo.Pregunta;
import com.examenes.dao.util.HibernateUtil;

public class PreguntaHibernateDAO implements PreguntaDAO {

    @Override
    public Pregunta findById(int id) {
        try (Session session = HibernateUtil.getFactoria().openSession()) {
            return session.get(Pregunta.class, id);
        }
    }

    @Override
    public List<Pregunta> findByTema(int temaId) {
        try (Session session = HibernateUtil.getFactoria().openSession()) {
            Query<Pregunta> query = session.createQuery("FROM Pregunta WHERE tema.id = :temaId", Pregunta.class);
            query.setParameter("temaId", temaId);
            return query.list();
        }
    }

    @Override
    public Pregunta save(Pregunta pregunta) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactoria().openSession()) {
            transaction = session.beginTransaction();
            session.save(pregunta);
            transaction.commit();
            return pregunta;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(Pregunta pregunta) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactoria().openSession()) {
            transaction = session.beginTransaction();
            session.update(pregunta);
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
            Pregunta pregunta = session.get(Pregunta.class, id);
            if (pregunta != null) {
                transaction = session.beginTransaction();
                session.delete(pregunta);
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

   

    @Override
    public List<Pregunta> findAllByTema(int idTema) {
        try (Session session = HibernateUtil.getFactoria().openSession()) {
            Query<Pregunta> query = session.createQuery("FROM Pregunta WHERE tema.id = :idTema", Pregunta.class);
            query.setParameter("idTema", idTema);
            List<Pregunta> preguntas = query.list();

            for (Pregunta pregunta : preguntas) {
                Hibernate.initialize(pregunta.getRespuestas());
            }

            return preguntas;
        }
    }
    
    @Override
    public List<Pregunta> findAll() {
        try (Session session = HibernateUtil.getFactoria().openSession()) {
            Query<Pregunta> query = session.createQuery("FROM Pregunta", Pregunta.class);
            List<Pregunta> preguntas = query.list();

            for (Pregunta pregunta : preguntas) {
                Hibernate.initialize(pregunta.getRespuestas());
            }

            return preguntas;
        }
    }


}
