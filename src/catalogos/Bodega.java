/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package catalogos;

/**
 *
 * @author carlosp
 */
public class Bodega {
  private String cod_bod;
  private String cd;
    public Bodega() 
    {

    }
  
  public Bodega(String cod_bod, String cd){
        this.cod_bod = cod_bod;
        this.cd = cd;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getCod_bod() {
        return cod_bod;
    }

    public void setCod_bod(String cod_bod) {
        this.cod_bod = cod_bod;
    }


    @Override
    public String toString() {
        return cd +"-" + cod_bod;
    }

            
}
