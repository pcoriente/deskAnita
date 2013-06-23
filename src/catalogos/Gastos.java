/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package catalogos;

/**
 *
 * @author carlosp
 */
public class Gastos {
    protected String concepto;
    protected String subconcepto;
    protected String descripcion;
    protected double importe;
    
    public Gastos(){
        concepto = "";
        subconcepto = "";
        descripcion = "";
        importe = 0;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getSubconcepto() {
        return subconcepto;
    }

    public void setSubconcepto(String subconcepto) {
        this.subconcepto = subconcepto;
    }
}
