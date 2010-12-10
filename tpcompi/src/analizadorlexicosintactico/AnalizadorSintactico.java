package analizadorlexicosintactico;

import automatas.*;

/**
 *
 * @author Administrator
 */
public class AnalizadorSintactico {

    private AnalizadorLexico lexico;
    private String expresionRegular;
    private Token actual;
    private Alfabeto alfabeto;
    private Automata automata;
    private boolean error = false;
    private String errMsg = "";

    /**
     *
     */
    public AnalizadorSintactico() {
    }

    /**
     *
     * @param expReg
     * @param alfabeto
     */
    public AnalizadorSintactico(String expReg, String alfabeto) {
        this.expresionRegular = expReg;
        this.alfabeto = new Alfabeto(alfabeto);        
        this.lexico = new AnalizadorLexico(expReg, alfabeto);
        try {
            this.actual = sgteCaracter();
        } catch (Exception ex) {
            this.error = true;
        }
        automata = new Automata();
        automata.setTipo(TipoAutomata.AFN);
    }

    private void Match(String simbolo) throws Exception {
        Token tok = new Token(simbolo); 
        if ( ObtenerActual().compareTo(tok) == 0 ) {
            this.setActual(this.sgteCaracter());
        } else {
            throw new Exception("No se pudo consumir la entrada");
        }
    }

    private Token sgteCaracter() throws Exception {
        Token sgte = null;
        sgte = this.lexico.siguienteToken();
        return sgte;
    }
    
    /**
     *
     * @return
     */
    public Automata traducir() {
        this.automata = this.So();
        if (!this.isHayErrores()) {
            if (actual.getTipo() != TipoToken.FIN) {
                this.error = true;
                this.errMsg = "Aun hay entrada por analizar";
            }
        }
        this.automata.setAlfabeto(this.alfabeto);
        this.automata.setExpresion(this.expresionRegular);
        return this.automata;
    }

    private Automata So() {
        Automata automataIzq = null;
        Automata automataDer;
        try {
            automataIzq = this.EXP1();
            automataDer = this.OR();
            if (automataDer != null) {
                automataIzq.thompson_or(automataDer);
            }
        } catch (Exception ex) {
            this.error = true;
            this.errMsg = "Error en la generacion del AFN";
        }
        return automataIzq;
    }

    private Automata OR() throws Exception {
        try {            
            Token or = new Token("|");            
            
            if (actual.compareTo(or) == 0) {
                this.Match("|");
                return So();
            } else {                 
                return null;
            }         
        } catch (Exception ex) {
            this.error = true;
            throw new Exception("Se esperaba el simbolo '|'");
        }
    }
    
    private Automata EXP1() throws Exception {
        Automata automataIzq = this.EXP2();
        Automata automataDer = this.preEXP1();
        
        if (automataDer != null) {
            automataIzq.thompson_concat(automataDer);
        }
        
        return automataIzq;
    }

    private Automata EXP2() throws Exception {
        
        Automata a = EXP3();

        if (a != null) {
            char op = operadores();

            switch (op) {
                case '*':
                    a.thompsonCerraduraKleene();
                    break;
                case '+':
                    a.thompsonCerraduraPositiva();
                    break;
                case '?':
                    a.thompsonCeroUno();
                    break;
                case '€':
                    break;
            }
        }
        return a;
    }

    private Automata preEXP1() throws Exception {
        
        String current = actual.getValor();
        Automata a = null;
       
        if ( (actual.getTipo() != TipoToken.FIN) &&
             (this.alfabeto.contiene(current) || current.compareTo("(")==0)
           ) {
            a = this.EXP1();
        }
        
        return a;
    }
    
    private Automata EXP3() throws Exception {
        
        Token parentesisAbierto = new Token("(");
        
        if(actual.compareTo(parentesisAbierto) == 0) {
            return this.parentesis();
        } else {
            return this.alfabeto();
        }
    }
    
    private char operadores() throws Exception {
        char operador = '€';
        
        if (actual.getValor().compareTo("") != 0) {
            operador = actual.getValor().charAt(0);

            switch (operador) {
                case '*':
                    this.Match("*");
                    break;
                case '+':
                    this.Match("+");
                    break;
                case '?':
                    this.Match("?");
                    break;
                default:
                    return '€';
            }
        }
        return operador;
    }
    
    private Automata parentesis() throws Exception {
        try {
            this.Match("(");
        } catch (Exception ex) {
            this.error = true;
            throw new Exception("No se pudo consumir '('");
        }
        
        Automata a = this.So();
        
        try {
            this.Match(")");
        } catch (Exception ex) {
            this.error = true;
            throw new Exception("No se pudo consumir ')'");
        }
        
        return a;
    }
    
    private Automata alfabeto() throws Exception {
        Automata automataSimple = null;
        try {
            if (actual.getTipo() != TipoToken.FIN) {
                automataSimple = new Automata(actual.getValor(),TipoAutomata.AFN);
                this.Match(actual.getValor());
            }
        } catch (Exception ex) {
            this.error = true;
            throw new Exception(ex.getMessage());
        }
        return automataSimple;
    }
    
    
    /**
     *
     * @return
     */
    public String obtenerExpresion() {
        return expresionRegular;
    }

    /**
     *
     * @param expReg
     */
    public void setExpresion(String expReg) {
        this.expresionRegular = expReg;
        this.lexico = new AnalizadorLexico(expReg, alfabeto);
        try {
            this.actual = sgteCaracter();
        } catch (Exception ex) {
            this.error = true;
        }
        automata = new Automata();
    }

    /**
     *
     * @return
     */
    public Token ObtenerActual() {
        return actual;
    }

    /**
     *
     * @param act
     */
    public void setActual(Token act) {
        this.actual = act;
    }

    /**
     *
     * @return
     */
    public Alfabeto ObtenerAlfabeto() {
        return alfabeto;
    }

    /**
     *
     * @param alfabeto
     */
    public void setAlfabeto(Alfabeto alfabeto) {
        this.alfabeto = alfabeto;
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
     * @param Aut
     */
    public void setAutomata(Automata Aut) {
        this.automata = Aut;
    }

    /**
     *
     * @return
     */
    public boolean isHayErrores() {
        return error;
    }

    /**
     *
     * @return
     */
    public String getErrMsg() {
        return errMsg;
    }
}
