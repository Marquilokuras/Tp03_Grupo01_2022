package ar.edu.unju.edm.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Curso;

@Service
public interface ICursoService {

	public List<Curso> mostrarCursos();
	public void guardarCursos(@Valid Curso cursoparaguardar);
	public void eliminarUsuario(Long dni) throws Exception;
	public void modificarCurso(Curso curso);
	public Curso buscarCurso(Long idCurso) throws Exception;
 

}