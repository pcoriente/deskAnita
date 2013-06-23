/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import catalogos.EntregasWallMart;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import javax.swing.JOptionPane;

/**
 *
 * @author carlosp
 */
public class DaoEntregasWallMart {
	public static gestionConexiones.GestionConexiones gestor;
        public static EntregasWallMart entregasWallMart = null;
        

        private static boolean errorEscritura;

        public static void escribeEntregasWallMart(HashSet gln) throws SQLException{
            Connection conexion = null;
		try {
                    gestor = new gestionConexiones.GestionConexiones();
                    try {
			conexion = gestor.conectaBase();
                    } catch (ClassNotFoundException e) {
			e.printStackTrace();
                    }
                    Statement sentenciaSql1 = conexion.createStatement();
                    String BeginSql = "Begin Transaction";
                    sentenciaSql1.executeUpdate(BeginSql);
                    
                    String BorraSql = "delete entregasWallMart";
                    sentenciaSql1.executeUpdate(BorraSql);
                    
                    //int i = 0;
                    Iterator it =  gln.iterator();
                    while(it.hasNext()){
                        entregasWallMart=(EntregasWallMart) it.next();
                        //entregasWallMart = entregasWallMart.setIdGln(BeginSql) 
                        //i++;
                        Statement sentencia = conexion.createStatement();
                        String sentenciaSQL = "INSERT entregasWallMart (idGln,idTienda) values("
                                    + "'"+entregasWallMart.getIdGln()+"','"+entregasWallMart.getIdTienda()+"')";
                        sentencia.executeUpdate(sentenciaSQL);
                        //System.out.println("vamos en la iteracion "+ i);
                    }

                    } catch (SQLException e) {
			Statement sentencia2 = conexion.createStatement();
			String sentenciaSQL = "Rollback Transaction";
			sentencia2.executeUpdate(sentenciaSQL);
                        errorEscritura = true;
			JOptionPane.showMessageDialog(null,"Error de Escritura Tabla entregasWallMart","Aviso",
	    			JOptionPane.ERROR_MESSAGE); 			
                        
                    } finally {
                        if(!errorEscritura){
                            Statement sentencia3 = conexion.createStatement();
                            String sentenciaSQL = "Commit Transaction";
                            sentencia3.executeUpdate(sentenciaSQL);
                            entregasWallMart = null;
                        }
                        conexion.close();
                        entregasWallMart = null;
       			JOptionPane.showMessageDialog(null,"Proceso Concluido","Aviso",
	    			JOptionPane.ERROR_MESSAGE); 			

                    }
        }
        public static HashMap leeEntregasWallMart() throws SQLException{
            Connection conexion = null;
            HashMap glns = new HashMap();
            try {
                    gestor = new gestionConexiones.GestionConexiones();
                    try {
			conexion = gestor.conectaBase();
                    } catch (ClassNotFoundException e) {
			e.printStackTrace();
                    }
                    Statement sentenciaSql4 = conexion.createStatement();
                    String selectSql = "select * from entregasWallMart";
                    ResultSet cursorEntregas = sentenciaSql4.executeQuery(selectSql);
                    while (cursorEntregas.next()) {
                        EntregasWallMart entregasWallMart = new EntregasWallMart();
                        entregasWallMart.setIdGln(cursorEntregas.getString("idGln"));
                        entregasWallMart.setIdTienda(cursorEntregas.getString("idTienda"));
                        String cIdGln = entregasWallMart.getIdGln().substring(8,13);
                        String cIdTienda = entregasWallMart.getIdTienda();
                        glns.put(cIdGln,cIdTienda);
                        //System.out.println(cIdGln.length()+" longitud"+cIdTienda);
                    }
                    //sentenciaSql4.executeUpdate(BeginSql);
                    
            }catch(SQLException e){
            }finally{
                conexion.close();
            }
            return glns;
        }
}
