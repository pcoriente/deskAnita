package controladores;

import catalogos.BancosClientes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import dominio.Pedido;
import dominio.DetallePedido;
import dominio.CabeceroPedido;
import catalogos.Cliente;
import catalogos.Empresa;
import catalogos.Grupo;
import catalogos.GruposCuentas;
import catalogos.MetodosPago;
import dao.DaoEmpresa;

public class ControladorEmpresa implements ActionListener {

    private JComboBox empresa;
    private JComboBox grupo;
    private JComboBox metodo;
    private JComboBox cuenta;
    private JButton importar;
    private JButton enviar;
    public String Chedraui = "006";
    public String Sams = "001";
    public String SuperCenter = "016";
    public String Aurrera = "019";
    public String Imss = "002";
    private String cGrupo;
    private String cEmpresa;
    private File archivoTexto;
    private Integer nCtaBco;

    public ControladorEmpresa(JComboBox nEmpresa, JComboBox nGrupo, JComboBox nMetodo, JComboBox nCuenta) {
        empresa = nEmpresa;
        grupo = nGrupo;
        metodo = nMetodo;
        cuenta = nCuenta;
    }

    public ControladorEmpresa(JButton nImportar, JButton nEnviar) {
        importar = nImportar;
        enviar = nEnviar;
    }
//	public ControladorEmpresa(JButton nEnviar) {
//		enviar = nEnviar;
//	}	

