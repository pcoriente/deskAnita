/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

/**
 *
 * @author carlosp
 */
public class VentasPedidos {

    private String cod_bod;
    private String descrip;
    private double ped_ani;
    private double fac_ani;
    private double ped_qui;
    private double fac_qui;
    private double total;
    private double tendencia;

    public String getCod_bod() {
        return cod_bod;
    }

    public void setCod_bod(String cod_bod) {
        this.cod_bod = cod_bod;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public double getFac_ani() {
        return fac_ani;
    }

    public void setFac_ani(double fac_ani) {
        this.fac_ani = fac_ani;
    }

    public double getFac_qui() {
        return fac_qui;
    }

    public void setFac_qui(double fac_qui) {
        this.fac_qui = fac_qui;
    }

    public double getPed_ani() {
        return ped_ani;
    }

    public void setPed_ani(double ped_ani) {
        this.ped_ani = ped_ani;
    }

    public double getPed_qui() {
        return ped_qui;
    }

    public void setPed_qui(double ped_qui) {
        this.ped_qui = ped_qui;
    }

    public double getTendencia() {
        return tendencia;
    }

    public void setTendencia(double tendencia) {
        this.tendencia = tendencia;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
 
}
