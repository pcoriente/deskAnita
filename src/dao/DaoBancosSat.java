/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import catalogos.BancosClientes;
import catalogos.BancosSat;
import catalogos.Cliente;
import catalogos.Direccion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JOptionPane;
import jxl.Cell;

/**
 *
 * @author carlosp
 */
public class DaoBancosSat {

    public static gestionConexiones.GestionConexiones gestor;
    public static BancosSat bancosSat = null;
    public static BancosClientes bancosClientes = null;
    protected static ErrorTransaccion error;
    private static boolean errorEscritura;
    public static Connection conexion = null;
    private static Cliente cliente = null;

    public static void escribeBancosSat(HashSet bco) throws SQLException {
        //Connection conexion = null;
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

            String BorraSql = "delete bancosSat";
            sentenciaSql1.executeUpdate(BorraSql);

            Iterator it = bco.iterator();
            while (it.hasNext()) {
                bancosSat = (BancosSat) it.next();
                Statement sentencia = conexion.createStatement();
                String sentenciaSQL = "INSERT bancosSat (codigoBanco,rfc,razonSocial,nombreCorto) values("
                        + "'" + bancosSat.getCodigoBanco() + "','" + bancosSat.getRfc() + "','" + bancosSat.getRazonSocial() + "','" + bancosSat.getNombreCorto() + "')";
                sentencia.executeUpdate(sentenciaSQL);
            }

        } catch (SQLException e) {
            Statement sentencia2 = conexion.createStatement();
            String sentenciaSQL = "Rollback Transaction";
            sentencia2.executeUpdate(sentenciaSQL);
            errorEscritura = true;
            error = new ErrorTransaccion(e);
            JOptionPane.showMessageDialog(null, "Error de Escritura Tabla Bancos SAT", "Aviso",
                    JOptionPane.ERROR_MESSAGE);

        } finally {
            if (!errorEscritura) {
                Statement sentencia3 = conexion.createStatement();
                String sentenciaSQL = "Commit Transaction";
                sentencia3.executeUpdate(sentenciaSQL);
                bancosSat = null;
            }
            conexion.close();
            bancosSat = null;
            JOptionPane.showMessageDialog(null, "Proceso Concluido", "Aviso",
                    JOptionPane.ERROR_MESSAGE);

        }

    }

    public static HashMap leeBancosSat() throws SQLException {
        //Connection conexion = null;
        HashMap bcos = new HashMap();

        gestor = new gestionConexiones.GestionConexiones();
        try {
            conexion = gestor.conectaBase();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Statement sentencia = conexion.createStatement();
        try {
            String sentenciaSQL = "SELECT idBanco,codigoBanco,nombreCorto FROM bancosSat";
            ResultSet cursorBcos = sentencia.executeQuery(sentenciaSQL);
            while (cursorBcos.next()) {

                BancosSat bancosSat = new BancosSat();

                bancosSat.setIdBanco(cursorBcos.getInt("idBanco"));
                bancosSat.setCodigoBanco(cursorBcos.getInt("codigoBanco"));
                bancosSat.setNombreCorto(cursorBcos.getString("nombreCorto"));


                String llave = Integer.toString(bancosSat.getCodigoBanco());
                String datos = Integer.toString(bancosSat.getIdBanco());

                bcos.put(llave.trim(), datos.trim());
            }

        } catch (SQLException e) {
            //Statement sentencia2 = conexion.createStatement();
            //sentencia2.executeUpdate(sentenciaSQL);
            errorEscritura = true;
            error = new ErrorTransaccion(e);
            JOptionPane.showMessageDialog(null, "Error de Escritura Tabla Bancos SAT", "Aviso",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            conexion.close();
        }
        return bcos;
    }

    public static void agregarDireccion(Direccion d) throws SQLException, ClassNotFoundException {
        //Connection conexion = null;
        try {
            gestor = new gestionConexiones.GestionConexiones();
            try {
                conexion = gestor.conectaBase();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            conexion.setAutoCommit(false);
            Statement sentenciaSql = conexion.createStatement();
            String BeginSql = "Begin Transaction";
            CallableStatement procAlm = conexion.prepareCall("{ call sp_direcciones(?,?,?,?,?,?,?,?,?,?,?) }");
            procAlm.setString(1, d.getCalle());
            procAlm.setString(2, d.getNumeroExterior());
            procAlm.setString(3, d.getNumeroInterior());
            procAlm.setString(4, d.getColonia());
            procAlm.setString(5, d.getLocalidad());
            procAlm.setString(6, d.getReferencia());
            procAlm.setString(7, d.getMunicipio());
            procAlm.setString(8, d.getEstado());
            procAlm.setInt(9, d.getIdPais());
            procAlm.setString(10, d.getCodigoPostal());
            procAlm.setString(11, d.getNumeroLocalizacion());
            // ejecutar el SP
            procAlm.execute();
            conexion.commit();
        } catch (SQLException ex) {
            conexion.rollback();
            throw (ex);
        } finally {
            conexion.close();
        }
    }

    public static void escribeBancosClientes(ArrayList bancosClientes, HashMap bcos) throws SQLException {
        Boolean resultVacio;
        String datosCta;
        try {
            gestor = new gestionConexiones.GestionConexiones();
            try {
                conexion = gestor.conectaBase();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            ResultSet cursorCtes = null;
            //conexion.setAutoCommit(false);
            Statement sentencia3 = conexion.createStatement();
            sentencia3.executeUpdate("Begin Transaction");

            /*Iterator iter = bcos.entrySet().iterator();
            Map.Entry e;
            
            while (iter.hasNext()) {
            e = (Map.Entry)iter.next();
            System.out.println("Clave: " + e.getKey() + " | Valor: " + e.getValue());
            }*/

            Statement preparaInstruccion = conexion.createStatement();
            Iterator it = bancosClientes.iterator();

            while (it.hasNext()) {
                BancosClientes bcoCte = (BancosClientes) it.next();
                //String codCli = bcoCte.getCodigoCliente().trim();

                resultVacio = true;
                Statement preparaInstruccion2 = conexion.createStatement();
                cursorCtes = preparaInstruccion2.executeQuery("select * from clientesBancos where codigoCliente='" + bcoCte.getCodigoCliente() + "' and numCtaPago='" + bcoCte.getNumCtaPago().trim() + "'");
                Object y = bcos.get(String.valueOf(bcoCte.getIdBanco()));

                if (y != null) {
                    //System.out.println("No procede  " + y);

                    //System.out.println("SI PROCEDE  " + y+"cuenta "+bcoCte.getNumCtaPago()+" cliente "+bcoCte.getCodigoCliente());
                    datosCta = (String) bcos.get(bcoCte.getIdBanco());

                    while (cursorCtes.next()) {

                        preparaInstruccion.executeUpdate("UPDATE clientesBancos set idBanco='" + y + "',"
                                + "numCtaPago='" + bcoCte.getNumCtaPago().trim() + "',medioPago='" + bcoCte.getMedioPago().trim() + "'"
                                + " WHERE idBanco='" + bcoCte.getIdBanco() + "' and codigoCliente='" + bcoCte.getCodigoCliente() + "' and numCtaPago='" + bcoCte.getNumCtaPago() + "'");
                        resultVacio = false;
                    }
                }

                if (resultVacio & y != null) {
                    preparaInstruccion.executeUpdate("INSERT clientesBancos (codigoCliente,idBanco,numCtaPago,medioPago) values "
                            + "('" + bcoCte.getCodigoCliente() + "','" + y + "',"
                            + "'" + bcoCte.getNumCtaPago() + "','" + bcoCte.getMedioPago() + "')");
                    System.out.println("Cliente " + bcoCte.getCodigoCliente() + "Cuenta Banco dos " + bcoCte.getNumCtaPago());

                }
            }
        } catch (SQLException e) {
            Statement sentencia2 = conexion.createStatement();
            String sentenciaSQL = "Rollback Transaction";
            sentencia2.executeUpdate(sentenciaSQL);
            errorEscritura = true;
            error = new ErrorTransaccion(e);
            JOptionPane.showMessageDialog(null, "Error de Escritura Tabla Bancos SAT", "Aviso",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            if (!errorEscritura) {
                Statement sentencia3 = conexion.createStatement();
                String sentenciaSQL = "Commit Transaction";
                sentencia3.executeUpdate(sentenciaSQL);
                bancosClientes = null;
            }
            conexion.close();
            bancosClientes = null;
            JOptionPane.showMessageDialog(null, "Proceso Concluido", "Aviso",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
//
      public static void escribeBancosGrupos(ArrayList bancosClientes, HashMap bcos) throws SQLException {
        Boolean resultVacio;
        String datosCta;
        try {
            gestor = new gestionConexiones.GestionConexiones();
            try {
                conexion = gestor.conectaBase();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            ResultSet cursorCtes = null;
            Statement sentencia3 = conexion.createStatement();
            sentencia3.executeUpdate("Begin Transaction");
            Statement preparaInstruccion = conexion.createStatement();
            Iterator it = bancosClientes.iterator();
            while (it.hasNext()) {
                BancosClientes bcoCte = (BancosClientes) it.next();
                resultVacio = true;
                Statement preparaInstruccion2 = conexion.createStatement();
                cursorCtes = preparaInstruccion2.executeQuery("SELECT * FROM gruposBancos where codigoGrupo='" + bcoCte.getCodigoCliente() + "' and numCtaPago='" + bcoCte.getNumCtaPago().trim() + "'");
                Object y = bcos.get(String.valueOf(bcoCte.getIdBanco()));

                if (y != null) {
                    datosCta = (String) bcos.get(bcoCte.getIdBanco());
                    while (cursorCtes.next()) {

                        preparaInstruccion.executeUpdate("UPDATE gruposBancos set idBanco='" + y + "',"
                                + "numCtaPago='" + bcoCte.getNumCtaPago().trim() + "',medioPago='" + bcoCte.getMedioPago().trim() + "'"
                                + " WHERE idBanco='" + bcoCte.getIdBanco() + "' and codigoGrupo='" + bcoCte.getCodigoCliente() + "' and numCtaPago='" + bcoCte.getNumCtaPago() + "'");
                        resultVacio = false;
                    }
                }

                if (resultVacio & y != null) {
                    preparaInstruccion.executeUpdate("INSERT gruposBancos (codigoGrupo,idBanco,numCtaPago,medioPago) values "
                            + "('" + bcoCte.getCodigoCliente() + "','" + y + "',"
                            + "'" + bcoCte.getNumCtaPago() + "','" + bcoCte.getMedioPago() + "')");
                    System.out.println("Cliente " + bcoCte.getCodigoCliente() + "Cuenta Banco dos " + bcoCte.getNumCtaPago());

                }
            }
        } catch (SQLException e) {
            Statement sentencia2 = conexion.createStatement();
            String sentenciaSQL = "Rollback Transaction";
            sentencia2.executeUpdate(sentenciaSQL);
            errorEscritura = true;
            error = new ErrorTransaccion(e);
            JOptionPane.showMessageDialog(null, "Error de Escritura Tabla Bancos SAT", "Aviso",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            if (!errorEscritura) {
                Statement sentencia3 = conexion.createStatement();
                String sentenciaSQL = "Commit Transaction";
                sentencia3.executeUpdate(sentenciaSQL);
                bancosClientes = null;
            }
            conexion.close();
            bancosClientes = null;
            JOptionPane.showMessageDialog(null, "Proceso Concluido", "Aviso",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
  
    public static ArrayList leeClientesGpos(Cell cellGpo) throws SQLException {
        ArrayList gpoCte = new ArrayList();
        try {
            gestor = new gestionConexiones.GestionConexiones();
            try {
                conexion = gestor.conectaBase();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            ResultSet cursorCteGpo = null;
            //conexion.setAutoCommit(false);
            Statement preparaInstruccion = conexion.createStatement();
            cursorCteGpo = preparaInstruccion.executeQuery("select cod_cli,cod_gru from clientes where cod_gru='" + cellGpo.getContents() + "'");

            while (cursorCteGpo.next()) {
                cliente = new Cliente();
                cliente.setCod_cli(cursorCteGpo.getString("cod_cli"));
                cliente.setCod_gru(cursorCteGpo.getString("cod_gru"));
                gpoCte.add(cliente);
                //System.out.println("" + cursorCteGpo.getString("cod_cli") + "grupo " + cursorCteGpo.getString("cod_gru"));
            }
        } finally {
            conexion.close();
        }
        return gpoCte;
    }
}