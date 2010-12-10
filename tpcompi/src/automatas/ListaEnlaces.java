package automatas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Administrator
 */
public class ListaEnlaces extends ArrayList<Enlace> {
 
    private int id; 
    private HashMap<String, Integer> TablaEnlaces;
    private ArrayList<Enlace> vacios;
    
    /**
     *
     */
    public ListaEnlaces(){
        this.TablaEnlaces = new HashMap<String, Integer>();
        this.vacios       = new ArrayList<Enlace>();
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
     * @return
     */
    public int getId() {
        return this.id;
    }

    /**
     *
     * @return
     */
    public ArrayList<Enlace> getVacios() {
        return vacios;
    }

    /**
     *
     * @param index
     * @return
     */
    public Enlace getEnlace(int index) {
        return this.get(index);
    }

    /**
     *
     * @return
     */
    public Iterator<Enlace> getIterator() {
        return this.iterator();
    }

    /**
     *
     * @param e
     */
    public void insertar(Enlace e) {
    
        int     indexToInsert   = this.cantidad();        
        String  simbolo         = e.getEtiqueta();
        
        this.add(e);
        
        if (e.isVacio()) {
            this.agregarEnlaceVacio(e);
        } else {
            this.TablaEnlaces.put(simbolo, indexToInsert);
        }
    }

    /**
     *
     * @param e
     * @param index
     */
    public void insertarAt(Enlace e, int index) {
    
        int     indexToInsert   = index;        
        String  simbolo         = e.getEtiqueta();
        
        this.add(index,e);
        
        if (e.isVacio()) {
            this.agregarEnlaceVacio(e);
        } else {
            this.TablaEnlaces.put(simbolo, indexToInsert);
        }
    }
    
    /**
     *
     * @param symbol
     * @return
     */
    public Enlace getEnlaceSimbolo(String symbol) {
        Integer index = this.TablaEnlaces.get(symbol);        
        Enlace result = null; 
        
        if (index != null) {
            result = this.get(index);
        }
        return result; 
    }

    /**
     *
     * @param l
     */
    public void insertarListaEnlaces(ListaEnlaces l) {
        Iterator <Enlace> i = l.getIterator();
        Enlace current; 
        
        while(i.hasNext()) {
            current = i.next();            
            this.insertar(current);
        }
    }

    private void agregarEnlaceVacio(Enlace e) {
        this.getVacios().add(e);
    }
    
    /**
     *
     * @param e
     */
    public void borrar(Enlace e) {
        
        String simbolo = e.getEtiqueta();
        
        this.remove(e);
        
        if (e.isVacio()) {
            this.getVacios().remove(e);
        } else {
            TablaEnlaces.remove(simbolo);                    
        }        
    }

    /**
     *
     * @return
     */
    public int cantidad() {
        return this.size();
    }

    /**
     *
     * @param e
     * @return
     */
    @SuppressWarnings("element-type-mismatch")
    public boolean contiene(Estado e) {        
        if (this.contains(e)) {
                return true;
        }        
        return false;
    }

    /**
     *
     * @param o
     * @return
     */
    public int compareTo(Object o) {
        
        int result = -1; 
        ListaEnlaces otro = (ListaEnlaces) o;
        
        if (this.cantidad() == otro.cantidad()) {
            for (int i = 0; i < this.cantidad(); i++) {
                Enlace a = this.getEnlace(i);
                Enlace b = otro.getEnlace(i);
                if (a.compareTo(b) != 0) {
                    return -1;
                }
            }
            result = 0;
        }
        return result;
    }
}   
