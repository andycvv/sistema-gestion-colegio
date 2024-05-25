package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import conexion.MySQLCnn;
import entidad.DetalleMatriculaInfo;
import interfaces.DetalleMatriculaInterface;

public class DetalleMatriculaModel implements DetalleMatriculaInterface{

	@Override
	public List<DetalleMatriculaInfo> listadoInfo() {
		List<DetalleMatriculaInfo> lista = new ArrayList<DetalleMatriculaInfo>();
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLCnn.getCnn();
			String sql = "{CALL }";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lista;
	}

}
