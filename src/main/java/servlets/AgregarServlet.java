package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.CursoModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entidad.Curso;
import entidad.CursoInfo;
import entidad.DetalleMatricula;

public class AgregarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AgregarServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesar(request, response);
	}

	private void procesar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// datos enviados del jsp
		int idCurso = Integer.parseInt(request.getParameter("idCurso"));
		CursoModel model = new CursoModel();
		Curso c = model.buscarCurso(idCurso);
		
		ArrayList<Curso> carro = (ArrayList<Curso>)request.getSession().getAttribute("carro");
		int cantCursos = (int)request.getSession().getAttribute("cantCursos");
		double subtotalVenta = (double)request.getSession().getAttribute("subtotalVenta");
		
		carro.add(c);
		cantCursos++;
		subtotalVenta +=  c.getPrecio();
		
		request.getSession().setAttribute("carro", carro);
		request.getSession().setAttribute("cantCursos", cantCursos);
		request.getSession().setAttribute("subtotalVenta", subtotalVenta);
		
		response.sendRedirect("./pages/registrarMatricula.jsp");
	}

}
