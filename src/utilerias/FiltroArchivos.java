/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilerias;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author carlosp
 */
public class FiltroArchivos extends FileFilter {

    @Override
    public boolean accept(File f) {
        //throw new UnsupportedOperationException("Not supported yet.");
        if (f.isDirectory()) {
            return true;
        }

        String extension = Utils.getExtension(f);
        if (extension != null) {
            if (extension.equals(Utils.xls)
                    || extension.equals(Utils.xlsx)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public String getDescription() {
        //throw new UnsupportedOperationException("Not supported yet.");
        return ("Archivos Excel");
    }
}
