package com.examenes.modelo;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Preguntas")
public class Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pregunta")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_tema")
    private Tema tema;

    @Column(name = "pregunta", nullable = false)
    private String texto;

    @Column(name = "dificultad", nullable = false)
    private Integer dificultad;

    @OneToMany(mappedBy = "pregunta", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Respuesta> respuestas;

	public Pregunta(Integer id, Tema tema, String texto, Integer dificultad, List<Respuesta> respuestas) {
		this.id = id;
		this.tema = tema;
		this.texto = texto;
		this.dificultad = dificultad;
		this.respuestas = respuestas;
	}
	
	public Pregunta () {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Integer getDificultad() {
		return dificultad;
	}

	public void setDificultad(Integer dificultad) {
		this.dificultad = dificultad;
	}

	public List<Respuesta> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(List<Respuesta> respuestas) {
		this.respuestas = respuestas;
	}

	@Override
	public String toString() {
		return "Pregunta [id=" + id + ", tema=" + tema + ", texto=" + texto + ", dificultad=" + dificultad
				+ ", respuestas=" + respuestas + ", toString()=" + super.toString() + "]";
	}


    
}