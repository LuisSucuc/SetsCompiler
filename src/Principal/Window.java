
package Principal;

import Analyzers.Lexical;
import Analyzers.Semantic;
import java.awt.Color;
import java.awt.Component;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;


public class Window extends javax.swing.JFrame {
    String ubicacionArchivo;

    public Window() {
        initComponents();
        analyzeButton.setEnabled(false);
        //centrar ventana
        this.setLocationRelativeTo(null);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        checkLexical = new javax.swing.JCheckBox();
        checkSintactic = new javax.swing.JCheckBox();
        checkSemantic = new javax.swing.JCheckBox();
        scrollPanel = new javax.swing.JScrollPane();
        responseTable = new javax.swing.JTable()
        {
            @Override

            public Component prepareRenderer (TableCellRenderer renderer, int rowIndex, int columnIndex){
                Component componenet = super.prepareRenderer(renderer, rowIndex, columnIndex);

                Object value = getModel().getValueAt(rowIndex,columnIndex);

                if(columnIndex == 1){

                    if(value.equals("Aceptado"))
                    {

                        componenet.setBackground(Color.GREEN);
                        componenet.setForeground(Color.BLACK);

                    }
                    else{

                        // if date  equal current date
                        componenet.setBackground(Color.RED);
                        componenet.setForeground(Color.WHITE);
                    }

                }

                else {

                    componenet.setBackground(Color.WHITE);
                    componenet.setForeground(Color.BLACK);
                }

                return componenet;
            }

        }
        ;
        selectButton = new javax.swing.JButton();
        pathLabel = new javax.swing.JTextField();
        analyzeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        checkLexical.setText("Léxico");
        checkLexical.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkLexicalActionPerformed(evt);
            }
        });

        checkSintactic.setText("Sintáctico");
        checkSintactic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkSintacticActionPerformed(evt);
            }
        });

        checkSemantic.setText("Semántico");
        checkSemantic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkSemanticActionPerformed(evt);
            }
        });

        responseTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Linea", "Respuesta"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollPanel.setViewportView(responseTable);

        selectButton.setText("Seleccionar");
        selectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectButtonActionPerformed(evt);
            }
        });

        pathLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pathLabelActionPerformed(evt);
            }
        });

        analyzeButton.setText("Analizar");
        analyzeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                analyzeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(pathLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(selectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(checkLexical)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkSintactic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkSemantic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(analyzeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scrollPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1245, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkLexical)
                    .addComponent(checkSintactic)
                    .addComponent(checkSemantic)
                    .addComponent(analyzeButton))
                .addGap(18, 18, 18)
                .addComponent(scrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pathLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectButton))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void checkLexicalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkLexicalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkLexicalActionPerformed

    private void checkSintacticActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkSintacticActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkSintacticActionPerformed

    private void checkSemanticActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkSemanticActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkSemanticActionPerformed

    private void pathLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pathLabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pathLabelActionPerformed

    private void selectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectButtonActionPerformed
       setFile();
    }//GEN-LAST:event_selectButtonActionPerformed

    private void analyzeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_analyzeButtonActionPerformed
        
        clearTable();
        Lexical lexical = new Lexical();
        Response response;
        try {
            lexical.Analizar(ubicacionArchivo);
            response = lexical.getResult();
            
            if (response.success) {
                checkLexical.setSelected(true);
                
                Semantic semantic = new Semantic();
                semantic.Analizar(ubicacionArchivo);
                response = semantic.getResult();
            }
            drawTable(response);
            
            
        } catch (IOException ex) {
            System.out.println("ERROR" + ex);
        }
    }//GEN-LAST:event_analyzeButtonActionPerformed
    
    public void clearTable(){
        DefaultTableModel model = (DefaultTableModel) responseTable.getModel();
        model.setRowCount(0);
    }
    
    public void setFile() {
        //Ventana para ubicación del archivo
        JFileChooser seleccionar_archivo = new JFileChooser();
        //Abre la ventana en al ubicación actual
        seleccionar_archivo.setCurrentDirectory(new java.io.File("."));
        //Se define el título de la ventana
        seleccionar_archivo.setDialogTitle("Buscar archivo");//seleccionar_archivo.setAcceptAllFileFilterUsed(true);

        //Si se seleccionó un archivo
        if (seleccionar_archivo.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            //Se obtiene la ubicación del archivo y se almacena en la variable correspondiente
            ubicacionArchivo = seleccionar_archivo.getSelectedFile().toString();
            //Se muestra al usuario en el label la ubicación y archivo seleccionado
            pathLabel.setText(ubicacionArchivo);
            analyzeButton.setEnabled(true);
        } else { //Si no se selecciona el archivo
            //Se limpia el label
            clear();
        }
    }
    
    public void drawTable(Response response){
        clearTable();
        DefaultTableModel model = (DefaultTableModel) responseTable.getModel();
        for (int i = 0; i < response.inputs.size(); i++) {
            model.addRow(new Object[]{response.inputs.get(i), response.responses.get(i) });
        }
        
        
    }
     
    public void clear(){
        //Cambia el texto en label de ubicacion archivo
        pathLabel.setText("");
        analyzeButton.setEnabled(false);
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Window().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton analyzeButton;
    private javax.swing.JCheckBox checkLexical;
    private javax.swing.JCheckBox checkSemantic;
    private javax.swing.JCheckBox checkSintactic;
    private javax.swing.JTextField pathLabel;
    private javax.swing.JTable responseTable;
    private javax.swing.JScrollPane scrollPanel;
    private javax.swing.JButton selectButton;
    // End of variables declaration//GEN-END:variables
}
