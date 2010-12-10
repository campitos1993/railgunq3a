/*
 * AlgSubconjuntos.java
 *
 * Created on 8 de noviembre de 2008, 04:42 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package afgenjava;

import exceptions.AutomataException;
import graphviz.GraphViz;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;
import traductor.Analizador;
import traductor.Token;

/**
 *
 * @author Administrador
 */
public class AlgSubconjuntos {
    Automata AFN;
    /**
     *  AFD, Matriz final que representa el AFD.
     **/
    private Dtrans dtrans;

    
    /**
     *  Lista de estados que se ira formando para el AFD
     */
    ArrayList<ListaEstados> Destados;
            
    
    
    /** Creates a new instance of AlgSubconjuntos */
    public AlgSubconjuntos(Automata AFN) {
        this.AFN = AFN;
        dtrans = new Dtrans();
        Destados = new ArrayList();
    }
    
    
    
    public Dtrans ejecutar() throws AutomataException{
        Iterator it;
        Token simbolo;
        ListaEstados U;
        
        Estado est_inicial = AFN.getEstados().getEstadoInicial();
        ListaEstados list_est = e_cerradura(est_inicial, new ListaEstados());
        list_est.setId(0);
        Destados.add(list_est);
    
        while (hayEstadosSinMarcar()){
            DtransClave clave;
            ListaEstados T = estadoSinMarcar();
            T.setMarcado(true);
            
            it = AFN.getAlpha().iterator();
            while(it.hasNext()){
                simbolo = new Token((String)it.next());
                U = e_cerradura(mover(T, simbolo));
                if(U == null){
                    continue;
                }
                int id_U = estaEnDestados(U);
                if(id_U == -1){
                    U.setMarcado(false);
                    U.setId(Destados.size());
                    Destados.add(U);
                }else{
                    U.setId(id_U);
                }
                clave = new DtransClave(T,simbolo);
                dtrans.setValor(clave, U);
            }
        }
        return this.dtrans;
    }
    
    
    /**
     * Ejecuta el algoritmo "e_cerradura(s)"
     * En donde a partir de un estado s, retorna una lista de estados
     * que se forma de recorrer desde el estado s por transiciones vacias.
     *
     **/
    public ListaEstados e_cerradura(Estado s, ListaEstados listaActual) {
        Iterator it = s.getEnlaces().getIterator();
        ListaEstados listaNueva = null;
        while(it.hasNext()){
            Enlace e = (Enlace) it.next();
            if(e.getEtiqueta().compareTo("€") == 0){
                listaNueva = e_cerradura(e.getDestino(), listaActual);
                listaActual = concatListas(listaActual, listaNueva );
                
            }
        }
        listaActual.insertar(s);
        return listaActual;
    }
    
    
    /**
     *   Implementacion de e_cerradura(ListaEstados) del Algoritmo de Subconjuntos.
     *   Recibe una lista de estados y por cada estado aplica el e_cerradura(estado), 
     * es decir; por cada estado de la lista recibida se recorre recursivamente por 
     * los enlaces "vacio" y se genera una nueva lista.
     *
     **/
    public ListaEstados e_cerradura(ListaEstados T){
        if(T == null){
            return null;
        }
        
        ListaEstados lista_ret = new ListaEstados();
        Iterator it = T.getIterator();
        Estado act;
        
        while(it.hasNext()){
            act = (Estado) it.next();
            lista_ret = concatListas(lista_ret, e_cerradura(act, new ListaEstados()));
        }
        
        return lista_ret;
    }
    
    public ListaEstados mover(ListaEstados T, Token a){
        Iterator itEstados = null; 
        Iterator itEnlaces = null;
        Estado estado = null;
        Enlace enlace = null;
        ListaEstados lista = new ListaEstados();
        
        itEstados = T.getIterator();
        while(itEstados.hasNext()){
            estado = (Estado) itEstados.next();
            itEnlaces = estado.getEnlaces().getIterator();
            
            while(itEnlaces.hasNext()){    
                enlace = (Enlace) itEnlaces.next();
                if(enlace.getEtiqueta().compareTo(a.getValor()) == 0){
                    lista.insertar(enlace.getDestino());
                }
            }
        }
        if(lista.size() == 0){
            return null;
        }else{
            return lista;
        }
    }

