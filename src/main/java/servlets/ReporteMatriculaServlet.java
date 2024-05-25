package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.MatriculaModel;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import entidad.MatriculaReporte;
import entidad.Usuario;

/**
 * Servlet implementation class ReporteMatriculaServlet
 */
public class ReporteMatriculaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReporteMatriculaServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipo = request.getParameter("tipo");
		switch(tipo) {
		case "report": reportarMatriculas(request, response); break;
		case "print": imprimirMatriculas(request, response); break;
		}
	}

	private void reportarMatriculas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int anio = Integer.parseInt(request.getParameter("anio"));
		int nivel = Integer.parseInt(request.getParameter("nivel"));
		
		MatriculaModel model = new MatriculaModel();
		List<MatriculaReporte> lista = model.listadoPorAnioYNivel(anio, nivel);
		request.getSession().setAttribute("reporteMatricula", lista);
		
		response.sendRedirect("./pages/reporteMatricula.jsp");
	}
	
	private void imprimirMatriculas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int anio = Integer.parseInt(request.getParameter("anio"));
		int nivel = Integer.parseInt(request.getParameter("nivel"));
		String nivelTexto = null;
		if(nivel == 1) nivelTexto = "Primaria";
		if(nivel == 2) nivelTexto = "Secundaria";
		MatriculaModel model = new MatriculaModel();
		List<MatriculaReporte> lista = model.listadoPorAnioYNivel(anio, nivel);
		
		Usuario u = (Usuario) request.getSession().getAttribute("usuario");
		
        Date fechaActual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fechaFormateada = formato.format(fechaActual);
        
		try {
			Document documento = new Document();
			FileOutputStream fos = new FileOutputStream("reporte.pdf");
			PdfWriter pdfW = PdfWriter.getInstance(documento, fos);
			// configuracion
			pdfW.setInitialLeading(20);
			// abrir el documento
			documento.open();
			
			// textos
			documento.add(new Paragraph("Reporte de matriculas del "+anio+" ("+nivelTexto+")",
										FontFactory.getFont("arial",
															22,
															Font.BOLDITALIC,
															BaseColor.ORANGE)));
			
			documento.add(new Paragraph(fechaFormateada + " - Hecho por: " + u.getNombres()));
			documento.add(new Paragraph(" "));
			documento.add(new Paragraph(" "));
			documento.add(new Paragraph(" "));
			
			// mostrar los datos en una tabla
			PdfPTable tabla = new PdfPTable(6);
			tabla.addCell("ID Matricula");
			tabla.addCell("Estudiante");
			tabla.addCell("DNI");
			tabla.addCell("Fecha");
			tabla.addCell("Nivel");
			tabla.addCell("Grado");
			
			// usar el listado en las columnas
			for(MatriculaReporte mr : lista) {
				tabla.addCell(mr.getIdMatricula()+"");
				tabla.addCell(mr.getEstudiante());
				tabla.addCell(mr.getDni());
				tabla.addCell(mr.getFecha());
				tabla.addCell(mr.getNivel());
				tabla.addCell(mr.getGrado());
			}
			documento.add(tabla);
			// cerrar el documento
			documento.close();
			Desktop.getDesktop().open(new File("reporte.pdf"));
		} catch (Exception e) {
			System.out.println("Error al generar el pdf");
		}
		
		response.sendRedirect("./pages/reporteMatricula.jsp");
	}

}
