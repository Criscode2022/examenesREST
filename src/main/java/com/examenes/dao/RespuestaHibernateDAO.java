package com.examenes.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.examenes.modelo.Respuesta;
import com.examenes.dao.util.HibernateUtil;

public class RespuestaHibernateDAO implements RespuestaDAO {

    @Override
    public Respuesta findById(int id) {
        try (Session session = HibernateUtil.getFactoria().openSession()) {
            return session.get(Respuesta.class, id);
        }
    }

    @Override
    public List<Respuesta> findAll() {
        try (Session session = HibernateUtil.getFactoria().openSession()) {
            Query<Respuesta> query = session.createQuery("FROM Respuesta", Respuesta.class);
            return query.list();
        }
    }

    @Override
    public List<Respuesta> findByPreguntaId(int preguntaId) {
        try (Session session = HibernateUtil.getFactoria().openSession()) {
            Query<Respuesta> query = session.createQuery("FROM Respuesta WHERE pregunta_id = :preguntaId", Respuesta.class);
            query.setParameter("preguntaId", preguntaId);
            return query.list();
        }
    }

    @Override
    public Respuesta save(Respuesta respuesta) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactoria().openSession()) {
            transaction = session.beginTransaction();
            session.save(respuesta);
            transaction.commit();
            return respuesta;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(Respuesta respuesta) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactoria().openSession()) {
            transaction = session.beginTransaction();
            session.update(respuesta);
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
            Respuesta respuesta = session.get(Respuesta.class, id);
            if (respuesta != null) {
                transaction = session.beginTransaction();
                session.delete(respuesta);
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
