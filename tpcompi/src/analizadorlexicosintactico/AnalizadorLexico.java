package analizadorlexicosintactico;

/**
 *
 * @author Administrator
 */
public class AnalizadorLexico {
    
    private StringBuffer expReg;
    
    private Alfabeto alfabeto;
    
    private String operadores;     
    
    /**
     *
     * @param expresion
     * @param alfabeto
     */
    public AnalizadorLexico(String expresion, String alfabeto) {
        this.expReg = new StringBuffer(expresion);
        this.alfabeto = new Alfabeto(alfabeto);
        this.operadores = "*+?|()";
    }

    /**
     *
     * @param expresion
     * @param alfabeto
     */
    public AnalizadorLexico(String expresion, Alfabeto alfabeto) {
        this.expReg = new StringBuffer(expresion);
        this.alfabeto = alfabeto;
        this.operadores = "*+?|()";
    }
    
    /**
     *
     * @return
     * @throws Exception
     */
    public Token siguienteToken() throws Exception {
        
        String letra = consumir();
        Token sgte;
        
        if (letra.equalsIgnoreCase(" ") || letra.equalsIgnoreCase("\t")) {
            sgte = siguienteToken();

        } else if (this.operadores.indexOf(letra) >= 0 || this.alfabeto.contiene(letra) || letra.length() == 0) {
            sgte = new Token(letra);

        } else {
            throw new Exception("Error con la Expresion");
        }

        return sgte;
    }
    
    private String consumir() {
        
        String consumido = "";
              
        if (this.expReg.length() > 0) {
            consumido = Character.toString( this.expReg.charAt(0) );
            this.expReg.deleteCharAt(0);
        }
        
        return consumido;
    }

    /**
     *
     * @return
     */
    public Alfabeto obtenerAlfabeto() {
        return alfabeto;
    }

    /**
     *
     * @return
     */
    public StringBuffer obtenerExpresion() {
        return expReg;
    }

    /**
     *
     * @return
     */
    public String obtenerOperadores() {
        return operadores;
    }
}
