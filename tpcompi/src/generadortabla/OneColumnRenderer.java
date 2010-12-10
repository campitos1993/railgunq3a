package generadortabla;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Clase encargada del coloreo de la 1era columna
 * 
 * @author Marco Alvarez
 * @author Sebastian Lena
 */
public class OneColumnRenderer extends DefaultTableCellRenderer {

    private int columna;
    private Color background;
    private Color foreground;

    /**
     * Constructor de la clase por Default
     */
    public OneColumnRenderer() {
        this.columna = 0;
        this.background = Color.darkGray;
        this.foreground = Color.black;

    }

    /**
     * Contructor con parametros
     * 
     * @param columna Columna a colorear
     * @param b Color del background
     * @param f Color del foreground
     */
    public OneColumnRenderer(int columna, Color b, Color f) {
        this.columna = columna;
        this.background = b;
        this.foreground = f;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
        setEnabled(table == null || table.isEnabled()); 

        if ((column == this.columna)) {
            setBackground(this.background);
            setForeground(this.foreground);
            setFont(new Font("Verdana", Font.BOLD, 12));
        } else {
            setBackground(Color.white);
            setForeground(Color.black);
        }
        

        setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        super.getTableCellRendererComponent(table, value, selected, focused, row, column);

        return this;
    }
}
