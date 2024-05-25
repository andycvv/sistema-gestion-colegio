package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conexion.MySQLCnn;
import entidad.Usuario;
import interfaces.UsuarioInterface;

public class UsuarioModel implements UsuarioInterface{

	@Override
	public int registrarUsuario(Usuario u) {
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLCnn.getCnn();
			String sql = "INSERT usuario VALUES (null,?,?,?,?,?,null,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, u.getNombres());
			pst.setString(2, u.getApePaterno());
			pst.setString(3, u.getApeMaterno());
			pst.setString(4, u.getCelular());
			pst.setString(5, u.getDni());
			pst.setInt(6, u.getIdRol());
			pst.setInt(7, 1);
			rs = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error en la sentencia registrarUsuario: " + e.getMessage());
		} finally {
			try {
				if(con != null) con.close();
				if(pst != null) pst.close();
			} catch (Exception e2) {
				System.out.println("Error al cerrar la conexion: " + e2.getMessage());
			}
		}
		return rs;
	}
	
	@Override
	public int modificarUsuario(Usuario u) {
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLCnn.getCnn();
			String sql = "update usuario set nombres=?, apePaterno=?,apeMaterno=?,celular=?,clave=?,idRol=?,idEstado=? where dni=?";
			pst = con.prepareStatement(sql);
			pst.setString(1, u.getNombres());
			pst.setString(2, u.getApePaterno());
			pst.setString(3, u.getApeMaterno());
			pst.setString(4, u.getCelular());
			pst.setString(5, u.getClave());
			pst.setInt(6, u.getIdRol());
			pst.setInt(7, u.getIdEstado());
			pst.setString(8, u.getDni());
			rs = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error en la sentencia modificarUsuario: " + e.getMessage());
		} finally {
			try {
				if(con != null) con.close();
				if(pst != null) pst.close();
			} catch (Exception e2) {
				System.out.println("Error al cerrar la conexion: " + e2.getMessage());
			}
		}
		return rs;
	}
	
	@Override
	public int eliminarUsuario(String dni) {
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLCnn.getCnn();
			String sql = "DELETE FROM usuario WHERE dni = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, dni);
			rs = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error en la sentencia eliminarUsuario: " + e.getMessage());
		} finally {
			try {
				if(con != null) con.close();
				if(pst != null) pst.close();
			} catch (Exception e2) {
				System.out.println("Error al cerrar la conexion: " + e2.getMessage());
			}
		}
		return rs;
	}
	
	@Override
	public List<Usuario> listado() {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pst = null;
		List<Usuario> lista = new ArrayList<Usuario>();
		try {
			con = MySQLCnn.getCnn();
			String sql = "SELECT * FROM usuario";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				Usuario u = new Usuario();
				u.setId(rs.getInt(1));
				u.setNombres(rs.getString(2));
				u.setApePaterno(rs.getString(3));
				u.setApeMaterno(rs.getString(4));
				u.setCelular(rs.getString(5));
				u.setDni(rs.getString(6));
				u.setClave(rs.getString(7));
				u.setIdRol(rs.getInt(8));
				u.setIdEstado(rs.getInt(9));
				lista.add(u);
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
	public List<Usuario> listadoProfesor() {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pst = null;
		List<Usuario> lista = new ArrayList<Usuario>();
		try {
			con = MySQLCnn.getCnn();
			String sql = "SELECT * FROM usuario WHERE idRol=2";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				Usuario u = new Usuario();
				u.setId(rs.getInt(1));
				u.setNombres(rs.getString(2));
				u.setApePaterno(rs.getString(3));
				u.setApeMaterno(rs.getString(4));
				u.setCelular(rs.getString(5));
				u.setDni(rs.getString(6));
				u.setClave(rs.getString(7));
				u.setIdRol(rs.getInt(8));
				u.setIdEstado(rs.getInt(9));
				lista.add(u);
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
	public List<Usuario> listadoEstudiante() {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pst = null;
		List<Usuario> lista = new ArrayList<Usuario>();
		try {
			con = MySQLCnn.getCnn();
			String sql = "SELECT * FROM usuario WHERE idRol=3";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				Usuario u = new Usuario();
				u.setId(rs.getInt(1));
				u.setNombres(rs.getString(2));
				u.setApePaterno(rs.getString(3));
				u.setApeMaterno(rs.getString(4));
				u.setCelular(rs.getString(5));
				u.setDni(rs.getString(6));
				u.setClave(rs.getString(7));
				u.setIdRol(rs.getInt(8));
				u.setIdEstado(rs.getInt(9));
				lista.add(u);
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
	
	public Usuario buscarEstudianteMatriculadoAnioActual(int idEstudiante) {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pst = null;
		Usuario u = null;
		try {
			con = MySQLCnn.getCnn();
			String sql = "{CALL buscarAlumnoMatriculadoPorAnio(?)}";
			pst = con.prepareStatement(sql);
			pst.setInt(1, idEstudiante);
			rs = pst.executeQuery();
			if(rs.next()) {
				u = new Usuario();
				u.setId(rs.getInt(1));
				u.setNombres(rs.getString(2));
				u.setApePaterno(rs.getString(3));
				u.setApeMaterno(rs.getString(4));
				u.setCelular(rs.getString(5));
				u.setDni(rs.getString(6));
				u.setClave(rs.getString(7));
				u.setIdRol(rs.getInt(8));
				u.setIdEstado(rs.getInt(9));
			}
		} catch (Exception e) {
			System.out.println("Error en la sentencia buscarEstudianteMatriculadoAnioActual: " + e.getMessage());
		} finally {
			try {
				if(con != null) con.close();
				if(pst != null) pst.close();
			} catch (Exception e2) {
				System.out.println("Error al cerrar la conexion: " + e2.getMessage());
			}
		}
		return u;
	}
	
	@Override
	public List<Usuario> listadoEstudianteDni(String dni) {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pst = null;
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		try {
			con = MySQLCnn.getCnn();
			String sql = "SELECT * FROM usuario WHERE idRol=3 AND dni LIKE ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, dni+"%");
			rs = pst.executeQuery();
			while(rs.next()) {
				Usuario u = new Usuario();
				u.setId(rs.getInt(1));
				u.setNombres(rs.getString(2));
				u.setApePaterno(rs.getString(3));
				u.setApeMaterno(rs.getString(4));
				u.setCelular(rs.getString(5));
				u.setDni(rs.getString(6));
				u.setClave(rs.getString(7));
				u.setIdRol(rs.getInt(8));
				u.setIdEstado(rs.getInt(9));
				lista.add(u);
			}
		} catch (Exception e) {
			System.out.println("Error en la sentencia listadoEstudianteDni: " + e.getMessage());
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
	public List<Usuario> listadoEstudiantesNombres(String nombres) {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pst = null;
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		try {
			con = MySQLCnn.getCnn();
			String sql = "SELECT * FROM usuario WHERE idRol=3 AND nombres LIKE ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, nombres + "%");
			rs = pst.executeQuery();
			while(rs.next()) {
				Usuario u = new Usuario();
				u.setId(rs.getInt(1));
				u.setNombres(rs.getString(2));
				u.setApePaterno(rs.getString(3));
				u.setApeMaterno(rs.getString(4));
				u.setCelular(rs.getString(5));
				u.setDni(rs.getString(6));
				u.setClave(rs.getString(7));
				u.setIdRol(rs.getInt(8));
				u.setIdEstado(rs.getInt(9));
				lista.add(u);
			}
		} catch (Exception e) {
			System.out.println("Error en la sentencia listadoEstudiantesNombres: " + e.getMessage());
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
	public List<Usuario> listadoEstudiantesApePaterno(String apePaterno) {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pst = null;
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		try {
			con = MySQLCnn.getCnn();
			String sql = "SELECT * FROM usuario WHERE idRol=3 AND apePaterno LIKE ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, apePaterno + "%");
			rs = pst.executeQuery();
			while(rs.next()) {
				Usuario u = new Usuario();
				u.setId(rs.getInt(1));
				u.setNombres(rs.getString(2));
				u.setApePaterno(rs.getString(3));
				u.setApeMaterno(rs.getString(4));
				u.setCelular(rs.getString(5));
				u.setDni(rs.getString(6));
				u.setClave(rs.getString(7));
				u.setIdRol(rs.getInt(8));
				u.setIdEstado(rs.getInt(9));
				lista.add(u);
			}
		} catch (Exception e) {
			System.out.println("Error en la sentencia listadoEstudiantesApePaterno: " + e.getMessage());
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
	public List<Usuario> listadoEstudiantesApeMaterno(String apeMaterno) {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pst = null;
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		try {
			con = MySQLCnn.getCnn();
			String sql = "SELECT * FROM usuario WHERE idRol=3 AND apeMaterno LIKE ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, apeMaterno + "%");
			rs = pst.executeQuery();
			while(rs.next()) {
				Usuario u = new Usuario();
				u.setId(rs.getInt(1));
				u.setNombres(rs.getString(2));
				u.setApePaterno(rs.getString(3));
				u.setApeMaterno(rs.getString(4));
				u.setCelular(rs.getString(5));
				u.setDni(rs.getString(6));
				u.setClave(rs.getString(7));
				u.setIdRol(rs.getInt(8));
				u.setIdEstado(rs.getInt(9));
				lista.add(u);
			}
		} catch (Exception e) {
			System.out.println("Error en la sentencia listadoEstudiantesApeMaterno: " + e.getMessage());
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
	
	public int actualizarCredenciales(String dni, String clave) {
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLCnn.getCnn();
			String sql = "UPDATE usuario SET clave = ?, idEstado = ? WHERE dni = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, clave);
			pst.setInt(2, 2);
			pst.setString(3, dni);
			rs = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error en la sentencia ActualizarCredenciales: " + e.getMessage());
		} finally {
			try {
				if(con != null) con.close();
				if(pst != null) pst.close();
			} catch (Exception e2) {
				System.out.println("Error al cerrar la conexion: " + e2.getMessage());
			}
		}
		return rs;
	}

	@Override
	public Usuario validarAcceso(String dni, String clave) {
		Usuario u = null;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLCnn.getCnn();
			String sql = "{CALL usp_validarAcceso(?,?)}";
			pst = con.prepareStatement(sql);
			pst.setString(1, dni);
			pst.setString(2, clave);
			rs = pst.executeQuery();
			if(rs.next()) {
				u = new Usuario();
				u.setId(rs.getInt(1));
				u.setNombres(rs.getString(2));
				u.setApePaterno(rs.getString(3));
				u.setApeMaterno(rs.getString(4));
				u.setCelular(rs.getString(5));
				u.setDni(rs.getString(6));
				u.setClave(rs.getString(7));
				u.setIdRol(rs.getInt(8));
				u.setIdEstado(rs.getInt(9));
			}
		} catch (Exception e) {
			System.out.println("Error en la sentencia validarAcceso: " + e.getMessage());
		} finally {
			try {
				if(con != null) con.close();
				if(pst != null) pst.close();
			} catch (Exception e2) {
				System.out.println("Error al cerrar la conexion: " + e2.getMessage());
			}
		}
		return u;
	}

	@Override
	public boolean validarRegistro(String dni, int idRol) {
		boolean isValid = false;
		ResultSet rs = null;	
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLCnn.getCnn();
			String sql = "{CALL usp_validarRegistro(?,?)}";
			pst = con.prepareStatement(sql);
			pst.setString(1, dni);
			pst.setInt(2, idRol);
			rs = pst.executeQuery();
			if(rs.next()) {
				isValid = true;
			}
		} catch (Exception e) {
			System.out.println("Error en la sentencia validarRegistro: " + e.getMessage());
		} finally {
			try {
				if(con != null) con.close();
				if(pst != null) pst.close();
			} catch (Exception e2) {
				System.out.println("Error al cerrar la conexion: " + e2.getMessage());
			}
		}
		return isValid;
	}
	
}
