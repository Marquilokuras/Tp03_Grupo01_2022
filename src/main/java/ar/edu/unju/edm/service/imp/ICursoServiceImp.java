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
		cursoparaguardar.setEstad(true);
		cursoRepository.save(cursoparaguardar);
	}

	@Override
	public List<Curso> mostrarCursos() {
		// TODO Auto-generated method stub
		List<Curso> auxiliar = new ArrayList<>();
		List<Curso> auxiliar2 = new ArrayList<>();
<<<<<<< HEAD
		auxiliar =(List<Curso>) cursoRepository.findAll();
=======
		auxiliar=(List<Curso>)cursoRepository.findAll();
>>>>>>> branch 'master' of https://github.com/Marquilokuras/Tp03_Grupo01_2022.git
		for(int i=0;i<auxiliar.size();i++) {
			if(auxiliar.get(i).getEstad()==true) {
				auxiliar2.add(auxiliar.get(i));
			}
		}
		return auxiliar2;
	
	}
	
	@Override
	public void eliminarUsuario(Long idCurso) throws Exception {
		// TODO Auto-generated method stub
		Curso auxiliar = new Curso();
<<<<<<< HEAD
		auxiliar = buscarCurso(idCurso) ;
		auxiliar.setEstad(false);
=======
		auxiliar = buscarCurso(idCurso);
		auxiliar.setEstado(false);
>>>>>>> branch 'master' of https://github.com/Marquilokuras/Tp03_Grupo01_2022.git
		cursoRepository.save(auxiliar);
	}
	
	@Override
	public void modificarCurso(Curso curso) {
		// TODO Auto-generated method stub
		curso.setEstad(true);
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