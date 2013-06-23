package dominio;

public class DetallePedido {
	private String cod_gru;
	private String numped;
	private String cod_cli;
	private String cod_emp;
	private String cod_pro;
	private float cantidad;
	private float preciouni;
	private String cod_bod;
	private String cod_ped;
	private float cantped;
	private String bdet;
	private int orden;
	private float cantent;
	public String getCod_gru() {
		return cod_gru;
	}
	public void setCod_gru(String codGru) {
		cod_gru = codGru;
	}
	public String getNumped() {
		return numped;
	}
	public void setNumped(String numped) {
		this.numped = numped;
	}
	public String getCod_cli() {
		return cod_cli;
	}
	public void setCod_cli(String codCli) {
		cod_cli = codCli;
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
	public float getCantidad() {
		return cantidad;
	}
	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}
	public float getPreciouni() {
		return preciouni;
	}
	public void setPreciouni(float preciouni) {
		this.preciouni = preciouni;
	}
	public String getCod_bod() {
		return cod_bod;
	}
	public void setCod_bod(String codBod) {
		cod_bod = codBod;
	}
	public String getCod_ped() {
		return cod_ped;
	}
	public void setCod_ped(String codPed) {
		cod_ped = codPed;
	}
	public float getCantped() {
		return cantped;
	}
	public void setCantped(float cantped) {
		this.cantped = cantped;
	}
	public String getBdet() {
		return bdet;
	}
	public void setBdet(String bdet) {
		this.bdet = bdet;
	}
	public int getOrden() {
		return orden;
	}
	public void setOrden(int orden) {
		this.orden = orden;
	}
	public float getCantent() {
		return cantent;
	}
	public void setCantent(float cantent) {
		this.cantent = cantent;
	}
}
