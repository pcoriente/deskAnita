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
public class ConsultaCanceladas {
    private String tipofac;
    private String cod_fac;
    private String cod_cli;
    private Date fechafac;
    private Date fechacan;
    private Double subfac;
    private Double descfac;
    private Double ivafac;
    private String cod_age;
    private String cod_bod;

    public String getCod_bod() {
        return cod_bod;
    }

    public void setCod_bod(String cod_bod) {
        this.cod_bod = cod_bod;
    }
    public String getCod_cli() {
        return cod_cli;
    }

    public String getCod_age() {
        return cod_age;
    }

    public void setCod_age(String cod_age) {
        this.cod_age = cod_age;
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

    public Double getDescfac() {
        return descfac;
    }

    public void setDescfac(Double descfac) {
        this.descfac = descfac;
    }

    public Date getFechacan() {
        return fechacan;
    }

    public void setFechacan(Date fechacan) {
        this.fechacan = fechacan;
    }

    public Date getFechafac() {
        return fechafac;
    }

    public void setFechafac(Date fechafac) {
        this.fechafac = fechafac;
    }

    public Double getIvafac() {
        return ivafac;
    }

    public void setIvafac(Double ivafac) {
        this.ivafac = ivafac;
    }

    public Double getSubfac() {
        return subfac;
    }

    public void setSubfac(Double subfac) {
        this.subfac = subfac;
    }

    public String getTipofac() {
        return tipofac;
    }

    public void setTipofac(String tipofac) {
        this.tipofac = tipofac;
    }
    
}
