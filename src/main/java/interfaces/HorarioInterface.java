package interfaces;

import java.util.List;

import entidad.Horario;

public interface HorarioInterface {
	public List<Horario> listado();
	public List<Horario> listadoHorarioAsignado();
}
