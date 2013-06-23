package catalogos;

public class Grupo {
	private String cod_gru;
	private String nombre;
	private String nomgen;
	public Grupo(){}
	
	public Grupo(String cod_gru, String nombre, String nomgen)
	{
		this.cod_gru = cod_gru;
		this.nombre = nombre;
		this.nomgen = nomgen;
	}
	
	
	public String getCod_gru() {
		return cod_gru;
	}
	public void setCod_gru(String codGru) {
		cod_gru = codGru;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNomgen() {
		return nomgen;
	}
	public void setNomgen(String nomgen) {
		this.nomgen = nomgen;
	}
	public String toString()
	{
		return this.nomgen.trim()+" - "+this.cod_gru.trim();
	}
	
}
