package automatas;

import java.util.ArrayList;
import java.util.Iterator;
import analizadorlexicosintactico.Token;

/**
 * Implementa los algoritmos de subconjuntos de modo a convertir un
 * AFN a un AFD equivalente.
 * @author Administrator
 */
public class Subconjuntos {
    Automata AFN;                        //el AFN de entrada
    private Dtrans dtrans;               //matriz para representar el AFD
    ArrayList<ListaEstados> Destados;    //lista de estados 
            
    /**
     *
     * @param AFN
     */
    public Subconjuntos(Automata AFN) {
        this.AFN = AFN;
        dtrans = new Dtrans();
        Destados = new ArrayList();
    }

    /**
     *
     * @return
     * @throws Exception
     */
    public Dtrans ejecutar() throws Exception{
        Iterator iterdor;
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
            
            iterdor = AFN.getAlpha().iterator();
            while(iterdor.hasNext()){
                simbolo = new Token((String)iterdor.next());
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
     * Implementa y ejecuta el algoritmo e_cerradura(s),
     *
     **/
    public ListaEstados e_cerradura(Estado s, ListaEstados listaActual) {
        Iterator it = s.getEnlaces().getIterator();
        ListaEstados listaNueva = null;
        while(it.hasNext()){
            Arco e = (Arco) it.next();
            if(e.getEtiqueta().compareTo("€") == 0){
                listaNueva = e_cerradura(e.getDestino(), listaActual);
                listaActual = concatListas(listaActual, listaNueva );
                
            }
        }
        listaActual.insertar(s);
        return listaActual;
    }
    
   /**
     * Implementacion de e_cerradura(ListaEstados) del Algoritmo de Subconjuntos.
     * Por cada estado de la lista recibida se recorre recursivamente por
     * los enlaces "vacio" y se genera una nueva lista.
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
    
    /**
     *
     * @param T
     * @param a
     * @return
     */
    public ListaEstados mover(ListaEstados T, Token a){
        Iterator itEstados = null; 
        Iterator itEnlaces = null;
        Estado estado = null;
        Arco enlace = null;
        ListaEstados lista = new ListaEstados();
        
        itEstados = T.getIterator();
        while(itEstados.hasNext()){
            estado = (Estado) itEstados.next();
            itEnlaces = estado.getEnlaces().getIterator();
            
            while(itEnlaces.hasNext()){    
                enlace = (Arco) itEnlaces.next();
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

    /**
     * tenemos estados sin marcar aun?
     * @return
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
    /**
     * Retorna la lista de estados sin marcar
     * @return
     * @throws Exception
     */
    private ListaEstados estadoSinMarcar() throws Exception{
        Iterator it = Destados.iterator();
        ListaEstados list_est;
        while (it.hasNext()){
            list_est = (ListaEstados) it.next();
            if(!list_est.isMarcado()){
                return list_est;
            }
        }
        throw new Exception("No hay Lista de Estados sin marcar en Destados.");
    }
     
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
    

    /***
     * Retorna el id de la lista de estados E dentro de
     * Destados, si es que E no esta en la lista de estados retorna -1.
     */
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
     * Elimina aquellos estados que no pueden ser alcanzados
     * @param AFD
     * @return
     */
    public static Automata eliminar_estados_inalcanzables(Automata AFD){
        Estado inicial = AFD.getInicial();
        AFD.getEstados().resetVisitas();
        visitarRecursivo(inicial);
        
        Automata AFDNEW = new Automata();
        AFDNEW.setAlfabeto(AFD.getAlpha());
        AFDNEW.setExpresion(AFD.getRegex());
                
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
     * Para marcar como vistitado un nodo asi como a su hijos, en forma
     * recursiva
     * @param actual
     */
    public static void visitarRecursivo(Estado actual){
        if(!actual.isVisitado()){
            actual.setVisitado(true);
            Iterator it = actual.getEnlaces().iterator();
            while(it.hasNext()){
                Arco enlace = (Arco)it.next();
                visitarRecursivo(enlace.getDestino());
            }
        }
    }
}
