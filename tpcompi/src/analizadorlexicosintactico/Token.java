package analizadorlexicosintactico;

/**
 * Clase que implementa los Token del Analizador Sintactico/Lexico
 *
 * @author Marco Alvarez
 * @author Sebastian Lena
 */
public class Token implements Comparable<Token> {
    private TipoToken tipo;
    private String valor;
    
    /**
     * Constructor de la clase
     * @param simbolo Valor del token
     */
    public Token(String simbolo) {
        this.valor = simbolo;
        this.setTipo(simbolo);
    }

    /**
     * Obtiene el tipo del Token
     * @return TipoToken del Token
     */
    public TipoToken getTipo() {
        return tipo;
    }

    /**
     * Obtiene el valor del Token
     * @return Valor del Token
     */
    public String getValor() {
        return valor;
    }

    /**
     * Setea el tipo del Token
     * @param tipo TipoToken que indica el tipo
     */
    public void setTipo(TipoToken tipo) {
        this.tipo = tipo;
    }

    /**
     * Setea el valor del Token y el tipo del Token
     * @param valor String con el simbolo
     */
    public void setValor(String valor) {
        this.valor = valor;
        this.setTipo(valor);
    }

    /*
     * Metodo para realizar la comparacion con otro Token.
     * @return 0 Se es igual, -1 Si es distinto
     */
    public int compareTo(Token t) {
        if (this.getTipo() == t.getTipo() 
                && this.getValor().compareTo(t.getValor()) == 0 ) {
            return 0;
        } else {
            return -1;
        }
    }

    /*
     * Setea el Tipo del Token dado el simbolo y almaneca el simbolo en el valor
     * si pertenece al alfabeto
     */
    private void setTipo(String simbolo) {
        
        if (simbolo.isEmpty()) {
            this.tipo = TipoToken.FIN;
        } else {

            switch (simbolo.charAt(0)) {
                case '*':
                    this.tipo = TipoToken.CERRADURAKLEENE;
                    break;
                case '+':
                    this.tipo = TipoToken.CERRADURAPOSITIVA;
                    break;
                case '?':
                    this.tipo = TipoToken.CEROUNO;
                    break;
                case '|':
                    this.tipo = TipoToken.OR;
                    break;
                case '(':
                    this.tipo = TipoToken.PARENTESISABIERTO;
                    break;
                case ')':
                    this.tipo = TipoToken.PARENTESISCERRADO;
                    break;
                default:
                    this.tipo = TipoToken.ALFABETO;
                    this.valor = simbolo;
                    break;
            }
        }
    }
}
