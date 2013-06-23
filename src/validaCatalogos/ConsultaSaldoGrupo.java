/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package validaCatalogos;

import java.util.Date;

/**
 *
 * @author carlosp
 */
public class ConsultaSaldoGrupo {
    private String cod_cli;
    private String numtda;
    private String pedido;
    private String cod_bod;
    private String cod_fac;
    private Date fechapli;
    private double importe;
    private double saldo;

    public String getCod_bod() {
        return cod_bod;
    }

    public void setCod_bod(String cod_bod) {
        this.cod_bod = cod_bod;
    }

    public String getCod_cli() {
        return cod_cli;
    }

    public void setCod_cli(String cod_cli) {
        this.cod_cli = cod_cli;
    }

    public String getCod_fac() {
        return cod_fac;
    }

    public void setCod_fac(String cod_fac) {
        this.cod_fac = cod_fac;
    }

    public Date getFechapli() {
        return fechapli;
    }

    public void setFechapli(Date fechapli) {
        this.fechapli = fechapli;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getNumtda() {
        return numtda;
    }

    public void setNumtda(String numtda) {
        this.numtda = numtda;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
} 
