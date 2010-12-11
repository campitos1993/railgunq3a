package automatas;

import java.util.ArrayList;
import java.util.Stack;
import analizadorlexicosintactico.*;

/**
 *
 * @author Administrator
 */
public class Validacion {

    //variables utilizadas para la validacion
    private String validationString;                    //el string a validar
    
    private ListaEstados estadosPath;
    private int currentIndex = -1;
    private Automata automata;                          //el automata
    Stack<Estado> estadosAnt;                           //estados actuales
    Stack<Estado> estadosNew;                           //estados nuevos
    boolean[] yaEstaEn;                                 //si ya esta en nuevos
    ArrayList<Arco> enlacesVacios;                    //aqui guardamos  mover(s,c)
    String currentCar;                                  //el caracter actual
    

    /**
     *
     */
    public Validacion() {
    }

    /**
     * Construye una validacion para una cadena y un automata dado
     * @param validationString
     * @param automata
     */
    public Validacion(String validationString, Automata automata) {
        this.validationString = validationString;
        this.automata = automata;
        
        this.estadosPath = new ListaEstados();
        this.yaEstaEn    = new boolean[automata.getEstados().size()];
        this.estadosAnt  = new Stack<Estado>();
        this.estadosNew  = new Stack<Estado>();
    }

    /**
     * Retorna el estado final
     * @return
     */
    public Estado getEstadoFinal() {
        Estado result = null; 
        
        if (this.automata.getTipo() == TipoAutomata.AFN) {
            // @TODO
        } else {
            if (estadosPath != null) {
                int cantidad = estadosPath.size();       
                if (cantidad > 0) {
                    result = estadosPath.get(cantidad-1);                        
                }
            }
        }
        
        return result;
    }

    /**
     * Retorna un estado prefinal
     * @return
     */
    public Estado getEstadoPreFinal() {
        Estado result = null; 
        
        if (this.automata.getTipo() == TipoAutomata.AFN) {
        } else {
            if (estadosPath != null) {
                int cantidad = estadosPath.size();                
                if (cantidad > 1) {
                    result = estadosPath.get(cantidad-2);                        
                } 
            }
        }
        
        return result;
    }
    
    /**
     * Funcion que realiza la validacion, dependiendo del tipo de automata.
     * Se recorre el automata de modo a validar si la cadena que se pasa como
     * entrada pertenece al alfabeto dada la expresion regular
     * @return
     */
    public boolean validar() {
        boolean exito = true;
        exito = this.validar_AFD();
        return exito; 
    }
    /**
     * Agrega el estado s si el mismo no se encuentra ya agregado
     * @param s
     */
    private void agregarEstado(Estado s) {
        this.estadosNew.push(s);
        this.yaEstaEn[s.getId()] = true;
        
        this.enlacesVacios = s.getEnlacesVacios();
        
        for (Arco e : this.enlacesVacios) {
            Estado t = e.getDestino();
            if (!this.yaEstaEn(t)) {
                this.agregarEstado(t);
            }
        }
    }
    /**
     * Si la lista contiene un estado que es final, devuelve true
     * @param S
     * @return
     */
    private boolean contieneFinal(ListaEstados S) {
        boolean exito = false; 
        for (Estado e : S ) {
            exito = e.isEstadofinal();
            if (exito) {
                break;
            }
        }        
        return exito;
    }
    
    private boolean validar_AFD() {        
        Estado s = this.automata.getInicial();
        String c = this.sigCar();  
        boolean exito = true; 
        this.estadosPath.insertar(s);
        while (c.compareToIgnoreCase("")!=0) {
            s = this.mover(s, c);
            if (s == null) {
                exito = false; 
                break;
            }
            this.estadosPath.insertar(s);
            c = this.sigCar(); 
        }
        if (s != null && !s.isEstadofinal()) {
           exito = false;
        }                
        return exito;
    }
    /**
     * nos movemos al estado s, dependiendo del caracter de entrada c
     * @param s
     * @param c
     * @return el estado al que nos debemos mover
     */
    private Estado mover(Estado s, String c) {
        Estado next = s.getDestinoFromHash(c);
        return next;
    }
    
    private String sigCar() {
        String siguiente = "";
        this.currentIndex++; 
        if (this.currentIndex < this.validationString.length()) {
            siguiente = this.validationString.charAt(this.currentIndex)+"";            
        } else {
            this.currentIndex = 0; 
        }
        return siguiente; 
    }
    
    private String currentCar() {
        String siguiente = this.validationString.charAt(this.currentIndex)+"";
        return siguiente; 
    }
    
    
    /**
     * Obtenemos la cadena a validar
     * @return
     */
    public String getValidationString() {
        return validationString;
    }

    /**
     * Seteamos la cadena a validar
     * @param validationString
     */
    public void setValidationString(String validationString) {
        this.validationString = validationString;
        this.currentIndex = 0; 
        this.estadosPath = new ListaEstados();
    }


    /**
     * Obtenemos la lista de los estados del camino tomado por el automata
     */
    public ListaEstados getEstadosPath() {
        return estadosPath;
    }

    /**
     * Obtenemos el indice de la lista
     * @return
     */
    public int getCurrentIndex() {
        return currentIndex;
    }

    /**
     * getter del automata
     * @return
     */
    public Automata getAutomata() {
        return automata;
    }

    /**
     * Setea el tipo de automata, AFD, AFDMin
     * @param automata
     */
    public void setAutomata(Automata automata) {
        this.automata = automata;
    }
    /**
     * nos dice si el estado t dado como parametro ya se encuentra
     * @param t
     * @return
     */
    private boolean yaEstaEn(Estado t) {
        return this.yaEstaEn[t.getId()];
    }
    
    /**
     * nos retorna el camino de los estados del automata
     * @return
     */
    public String getSimulationPath() {
        return this.estadosPath.toString();
    }
    
}
