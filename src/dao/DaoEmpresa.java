package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import catalogos.Cliente;
import catalogos.Empresa;
import catalogos.Grupo;
import catalogos.GruposCuentas;
import catalogos.MetodosPago;
import catalogos.Producto;
import controladores.AsignaValoresVariables;
import dominio.CabeceroPedido;
import dominio.DetallePedido;
import dominio.Pedido;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DaoEmpresa {
    // Parametros conexion
    //private static gestorConexiones.GestorConexiones gestor;
    ////public static gestorConexiones.GestorConexiones gestor; ESTA ES LA BUENA

    public static gestionConexiones.GestionConexiones gestor;
    private static String nombreTabla = "";

    public static ArrayList leeTablaEmpresa() throws ErrorTransaccion, SQLException {
        ErrorTransaccion error;
        nombreTabla = "empresas";
        Connection conexion = null;
        ArrayList arregloEmpresas = new ArrayList();

        try {
            ////gestor = new gestorConexiones.GestorConexiones();
            gestor = new gestionConexiones.GestionConexiones();

            ////conexion = gestor.crearConexion();
            try {
                conexion = gestor.conectaBase();
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //conexion.setAutoCommit(false);
            Statement sentencia = conexion.createStatement();
            String sentenciaSQL = "SELECT cod_emp,nombre FROM " + nombreTabla;
            ResultSet cursorEmpresa = sentencia.executeQuery(sentenciaSQL);

            while (cursorEmpresa.next()) {
                Empresa empresa = new Empresa();
                empresa.setCod_emp(cursorEmpresa.getString("cod_emp"));
                empresa.setNombre(cursorEmpresa.getString("nombre"));
                arregloEmpresas.add(empresa);

            }

        } catch (SQLException e) {
            //e.printStackTrace();
            error = new ErrorTransaccion(e);
            throw (error);
            //} catch (IOException e) {
            //	e.printStackTrace();
        } finally {
            conexion.close();
        }
        return arregloEmpresas;
    }

    public static ArrayList leeTablaCuentas(int codigoGpo) throws ErrorTransaccion, SQLException {
        ErrorTransaccion error;
        Connection conexion = null;
        ArrayList arregloCtasBcos = new ArrayList();
        try {
            gestor = new gestionConexiones.GestionConexiones();
            try {
                conexion = gestor.conectaBase();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Statement sentencia = conexion.createStatement();
            String sentenciaSQL = "SELECT idGrupoBanco,codigoGrupo,numCtaPago,medioPago,nombreCorto,gb.idBanco FROM gruposBancos gb, bancosSat bs where gb.idbanco=bs.idbanco and codigoGrupo='"+codigoGpo+"'";
            ResultSet cursorGruposCtas = sentencia.executeQuery(sentenciaSQL);

            while (cursorGruposCtas.next()) {
                GruposCuentas gc = new GruposCuentas();
                gc.setIdGrupoBanco(cursorGruposCtas.getInt("idGrupoBanco"));
                gc.setCodigoGrupo(cursorGruposCtas.getInt("codigoGrupo"));
                gc.setNumCtaPago(cursorGruposCtas.getString("numCtaPago"));
                gc.setNombreBanco(cursorGruposCtas.getString("nombreCorto"));
                gc.setIdBanco(cursorGruposCtas.getInt("idBanco"));
                System.out.println("si entre al while de las cuentas ");
                arregloCtasBcos.add(gc);
            }
        } catch (SQLException e) {
            error = new ErrorTransaccion(e);
            throw (error);
        } finally {
            conexion.close();
        }
        System.out.println("Si cargo cuentas");
        return arregloCtasBcos;
    }
    public static ArrayList leeTablaMetodos() throws ErrorTransaccion, SQLException {
        ErrorTransaccion error;
        Connection conexion = null;
        ArrayList arregloMetodos = new ArrayList();

        try {
            gestor = new gestionConexiones.GestionConexiones();
            try {
                conexion = gestor.conectaBase();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Statement sentencia = conexion.createStatement();
            String sentenciaSQL = "SELECT idLeyenda,leyenda FROM leyendasPagos";
            ResultSet cursorMetodos = sentencia.executeQuery(sentenciaSQL);

            while (cursorMetodos.next()) {
                MetodosPago mp = new MetodosPago();
                mp.setIdLeyenda(cursorMetodos.getInt("idLeyenda"));
                mp.setLeyenda(cursorMetodos.getString("leyenda"));
                arregloMetodos.add(mp);
            }
        } catch (SQLException e) {
            error = new ErrorTransaccion(e);
            throw (error);
        } finally {
            conexion.close();
        }
        return arregloMetodos;
    }

    public static ArrayList leeTablaGrupo() throws SQLException {
        nombreTabla = "grupos";
        Connection conexion = null;
        ArrayList arregloGrupos = new ArrayList();

        try {
            ////gestor = new gestorConexiones.GestorConexiones();
            gestor = new gestionConexiones.GestionConexiones();

            ////conexion = gestor.crearConexion();
            try {
                conexion = gestor.conectaBase();
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Statement sentencia = conexion.createStatement();
            String sentenciaSQL = "SELECT cod_gru,nombre,nomgen FROM "
                    + nombreTabla + " order by cod_gru";
            ResultSet cursorGrupo = sentencia.executeQuery(sentenciaSQL);

            while (cursorGrupo.next()) {
                Grupo grupo = new Grupo();
                grupo.setCod_gru(cursorGrupo.getString("cod_gru"));
                grupo.setNombre(cursorGrupo.getString("nombre"));
                grupo.setNomgen(cursorGrupo.getString("nomgen"));
                arregloGrupos.add(grupo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            ////} catch (IOException e) {
            ////	e.printStackTrace();
        } finally {
            conexion.close();
        }
        return arregloGrupos;
    }

    public static HashMap leeTablaCliente(String cGrupo) throws SQLException {
        Connection conexion = null;
        HashMap aCval = new HashMap();
        String cTda = null;
        String cCliente = null;
        String nTienda = null;
        try {
            ////gestor = new gestorConexiones.GestorConexiones();
            gestor = new gestionConexiones.GestionConexiones();

            ////conexion = gestor.crearConexion();
            try {
                conexion = gestor.conectaBase();
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Statement sentencia = conexion.createStatement();
            // String sentenciaSQL =
            // "SELECT cod_gru,nombre,nomgen FROM "+nombreTabla;
            String sentenciaSQL = "SELECT c.cod_cli,c.cod_gru,c.numtda,a.cod_bod,c.cod_age,c.bperm from CLIENTES c, AGENTES a WHERE c.cod_gru="
                    + cGrupo + " and c.cod_age in(SELECT cod_age FROM agentes WHERE c.cod_age=a.cod_age)";
            ResultSet cursorCliente = sentencia.executeQuery(sentenciaSQL);

            while (cursorCliente.next()) {
                try {
                    Cliente cliente = new Cliente();
                    cliente.setCod_cli(cursorCliente.getString("cod_cli"));
                    cliente.setCod_gru(cursorCliente.getString("cod_gru"));
                    cCliente = cliente.getCod_cli();
                    cTda = String.valueOf(
                            (cursorCliente.getString("numtda"))).replace(" ", "");

                    if (cTda.isEmpty()) {
                        cTda = "0";
                    }
                    int nTda = Integer.parseInt(cTda);
                    //System.out.println("este es el numero a string "+nTda);
                    nTienda = String.valueOf(nTda).trim();
                    cliente.setCod_bod(cursorCliente.getString("cod_bod"));
                    cliente.setCod_age(cursorCliente.getString("cod_age"));
                    cliente.setBperm(cursorCliente.getString("bperm"));
                    //cCliente = cliente.getCod_cli();
                    String cDatos = cliente.getCod_cli() + cliente.getCod_gru()
                            + cliente.getCod_bod() + cliente.getCod_age()
                            + cliente.getBperm();
                    aCval.put(nTienda, cDatos);
                    //System.out.println("número de Tienda: "+nTienda+" Cliente: "+cDatos+" longitud "+nTienda.length());

                } catch (NumberFormatException e) {
                    //JOptionPane.showMessageDialog(null,"Numero invalido de Tienda "+nTienda+" Cliente: "+cCliente,"Aviso",
                    //        JOptionPane.ERROR_MESSAGE);
                    //System.out.println("Error en número de Tienda: "+nTienda+" Cliente: "+cCliente);
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
            ////} catch (IOException e) {
            ////	e.printStackTrace();
        } finally {
            conexion.close();
        }

        //Iterator itr = aCval.entrySet().iterator();
        //while (itr.hasNext()) {
        //    Map.Entry e = (Map.Entry)itr.next();
        //    System.out.println("clave: "+e.getKey()+"valor:"+e.getValue());                
        //}      
        return aCval;
    }

    public static HashMap leeTablaProducto(String cGrupo, String cEmpresa)
            throws SQLException {
        Connection conexion = null;
        HashMap aPval = new HashMap();

        try {
            ////gestor = new gestorConexiones.GestorConexiones();
            gestor = new gestionConexiones.GestionConexiones();

            ////conexion = gestor.crearConexion();
            try {
                conexion = gestor.conectaBase();
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Statement sentencia = conexion.createStatement();
            String sentenciaSQL = null;
            System.out.println("Grupo que envia al DAO "+cGrupo);
            // "SELECT cod_gru,nombre,nomgen FROM "+nombreTabla;
            if (cGrupo.equals("001") || cGrupo.equals("002") || cGrupo.equals("016") || cGrupo.equals("019")) {
                sentenciaSQL = "SELECT l.precio,p.descrip,b.sku as codbar,p.pzasepq,p.biva,p.cod_pro,p.cod_emp FROM listas l, producto p, bolgpo b WHERE l.cod_emp="
                        + cEmpresa + " and l.cod_gru="
                        + cGrupo + " and l.status='A' and l.cod_emp+l.cod_pro=p.cod_emp+p.cod_pro and l.cod_emp+l.cod_pro+l.cod_gru=b.cod_emp+b.cod_pro+b.cod_gru";
            } else {
                sentenciaSQL = "SELECT l.precio,p.descrip,p.codbar,p.pzasepq,p.biva,p.cod_pro,p.cod_emp FROM listas l, producto p WHERE l.cod_emp="
                        + cEmpresa + " and l.cod_gru="
                        + cGrupo + " and l.cod_emp+l.cod_pro=p.cod_emp+p.cod_pro and l.status='A'";
            }

            ResultSet cursorProducto = sentencia.executeQuery(sentenciaSQL);

            while (cursorProducto.next()) {
                Producto producto = new Producto();
                producto.setCod_emp(cursorProducto.getString("cod_emp"));
                producto.setCod_pro(cursorProducto.getString("cod_pro"));
                producto.setDescrip(cursorProducto.getString("descrip"));
                producto.setCodbar(cursorProducto.getString("codbar"));
                producto.setBiva(cursorProducto.getString("biva"));
                producto.setPzasepq(cursorProducto.getInt("pzasepq"));
                producto.setPrecio(cursorProducto.getFloat("precio"));
                producto.setPrecio(Float.parseFloat(cursorProducto.getString("precio")));

                String pzas = String.valueOf(producto.getPzasepq());
                while (pzas.length() < 5) {
                    pzas = "0" + pzas;
                }

                String cCodBar = (cursorProducto.getString("codbar"));
                String cDatos = producto.getCod_pro() + producto.getBiva()
                        + pzas + String.valueOf(producto.getPrecio());
                aPval.put(cCodBar, cDatos);

            }

        } catch (SQLException e) {
            e.printStackTrace();
            ////} catch (IOException e) {
            ////	e.printStackTrace();
        } finally {
            conexion.close();
        }
        // return arregloProductos;
        return aPval;
    }

    public static void guardaPedidosWallMart(String cGrupo, String cEmpresa, List<Pedido> aPed, HashMap aCte, HashMap aPval, Integer nCuenta,Integer nMetodo) throws SQLException {
        Connection conexion = null;
        String sigped = null;
        String sig = null;
        String cBodega = null;
        String datosCte = null;
        boolean errorTransaccion = false;
        Date fechaPed;
        Date fechaEnt;
        Date fechaCan;
        String ordenCompra = aPed.get(0).getOrdcomp();
        datosCte = (String) aCte.get(aPed.get(0).getNumtda().trim());
        String datosPro = null;
        float cantidadPed;
        float nSubfac = 0;
        float nIvafac = 0;
        int orden = 0;
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
            cBodega = datosCte.substring(8, 10);
            Statement sentenciaSql2 = conexion.createStatement();
            String LeeSigPed = null;
            LeeSigPed = "select max(cod_ped) as sigped from pedbodeg where cod_bod='"
                    + cBodega + "'" + " AND cod_emp='" + cEmpresa + "'";
            ResultSet cursorSigped = sentenciaSql2.executeQuery(LeeSigPed);
            cursorSigped.next();
            sigped = cursorSigped.getString("sigped");
            sig = String.valueOf(Integer.parseInt(sigped) + 1);
            while (sig.length() < 6) {
                sig = "0" + sig;
            }
            for (int i = 0; i < aPed.size(); i++) {
                datosPro = (String) aPval.get((aPed.get(i).getCodbar()));
                if (ordenCompra.equals(aPed.get(i).getOrdcomp())) {
                    orden++;
                    cantidadPed = aPed.get(i).getCantidad();
                    nSubfac = nSubfac + (cantidadPed * Float.parseFloat(datosPro.substring(13)));
                    String insertaDetallePedido = AsignaValoresVariables.asignaDetallePedido(orden, ordenCompra, datosCte, datosPro, cEmpresa, sig, cantidadPed);
                    Statement sentenciaSql3 = conexion.createStatement();
                    sentenciaSql3.executeUpdate(insertaDetallePedido);
                } else {
                    fechaPed = aPed.get(i - 1).getFechaord();
                    fechaEnt = aPed.get(i - 1).getFechaesp();
                    fechaCan = aPed.get(i - 1).getFechacan();
                    String insertaCabeceroPedido = AsignaValoresVariables.asignaCabeceroPedido(datosCte, ordenCompra, cEmpresa, sig, fechaPed, fechaEnt, fechaCan, nSubfac,nCuenta,nMetodo);
                    Statement sentenciaSql4 = conexion.createStatement();
                    sentenciaSql4.executeUpdate(insertaCabeceroPedido);
                    sigped = null;
                    nSubfac = 0;
                    orden = 1;
                    ordenCompra = aPed.get(i).getOrdcomp();
                    datosCte = (String) aCte.get(aPed.get(i).getNumtda().trim());
                    cBodega = datosCte.substring(8, 10);
                    Statement sentenciaSql5 = conexion.createStatement();
                    LeeSigPed = null;
                    LeeSigPed = "select max(cod_ped) as sigped from pedbodeg where cod_bod='"
                            + cBodega + "'" + " AND cod_emp='" + cEmpresa + "'";
                    ResultSet cursorSigped1 = sentenciaSql5.executeQuery(LeeSigPed);
                    cursorSigped1.next();
                    sigped = cursorSigped1.getString("sigped");
                    sig = String.valueOf(Integer.parseInt(sigped) + 1);
                    while (sig.length() < 6) {
                        sig = "0" + sig;
                    }
                    orden++;
                    cantidadPed = aPed.get(i).getCantidad();
                    nSubfac = nSubfac + (cantidadPed * Float.parseFloat(datosPro.substring(13)));
                    String insertaDetallePedido = AsignaValoresVariables.asignaDetallePedido(orden, ordenCompra, datosCte, datosPro, cEmpresa, sig, cantidadPed);
                    Statement sentenciaSql6 = conexion.createStatement();
                    sentenciaSql6.executeUpdate(insertaDetallePedido);
                }
            }
            fechaPed = aPed.get(aPed.size() - 1).getFechaord();
            fechaEnt = aPed.get(aPed.size() - 1).getFechaesp();
            fechaCan = aPed.get(aPed.size() - 1).getFechacan();
            String insertaCabeceroPedido = AsignaValoresVariables.asignaCabeceroPedido(datosCte, ordenCompra, cEmpresa, sig, fechaPed, fechaEnt, fechaCan, nSubfac,nCuenta,nMetodo);
            Statement sentenciaSql7 = conexion.createStatement();
            sentenciaSql7.executeUpdate(insertaCabeceroPedido);
        } catch (SQLException e) {
            Statement sentenciaSql8 = conexion.createStatement();
            String RollbackSql = "Rollback Transaction";
            sentenciaSql8.executeUpdate(RollbackSql);
            errorTransaccion = true;
        } finally {
            if (!errorTransaccion) {
                Statement sentenciaSql9 = conexion.createStatement();
                String CommitSql = "Commit Transaction";
                sentenciaSql9.executeUpdate(CommitSql);
                JOptionPane.showMessageDialog(null, "Proceso Concluido Exitosamente", "Aviso",
                        JOptionPane.ERROR_MESSAGE);
            }
            //Statement sentenciaSql9 = conexion.createStatement();
            //String CommitSql = "Rollback Transaction";
            //sentenciaSql9.executeUpdate(CommitSql);

            conexion.close();
        }
    }
}
