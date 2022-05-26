package ar.edu.unju.edm.model;

import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
public class Usuario {
	private String nombre;
	private String apellido;
	private String email;
	@NotEmpty //para string
	private String contrasena;
	private Boolean estado;
	@NotNull //para numeros
	@Min(value=1000000,message="El DNI debe ser mayor que 1.000.000")
	@Max(value=99999999,message="El DNI debe ser menor que 99.999.999")
	private Long dni;
	@DateTimeFormat(pattern = "yyyy-MM-dd") //faltaba esto para que permita entrar la fechadenac
	private LocalDate fechaNacimiento;
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	
	
	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
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
	
	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	
	
	
	
}
