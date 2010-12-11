package automatas;
import java.util.*;

/**
 * Clase encargada de generar y construir el autómata para una expresión regular
 * 
 * @author Marco Alvarez
 * @author Sebastian Lena
 */
public class Automata {



    /**
     * la expresion regular
     */
    private String regex;
    /**
     * el alfabeto que se utiliza
     */
    private ArrayList<String> alpha;
    /**
     * para representar epsilon, vacio
     */
    private String empty = "€";
    /**
     * variable auxiliar utilizada para indicar el nivel
     */
    private int level = 0;
    /**
     * estados que formaran al automata en cuestion
     */
    private ListaEstados estados;
    /**
     * el estado inicial del automata
     */
    private Estado inicial;
    /**
     * los estados finales
     */
    private ListaEstados finales;
    /**
     * el id del tipo de automata que corresponde, AFN, AFD, AFDMin
     */
    private TipoAutomata tipo;

    /**
     * Constructor
     */
    public Automata() {
        this.estados = new ListaEstados();
        this.finales = new ListaEstados();
    }

    /**
     * Constructor simple.
     * Dos estados y un solo enlace a través del simbolo especificado.
     * @param simbolo expresion regular simple formada por un solo caracter
     */
    public Automata(String simbolo) {
        this.estados = new ListaEstados();

        Estado e1 = new Estado(0, true, false, false);
        Estado e2 = new Estado(1, false, true, false);
        Arco enlace = new Arco(e1, e2, simbolo);
        e1.addEnlace(enlace);

        this.estados.insertar(e1);
        this.estados.insertar(e2);

        this.inicial = e1;
        this.finales = new ListaEstados();
        this.finales.add(e2);
    }

    /**
     * Constructor para automatas simples, se le especifica el tipo de automata
     * @param simbolo Expresion regular simple
     * @param tipo Tipo de automata en construcción
     */
    public Automata(String simbolo, TipoAutomata tipo) {
        this(simbolo);
        this.tipo = tipo;
    }

