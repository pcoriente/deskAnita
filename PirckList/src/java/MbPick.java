/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Comodoro
 */
@ManagedBean
@SessionScoped
public class MbPick implements Serializable {

    private DualListModel<String> x;
    List<String> estadosSource = new ArrayList<String>();
    List<String> estadosTarget = new ArrayList<String>();

    /**
     * Creates a new instance of MbPick
     */
    public MbPick() {
        estadosSource.add("Yucatan");
        estadosSource.add("Veracruz");
        estadosSource.add("Morelia");
        estadosSource.add("Campeche");
        x = new DualListModel<String>(estadosSource, estadosTarget);
    }

    public DualListModel<String> getX() {
        return x;
    }

    public void setX(DualListModel<String> x) {
        this.x = x;
    }

    public List<String> getEstadosSource() {
        return estadosSource;
    }

    public void setEstadosSource(List<String> estadosSource) {
        this.estadosSource = estadosSource;
    }

    public List<String> getEstadosTarget() {
        return estadosTarget;
    }

    public void setEstadosTarget(List<String> estadosTarget) {
        this.estadosTarget = estadosTarget;
    }

    public void dameValores() {
        
        
        if(estadosTarget.isEmpty()){
            System.err.println("Esta vacio");
        }
    }
}
