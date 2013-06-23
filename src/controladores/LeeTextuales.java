package controladores;

import catalogos.EntregasWallMart;
import dao.DaoEntregasWallMart;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import dominio.Pedido;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LeeTextuales {

	public static  List leeArchivoSams(File archivoTexto) throws IOException
	{
		List<Pedido> ped = new ArrayList<dominio.Pedido>();
		//ClienteVal clienteval;
		//clienteval = null;
		Pedido pedido;
		pedido = null;
		BufferedReader in = new BufferedReader(new FileReader(archivoTexto));
		String registro;
		String clientes;
		String anio;
		String mes;
		String dia;
		String fecha;
		registro = in.readLine();
		while ((registro = in.readLine()) != null) {
			String[] pedidoArray;
			pedidoArray = registro.split(",");
			pedido = new Pedido();
			pedido.setOrdcomp(pedidoArray[0]);
			pedido.setPosicion(pedidoArray[1]);			
			anio=pedidoArray[2].substring(0,4);
			mes=pedidoArray[2].substring(4,6);
			dia=pedidoArray[2].substring(6);
			fecha=anio+"-"+mes+"-"+dia;
			pedido.setFechaesp(Date.valueOf(fecha));
			anio=pedidoArray[3].substring(0,4);
			mes=pedidoArray[3].substring(4,6);
			dia=pedidoArray[3].substring(6);
			fecha=anio+"-"+mes+"-"+dia;
			pedido.setFechacan(Date.valueOf(fecha));			
			pedido.setNumtda (String.valueOf(Integer.parseInt(pedidoArray[0].substring(0,4))));
			pedido.setCodbar(pedidoArray[6]);
			pedido.setModelo(pedidoArray[8]);
			pedido.setDescrip(pedidoArray[7]);
			pedido.setEmpaque(pedidoArray[8]);
			pedido.setCantidad(Float.parseFloat(pedidoArray[11]));
			pedido.setCosto(Float.parseFloat(pedidoArray[12]));
			pedido.setCnose(pedidoArray[10]);
			anio=pedidoArray[16].substring(0,4);
			mes=pedidoArray[16].substring(4,6);
			dia=pedidoArray[16].substring(6);
			fecha=anio+"-"+mes+"-"+dia;
			pedido.setFechaord(Date.valueOf(fecha));
			pedido.setNumprov(pedidoArray[15]);
			pedido.setCant(Float.parseFloat(pedidoArray[19]));
			ped.add(pedido);
			}
		//for(int i = 0; i<ped.size(); i++)
		//	System.out.println(ped.get(i).getOrdcomp()+"-"+ped.get(i).getNumtda());
        return  ped;
	}

	public static  List<Pedido> leeArchivoWallMart(File archivoTexto,String cGrupo) throws IOException, SQLException
	{
		List<Pedido> ped = new ArrayList<dominio.Pedido>();
		Pedido pedido;
		pedido = null;
		BufferedReader in = new BufferedReader(new FileReader(archivoTexto));
		String registro;
		String anio;
		String mes;
		String dia;
		String fecha;
                String tienda;
                HashMap aGln = null;
                try {
                    aGln=DaoEntregasWallMart.leeEntregasWallMart();
		} catch (SQLException e1) {
                    System.out.println("Error de Lectura tabla entregasWallMart");
		}
                //gln=DaoEntregasWallMart.leeEntregasWallMart();
        //Recorrer un HashMap
	/*Iterator itr = aGln.entrySet().iterator();
	while (itr.hasNext()) {
		Map.Entry e = (Map.Entry)itr.next();
		System.out.println("clave: "+e.getKey()+"valor:"+e.getValue());                
        }*/
               
		registro = in.readLine();
		while ((registro = in.readLine()) != null) {
			String[] pedidoArray;
			pedidoArray = registro.split(",");
                        if ((pedidoArray[14].substring(8).equals("0")) && cGrupo.equals("016")
                                || (pedidoArray[14].substring(8).equals("2")) && cGrupo.equals("019")){
                            pedido = new Pedido();
                            pedido.setOrdcomp(pedidoArray[0]);
                            pedido.setPosicion(pedidoArray[1]);
                            anio=pedidoArray[2].substring(0,4);
                            mes=pedidoArray[2].substring(4,6);
                            dia=pedidoArray[2].substring(6);
                            fecha=anio+"-"+mes+"-"+dia;
                            pedido.setFechaesp(Date.valueOf(fecha));
                            anio=pedidoArray[3].substring(0,4);
                            mes=pedidoArray[3].substring(4,6);
                            dia=pedidoArray[3].substring(6);
                            fecha=anio+"-"+mes+"-"+dia;
                            pedido.setFechacan(Date.valueOf(fecha));
                            Object v = aGln.get(pedidoArray[4].substring(0,5));
                            if (v != null){
                                pedido.setNumtda( v.toString());
                            }
                            //pedido.setCodbar(pedidoArray[6]);
                            pedido.setCodbar (String.valueOf(Integer.parseInt(pedidoArray[6]))); 
                            pedido.setModelo(pedidoArray[8]);
                            pedido.setDescrip(pedidoArray[7]);
                            pedido.setEmpaque(pedidoArray[8]);
                            pedido.setCantidad(Float.parseFloat(pedidoArray[10]));
                            pedido.setCosto(Float.parseFloat(pedidoArray[11]));
                            pedido.setCnose(pedidoArray[9]);
                            anio=pedidoArray[15].substring(0,4);
                            mes=pedidoArray[15].substring(4,6);
                            dia=pedidoArray[15].substring(6);
                            fecha=anio+"-"+mes+"-"+dia;
                            pedido.setFechaord(Date.valueOf(fecha));
                            pedido.setNumprov(pedidoArray[14]);
                            pedido.setCant(Float.parseFloat(pedidoArray[18]));
                            ped.add(pedido);
                        }
                    }
                    //for(int i = 0; i<ped.size(); i++)
                    //	System.out.println("impresion "+ped.get(i).getOrdcomp()+"-"+ped.get(i).getNumtda()+"-"+ped.get(i).getNumprov());
        return  ped;
	}

	public static  List leeArchivoImss(File archivoTexto) throws IOException{
		List<Pedido> ped = new ArrayList<dominio.Pedido>();
		Pedido pedido;
		pedido = null;
		BufferedReader in = new BufferedReader(new FileReader(archivoTexto));
		String registro;
		//String clientes;
		String anio;
		String mes;
		String dia;
		String fecha;
		registro = in.readLine();
		while ((registro = in.readLine()) != null) {
			String[] pedidoArray;
			pedidoArray = registro.split(",");
			pedido = new Pedido();
			pedido.setOrdcomp(pedidoArray[0]);
			anio=pedidoArray[1].substring(6);
			mes=pedidoArray[1].substring(3,5);
			dia=pedidoArray[1].substring(0,2);
			fecha=anio+"-"+mes+"-"+dia;
			pedido.setFechaesp(Date.valueOf(fecha));
                        pedido.setFechaord(Date.valueOf(fecha));
			anio=pedidoArray[2].substring(6);
			mes=pedidoArray[2].substring(3,5);
			dia=pedidoArray[2].substring(0,2);
			fecha=anio+"-"+mes+"-"+dia;
                        pedido.setFechacan(Date.valueOf(fecha));
			pedido.setNumtda (String.valueOf(Integer.parseInt(pedidoArray[3])));
                        pedido.setDescrip(pedidoArray[12]);
			pedido.setCodbar(pedidoArray[10]);
                        //pedido.setCodbar(pedidoArray[11]);
			//pedido.setCantidad((Float.parseFloat(pedidoArray[14].substring(0,7)))*(Float.parseFloat(pedidoArray[16])));
			pedido.setCantidad((Float.parseFloat(pedidoArray[14].substring(0,2)))*(Float.parseFloat(pedidoArray[16])));                        
                        pedido.setCosto(Float.parseFloat(pedidoArray[17]));
                        pedido.setNumprov(pedidoArray[5]);
                        ped.add(pedido);
                }
            return ped;
        }
	public static  List leeArchivoComa(File archivoTexto) throws IOException{
		List<Pedido> ped = new ArrayList<dominio.Pedido>();
		Pedido pedido;
		pedido = null;
		BufferedReader in = new BufferedReader(new FileReader(archivoTexto));
		String registro;
		String anio;
		String mes;
		String dia;
		String fecha;
		registro = in.readLine();
		while ((registro = in.readLine()) != null) {
			String[] pedidoArray;
			pedidoArray = registro.split(",");
			pedido = new Pedido();
                        pedido.setOrdcomp(pedidoArray[0]);
                        pedido.setPosicion(pedidoArray[1]);
			anio=pedidoArray[2].substring(6);
			mes=pedidoArray[2].substring(3,5);
			dia=pedidoArray[2].substring(0,2);
			fecha=anio+"-"+mes+"-"+dia;
			pedido.setFechaesp(Date.valueOf(fecha));
                        pedido.setFechaord(Date.valueOf(fecha));
			anio=pedidoArray[3].substring(6);
			mes=pedidoArray[3].substring(3,5);
			dia=pedidoArray[3].substring(0,2);
			fecha=anio+"-"+mes+"-"+dia;
                        pedido.setFechacan(Date.valueOf(fecha));
                        pedido.setDescrip(pedidoArray[5]);
                        
                }
            return ped;
        }
}