    /**
     * Thompson para la operación "|"
     * @param A2 Automata a seguir
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

        estado_inicial.addEnlace(new Arco(estado_inicial, inicial_A1, this.empty));
        estado_inicial.addEnlace(new Arco(estado_inicial, inicial_A2, this.empty));
        final_A1.addEnlace( new Arco( final_A1, estado_final, this.empty) );
        final_A2.addEnlace( new Arco( final_A2, estado_final, this.empty) );

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
     * Thompson para la operación de concatenación
     * @param A2 Automata siguiente al actual
     */
    public void thompson_concat(Automata A2){
        Automata A1 = this;

        Estado final_A1   = A1.getFinales().getEstado(0);
        Estado inicial_A2 = A2.getInicial();

        inicial_A2.setEstadoinicial(false);
        final_A1.setEstadofinal(false);

        int a1_estado_final = A1.estados.size() - 1;
        A2.renumerar(a1_estado_final);

        Iterator <Arco> enlaces_a2_inicio = inicial_A2.getEnlaces().getIterator();

        while(enlaces_a2_inicio.hasNext()){
            Arco current = enlaces_a2_inicio.next();
            current.setOrigen(final_A1);
            final_A1.addEnlace(current);
        }

        Iterator <Estado> estados_a2 = A2.estados.getIterator();

        while(estados_a2.hasNext()){
            Estado est_a2 = estados_a2.next();
            Iterator <Arco> enlaces = est_a2.getEnlaces().getIterator();
            while(enlaces.hasNext()){
                Arco current = enlaces.next();
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
     * implementación de las operaciones de kleene (*), plus (+), cerouno(?)
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
        estado_inicial.addEnlace(new Arco(estado_inicial, ex_estado_inicial, this.empty));
        ex_estado_final.addEnlace(new Arco(ex_estado_final, estado_final, this.empty));
        this.inicial = estado_inicial;
        this.finales.set(0, estado_final);
        A1.estados.insertar(estado_inicial);
        A1.estados.insertar(estado_final);
    }

    /**
     * operación '?' sobre el automata actual.
     * se le agrega enlaces vacios al comienzo del mismo y al final, y un enlace
     * vacio entrel el estado de ini y el de fin para permitir el recorrido
     */
    public void thompsonCeroUno() {
        this.thompson_common();
        this.inicial.addEnlace(new Arco(this.inicial, this.finales.getEstado(0), this.empty));
    }

    /**
     *
     */
    public void thompsonCerraduraPositiva() {
        Estado inicio_original = this.inicial;
        Estado fin_original    = this.getFinales().getEstado(0);
        this.thompson_common();
        fin_original.addEnlace(new Arco(fin_original, inicio_original, this.empty));
    }

    /**
     *
     */
    public void thompsonCerraduraKleene(){
        Estado inicio_original = this.inicial;
        Estado fin_original    = this.finales.get(0);
        this.thompson_common();
        fin_original.addEnlace(new Arco(fin_original, inicio_original, this.empty));
        this.inicial.addEnlace(new Arco(this.inicial, this.finales.getEstado(0), this.empty));
    }

    /**
     * Obtener el estado el cual esta referenciado por index
     * @param index
     * @return el Estado dado por index
     */
    public Estado getEstado(int index){
        return this.estados.getEstado(index);
    }

    /**
     * obtener la lista de estados del automata
     * @return Lista de estados
     */
    public ListaEstados getEstados() {
        return this.estados;
    }

    /**
     * Obtener un estado identificado por el id
     * @param id
     * @return el Estado con id = "id"
     */
    public Estado getEstadoById(int id) {
        return this.estados.getEstadoById(id);
    }

    /**
     * Obtener lista de estados que son finales
     * @return Lista de estados marcados como finales
     */
    public ListaEstados getFinales() {
        return finales;
    }

    /**
     * Obtener lista de estados que no son finales
     * @return Lista de estados no finales
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
     * Obtiene el estado inicial (marcado como tal)
     * @return Estado inicial
     */
    public Estado getInicial() {
        return inicial;
    }

    /**
     * Marca un estado dado por ini como inicial
     * @param ini
     */
    public void setInicial(Estado ini) {
        this.inicial = ini;
    }

    /**
     * Se obtiene el abc
     * @return el abc (abecedario)
     */
    public ArrayList<String> getAlpha() {
        return this.alpha;
    }

    /**
     * Obtiene el abc que realmente se utiliza
     * @return abc realmente utilizado, se exclueyen aquellos caracteres que no
     * se utilizan
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
     * Obtiene la expresion regular
     * @return regex
     */
    public String getRegex() {
        return this.regex;
    }

    /**
     * Setea el abc a uno dado por alpha
     * @param alpha
     */
    public void setAlfabeto(ArrayList<String> alpha) {
        this.alpha = alpha;
    }

    /**
     * Setea la expresion regular a una dada por regex
     * @param regex
     */
    public void setExpresion(String regex) {
        this.regex = regex;
    }

    /**
     * Se renumera los ids de los estados de los autoamatas en un incremento dado
     * @param incremento
     */
    public void renumerar(int incremento){
        Iterator it = this.estados.getIterator();
        while (it.hasNext()){
            Estado e = (Estado) it.next();
            e.setId(e.getId()+incremento);
        }

    }
    /**
     * Elimina un estado dado, se realiza una busqueda
     * @param e el estado a buscar para eliminar
     */
    private void eliminarEstado(Estado e){

        for(Estado est: this.estados){
            for(Arco enlace: est.getEnlaces()){
                if( e.getId() != est.getId() && enlace.getDestino().getId() == e.getId()){
                        est.eliminarEnlace(enlace);
                }
            }
        }
    }

    /**
     * Elimina los estados que quedan sin enlaces
     */
    public void eliminar_estados_muertos(){
       for(Estado e : this.getEstados()){
           if(e.esEstadoMuerto()){
               eliminarEstado(e);
           }
       }
   }


    /**
     * Obtiene todos los enlaces de un automata
     * @return Lista de enlaces
     */
    public ListaArcos getEnlaces(){
        ListaArcos ret = new ListaArcos();
        for(Estado est: getEstados()){
            for(Arco enlace: est.getEnlaces()){
                ret.add(enlace);
            }
        }

        return ret;
    }

    /**
     * Obtiene el tipo de automata (AFN, AFD, AFDMin)
     * @return el tipo
     */
    public TipoAutomata getTipo() {
        return tipo;
    }

    /**
     * Setea el tipo de automata
     * @param tipo
     */
    public void setTipo(TipoAutomata tipo) {
        this.tipo = tipo;
    }

    /**
     * Adhiere un estado e
     * @param e
     */
    public void addEstado(Estado e){
        this.estados.insertar(e);
    }

    /**
     * Obtiene el nivel
     * @return
     */
    public int getLevel() {
        return level;
    }

    /**
     * Setea el nivel
     * @param level
     */
    public void setLevel(int level) {
        this.level = level;
    }
}