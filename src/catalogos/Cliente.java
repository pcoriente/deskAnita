package catalogos;

public class Cliente {
	private String cod_cli;
	private String cod_gru;
	private String cod_bod;
	private String numtda;
	private String cod_age;
	private String bperm;
	public Cliente(){
		
	}
	public String getCod_cli() {
		return cod_cli;
	}
	public void setCod_cli(String codCli) {
		cod_cli = codCli;
	}
	public String getCod_gru() {
		return cod_gru;
	}
	public void setCod_gru(String codGru) {
		cod_gru = codGru;
	}
	public String getCod_bod() {
		return cod_bod;
	}
	public void setCod_bod(String codBod) {
		cod_bod = codBod;
	}
	public String toString()
	{
		return this.numtda+"-"+this.cod_cli;
	}
	public String getNumtda() {
		return numtda;
	}
	public void setNumtda(String numtda) {
		this.numtda = numtda;
	}
	public String getCod_age() {
		return cod_age;
	}
	public void setCod_age(String codAge) {
		cod_age = codAge;
	}
	public String getBperm() {
		return bperm;
	}
	public void setBperm(String bperm) {
		this.bperm = bperm;
	}

}
