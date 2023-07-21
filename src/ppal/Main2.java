package ppal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import utilidades.ConexionBD;

public class Main2 {

	public static void main(String[] args) {
		
		Scanner teclado = new Scanner(System.in);
		
		System.out.println("Introduce el precio");
		double precio =teclado.nextDouble();
		teclado.nextLine();
		System.out.println("Introduce el nombre ");
		String nombre = teclado.nextLine();
		
		
		ConexionBD conexionBD = new ConexionBD();
		
		System.out.println("Conectando a la base de datos tienda...");
		
		Connection con = conexionBD.getConexion();
		System.out.println("Liberando la conexión");
		
		// operaciones contra la base de datos....
		PreparedStatement sentencia=null;
		ResultSet resultado = null;
		try {
			String consulta= "select codigo, nombre, precio, Codigo_fabricante, canon from producto"
					+ " where precio>? and nombre like concat('%',?,'%')";
			System.out.println(consulta);
			sentencia = con.prepareStatement(consulta);
			
			// establecemos los parámetros
			sentencia.setDouble(1, precio);
			sentencia.setString(2, nombre);
			
			
			
			resultado = sentencia.executeQuery();
			
			System.out.println("Código\tNombre\ttPrecio\tCodigo_fabricante\tCanon");
			System.out.println("------------------------------------------------");
			while(resultado.next()) {
				
				int codigo = resultado.getInt("codigo");
				nombre = resultado.getString("nombre");
				precio = resultado.getDouble("precio");
				int codigoFabricante = resultado.getInt("codigo_fabricante");
				float canon = resultado.getFloat("canon");
				
				System.out.printf("%d\t%-30s\t%.2f\t%d\t%.2f\n", 
						codigo, nombre,precio, codigoFabricante, canon);
			}
		} catch (SQLException e) {
			System.out.println("Error en la base de datos "+e.getMessage());
		} finally {
			try {
				resultado.close();
				sentencia.close();
			} catch (SQLException e) {
				System.out.println("Error liberando recursos");
			}
		}
		System.out.println("Cerrando la conexión...");
		conexionBD.desconectar();
		

	}

}
