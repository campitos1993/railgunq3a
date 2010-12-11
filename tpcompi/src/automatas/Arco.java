package automatas;

/**
 * Clase para representar los enlaces (arcos) que conectan a los estados
 * de un automata finito.
 * Queda definido por el estado origen, el estado destino, y el simbolo
 * del alfabeto
 * @author Administrator
 */
public class Arco implements Comparable<Arco> {

    private Estado origen;          //estado origen del enlace
    private Estado destino;         //estado destino del enlace
    private String etiqueta;        //la etiqueta, el simbolo del abecedario
    private boolean vacio;          //si es vacio, entonces no tiene etiqueta

    /**
     * Crea un nuevo enlace
     * @param origen estado inicial
     * @param destino estado final
     * @param label la etiqueta
     */
    public Arco(Estado origen, Estado destino, String label) {
        this.origen = origen;
        this.destino = destino;
        this.etiqueta = label;
        
        if (label.compareTo("€")==0) {
            this.vacio = true;
        } else {
            this.vacio = false;
        }
    }

    /**
     *
     * @return
     */
    public Estado getOrigen() {
        return origen;
    }

    /**
     *
     * @param origen
     */
    public void setOrigen(Estado origen) {
        this.origen = origen;
    }

    /**
     *
     * @return
     */
    public Estado getDestino() {
        return destino;
    }

    /**
     *
     * @param destino
     */
    public void setDestino(Estado destino) {
        this.destino = destino;
    }

    /**
     *
     * @return
     */
    public String getEtiqueta() {
        return this.etiqueta;
    }

    /**
     *
     * @param label
     */
    public void setEtiqueta(String label) {
        this.etiqueta = label;
    }

    /**
     *
     * @param vacio
     */
    public void setVacio(boolean vacio) {
        this.vacio = vacio;
    }

    /**
     *
     * @return
     */
    public boolean isVacio() {
        return vacio;
    }

    public int compareTo(Arco e) {
        
        Estado origi;
        Estado desti;
        String simbi;

        origi = e.getOrigen();
        desti = e.getDestino();
        simbi = e.getEtiqueta();
        if (origi == this.getOrigen()
                && desti == this.getDestino()
                && simbi.equals(this.getEtiqueta())
                ) {
            return 0;
        } else {
            return -1;
        }
    }
}
    

