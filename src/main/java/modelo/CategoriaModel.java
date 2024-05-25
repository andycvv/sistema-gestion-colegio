package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import conexion.MySQLCnn;
import entidad.Categoria;
import interfaces.CategoriaInterface;

public class CategoriaModel implements CategoriaInterface{

	@Override
	public int registrarCategoria(Categoria c) {
		// declaracion de variables
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		// entrada de datos
		try {
			con = MySQLCnn.getCnn();
			String sql = "INSERT categoria VALUES (null, ?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, c.getNombre());
			rs = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error en la sentencia registrarCategoria: " + e.getMessage());
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
	public ArrayList<Categoria> listado() {
		// declaracion de variables
		ResultSet rs = null;
		ArrayList<Categoria> lista = new ArrayList<Categoria>();
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLCnn.getCnn();
			String sql = "SELECT * FROM categoria";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				Categoria c = new Categoria();
				c.setIdCategoria(rs.getInt(1));
				c.setNombre(rs.getString(2));
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
	public int eliminarCategoria(int idCategoria) {
		// declaracion de variables
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLCnn.getCnn();
			String sql = "DELETE FROM categoria WHERE id = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, idCategoria);
			rs = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error en la sentencia eliminarCategoria: " + e.getMessage());
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
	public int editarCategoria(Categoria c) {
		// declaracion de variables
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		// entrada de datos
		try {
			con = MySQLCnn.getCnn();
			String sql = "UPDATE categoria SET nombre = ? WHERE idCategoria = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, c.getNombre());
			pst.setInt(2, c.getIdCategoria());
			rs = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error en la sentencia editarCategoria: " + e.getMessage());
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

}
