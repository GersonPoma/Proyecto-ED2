/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package FormArboles;

import Arboles.AVL;
import Arboles.ArbolB;
import Arboles.ArbolBinarioBusqueda;
import Arboles.ArbolMViasBusqueda;
import Arboles.IArbolBusqueda;
import Excepciones.OrdenInvalidoExcepcion;
import com.mycompany.proyecto.arboles.TestArbol;
import com.sun.source.tree.SwitchTree;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author jairo
 */
public class FormularioArboles extends javax.swing.JFrame {

    /**
     * Creates new form FormularioArboles
     */
    
    IArbolBusqueda <String,String> arbolPrueba ;
    
//    ArbolBinarioBusqueda<Integer,String> arbolPrueba;
    public FormularioArboles(){   
        arbolPrueba = new AVL<>();
        arbolPrueba.insertar("jairo", "71685691");
        arbolPrueba.insertar("brayan", "72665897");
        arbolPrueba.insertar("chino", "76620902");       
        arbolPrueba.insertar("cinthia", "79917970");
        arbolPrueba.insertar("dakar", "72608216");
        arbolPrueba.insertar("erick", "78566525");
        arbolPrueba.insertar("jhoel", "76814386");
        arbolPrueba.insertar("laura", "71028922");
        arbolPrueba.insertar("noelia", "77336310");
        arbolPrueba.insertar("villarroel", "76303798");  
        initComponents() ;
        this.setLocationRelativeTo(null);   
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        RecorridoEnInOrden = new javax.swing.JButton();
        CrearArbol = new javax.swing.JButton();
        size = new javax.swing.JButton();
        buscar = new javax.swing.JButton();
        insertar = new javax.swing.JButton();
        eliminar = new javax.swing.JButton();
        vaciar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        RecorridoEnInOrden.setText("Mostrar Lista de Contactos");
        RecorridoEnInOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RecorridoEnInOrdenActionPerformed(evt);
            }
        });

        CrearArbol.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        CrearArbol.setText("Elegir Arbol");
        CrearArbol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CrearArbolActionPerformed(evt);
            }
        });

        size.setText("Cantidad de Contactos");
        size.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sizeActionPerformed(evt);
            }
        });

        buscar.setText("Buscar Contacto");
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });

        insertar.setText("Guardar Contacto");
        insertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertarActionPerformed(evt);
            }
        });

        eliminar.setText("Eliminar Contacto");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });

        vaciar.setText("Vaciar lista de contactos");
        vaciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vaciarActionPerformed(evt);
            }
        });
        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(eliminar)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(buscar)
                                    .addComponent(insertar))
                                .addGap(60, 60, 60)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(size)
                                    .addComponent(vaciar)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(201, 201, 201)
                        .addComponent(CrearArbol))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(RecorridoEnInOrden))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(148, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(CrearArbol)
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buscar)
                    .addComponent(vaciar))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(insertar)
                    .addComponent(size))
                .addGap(7, 7, 7)
                .addComponent(eliminar)
                .addGap(44, 44, 44)
                .addComponent(RecorridoEnInOrden)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CrearArbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrearArbolActionPerformed
        
        String tipoArbol = JOptionPane.showInputDialog(null,"  Elija el tipo de Arbol( ABB , AVL , AMB , AB ):  ");
        
        switch (tipoArbol) {
            case "ABB":
                arbolPrueba = new ArbolBinarioBusqueda<String, String>();
                JOptionPane.showMessageDialog(null, "Su Arbol fue creado");
                break;
            case "AVL":
                arbolPrueba = new AVL<>();
                JOptionPane.showMessageDialog(null, "Su Arbol fue creado");
                break; 
            case "AMB":
                try {
                    int orden =Integer.parseInt(JOptionPane.showInputDialog("ponga el orden que desea para el ArbolMvias"));
                    arbolPrueba = new ArbolMViasBusqueda<String, String>(orden);
                    JOptionPane.showMessageDialog(null, "Su Arbol fue creado");
                } catch (OrdenInvalidoExcepcion ex) {
                    //Logger.getLogger(FormularioArboles.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Orden del arbol debe ser almenos 3");
                }
                break; 
 
            case "AB":
                try {
                    int orden =Integer.parseInt(JOptionPane.showInputDialog("ponga el orden que desea para el arbolB"));
                    arbolPrueba = new ArbolB<String, String>(orden);
                    JOptionPane.showMessageDialog(null, "Su Arbol fue creado");
                } catch (OrdenInvalidoExcepcion ex) {
                    //Logger.getLogger(FormularioArboles.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Orden del arbol debe ser almenos 3");
                }
                break;     
  
            default:
                arbolPrueba = new AVL<>();
                JOptionPane.showMessageDialog(null, " Tipo de Arbol invalido. usando AVL ");
                break;
        }
        
        
        arbolPrueba.insertar("jairo", "71685691");
        arbolPrueba.insertar("brayan", "72665897");
        arbolPrueba.insertar("chino", "76620902");       
        arbolPrueba.insertar("cinthia", "79917970");
        arbolPrueba.insertar("dakar", "72608216");
        arbolPrueba.insertar("erick", "78566525");
        arbolPrueba.insertar("jhoel", "76814386");
        arbolPrueba.insertar("laura", "71028922");
        arbolPrueba.insertar("noelia", "77336310");
        arbolPrueba.insertar("villarroel", "76303798");      
    }//GEN-LAST:event_CrearArbolActionPerformed
    
    private void RecorridoEnInOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RecorridoEnInOrdenActionPerformed
        // TODO add your handling code here:
        if (arbolPrueba.esArbolVacio()) {
            String recorridoVacio = " ";
            jTextArea1.setText(recorridoVacio);
            return;
        }
        String recorrido ="LISTA DE CONTACTOS \n";
        for (int i = 0; i <arbolPrueba.recorridoEnInOrden().size(); i++) {
            recorrido =  recorrido + arbolPrueba.recorridoEnInOrden().get(i) + " \n"; 
        }
        jTextArea1.setText(recorrido);
        
    }//GEN-LAST:event_RecorridoEnInOrdenActionPerformed

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
        // TODO add your handling code here:
        String clave = JOptionPane.showInputDialog("Escriba el contacto que desea buscar");
        String valorAsociado = arbolPrueba.buscar(clave);
        if (valorAsociado==null) {
            JOptionPane.showMessageDialog(null,"El contacto " + clave + " que busca no se encuentra en la Lista de contactos", "Buscar contacto", JOptionPane.PLAIN_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null,"Nombre : "+ clave + " , Numero : " + valorAsociado, "Buscar contacto", JOptionPane.PLAIN_MESSAGE);
        }
        
    }//GEN-LAST:event_buscarActionPerformed

    private void insertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertarActionPerformed
        // TODO add your handling code here:
        String clave = (JOptionPane.showInputDialog("Escriba el nombre del contacto que desea guardar"));
        String valorAsociado = JOptionPane.showInputDialog("Escriba el numero del contacto que desea guardar");
        arbolPrueba.insertar(clave, valorAsociado);
    }//GEN-LAST:event_insertarActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        // TODO add your handling code here:
        String clave = (JOptionPane.showInputDialog("escriba el nombre del contacto que desea eliminar"));
        arbolPrueba.eliminar(clave);
        
        
    }//GEN-LAST:event_eliminarActionPerformed

    private void vaciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vaciarActionPerformed
        // TODO add your handling code here:
        arbolPrueba.vaciar();
        JOptionPane.showMessageDialog(null,"La Lista de Contactos quedo vacia", "Vaciar Lista de Contactos", JOptionPane.PLAIN_MESSAGE);
        
    }//GEN-LAST:event_vaciarActionPerformed

    private void sizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sizeActionPerformed
        // TODO add your handling code here:
        int size = arbolPrueba.recorridoEnInOrden().size();
        JOptionPane.showMessageDialog(null,"La cantidad de contactos es : "+ size, "Cantidad de contactos", JOptionPane.PLAIN_MESSAGE);
    }//GEN-LAST:event_sizeActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(FormularioArboles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormularioArboles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormularioArboles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormularioArboles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                    new FormularioArboles().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CrearArbol;
    private javax.swing.JButton RecorridoEnInOrden;
    private javax.swing.JButton buscar;
    private javax.swing.JButton eliminar;
    private javax.swing.JButton insertar;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton size;
    private javax.swing.JButton vaciar;
    // End of variables declaration//GEN-END:variables
}