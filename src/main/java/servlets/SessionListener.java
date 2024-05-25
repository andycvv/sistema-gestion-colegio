package servlets;

import java.util.ArrayList;

import entidad.Curso;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

    public SessionListener() {
        // TODO Auto-generated constructor stub
    }

    public void sessionCreated(HttpSessionEvent se)  {
    	ArrayList<Curso> carro = new ArrayList<Curso>();
    	int cantCursos = 0;
    	double subtotalVenta = 0.0;
    	
    	se.getSession().setAttribute("carro", carro);
    	se.getSession().setAttribute("cantCursos", cantCursos);
    	se.getSession().setAttribute("subtotalVenta", subtotalVenta);
    }

    public void sessionDestroyed(HttpSessionEvent se)  {
         // TODO Auto-generated method stub
    }
	
}
