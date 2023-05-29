package com.examenes.servicio;

import com.examenes.dao.*;
import com.examenes.dao.util.HibernateUtil;
import com.examenes.modelo.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;

@Path("/")
public class ExamenesControlador {

    private ExamenesServicio servicio = new ExamenServicioImpl();

    @GET
    @Path("/temas")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tema> obtenerTodosTemas() {
        return servicio.buscarTodosTemas();
    }

    @GET
    @Path("/tema/{idTema}")
    @Produces(MediaType.APPLICATION_JSON)
    public Tema obtenerTemaPorId(@PathParam("idTema") int idTema) {
        return servicio.buscarTemaPorId(idTema);
    }
    
    


    @GET
    @Path("/pregunta/{idPregunta}")
    @Produces(MediaType.APPLICATION_JSON)
    public Pregunta obtenerPreguntaPorId(@PathParam("idPregunta") int idPregunta) {
        return servicio.buscarPreguntaPorId(idPregunta);
    }

    
    @GET
    @Path("/respuesta/{idRespuesta}")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta obtenerRespuestaPorId(@PathParam("idRespuesta") int idRespuesta) {
        return servicio.buscarRespuestaPorId(idRespuesta);
    }

    @POST
    @Path("/tema")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean agregarTema(Tema tema) {
        return servicio.agregarTema(tema);
    }

    @POST
    @Path("/pregunta")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean agregarPregunta(Pregunta pregunta) {
        return servicio.agregarPregunta(pregunta);
    }

    @POST
    @Path("/respuesta")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean agregarRespuesta(Respuesta respuesta) {
        return servicio.agregarRespuesta(respuesta);
    }

    @PUT
    @Path("/tema")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean modificarTema(Tema tema) {
        return servicio.modificarTema(tema);
    }

    @PUT
    @Path("/pregunta")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean modificarPregunta(Pregunta pregunta) {
        return servicio.modificarPregunta(pregunta);
    }

    @PUT
    @Path("/respuesta")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean modificarRespuesta(Respuesta respuesta) {
        return servicio.modificarRespuesta(respuesta);
    }

    @DELETE
    @Path("/tema/{idTema}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean eliminarTema(@PathParam("idTema") int idTema) {
        return servicio.eliminarTema(idTema);
    }

    @DELETE
    @Path("/pregunta/{idPregunta}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean eliminarPregunta(@PathParam("idPregunta") int idPregunta) {
        return servicio.eliminarPregunta(idPregunta);
    }

    @DELETE
    @Path("/respuesta/{idRespuesta}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean eliminarRespuesta(@PathParam("idRespuesta") int idRespuesta) {
        return servicio.eliminarRespuesta(idRespuesta);
    }

    @GET
    @Path("/tema/{idTema}/preguntas")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pregunta> obtenerTodasPreguntasPorTema(@PathParam("idTema") int idTema) {
        return servicio.buscarTodasPreguntasPorTema(idTema);
    }
    
    @GET
    @Path("/preguntas")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pregunta> buscarTodasPreguntas() {
        return servicio.buscarTodasPreguntas();
    }
    
    
        @POST
        @Path("/examen")
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public Examen crearExamen(Examen examen) {
            List<String> nombresTemas = examen.getNombresTemas();
            int dificultad = examen.getDificultad();
            int limite = examen.getLimite();

            // Selección de las preguntas pedidas por el usuario
            for (String nombreTema : nombresTemas) {
                Tema tema = buscarTemaPorNombre(nombreTema);
                List<Pregunta> preguntasSeleccionadas = buscarPreguntasPorTemaYDificultad(tema.getId(), dificultad, limite);

                // Añadir las preguntas al examen
                for (Pregunta pregunta : preguntasSeleccionadas) {
                    Hibernate.initialize(pregunta.getRespuestas());
                    examen.añadirPregunta(pregunta);
                }

            }

            // Implememtar método para desordenar preguntas y respuestas
            examen.desordenar();

            return examen;
        }

        private Tema buscarTemaPorNombre(String nombreTema) {
            Session session = HibernateUtil.getFactoria().openSession();
            String hql = "FROM Tema T WHERE T.nombre = :nombreTema";
            Query query = session.createQuery(hql);
            query.setParameter("nombreTema", nombreTema);
            Tema tema = (Tema) query.uniqueResult();
            session.close();
            return tema;
        }

        private List<Pregunta> buscarPreguntasPorTemaYDificultad(int idTema, int dificultad, int limite) {
            Session session = HibernateUtil.getFactoria().openSession();
            String hql = "FROM Pregunta P WHERE P.tema.id = :idTema AND P.dificultad = :dificultad ORDER BY RAND()";
            Query query = session.createQuery(hql);
            query.setParameter("idTema", idTema);
            query.setParameter("dificultad", dificultad);
            query.setMaxResults(limite);
            List<Pregunta> preguntas = query.list();
            session.close();
            return preguntas;
        }
    }


    
   
