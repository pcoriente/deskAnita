package dominio;

import java.sql.Date;

public class CabeceroPedido {
	private String cod_gru;
	private String numped;
	private String cod_cli;
	private String cod_age;
	private String cod_emp;
	private String cod_bod;
	private String cod_ped;
	private float subfac;
	private float descfac;
	private float ivafac;
	private Date fechaped;
	private String bfactur;
	private String cod_lta;
	private String bped;
	private String bbol;
	private String usuario;
	private String nombre;
	private Date fechaent;
	private Date fechacan;
	private Date fecharec;
	private String foliorec;
	private Date fecap;
	private String motivocance;
        private Integer idclienteBanco;
        private Integer idLeyenda;
        
	public String getCod_gru() {
		return cod_gru;
	}

    public Integer getIdLeyenda() {
        return idLeyenda;
    }

    public void setIdLeyenda(Integer idLeyenda) {
        this.idLeyenda = idLeyenda;
    }

    public Integer getIdclienteBanco() {
        return idclienteBanco;
    }

    public void setIdclienteBanco(Integer idclienteBanco) {
        this.idclienteBanco = idclienteBanco;
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
	public String getCod_age() {
		return cod_age;
	}
	public void setCod_age(String codAge) {
		cod_age = codAge;
	}
	public String getCod_emp() {
		return cod_emp;
	}
	public void setCod_emp(String codEmp) {
		cod_emp = codEmp;
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
	public float getSubfac() {
		return subfac;
	}
	public void setSubfac(float subfac) {
		this.subfac = subfac;
	}
	public float getDescfac() {
		return descfac;
	}
	public void setDescfac(float descfac) {
		this.descfac = descfac;
	}
	public float getIvafac() {
		return ivafac;
	}
	public void setIvafac(float ivafac) {
		this.ivafac = ivafac;
	}
	public Date getFechaped() {
		return fechaped;
	}
	public void setFechaped(Date fechaped) {
		this.fechaped = fechaped;
	}
	public String getBfactur() {
		return bfactur;
	}
	public void setBfactur(String bfactur) {
		this.bfactur = bfactur;
	}
	public String getCod_lta() {
		return cod_lta;
	}
	public void setCod_lta(String codLta) {
		cod_lta = codLta;
	}
	public String getBped() {
		return bped;
	}
	public void setBped(String bped) {
		this.bped = bped;
	}
	public String getBbol() {
		return bbol;
	}
	public void setBbol(String bbol) {
		this.bbol = bbol;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getFechaent() {
		return fechaent;
	}
	public void setFechaent(Date fechaent) {
		this.fechaent = fechaent;
	}
	public Date getFechacan() {
		return fechacan;
	}
	public void setFechacan(Date fechacan) {
		this.fechacan = fechacan;
	}
	public Date getFecharec() {
		return fecharec;
	}
	public void setFecharec(Date fecharec) {
		this.fecharec = fecharec;
	}
	public String getFoliorec() {
		return foliorec;
	}
	public void setFoliorec(String foliorec) {
		this.foliorec = foliorec;
	}
	public Date getFecap() {
		return fecap;
	}
	public void setFecap(Date fecap) {
		this.fecap = fecap;
	}
	public String getMotivocance() {
		return motivocance;
	}
	public void setMotivocance(String motivocance) {
		this.motivocance = motivocance;
	}
}
