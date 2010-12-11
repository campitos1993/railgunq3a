package analizadorlexicosintactico;

import automatas.*;

/**
 * Clase que emula al Analizador Sintactico. En ésta se realiza la traduccion
 *
 * @author Marco Alvarez
 * @author Sebastian Lena
 */
public class AnalizadorSintactico {

    private AnalizadorLexico lexico;
    private String expresionRegular;
    private Alfabeto alfabeto;
    private Automata automata;
    private Token actual;
    private String errMsg = "";
    private boolean error = false;

    /**
     * Constructor vacio de la Clase
     */
    public AnalizadorSintactico() {
    }

    /**
     * Contructor de la Clase, toma el String de la expresion regular y el alfabeto,
     * genera el analizador lexico y genera el AFN.
     * @param expReg la Expresion Regular
     * @param alfabeto el conjunto de simbolos del alfabeto
     */
    public AnalizadorSintactico(String expReg, String alfabeto) {
        this.expresionRegular = expReg;
        this.alfabeto = new Alfabeto(alfabeto);        
        this.lexico = new AnalizadorLexico(expReg, alfabeto);
        try {
            this.actual = sgteCaracter();
        } catch (Exception ex) {
            this.errMsg = "La Expresion no coincide con el Alfabeto";
            this.error = true;
        }
        automata = new Automata();
        automata.setTipo(TipoAutomata.AFN);
    }

    /*
     * Matchea el simbolo obtenido con el esperado. Lanza una Excepcion en caso
     * de fallo
     */
    private void Match(String simbolo) throws Exception {
        Token tok = new Token(simbolo); 
        if ( ObtenerActual().compareTo(tok) == 0 ) {
            this.setActual(this.sgteCaracter());
        } else {
            this.errMsg = "No se pudo consumir la entrada";
            throw new Exception("No se pudo consumir la entrada");
           
        }
    }

    /*
     * Obtiene del Analizador Lexico el siguiente token a evaluar
     */
    private Token sgteCaracter() throws Exception {
        Token sgte = null;
        sgte = this.lexico.siguienteToken();
        return sgte;
    }
    
    /**
     * Metodo que se encarga del analisis sintactico de la expresion. Utiliza el
     * siguiente BNF para realizar la evaluacion:
     * So -> EXP1 OR
     * EXP1 -> EXP2 EXP1 | €
     * OR -> '|' EXP1 OR | €
     * EXP 2 -> EXP3 OPERACION
     * EXP3 -> PARENTESIS | alfabeto
     * OPERACION -> * | + | ? | €
     * PARENTESIS -> '(' So ')'
     *
     * @return El automata finito no determinista (AFN) de la expresion
     */
    public Automata traducir() {
        this.automata = this.So();
        if (!this.isHayErrores()) {
            if (actual.getTipo() != TipoToken.FIN) {
                this.error = true;
                this.errMsg = "Aun hay entrada por analizar";
                return null;
            }
        } else {
            this.error = true;
            this.errMsg = "La expresion contiene simbolos que no estan en el alfabeto";
            return null;
        }
        this.automata.setAlfabeto(this.alfabeto);
        this.automata.setExpresion(this.expresionRegular);
        return this.automata;
    }

    /*
     * Metodo correspondiente a la Produccion:
     * So -> EXP1 OR 
     */
    private Automata So() {
        Automata automataIzq = null;
        Automata automataDer;
        try {
            automataIzq = this.EXP1();
            automataDer = this.OR();
            if (automataDer != null) {
                automataIzq.thompson_or(automataDer); //automata equivalente a (r|s)
            }
        } catch (Exception ex) {
            this.error = true;
            this.errMsg = "Error en la generacion del AFN";
        }
        return automataIzq;
    }

    /*
     * Metodo correspondiente a la Produccion:
     * OR -> '|' EXP1 OR | €
     */
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

    /*
     * Metodo correspondiente a la Produccion:
     * EXP1 -> EXP2 EXP1 | €
     */
    private Automata EXP1() throws Exception {
        Automata automataIzq = this.EXP2();
        Automata automataDer = this.preEXP1();
        
        if (automataDer != null) {
            automataIzq.thompson_concat(automataDer); //automata equivalente a rs
        }
        
        return automataIzq;
    }

    private Automata EXP2() throws Exception {
        
        Automata a = EXP3();

        if (a != null) {
            char op = OPERACION();

            switch (op) {
                case '*':
                    a.thompsonCerraduraKleene(); //automata equivalente a r*
                    break;
                case '+':
                    a.thompsonCerraduraPositiva(); //automata equivalente a r+
                    break;
                case '?':
                    a.thompsonCeroUno(); //automata equivalente a r?
                    break;
                case '€':
                    break;
            }
        }
        return a;
    }

    /*
     * Si el valor actual es un simbolo del alfabeto o el caracter '('. Se llama
     * a EXP1 sino se retorna null
     */
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

    /*
     * Metodo correspondiente a la Produccion:
     * EXP3 -> PARENTESIS | alfabeto
     */
    private Automata EXP3() throws Exception {
        
        Token parentesisAbierto = new Token("(");
        
        if(actual.compareTo(parentesisAbierto) == 0) {
            return this.PARENTESIS();
        } else {
            return this.alfabeto();
        }
    }

    /*
     * Metodo correspondiente a la Produccion:
     * OPERACION -> * | + | ? | €
     */
    private char OPERACION() throws Exception {
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

    /*
     * Metodo correspondiente a la Produccion:
     * PARENTESIS -> '(' So ')'
     */
    private Automata PARENTESIS() throws Exception {
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

    /*
     * Crea el automata simple para cada entrada del simbolo del alfabeto
     */
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
     * Obtiene el Token evaluado
     * @return Token evaluado
     */
    public Token ObtenerActual() {
        return actual;
    }

    /**
     * Setea el Token a evaluar
     * @param act Token a setear
     */
    public void setActual(Token act) {
        this.actual = act;
    }

    /**
     * Retorna si hubo o no errores en el Analizador
     * @return true o false
     */
    public boolean isHayErrores() {
        return error;
    }

    /**
     * Obtiene el mensaje que se cargo al haber un error
     * @return El mensaje de error
     */
    public String getErrMsg() {
        return errMsg;
    }
}
