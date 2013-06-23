package utilerias;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PruebasValidaciones {
	private static gestionConexiones.GestionConexiones gestor;
	private static String nombreTabla = "empresas";

	public static void main(String[] args) throws IOException, SQLException {
//		Connection conexion = null;
//		try{
//			gestor = new gestionConexiones.GestionConexiones();	
//			
//			conexion = gestor.crearConexion();
//			Statement sentencia = conexion.createStatement();
//			String sentenciaSQL = "SELECT cod_emp,nombre FROM "+nombreTabla+" ORDER BY cod_emp";
//			ResultSet cursorEmpresa  = sentencia.executeQuery(sentenciaSQL);
//			System.out.println("Cursor Empresa: " + cursorEmpresa);
//			
//			
//		}catch(SQLException e){
//			e.printStackTrace();
//			return;	
//		}catch(IOException e){
//			e.printStackTrace();
//			return;	
//		}
//		
//		finally{
//			conexion.close();
//		}
	}

}
