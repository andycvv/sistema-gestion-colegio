package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.UsuarioModel;
import utils.Alertas;

import java.io.IOException;

import entidad.Usuario;

/**
 * Servlet implementation class UsuarioServlet
 */
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UsuarioServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		declaracion de variables
		String tipo;
		
//		entrada de datos
		tipo = request.getParameter("tipo");
		
//		evaluar
		switch(tipo) {
		case "register": registrarUsuario(request, response); break;
		case "login": loginUsuario(request, response); break;
		case "update-cred": actualizarCredenciales(request, response); break;
		case "edit": editarUsuario(request, response); break;
		case "delete": eliminarUsuario(request, response); break;
		case "salir": salir(request, response); break;
		}
	}
	
	private void salir(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SessionProject session = new SessionProject();
		session.invalidateSession(request);
		response.sendRedirect("./index.jsp");
	}

	protected void registrarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// variables
		String nombres, apePaterno, apeMaterno, celular, dni, clave;
	    int idRol;
		// entrada de datos
	    nombres = request.getParameter("nombres-reg");
	    apePaterno = request.getParameter("apePaterno-reg");
	    apeMaterno = request.getParameter("apeMaterno-reg");
	    celular = request.getParameter("celular-reg");
	    dni = request.getParameter("dni-reg");
		clave = request.getParameter("clave-reg");
		idRol = Integer.parseInt(request.getParameter("idRol-reg"));
		// procesos
		Usuario u = new Usuario();
		u.setNombres(nombres);
		u.setApePaterno(apePaterno);
		u.setApeMaterno(apeMaterno);
		u.setCelular(celular);
		u.setDni(dni);
		u.setClave(clave);
		u.setIdRol(idRol);
		
		UsuarioModel model = new UsuarioModel();
		int rs = model.registrarUsuario(u);
		if(rs == 1) {
			request.getSession().setAttribute("alerta", Alertas.success("Registro exitoso!"));
			System.out.println("Registro exitoso");
		} else {
			request.getSession().setAttribute("alerta", Alertas.error("Usuario","El registro fracasó!"));
			System.out.println("El registro fracasó");
		}
		
		response.sendRedirect("./pages/mntmUsuario.jsp");
	}
	
	protected void editarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// variables
		String nombres, apePaterno, apeMaterno, celular, dni, clave;
	    int idRol, idEstado;
		// entrada de datos
	    nombres = request.getParameter("nombres-edit");
	    apePaterno = request.getParameter("apePaterno-edit");
	    apeMaterno = request.getParameter("apeMaterno-edit");
	    celular = request.getParameter("celular-edit");
	    dni = request.getParameter("dni-edit");
		clave = request.getParameter("clave-edit");
		idRol = Integer.parseInt(request.getParameter("idRol-edit"));
		idEstado = Integer.parseInt(request.getParameter("idEstado-edit"));
		// procesos
		Usuario u = new Usuario();
		u.setNombres(nombres);
		u.setApePaterno(apePaterno);
		u.setApeMaterno(apeMaterno);
		u.setCelular(celular);
		u.setDni(dni);
		u.setClave(clave);
		u.setIdRol(idRol);
		u.setIdEstado(idEstado);
		
		UsuarioModel model = new UsuarioModel();
		int rs = model.modificarUsuario(u);
		if(rs == 1) {
			request.getSession().setAttribute("alerta", Alertas.success("Modificación exitosa!"));
			System.out.println("Modificación exitosa");
		} else {
			request.getSession().setAttribute("alerta", Alertas.error("Usuario","La modificación fracasó!"));
			System.out.println("La modificación fracasó");
		}
		
		response.sendRedirect("./pages/mntmUsuario.jsp");
	}
	
	protected void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// variables
		String dni;
		// entrada de datos
		dni = request.getParameter("dni");
		// procesos
		UsuarioModel model = new UsuarioModel();
		int rs = model.eliminarUsuario(dni);
		if(rs == 1) {
			request.getSession().setAttribute("alerta", Alertas.success("Eliminación exitosa!"));
			System.out.println("Elimnación exitosa");
		} else {
			request.getSession().setAttribute("alerta", Alertas.error("Usuario","La eliminación fracasó!"));
			System.out.println("La eliminación fracasó");
		}
		
		response.sendRedirect("./pages/mntmUsuario.jsp");
	}
	
	protected void loginUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// variables
		String dni, clave;
		// entrada de datos
		dni = request.getParameter("dni-log");
		clave = request.getParameter("clave-log");
		// procesos
		UsuarioModel model = new UsuarioModel();
		Usuario u = model.validarAcceso(dni, clave);
		if(u == null) {
			System.out.println("Usuario o contraseña incorrecta");
			request.getSession().setAttribute("alerta", Alertas.question("Sesión","Este usuario no existe!"));
			response.sendRedirect("./index.jsp");
		} else {
			request.getSession().setAttribute("primeraVez", true);
			request.getSession().setAttribute("usuario", u);
			if (u.getIdRol() != 1) {
			    request.getSession().setAttribute("alerta", Alertas.animated("Proximamente habrá contenido para ti!"));
			} else {
				request.getSession().setAttribute("alerta", Alertas.successSession("Inicio de sesión exitoso!"));
			}
			response.sendRedirect("./pages/principal.jsp");
		}
	}
	
	protected void actualizarCredenciales(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// variables
		String dni, clave;
		int idRol;
		// entrada de datos
		dni = request.getParameter("dni-cred");
		clave = request.getParameter("clave-cred");
		idRol = Integer.parseInt(request.getParameter("idRol-cred"));
		// procesos
		UsuarioModel model = new UsuarioModel();
		if(!model.validarRegistro(dni, idRol)) {
			System.out.println("El DNI no ha sido encontrado en el sistema");
			request.getSession().setAttribute("alerta", Alertas.error("Sesión","Este DNI no puede ser registrado"));
			response.sendRedirect("./pages/register.jsp");
		} else {
			int rs = model.actualizarCredenciales(dni, clave);
			if(rs == 1) {
				request.getSession().setAttribute("alerta", Alertas.success("Registro de credenciales exitoso"));
				System.out.println("Registro de credenciales exitoso");
			} else {
				request.getSession().setAttribute("alerta", Alertas.error("Sesión","El registro de credenciales fracasó"));
				System.out.println("El registro fracasó");
			}
			
			response.sendRedirect("./index.jsp");
		}
	}
	
}
