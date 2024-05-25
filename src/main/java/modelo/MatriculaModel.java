package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexion.MySQLCnn;
import entidad.DetalleMatricula;
import entidad.Matricula;
import entidad.MatriculaReporte;
import interfaces.MatriculaInterface;

public class MatriculaModel implements MatriculaInterface {

	@Override
	public int registrarMatricula(Matricula matricula, ArrayList<DetalleMatricula> detalle) {
		Connection con = null;
		PreparedStatement pst1 = null;
		PreparedStatement pst2 = null;
		PreparedStatement pst3 = null;
		int rs = -1;
		try {
			// establecer la conexion con la bd
			con = MySQLCnn.getCnn();
			// anular el automcommit
			con.setAutoCommit(false);
			// crear la primera sentencia
			String sql1 = "INSERT matricula VALUES (null, ?,?,?,?)";
			// indicar la sentencia al primer pst
			pst1 = con.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
			// establecer los parametros
			pst1.setInt(1, matricula.getIdEstudiante());
			pst1.setString(2, matricula.getFecha());
			pst1.setInt(3, matricula.getIdNivel());
			pst1.setInt(4, matricula.getIdGrado());
			// ejecutar
			rs = pst1.executeUpdate();
	        // obtener el ID de la matrícula recién insertada
	        int idMatricula = -1;
	        try (ResultSet generatedKeys = pst1.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                idMatricula = generatedKeys.getInt(1);
	            } else {
	            	System.out.println("No se pudo obtener el ID de la matrícula");
	            }
	        } catch (SQLException e) {
	            System.out.println("Error al obtener las claves generadas: " + e.getMessage());
	        }
			// crear la segunda sentencia
			String sql2 = "INSERT detalleMatricula VALUES (?,?)";
			// recorrer el detalleMatricula
			for(DetalleMatricula d : detalle) {
				pst2 = con.prepareStatement(sql2);
				// establecer los parametros
				pst2.setInt(1, idMatricula);
				pst2.setInt(2, d.getIdCurso());
				// ejecutar
				rs = pst2.executeUpdate();
			}
			// crear la tercera sentencia
			String sql3 = "{CALL ActualizarCapacidadAulasPorMatricula(?)}";
			// recorrer el detalleMatricula
			pst3 = con.prepareStatement(sql3);
			// establecer los parametros
			pst3.setInt(1, idMatricula);
			// ejecutar
			rs = pst3.executeUpdate();
			// confirmar la transaccion
			con.commit();
		} catch (Exception e) {
			try {
				con.rollback();
				System.out.println("Error en la sentencia registrarMatricula: " + e.getMessage());
				rs = -1;
			} catch (SQLException e2) {
				System.out.println("Error en el rollback: " + e2.getMessage());
			}
		} finally {
			try {
				if(pst3 != null) pst3.close();
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
	public List<MatriculaReporte> listadoPorAnioYNivel(int anio, int idNivel) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<MatriculaReporte> lista = new ArrayList<MatriculaReporte>();
		try {
			con = MySQLCnn.getCnn();
			String sql = "{CALL ReporteMatriculasPorAnioYNivel(?,?)}";
			pst = con.prepareStatement(sql);
			pst.setInt(1, anio);
			pst.setInt(2, idNivel);
			rs = pst.executeQuery();
			while(rs.next()) {
				MatriculaReporte mr = new MatriculaReporte();
				mr.setIdMatricula(rs.getInt(1));
				mr.setEstudiante(rs.getString(2));
				mr.setDni(rs.getString(3));
				mr.setFecha(rs.getString(4));
				mr.setNivel(rs.getString(5));
				mr.setGrado(rs.getString(6));
				lista.add(mr);
			}
		} catch (Exception e) {
			System.out.println("Error en la sentencia listadoPorAnioYNivel: " + e.getMessage());
		} finally {
			try {
				if(pst != null) pst.close();
				if (con!= null) con.close();
			} catch (Exception e2) {
				System.out.println("Error al cerrar la conexion: " + e2.getMessage());
			}
		}
		return lista;
	}

}
