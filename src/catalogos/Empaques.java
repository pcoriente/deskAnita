/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package catalogos;

/**
 *
 * @author carlosp
 */
public class Empaques {
    private int idEmpaque;
    private String empaque;
    private int capacidad;
    private double peso;
    private double volumen;

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getEmpaque() {
        return empaque;
    }

    public void setEmpaque(String empaque) {
        this.empaque = empaque;
    }

    public int getIdEmpaque() {
        return idEmpaque;
    }

    public void setIdEmpaque(int idEmpaque) {
        this.idEmpaque = idEmpaque;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getVolumen() {
        return volumen;
    }

    public void setVolumen(double volumen) {
        this.volumen = volumen;
    }
    public String toString()
    {
	return this.idEmpaque+"  "+this.empaque+ +this.capacidad+ +this.peso+ +this.volumen;
    }    
}
