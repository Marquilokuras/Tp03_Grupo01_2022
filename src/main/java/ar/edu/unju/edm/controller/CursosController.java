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
import ar.edu.unju.edm.model.Curso;
import ar.edu.unju.edm.service.ICursoService;
//import ar.edu.unju.edm.until.ListaCursos;

@Controller
public class CursosController {
	private static final Log EMILIO=LogFactory.getLog(CursosController.class); //.getLog(UsuarioController.class);//constante con mayuscula 

	
	@Autowired
	Curso nuevoCurso;
	
	@Autowired
	ICursoService serviceCurso;
	
	//@Autowired
	//ListaCursos lista;

	@GetMapping("/otroCursos")//entra
	public ModelAndView addCurso() {
		ModelAndView vista = new ModelAndView("cargarCurso");
		vista.addObject("curso", nuevoCurso);
		vista.addObject("editMode", false);
		return vista;
	}

	@PostMapping("/guardarCursos")
	public String saveCurso(@Valid  @ModelAttribute ("curso") Curso cursoparaguardar, BindingResult resultado, Model model) { //del modelo viene 1 atributo llamado usuario y lo agarra le indica el tipo y un nombre 
		if(resultado.hasErrors()) {
			EMILIO.fatal("Error de Validacion");
			model.addAttribute("curso",cursoparaguardar);
			return "cargarCurso";
		}
		try { //controla si algo se ejecuta bien
			serviceCurso.guardarCursos(cursoparaguardar);
		}catch(Exception error){ //si no sale por aqui
			model.addAttribute("formUsuarioErrorMessage", error.getMessage());
			model.addAttribute("curso",cursoparaguardar);
			EMILIO.error("No se pudo guardar el curso");
			return "cargarCurso";
		}
		model.addAttribute("formCursoErrorMessage", "Curso Guardado Correctamente");
		model.addAttribute("curso", nuevoCurso);
		return "cargarCurso";
	}

	@GetMapping("/listadoCursos")
	public ModelAndView showCursos() {
		ModelAndView vista = new ModelAndView("listadoCursos");
		vista.addObject("listaCursos", serviceCurso.mostrarCursos());
		return vista;
	}

	@GetMapping("/editarCursos/{idCurso}")
	public ModelAndView editCurso(@PathVariable(name="idCurso")Long idCurso,Model model) throws Exception{
		Curso cursoEncontrado = new Curso();
		try {
			cursoEncontrado = serviceCurso.buscarCurso(idCurso);
		}catch(Exception e){
			model.addAttribute("formUsuarioErrorMessage", e.getMessage());
		}
		ModelAndView modelView = new ModelAndView("cargarCurso");
		modelView.addObject("curso", cursoEncontrado);
		EMILIO.error("curso: "+ cursoEncontrado.getIdCurso());
		modelView.addObject("editMode", true);
		return modelView;
	}

	@PostMapping("/modificarCurso")//se recibe
	public ModelAndView postEditarCurso(@ModelAttribute ("curso") Curso cursoparamodificar) {  
		serviceCurso.modificarCurso(cursoparamodificar);
		ModelAndView vista = new ModelAndView("listadoCursos");
		vista.addObject("listaCursos", serviceCurso.mostrarCursos());
		vista.addObject("formUsuarioErrorMessage", "Curso Guardado Correctamente");
		return vista;
	}
	
	@GetMapping("/eliminarCurso/{idCurso}")
	public String deleteCurso(@PathVariable(name="idCurso")Long idCurso, Model model) {
		try {
			serviceCurso.eliminarCurso(idCurso);
		}catch(Exception error){
			EMILIO.error("No se pudo eliminar el curso");
			model.addAttribute("formUsuarioErrorMessage", error.getMessage());
			return "redirect:/otroCursos";
		}
		return "redirect:/listadoCursos";
	}
}