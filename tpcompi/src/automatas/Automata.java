package automatas;
import java.util.*;

/**
 *
 * @author Administrator
 */
public class Automata {

    private ListaEstados estados;  
    private Estado inicial;         
    private ListaEstados finales;   
    private TipoAutomata tipo;
    private String regex;           
    private ArrayList<String> alpha;    
    private String empty = "€";
    private int level = 0;

    /**
     *
     */
    public Automata() {
        this.estados = new ListaEstados();
        this.finales = new ListaEstados();
    }
    
    /**
     *
     * @param simbolo
     */
    public Automata(String simbolo) {
        this.estados = new ListaEstados();

        Estado e1 = new Estado(0, true, false, false);
        Estado e2 = new Estado(1, false, true, false);        
        Enlace enlace = new Enlace(e1, e2, simbolo);
        e1.addEnlace(enlace);

        this.estados.insertar(e1);
        this.estados.insertar(e2);
        
        this.inicial = e1;
        this.finales = new ListaEstados();
        this.finales.add(e2);
    }

    /**
     *
     * @param simbolo
     * @param tipo
     */
    public Automata(String simbolo, TipoAutomata tipo) {
        this(simbolo);
        this.tipo = tipo;
    }
    
    /**
     *
     * @param A2
     */
    public void thompson_or(Automata A2){
        
        Automata A1 = this;

        Estado final_A1 = A1.getFinales().getEstado(0);
        Estado final_A2 = A2.getFinales().getEstado(0);
        Estado inicial_A1 = A1.getInicial();
        Estado inicial_A2 = A2.getInicial();
        
        final_A1.setEstadofinal(false);
        final_A2.setEstadofinal(false);
        
        Estado estado_inicial = new Estado(0, true, false, false);
        Estado estado_final = new Estado(A1.estados.size()+A2.estados.size()+1, false, true, false);

        A1.inicial.setEstadoinicial(false);
        A2.inicial.setEstadoinicial(false);
        
        A1.renumerar(1);
        A2.renumerar(A1.estados.size()+1);

        estado_inicial.addEnlace(new Enlace(estado_inicial, inicial_A1, this.empty));
        estado_inicial.addEnlace(new Enlace(estado_inicial, inicial_A2, this.empty));
        final_A1.addEnlace( new Enlace( final_A1, estado_final, this.empty) );
        final_A2.addEnlace( new Enlace( final_A2, estado_final, this.empty) );

        Iterator it = A2.estados.getIterator();
        while(it.hasNext()){
            A1.estados.insertar((Estado)it.next());
        }
        A1.estados.insertar(estado_inicial);
        A1.estados.insertar(estado_final);
        
        A1.inicial=estado_inicial;
        A1.getFinales().set(0, estado_final);        
    }

    /**
     *
     * @param A2
     */
    public void thompson_concat(Automata A2){
        Automata A1 = this;

        Estado final_A1   = A1.getFinales().getEstado(0);
        Estado inicial_A2 = A2.getInicial();
        
        inicial_A2.setEstadoinicial(false);
        final_A1.setEstadofinal(false);
        
        int a1_estado_final = A1.estados.size() - 1;
        A2.renumerar(a1_estado_final);
                        
        Iterator <Enlace> enlaces_a2_inicio = inicial_A2.getEnlaces().getIterator();        
        
        while(enlaces_a2_inicio.hasNext()){            
            Enlace current = enlaces_a2_inicio.next();            
            current.setOrigen(final_A1);
            final_A1.addEnlace(current);
        }
        
        Iterator <Estado> estados_a2 = A2.estados.getIterator();
        
        while(estados_a2.hasNext()){
            Estado est_a2 = estados_a2.next();
            Iterator <Enlace> enlaces = est_a2.getEnlaces().getIterator();        
            while(enlaces.hasNext()){
                Enlace current = enlaces.next();
                Estado current_destino = current.getDestino();
                if (current_destino.getId() == inicial_A2.getId()) {
                    current.setDestino(final_A1);                    
                }
            }
            if(est_a2.getId() != inicial_A2.getId()){
                A1.estados.insertar(est_a2);
            }
        }
        A1.getFinales().set(0, A2.getFinales().getEstado(0));
    }

    /**
     *
     */
    public void thompson_common() {
      
        Automata A1 = this;
        A1.renumerar(1);
        Estado estado_inicial = new Estado(0, true, false, false);
        Estado estado_final   = new Estado(A1.estados.size()+1, false, true, false);
        Estado ex_estado_inicial = A1.getInicial();
        Estado ex_estado_final   = A1.getFinales().getEstado(0);
        ex_estado_inicial.setEstadoinicial(false);
        ex_estado_final.setEstadofinal(false);
        estado_inicial.addEnlace(new Enlace(estado_inicial, ex_estado_inicial, this.empty));   
        ex_estado_final.addEnlace(new Enlace(ex_estado_final, estado_final, this.empty));
        this.inicial = estado_inicial;
        this.finales.set(0, estado_final);
        A1.estados.insertar(estado_inicial);
        A1.estados.insertar(estado_final);   
    }            
    
