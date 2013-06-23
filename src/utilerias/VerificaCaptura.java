/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilerias;

import java.awt.Toolkit;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author carlosp
 */
public class VerificaCaptura extends InputVerifier{
    int op=-1;
    public static int DOBLE = 1;
    public static int ENTERO = 2;
    public static int FLOTANTE = 3;
    public static int LONGITUD = 4;
    
    public VerificaCaptura(int opcion){
        op = opcion;
    }
    //static int CARACTER=2;
    @Override
    public boolean verify(JComponent input) {
        JTextField txt=(JTextField)input;
        String t=txt.getText();
        boolean sw=true;
        
        if (op == DOBLE){
            try{
                //Float.parseFloat(t);
                Double.parseDouble(t);
                sw = true;
            }catch(Exception e){
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null,"No se permiten caracteres Alfanuméricos","Formato Doble",JOptionPane.ERROR_MESSAGE);
                    ((JTextField)input).selectAll();
                sw = false; 
            }
        }else
            if (op == ENTERO){
                try{
                    Integer.parseInt(t);
                    sw = true;
                }catch(Exception e){
                    Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(null,"No se permiten caracteres Alfanuméricos","Formato Numérico",JOptionPane.ERROR_MESSAGE);
                    ((JTextField)input).selectAll();
                sw = false; 

                }
            }else
                if (op == LONGITUD){
                    try{
                        if (t.length() > txt.getColumns()){
                            sw = false;
                            JOptionPane.showMessageDialog(null,"Longitud excede caracteres Permitidos, "+txt.getColumns(),"Formato Incorrecto",JOptionPane.ERROR_MESSAGE);
                                ((JTextField)input).selectAll();
                        }
                    }catch(Exception e){
                        Toolkit.getDefaultToolkit().beep();
                        sw = false;
                    }
                }else
                    if (op == FLOTANTE){
                        try{
                            Float.parseFloat(t);
                            sw = true;
                        }catch(Exception e){
                            Toolkit.getDefaultToolkit().beep();
                            JOptionPane.showMessageDialog(null,"No se permiten caracteres Alfanuméricos","Formato Flotante",JOptionPane.ERROR_MESSAGE);
                                ((JTextField)input).selectAll();
                            sw = false; 
                        }
                    }
        return sw;
    }
}
 
