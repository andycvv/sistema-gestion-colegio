package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conexion.MySQLCnn;
import entidad.Horario;
import entidad.Usuario;
import interfaces.HorarioInterface;

public class HorarioModel implements HorarioInterface {

	@Override
	public List<Horario> listado() {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pst = null;
		List<Horario> lista = new ArrayList<Horario>();
		try {
			con = MySQLCnn.getCnn();
			String sql = "SELECT * FROM horario";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				Horario h = new Horario();
				h.setIdHorario(rs.getInt(1));
				h.setDia(rs.getString(2));
				h.setHoraInicio(rs.getString(3));
				h.setHoraFin(rs.getString(4));
				h.setCodAula(rs.getString(5));
				lista.add(h);
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
	public List<Horario> listadoHorarioAsignado() {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pst = null;
		List<Horario> lista = new ArrayList<Horario>();
		try {
			con = MySQLCnn.getCnn();
			String sql = "{CALL usp_salonesAsignados}";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				Horario h = new Horario();
				h.setIdHorario(rs.getInt(1));
				h.setDia(rs.getString(2));
				h.setHoraInicio(rs.getString(3));
				h.setHoraFin(rs.getString(4));
				h.setCodAula(rs.getString(5));
				lista.add(h);
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
