package com.examenes.dao;

import java.util.List;

import com.examenes.modelo.Respuesta;

public interface RespuestaDAO {

    Respuesta findById(int id);

    List<Respuesta> findAll();

    List<Respuesta> findByPreguntaId(int preguntaId);

    Respuesta save(Respuesta respuesta);

    boolean update(Respuesta respuesta);

    boolean delete(int id);
}
