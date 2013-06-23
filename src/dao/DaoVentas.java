/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dominio.VentasPedidos;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

/**
 *
 * @author carlosp
 */
public class DaoVentas {

    public static gestionConexiones.GestionConexiones gestor;
    public static Connection conexion = null;

    //public static void agregarDireccion(Direccion d) throws SQLException, ClassNotFoundException {
    public static void leeVentas(String fechaInicial, String fechaFinal) throws SQLException, ClassNotFoundException {
        try {
            gestor = new gestionConexiones.GestionConexiones();
            try {
                conexion = gestor.conectaBase();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("estamos en el error de conexion conecta base");
            }
            //
            Statement sentencia2 = conexion.createStatement();
            sentencia2.executeUpdate("Begin Transaction");

            Statement sentenciaSql1 = conexion.createStatement();
            String BeginSql = "consultaAgentes '" + fechaInicial + "','" + fechaFinal + "'";
            ResultSet c_vtas = sentenciaSql1.executeQuery(BeginSql);
            while (c_vtas.next()) {
                VentasPedidos vp = new VentasPedidos();
                vp.setCod_bod(c_vtas.getString("cod_bod"));
                vp.setDescrip(c_vtas.getString("descrip"));
                vp.setPed_ani(c_vtas.getDouble("ped_ani"));
                vp.setFac_ani(c_vtas.getDouble("fac_ani"));
                vp.setPed_qui(c_vtas.getDouble("ped_qui"));
                vp.setFac_qui(c_vtas.getDouble("fac_qui"));
                vp.setTotal(c_vtas.getDouble("total"));
                vp.setTendencia(c_vtas.getDouble("tendencia"));

                System.out.println(vp.getCod_bod() + " " + vp.getDescrip() + " " + vp.getPed_ani() + " " + vp.getFac_ani() + " " + vp.getPed_qui() + " " + vp.getFac_qui() + " " + vp.getTotal() + " " + vp.getTendencia());

            }
            //Statement sentencia3 = conexion.createStatement();
            sentencia2.executeUpdate("Commit Transaction");
        } catch (SQLException ex) {
            Statement sentencia2 = conexion.createStatement();
            sentencia2.executeUpdate("Rollback Transaction");

        } finally {
            conexion.close();
        }
    }
}
