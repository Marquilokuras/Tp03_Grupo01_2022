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
import ar.edu.unju.edm.until.ListaCursos;


@Controller
public class CursosController {
	private static final Log EMILIO=LogFactory.getLog(CursosController.class); //.getLog(UsuarioController.class);//constante con mayuscula 

	@Autowired
	Curso nuevoCurso;
	
	@Autowired
	ICursoService serviceCurso;
	
	@Autowired
	ListaCursos lista;

	@GetMapping("/otroCursos")//entra
	public ModelAndView addCurso() {
		ModelAndView vista = new ModelAndView("cargarCurso");//pasa nombre de la lista a pasar
		//vista.addObject("nuevoUsuario");
		vista.addObject("curso", nuevoCurso);
		vista.addObject("editMode", false);
		
		return vista;
	}

	@PostMapping("/guardarCursos")//se recibe
	public String saveCurso(@Valid  @ModelAttribute ("curso") Curso cursoparaguardar, BindingResult resultado, Model model) { //del modelo viene 1 atributo llamado usuario y lo agarra le indica el tipo y un nombre 

		
		if(resultado.hasErrors()) {
			EMILIO.fatal("Error de Validacion");
			model.addAttribute("curso",cursoparaguardar);
			return "cargarCurso";
		}
		try { //controla si algo se ejecuta bien
			serviceCurso.guardarCursos(cursoparaguardar);
		}catch(Exception error){ //si no sale por aqui
			model.addAttribute("formCursoErrorMessage", error.getMessage());
			model.addAttribute("curso",cursoparaguardar);
			EMILIO.error("No se pudo guardar el curso");
			return "cargarCurso";
		}
		model.addAttribute("formUsuarioErrorMessage", "Curso Guardado Correctamente");
		model.addAttribute("curso", nuevoCurso);
		return "cargarCurso";
	}

	@GetMapping("/listadoCursos")
	public ModelAndView showCursos() {
		ModelAndView vista = new ModelAndView("listadoCursos");

		vista.addObject("listaCursos", lista.getListado());
		return vista;
	}

	@GetMapping("/editarCursos/{id}")
	public ModelAndView editCurso(@PathVariable(name="id")Long idCurso) {
		Curso cursoEncontrado = new Curso();
		for(int i=0;i<lista.getListado().size();i++) {
			if(lista.getListado().get(i).getIdCurso().equals(idCurso)) {
				cursoEncontrado = lista.getListado().get(i);
			}
		};
		EMILIO.fatal("Error de entrada"+ cursoEncontrado.getIdCurso());
		ModelAndView encontrado = new ModelAndView("cargarCurso");

		encontrado.addObject("curso", cursoEncontrado);
		encontrado.addObject("editMode", true);
		return encontrado;
	}

	@PostMapping("/modificarCurso")//se recibe
	public String modCurso(@Valid  @ModelAttribute ("curso") Curso cursosparamodificar, BindingResult resultado, Model model) { //del modelo viene 1 atributo llamado usuario y lo agarra le indica el tipo y un nombre 
		EMILIO.info("Ingresando al metodo guardar Curso: "+ cursosparamodificar.getFechaInicio() );
		if(resultado.hasErrors()) {
			EMILIO.fatal("Error de validacion");
			model.addAttribute("curso",cursosparamodificar);
			return "cargarCurso";
		}
		for(int i=0;i<lista.getListado().size();i++) {
			if(lista.getListado().get(i).getIdCurso().equals(cursosparamodificar.getIdCurso())) {
				lista.getListado().set(i, cursosparamodificar);
			}
		};

		EMILIO.error("Tamaño del Listado: " + lista.getListado().size());
		return "redirect:/listadoCursos";
	}
	
	@GetMapping("/eliminarCurso/{idCurso}")
	public ModelAndView deleteCurso(@PathVariable(name="idCurso")Long id) {
		Curso cursoEncontrado = new Curso();
		for(int i=0;i<lista.getListado().size();i++) {
			if(lista.getListado().get(i).getIdCurso().equals(id)) {
				cursoEncontrado = lista.getListado().remove(i);
			}
		};
		EMILIO.fatal("Error de entrada"+ cursoEncontrado.getIdCurso());
		ModelAndView encontrado = new ModelAndView("cursoUsuario");
		
		encontrado.addObject("curso", cursoEncontrado);
		encontrado.addObject("editMode", true);
		return encontrado;
	}

	@PostMapping("/sacarCurso")//se recibe
	public String sacarCurso(@Valid  @ModelAttribute ("curso") Curso cursoparasacar, BindingResult resultado, Model model) { //del modelo viene 1 atributo llamado usuario y lo agarra le indica el tipo y un nombre 
		EMILIO.info("Ingresando al metodo sacar Curso: "+ cursoparasacar.getFechaInicio() );
		if(resultado.hasErrors()) {
			EMILIO.fatal("Error de validacion");
			model.addAttribute("curso",cursoparasacar);
			return "cargarCurso";
		}
		for(int i=0;i<lista.getListado().size();i++) {
			if(lista.getListado().get(i).getIdCurso().equals(cursoparasacar.getIdCurso())) {
			}
		}
		return "cargarCurso";
	}
}