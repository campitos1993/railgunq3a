package analizadorlexicosintactico;

/**
 * Enumera los diferentes tipos de Token existentes
 * 
 * @author Marco Alvarez
 * @author Sebastian Lena
 */
public enum TipoToken {

    /**
     * 0 si es erroneo
     */
    NONE,
    /**
     * 1 para cerradura de Kleene
     */
    CERRADURAKLEENE,
    /**
     * 2 para cerradura positiva de Kleene
     */
    CERRADURAPOSITIVA,
    /**
     * 3 cero o una vez
     */
    CEROUNO,
    /**
     * 4 para operador '|'
     */
    OR,
    /**
     * 5 para el parentesis '('
     */
    PARENTESISABIERTO,
    /**
     * 6 para el parentesis ')'
     */
    PARENTESISCERRADO,
    /**
     * 7 pertenece al alfabeto
     */
    ALFABETO,
    /**
     * 8 fin de expresion
     */
    FIN
}
