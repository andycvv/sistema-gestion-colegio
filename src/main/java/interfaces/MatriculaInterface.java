package interfaces;

import java.util.ArrayList;
import java.util.List;

import entidad.DetalleMatricula;
import entidad.Matricula;
import entidad.MatriculaReporte;

public interface MatriculaInterface {
	public int registrarMatricula(Matricula matricula, ArrayList<DetalleMatricula> detalle);
	public List<MatriculaReporte> listadoPorAnioYNivel(int anio, int idNivel);
}
