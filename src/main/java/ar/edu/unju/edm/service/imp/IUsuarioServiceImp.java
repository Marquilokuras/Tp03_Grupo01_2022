package ar.edu.unju.edm.service.imp;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.unju.edm.controller.UsuarioController;
import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.repository.UsuarioRepository;
import ar.edu.unju.edm.service.IUsuarioService;
import ar.edu.unju.edm.until.ListaUsuario;

@Service
public class IUsuarioServiceImp implements IUsuarioService {

	private static final Log MARCOS = LogFactory.getLog(UsuarioController.class);
	
	@Autowired
	ListaUsuario lista;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Override
	public void guardarUsuario(Usuario usuarioparaguardar) {
		// TODO Auto-generated method stub
		usuarioparaguardar.setEstado(true);
	//	lista.getListado().add(usuarioparaguardar); 
		usuarioRepository.save(usuarioparaguardar);
	}

	@Override
	public List<Usuario> mostrarUsuarios() {
		// TODO Auto-generated method stub
		List<Usuario> auxiliar = new ArrayList<>();
		List<Usuario> auxiliar2 = new ArrayList<>();
		/*
		for(int i=0;i<lista.getListado().size();i++) {
			if(lista.getListado().get(i).getEstado()==true) {
				auxiliar.add(lista.getListado().get(i));
			}
		}*/
		auxiliar=(List<Usuario>) usuarioRepository.findAll();
		for(int i=0;i<auxiliar.size();i++) {
			if(auxiliar.get(i).getEstado()==true) {
				auxiliar2.add(auxiliar.get(i));
			}
		}
		return auxiliar2;
	
	}
	
	@Override
	public void eliminarUsuario(Long dni) throws Exception {
		// TODO Auto-generated method stub
		Usuario auxiliar = new Usuario();
		auxiliar = buscarUsuario(dni) ;
		auxiliar.setEstado(false);
		usuarioRepository.save(auxiliar);
	}
	
	@Override
	public void modificarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		usuario.setEstado(true);
	  /*for(int i = 0; i < lista.getListado().size(); i++) {
			if(lista.getListado().get(i).getDni().equals(usuario.getDni())) {
				MARCOS.error("Encontrado");
				lista.getListado().set(i, usuario);
			}
		}*/
		usuarioRepository.save(usuario);
		
	}

	@Override
	public Usuario buscarUsuario(Long dni) throws Exception {
		// TODO Auto-generated method stub´
		Usuario usuarioEncontrado = new Usuario();
		
		/*for(int i=0;i<lista.getListado().size();i++) {
			if(lista.getListado().get(i).getDni().equals(id)) {
				auxiliar = lista.getListado().get(i);
			}
		}*/
		usuarioEncontrado=usuarioRepository.findById(dni).orElseThrow(()->new Exception("Usuario No Encontrado"));
		return usuarioEncontrado;
	}
}