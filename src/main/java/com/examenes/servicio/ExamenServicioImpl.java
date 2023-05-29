package com.examenes.servicio;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.examenes.dao.TemaDAO;
import com.examenes.dao.PreguntaDAO;
import com.examenes.dao.RespuestaDAO;
import com.examenes.dao.TemaHibernateDAO;
import com.examenes.dao.util.HibernateUtil;
import com.examenes.dao.PreguntaHibernateDAO;
import com.examenes.dao.RespuestaHibernateDAO;
import com.examenes.modelo.Pregunta;
import com.examenes.modelo.Respuesta;
import com.examenes.modelo.Tema;

public class ExamenServicioImpl implements ExamenesServicio {

    private TemaDAO temaDao = new TemaHibernateDAO();
    private PreguntaDAO preguntaDao = new PreguntaHibernateDAO();
    private RespuestaDAO respuestaDao = new RespuestaHibernateDAO();

    @Override
    public Tema buscarTemaPorId(int idTema) {
        return temaDao.findById(idTema);
    }
    
    @Override
    public Pregunta buscarPreguntaPorId(int idPregunta) {
        return preguntaDao.findById(idPregunta);
    }

    @Override
    public Respuesta buscarRespuestaPorId(int idRespuesta) {
        return respuestaDao.findById(idRespuesta);
    }

    @Override
    public List<Tema> buscarTodosTemas() {
        return temaDao.findAll();
    }
  


    @Override
    public List<Respuesta> buscarRespuestasPorPregunta(int idPregunta) {
        return respuestaDao.findByPreguntaId(idPregunta);
    }

    @Override
    public boolean agregarTema(Tema tema) {
        return temaDao.save(tema) != null;
    }

    @Override
    public boolean agregarPregunta(Pregunta pregunta) {
        return preguntaDao.save(pregunta) != null;
    }

    @Override
    public boolean agregarRespuesta(Respuesta respuesta) {
        return respuestaDao.save(respuesta) != null;
    }

    @Override
    public boolean modificarTema(Tema tema) {
        return temaDao.update(tema);
    }

    @Override
    public boolean modificarPregunta(Pregunta pregunta) {
        return preguntaDao.update(pregunta);
    }

    @Override
    public boolean modificarRespuesta(Respuesta respuesta) {
        return respuestaDao.update(respuesta);
    }

    @Override
    public boolean eliminarTema(int idTema) {
        return temaDao.delete(idTema);
    }

    @Override
    public boolean eliminarPregunta(int idPregunta) {
        return preguntaDao.delete(idPregunta);
    }

    @Override
    public boolean eliminarRespuesta(int idRespuesta) {
        return respuestaDao.delete(idRespuesta);
    }


	@Override
	public List<Pregunta> buscarTodasPreguntasPorTema(int idTema) {
	    return preguntaDao.findAllByTema(idTema);
	}

	@Override
	public List<Pregunta> buscarTodasPreguntas() {
		return preguntaDao.findAll();
	}


}

