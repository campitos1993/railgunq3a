package automatas;

/**
 *
 * @author Administrator
 */
public class Enlace implements Comparable<Enlace> {

    private Estado origen;
    private Estado destino;
    private String etiqueta;
    private boolean vacio;

    /**
     *
     * @param origen
     * @param destino
     * @param label
     */
    public Enlace(Estado origen, Estado destino, String label) {
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

    public int compareTo(Enlace e) {
        
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
    

