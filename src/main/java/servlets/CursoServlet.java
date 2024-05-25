package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.CursoModel;
import utils.Alertas;

import java.io.IOException;

import entidad.Curso;

public class CursoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CursoServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// declaracion de variables
		String tipo;
		
		// entrada de datos
		tipo = request.getParameter("tipo");
		
		// evaluar
		switch(tipo) {
		case "register": registrarCurso(request, response); break;
		case "delete": eliminarCurso(request, response); break;
		case "edit": editarCurso(request, response); break;
		}
	}

	private void registrarCurso(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// declaracion de variables
		String nombre;
		int idProfesor,idHorario,idCategoria,idNivel;
		double precio;
		int rs = 0;
		
		// entrada de datos
		nombre = request.getParameter("nombre-reg");
		idProfesor = Integer.parseInt(request.getParameter("idProfesor-reg"));
		idHorario = Integer.parseInt(request.getParameter("idHorario-reg"));
		idCategoria = Integer.parseInt(request.getParameter("idCategoria-reg"));
		idNivel = Integer.parseInt(request.getParameter("idNivel-reg"));
		precio = Double.parseDouble(request.getParameter("precio-reg"));
		
		Curso c = new Curso();
		c.setNombre(nombre);
		c.setIdProfesor(idProfesor);
		c.setIdHorario(idHorario);
		c.setIdCategoria(idCategoria);
		c.setIdNivel(idNivel);
		c.setPrecio(precio);
		CursoModel model = new CursoModel();
		rs = model.registrarCurso(c);
		
		// evaluar
		if(rs == 1) {
			request.getSession().setAttribute("alerta", Alertas.success("Registro exitoso!"));
			System.out.println("Registro exitoso");
		} else {
			request.getSession().setAttribute("alerta", Alertas.error("Curso","El registro fracasó!"));
			System.out.println("El registro fracasó");
		}
		
		response.sendRedirect("./pages/mntmCurso.jsp");
	}
	
	private void editarCurso(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			// declaracion de variables
			String nombre;
			int idCurso,idProfesor,idHorario,idCategoria,idNivel;
			double precio;
			int rs = 0;
			
			// entrada de datos
			idCurso = Integer.parseInt(request.getParameter("idCurso-edit"));
			nombre = request.getParameter("nombre-edit");
			idProfesor = Integer.parseInt(request.getParameter("idProfesor-edit"));
			idHorario = Integer.parseInt(request.getParameter("idHorario-edit"));
			idCategoria = Integer.parseInt(request.getParameter("idCategoria-edit"));
			idNivel = Integer.parseInt(request.getParameter("idNivel-edit"));
			precio = Double.parseDouble(request.getParameter("precio-edit"));
			
			Curso c = new Curso();
			c.setIdCurso(idCurso);
			c.setNombre(nombre);
			c.setIdProfesor(idProfesor);
			c.setIdHorario(idHorario);
			c.setIdCategoria(idCategoria);
			c.setIdNivel(idNivel);
			c.setPrecio(precio);
			
			CursoModel model = new CursoModel();
			rs = model.editarCurso(c);
			
			// evaluar
			if(rs == 1) {
				request.getSession().setAttribute("alerta", Alertas.success("Modificación exitosa!"));
				System.out.println("Edición exitosa");
			} else {
				request.getSession().setAttribute("alerta", Alertas.error("Curso","La modificación fracasó!"));
				System.out.println("La edición fracasó");
			}
		} catch (Exception e) {
			request.getSession().setAttribute("alerta", Alertas.error("Edición","No hay horarios disponibles!"));
		} finally {
			response.sendRedirect("./pages/mntmCurso.jsp");
		}
	}
	
	private void eliminarCurso(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// declaracion de variables
		int idCurso;
		int rs = 0;
		
		// entrada de datos
		idCurso = Integer.parseInt(request.getParameter("idCurso"));
		
		// procesos
		CursoModel modelo = new CursoModel();
		rs = modelo.eliminarCurso(idCurso);
		
		// evaluar
		if(rs == 1) {
			request.getSession().setAttribute("alerta", Alertas.success("Eliminación exitosa!"));
			System.out.println("Eliminación exitosa");
		} else {
			request.getSession().setAttribute("alerta", Alertas.error("Curso","La eliminación fracasó!"));
			System.out.println("La eliminación fracasó");
		}
		
		response.sendRedirect("./pages/mntmCurso.jsp");
	}

}
