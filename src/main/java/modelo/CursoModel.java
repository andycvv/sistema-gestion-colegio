package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexion.MySQLCnn;
import entidad.Curso;
import entidad.CursoInfo;
import interfaces.CursoInterface;

public class CursoModel implements CursoInterface{

	@Override
	public int registrarCurso(Curso c) {
	    int rs = -1;
	    Connection con = null;
	    PreparedStatement pst1 = null;
	    PreparedStatement pst2 = null;
	    ResultSet generatedKeys = null;
	    
	    try {
	        con = MySQLCnn.getCnn();
	        con.setAutoCommit(false);
	        
	        String sql1 = "INSERT INTO curso (nombre, idProfesor, idHorario, idCategoria, idNivel, precio) VALUES (?, ?, ?, ?, ?, ?)";
	        pst1 = con.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
	        pst1.setString(1, c.getNombre());
	        pst1.setInt(2, c.getIdProfesor());
	        pst1.setInt(3, c.getIdHorario());
	        pst1.setInt(4, c.getIdCategoria());
	        pst1.setInt(5, c.getIdNivel());
	        pst1.setDouble(6, c.getPrecio());
	        rs = pst1.executeUpdate();
	        
	        if (rs > 0) {
	            generatedKeys = pst1.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                int idCurso = generatedKeys.getInt(1);
	                
	                String sql2 = "{CALL cambiarEstadoAula(?)}";
	                pst2 = con.prepareStatement(sql2);
	                pst2.setInt(1, idCurso);
	                rs = pst2.executeUpdate();
	                
	                con.commit();
	            }
	        }
	    } catch (Exception e) {
	        try {
	            con.rollback();
	            System.out.println("Error en la sentencia registrarCurso: " + e.getMessage());
	            rs = -1;
	        } catch (SQLException e2) {
	            System.out.println("Error en el rollback: " + e2.getMessage());
	        }
	    } finally {
	        try {
	            if (generatedKeys != null) generatedKeys.close();
	            if (pst2 != null) pst2.close();
	            if (pst1 != null) pst1.close();
	            if (con != null) con.close();
	        } catch (SQLException e2) {
	            System.out.println("Error al cerrar la conexión: " + e2.getMessage());
	        }
	    }
	    
