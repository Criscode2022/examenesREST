package com.examenes.modelo;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "Respuestas")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_respuesta")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_pregunta")
    @JsonBackReference
    private Pregunta pregunta;

    @Column(name = "respuesta", nullable = false)
    private String texto;

    @Column(name = "es_correcta", nullable = false)
    private String esCorrecta;

    public Respuesta(Integer id, Pregunta pregunta, String texto, String esCorrecta) {
        this.id = id;
        this.pregunta = pregunta;
        this.texto = texto;
        this.esCorrecta = esCorrecta;
    }
	
	public Respuesta() {};


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Pregunta getPregunta() {
		return pregunta;
	}

	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getEsCorrecta() {
		return esCorrecta;
	}

	public void setEsCorrecta(String esCorrecta) {
		this.esCorrecta = esCorrecta;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Respuesta other = (Respuesta) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Respuesta [id=" + id + ", pregunta=" + pregunta + ", texto=" + texto + ", esCorrecta=" + esCorrecta
				+ ", toString()=" + super.toString() + "]";
	}
    
    

}
