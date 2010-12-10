package interfaz;

import generadortabla.TabladelAutomata;
import generadortabla.OneColumnRenderer;
import automatas.MinimizacionEstados;
import automatas.Subconjuntos;
import automatas.Automata;
import automatas.Dtrans;
import automatas.TipoAutomata;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import analizadorlexicosintactico.AnalizadorSintactico;

/**
 * Clase del Menu Principal
 *
 * @author Marco Alvarez
 * @author Sebastian Lena
 */
public class Main extends javax.swing.JFrame {

    private Automata afn;
    private Automata afd;
    private Automata afdMin;

    private Acerca acerca;
    private DibujarAutomata dibujar;
    private Validar validar;

    //abecedarios
    private String a_z = "abcdefghijklmnopqrstuvwxyz";
    private String A_Z = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String dig = "0123456789";

    /**
     * Constructor por Default
     */
    public Main() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Editor = new javax.swing.JPanel();
        Exp_jLabel = new javax.swing.JLabel();
        jTextFieldExpReg = new javax.swing.JTextField();
        ABC_jLabel = new javax.swing.JLabel();
        jTextFieldABC = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        Aceptar = new javax.swing.JButton();
        Matriz = new javax.swing.JPanel();
        Matriz_jTabbedPane1 = new javax.swing.JTabbedPane();
        AFN_jPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        AFN_jTable = new javax.swing.JTable();
        AFD_jPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        AFD_jTable = new javax.swing.JTable();
        AFDM_jPanel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        AFDM_jTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        Menu = new javax.swing.JMenuBar();
        Nuevo = new javax.swing.JMenu();
        Validar = new javax.swing.JMenu();
        Dibujar = new javax.swing.JMenu();
        Dibujar_AFN = new javax.swing.JMenuItem();
        Dibujar_AFD = new javax.swing.JMenuItem();
        Dibujar_AFDM = new javax.swing.JMenuItem();
        Ayuda = new javax.swing.JMenu();
        Salir = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Trabajo Práctico - Compiladores 2010");
        setBackground(new java.awt.Color(204, 204, 204));
        setForeground(java.awt.Color.black);
        setMaximizedBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setResizable(false);

        Editor.setBackground(javax.swing.UIManager.getDefaults().getColor("Menu.selectionBackground"));
        Editor.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Editor", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        Exp_jLabel.setText("Expresion Regular:");

        ABC_jLabel.setText("Alfabeto:");

