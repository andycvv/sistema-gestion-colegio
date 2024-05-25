package entidad;

public class DetalleMatriculaInfo {
	// atributos privados
    private int idMatricula;
    private int idCurso;
    private String nombreCurso;
    private String nombreProfesor;
    private String diaSemana;
    private String horaInicioFin;
    private String aula;
    private double precio;
    
    // constructor
    public DetalleMatriculaInfo() {
    	
    }
    
    // metodos get/set
	public int getIdMatricula() {
		return idMatricula;
	}

	public void setIdMatricula(int idMatricula) {
		this.idMatricula = idMatricula;
	}

	public int getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}

	public String getNombreCurso() {
		return nombreCurso;
	}

	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}

	public String getNombreProfesor() {
		return nombreProfesor;
	}

	public void setNombreProfesor(String nombreProfesor) {
		this.nombreProfesor = nombreProfesor;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	public String getHoraInicioFin() {
		return horaInicioFin;
	}

	public void setHoraInicioFin(String horaInicioFin) {
		this.horaInicioFin = horaInicioFin;
	}

	public String getAula() {
		return aula;
	}

	public void setAula(String aula) {
		this.aula = aula;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
    
}