    public void actionPerformed(ActionEvent e) {
        Empresa emp = (Empresa) empresa.getSelectedItem();
        Grupo gru = (Grupo) grupo.getSelectedItem();
        GruposCuentas bc = (GruposCuentas) cuenta.getSelectedItem();
        MetodosPago mp = (MetodosPago) metodo.getSelectedItem();

        int resultado = JOptionPane.showConfirmDialog(empresa, "¿Es Correcta La Empresa y el Grupo que Eligio? \n" + emp.getNombre() + '\n' + gru.getNomgen() + "", "Aviso",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);


        if (resultado == JOptionPane.OK_OPTION) {
            JFileChooser at = new JFileChooser();
            int resultArch = at.showDialog(importar, "Cargar Archivo");
            if (resultArch == JFileChooser.APPROVE_OPTION) {
                archivoTexto = at.getSelectedFile();
                cGrupo = gru.getCod_gru();
                cEmpresa = emp.getCod_emp();
                //nCtaBco = bc.getIdGrupoBanco();
                if (cuenta.getItemCount() > 0){ 
                    //nCtaBco = bc.getIdGrupoBanco();
                    nCtaBco = bc.getIdGrupoBanco();
                    //System.out.println("la cuenta banco es "+nCtaBco);
                }else{
                    nCtaBco = 0;
                    JOptionPane.showMessageDialog(null, "El Grupo no Tiene Cuentas Asignadas", "Aviso",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            Integer nMetodo = mp.getIdLeyenda();
            boolean bCteError = false;
            boolean bProError = false;
            HashMap aCte = null;
            try {
                aCte = DaoEmpresa.leeTablaCliente(cGrupo);
            } catch (SQLException e1) {
                //e1.printStackTrace();
                System.out.println("Error de Lectura tabla Clientes");
            }
            HashMap aPro = null;
            try {
                aPro = DaoEmpresa.leeTablaProducto(cGrupo, cEmpresa);
            } catch (SQLException e2) {
                System.out.println("Error de Lectura Tabla Productos");
            }

            if ((gru.getCod_gru().equals(SuperCenter)) || (gru.getCod_gru().equals(Aurrera))) {
                List<Pedido> aPed = null;
                try {
                    try {
                        aPed = LeeTextuales.leeArchivoWallMart(archivoTexto, cGrupo);
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorEmpresa.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException e1) {
                    System.out.println("Error en lectura archivo Textual WallMart, Verifique");
                }
                for (int i = 0; i < aPed.size(); i++) {
                    Object v = aCte.get(aPed.get(i).getNumtda().trim());
                    System.out.println("que ondiiux longitud " + aPed.get(i).getNumtda().trim().length());
                    if (v == null) {
                        JOptionPane.showMessageDialog(null, "La Tienda " + aPed.get(i).getNumtda() + "No esta en el Catálogo", "Aviso",
                                JOptionPane.ERROR_MESSAGE);
                        bCteError = true;
                    }
                    Object y = aPro.get(aPed.get(i).getCodbar());
                    if (y == null) {
                        JOptionPane.showMessageDialog(null, "El Producto " + aPed.get(i).getCodbar() + "No esta en el Catálogo", "Aviso",
                                JOptionPane.ERROR_MESSAGE);
                        bProError = true;
                    }
                }
                if (!bCteError && !bProError) {
                    try {
                        DaoEmpresa.guardaPedidosWallMart(cGrupo, cEmpresa, aPed, aCte, aPro, nCtaBco, nMetodo);
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorEmpresa.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                if (gru.getCod_gru().equals(Sams)) {
                    List<Pedido> aPed = null;
                    try {
                        aPed = LeeTextuales.leeArchivoSams(archivoTexto);
                    } catch (IOException e1) {
                        System.out.println("Error de Lectura Archivo Textual Sam's, Verifique");
                    }
                    for (int i = 0; i < aPed.size(); i++) {
                        Object v = aCte.get(aPed.get(i).getNumtda());
                        if (v == null) {
                            JOptionPane.showMessageDialog(null, "La Tienda " + aPed.get(i).getNumtda() + " No esta en el Catálogo", "Aviso",
                                    JOptionPane.ERROR_MESSAGE);
                            bCteError = true;
                        }
                        Object y = aPro.get(aPed.get(i).getCodbar());
                        if (y == null) {
                            JOptionPane.showMessageDialog(null, "El Producto " + aPed.get(i).getCodbar() + " No esta en el Catálogo", "Aviso",
                                    JOptionPane.ERROR_MESSAGE);
                            bProError = true;
                        }
                    }
                    if (!bCteError && !bProError) {
                        try {
                            DaoEmpresa.guardaPedidosWallMart(cGrupo, cEmpresa, aPed, aCte, aPro, nCtaBco, nMetodo);
                        } catch (SQLException ex) {
                            Logger.getLogger(ControladorEmpresa.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    //System.out.println("stand by");
                } else {
                    if (gru.getCod_gru().equals(Imss)) {
                        List<Pedido> aPed = null;
                        try {
                            aPed = LeeTextuales.leeArchivoImss(archivoTexto);
                        } catch (IOException e1) {
                            System.out.println("Error de Lectura Archivo Textual IMSS, Verifique");
                        }
                        for (int i = 0; i < aPed.size(); i++) {
                            Object v = aCte.get(aPed.get(i).getNumtda().trim());
                            //System.out.println("que ondiiux longitud "+aPed.get(i).getNumtda().trim().length());
                            if (v == null) {
                                JOptionPane.showMessageDialog(null, "La Tienda " + aPed.get(i).getNumtda() + "No esta en el Catálogo", "Aviso",
                                        JOptionPane.ERROR_MESSAGE);
                                bCteError = true;
                            }
                            Object y = aPro.get(aPed.get(i).getCodbar());
                            if (y == null) {
                                JOptionPane.showMessageDialog(null, "El Producto " + aPed.get(i).getCodbar() + "No esta en el Catálogo", "Aviso",
                                        JOptionPane.ERROR_MESSAGE);
                                bProError = true;
                            }
                        }
                        if (!bCteError && !bProError) {
                            try {
                                DaoEmpresa.guardaPedidosWallMart(cGrupo, cEmpresa, aPed, aCte, aPro, nCtaBco, nMetodo);
                            } catch (SQLException ex) {
                                Logger.getLogger(ControladorEmpresa.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }

                }
            }

        }//if elegir-archivo
    }//if empresa-grupo
}//action event
//}//clase
