/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import paint.CustomDrawPanel;
import paint.ShapeSelectorPanel;
import paint.creditos;
import paint.paint;
import paint.prueba;
import subProyectos.Ex3EllipsesSlider;
import subProyectos.Hello2D;
import subProyectos.Hello2DV2;
import paneles.PaintFlow;
import subProyectos.RandomEllipses;
import subProyectos.ShapeClasses;
import subProyectos.TestColors;

/**
 *
 * @author alex5
 */
public class proyectoPrincipal extends javax.swing.JFrame {

    JPanel hello2dP = new Hello2D();
    JPanel paintFlowP = new PaintFlow();
    JPanel hello2DV2P = new Hello2DV2();
    JPanel Ex3EllipsesSliderP = new Ex3EllipsesSlider();
    JPanel creditosP = new creditos();
    JPanel paintP = new paint();
    JPanel ShapeC = new ShapeClasses();
    JPanel randomElip = new RandomEllipses();
    JPanel PColores = new TestColors();
    CustomDrawPanel drawPanel = new CustomDrawPanel();
    ShapeSelectorPanel PanelPaint = new ShapeSelectorPanel(drawPanel);
    prueba prueba = new prueba();

    public proyectoPrincipal() {
        initComponents();
        valoresIniciales();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPrincipal = new javax.swing.JScrollPane();
        BarProyectos = new javax.swing.JMenuBar();
        MenuSelectorProyectos = new javax.swing.JMenu();
        HelloJava2D = new javax.swing.JMenuItem();
        Hello2D = new javax.swing.JMenuItem();
        paintFlow = new javax.swing.JMenuItem();
        Elipses = new javax.swing.JMenuItem();
        RandomElipse = new javax.swing.JMenuItem();
        ShapeClas = new javax.swing.JMenuItem();
        TestColor = new javax.swing.JMenuItem();
        Paint = new javax.swing.JMenuItem();
        paintPrueba = new javax.swing.JMenuItem();
        creditos = new javax.swing.JMenu();
        acercaDeOp = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(scrollPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1400, 780));

        MenuSelectorProyectos.setText("Proyectos");

