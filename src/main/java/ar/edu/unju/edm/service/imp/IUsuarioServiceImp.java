package ar.edu.unju.edm.service.imp;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.unju.edm.controller.UsuarioController;
import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.service.IUsuarioService;
import ar.edu.unju.edm.until.ListaUsuario;

@Service
public class IUsuarioServiceImp implements IUsuarioService {

	private static final Log MARCOS = LogFactory.getLog(UsuarioController.class);
	
	@Autowired
	ListaUsuario lista;
	
	@Override
	public void guardarUsuario(Usuario usuarioparaguardar) {
		// TODO Auto-generated method stub
		usuarioparaguardar.setEstado(true);
		lista.getListado().add(usuarioparaguardar);  //el user se guarda en listado
	}

	@Override
	public List<Usuario> mostrarUsuarios() {
		// TODO Auto-generated method stub
		List<Usuario> auxiliar = new ArrayList<>();
		
		for(int i=0;i<lista.getListado().size();i++) {
			if(lista.getListado().get(i).getEstado()==true) {
				auxiliar.add(lista.getListado().get(i));
			}
		}
		return auxiliar;
	}
	
	@Override
	public void eliminarUsuario(Long dni) {
		// TODO Auto-generated method stub
		Usuario auxiliar = new Usuario();
		auxiliar = buscarUsuario(dni);
		auxiliar.setEstado(false);
	}
	
	@Override
	public void modificarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		usuario.setEstado(true);
		for(int i = 0; i < lista.getListado().size(); i++) {
			if(lista.getListado().get(i).getDni().equals(usuario.getDni())) {
				MARCOS.error("Encontrado");
				lista.getListado().set(i, usuario);
			}
		}
	}

	@Override
	public Usuario buscarUsuario(Long id) {
		// TODO Auto-generated method stub´
		Usuario auxiliar = new Usuario();
		
		for(int i=0;i<lista.getListado().size();i++) {
			if(lista.getListado().get(i).getDni().equals(id)) {
				auxiliar = lista.getListado().get(i);
			}
		}
		return auxiliar;
	}
}