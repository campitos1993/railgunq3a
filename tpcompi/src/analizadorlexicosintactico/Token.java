package analizadorlexicosintactico;

/**
 *
 * @author Administrator
 */
public class Token implements Comparable<Token> {
    private TipoToken tipo;
    private String valor;
    
    /**
     *
     * @param simbolo
     */
    public Token(String simbolo) {
        this.valor = simbolo;
        this.setTipo(simbolo);
    }

    /**
     *
     * @return
     */
    public TipoToken getTipo() {
        return tipo;
    }

    /**
     *
     * @return
     */
    public String getValor() {
        return valor;
    }

    /**
     *
     * @param tipo
     */
    public void setTipo(TipoToken tipo) {
        this.tipo = tipo;
    }

    /**
     *
     * @param valor
     */
    public void setValor(String valor) {
        this.valor = valor;
        this.setTipo(valor);
    }
    
    public int compareTo(Token t) {
        if (this.getTipo() == t.getTipo() 
                && this.getValor().compareTo(t.getValor()) == 0 ) {
            return 0;
        } else {
            return -1;
        }
    }

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
