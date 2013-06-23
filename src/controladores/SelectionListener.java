/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/**
 *
 * @author carlosp
 */
public class SelectionListener implements ListSelectionListener {
    JTable jTableGastos;

    // It is necessary to keep the table since it is not possible
    // to determine the table from the event's source
    SelectionListener(JTable jTableGastos) {
        this.jTableGastos = jTableGastos;
    
    
    }

    @Override
    public void valueChanged(ListSelectionEvent evt) {
        // If cell selection is enabled, both row and column change events are fired
        
        //if (evt.getSource() == table.getSelectionModel() && table.getRowSelectionAllowed()) {
        if (evt.getSource() == jTableGastos.getSelectionModel() && jTableGastos.getRowSelectionAllowed()){
            int primero = evt.getFirstIndex();
            int ultimo = evt.getLastIndex();
        }else if (evt.getSource() == jTableGastos.getColumnModel().getSelectionModel() && jTableGastos.getColumnSelectionAllowed()){
            int primero = evt.getFirstIndex();
            int ultimo = evt.getLastIndex();            
        };
                   
    }    
}
