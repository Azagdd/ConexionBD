package ppal;

import java.sql.Connection;
import utilidades.ConexionBD;

public class Main {

	public static void main(String[] args) {
		
		ConexionBD conexionBD = new ConexionBD();
		
		System.out.println("Conectando a la base de datos tienda...");
		
		Connection con = conexionBD.getConexion();
		System.out.println("Liberando la conexión");
		
		// operaciones contra la base de datos....
		
		
		
		conexionBD.desconectar();
		

	}

}
