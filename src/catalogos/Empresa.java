package catalogos;

public class Empresa {
	private String cod_emp;
	private String nombre;
	public Empresa()
	{
	
	}
	public Empresa(String cod_emp, String nombre)
	{
		this.cod_emp = cod_emp;
		this.nombre = nombre;
	}
	
	public String getCod_emp() {
		return cod_emp;
	}
	public void setCod_emp(String codEmp) {
		cod_emp = codEmp;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String toString()
	{
		return this.nombre+"-"+this.cod_emp;
	}
	
}
