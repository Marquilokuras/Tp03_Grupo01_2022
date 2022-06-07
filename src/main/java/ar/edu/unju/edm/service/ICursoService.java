package ar.edu.unju.edm.service;

import java.util.List;
import org.springframework.stereotype.Service;
import ar.edu.unju.edm.model.Curso;

@Service
public interface ICursoService {

	public void guardarCursos(Curso curso);
	public List<Curso>mostrarCursos();
	public void eliminarCurso(Long idCurso) throws Exception;
	public void modificarCurso(Curso curso);
	public Curso buscarCurso(Long idCurso) throws Exception;
}