        jTextFieldABC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldABCKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldABCKeyReleased(evt);
            }
        });

        jCheckBox1.setBackground(javax.swing.UIManager.getDefaults().getColor("Menu.selectionBackground"));
        jCheckBox1.setText("[a-z]");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jCheckBox2.setBackground(javax.swing.UIManager.getDefaults().getColor("Menu.selectionBackground"));
        jCheckBox2.setText("[A-Z]");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jCheckBox3.setBackground(javax.swing.UIManager.getDefaults().getColor("Menu.selectionBackground"));
        jCheckBox3.setText("[0-9]");
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        Aceptar.setText("Aceptar");
        Aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout EditorLayout = new javax.swing.GroupLayout(Editor);
        Editor.setLayout(EditorLayout);
        EditorLayout.setHorizontalGroup(
            EditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EditorLayout.createSequentialGroup()
                .addGroup(EditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(EditorLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(Exp_jLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldExpReg, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE))
                    .addGroup(EditorLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(ABC_jLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldABC, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE))
                    .addGroup(EditorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Aceptar)))
                .addContainerGap())
        );
        EditorLayout.setVerticalGroup(
            EditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EditorLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(EditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Exp_jLabel)
                    .addComponent(jTextFieldExpReg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(EditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldABC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ABC_jLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(Aceptar)
                    .addComponent(jCheckBox2)
                    .addComponent(jCheckBox3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Matriz.setBackground(javax.swing.UIManager.getDefaults().getColor("Menu.selectionBackground"));
        Matriz.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Matriz de Transición", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        AFN_jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        this.AFN_jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ESTADOS", "Alfabeto1", "Alfabeto2", "Alfabeto3"
            }
        ));
        jScrollPane1.setViewportView(AFN_jTable);

        javax.swing.GroupLayout AFN_jPanelLayout = new javax.swing.GroupLayout(AFN_jPanel);
        AFN_jPanel.setLayout(AFN_jPanelLayout);
        AFN_jPanelLayout.setHorizontalGroup(
            AFN_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
        );
        AFN_jPanelLayout.setVerticalGroup(
            AFN_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
        );

        Matriz_jTabbedPane1.addTab("AFN", AFN_jPanel);

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        AFD_jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        this.AFD_jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ESTADOS", "Alfabeto1", "Alfabeto2", "Alfabeto3"
            }
        ));
        jScrollPane3.setViewportView(AFD_jTable);

        javax.swing.GroupLayout AFD_jPanelLayout = new javax.swing.GroupLayout(AFD_jPanel);
        AFD_jPanel.setLayout(AFD_jPanelLayout);
        AFD_jPanelLayout.setHorizontalGroup(
            AFD_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
        );
        AFD_jPanelLayout.setVerticalGroup(
            AFD_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
        );

        Matriz_jTabbedPane1.addTab("AFD", AFD_jPanel);

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        AFDM_jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        this.AFDM_jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ESTADOS", "Alfabeto1", "Alfabeto2", "Alfabeto3"
            }
        ));
        jScrollPane4.setViewportView(AFDM_jTable);

        javax.swing.GroupLayout AFDM_jPanelLayout = new javax.swing.GroupLayout(AFDM_jPanel);
        AFDM_jPanel.setLayout(AFDM_jPanelLayout);
        AFDM_jPanelLayout.setHorizontalGroup(
            AFDM_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
        );
        AFDM_jPanelLayout.setVerticalGroup(
            AFDM_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
        );

        Matriz_jTabbedPane1.addTab("AFDmin", AFDM_jPanel);

        javax.swing.GroupLayout MatrizLayout = new javax.swing.GroupLayout(Matriz);
        Matriz.setLayout(MatrizLayout);
        MatrizLayout.setHorizontalGroup(
            MatrizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MatrizLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Matriz_jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                .addContainerGap())
        );
        MatrizLayout.setVerticalGroup(
            MatrizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MatrizLayout.createSequentialGroup()
                .addComponent(Matriz_jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setForeground(java.awt.SystemColor.textInactiveText);
        jLabel1.setText("Compiadores 2010");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        Nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/new_icon.png"))); // NOI18N
        Nuevo.setText("Nuevo");
        Nuevo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NuevoMouseClicked(evt);
            }
        });
        Nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NuevoActionPerformed(evt);
            }
        });
        Menu.add(Nuevo);

        Validar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/validar.png"))); // NOI18N
        Validar.setText("Validar");
        Validar.setEnabled(false);
        Validar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ValidarMouseClicked(evt);
            }
        });
        Validar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ValidarActionPerformed(evt);
            }
        });
        Menu.add(Validar);

        Dibujar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/front pencil.png"))); // NOI18N
        Dibujar.setText("Dibujar");
        Dibujar.setEnabled(false);

        Dibujar_AFN.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        Dibujar_AFN.setText("AFN");
        Dibujar_AFN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Dibujar_AFNActionPerformed(evt);
            }
        });
        Dibujar.add(Dibujar_AFN);

        Dibujar_AFD.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
        Dibujar_AFD.setText("AFD");
        Dibujar_AFD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Dibujar_AFDActionPerformed(evt);
            }
        });
        Dibujar.add(Dibujar_AFD);

        Dibujar_AFDM.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F7, 0));
        Dibujar_AFDM.setText("AFD Minimo");
        Dibujar_AFDM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Dibujar_AFDMActionPerformed(evt);
            }
        });
        Dibujar.add(Dibujar_AFDM);

        Menu.add(Dibujar);

        Ayuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/acerca.png"))); // NOI18N
        Ayuda.setText("Acerca de...");
        Ayuda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AyudaMouseClicked(evt);
            }
        });
        Ayuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AyudaActionPerformed(evt);
            }
        });
        Menu.add(Ayuda);

        Salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/salir.png"))); // NOI18N
        Salir.setText("Salir");
        Salir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SalirMouseClicked(evt);
            }
        });
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirActionPerformed(evt);
            }
        });
        Menu.add(Salir);

        setJMenuBar(Menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Matriz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Editor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Editor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Matriz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AceptarActionPerformed
        String expresion = jTextFieldExpReg.getText();
        String alfabeto = jTextFieldABC.getText();
        boolean error = false;

        // si no se inserto una expresion regular, mensaje de error
        if (expresion.compareTo("") == 0) {
            JOptionPane.showMessageDialog(this,
                "Debe insertar una Expresion Regular",
                "No válido", JOptionPane.ERROR_MESSAGE);
            jTextFieldExpReg.requestFocus();
            return;
            
        // si no se inserto un alfabeto, mensaje de error
        } else if (alfabeto.compareTo("") == 0) {
            JOptionPane.showMessageDialog(this,
                "Debe insertar el Alfabeto",
                "No válido", JOptionPane.ERROR_MESSAGE);
            jTextFieldABC.requestFocus();
            return;

        // genera los 3 automatas
        } else {
            // AFN
            AnalizadorSintactico analizador = new AnalizadorSintactico(expresion, alfabeto);
            error = analizador.isHayErrores();
            if (error) {
                JOptionPane.showMessageDialog(this,
                    analizador.getErrMsg(),
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                this.setAFN(analizador.traducir());
            }
            error = analizador.isHayErrores();
            if (error) {
                JOptionPane.showMessageDialog(this,
                    analizador.getErrMsg(),
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
                
            // si no hubo error al generar el AFN, se genera el AFD
            } else {
                Subconjuntos algSub;
                Dtrans dtran;
                try {
                    algSub = new Subconjuntos(this.afn);
                    dtran = algSub.ejecutar();
                    this.afd = dtran.convertAutomata();
                    this.afd = Subconjuntos.eliminar_estados_inalcanzables(this.afd);
                    this.afd.setAlfabeto(this.afn.getAlpha());
                    this.afd.setExpresion(expresion);
                    this.afd.setTipo(TipoAutomata.AFD);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this,
                        ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // si no hubo error al generar el AFD, se genera el AFD Minimo
                try {
                    MinimizacionEstados minimize = new MinimizacionEstados(this.afd);
                    this.afdMin = minimize.minimizar();
                    this.afdMin.eliminar_estados_muertos();
                    this.afdMin.setAlfabeto(this.afn.getAlpha());
                    this.afdMin.setExpresion(expresion);
                    this.afdMin.setTipo(TipoAutomata.AFDMin);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this,
                        ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
           }
        }

        // carga la tabla de transiciones de los automatas
        this.cargarTabla(AFN_jTable, this.afn);
        this.cargarTabla(AFD_jTable, this.afd);
        this.cargarTabla(AFDM_jTable, this.afdMin);

        // habilita las opciones de dibujar y validar
        this.unlock();

        // informa del exito del procesamiento
        JOptionPane.showMessageDialog(this,
            "Expresion Regular procesada con éxito",
            "Aceptado", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_AceptarActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        String temp = jTextFieldABC.getText();
        // agrega el alfabeto [abcdefghijklmnopqrstuvwxyz] y bloqueda la entrada por teclado
        if(jCheckBox1.isSelected()){
            temp = temp + a_z;
            jTextFieldABC.setText(temp);
            jTextFieldABC.setEditable(false);
        } else {
            int ind = temp.lastIndexOf(a_z);
            int end = a_z.length()-1 + ind;
            String nuevoTexto = new String("");
            if(ind!=0)
                nuevoTexto = nuevoTexto + temp.substring(0, ind);
            if(end+1 < temp.length())
                nuevoTexto = nuevoTexto + temp.substring(end+1, temp.length());
            jTextFieldABC.setText(nuevoTexto);
            if(jCheckBox2.isSelected()==false && jCheckBox3.isSelected()==false)
                jTextFieldABC.setEditable(true);
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        String temp = jTextFieldABC.getText();
         // agrega el alfabeto [ABCDEFGHIJKLMNOPQRSTUVWXYZ] y bloqueda la entrada por teclado
        if(jCheckBox2.isSelected()){
            temp = temp + A_Z;
            jTextFieldABC.setText(temp);
            jTextFieldABC.setEditable(false);
        } else {
            int ind = temp.lastIndexOf(A_Z);
            int end = A_Z.length()-1 + ind;
            String nuevoTexto = new String("");
            if(ind!=0)
                nuevoTexto = nuevoTexto + temp.substring(0, ind);
            if(end+1 < temp.length())
                nuevoTexto = nuevoTexto + temp.substring(end+1, temp.length());
            jTextFieldABC.setText(nuevoTexto);
            if(jCheckBox1.isSelected()==false && jCheckBox3.isSelected()==false)
                jTextFieldABC.setEditable(true);
        }
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        String temp = jTextFieldABC.getText();
        // agrega el alfabeto [0123456789] y bloqueda la entrada por teclado
        if(jCheckBox3.isSelected()){
            temp = temp + dig;
            jTextFieldABC.setText(temp);
            jTextFieldABC.setEditable(false);
        } else {
            int ind = temp.lastIndexOf(dig);
            int end = dig.length()-1 + ind;
            String nuevoTexto = new String("");
            if(ind!=0)
                nuevoTexto = nuevoTexto + temp.substring(0, ind);
            if(end+1 < temp.length())
                nuevoTexto = nuevoTexto + temp.substring(end+1, temp.length());
            jTextFieldABC.setText(nuevoTexto);
            if(jCheckBox1.isSelected()==false && jCheckBox2.isSelected()==false)
                jTextFieldABC.setEditable(true);
        }
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NuevoActionPerformed
        this.lock(); //resetea los campos
    }//GEN-LAST:event_NuevoActionPerformed

    private void ValidarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ValidarActionPerformed
        if(!Validar.isEnabled())
            return;
        if(this.validar == null) {
            this.validar = new Validar(jTextFieldExpReg.getText(),jTextFieldABC.getText(),this.afd);
        }
        this.validar.setVisible(true);
        this.validar.focoTXT();
    }//GEN-LAST:event_ValidarActionPerformed

    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_SalirActionPerformed

    private void AyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AyudaActionPerformed
        if (acerca == null) {
            acerca = new Acerca();
        }
        acerca.setVisible(true);
    }//GEN-LAST:event_AyudaActionPerformed

    private void Dibujar_AFDMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Dibujar_AFDMActionPerformed
        this.graficarAutomata(this.afdMin);
}//GEN-LAST:event_Dibujar_AFDMActionPerformed

    private void Dibujar_AFDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Dibujar_AFDActionPerformed
        this.graficarAutomata(this.afd);
}//GEN-LAST:event_Dibujar_AFDActionPerformed

    private void Dibujar_AFNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Dibujar_AFNActionPerformed
        this.graficarAutomata(this.afn);
}//GEN-LAST:event_Dibujar_AFNActionPerformed

    private void NuevoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NuevoMouseClicked
        this.NuevoActionPerformed(null);
    }//GEN-LAST:event_NuevoMouseClicked

    private void ValidarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ValidarMouseClicked
        ValidarActionPerformed(null);
    }//GEN-LAST:event_ValidarMouseClicked

    private void AyudaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AyudaMouseClicked
        AyudaActionPerformed(null);
    }//GEN-LAST:event_AyudaMouseClicked

    private void SalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SalirMouseClicked
        SalirActionPerformed(null);
    }//GEN-LAST:event_SalirMouseClicked

    private void jTextFieldABCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldABCKeyPressed
        //si se inserto alfabeto por el teclado no se permite tildar los jCheckBox
        // si se borro todo el contenido de jTextField se permite tildar de nuevo
        if(jTextFieldABC.getText().compareTo("")==0){
            jCheckBox1.setEnabled(true);
            jCheckBox2.setEnabled(true);
            jCheckBox3.setEnabled(true);
        } else {
            jCheckBox1.setEnabled(false);
            jCheckBox2.setEnabled(false);
            jCheckBox3.setEnabled(false);
        }
    }//GEN-LAST:event_jTextFieldABCKeyPressed

    private void jTextFieldABCKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldABCKeyReleased
       //si se inserto alfabeto por el teclado no se permite tildar los jCheckBox
       // si se borro todo el contenido de jTextField se permite tildar de nuevo
        if(jTextFieldABC.getText().compareTo("")==0){
            jCheckBox1.setEnabled(true);
            jCheckBox2.setEnabled(true);
            jCheckBox3.setEnabled(true);
        } else {
            jCheckBox1.setEnabled(false);
            jCheckBox2.setEnabled(false);
            jCheckBox3.setEnabled(false);
        }
    }//GEN-LAST:event_jTextFieldABCKeyReleased

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    /**
     * Carga los datos del automata a la tabla de transiciones
     *
     * @param Tabla Jtable para el automata
     * @param automata Automata (AFN, AFD o AFDMin)
     */
    public void cargarTabla(JTable Tabla, Automata automata) {
        TabladelAutomata tmodel = new TabladelAutomata(automata);
        tmodel.arreglarObjetosNulos();
        Tabla.setModel(tmodel);
        this.resetTablaRenderer(Tabla);
    }
    

    private void resetTablaRenderer(JTable Tabla) {
        Tabla.setBackground(Color.white);
        Tabla.setForeground(Color.black);

        DefaultTableCellRenderer dt = (DefaultTableCellRenderer) Tabla.getDefaultRenderer(String.class);
        dt.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        dt.setBackground(Color.white);
        dt.setForeground(Color.black);

        OneColumnRenderer cr = new OneColumnRenderer(0, Color.darkGray, Color.white);
        cr.setFont(new Font("Verdana",Font.BOLD, 12));
        Tabla.getColumnModel().getColumn(0).setCellRenderer(cr);
    }

    /**
     *
     * @return
     */
    public Automata getAFN() {
        return afn;
    }

    /**
     *
     * @param AFN
     */
    public void setAFN(Automata AFN) {
        this.afn = AFN;
    }

    /**
     *
     * @return
     */
    public Automata getAFD() {
        return afd;
    }

    /**
     *
     * @param AFD
     */
    public void setAFD(Automata AFD) {
        this.afd = AFD;
    }

    /**
     *
     * @return
     */
    public Automata getAFDMin() {
        return afdMin;
    }

    /**
     *
     * @param AFDMin
     */
    public void setAFDMin(Automata AFDMin) {
        this.afdMin = AFDMin;
    }

    private void lock(){
        Dibujar.setEnabled(false);
        Validar.setEnabled(false);
        jTextFieldExpReg.setText("");
        jTextFieldExpReg.setEditable(true);
        jTextFieldExpReg.setEnabled(true);
        jTextFieldABC.setText("");
        jTextFieldABC.setEditable(true);
        jTextFieldABC.setEnabled(true);
        Aceptar.setEnabled(true);
        jCheckBox1.setEnabled(true);
        jCheckBox2.setEnabled(true);
        jCheckBox3.setEnabled(true);
        jCheckBox1.setSelected(false);
        jCheckBox2.setSelected(false);
        jCheckBox3.setSelected(false);
        validar = null;
        
        this.AFN_jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ESTADOS", "Alfabeto1", "Alfabeto2", "Alfabeto3"
            }
        ));
        this.AFD_jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ESTADOS", "Alfabeto1", "Alfabeto2", "Alfabeto3"
            }
        ));

        this.AFDM_jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ESTADOS", "Alfabeto1", "Alfabeto2", "Alfabeto3"
            }
        ));
        
    }

    private void unlock(){
        Dibujar.setEnabled(true);
        Validar.setEnabled(true);
        jTextFieldExpReg.setEditable(false);
        jTextFieldABC.setEditable(false);
        Aceptar.setEnabled(false);
        jCheckBox1.setEnabled(false);
        jCheckBox2.setEnabled(false);
        jCheckBox3.setEnabled(false);
    }

    private void graficarAutomata(Automata automata) {
        if(this.dibujar==null){
            this.dibujar = new DibujarAutomata(automata);
            this.dibujar.setExpresion(jTextFieldExpReg.getText());
            this.dibujar.setVisible(true);
            this.dibujar.toFront();
        } else {
            this.dibujar = null;
            this.graficarAutomata(automata);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ABC_jLabel;
    private javax.swing.JPanel AFDM_jPanel;
    private javax.swing.JTable AFDM_jTable;
    private javax.swing.JPanel AFD_jPanel;
    private javax.swing.JTable AFD_jTable;
    private javax.swing.JPanel AFN_jPanel;
    private javax.swing.JTable AFN_jTable;
    private javax.swing.JButton Aceptar;
    private javax.swing.JMenu Ayuda;
    private javax.swing.JMenu Dibujar;
    private javax.swing.JMenuItem Dibujar_AFD;
    private javax.swing.JMenuItem Dibujar_AFDM;
    private javax.swing.JMenuItem Dibujar_AFN;
    private javax.swing.JPanel Editor;
    private javax.swing.JLabel Exp_jLabel;
    private javax.swing.JPanel Matriz;
    private javax.swing.JTabbedPane Matriz_jTabbedPane1;
    private javax.swing.JMenuBar Menu;
    private javax.swing.JMenu Nuevo;
    private javax.swing.JMenu Salir;
    private javax.swing.JMenu Validar;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTextFieldABC;
    private javax.swing.JTextField jTextFieldExpReg;
    // End of variables declaration//GEN-END:variables

}