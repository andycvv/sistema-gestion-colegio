package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLCnn {

	public static Connection getCnn() {

		Connection c = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			String url = "jdbc:mysql://localhost:3306/santaCruz";
			String user = "root";
			String pwd = "159263";
			c = DriverManager.getConnection(url, user, pwd);
		} catch (ClassNotFoundException e) {
			System.out.println("Error >> dirver no instalado" + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Error >> de conexion con la BD" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error >> general" + e.getMessage());
		}

		return c;
	}

	public void closeConnection(Connection c) {
		if (c != null) {
			try {
				c.close();
			} catch (SQLException e) {
				System.out.println("Problemas al cerrar la conexion");
			}
		}
	}

	public static void main(String[] args) {
		MySQLCnn cnn = new MySQLCnn();
		Connection connection = null;
		try {
			connection = MySQLCnn.getCnn();
			if (connection != null) {
				System.out.println("¡Conexión exitosa!");
			}
		} catch (Exception e) {
			System.err.println("Error al conectar a la base de datos: " + e.getMessage());
		} finally {
			cnn.closeConnection(connection);
		}
	}

}
