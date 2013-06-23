/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import catalogos.Gastos;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author carlosp
 */
public class TablaModeloModificada extends AbstractTableModel{
     public static final int CONCEPTO = 0;
     public static final int SUBCONCEPTO = 1;
     public static final int DESCRIPCION = 2;
     public static final int IMPORTE = 3;
     public static final int HIDDEN = 4;
    
    protected ArrayList capturaRegistro;
    //protected Vector capturaVector;
    protected String[] nombreColumnas;
    
    public TablaModeloModificada(String[] nombreColumnas) {
        this.nombreColumnas = nombreColumnas;
        capturaRegistro = new ArrayList();
        //capturaVector = new Vector();
        //System.out.println("constructor clase validatablamodelo");
     }

    TablaModeloModificada() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    @Override
     public String getColumnName(int column) {
         return nombreColumnas[column];
     }
    @Override
     public boolean isCellEditable(int row, int column) {
        if (column == 2)
            System.out.println("editando celdas");
         if (column == 4 || column == 2) return false;
         else return true;
     }

    @Override
     public Class getColumnClass(int column) {
         switch (column) {
             case CONCEPTO:
             case SUBCONCEPTO:
             case DESCRIPCION:
                 return String.class;
             case IMPORTE:
                 return Double.class;
             default:
                return Object.class;
         }
     }
     
    @Override
         public Object getValueAt(int row, int column) {
         Gastos record = (Gastos)capturaRegistro.get(row);
         //Gastos record = (Gastos)capturaVector.get(row);
         switch (column) {
             case CONCEPTO:
                return record.getConcepto();
             case SUBCONCEPTO:
                return record.getSubconcepto();
             case DESCRIPCION:
                return record.getDescripcion();
             case IMPORTE:
                 return record.getImporte();
             default:
                return new Object();
         }
     }
    @Override
     public void setValueAt(Object value, int row, int column) {
         Gastos record = (Gastos)capturaRegistro.get(row);
         //Gastos record = (Gastos)capturaVector.get(row);
         switch (column) {
             case CONCEPTO:
                record.setConcepto((String)value);
                break;
             case SUBCONCEPTO:
                record.setSubconcepto((String)value);
                break;
             case DESCRIPCION:
                record.setDescripcion((String)value);
                break;
             case IMPORTE:
                 record.setImporte(Double.valueOf(value.toString()));
                 //record.setImporte((double)value);
                 break;
             default:
                System.out.println("fila invalida");
         }
         fireTableCellUpdated(row, column);
        
     }

         
    @Override
     public int getColumnCount() {
         return nombreColumnas.length;
     }
    @Override
     public int getRowCount() {
         return capturaRegistro.size();
         //return capturaVector.size();
     }     
     public boolean hasEmptyRow() {
         if (capturaRegistro.isEmpty()) return false;
         //if (capturaRegistro.size() == 0) return false;
         //if (capturaVector.size() == 0) return false;
         Gastos gastos = (Gastos)capturaRegistro.get(capturaRegistro.size() -1);
         //Gastos gastos = (Gastos)capturaVector.get(capturaVector.size() - 1);
         if (gastos.getConcepto().trim().equals("") &&
                 gastos.getSubconcepto().trim().equals("") &&
                 gastos.getDescripcion().trim().equals("") &&
                 gastos.getImporte() == 0)
            {
                return true;
            }
            else return false;
     }

     public void addEmptyRow() {
         capturaRegistro.add(new Gastos());
         //capturaVector.add(new Gastos());
         fireTableRowsInserted(
            capturaRegistro.size() -1,
            //capturaVector.size() - 1,
            capturaRegistro.size() - 1);
            //capturaVector.size() - 1);
     }


    
}
