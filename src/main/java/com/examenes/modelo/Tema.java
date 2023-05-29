package com.examenes.modelo;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import com.examenes.modelo.Pregunta;


@Entity
@Table(name = "Temas")
public class Tema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tema")
    private Integer id;

    @Column(name = "tema", nullable = false)
    private String nombre;
    
    @JsonIgnore
    @OneToMany(mappedBy = "tema", cascade = CascadeType.ALL)
    private List<Pregunta> preguntas;

	public Tema(Integer id, String nombre, List<Pregunta> preguntas) {
		this.id = id;
		this.nombre = nombre;
		this.preguntas = preguntas;
	}
	
	public Tema() {};
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Pregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
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
		Tema other = (Tema) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Tema [id=" + id + ", nombre=" + nombre + ", toString()=" + super.toString() + "]";
	}

    
    
    
}
