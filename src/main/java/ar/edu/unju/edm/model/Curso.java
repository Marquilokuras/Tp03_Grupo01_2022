package ar.edu.unju.edm.model;


import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
@Entity
public class Curso {
	@NotEmpty //vacio string 
	@Size (min=5, max=30, message="El nombre del curso de contener entre 5 a 30 caracteres")
	private String curso;
	@NotEmpty //vacio string
	private String docente;
	@Column(name="descripcion")
	private String descripcion;
	@NotNull
	@Min(value=0,message="El duracion debe ser mayor que 0")
	@Max(value=8760,message="El duracion debe ser menor que 8760")
	private Integer duracion;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idCurso;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaInicio;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaFinal;
	//@NotNull
	//@Min(value=1,message="El duracion debe ser mayor que 1")
	//@Max(value=99999,message="El duracion debe ser menor que 99999")
	//private Long cupo;
	//@NotNull
	//@Min(value=1,message="El duracion debe ser mayor que 1")
	//@Max(value=99999,message="El duracion debe ser menor que 99999")
	//private Double costo;
	//private Integer valoracion;
	private Boolean estado;
	
	public Curso() {
		// TODO Auto-generated constructor stub
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getDocente() {
		return docente;
	}

	public void setDocente(String docente) {
		this.docente = docente;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public Long getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(Long idCurso) {
		this.idCurso = idCurso;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(LocalDate fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	/*public Long getCupo() {
		return cupo;
	}

	public void setCupo(Long cupo) {
		this.cupo = cupo;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public Integer getValoracion() {
		return valoracion;
	}

	public void setValoracion(Integer valoracion) {
		this.valoracion = valoracion;
	}*/

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
}
