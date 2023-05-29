package com.examenes.dao;

import java.util.List;

import com.examenes.modelo.Tema;

public interface TemaDAO {

    Tema findById(int id);

    List<Tema> findAll();
    
    Tema save(Tema tema);

    boolean update(Tema tema);

    boolean delete(int id);
}

