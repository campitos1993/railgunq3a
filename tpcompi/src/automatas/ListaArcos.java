package automatas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Clase que implementa el manejo de los enlaces de un estado, es decir
 * aquellos que salen del mismo
 * @author Administrator
 */
public class ListaArcos extends ArrayList<Arco> {
 
    private int id;                                     //id de la lista
    private HashMap<String, Integer> TablaEnlaces;      //tabla hash para la lista de enlaces, permite indexar para cada símbolo del alfabeto, el índice del array list
    private ArrayList<Arco> vacios;                   //lista de enlaces cuyo label es el 'e'
    
    /**
     * Crea una nueva lista, setea la tabla y la lista de vacios
     */
    public ListaArcos(){
        this.TablaEnlaces = new HashMap<String, Integer>();
        this.vacios       = new ArrayList<Arco>();
    }

    /**
     * Setter del identificador
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Getter del identificador
     * @return
     */
    public int getId() {
        return this.id;
    }

    /**
     * Obtener la lista de enlaces
     * @return
     */
    public ArrayList<Arco> getVacios() {
        return vacios;
    }

    /**
     * Obtenemos el enlace con id = index
     * @param index
     * @return
     */
    public Arco getEnlace(int index) {
        return this.get(index);
    }

    /**
     * Obtenemos el Iterador sobre la lista
     * @return
     */
    public Iterator<Arco> getIterator() {
        return this.iterator();
    }

    /**
     * Add un nuevo enlace e a la lista
     * @param e
     */
    public void insertar(Arco e) {
    
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
     * Insertar un nuevo enlace en la posicion indicada por index
     * @param e
     * @param index
     */
    public void insertarAt(Arco e, int index) {
    
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
     * Obtenemos el enlace cuya etiqueta = symbol
     * @param symbol
     * @return
     */
    public Arco getEnlaceSimbolo(String symbol) {
        Integer index = this.TablaEnlaces.get(symbol);        
        Arco result = null;
        
        if (index != null) {
            result = this.get(index);
        }
        return result; 
    }

    /**
     * Concatenar una lista enlaces a esta al final.
     * @param l
     */
    public void insertarListaEnlaces(ListaArcos l) {
        Iterator <Arco> i = l.getIterator();
        Arco current;
        
        while(i.hasNext()) {
            current = i.next();            
            this.insertar(current);
        }
    }
    /**
     * add un enlace cuya etiqueta = vacio
     * @param e
     */
    private void agregarEnlaceVacio(Arco e) {
        this.getVacios().add(e);
    }
    
    /**
     * Borra el enlace e
     * @param e
     */
    public void borrar(Arco e) {
        
        String simbolo = e.getEtiqueta();
        
        this.remove(e);
        
        if (e.isVacio()) {
            this.getVacios().remove(e);
        } else {
            TablaEnlaces.remove(simbolo);                    
        }        
    }

    /**
     * Retorna la cantidad de enlaces de la lista
     * @return
     */
    public int cantidad() {
        return this.size();
    }

    /**
     * Si la lista contiene el enlace e
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
     * Para comparar dos listas de enlaces
     * @param o
     * @return
     */
    public int compareTo(Object o) {
        
        int result = -1; 
        ListaArcos otro = (ListaArcos) o;
        
        if (this.cantidad() == otro.cantidad()) {
            for (int i = 0; i < this.cantidad(); i++) {
                Arco a = this.getEnlace(i);
                Arco b = otro.getEnlace(i);
                if (a.compareTo(b) != 0) {
                    return -1;
                }
            }
            result = 0;
        }
        return result;
    }
}   