	    return rs;
	}


	@Override
	public ArrayList<Curso> listado() {
		// declaracion de variables
		ResultSet rs = null;
		ArrayList<Curso> lista = new ArrayList<Curso>();
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLCnn.getCnn();
			String sql = "SELECT * FROM curso";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				Curso c = new Curso();
				c.setIdCurso(rs.getInt(1));
				c.setNombre(rs.getString(2));
				c.setIdProfesor(rs.getInt(3));
				c.setIdHorario(rs.getInt(4));
				c.setIdCategoria(rs.getInt(5));
				c.setIdNivel(rs.getInt(6));
				c.setPrecio(rs.getDouble(7));
				lista.add(c);
			}
		} catch (Exception e) {
			System.out.println("Error en la sentencia listado: " + e.getMessage());
		} finally {
			try {
				if(con != null) con.close();
				if(pst != null) pst.close();
			} catch (Exception e2) {
				System.out.println("Error al cerrar la conexion: " + e2.getMessage());
			}
		}
		
		return lista;
	}
	
	@Override
	public Curso buscarCurso(int idCurso) {
		// declaracion de variables
		ResultSet rs = null;
		Curso c = null;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLCnn.getCnn();
			String sql = "SELECT * FROM curso WHERE idCurso=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, idCurso);
			rs = pst.executeQuery();
			if(rs.next()) {
				c = new Curso();
				c.setIdCurso(rs.getInt(1));
				c.setNombre(rs.getString(2));
				c.setIdProfesor(rs.getInt(3));
				c.setIdHorario(rs.getInt(4));
				c.setIdCategoria(rs.getInt(5));
				c.setIdNivel(rs.getInt(6));
				c.setPrecio(rs.getDouble(7));
			}
		} catch (Exception e) {
			System.out.println("Error en la sentencia buscarCurso: " + e.getMessage());
		} finally {
			try {
				if(con != null) con.close();
				if(pst != null) pst.close();
			} catch (Exception e2) {
				System.out.println("Error al cerrar la conexion: " + e2.getMessage());
			}
		}
		
		return c;
	}
	
	@Override
	public ArrayList<CursoInfo> listadoCursoInfo() {
		// declaracion de variables
		ResultSet rs = null;
		ArrayList<CursoInfo> lista = new ArrayList<CursoInfo>();
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLCnn.getCnn();
			String sql = "{CALL obtenerCursoInfo}";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				CursoInfo c = new CursoInfo();
				c.setIdCurso(rs.getInt(1));
				c.setNombre(rs.getString(2));
				c.setProfesor(rs.getString(3));
				c.setHorario(rs.getString(4));
				c.setCodAula(rs.getString(5));
				c.setPrecio(rs.getDouble(6));
				lista.add(c);
			}
		} catch (Exception e) {
			System.out.println("Error en la sentencia listadoCursoInfo: " + e.getMessage());
		} finally {
			try {
				if(con != null) con.close();
				if(pst != null) pst.close();
			} catch (Exception e2) {
				System.out.println("Error al cerrar la conexion: " + e2.getMessage());
			}
		}
		
		return lista;
	}
	
	@Override
	public ArrayList<CursoInfo> listadoCursoInfoPorNivel(int idNivel){
		// declaracion de variables
		ResultSet rs = null;
		ArrayList<CursoInfo> lista = new ArrayList<CursoInfo>();
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLCnn.getCnn();
			String sql = "{CALL obtenerCursoInfoPorNivel(?)}";
			pst = con.prepareStatement(sql);
			pst.setInt(1, idNivel);
			rs = pst.executeQuery();
			while(rs.next()) {
				CursoInfo c = new CursoInfo();
				c.setIdCurso(rs.getInt(1));
				c.setNombre(rs.getString(2));
				c.setProfesor(rs.getString(3));
				c.setHorario(rs.getString(4));
				c.setCodAula(rs.getString(5));
				c.setPrecio(rs.getDouble(6));
				lista.add(c);
			}
		} catch (Exception e) {
			System.out.println("Error en la sentencia listadoCursoInfoPorNivel: " + e.getMessage());
		} finally {
			try {
				if(con != null) con.close();
				if(pst != null) pst.close();
			} catch (Exception e2) {
				System.out.println("Error al cerrar la conexion: " + e2.getMessage());
			}
		}
		
		return lista;
	}

	@Override
	public int eliminarCurso(int idCurso) {
		// declaracion de variables
		int rs = -1;
		Connection con = null;
		PreparedStatement pst1 = null;
		PreparedStatement pst2 = null;
		try {
			con = MySQLCnn.getCnn();
			con.setAutoCommit(false);
			String sql2 = "{CALL cambiarEstadoAulaPorCurso(?)}";
			pst2 = con.prepareStatement(sql2);
			pst2.setInt(1, idCurso);
			rs = pst2.executeUpdate();
			String sql1 = "DELETE FROM curso WHERE idCurso = ?";
			pst1 = con.prepareStatement(sql1);
			pst1.setInt(1, idCurso);
			rs = pst1.executeUpdate();
			con.commit();
		} catch (Exception e) {
			try {
				con.rollback();
				System.out.println("Error en la sentencia eliminarCurso: " + e.getMessage());
				rs = -1;
			} catch (SQLException e2) {
				System.out.println("Error en el rollback: " + e2.getMessage());
			}
		} finally {
			try {
				if(pst2 != null) pst2.close();
				if(pst1 != null) pst1.close();
				if(con != null) con.close();
			} catch (SQLException e2) {
				System.err.println("Error al cerrar la conexion: " + e2.getMessage());
			}
		}
		
		return rs;
	}

	@Override
	public int editarCurso(Curso c) {
		// declaracion de variables
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		// entrada de datos
		try {
			con = MySQLCnn.getCnn();
			// UPDATE curso SET nombre='',idProfesor='',idHorario='',idCategoria='',idNivel='',precio='' WHERE idCurso='';
			String sql = "UPDATE curso SET nombre=?,idProfesor=?,idHorario=?,idCategoria=?,idNivel=?,precio=? WHERE idCurso = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, c.getNombre());
			pst.setInt(2, c.getIdProfesor());
			pst.setInt(3, c.getIdHorario());
			pst.setInt(4, c.getIdCategoria());
			pst.setInt(5, c.getIdNivel());
			pst.setDouble(6, c.getPrecio());
			pst.setInt(7, c.getIdCurso());
			rs = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error en la sentencia editarCurso: " + e.getMessage());
		} finally {
			try {
				if(con != null) con.close();
				if(pst != null) pst.close();
			} catch (Exception e2) {
				System.out.println("Error al cerrar la conexión: " + e2.getMessage());
			}
		}
		
		return rs;
	}

	@Override
	public ArrayList<CursoInfo> listadoCursoInfoPorNombre(String nombre) {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pst = null;
		ArrayList<CursoInfo> lista = new ArrayList<CursoInfo>();
		try {
			con = MySQLCnn.getCnn();
			String sql = "{CALL obtenerCursoInfoPorNombre(?)}";
			pst = con.prepareStatement(sql);
			pst.setString(1, nombre + "%");
			rs = pst.executeQuery();
			while(rs.next()) {
				CursoInfo c = new CursoInfo();
				c.setIdCurso(rs.getInt(1));
				c.setNombre(rs.getString(2));
				c.setProfesor(rs.getString(3));
				c.setHorario(rs.getString(4));
				c.setCodAula(rs.getString(5));
				c.setPrecio(rs.getDouble(6));	
				lista.add(c);
			}
		} catch (Exception e) {
			System.out.println("Error en la sentencia listadoCursoInfoPorNombre: " + e.getMessage());
		} finally {
			try {
				if(con != null) con.close();
				if(pst != null) pst.close();
			} catch (Exception e2) {
				System.out.println("Error al cerrar la conexión: " + e2.getMessage());
			}
		}
		return lista;
	}


	@Override
	public ArrayList<CursoInfo> listadoCursoInfoPorProfesor(String profesor) {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pst = null;
		ArrayList<CursoInfo> lista = new ArrayList<CursoInfo>();
		try {
			con = MySQLCnn.getCnn();
			String sql = "{CALL obtenerCursoInfoPorProfesor(?)}";
			pst = con.prepareStatement(sql);
			pst.setString(1, profesor + "%");
			rs = pst.executeQuery();
			while(rs.next()) {
				CursoInfo c = new CursoInfo();
				c.setIdCurso(rs.getInt(1));
				c.setNombre(rs.getString(2));
				c.setProfesor(rs.getString(3));
				c.setHorario(rs.getString(4));
				c.setCodAula(rs.getString(5));
				c.setPrecio(rs.getDouble(6));
				lista.add(c);
			}
		} catch (Exception e) {
			System.out.println("Error en la sentencia listadoCursoInfoPorProfesor: " + e.getMessage());
		} finally {
			try {
				if(con != null) con.close();
				if(pst != null) pst.close();
			} catch (Exception e2) {
				System.out.println("Error al cerrar la conexión: " + e2.getMessage());
			}
		}
		return lista;
	}


	@Override
	public ArrayList<CursoInfo> listadoCursoInfoPorAula(String aula) {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pst = null;
		ArrayList<CursoInfo> lista = new ArrayList<CursoInfo>();
		try {
			con = MySQLCnn.getCnn();
			String sql = "{CALL obtenerCursoInfoPorCodigoAula(?)}";
			pst = con.prepareStatement(sql);
			pst.setString(1, aula + "%");
			rs = pst.executeQuery();
			while(rs.next()) {
				CursoInfo c = new CursoInfo();
				c.setIdCurso(rs.getInt(1));
				c.setNombre(rs.getString(2));
				c.setProfesor(rs.getString(3));
				c.setHorario(rs.getString(4));
				c.setCodAula(rs.getString(5));
				c.setPrecio(rs.getDouble(6));
				lista.add(c);
			}
		} catch (Exception e) {
			System.out.println("Error en la sentencia listadoCursoInfoPorAula" + e.getMessage());
		} finally{
			try {
				if(con != null) con.close();
				if(pst != null) pst.close();
			} catch (Exception e2) {
				System.out.println("Error al cerrar la conexión: " + e2.getMessage());
			}
		}
		return lista;
	}
}