    /**
     *
     */
    public void thompsonCeroUno() {
        this.thompson_common();
        this.inicial.addEnlace(new Enlace(this.inicial, this.finales.getEstado(0), this.empty));
    }
    
    /**
     *
     */
    public void thompsonCerraduraPositiva() {
        Estado inicio_original = this.inicial;
        Estado fin_original    = this.getFinales().getEstado(0);
        this.thompson_common();
        fin_original.addEnlace(new Enlace(fin_original, inicio_original, this.empty));
    }
    
    /**
     *
     */
    public void thompsonCerraduraKleene(){
        Estado inicio_original = this.inicial;
        Estado fin_original    = this.finales.get(0);
        this.thompson_common();
        fin_original.addEnlace(new Enlace(fin_original, inicio_original, this.empty));
        this.inicial.addEnlace(new Enlace(this.inicial, this.finales.getEstado(0), this.empty));
    }

    /**
     *
     * @param index
     * @return
     */
    public Estado getEstado(int index){
        return this.estados.getEstado(index);
    }
    
    /**
     *
     * @return
     */
    public ListaEstados getEstados() {
        return this.estados;
    }
    
    /**
     *
     * @param id
     * @return
     */
    public Estado getEstadoById(int id) {
        return this.estados.getEstadoById(id);
    }

    /**
     *
     * @return
     */
    public ListaEstados getFinales() {
        return finales;
    }
    
    /**
     *
     * @return
     */
    public ListaEstados getNoFinales(){
        ListaEstados lista = new ListaEstados();
        for(Estado x : estados){
            if(!x.isEstadofinal()){
                lista.insertar(x);
            }
        }
        return lista;
    }

    /**
     *
     * @return
     */
    public Estado getInicial() {
        return inicial;
    }

    /**
     *
     * @param ini
     */
    public void setInicial(Estado ini) {
        this.inicial = ini;
    }
    
    /**
     *
     * @return
     */
    public ArrayList<String> getAlpha() {
        return this.alpha;
    }

    /**
     *
     * @return
     */
    public int getAlphaUsed(){
        int alphaUsed = 0;
        ArrayList<String> alphaTemp = new ArrayList();
        String rex = this.regex;
        String c = "";

        for(int i = 0; i < rex.length(); i++){
            c += rex.charAt(i);

            if(!alphaTemp.contains(c)){
                if(this.alpha.contains(c)){
                    alphaTemp.add(c);
                    alphaUsed++;
                }
            }
            c = "";            
        }                
        return alphaUsed;
    }
    
    /**
     *
     * @return
     */
    public String getRegex() {
        return this.regex;
    }

    /**
     *
     * @param alpha
     */
    public void setAlfabeto(ArrayList<String> alpha) {
        this.alpha = alpha;
    }

    /**
     *
     * @param regex
     */
    public void setExpresion(String regex) {
        this.regex = regex;
    }

    /**
     *
     * @param incremento
     */
    public void renumerar(int incremento){
        Iterator it = this.estados.getIterator();
        while (it.hasNext()){
            Estado e = (Estado) it.next();
            e.setId(e.getId()+incremento);
        }

    }

    private void eliminarEstado(Estado e){
        
        for(Estado est: this.estados){
            for(Enlace enlace: est.getEnlaces()){
                if( e.getId() != est.getId() && enlace.getDestino().getId() == e.getId()){
                        est.eliminarEnlace(enlace);
                }
            }
        }
    }

    /**
     *
     */
    public void eliminar_estados_muertos(){
       for(Estado e : this.getEstados()){
           if(e.esEstadoMuerto()){
               eliminarEstado(e);
           }
       }
   }
    
    
    /**
     *
     * @return
     */
    public ListaEnlaces getEnlaces(){
        ListaEnlaces ret = new ListaEnlaces();
        for(Estado est: getEstados()){
            for(Enlace enlace: est.getEnlaces()){    
                ret.add(enlace);
            }
        }
        
        return ret;
    }

    /**
     *
     * @return
     */
    public TipoAutomata getTipo() {
        return tipo;
    }

    /**
     *
     * @param tipo
     */
    public void setTipo(TipoAutomata tipo) {
        this.tipo = tipo;
    }

    /**
     *
     * @param e
     */
    public void addEstado(Estado e){
        this.estados.insertar(e);
    }
  
    /**
     *
     * @return
     */
    public int getLevel() {
        return level;
    }

    /**
     *
     * @param level
     */
    public void setLevel(int level) {
        this.level = level;
    }
}
