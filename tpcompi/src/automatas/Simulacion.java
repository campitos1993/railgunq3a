package automatas;

import java.util.ArrayList;
import java.util.Stack;
import analizadorlexicosintactico.*;

/**
 *
 * @author Administrator
 */
public class Simulacion {

    private String validationString;
    private ArrayList<ListaEstados> estadosPathAFN;
    private ListaEstados estadosPath;
    private int currentIndex = -1;
    private Automata automata;
    Stack<Estado> estadosAnt;
    Stack<Estado> estadosNew;
    boolean[] yaEstaEn;
    ArrayList<Enlace> enlacesVacios;
    String currentCar; 
    

    /**
     *
     */
    public Simulacion() {
    }

    /**
     *
     * @param validationString
     * @param automata
     */
    public Simulacion(String validationString, Automata automata) {
        this.validationString = validationString;
        this.automata = automata;
        
        this.estadosPathAFN = new ArrayList<ListaEstados>();
        this.estadosPath = new ListaEstados();
        this.yaEstaEn    = new boolean[automata.getEstados().size()];
        this.estadosAnt  = new Stack<Estado>();
        this.estadosNew  = new Stack<Estado>();
    }

    /**
     *
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
     *
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
     *
     * @return
     */
    public boolean validar() {
        boolean exito = true;
        
        if (this.automata.getTipo() == TipoAutomata.AFN) {
            exito = this.validar_AFN();
        } else {
            exito = this.validar_AFD();
        }
            
        return exito; 
    }

    private void agregarEstado(Estado s) {
        this.estadosNew.push(s);
        this.yaEstaEn[s.getId()] = true;
        
        this.enlacesVacios = s.getEnlacesVacios();
        
        for (Enlace e : this.enlacesVacios) {
            Estado t = e.getDestino();
            if (!this.yaEstaEn(t)) {
                this.agregarEstado(t);
            }
        }
    }

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
    
    private Estado validar_AFN_Backtracking(Estado current_state) {
        
        String current = this.currentCar();
        Estado result = current_state; 
        Enlace path = current_state.getEnlaceSimboloFromHash(current);

        if (path == null && this.automata.getTipo() == TipoAutomata.AFN) {
            ArrayList<Enlace> emptys = current_state.getEnlacesVacios();
            for (Enlace enlace : emptys) {                
                Estado siguiente = enlace.getDestino();       
                int indexEstado = this.estadosPath.cantidad();
                this.estadosPath.add(siguiente);
                result = this.validar_AFN_Backtracking(siguiente);
                if (result != null) {
                    break;
                }
                this.estadosPath.remove(indexEstado);
            }
        } else {
            Estado siguiente = path.getDestino();        
            this.estadosPath.add(siguiente);            
            this.sigCar();
            result = this.validar_AFN_Backtracking(siguiente);
        }
        return result;         
    }

    
    private boolean validar_AFN() {
        boolean exito = true; 
        Subconjuntos subc = new Subconjuntos(this.automata);
        ListaEstados S = new ListaEstados();
        S = subc.e_cerradura(this.automata.getInicial(), S);
        String c = this.sigCar();
        
        this.estadosPathAFN.add(S);
        while (c.compareToIgnoreCase("")!=0) {
            S = subc.mover(S, new Token(c));
            S = subc.e_cerradura(S);   
            if (S == null || S.size() == 0) {
                exito = false;
                break;
            }
            this.estadosPathAFN.add(S);
            c = this.sigCar(); 
        }
        if (exito) {
            exito = this.contieneFinal(S);
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
     *
     * @return
     */
    public String getValidationString() {
        return validationString;
    }

    /**
     *
     * @param validationString
     */
    public void setValidationString(String validationString) {
        this.validationString = validationString;
        this.currentIndex = 0; 
        this.estadosPath = new ListaEstados();
    }

    /**
     *
     * @return
     */
    public ArrayList<ListaEstados> getEstadosPathAFN() {
        return estadosPathAFN;
    }

    /**
     *
     * @return
     */
    public ListaEstados getEstadosPath() {
        return estadosPath;
    }

    /**
     *
     * @return
     */
    public int getCurrentIndex() {
        return currentIndex;
    }

    /**
     *
     * @return
     */
    public Automata getAutomata() {
        return automata;
    }

    /**
     *
     * @param automata
     */
    public void setAutomata(Automata automata) {
        this.automata = automata;
    }

    private boolean yaEstaEn(Estado t) {
        return this.yaEstaEn[t.getId()];
    }
    
    /**
     *
     * @return
     */
    public String getSimulationPath() {
        return this.estadosPath.toString();
    }
    
}
