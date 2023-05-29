package com.examenes.dao;

import java.util.List;

import com.examenes.modelo.Pregunta;

public interface PreguntaDAO {

    Pregunta findById(int id);

    List<Pregunta> findByTema(int temaId);

    Pregunta save(Pregunta pregunta);

    boolean update(Pregunta pregunta);

    boolean delete(int id);

    List<Pregunta> findAll();
    
    List<Pregunta> findAllByTema(int idTema);

}
