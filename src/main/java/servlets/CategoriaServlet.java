package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.CategoriaModel;
import utils.Alertas;

import java.io.IOException;

import entidad.Categoria;

public class CategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CategoriaServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// declaracion de variables
		String tipo;

		// entrada de datos
		tipo = request.getParameter("tipo");

		// evaluar
		switch (tipo) {
		case "register":
			registrarCategoria(request, response);
			break;
		case "delete":
			eliminarCategoria(request, response);
			break;
		case "edit":
			editarCategoria(request, response);
			break;
		}
	}

	private void registrarCategoria(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// declaracion de variables
		String nombre;
		int rs = 0;

		// entrada de datos
		nombre = request.getParameter("nombre-reg");

		Categoria c = new Categoria();
		c.setNombre(nombre);

		CategoriaModel model = new CategoriaModel();
		rs = model.registrarCategoria(c);

		// evaluar
		if (rs == 1) {
			request.getSession().setAttribute("alerta", Alertas.success("Registro exitoso!"));
			System.out.println("Registro exitoso");
		} else {
			request.getSession().setAttribute("alerta", Alertas.error("Categoría","El registro fracasó!"));
			System.out.println("El registro fracasó");
		}

		response.sendRedirect("./pages/mntmCategoria.jsp");
	}

	private void editarCategoria(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// declaracion de variables
		String nombre;
		int idCategoria;
		int rs = 0;

		// entrada de datos
		idCategoria = Integer.parseInt(request.getParameter("id-edit"));
		nombre = request.getParameter("nombre-edit");

		Categoria c = new Categoria();
		c.setIdCategoria(idCategoria);
		c.setNombre(nombre);

		CategoriaModel model = new CategoriaModel();
		rs = model.editarCategoria(c);

		// evaluar
		if (rs == 1) {
			request.getSession().setAttribute("alerta", Alertas.success("Modificación exitosa!"));
			System.out.println("Edición exitosa");
		} else {
			request.getSession().setAttribute("alerta", Alertas.error("Categoría","La modificación fracasó!"));
			System.out.println("La edición fracasó");
		}

		response.sendRedirect("./pages/mntmCategoria.jsp");
	}

	private void eliminarCategoria(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// declaracion de variables
		int idCategoria;
		int rs = 0;

		// entrada de datos
		idCategoria = Integer.parseInt(request.getParameter("idCategoria"));

		// procesos
		CategoriaModel modelo = new CategoriaModel();
		rs = modelo.eliminarCategoria(idCategoria);

		// evaluar
		if (rs == 1) {
			request.getSession().setAttribute("alerta", Alertas.success("Eliminación exitosa!"));
			System.out.println("Eliminación exitosa");
		} else {
			request.getSession().setAttribute("alerta", Alertas.error("Categoría","La eliminación fracasó!"));
			System.out.println("La eliminación fracasó");
		}

		response.sendRedirect("./pages/mntmCategoria.jsp");
	}

}
