package catalogos;

public class Producto {
	private String cod_emp;
	private String cod_pro;
	private String descrip;
	private String codbar;
	private int pzasepq;
	private String biva;
	private float precio;
	public Producto()
	{
		
	}
	public String getCod_emp() {
		return cod_emp;
	}
	public void setCod_emp(String codEmp) {
		cod_emp = codEmp;
	}
	public String getCod_pro() {
		return cod_pro;
	}
	public void setCod_pro(String codPro) {
		cod_pro = codPro;
	}
	public String getDescrip() {
		return descrip;
	}
	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}
	public String getCodbar() {
		return codbar;
	}
	public void setCodbar(String codbar) {
		this.codbar = codbar;
	}
	public int getPzasepq() {
		return pzasepq;
	}
	public void setPzasepq(int pzasepq) {
		this.pzasepq = pzasepq;
	}
	public String getBiva() {
		return biva;
	}
	public void setBiva(String biva) {
		this.biva = biva;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
}
