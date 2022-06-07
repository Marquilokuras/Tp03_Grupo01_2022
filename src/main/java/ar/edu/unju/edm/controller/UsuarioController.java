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

@Controller
public class UsuarioController {

	private static final Log EMILIO=LogFactory.getLog(UsuarioController.class);//constante con mayuscula

	@Autowired
	Usuario nuevoUsuario;
	
	@Autowired
	IUsuarioService serviceUsuario;
	
	@GetMapping("/otroUsuario")//entra
	public ModelAndView addUser() {
		EMILIO.info("Ingresando al metodo:bbbb ");
		ModelAndView vista = new ModelAndView("cargarUsuario");//pasa nombre de la lista a pasar
		//vista.addObject("nuevoUsuario");
		vista.addObject("usuario", nuevoUsuario);
		vista.addObject("editMode", false);
		return vista;
	}
	
	@PostMapping("/guardarusuario")
	public String saveUser(@Valid @ModelAttribute("usuario") Usuario usuarioparaguardar, BindingResult resultado, Model model) { //del modelo viene 1 atributo llamado usuario y lo agarra le indica el tipo y un nombre 
	
		EMILIO.info("Ingresando al metodo guardar. Usuario: "+usuarioparaguardar.getFechaNacimiento());
		
		if(resultado.hasErrors()) {
			EMILIO.fatal("Error de Validacion");
			model.addAttribute("usuario",usuarioparaguardar);
			return "cargarUsuario";
		}
		try { //controla si algo se ejecuta bien
			serviceUsuario.guardarUsuario(usuarioparaguardar);
		}catch(Exception error){ //si no sale por aqui
			model.addAttribute("formUsuarioErrorMessage", error.getMessage());
			model.addAttribute("usuario",usuarioparaguardar);
			EMILIO.error("No se pudo guardar el usuario");
			return "cargarUsuario";
		}
		model.addAttribute("formUsuarioErrorMessage", "Usuario Guardado Correctamente");
		model.addAttribute("usuario", nuevoUsuario);
		return "cargarUsuario";
	}
	
	@GetMapping("/listadoUsuario")
	public ModelAndView showUser() {
		ModelAndView vista = new ModelAndView("listadoUsuario");
		vista.addObject("listaUsuario", serviceUsuario.mostrarUsuarios());
		return vista;
	}
	
	@GetMapping("/eliminarUsuario/{dni}")
	public String deleteUser(@PathVariable(name="dni")Long dni, Model model) {
		try {
			serviceUsuario.eliminarUsuario(dni);
		}catch(Exception error){
			EMILIO.error("No se pudo eliminar el usuario");
			model.addAttribute("formUsuarioErrorMessage", error.getMessage());
			return "redirect:/otroUsuario";
		}
		return "redirect:/listadoUsuario";
	}
	
	
	@GetMapping("/editarUsuario/{dni}")
	//public ModelAndView ObtenerFormularioEditarUsuario(Model model, @PathVariable(name="dni")Long dni) throws Exception {
	public ModelAndView edituser(Model model,@PathVariable (name="dni") Long dni)throws Exception {	
	Usuario usuarioEncontrado = new Usuario();
		try {
			usuarioEncontrado = serviceUsuario.buscarUsuario(dni);
		}catch(Exception error){
			model.addAttribute("formUsuarioErrorMessage", error.getMessage());
		}
		ModelAndView modelView = new ModelAndView("cargarUsuario");
		modelView.addObject("usuario", usuarioEncontrado);
		EMILIO.error("saliendo del metodo :AAAAAA"+ usuarioEncontrado.getDni());
		//EMILIO.error("usuario: "+ usuarioEncontrado.getDni());
		modelView.addObject("editMode", true);
		return modelView;
	}
	
	@PostMapping("/editarUsuario")
	public ModelAndView postEditarUsuario(@ModelAttribute ("usuario") Usuario usuarioparamodificar) {  
		serviceUsuario.modificarUsuario(usuarioparamodificar);
		ModelAndView vista = new ModelAndView("listadoUsuario");
		vista.addObject("listaUsuario", serviceUsuario.mostrarUsuarios());
		vista.addObject("formUsuarioErrorMessage", "Usuario Guardado Correctamente");
		return vista;
	}
}