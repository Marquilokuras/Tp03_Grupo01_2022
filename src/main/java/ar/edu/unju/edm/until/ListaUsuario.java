package ar.edu.unju.edm.until;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import ar.edu.unju.edm.model.Usuario;

@Component
public class ListaUsuario {
	private List<Usuario> listado = new ArrayList<>(); //List es una interfaz , implementacion de List es ArrayList
	public ListaUsuario() {
		// TODO Auto-generated constructor stub
	}
	public List<Usuario> getListado() {
		return listado;
	}

	public void setListado(List<Usuario> listado) {
		this.listado = listado;
	}
}
