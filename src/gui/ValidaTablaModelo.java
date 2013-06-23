/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import catalogos.Gastos;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author carlosp
 */
public class ValidaTablaModelo extends AbstractTableModel{
     public static final int CONCEPTO = 0;
     public static final int SUBCONCEPTO = 1;
     public static final int DESCRIPCION = 2;
     public static final int IMPORTE = 3;
     public static final int HIDDEN = 4;
    
    
    protected Vector capturaVector;
    protected String[] nombreColumnas;
    
    public ValidaTablaModelo(String[] nombreColumnas) {
        this.nombreColumnas = nombreColumnas;
        capturaVector = new Vector();
        System.out.println("constructor clase validatablamodelo");
     }
    
     public String getColumnName(int column) {
         return nombreColumnas[column];
     }
     public boolean isCellEditable(int row, int column) {
         if (column == 4 || column == 2) return false;
         else return true;
     }

     public Class getColumnClass(int column) {
         switch (column) {
             case CONCEPTO:
             case SUBCONCEPTO:
             case DESCRIPCION:
                 return String.class;
             case IMPORTE:
                 return Float.class;
             default:
                return Object.class;
         }
     }
     
         public Object getValueAt(int row, int column) {
         Gastos record = (Gastos)capturaVector.get(row);
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

     public void setValueAt(Object value, int row, int column) {
         Gastos record = (Gastos)capturaVector.get(row);
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
                 record.setImporte((Float)value);
                 break;
             default:
                System.out.println("fila invalida");
         }
         fireTableCellUpdated(row, column);
        
     }

         
     public int getColumnCount() {
         return nombreColumnas.length;
     }
     public int getRowCount() {
         return capturaVector.size();
     }     
     public boolean hasEmptyRow() {
         if (capturaVector.size() == 0) return false;
         Gastos gastos = (Gastos)capturaVector.get(capturaVector.size() - 1);
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
         capturaVector.add(new Gastos());
         fireTableRowsInserted(
            capturaVector.size() - 1,
            capturaVector.size() - 1);
     }


}
