package ar.edu.unju.edm.service.imp;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.unju.edm.model.Curso;
import ar.edu.unju.edm.repository.CursoRepository;
import ar.edu.unju.edm.service.ICursoService;
import ar.edu.unju.edm.until.ListaCursos;

@Service
public class ICursoServiceImp implements ICursoService{
	
	@Autowired
	ListaCursos lista;
	
	@Autowired
	CursoRepository cursoRepository;
	
	@Override
	public void guardarCursos(Curso cursoparaguardar) {
		// TODO Auto-generated method stub
		cursoparaguardar.setEstado(true);
		cursoRepository.save(cursoparaguardar);
	}

	@Override
	public List<Curso> mostrarCursos() {
		// TODO Auto-generated method stub
		List<Curso> auxiliar = new ArrayList<>();
		List<Curso> auxiliar2 = new ArrayList<>();
		auxiliar=(List<Curso>)cursoRepository.findAll();
		for(int i=0;i<auxiliar.size();i++) {
			if(auxiliar.get(i).getEstado()==true) {
				auxiliar2.add(auxiliar.get(i));
			}
		}
		return auxiliar2;
	
	}
	
	@Override
	public void eliminarCurso(Long idCurso) throws Exception {
		// TODO Auto-generated method stub
		Curso auxiliar = new Curso();
		auxiliar = buscarCurso(idCurso);
		auxiliar.setEstado(false);
		cursoRepository.save(auxiliar);
	}
	
	@Override
	public void modificarCurso(Curso curso) {
		// TODO Auto-generated method stub
		curso.setEstado(true);
		cursoRepository.save(curso);
	}

	@Override
	public Curso buscarCurso(Long idCurso) throws Exception {
		// TODO Auto-generated method stub´
		Curso cursoEncontrado = new Curso();
		cursoEncontrado=cursoRepository.findById(idCurso).orElseThrow(()->new Exception("Curso No Encontrado"));
		return cursoEncontrado;
	}

	
}