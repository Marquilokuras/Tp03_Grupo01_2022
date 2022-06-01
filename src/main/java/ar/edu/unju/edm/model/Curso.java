package ar.edu.unju.edm.model;

import java.time.LocalDate;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
public class Curso {
	@NotEmpty //vacio string 
	@Size (min=5, max=30, message="El nombre del curso de contener entre 5 a 30 caracteres")
	private String curso;
	@NotEmpty //vacio string
	private String docente;
	@NotEmpty //vacio string
	private String email;
	@NotEmpty //vacio string
	private String contrasena;
	@NotNull
	private int cantidadHoras;
	@NotNull //para numeros
	@Min(value=1000000,message="El ID debe ser mayor que millon")
	@Max(value=99999999,message="El ID debe ser menor que 9999999")
	private Long id;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaNacimiento;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public int getCantidadHoras() {
		return cantidadHoras;
	}

	public void setCantidadHoras(int cantidadHoras) {
		this.cantidadHoras = cantidadHoras;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
}
