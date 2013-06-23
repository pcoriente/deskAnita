/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author carlosp
 */
public class ErrorTransaccion extends Exception{
    private int numero;
    private String descripcion;
    private String msgOriginal;
    
    public ErrorTransaccion() {}
    

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMsgOriginal() {
        return msgOriginal;
    }

    public void setMsgOriginal(String msgOriginal) {
        this.msgOriginal = msgOriginal;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    public ErrorTransaccion(SQLException e) {
        //String tmp, tmp1;
        //int x;

        this.numero=e.getErrorCode();
        this.msgOriginal=e.getMessage();
        switch (this.numero) {
            case 208:
                this.descripcion="El nombre de objeto '%1!' no es válido\n"+this.msgOriginal;
                JOptionPane.showMessageDialog(null,this.descripcion,"Aviso",
                        JOptionPane.ERROR_MESSAGE);
                break;
            case 109: 
                this.descripcion="Hay más columnas en la instrucción INSERT que valores en la cláusula VALUES. El número de valores de VALUES debe coincidir con el de columnas de INSERT\n"+this.msgOriginal;
                JOptionPane.showMessageDialog(null,this.descripcion,"Aviso",
                    JOptionPane.ERROR_MESSAGE);
                break;
            case 207:
                this.descripcion="El nombre de columna '%1!' no es válido\n"+this.msgOriginal;
                JOptionPane.showMessageDialog(null,this.descripcion,"Aviso",
                    JOptionPane.ERROR_MESSAGE);
                break;
            default:
                this.descripcion="Este mensaje no esta en la lista, chécalo !!!\n"+this.msgOriginal;
                JOptionPane.showMessageDialog(null,this.descripcion,"Aviso",
                    JOptionPane.ERROR_MESSAGE);
                break;                 
        }
    }
    
    public String toString () {
        String str="";

        if(this.numero > 0) str="Error "+this.numero+": ";
        str=str+this.descripcion;
        return str;
    }
    
}
