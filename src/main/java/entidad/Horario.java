package entidad;

public class Horario {
	// atributos privados
	private int idHorario;
	private String dia;
	private String horaInicio;
	private String horaFin;
	private String codAula;
	
	// constructor
	public Horario() {
		
	}

	// metodos get/set
	public int getIdHorario() {
		return idHorario;
	}

	public void setIdHorario(int idHorario) {
		this.idHorario = idHorario;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}

	public String getCodAula() {
		return codAula;
	}

	public void setCodAula(String codAula) {
		this.codAula = codAula;
	}
	
}
