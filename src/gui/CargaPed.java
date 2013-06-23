package gui;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import catalogos.Empresa;
import catalogos.Grupo;
import controladores.ControladorEmpresa;
import controladores.ControladorGuardar;
import dao.DaoEmpresa;
import dao.ErrorTransaccion;

/**
 * This code was edited or generated using CloudGarden's Jigloo
 * SWT/Swing GUI Builder, which is free for non-commercial
 * use. If Jigloo is being used commercially (ie, by a corporation,
 * company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details.
 * Use of Jigloo implies acceptance of these licensing terms.
 * A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
 * THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
 * LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class CargaPed extends javax.swing.JFrame {

    private JLabel etiquetaEmpresa;
    private JButton botonImportarArchivo;
    private JComboBox comboGrupo;
    private JComboBox comboEmpresa;
    private JComboBox comboCuenta;
    private JComboBox comboMetodo;
    private JLabel etiquetaGrupo;
    private ControladorEmpresa controladorEmpresa;
    private ControladorGuardar controladorGuardar;
    private JMenuBar menuSea;
    public HashMap aCval;

    /**
     * @param arg
     */
    /*
    public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
    public void run() {
    CargaPed inst = null;
    try {
    inst = new CargaPed();
    } catch (SQLException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
    } catch (IOException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
    }
    inst.setLocationRelativeTo(null);
    inst.setVisible(true);				
    }
    
    });
    }
     */
//	public CargaPed(JMenuBar jMenuBarSea) {
//		menuSea=jMenuBarSea;
    // TODO Auto-generated constructor stub
//	}
    public CargaPed(JMenuBar jMenuBarSea) throws ErrorTransaccion, SQLException, IOException {
        super();
        initGui();
        menuSea = jMenuBarSea;
        cargaEmpresas(DaoEmpresa.leeTablaEmpresa());
        cargaGrupos(DaoEmpresa.leeTablaGrupo());
        pack();
    }

    private void initGui() {
        comboEmpresa = new javax.swing.JComboBox();
        comboGrupo = new javax.swing.JComboBox();
        botonImportarArchivo = new javax.swing.JButton();
        //botonEnviaPedido = new javax.swing.JButton();
        //controladorEmpresa = new ControladorEmpresa(comboEmpresa, comboGrupo);
        //botonImportarArchivo.addActionListener(controladorEmpresa);       

        try {
            setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            this.setPreferredSize(new Dimension(788, 394));
            getContentPane().setLayout(null);
            this.setTitle("Integración Pedidos Autoervicios");
            this.addWindowListener(new WindowAdapter() {

                public void windowClosing(WindowEvent evt) {
                    thisWindowClosing(evt);
                }
            });
            {
                etiquetaEmpresa = new JLabel();
                getContentPane().add(etiquetaEmpresa);
                etiquetaEmpresa.setText("Empresa:");
                etiquetaEmpresa.setBounds(140, 56, 84, 14);
                etiquetaEmpresa.setFont(new java.awt.Font("Arial", 0, 14));
            }
            {
                etiquetaGrupo = new JLabel();
                getContentPane().add(etiquetaGrupo);
                etiquetaGrupo.setText("Grupo:");
                etiquetaGrupo.setBounds(142, 84, 49, 14);
                etiquetaGrupo.setFont(new java.awt.Font("Arial", 0, 14));
            }
            {
                //ComboBoxModel comboEmpresaModel = 
                //	new DefaultComboBoxModel();
                //new String[] { "Item One", "Item Two" });

                //comboEmpresa = new JComboBox();
                //cargaEmpresas(controladorEmpresa.leeEmpresa());	
                getContentPane().add(comboEmpresa);
                comboEmpresa.setBounds(203, 56, 254, 21);
            }
            {
                //ComboBoxModel comboGrupoModel = 
                //	new DefaultComboBoxModel(
                //			new String[] { "Item One", "Item Two" });
                //comboGrupo = new JComboBox();
                getContentPane().add(comboGrupo);
                //comboGrupo.setModel(comboGrupoModel);
                comboGrupo.setBounds(203, 82, 210, 18);
                {
                    botonImportarArchivo = new JButton();
                    getContentPane().add(botonImportarArchivo);
                    botonImportarArchivo.setText("Importar Archivo");
                    botonImportarArchivo.setBounds(215, 124, 150, 21);
                    botonImportarArchivo.setFont(new java.awt.Font("Arial", 0, 10));
                    controladorEmpresa = new ControladorEmpresa(comboEmpresa, comboGrupo, comboMetodo, comboCuenta);
                    botonImportarArchivo.addActionListener(controladorEmpresa);
                }
            }
            pack();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void thisWindowClosing(WindowEvent evt) {
        //int confirmacion = JOptionPane.showConfirmDialog(this,"¿Quieres cerrar la ventana?",
        //		"Confirmacion",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
        //if (confirmacion == JOptionPane.OK_OPTION){
        menuSea.setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //}
        //if (confirmacion == JOptionPane.OK_OPTION)
        //setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);


    }

    public void cargaEmpresas(ArrayList lista) {
        java.util.Iterator it = lista.iterator();
        while (it.hasNext()) {
            Empresa empresa = (Empresa) it.next();
            comboEmpresa.addItem(empresa);
        }

    }

    public void cargaGrupos(ArrayList lista) {
        java.util.Iterator it = lista.iterator();
        while (it.hasNext()) {
            Grupo grupo = (Grupo) it.next();
            comboGrupo.addItem(grupo);
        }

    }
}
