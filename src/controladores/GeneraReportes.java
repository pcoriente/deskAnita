/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;


import catalogos.Empresa;
import catalogos.Grupo;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dao.DaoCatalogos;
import dao.ErrorTransaccion;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import javax.swing.JComboBox;
import com.itextpdf.text.Element;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import validaCatalogos.ConsultaCanceladas;

/**
 *
 * @author carlosp
 */
public class GeneraReportes {
    private static JComboBox empresa;
    private static JComboBox grupo;
    private static String fechIni;
    private static String fechFin;
    //private static String nombEmp;

    public GeneraReportes(JComboBox jComboEmpresa, JComboBox jComboGrupo, String fechaInicial, String fechaFinal) {
        empresa = jComboEmpresa;
        grupo = jComboGrupo;
        fechIni = fechaInicial;
        fechFin = fechaFinal;
    }
    public static ArrayList devuelveConsulta(JComboBox jComboEmpresa, JComboBox jComboGrupo, String fechaInicial, String fechaFinal) throws ErrorTransaccion, SQLException, ClassNotFoundException {
        Empresa emp = (Empresa)jComboEmpresa.getSelectedItem();
        Grupo gru = (Grupo)jComboGrupo.getSelectedItem();
        String cGrupo = gru.getCod_gru();
        String cEmpresa = emp.getCod_emp();

        ArrayList aSdos = new ArrayList();
        String seleccionaCartera =  null;
        seleccionaCartera = "select cart.cod_cli,cli.numtda,fac.pedido,cart.cod_bod,cart.cod_fac,cart.fechapli,cart.importe,cart.saldo "+
                "from cartera cart,clientes cli, facturas fac where cart.cod_emp='"+cEmpresa+"' and cart.fechapli>='"+fechaInicial+
                "' and cart.fechapli<='"+fechaFinal+"' and cart.cod_cli in(select cod_cli from clientes where cod_gru='"+cGrupo+"') "+
                "and saldo>0 and cart.tipofac+cart.cod_emp+cart.cod_bod+cart.cod_fac=fac.tipofac+fac.cod_emp+fac.cod_bod+fac.cod_fac "+
                "and cart.cod_cli=cli.cod_cli order by cart.cod_bod,cart.cod_fac";
        aSdos = DaoCatalogos.leeSaldosCartera(seleccionaCartera);
        return aSdos;
    }
    
    public static ArrayList consultaCanceMismoMes(JComboBox jComboEmpresa, JComboBox jComboGrupo, String fechaInicial, String fechaFinal) throws ErrorTransaccion, SQLException, ClassNotFoundException {
        Empresa emp = (Empresa)jComboEmpresa.getSelectedItem();
        Grupo gru = (Grupo)jComboGrupo.getSelectedItem();
        String cGrupo = gru.getCod_gru();
        String cEmpresa = emp.getCod_emp();
        
        ArrayList aCance = new ArrayList();
        String seleccionaCanceladas = null;
            seleccionaCanceladas = "select tipofac,cod_bod,cod_fac,cod_cli,cod_age,fechafac,fechacan,subfac,descfac,ivafac from facturas where "+
                "estado = 'C' and cod_emp = '"+cEmpresa+"' and fechacan >= '"+fechaInicial+"' and fechacan <= '"+fechaFinal+"' order by cod_bod,cod_fac";
        aCance = DaoCatalogos.leeCanceladasMes(seleccionaCanceladas);
        
        return aCance;
    }
    public static void generaDocumento(ArrayList aCance,JComboBox jComboEmpresa,String fechaInicial, String fechaFinal){
        Empresa emp = (Empresa)jComboEmpresa.getSelectedItem(); 
        String nombreEmp = emp.getNombre();
    try {
        Document document = new Document();
        FileOutputStream file;
        file = new FileOutputStream("fichero.pdf");
        PdfWriter.getInstance(document, file).setInitialLeading(20);
        document.open();
        agregaTitulo(document, nombreEmp,fechaInicial,fechaFinal);
        creaTabla(document,aCance);
        document.close();
        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+"fichero.pdf");
        //System.out.println("Final");
        }catch (DocumentException ex) { 
            //Logger.getLogger(GeneraReportes.class.getName()).log(Level.SEVERE, null, ex);
        }catch (FileNotFoundException ex) {
                //Logger.getLogger(GeneraReportes.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException ex) {
                //Logger.getLogger(GeneraReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    private static void agregaTitulo(Document document,String nombreEmp,String fechaInicial,String fechaFinal) throws DocumentException, BadElementException, MalformedURLException, IOException{
        Paragraph prefacio = new Paragraph();
        //agregaLineaVacia(prefacio, 1);
        String fIni=fechaInicial.substring(8)+"/"+fechaInicial.substring(5,7)+"/"+fechaInicial.substring(0,4);
        String fFin=fechaFinal.substring(8)+"/"+fechaFinal.substring(5,7)+"/"+fechaFinal.substring(0,4);

        Paragraph titulo = new Paragraph(nombreEmp);
        titulo.setAlignment(Element.ALIGN_CENTER);
        document.add(titulo);
        
        titulo = new Paragraph("Facturas Canceladas Mismo Mes",
                                        FontFactory.getFont("arial",   // fuente
                                        14,                            // tamaño
                                        Font.ITALIC,                    // col        
                                        BaseColor.BLUE));
        titulo.setAlignment(Element.ALIGN_CENTER);
        document.add(titulo);        
        titulo=new Paragraph("Fecha Inicial: "+fIni+"                 Fecha Final: "+fFin);
        titulo.setAlignment(Element.ALIGN_CENTER);
        document.add(titulo);
        
        agregaLineaVacia(prefacio, 1);
        document.add(prefacio);
        //document.newPage();
    }
    
