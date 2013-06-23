/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package catalogos;

/**
 *
 * @author carlosp
 */
public class MetodosPago {

    private int idLeyenda;
    private String leyenda;

    public MetodosPago(int idLeyenda, String leyenda) {
        this.idLeyenda = idLeyenda;
        this.leyenda = leyenda;
    }

    public MetodosPago() {
    }

    public int getIdLeyenda() {
        return idLeyenda;
    }

    public void setIdLeyenda(int idLeyenda) {
        this.idLeyenda = idLeyenda;
    }

    public String getLeyenda() {
        return leyenda;
    }

    public void setLeyenda(String leyenda) {
        this.leyenda = leyenda;
    }

    public String toString() {
        return this.leyenda;
    }
}
