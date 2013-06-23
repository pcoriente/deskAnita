/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dominio.CabeceroPedido;
import dominio.DetallePedido;
import java.sql.Date;

/**
 *
 * @author carlosp
 */
public class AsignaValoresVariables {

    public static String asignaDetallePedido(int orden, String ordenCompra, String datosCte, String datosPro, String cEmpresa, String sigPed, float cantidadPed) {
        DetallePedido detallepedido;
        detallepedido = new DetallePedido();
        detallepedido.setCod_gru(datosCte.substring(5, 8));
        detallepedido.setNumped(ordenCompra);
        detallepedido.setCod_cli(datosCte.substring(0, 5));
        detallepedido.setCod_emp(cEmpresa);
        detallepedido.setCod_pro(datosPro.substring(0, 7));
        detallepedido.setCantidad((float) 0.0);
        detallepedido.setPreciouni(Float.parseFloat(datosPro.substring(13)));
        detallepedido.setCod_bod(datosCte.substring(8, 10));
        detallepedido.setCod_ped(sigPed);
        if (datosCte.substring(5, 8).equals("001")) {//Sam's
            if (datosPro.substring(0, 7).equals("0069100") || datosPro.substring(0, 7).equals("0129100"))// || datosPro.substring(0,7).equals("0129070") || datosPro.substring(0,7).equals("0249000") || datosPro.substring(0,7).equals("0249070"))
            {
                cantidadPed = cantidadPed * Float.parseFloat(datosPro.substring(8, 13));
            } else if (datosPro.substring(0, 7).equals("0017370") || datosPro.substring(0, 7).equals("0017380") || datosPro.substring(0, 7).equals("0038220") || datosPro.substring(0, 7).equals("0038520")) {
                cantidadPed = cantidadPed * 1;
            } else if (datosPro.substring(0, 7).equals("0018460")) {
                cantidadPed = (float) Math.rint(cantidadPed / 6);
            } else if (datosPro.substring(0, 7).equals("0505540")) {
                cantidadPed = cantidadPed * 10;
            }
        }
        detallepedido.setCantped(cantidadPed);
        detallepedido.setBdet("1");
        detallepedido.setOrden(orden);
        String insertaDetallePedido = "INSERT depedcont (cod_gru,numped,cod_cli,cod_emp,cod_pro,cantidad,preciouni,cod_bod,cod_ped,"
                + "cantped,bdet,orden) VALUES ('"
                + detallepedido.getCod_gru() + "','" + detallepedido.getNumped()
                + "','" + detallepedido.getCod_cli() + "','" + detallepedido.getCod_emp()
                + "','" + detallepedido.getCod_pro() + "','" + detallepedido.getCantidad()
                + "','" + detallepedido.getPreciouni() + "','" + detallepedido.getCod_bod()
                + "','" + detallepedido.getCod_ped() + "','" + detallepedido.getCantped()
                + "','" + detallepedido.getBdet() + "','" + detallepedido.getOrden() + "')";
        return insertaDetallePedido;
    }

    public static String asignaCabeceroPedido(String datosCte, String ordenCompra, String cEmpresa, String sigPed, Date fechaPed, Date fechaEnt, Date fechaCan, float nSubfac, Integer nCuenta, Integer nMetodo) {
        CabeceroPedido cabeceropedido;
        cabeceropedido = new CabeceroPedido();
        cabeceropedido.setCod_gru(datosCte.substring(5, 8));
        cabeceropedido.setNumped(ordenCompra);
        cabeceropedido.setCod_cli(datosCte.substring(0, 5));
        cabeceropedido.setCod_age(datosCte.substring(10, 12));
        cabeceropedido.setCod_emp(cEmpresa);
        cabeceropedido.setCod_bod(datosCte.substring(8, 10));
        cabeceropedido.setCod_ped(sigPed);
        cabeceropedido.setSubfac(nSubfac);
        cabeceropedido.setDescfac((float) 0.0);
        cabeceropedido.setIvafac((float) 0.0);
        cabeceropedido.setFechaped(fechaPed);
        cabeceropedido.setBfactur("0");
        cabeceropedido.setCod_lta("");
        cabeceropedido.setBped("0");
        cabeceropedido.setBbol("0");
        cabeceropedido.setFechaent(fechaEnt);
        cabeceropedido.setFechacan(fechaCan);
        cabeceropedido.setIdclienteBanco(nCuenta);
        cabeceropedido.setIdLeyenda(nMetodo);
        String insertaCabeceroPedido = "INSERT pedbodeg (cod_gru,numped,cod_cli,cod_age,cod_emp,cod_bod,cod_ped,subfac,descfac,ivafac,"
                + "fechaped,bfactur,cod_lta,bped,bbol,fechaent,fechacan,idClienteBanco,idLeyenda,bCtaPag) VALUES ('"
                + cabeceropedido.getCod_gru() + "','" + cabeceropedido.getNumped()
                + "','" + cabeceropedido.getCod_cli() + "','" + cabeceropedido.getCod_age()
                + "','" + cabeceropedido.getCod_emp() + "','" + cabeceropedido.getCod_bod()
                + "','" + cabeceropedido.getCod_ped() + "','" + cabeceropedido.getSubfac()
                + "','" + cabeceropedido.getDescfac() + "','" + cabeceropedido.getIvafac()
                + "','" + cabeceropedido.getFechaped() + "','" + cabeceropedido.getBfactur()
                + "','" + cabeceropedido.getCod_lta() + "','" + cabeceropedido.getBped()
                + "','" + cabeceropedido.getBbol() + "','" + cabeceropedido.getFechaent()
                + "','" + cabeceropedido.getFechacan() + "','" + cabeceropedido.getIdclienteBanco()
                + "','" + cabeceropedido.getIdLeyenda() + "','1')";
        return insertaCabeceroPedido;
    }
}