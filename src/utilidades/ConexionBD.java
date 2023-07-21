package utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

	// datos de la conexión
	private static String database="tienda";
	private static String user="tienda";
	private static String pass="1234";
	private static String url="jdbc:mysql://localhost:3306/"+database;
	
	// Objeto Connection
	private Connection conexion = null; 
	

	public Connection getConexion() {
		if (conexion!=null) {
			return this.conexion;
		}
		
		try {
			// REgistramos el driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// Pedir un objeto Connection
			this.conexion=DriverManager.getConnection(url, user, pass);
			System.out.println("Conexión realizada correctamente");
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("No ha se ha podido registrar el driver. "
					+ "Consulte con el adminsitrador");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("No se ha podido conectar. "+e.getMessage());
			e.printStackTrace();
		}
		return this.conexion;
	}
	
	
	public void desconectar() {
		try {
			this.conexion.close();
		} catch (SQLException e) {
			System.out.println("Error liberando la conexión.");
			e.printStackTrace();
		}
	}
	
	
}
