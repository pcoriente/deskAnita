/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package catalogos;

/**
 *
 * @author carlosp
 */
public class BolGpo {
    private String cod_gru;
    private String cod_emp;
    private String cod_pro;
    private double bolauto;
    private double num;
    private double den;
    private double desctocom1;
    private double desctocom2;
    private double desctocom3;
    private double desctocom4;
    private double desctocom5;
    private double desctologi;

    public double getBolauto() {
        return bolauto;
    }

    public void setBolauto(double bolauto) {
        this.bolauto = bolauto;
    }

    public String getCod_emp() {
        return cod_emp;
    }

    public void setCod_emp(String cod_emp) {
        this.cod_emp = cod_emp;
    }

    public String getCod_gru() {
        return cod_gru;
    }

    public void setCod_gru(String cod_gru) {
        this.cod_gru = cod_gru;
    }

    public String getCod_pro() {
        return cod_pro;
    }

    public void setCod_pro(String cod_pro) {
        this.cod_pro = cod_pro;
    }

    public double getDen() {
        return den;
    }

    public void setDen(double den) {
        this.den = den;
    }

    public double getDesctocom1() {
        return desctocom1;
    }

    public void setDesctocom1(double desctocom1) {
        this.desctocom1 = desctocom1;
    }

    public double getDesctocom2() {
        return desctocom2;
    }

    public void setDesctocom2(double desctocom2) {
        this.desctocom2 = desctocom2;
    }

    public double getDesctocom3() {
        return desctocom3;
    }

    public void setDesctocom3(double desctocom3) {
        this.desctocom3 = desctocom3;
    }

    public double getDesctocom4() {
        return desctocom4;
    }

    public void setDesctocom4(double desctocom4) {
        this.desctocom4 = desctocom4;
    }

    public double getDesctocom5() {
        return desctocom5;
    }

    public void setDesctocom5(double desctocom5) {
        this.desctocom5 = desctocom5;
    }

    public double getDesctologi() {
        return desctologi;
    }

    public void setDesctologi(double desctologi) {
        this.desctologi = desctologi;
    }

    public double getNum() {
        return num;
    }

    public void setNum(double num) {
        this.num = num;
    }
    public String toString() {
        return cod_gru +" " + cod_emp+" "+cod_pro+" "+bolauto+" "+num+" "+den+" "+desctocom1+" "+desctocom2+" "+desctocom3+" "+desctocom4+" "+desctocom5+" "+desctologi;
    }
    
}
