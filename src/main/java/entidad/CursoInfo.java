package entidad;

public class CursoInfo {
	private int idCurso;
	private String nombre;
	private String profesor;
	private String horario;
	private String codAula;
	private double precio;
	
	public CursoInfo() {
		
	}

	public int getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getProfesor() {
		return profesor;
	}

	public void setProfesor(String profesor) {
		this.profesor = profesor;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getCodAula() {
		return codAula;
	}

	public void setCodAula(String codAula) {
		this.codAula = codAula;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
}
