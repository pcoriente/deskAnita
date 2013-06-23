/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package catalogos;

/**
 *
 * @author carlosp
 */
public class GruposCuentas {

    private int idGrupoBanco;
    private int codigoGrupo;
    private String numCtaPago;
    private String nombreBanco;
    private int idBanco;

    public int getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(int idBanco) {
        this.idBanco = idBanco;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public Integer getIdGrupoBanco() {
        return idGrupoBanco;
    }

    public void setIdGrupoBanco(Integer idGrupoBanco) {
        this.idGrupoBanco = idGrupoBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public GruposCuentas() {
    }

    public GruposCuentas(int codigoGrupo, String numCtaPago) {
        this.codigoGrupo = codigoGrupo;
        this.numCtaPago = numCtaPago;
    }

    public int getCodigoGrupo() {
        return codigoGrupo;
    }

    public void setCodigoGrupo(int codigoGrupo) {
        this.codigoGrupo = codigoGrupo;
    }

    public String getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }

    public String getNumCtaPago() {
        return numCtaPago;
    }

    public void setNumCtaPago(String numCtaPago) {
        this.numCtaPago = numCtaPago;
    }
    private String medioPago;

    public String toString() {
        return this.numCtaPago.trim() + " - " + this.nombreBanco.trim() + " - " + this.idGrupoBanco;
    }
}
