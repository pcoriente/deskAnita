/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilerias;

import javax.print.attribute.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author carlosp
 */
public class ValidaNumeros extends PlainDocument{
    protected int precision = 0;
    protected boolean negativos = false;
    
    public ValidaNumeros(int precision, boolean negativos){
        super();
        this.precision = precision;
        this.negativos = negativos;
    }
    public void insertString(int arg0, String arg1, AttributeSet arg2) throws BadLocationException{
            if (arg1 != null){
                System.out.println("estamos validando numeros");
                for (int i=0;i<arg1.length();i++)
                    if (!Character.isDigit(arg1.charAt(i)))
                        return;
                super.insertString(arg0, arg1, null);
        //super.insertString(arg0, arg1, arg2);
    } 
    }

}

