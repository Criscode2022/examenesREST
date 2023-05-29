package com.examenes.servicio;

import java.util.List;

import com.examenes.modelo.Tema;
import com.examenes.modelo.Pregunta;
import com.examenes.modelo.Respuesta;

public interface ExamenesServicio {

    public Tema buscarTemaPorId(int idTema);

    public Pregunta buscarPreguntaPorId(int idPregunta);

    public Respuesta buscarRespuestaPorId(int idRespuesta);

    public List<Tema> buscarTodosTemas();

    public List<Respuesta> buscarRespuestasPorPregunta(int idPregunta);

    public boolean agregarTema(Tema tema);

    public boolean agregarPregunta(Pregunta pregunta);

    public boolean agregarRespuesta(Respuesta respuesta);

    public boolean modificarTema(Tema tema);

    public boolean modificarPregunta(Pregunta pregunta);

    public boolean modificarRespuesta(Respuesta respuesta);

    public boolean eliminarTema(int idTema);

    public boolean eliminarPregunta(int idPregunta);

    public boolean eliminarRespuesta(int idRespuesta);
    
    List<Pregunta> buscarTodasPreguntasPorTema(int idTema);
    
    List<Pregunta> buscarTodasPreguntas ();


}
