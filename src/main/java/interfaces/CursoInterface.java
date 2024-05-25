package interfaces;

import java.util.ArrayList;

import entidad.Curso;
import entidad.CursoInfo;

public interface CursoInterface {
	public int registrarCurso(Curso a);
	public int editarCurso(Curso a);
	public int eliminarCurso(int idCurso);
	public ArrayList<Curso> listado();
	public Curso buscarCurso(int idCurso);
	public ArrayList<CursoInfo> listadoCursoInfo();
	public ArrayList<CursoInfo> listadoCursoInfoPorNivel(int idNivel);
	public ArrayList<CursoInfo> listadoCursoInfoPorNombre(String nombre);
	public ArrayList<CursoInfo> listadoCursoInfoPorProfesor(String profesor);
	public ArrayList<CursoInfo> listadoCursoInfoPorAula(String aula);
}
