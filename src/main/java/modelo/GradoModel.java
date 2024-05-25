package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conexion.MySQLCnn;
import entidad.Grado;
import interfaces.GradoInterface;

public class GradoModel implements GradoInterface {

	@Override
	public List<Grado> listado() {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pst = null;
		List<Grado> lista = new ArrayList<Grado>();
		try {
			con = MySQLCnn.getCnn();
			String sql = "SELECT * FROM grado";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				Grado g = new Grado();
				g.setIdGrado(rs.getInt(1));
				g.setNombre(rs.getString(2));
				lista.add(g);
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
	
}
