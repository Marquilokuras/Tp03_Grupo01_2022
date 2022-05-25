package ar.edu.unju.edm.controller;

import javax.validation.Valid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.until.ListaUsuario;

@Controller
public class UsuarioController {
	private static final Log MARCOS=LogFactory.getLog(UsuarioController.class); //.getLog(UsuarioController.class);//constante con mayuscula 
	
	@Autowired
	Usuario nuevoUsuario;
	
	@Autowired
	ListaUsuario lista;
	
	@GetMapping("/otroUsuario")//entra
	public ModelAndView addUser() {
		ModelAndView vista = new ModelAndView("cargarUsuario");//pasa nombre de la lista a pasar
		//vista.addObject("nuevoUsuario");
		vista.addObject("usuario", nuevoUsuario);
		vista.addObject("band", "false");
		return vista;
	}
	
	@PostMapping("/guardarusuario")//se recibe
	public String saveUser(@Valid  @ModelAttribute ("usuario") Usuario usuarioparaguardar, BindingResult resultado, Model model) { //del modelo viene 1 atributo llamado usuario y lo agarra le indica el tipo y un nombre 
		MARCOS.info("Ingresando al metodo guardar Usuario: "+ usuarioparaguardar.getFechaNacimiento() );
		if(resultado.hasErrors()) {
			MARCOS.fatal("Error de validacion");
			model.addAttribute("usuario",usuarioparaguardar);
			return "cargarUsuario";
		}
		lista.getListado().add(usuarioparaguardar); //el user se guarda en listado
		MARCOS.error("Tamaño del Listado: " + lista.getListado().size());
		return "redirect:/otroUsuario";
	}
	
	@GetMapping("/listadoUsuario")
	public ModelAndView showUser() {
		ModelAndView vista = new ModelAndView("listadoUsuario");
		
		vista.addObject("listaUsuario", lista.getListado());
		return vista;
	}
	
	@GetMapping("/editarUsuario/{dni}")
	public ModelAndView editUser(@PathVariable(name="dni")Long dni) {
		Usuario usuarioEncontrado = new Usuario();
		for(int i=0;i<lista.getListado().size();i++) {
			if(lista.getListado().get(i).getDni().equals(dni)) {
				usuarioEncontrado = lista.getListado().get(i);
			}
		};
		MARCOS.fatal("Error de entrada"+ usuarioEncontrado.getDni());
		ModelAndView encontrado = new ModelAndView("cargarUsuario");
		
		encontrado.addObject("usuario", usuarioEncontrado);
		encontrado.addObject("band", "true");
		return encontrado;
	}
	
	@PostMapping("/modificarUsuario")//se recibe
	public String modUser(@Valid  @ModelAttribute ("usuario") Usuario usuarioparamodificar, BindingResult resultado, Model model) { //del modelo viene 1 atributo llamado usuario y lo agarra le indica el tipo y un nombre 
		MARCOS.info("Ingresando al metodo guardar Usuario: "+ usuarioparamodificar.getFechaNacimiento() );
		if(resultado.hasErrors()) {
			MARCOS.fatal("Error de validacion");
			model.addAttribute("usuario",usuarioparamodificar);
			return "cargarUsuario";
		}
		for(int i=0;i<lista.getListado().size();i++) {
			if(lista.getListado().get(i).getDni().equals(usuarioparamodificar.getDni())) {
				lista.getListado().set(i, usuarioparamodificar);
			}
		};
					
		MARCOS.error("Tamaño del Listado: " + lista.getListado().size());
		return "redirect:/listadoUsuario";
	}
	
	@GetMapping("/eliminarUsuario/{dni}")
	public ModelAndView deleteUser(@PathVariable(name="dni")Long dni) {
		Usuario usuarioEncontrado = new Usuario();
		for(int i=0;i<lista.getListado().size();i++) {
			if(lista.getListado().get(i).getDni().equals(dni)) {
				usuarioEncontrado = lista.getListado().remove(i);
			}
		};
		MARCOS.fatal("Error de entrada"+ usuarioEncontrado.getDni());
		ModelAndView encontrado = new ModelAndView("cargarUsuario");
		
		encontrado.addObject("usuario", usuarioEncontrado);
		encontrado.addObject("band", "true");
		return encontrado;
	}
	
	@PostMapping("/sacarUsuario")//se recibe
	public String sacarUser(@Valid  @ModelAttribute ("usuario") Usuario usuarioparasacar, BindingResult resultado, Model model) { //del modelo viene 1 atributo llamado usuario y lo agarra le indica el tipo y un nombre 
		MARCOS.info("Ingresando al metodo guardar Usuario: "+ usuarioparasacar.getFechaNacimiento() );
		if(resultado.hasErrors()) {
			MARCOS.fatal("Error de validacion");
			model.addAttribute("usuario",usuarioparasacar);
			return "cargarUsuario";
		}
		for(int i=0;i<lista.getListado().size();i++) {
			if(lista.getListado().get(i).getDni().equals(usuarioparasacar.getDni())) {
				lista.getListado().remove(i);
			}
		};
					
		MARCOS.error("Tamaño del Listado: " + lista.getListado().size());
		return "redirect:/listadoUsuario";
	}
}
