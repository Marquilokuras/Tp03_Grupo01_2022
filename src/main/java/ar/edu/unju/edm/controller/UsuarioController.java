package ar.edu.unju.edm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.until.ListaUsuario;

@Controller
public class UsuarioController {
	@Autowired
	Usuario nuevoUsuario;
	
	@Autowired
	ListaUsuario lista;
	
	@GetMapping("/otroUsuario")
	public ModelAndView addUser() {
		ModelAndView vista = new ModelAndView("cargarUsuario");//pasa nombre de la lista a pasar
		//vista.addObject("nuevoUsuario");
		vista.addObject("usuario", nuevoUsuario);
		return vista;
	}
	
	@PostMapping("/guardarusuario")
	public String saveUser(@ModelAttribute ("usuario") Usuario usuarioparaguardar) { //del modelo viene 1 atributo llamado usuario y lo agarra le indica el tipo y un nombre 
		lista.getListado().add(usuarioparaguardar); //el user se guarda en listado
		System.out.println("Tamaño del Listado: " + lista.getListado().size());
		return "redirect:/otroUsuario";
	}
}
