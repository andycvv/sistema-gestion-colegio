package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.MatriculaModel;
import modelo.UsuarioModel;
import utils.Alertas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entidad.Curso;
import entidad.DetalleMatricula;
import entidad.Matricula;
import entidad.Usuario;

public class MatriculaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MatriculaServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// declaracion de variables
		String tipo;
		
		// entrada de datos
		tipo = request.getParameter("tipo");
		
		// evaluar
		switch(tipo) {
		case "register": registrarMatricula(request, response); break;
		case "mostrarCursos": mostrarCursosDisponibles(request, response); break;
		case "removeCurso": removerCurso(request, response); break;
		}
	}
	
	private void mostrarCursosDisponibles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idNivel = request.getParameter("idNivel-mat");
		request.getSession().setAttribute("nivel", idNivel);
		response.sendRedirect("./pages/cursosDisponibles.jsp");
	}
	
	private void registrarMatricula(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// declaracion de variables
		String idEstudianteString = request.getParameter("idEstudiante-mat");
		if(idEstudianteString == null) {
			request.getSession().setAttribute("alerta", Alertas.error("Matrícula", "No hay alumnos para matricular!"));
			System.out.println("Tiene que existir un alumno para matricularse!");
			response.sendRedirect("./pages/registrarMatricula.jsp");
			return;
		}
		int idEstudiante = Integer.parseInt((String)request.getParameter("idEstudiante-mat"));
		String fecha = (String) request.getParameter("fecha-mat");
		int idNivel = Integer.parseInt((String)request.getParameter("idNivel-mat"));
		int idGrado = Integer.parseInt((String)request.getParameter("idGrado-mat"));
		Matricula m = new Matricula();
		m.setIdEstudiante(idEstudiante);
		m.setFecha(fecha);
		m.setIdNivel(idNivel);
		m.setIdGrado(idGrado);
		
		ArrayList<Curso> carro = (ArrayList<Curso>)request.getSession().getAttribute("carro");
		int cantCursos = (int)request.getSession().getAttribute("cantCursos");
		double subtotalVenta = (double)request.getSession().getAttribute("subtotalVenta");
		
		if(carro.size()<5) {
			request.getSession().setAttribute("alerta", Alertas.question("Matrícula", "Debe matricularse por lo menos en 5 cursos."));
			System.out.println("Debe matricularse por lo menos en 5 cursos");
			response.sendRedirect("./pages/registrarMatricula.jsp");
			return;
		} else if(carro.size()>8){
			request.getSession().setAttribute("alerta", Alertas.question("Matrícula", "No puede matricularse en más de 8 cursos."));
			System.out.println("No puede matricularse en más de 8 cursos");
			response.sendRedirect("./pages/registrarMatricula.jsp");
			return;
		}else {
			ArrayList<DetalleMatricula> detalle = new ArrayList<DetalleMatricula>();
			for(Curso c : carro) {
				DetalleMatricula d = new DetalleMatricula();
				d.setIdCurso(c.getIdCurso());
				detalle.add(d);
			}
			MatriculaModel model = new MatriculaModel();
			int rs = model.registrarMatricula(m, detalle);
			if(rs >= 1) {
				carro.clear();
				cantCursos = 0;
				subtotalVenta = 0;
				
				request.getSession().setAttribute("carro", carro);
				request.getSession().setAttribute("cantCursos", cantCursos);
				request.getSession().setAttribute("subtotalVenta", subtotalVenta);
				
				request.getSession().setAttribute("alerta", Alertas.success("Matricula exitosa!"));
				response.sendRedirect("./pages/registrarMatricula.jsp");
				System.out.println("Matricula exitosa");
			} else {
				request.getSession().setAttribute("alerta", Alertas.error("Matrícula", "Error en la matricula!"));
				System.out.println("Error en la matricula");
			}
		}
	}
	
	private void removerCurso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idCurso = Integer.parseInt(request.getParameter("idCurso"));
		ArrayList<Curso> carro = (ArrayList<Curso>)request.getSession().getAttribute("carro");
		int cantCursos = (int)request.getSession().getAttribute("cantCursos");
		double subtotalVenta = (double)request.getSession().getAttribute("subtotalVenta");
		
		for(Curso c : carro) {
			if(c.getIdCurso() == idCurso) {
				carro.remove(c);
				cantCursos--;
				subtotalVenta -=  c.getPrecio();
				
				request.getSession().setAttribute("carro", carro);
				request.getSession().setAttribute("cantCursos", cantCursos);
				request.getSession().setAttribute("subtotalVenta", subtotalVenta);
				
				response.sendRedirect("./pages/registrarMatricula.jsp");
				return;
			}
		}
	}

}
