package automatas;

import java.util.*;

/**
 * Clase que implementa el algoritmo de minimizacion de estados de un AFD
 * 
 * @author Marco Alvarez
 * @author Sebastian Lena
 */
public class MinimizacionEstados {

    Automata AFD;

    /**
     *
     * @param automata
     */
    public MinimizacionEstados(Automata automata){
        this.AFD = automata;
    }
    
    /**
     * Algoritmo de minimizacion, explicado en el libro del dragon
     * cuidado, tiene mucho fuego!
     * @return
     * @throws Exception
     */
    public Automata minimizar() throws Exception{
       ArrayList<ListaEstados> anterior = new ArrayList<ListaEstados>();
       ArrayList<ListaEstados> actual = new ArrayList<ListaEstados>();
       
       int numeroEstado = 0;
       ListaEstados noFinales = AFD.getNoFinales();
       ListaEstados finales = AFD.getFinales();
       
       if(noFinales != null && noFinales.cantidad() > 0){
            noFinales.setId(numeroEstado++);
            anterior.add(noFinales);
       }
       
       if(finales != null && finales.cantidad() > 0){
            finales.setId(numeroEstado++);
            anterior.add(finales);           
       }

       boolean seguir = true;
       while(seguir){
           int cant = 0;
           for(ListaEstados cadaLista: anterior){
                Iterator it = separarGrupos(anterior, cadaLista);
                while(it != null && it.hasNext()){
                    ListaEstados list= (ListaEstados)it.next();
                    list.setId(cant++);
                    actual.add(list);
                }
           }
           
           if(anterior.size() == actual.size()){
               seguir = false;
           }else{
               anterior = actual;
               actual = new ArrayList<ListaEstados>();
           }
       }

       Automata AFDM = new Automata();
       Iterator it = actual.iterator();
       while(it.hasNext()){
            ListaEstados lest = (ListaEstados) it.next();
            Estado nuevo = new Estado(lest.getId() , false, false,false);
            
            try{
                lest.getEstadoInicial();
                nuevo.setEstadoinicial(true);
                AFDM.setInicial(nuevo);
            }catch(Exception ex){
                nuevo.setEstadoinicial(false);
            }
                
            if(lest.getEstadosFinales().cantidad() > 0){
                nuevo.setEstadofinal(true);        
                AFDM.getFinales().insertar(nuevo);
            }else{
                nuevo.setEstadofinal(false);
            }
            AFDM.addEstado(nuevo);
       }
       
       it = actual.iterator();
       while(it.hasNext()){
            ListaEstados lest = (ListaEstados) it.next();
            Estado estado_afdm  = AFDM.getEstadoById(lest.getId());
            Estado representante = lest.get(0);
            
            Iterator itenlaces = representante.getEnlaces().getIterator();
            while (itenlaces.hasNext()){
                Arco e = (Arco) itenlaces.next();
                ListaEstados lista_destino = enqueLista(actual, e.getDestino());
                Estado est_destino = AFDM.getEstadoById(lista_destino.getId());
                Arco nuevo_enlace = new Arco(estado_afdm, est_destino, e.getEtiqueta());
                estado_afdm.addEnlace(nuevo_enlace);
            }
       }
       return AFDM;
   }
   
   /**
    * Retorna el iterador de la separacion de todas las lista con la lista,
    * pasados como parametros
    * @param todas
    * @param lista
    * @return
    */
   public Iterator separarGrupos(ArrayList<ListaEstados> todas, ListaEstados lista) {
        Hashtable listasNuevas = new Hashtable(); 
        for(Estado estado : lista){   
            String claveSimbolos = "";
            String claveEstados = "";
            
            for(Arco enlace : estado.getEnlaces()){
                Estado dest = enlace.getDestino();
                ListaEstados tmp = enqueLista(todas, dest);
                claveSimbolos += enlace.getEtiqueta().trim();
                claveEstados += tmp.getId();
                
            }
            String clave = generarClaveHash(claveSimbolos, claveEstados);
            if(listasNuevas.containsKey(clave)){
                ((ListaEstados)listasNuevas.get(clave)).insertar(estado);
            }else{
                ListaEstados nueva = new ListaEstados();
                nueva.insertar(estado);
                listasNuevas.put(clave, nueva);    
            }
        }
        return listasNuevas.values().iterator();
   }
   
   
   /**
    * Retorna el hash para el simbolo y el estado pasados como parametros
    * @param simbolos
    * @param estados
    * @return
    */
   public String generarClaveHash(String simbolos, String estados ){
       String cadenaFinal = "";

        char est[] = estados.toCharArray();
        char c[] = simbolos.toCharArray();
        boolean hayCambios = true;
        for (int i = 0; hayCambios ; i++) {
            hayCambios = false;
            for (int j = 0; j < c.length - 1; j++) {
              if (c[j] > c[j + 1]) {
                  char tmp = c[j+1];
                  c[j+1] = c[j];
                  c[j] = tmp;
                  char tmpEst = est[j+1];
                  est[j+1] = est[j];
                  est[j] = tmpEst;
                  hayCambios = true;
              }
            }
        }
       cadenaFinal = String.copyValueOf(c) + String.copyValueOf(est);
       return cadenaFinal;
   }
   
   /**
    * Para obtener las listas que se encuentran en un estado dado por Estado
    * @param listas
    * @param estado
    * @return
    */
   public ListaEstados enqueLista(ArrayList<ListaEstados> listas, Estado estado){
        for(ListaEstados lista : listas){
            try{
                lista.getEstadoById(estado.getId());
                return lista;
            }catch(Exception ex){}
        }
        return null;
   }
}
