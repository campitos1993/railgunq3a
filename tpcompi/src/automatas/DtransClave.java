package automatas;

import analizadorlexicosintactico.Token;

/**
 *
 * @author Administrator
 */
public class DtransClave {

    private ListaEstados indiceEstados;
    private Token indiceToken;

    /**
     *
     * @param list
     * @param tok
     */
    public DtransClave(ListaEstados list, Token tok) {
        this.indiceEstados = list;
        this.indiceToken = tok;
    }
    
    /**
     *
     * @return
     */
    public ListaEstados getIndiceEstados() {
        return this.indiceEstados;
    }

    /**
     *
     * @param indiceEstados
     */
    public void setIndiceEstados(ListaEstados indiceEstados) {
        this.indiceEstados = indiceEstados;
    }

    /**
     *
     * @return
     */
    public Token getIndiceToken() {
        return this.indiceToken;
    }

    /**
     * 
     * @param indiceToken
     */
    public void setIndiceToken(Token indiceToken) {
        this.indiceToken = indiceToken;
    }
    
    /**
     *
     * @param otro
     * @return
     */
    public int compareTo(Object otro){
        DtransClave o = (DtransClave) otro;
        if(indiceToken.getValor().compareTo(o.getIndiceToken().getValor()) == 0) {
            if(indiceEstados.compareTo(o.getIndiceEstados()) == 0){
                return 0;
            }else{
                return -1;    
            }
        }else{
            return -1;
        }
    }
}