    private static void agregaLineaVacia(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
    private static void creaTabla(Document document,ArrayList aCance)throws DocumentException, BadElementException, MalformedURLException, IOException{
        PdfPTable tabla = new PdfPTable(9);
        float[] medidaCeldas = {0.12f,0.08f,.08f,0.13f,0.13f,0.12f,0.11f,0.11f,0.12f};
        tabla.setWidths(medidaCeldas);
        PdfPCell c1 = new PdfPCell(new Phrase("Documento",FontFactory.getFont("arial",8)));
	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	tabla.addCell(c1);
	c1 = new PdfPCell(new Phrase("Cliente",FontFactory.getFont("arial",8)));
	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	tabla.addCell(c1);
	c1 = new PdfPCell(new Phrase("Agente",FontFactory.getFont("arial",8)));
	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	tabla.addCell(c1);
	c1 = new PdfPCell(new Phrase("Fecha Doc.",FontFactory.getFont("arial",8)));
	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	tabla.addCell(c1);
	c1 = new PdfPCell(new Phrase("Fecha Cancel",FontFactory.getFont("arial",8)));
	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	tabla.addCell(c1);
	c1 = new PdfPCell(new Phrase("SubTotal",FontFactory.getFont("arial",8)));
	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	tabla.addCell(c1);
        c1 = new PdfPCell(new Phrase("Descuento",FontFactory.getFont("arial",8)));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(c1);
        c1 = new PdfPCell(new Phrase("I.V.A.",FontFactory.getFont("arial",8)));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(c1);
        c1 = new PdfPCell(new Phrase("Total",FontFactory.getFont("arial",8)));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(c1);
        
	tabla.setHeaderRows(1);

        String data [][] = obtenerInformacion(aCance);
        for (int i = 0; i < data.length; i++){
            for (int j = 0; j < 9; j++){
                c1 = new PdfPCell(new Phrase(data[i][j],FontFactory.getFont("arial",7)));   // fuente
                tabla.addCell(c1);
            }
        }
        document.add(tabla);
    }
    public static String[][] obtenerInformacion(ArrayList aCance){
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        String matrix[][] = new String [aCance.size()][9];
        for(int i=0; i < aCance.size(); i++){
            ConsultaCanceladas registro = (ConsultaCanceladas)aCance.get(i);
            System.out.println(registro.getCod_fac());
            matrix[i][0] = (String) (registro.getCod_bod().toString())+"-"+(registro.getTipofac().toString())+"-"+(registro.getCod_fac().toString());
            matrix[i][1] = (String) (registro.getCod_cli().toString());
            matrix[i][2] = (String) (registro.getCod_age().toString());
            matrix[i][3] = (String) (sdf.format(registro.getFechafac()).toString());
            matrix[i][4] = (String) (sdf.format(registro.getFechacan()).toString());
            matrix[i][5] = (String) (String.valueOf(registro.getSubfac()));
            matrix[i][6] = (String) (String.valueOf(registro.getDescfac()));
            matrix[i][7] = (String) (String.valueOf(registro.getIvafac()));
            NumberFormat mf = NumberFormat.getInstance();
            mf.setMaximumFractionDigits(2);
            //mf.format((registro.getSubfac()-registro.getDescfac()+registro.getIvafac()));
            matrix[i][8] = (String) mf.format((registro.getSubfac()-registro.getDescfac()+registro.getIvafac()));            
        }
        return matrix;
    }
    //String redondeo=String.format(“%.2f”,a)
}

