package com.examenes.modelo;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Examen {

    private List<Pregunta> preguntas;
    private int dificultad;
    private int limite;
    private List<String> nombresTemas;

    public List<String> getNombresTemas() {
        return nombresTemas;
    }

    public void setNombresTemas(List<String> nombresTemas) {
        this.nombresTemas = nombresTemas;
    }

   

    public Examen(List<Pregunta> preguntas, int dificultad, int limite, List<String> nombresTemas) {
		this.preguntas = preguntas;
		this.dificultad = dificultad;
		this.limite = limite;
		this.nombresTemas = nombresTemas;
	}

	public Examen() {
        this.preguntas = new ArrayList<>();
    }
    
    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public int getDificultad() {
        return dificultad;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }

    public int getLimite() {
        return limite;
    }

    public void setLimite(int limite) {
        this.limite = limite;
    }

    // Método para agregar una pregunta al examen
    
    public void añadirPregunta(Pregunta pregunta) {
        if (preguntas.size() < limite) {
            this.preguntas.add(pregunta);
        }
    }


    // Método para desordenar las preguntas y las respuestas de cada pregunta
    
    public void desordenar() {
        Collections.shuffle(this.preguntas);

        for (Pregunta pregunta : this.preguntas) {
            Collections.shuffle(pregunta.getRespuestas());
        }
    }
}
