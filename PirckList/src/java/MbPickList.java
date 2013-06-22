/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Comodoro
 */
@ManagedBean
@RequestScoped
public class MbPickList {

    /**
     * Creates a new instance of MbPickList
     */
    private DualListModel<String> x;
    List<String> estadosSource;
    List<String> estadosTarget;

    public MbPickList() {
        estadosSource.add("Yucatan");
        estadosSource.add("Veracruz");
        estadosSource.add("Morelia");
        estadosSource.add("Campeche");
        x=new DualListModel<String>(estadosSource, estadosTarget);
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
    
    
}