    /***
     *  Dice si existen estados que no fueron marcados.
     */
    private boolean hayEstadosSinMarcar(){
        Iterator it = Destados.iterator();
        ListaEstados list_est;
        while (it.hasNext()){
            list_est = (ListaEstados) it.next();
            if(!list_est.isMarcado()){
                return true;
            }
        }
        return false;
    }
    
    private ListaEstados estadoSinMarcar() throws AutomataException{
        Iterator it = Destados.iterator();
        ListaEstados list_est;
        while (it.hasNext()){
            list_est = (ListaEstados) it.next();
            if(!list_est.isMarcado()){
                return list_est;
            }
        }
        throw new AutomataException("No hay Lista de Estados sin marcar en Destados.");
    }
    
    
    /***
     * Metodo que retorna el id de la lista de estados U dentro de 
     * Destados, si es que U no esta en la lista de estados retorna -1.
     *
     */
    private int estaEnDestados(ListaEstados U){
        Iterator it = Destados.iterator();
        ListaEstados tmp;
        while(it.hasNext()){
            tmp = (ListaEstados)it.next();
            if(tmp.compareTo(U) == 0){
                return tmp.getId();
            }
        }
        return -1;
    }
    

    public static ListaEstados concatListas(ListaEstados A, ListaEstados B){
        ListaEstados ret = new ListaEstados();
        Iterator it;
        Estado est_tmp, test;
        
        if(A != null){
            it = A.getIterator();
            while(it.hasNext()){
                est_tmp = (Estado) it.next();
                try{
                    ret.getEstadoById(est_tmp.getId());
                }catch(Exception ex){
                    ret.insertar(est_tmp);
                }
            }
        }

        if(B != null){
            it = B.getIterator();
            while(it.hasNext()){
                est_tmp = (Estado) it.next();
                try{
                    ret.getEstadoById(est_tmp.getId());
                }catch(Exception ex){
                    ret.insertar(est_tmp);
                }
            }
        }

        return ret;
    }
    
    

    /**
     * Metodo estatico que recibe un AFD y retorna un nuevo AFD sin los estados
     * inalcanzables.
     * Necesita del metodo estatico "recorrer"
     * @param AFD
     * @return
     */
    public static Automata eliminar_estados_inalcanzables(Automata AFD){
        Estado inicial = AFD.getInicial();
        AFD.getEstados().resetVisitas();
        visitarRecursivo(inicial);
        
        Automata AFDNEW = new Automata();
        AFDNEW.setAlpha(AFD.getAlpha());
        AFDNEW.setRegex(AFD.getRegex());
                
        Iterator it = AFD.getEstados().getIterator();
        while(it.hasNext()){
            Estado e = (Estado)it.next();
            if(e.isVisitado()){
                
                if(e.isEstadoinicial()){
                   AFDNEW.setInicial(e); 
                }
                if(e.isEstadofinal()){
                    AFDNEW.getFinales().insertar(e);
                }    
                AFDNEW.addEstado(e);
            }
            
        }
        
        return AFDNEW;
    }
    
    
    /**
     * Funcion que marca como visitado un nodo con sus respectivos 
     * hijos, lo hace recursivamente.
     * @param actual
     */
    public static void visitarRecursivo(Estado actual){
        if(!actual.isVisitado()){
            actual.setVisitado(true);
            Iterator it = actual.getEnlaces().iterator();
            while(it.hasNext()){
                Enlace enlace = (Enlace)it.next();
                visitarRecursivo(enlace.getDestino());
            }
        }
    }
    
}
