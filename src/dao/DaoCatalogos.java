package dao;


import catalogos.Bodega;
import catalogos.Empaques;
import catalogos.ProveedoresIva;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import validaCatalogos.ConceptosGastos;
import validaCatalogos.ConsultaCanceladas;
import validaCatalogos.ConsultaSaldoGrupo;


public class DaoCatalogos {
        public static gestionConexiones.GestionConexiones gestor;
        private static Connection conexion = null;
        
        public static ArrayList leeTablaBodega() throws SQLException {
            ArrayList arregloBodegas = new ArrayList();
            //Connection conexion = null;
            try{
                gestor = new gestionConexiones.GestionConexiones();
                conexion = gestor.conectaBase();
                Statement sentenciaSql = conexion.createStatement();
                String seleccionaBodega = "SELECT cod_bod,cd FROM bodegas ORDER BY cod_bod";
                ResultSet cursorBodega = sentenciaSql.executeQuery(seleccionaBodega);
                while (cursorBodega.next()){
                    Bodega bodega = new Bodega();
                 
                    bodega.setCod_bod(cursorBodega.getString("cod_bod"));
                    bodega.setCd(cursorBodega.getString("cd"));
				arregloBodegas.add(bodega);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DaoCatalogos.class.getName()).log(Level.SEVERE, null, ex);
            }catch(SQLException e){
                e.printStackTrace();
            }
            finally{
                conexion.close();
            }
            return arregloBodegas;
        }
        
        public static HashMap leeTablaConceptoGastos(String emp)throws SQLException, ClassNotFoundException{
             HashMap mapaConceptosGastos = new HashMap();
            try{
                gestor = new gestionConexiones.GestionConexiones();
                conexion = gestor.conectaBase();
                Statement sentenciaSql = conexion.createStatement();
                String seleccionaConceptos = "SELECT cod_con,cod_sub,descrip FROM gtoscon WHERE cod_emp='"+emp+"'" + " and tipo='D' ORDER BY cod_con,cod_sub";
                ResultSet cursorConceptoGastos = sentenciaSql.executeQuery(seleccionaConceptos);
                while (cursorConceptoGastos.next()){
                    ConceptosGastos gtcon = new ConceptosGastos();
                    gtcon.setCod_con(cursorConceptoGastos.getString("cod_con"));
                    gtcon.setCod_sub(cursorConceptoGastos.getString("cod_sub"));
                    gtcon.setDescrip(cursorConceptoGastos.getString("descrip"));
                    mapaConceptosGastos.put(gtcon.getCod_con().trim()+gtcon.getCod_sub().trim(), gtcon.getDescrip().trim());
                }

            }catch(ClassNotFoundException ex){
                Logger.getLogger(DaoCatalogos.class.getName()).log(Level.SEVERE, null, ex);
            }catch(SQLException e){
                e.printStackTrace();
            }
            finally{
                conexion.close();
            }
            return mapaConceptosGastos;
        }
        
        public static ArrayList leeTablaEmpaques() throws ErrorTransaccion, SQLException{
            ErrorTransaccion error;
            ArrayList arregloEmpaques = new ArrayList();
            try{
                gestor = new gestionConexiones.GestionConexiones();
                conexion = gestor.conectaBase();
                Statement sentenciaSql = conexion.createStatement();
                String seleccionaEmpaques = "SELECT * FROM empaques ORDER BY idEmpaque";
                ResultSet cursorEmpaques = sentenciaSql.executeQuery(seleccionaEmpaques);
                while (cursorEmpaques.next()){
                    Empaques empaques = new Empaques();
                    empaques.setIdEmpaque(cursorEmpaques.getInt("idEmpaque"));
                    empaques.setEmpaque(cursorEmpaques.getString("empaque"));
                    empaques.setCapacidad(cursorEmpaques.getInt("capacidad"));
                    empaques.setPeso(cursorEmpaques.getFloat("peso"));
                    empaques.setVolumen(cursorEmpaques.getFloat("volumen"));
                    arregloEmpaques.add(empaques);
                    //mapaConceptosGastos.put(gtcon.getCod_con().trim()+gtcon.getCod_sub().trim(), gtcon.getDescrip().trim());
                }

            }catch(ClassNotFoundException ex){
                System.out.println("este es el mensaje, va "+ex.getMessage());
                Logger.getLogger(DaoCatalogos.class.getName()).log(Level.SEVERE, null, ex);
            }catch(SQLException e){
                error = new ErrorTransaccion(e);
                System.out.println("va el codigo de error "+error);
                throw(error);
            }
            finally{
                conexion.close();
            }
            return arregloEmpaques;
        }
        
        public static ArrayList leeSaldosCartera(String seleccionaCartera) throws  ErrorTransaccion, SQLException,ClassNotFoundException{
            ErrorTransaccion error;
            ArrayList arregloSaldos = new ArrayList();
            try{
                gestor = new gestionConexiones.GestionConexiones();
                conexion = gestor.conectaBase();
                Statement sentenciaSql = conexion.createStatement();
                //String seleccionaEmpaques = "SELECT * FROM empaques ORDER BY idEmpaque";
                ResultSet cursorSaldos = sentenciaSql.executeQuery(seleccionaCartera);
                while (cursorSaldos.next()){
                    ConsultaSaldoGrupo csg = new ConsultaSaldoGrupo();
                    csg.setCod_cli(cursorSaldos.getString("cod_cli"));
                    csg.setNumtda(cursorSaldos.getString("numtda"));
                    csg.setPedido(cursorSaldos.getString("pedido"));
                    csg.setCod_bod(cursorSaldos.getString("cod_bod"));
                    csg.setCod_fac(cursorSaldos.getString("cod_fac"));
                    csg.setFechapli(cursorSaldos.getDate("fechapli"));
                    csg.setImporte(cursorSaldos.getDouble("importe"));
                    csg.setSaldo(cursorSaldos.getDouble("saldo"));
                    arregloSaldos.add(csg);
                }
            }catch(SQLException e){
                error = new ErrorTransaccion(e);
                //System.out.println("va el codigo de error "+error);
                throw(error);            
            }
        return arregloSaldos;
        }
        
