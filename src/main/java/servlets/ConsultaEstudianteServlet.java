package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.UsuarioModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entidad.Usuario;

public class ConsultaEstudianteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ConsultaEstudianteServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		declaracion de variables
		String tipo;
		
//		entrada de datos
		tipo = request.getParameter("tipo");
		
//		evaluar
		switch(tipo) {
		case "read": buscarEstudiante(request, response); break;
		case "clear": reiniciarEstudiante(request, response); break;
		}
	}

	
	private void buscarEstudiante(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// variables
		int tipoConsulta;
		// entrada de datos
		tipoConsulta = Integer.parseInt(request.getParameter("tipoConsulta"));
		// procesos
		switch(tipoConsulta) {
		case 1: buscarDni(request, response); break;
		case 2: buscarNombres(request, response); break;
		case 3: buscarApePaterno(request, response); break;
		case 4: buscarApeMaterno(request, response); break;
		}
		
	}

	private void buscarDni(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// variables
		String dni;
		List<Usuario> lista = null;
		// entrada de datos
		dni = request.getParameter("inputCon");
		// procesos
		UsuarioModel model = new UsuarioModel();
		if(dni != "") {
			lista = model.listadoEstudianteDni(dni);
		} else {
			lista = new ArrayList<Usuario>();
		}
		request.getSession().setAttribute("lista", lista);
		response.sendRedirect("./pages/consultarEstudiante.jsp");
	}
	
	private void buscarNombres(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// variables
		String nombres;
		List<Usuario> lista = null;
		// entrada de datos
		nombres = request.getParameter("inputCon");
		// procesos
		UsuarioModel model = new UsuarioModel();
		if(nombres != "") {
			lista = model.listadoEstudiantesNombres(nombres);
		} else {
			lista = new ArrayList<Usuario>();
		}
		request.getSession().setAttribute("lista", lista);
		response.sendRedirect("./pages/consultarEstudiante.jsp");
	}
	
	private void buscarApePaterno(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		// variables
		String apePaterno;
		List<Usuario> lista = null;
		// entrada de datos
		apePaterno = request.getParameter("inputCon");
		// procesos
		UsuarioModel model = new UsuarioModel();
		if(apePaterno != "") {
			lista = model.listadoEstudiantesApePaterno(apePaterno);
		} else {
			lista = new ArrayList<Usuario>();
		}
		request.getSession().setAttribute("lista", lista);
		response.sendRedirect("./pages/consultarEstudiante.jsp");
	}
	
	private void buscarApeMaterno(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		// variables
		String apeMaterno;
		List<Usuario> lista = null;
		// entrada de datos
		apeMaterno = request.getParameter("inputCon");
		// procesos
		UsuarioModel model = new UsuarioModel();
		if(apeMaterno != "") {
			lista = model.listadoEstudiantesApeMaterno(apeMaterno);
		} else {
			lista = new ArrayList<Usuario>();
		}
		request.getSession().setAttribute("lista", lista);
		response.sendRedirect("./pages/consultarEstudiante.jsp");
	}
	
	private void reiniciarEstudiante(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// procesos
		List<Usuario> lista = null;
		
		request.getSession().setAttribute("lista", lista);
		response.sendRedirect("./pages/consultarEstudiante.jsp");
	}

}