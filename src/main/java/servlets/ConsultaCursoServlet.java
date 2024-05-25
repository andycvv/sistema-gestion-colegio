package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.CursoModel;

import java.io.IOException;
import java.util.ArrayList;

import entidad.CursoInfo;

public class ConsultaCursoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ConsultaCursoServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// variables
		String tipo;
		
		// entrada de datos
		tipo = request.getParameter("tipo");
		
		// procesos
		switch(tipo) {
		case "read": buscarCurso(request, response); break;
		case "clear": reiniciarCurso(request, response); break;
		}
	}
	
	private void buscarCurso(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// variables
		int tipoConsulta;
				
		// entrada de datos
		tipoConsulta = Integer.parseInt(request.getParameter("tipoConsulta"));
				
		// procesos
		switch(tipoConsulta) {
		case 1: buscarNombre(request, response); break;
		case 2: buscarProfesor(request, response); break;
		case 3: buscarAula(request, response); break;
				}
	}
	
	private void reiniciarCurso(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// procesos
		CursoModel model = new CursoModel();
		ArrayList<CursoInfo> lista = model.listadoCursoInfo();
		
		request.getSession().setAttribute("lista", lista);
		response.sendRedirect("./pages/consultarCurso.jsp");
	}

	private void buscarNombre(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// variables 
		ArrayList<CursoInfo> lista = null;
		String inputCon;
		
		// entrada de datos
		inputCon = request.getParameter("inputCon");
		
		// procesos
		CursoModel model = new CursoModel();
		if(inputCon != "") {
			lista = model.listadoCursoInfoPorNombre(inputCon);
		} else {
			lista = new ArrayList<CursoInfo>();
		}

		request.getSession().setAttribute("lista", lista);
		response.sendRedirect("./pages/consultarCurso.jsp");
	}
	
	private void buscarProfesor(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// variables
		ArrayList<CursoInfo> lista = null;
		String inputCon;
		
		// entrada de datos
		inputCon = request.getParameter("inputCon");
		
		// procesos
		CursoModel model = new CursoModel();
		if(inputCon != "") {
			lista = model.listadoCursoInfoPorProfesor(inputCon);
		} else {
			lista = new ArrayList<CursoInfo>();
		}
		
		request.getSession().setAttribute("lista", lista);
		response.sendRedirect("./pages/consultarCurso.jsp");
	}
	
	private void buscarAula(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// variables
		ArrayList<CursoInfo> lista = null;
		String inputCon;
		
		// entrada de datos
		inputCon = request.getParameter("inputCon");
				
		// procesos
		CursoModel model = new CursoModel();
		if(inputCon != "") {
			lista = model.listadoCursoInfoPorAula(inputCon);
		} else {
			lista = new ArrayList<CursoInfo>();
		}
		
		request.getSession().setAttribute("lista", lista);
		response.sendRedirect("./pages/consultarCurso.jsp");
	}

}