        public static ArrayList leeCanceladasMes(String seleccionaCanceladas) throws ErrorTransaccion, SQLException,ClassNotFoundException{
        ErrorTransaccion error;
        ArrayList arregloCanceladas = new ArrayList();
        try{
            //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            gestor = new gestionConexiones.GestionConexiones();
            conexion = gestor.conectaBase();
            Statement sentenciaSql = conexion.createStatement();
            ResultSet cursorCanceladas = sentenciaSql.executeQuery(seleccionaCanceladas);
            while (cursorCanceladas.next()){
                ConsultaCanceladas cance = new ConsultaCanceladas();
                cance.setTipofac(cursorCanceladas.getString("tipofac"));
                cance.setCod_fac(cursorCanceladas.getString("cod_fac"));
                cance.setCod_cli(cursorCanceladas.getString("cod_cli"));
                cance.setFechafac(cursorCanceladas.getDate("fechafac"));
                cance.setFechacan(cursorCanceladas.getDate("fechacan"));
                cance.setSubfac(cursorCanceladas.getDouble("subfac"));
                cance.setDescfac(cursorCanceladas.getDouble("descfac"));
                cance.setIvafac(cursorCanceladas.getDouble("ivafac"));
                cance.setCod_age(cursorCanceladas.getString("cod_age"));
                cance.setCod_bod(cursorCanceladas.getString("cod_bod"));
                arregloCanceladas.add(cance);
                
            }
        }catch(SQLException e){
            error = new ErrorTransaccion(e);
            throw(error);
        }
        return arregloCanceladas;
        }
        
        public static void escribeTablaEmpaques (String guardaEmpaques) throws ErrorTransaccion,SQLException{
            boolean errorTransaccion = false;
            ErrorTransaccion error;
            try{
                gestor = new gestionConexiones.GestionConexiones();
                conexion = gestor.conectaBase();
                
                Statement sentenciaSql1 = conexion.createStatement();
                String BeginSql = "Begin Transaction";
                sentenciaSql1.executeUpdate(BeginSql);
                
                
                Statement sentenciaSq2 = conexion.createStatement();
                sentenciaSq2.executeUpdate(guardaEmpaques);
            
            }catch(SQLException e){
                error = new ErrorTransaccion(e);
                errorTransaccion = true;
                //System.out.println("va el codigo de error "+error);
                Statement sentenciaSql3 = conexion.createStatement();
                String RollbackSql = "Rollback Transaction";
                sentenciaSql3.executeUpdate(RollbackSql);
                errorTransaccion = true;
                throw(error);
            }catch(ClassNotFoundException ex) {
                System.out.println("este es el mensaje, va "+ex.getMessage());
                Logger.getLogger(DaoCatalogos.class.getName()).log(Level.SEVERE, null, ex);
            }finally {
                if (!errorTransaccion){
                    Statement sentenciaSql4 = conexion.createStatement();
                    String CommitSql = "Commit Transaction";
                    sentenciaSql4.executeUpdate(CommitSql);
                    JOptionPane.showMessageDialog(null,"Transacción Exitosa","Aviso",
                            JOptionPane.ERROR_MESSAGE);
                }
                conexion.close();
            }
        }
        
        public static JTable leeTablaProviva(JTable jTableGastos) throws SQLException {
        DefaultTableModel gastos;
        gastos = (DefaultTableModel) jTableGastos.getModel();
        Object [] fila = null; 
            
            //ArrayList aProviva = new ArrayList();
            ResultSet cursorProviva = null;
            gestor = new gestionConexiones.GestionConexiones();
            try {
                conexion = gestor.conectaBase();
                Statement sentenciaSql = conexion.createStatement();
                String seleccionaProviva = "SELECT rfc,nombre,cod_pvd,cod_emp,cd,edo FROM proviva order by cod_emp,cod_pvd";
                cursorProviva = sentenciaSql.executeQuery(seleccionaProviva);
                while (cursorProviva.next()){
                    fila = new Object[6];
                    fila[0] = cursorProviva.getString("rfc");
                    fila[1] = cursorProviva.getString("nombre");
                    fila[2] = cursorProviva.getString("cod_pvd");
                    fila[3] = cursorProviva.getString("cod_emp");
                    fila[4] = cursorProviva.getString("cd");
                    fila[5] = cursorProviva.getString("edo");
                    gastos.addRow(fila);
                    
                    //ProveedoresIva proveedoresIva = new ProveedoresIva();
                    //proveedoresIva.setRfc(cursorProviva.getString("rfc"));
                    //proveedoresIva.setNombre(cursorProviva.getString("nombre"));
                    //proveedoresIva.setCod_pvd(cursorProviva.getString("cod_pvd"));
                    //proveedoresIva.setCod_emp(cursorProviva.getString("cod_emp"));
                    //proveedoresIva.setCd(cursorProviva.getString("cd"));
                    //proveedoresIva.setEdo(cursorProviva.getString("edo"));
                    //aProviva.add(proveedoresIva);
                }
                //return cursorProviva;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DaoCatalogos.class.getName()).log(Level.SEVERE, null, ex);
            }catch(SQLException e){
                e.printStackTrace();
            }
            finally{
                conexion.close();
            }
            System.out.println("retorno de la tabla");
            return jTableGastos;
        }
}
