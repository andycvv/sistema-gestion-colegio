package interfaces;

import java.util.List;

import entidad.Usuario;

public interface UsuarioInterface {
	public int registrarUsuario(Usuario u);
	public int modificarUsuario(Usuario u);
	public int eliminarUsuario(String dni);
	public List<Usuario> listado();
	public List<Usuario> listadoProfesor();
	public List<Usuario> listadoEstudiante();
	
	public Usuario buscarEstudianteMatriculadoAnioActual(int idEstudiante);
	public List<Usuario> listadoEstudianteDni(String dni);
	public List<Usuario> listadoEstudiantesNombres(String nombres);
	public List<Usuario> listadoEstudiantesApePaterno(String apePaterno);
	public List<Usuario> listadoEstudiantesApeMaterno(String apeMaterno);
	
	public int actualizarCredenciales(String dni, String clave);
	public Usuario validarAcceso(String dni, String clave);
	public boolean validarRegistro(String dni, int idRol);
}