        HelloJava2D.setText("Hello Java 2D");
        HelloJava2D.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HelloJava2DActionPerformed(evt);
            }
        });
        MenuSelectorProyectos.add(HelloJava2D);

        Hello2D.setText("Hello 2D");
        Hello2D.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Hello2DActionPerformed(evt);
            }
        });
        MenuSelectorProyectos.add(Hello2D);

        paintFlow.setText("Paint Flow");
        paintFlow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paintFlowActionPerformed(evt);
            }
        });
        MenuSelectorProyectos.add(paintFlow);

        Elipses.setText("Elipses");
        Elipses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ElipsesActionPerformed(evt);
            }
        });
        MenuSelectorProyectos.add(Elipses);

        RandomElipse.setText("Random Elipses");
        RandomElipse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RandomElipseActionPerformed(evt);
            }
        });
        MenuSelectorProyectos.add(RandomElipse);

        ShapeClas.setText("Shapes Class");
        ShapeClas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShapeClasActionPerformed(evt);
            }
        });
        MenuSelectorProyectos.add(ShapeClas);

        TestColor.setText("Prueba de Color");
        TestColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TestColorActionPerformed(evt);
            }
        });
        MenuSelectorProyectos.add(TestColor);

        Paint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/paintApp/painticon.png"))); // NOI18N
        Paint.setText("Paint");
        Paint.setToolTipText("");
        Paint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PaintActionPerformed(evt);
            }
        });
        MenuSelectorProyectos.add(Paint);

        paintPrueba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paintPruebaActionPerformed(evt);
            }
        });
        MenuSelectorProyectos.add(paintPrueba);

        BarProyectos.add(MenuSelectorProyectos);

        creditos.setText("acerca de");

        acercaDeOp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/paintApp/iconCredit.png"))); // NOI18N
        acercaDeOp.setText("Creditos ");
        acercaDeOp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acercaDeOpActionPerformed(evt);
            }
        });
        creditos.add(acercaDeOp);

        jMenuItem1.setText("Comandos");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        creditos.add(jMenuItem1);

        BarProyectos.add(creditos);

        setJMenuBar(BarProyectos);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void valoresIniciales() {
        //PanelPaint.add(drawPanel);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/paintApp/icon.png")));

    }


    private void HelloJava2DActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HelloJava2DActionPerformed
        setTitle("Hola 2d");
        scrollPrincipal.setViewportView(hello2dP);
        hello2dP.revalidate();
        hello2dP.repaint();
    }//GEN-LAST:event_HelloJava2DActionPerformed

    private void Hello2DActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Hello2DActionPerformed
        setTitle("Hola 2d");
        scrollPrincipal.setViewportView(hello2DV2P);
        hello2DV2P.revalidate();
        hello2DV2P.repaint();
    }//GEN-LAST:event_Hello2DActionPerformed

    private void ElipsesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ElipsesActionPerformed
        setTitle("Elipses");
        scrollPrincipal.setViewportView(Ex3EllipsesSliderP);
        Ex3EllipsesSliderP.revalidate();
        Ex3EllipsesSliderP.repaint();
    }//GEN-LAST:event_ElipsesActionPerformed

    private void paintFlowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paintFlowActionPerformed
        setTitle("paintFlow");
        scrollPrincipal.setViewportView(paintFlowP);
    }//GEN-LAST:event_paintFlowActionPerformed

    private void PaintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PaintActionPerformed
        setTitle("Paint V2");
        scrollPrincipal.setViewportView(paintP);
        paintP.revalidate();
        paintP.repaint();

        /*setTitle("Paint");
        scrollPrincipal.setViewportView(PanelPaint);
        PanelPaint.revalidate();
        PanelPaint.repaint();*/
    }//GEN-LAST:event_PaintActionPerformed

    private void paintPruebaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paintPruebaActionPerformed
        /*setTitle("Paint");
        scrollPrincipal.setViewportView(PanelPaint);
        PanelPaint.revalidate();
        PanelPaint.repaint();
        /*setTitle("Paint V2");
        scrollPrincipal.setViewportView(paintP);
        paintP.revalidate();
        paintP.repaint();*/
    }//GEN-LAST:event_paintPruebaActionPerformed

    private void acercaDeOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acercaDeOpActionPerformed
        setTitle("Acerca De");
        scrollPrincipal.setViewportView(creditosP);
        creditosP.revalidate();
        creditosP.repaint();
    }//GEN-LAST:event_acercaDeOpActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        JOptionPane.showMessageDialog(null, "Comandos de Figuras (CTRL + Número)\n"
                + "CTRL+1 - Círculo\n"
                + "CTRL+2 - Rectángulo\n"
                + "CTRL+3 - Elipse\n"
                + "CTRL+4 - Línea discontinua\n"
                + "CTRL+5 - Arco\n"
                + "CTRL+6 - Triángulo\n"
                + "CTRL+7 - Estrella\n"
                + "CTRL+8 - Curva cuadrática\n"
                + "CTRL+9 - Curva cúbica\n"
                + "CTRL+0 - Óvalo (Nota:  las figuras Electronicas no tienen atajo directo)\n"
                + "Herramientas de Dibujo y Selección\n"
                + "CTRL+S - Modo Selección\n"
                + "CTRL+M - Modo Mover\n"
                + "CTRL+R - Modo Rotar\n"
                + "CTRL+E - Modo Escalar\n"
                + "CTRL+P - Modo Pincel\n"
                + "CTRL+G - Modo Goma\n"
                + "Operaciones con Figuras\n"
                + "CTRL+C - Copiar figuras seleccionadas\n"
                + "CTRL+V - Pegar figuras copiadas\n"
                + "CTRL+D - Borrar figuras seleccionadas \n"
                + "CTRL+Z - Deshacer (borrar última figura)\n"
                + "CTRL+F - Traer al frente\n"
                + "CTRL+B - Enviar atrás\n"
                + "CTRL+J - Agrupar figuras\n"
                + "CTRL+U - Desagrupar figuras\n"
                + "CTRL+L - Fusionar figuras seleccionadas\n"
                + "Funciones Adicionales (No tienen atajos de teclado)\n"
                + "Zoom con Rueda del Ratón + CTRL - Acercar/Alejar\n"
                + "Clic Medio + Arrastrar - Mover el lienzo\n"
                + "Guardar Dibujo - Desde el menú o interfaz\n"
                + "Cargar Imagen como Relleno - Desde el menú o interfaz\n"
                + "Aplicar Texturas - Desde el menú o interfaz");
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void RandomElipseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RandomElipseActionPerformed
        setTitle("Elipses Aleatorias");
        scrollPrincipal.setViewportView(randomElip);
        randomElip.revalidate();
        randomElip.repaint();
    }//GEN-LAST:event_RandomElipseActionPerformed

    private void ShapeClasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShapeClasActionPerformed
        setTitle("Sahpes en Clase");
        scrollPrincipal.setViewportView(ShapeC);
        ShapeC.revalidate();
        ShapeC.repaint();
    }//GEN-LAST:event_ShapeClasActionPerformed

    private void TestColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TestColorActionPerformed
        setTitle("Colores RGB");
        scrollPrincipal.setViewportView(PColores);
        PColores.revalidate();
        PColores.repaint();
    }//GEN-LAST:event_TestColorActionPerformed

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
            java.util.logging.Logger.getLogger(proyectoPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(proyectoPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(proyectoPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(proyectoPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new proyectoPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar BarProyectos;
    private javax.swing.JMenuItem Elipses;
    private javax.swing.JMenuItem Hello2D;
    private javax.swing.JMenuItem HelloJava2D;
    private javax.swing.JMenu MenuSelectorProyectos;
    private javax.swing.JMenuItem Paint;
    private javax.swing.JMenuItem RandomElipse;
    private javax.swing.JMenuItem ShapeClas;
    private javax.swing.JMenuItem TestColor;
    private javax.swing.JMenuItem acercaDeOp;
    private javax.swing.JMenu creditos;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem paintFlow;
    private javax.swing.JMenuItem paintPrueba;
    private javax.swing.JScrollPane scrollPrincipal;
    // End of variables declaration//GEN-END:variables
}
