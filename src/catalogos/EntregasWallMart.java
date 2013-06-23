/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package catalogos;
/**
 *
 * @author carlosp
 */
public class EntregasWallMart {
    	private String idGln;
	private String idTienda;

//    public EntregasWallMart(String idGln, String idTienda) {
//        this.idGln = idGln;
//        this.idTienda = idTienda;
//    }

    

    public String getIdGln() {
        return idGln;
    }

    public void setIdGln(String idGln) {
        this.idGln = idGln;
    }

    public String getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(String idTienda) {
        this.idTienda = idTienda;
    }
    @Override
public String toString(){
    return (idTienda+"-"+idGln);
    };
}
