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
import ar.edu.unju.edm.service.IUsuarioService;
import ar.edu.unju.edm.until.ListaUsuario;

@Controller
public class UsuarioController {

	private static final Log 
	EMILIO=LogFactory.getLog(UsuarioController.class);//constante con mayuscula

	@Autowired
	Usuario nuevoUsuario;
	
	@Autowired
	IUsuarioService serviceUsuario;
	
	@GetMapping("/otroUsuario")//entra
	public ModelAndView addUser() {
		ModelAndView vista = new ModelAndView("cargarUsuario");//pasa nombre de la lista a pasar
		//vista.addObject("nuevoUsuario");
		vista.addObject("usuario", nuevoUsuario);
		vista.addObject("band", "false");
		return vista;
	}
	
	@PostMapping("/guardarusuario")
	public String saveUser(@Valid @ModelAttribute ("usuario") Usuario usuarioparaguardar, BindingResult resultado, Model model) { //del modelo viene 1 atributo llamado usuario y lo agarra le indica el tipo y un nombre 
	
		EMILIO.info("Ingresando al metodo guardar. Usuario: "+usuarioparaguardar.getFechaNacimiento());
		
		if(resultado.hasErrors()) {
			EMILIO.fatal("Error de Validacion");
			model.addAttribute("usuario",usuarioparaguardar);
			return "cargarUsuario";
		}
		try { //controla si algo se ejecuta bien
			serviceUsuario.guardarUsuario(usuarioparaguardar);
		}catch(Exception error){ //si no sale por aqui
			EMILIO.error("No se pudo guardar el usuario");
		}
		return "redirect:/otroUsuario";
	}
	
	@GetMapping("/listadoUsuario")
	public ModelAndView showUser() {
		ModelAndView vista = new ModelAndView("listadoUsuario");
		vista.addObject("listaUsuario", serviceUsuario.mostrarUsuarios());
		return vista;
	}
	
	@GetMapping("/eliminarUsuario/{dni}")
	public String deleteUser(@PathVariable(name="dni")Long dni) {
		try {
			serviceUsuario.eliminarUsuario(dni);
		}catch(Exception error){
			EMILIO.error("No se pudo elminar el usuario");
		}
		return "redirect:/listadoUsuario";
	}
	
	/*@GetMapping("/editarUsuario/{dni}")
	
	public ModelAndView editUser(@PathVariable(name="dni")Long dni) {
		Usuario usuarioEncontrado = new Usuario();
		for(int i=0;i<lista.getListado().size();i++) {
			if(lista.getListado().get(i).getDni().equals(usuarioEncontrado)) {
				usuarioEncontrado = lista.getListado().get(i);
			}
		};
		EMILIO.fatal("Error de entrada"+usuarioEncontrado.getApellido());
		}
			
		return "redirect:/listadoUsuario";
	}
	
	/*
	@PostMapping("/modificarUsuario")
	public String modUser(@Valid @ModelAttribute ("usuario") Usuario usuarioparamodificar, BindingResult resultado, Model model) { //del modelo viene 1 atributo llamado usuario y lo agarra le indica el tipo y un nombre 
		lista.getListado().add(usuarioparamodificar); //el user se guarda en listado
		EMILIO.info("Ingresando al metodo guardar. Usuario: "+usuarioparamodificar.getFechaNacimiento());
		
		if(resultado.hasErrors()) {
			EMILIO.fatal("Error de Validacion");
			model.addAttribute("usuario",usuarioparamodificar);
			return "cargarUsuario";
		}
		for(int i=0;i<lista.getListado().size();i++) {
			if(lista.getListado().get(i).getDni().equals(usuarioparamodificar.getDni())) {
				lista.getListado().set(i, usuarioparamodificar);
			}
		};

		
		lista.getListado().add(usuarioparamodificar); //el user se guarda en listado
		
		EMILIO.error("Tamaño del Listado: " + lista.getListado().size());
		return "redirect: /listadoUsuario";
	}
	
	/*@PostMapping("/borrarUsuario")
	public String elimUser(@Valid @ModelAttribute ("usuario") Usuario usuarioparaeliminar, BindingResult resultado, Model model) { //del modelo viene 1 atributo llamado usuario y lo agarra le indica el tipo y un nombre 
		lista.getListado().add(usuarioparaeliminar); //el user se guarda en listado
		EMILIO.info("Ingresando al metodo guardar. Usuario: "+usuarioparaeliminar.getFechaNacimiento());
		
		if(resultado.hasErrors()) {
			EMILIO.fatal("Error de Validacion");
			model.addAttribute("usuario",usuarioparaeliminar);
			return "cargarUsuario";
		}
		for(int i=0;i<lista.getListado().size();i++) {
			if(lista.getListado().get(i).getDni().equals(usuarioparaeliminar.getDni())) {
				lista.getListado().set(i, usuarioparaeliminar);
			}
		};
		
		lista.getListado().add(usuarioparaeliminar); //el user se guarda en listado
		
		EMILIO.error("Tamaño del Listado: " + lista.getListado().size());
		return "redirect:/listadoUsuario";
	}
	
}

					
		MARCOS.error("Tamaño del Listado: " + lista.getListado().size());
		return "redirect:/listadoUsuario";
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
¨*/
}

