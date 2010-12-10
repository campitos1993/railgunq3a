package analizadorlexicosintactico;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Administrator
 */
public class Alfabeto extends ArrayList<String> {

    /**
     *
     * @param simbolos
     */
    public Alfabeto(String simbolos) {
        for (int i = 0; i < simbolos.length(); i++) {
            String tmp = "" + simbolos.charAt(i);
            if (! this.contains(tmp)) {
                this.add(tmp);
            }
        }
        this.ordenar();        
    }
    
    /**
     *
     * @param simbolo
     * @return
     */
    public boolean contiene(String simbolo) {
        if ( this.contains(simbolo) ) return true;
        return false;
    }
    
    private void ordenar() {
        String a[] = new String[1];
        a = this.toArray(a);
        java.util.Arrays.sort(a);

        this.removeAll(this);
        for(int i = 0; i < a.length; i++) {
            this.add(a[i]);
        }
    }

    /**
     *
     * @return
     */
    public int ObtenerTamano() {
        return this.size();
    }

    /**
     *
     * @return
     */
    public Iterator ObtenerIterador() {
        return this.iterator();
    }
}
