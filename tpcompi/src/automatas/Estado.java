package automatas;

import java.util.ArrayList;
import analizadorlexicosintactico.Token;

/**
 *
 * @author Administrator
 */
public class Estado implements Comparable<Estado> {
    private int id; 
    private ListaEnlaces enlaces;

    private boolean estadoinicial;
    private boolean estadofinal;
    private boolean visitado;

    /**
     *
     * @param id
     * @param esInicial
     * @param esFinal
     * @param visitado
     */
    public Estado(int id, boolean esInicial, boolean esFinal, boolean visitado) {
        this.id         = id;
        this.estadoinicial  = esInicial;
        this.estadofinal    = esFinal;
        this.visitado   = visitado;
        this.enlaces = new ListaEnlaces();
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public ListaEnlaces getEnlaces() {
        return enlaces;
    }

    /**
     *
     * @return
     */
    public boolean isEstadofinal() {
        return estadofinal;
    }

    /**
     *
     * @return
     */
    public boolean isEstadoinicial() {
        return estadoinicial;
    }

    /**
     *
     * @return
     */
    public boolean isVisitado() {
        return visitado;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @param estadofinal
     */
    public void setEstadofinal(boolean estadofinal) {
        this.estadofinal = estadofinal;
    }

    /**
     *
     * @param estadoinicial
     */
    public void setEstadoinicial(boolean estadoinicial) {
        this.estadoinicial = estadoinicial;
    }

    /**
     *
     * @param visitado
     */
    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    /**
     *
     * @param e
     */
    public void addEnlace(Enlace e) {
        enlaces.insertar(e);        
    }

    /**
     *
     * @param a
     * @return
     */
    public Estado estadoDestino(Token a){
        return estadoDestinoString(a.getValor());
    }

    /**
     *
     * @param a
     * @return
     */
    public Estado estadoDestinoString(String a){
        for(Enlace x: enlaces){
            if(x.getEtiqueta().compareTo(a)== 0){
                return x.getDestino();
            }
        }
        return null;   
    }
    
    /**
     *
     * @param simbolo
     * @return
     */
    public Estado getDestinoFromHash(String simbolo) {
        Enlace link = this.getEnlaceSimboloFromHash(simbolo); 
        Estado result = null;
        
        if (link != null) {
            result =link.getDestino();
        }
        return result;
    }

    /**
     *
     * @param simbolo
     * @return
     */
    public Enlace getEnlaceSimboloFromHash(String simbolo) {
        return this.enlaces.getEnlaceSimbolo(simbolo);
    }

    /**
     *
     * @return
     */
    public ArrayList<Enlace> getEnlacesVacios() {
        return this.enlaces.getVacios();
    }
    
    
    /**
     *
     * @param e
     */
    public void eliminarEnlace(Enlace e){
        this.enlaces.borrar(e);
    }
    
    /**
     *
     * @return
     */
    public boolean esEstadoMuerto(){
        if(isEstadofinal()){
            return false;
        }
        
        boolean esMuerto = true;
        for(Enlace e: this.enlaces){
            if(e.getDestino().getId() != this.getId()){
                esMuerto = false;
            }
        }
        return esMuerto;
    }

    public int compareTo(Estado e) {
        if (this.getId() == e.getId()) {
            return 0;
        } else if (this.getId() > e.getId()) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        String result = ""+id;
        if (this.isEstadofinal()) {
            result = result + "(fin)";
        }
        
        if (this.isEstadoinicial()){
            result = result + "(ini)";
        }
        return result; 
    }
}
