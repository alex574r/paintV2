/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package paint;

import OpcionesEmergentes.mensajesE;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import recursos.funciones;

/**
 *
 * @author alex5
 */
public class paint extends javax.swing.JPanel {

    private CustomDrawPanel drawPanel;  // Declara el panel de dibujo personalizado
    //private JPanel floatingPanel; // Panel flotante
    funciones funcion = new funciones();
    mensajesE mensaje = new mensajesE();

    public paint() {

        initComponents();
        setupDrawingPanel();
        setupPanels();
    }

    private void setupDrawingPanel() {
        drawPanel = new CustomDrawPanel();
        drawPanel.setBackground(Color.WHITE);

        // Configuración para AbsoluteLayout en el JLayeredPane
        drawPanel.setBounds(0, 0, 1390, 700);

        // Añadir al JLayeredPane usando AbsoluteConstraints
        jLayeredPane1.setLayer(drawPanel, JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.add(drawPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1390, 700));
    }

    private void setupPanels() {
        funcion.remover_fondo_panel(floatingPanel);
        drawPanel.setSeleccionMultiple(true);
        ShapesOpciones.setEnabled(false);
        drawPanel.setRelleno(true);
        drawPanel.setContorno(true);

    }

    private void valores_Default() {
        drawPanel.setModoPincel(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popMenuSahpe = new javax.swing.JPopupMenu();
        CirculoShape = new javax.swing.JMenuItem();
        RecatanguloShape = new javax.swing.JMenuItem();
        ElipseShape = new javax.swing.JMenuItem();
        CurvaCShape = new javax.swing.JMenuItem();
        LineaSahpe = new javax.swing.JMenuItem();
        PentagonoShape = new javax.swing.JMenuItem();
        TrianguloShape = new javax.swing.JMenuItem();
        ArcoShape = new javax.swing.JMenuItem();
        OvaloShape = new javax.swing.JMenuItem();
        CurvaCuadraticaSahpe = new javax.swing.JMenuItem();
        popMenuShapeElec = new javax.swing.JPopupMenu();
        ResistenciaShape = new javax.swing.JMenuItem();
        TransformadorShape = new javax.swing.JMenuItem();
        DiodoShape = new javax.swing.JMenuItem();
        PilaShape = new javax.swing.JMenuItem();
        FuenteACShape = new javax.swing.JMenuItem();
        InterruptorShape = new javax.swing.JMenuItem();
        pulsadorShape = new javax.swing.JMenuItem();
        FusibleShape = new javax.swing.JMenuItem();
        TierraShape = new javax.swing.JMenuItem();
        MicrocontroladorShape = new javax.swing.JMenuItem();
        popMenuEslilo = new javax.swing.JPopupMenu();
        DiseñoLibreOp = new javax.swing.JMenuItem();
        SeleccionOp = new javax.swing.JMenuItem();
        popMenuConfiguracionSP = new javax.swing.JPopupMenu();
        RellenoOP = new javax.swing.JMenuItem();
        TrazoOP = new javax.swing.JMenuItem();
        popMenuOpcionSelecccion = new javax.swing.JPopupMenu();
        MoverOP = new javax.swing.JMenuItem();
        RotarOP = new javax.swing.JMenuItem();
        EscalarOp = new javax.swing.JMenuItem();
        FusionarOp = new javax.swing.JMenuItem();
        BorrarOp = new javax.swing.JMenuItem();
        CopiarOP = new javax.swing.JMenuItem();
        PegarOp = new javax.swing.JMenuItem();
        TraerEnfrenteOp = new javax.swing.JMenuItem();
        EnviarAtrasOP = new javax.swing.JMenuItem();
        EliminarOP = new javax.swing.JMenuItem();
        AgruparOp = new javax.swing.JMenuItem();
        DesagruparOP = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        floatingPanel = new javax.swing.JPanel();
        ShapesOpciones = new javax.swing.JLabel();
        ModoPincel = new javax.swing.JLabel();
        ConfiguracionShapes = new javax.swing.JLabel();
        ShapesSeleccionE = new javax.swing.JLabel();
        ShapesSeleccion = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        CirculoShape.setText("Círculo");
        CirculoShape.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CirculoShapeActionPerformed(evt);
            }
        });
        popMenuSahpe.add(CirculoShape);

        RecatanguloShape.setText("Rectangulo");
        RecatanguloShape.setActionCommand("");
        RecatanguloShape.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RecatanguloShapeActionPerformed(evt);
            }
        });
        popMenuSahpe.add(RecatanguloShape);

        ElipseShape.setText("Elipse");
        ElipseShape.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ElipseShapeActionPerformed(evt);
            }
        });
        popMenuSahpe.add(ElipseShape);

        CurvaCShape.setText("Curva");
        CurvaCShape.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CurvaCShapeActionPerformed(evt);
            }
        });
        popMenuSahpe.add(CurvaCShape);

        LineaSahpe.setText("Linea");
        LineaSahpe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LineaSahpeActionPerformed(evt);
            }
        });
        popMenuSahpe.add(LineaSahpe);

        PentagonoShape.setText("Pentagono");
        PentagonoShape.setToolTipText("");
        PentagonoShape.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PentagonoShapeActionPerformed(evt);
            }
        });
        popMenuSahpe.add(PentagonoShape);

        TrianguloShape.setText("Triangulo");
        TrianguloShape.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TrianguloShapeActionPerformed(evt);
            }
        });
        popMenuSahpe.add(TrianguloShape);

        ArcoShape.setText("Arco");
        ArcoShape.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ArcoShapeActionPerformed(evt);
            }
        });
        popMenuSahpe.add(ArcoShape);

        OvaloShape.setText("Ovalo");
        OvaloShape.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OvaloShapeActionPerformed(evt);
            }
        });
        popMenuSahpe.add(OvaloShape);

        CurvaCuadraticaSahpe.setText("Curvva Cuadratica");
        CurvaCuadraticaSahpe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CurvaCuadraticaSahpeActionPerformed(evt);
            }
        });
        popMenuSahpe.add(CurvaCuadraticaSahpe);

        ResistenciaShape.setText("Resistencia");
        ResistenciaShape.setToolTipText("");
        ResistenciaShape.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResistenciaShapeActionPerformed(evt);
            }
        });
        popMenuShapeElec.add(ResistenciaShape);

        TransformadorShape.setText("Transformador");
        TransformadorShape.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TransformadorShapeActionPerformed(evt);
            }
        });
        popMenuShapeElec.add(TransformadorShape);

        DiodoShape.setText("Diodo");
        DiodoShape.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DiodoShapeActionPerformed(evt);
            }
        });
        popMenuShapeElec.add(DiodoShape);

        PilaShape.setText("Pila");
        PilaShape.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PilaShapeActionPerformed(evt);
            }
        });
        popMenuShapeElec.add(PilaShape);

        FuenteACShape.setText("Fuente AC");
        FuenteACShape.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FuenteACShapeActionPerformed(evt);
            }
        });
        popMenuShapeElec.add(FuenteACShape);

        InterruptorShape.setText("Interruptor");
        InterruptorShape.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InterruptorShapeActionPerformed(evt);
            }
        });
        popMenuShapeElec.add(InterruptorShape);

        pulsadorShape.setText("Pulsador");
        pulsadorShape.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsadorShapeActionPerformed(evt);
            }
        });
        popMenuShapeElec.add(pulsadorShape);

        FusibleShape.setText("Fusible");
        FusibleShape.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FusibleShapeActionPerformed(evt);
            }
        });
        popMenuShapeElec.add(FusibleShape);

        TierraShape.setText("Tierra");
        TierraShape.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TierraShapeActionPerformed(evt);
            }
        });
        popMenuShapeElec.add(TierraShape);

        MicrocontroladorShape.setText("Microcontrolador");
        MicrocontroladorShape.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MicrocontroladorShapeActionPerformed(evt);
            }
        });
        popMenuShapeElec.add(MicrocontroladorShape);

        DiseñoLibreOp.setText("Diseño Libre");
        DiseñoLibreOp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DiseñoLibreOpActionPerformed(evt);
            }
        });
        popMenuEslilo.add(DiseñoLibreOp);

        SeleccionOp.setText("Seleccion Shapes");
        SeleccionOp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SeleccionOpActionPerformed(evt);
            }
        });
        popMenuEslilo.add(SeleccionOp);

        RellenoOP.setText("Relleno");
        RellenoOP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RellenoOPActionPerformed(evt);
            }
        });
        popMenuConfiguracionSP.add(RellenoOP);

        TrazoOP.setText("Trazo");
        popMenuConfiguracionSP.add(TrazoOP);

        MoverOP.setText("Mover");
        MoverOP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MoverOPActionPerformed(evt);
            }
        });
        popMenuOpcionSelecccion.add(MoverOP);

        RotarOP.setText("Rotar");
        RotarOP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RotarOPActionPerformed(evt);
            }
        });
        popMenuOpcionSelecccion.add(RotarOP);

        EscalarOp.setText("Escalar");
        EscalarOp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EscalarOpActionPerformed(evt);
            }
        });
        popMenuOpcionSelecccion.add(EscalarOp);

        FusionarOp.setText("Fusionar");
        FusionarOp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FusionarOpActionPerformed(evt);
            }
        });
        popMenuOpcionSelecccion.add(FusionarOp);

        BorrarOp.setText("Borrar");
        BorrarOp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BorrarOpActionPerformed(evt);
            }
        });
        popMenuOpcionSelecccion.add(BorrarOp);

        CopiarOP.setText("Copiar");
        CopiarOP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CopiarOPActionPerformed(evt);
            }
        });
        popMenuOpcionSelecccion.add(CopiarOP);

        PegarOp.setText("Pegar");
        PegarOp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PegarOpActionPerformed(evt);
            }
        });
        popMenuOpcionSelecccion.add(PegarOp);

        TraerEnfrenteOp.setText("Traer En Frente");
        TraerEnfrenteOp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TraerEnfrenteOpActionPerformed(evt);
            }
        });
        popMenuOpcionSelecccion.add(TraerEnfrenteOp);

        EnviarAtrasOP.setText("Enviar Atras");
        EnviarAtrasOP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnviarAtrasOPActionPerformed(evt);
            }
        });
        popMenuOpcionSelecccion.add(EnviarAtrasOP);

        EliminarOP.setText("Eliminar Objeto");
        EliminarOP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarOPActionPerformed(evt);
            }
        });
        popMenuOpcionSelecccion.add(EliminarOP);

        AgruparOp.setText("Agrupar");
        AgruparOp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgruparOpActionPerformed(evt);
            }
        });
        popMenuOpcionSelecccion.add(AgruparOp);

        DesagruparOP.setText("Desagrupar");
        DesagruparOP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DesagruparOPActionPerformed(evt);
            }
        });
        popMenuOpcionSelecccion.add(DesagruparOP);

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1390, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1390, 70));

        jLayeredPane1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 0, 290, 700));

        floatingPanel.setBackground(new java.awt.Color(255, 51, 51));
        floatingPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ShapesOpciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/paintApp/iconoOpcionesSeleccion.png"))); // NOI18N
        ShapesOpciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ShapesOpcionesMouseClicked(evt);
            }
        });
        floatingPanel.add(ShapesOpciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, 64, 32));

        ModoPincel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/paintApp/iconDibujo.png"))); // NOI18N
        ModoPincel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ModoPincelMouseClicked(evt);
            }
        });
        floatingPanel.add(ModoPincel, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, 64, 32));

        ConfiguracionShapes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/paintApp/inconConfiguracion.png"))); // NOI18N
        ConfiguracionShapes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ConfiguracionShapesMouseClicked(evt);
            }
        });
        floatingPanel.add(ConfiguracionShapes, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, 64, 32));

        ShapesSeleccionE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/paintApp/iconElectronics.png"))); // NOI18N
        ShapesSeleccionE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ShapesSeleccionEMouseClicked(evt);
            }
        });
        floatingPanel.add(ShapesSeleccionE, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 64, 32));

        ShapesSeleccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/paintApp/iconShapes.png"))); // NOI18N
        ShapesSeleccion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ShapesSeleccionMouseClicked(evt);
            }
        });
        floatingPanel.add(ShapesSeleccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 64, 32));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/paintApp/iconDiseñoLibre.png"))); // NOI18N
        jLabel2.setToolTipText("Diseño Libre");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        floatingPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 64, 32));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/paintApp/barraNavegacion.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        floatingPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 70));

        jLayeredPane1.setLayer(floatingPanel, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.add(floatingPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(395, 583, 600, 70));

        add(jLayeredPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 1390, 700));
    }// </editor-fold>//GEN-END:initComponents

    private void ShapesSeleccionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ShapesSeleccionMouseClicked
        valores_Default();
        popMenuSahpe.show(ShapesSeleccion, evt.getX(), evt.getY());
    }//GEN-LAST:event_ShapesSeleccionMouseClicked

    private void CirculoShapeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CirculoShapeActionPerformed
        drawPanel.setFigura(1);
    }//GEN-LAST:event_CirculoShapeActionPerformed

    private void ShapesSeleccionEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ShapesSeleccionEMouseClicked
        popMenuShapeElec.show(ShapesSeleccionE, evt.getX(), evt.getY());
        valores_Default();
    }//GEN-LAST:event_ShapesSeleccionEMouseClicked

    private void RecatanguloShapeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RecatanguloShapeActionPerformed
        drawPanel.setFigura(2);
    }//GEN-LAST:event_RecatanguloShapeActionPerformed

    private void ElipseShapeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ElipseShapeActionPerformed
        drawPanel.setFigura(3);
    }//GEN-LAST:event_ElipseShapeActionPerformed

    private void CurvaCShapeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CurvaCShapeActionPerformed
        drawPanel.setFigura(8);
    }//GEN-LAST:event_CurvaCShapeActionPerformed

    private void LineaSahpeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LineaSahpeActionPerformed
        drawPanel.setFigura(4);
    }//GEN-LAST:event_LineaSahpeActionPerformed

    private void PentagonoShapeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PentagonoShapeActionPerformed
        drawPanel.setFigura(7);
    }//GEN-LAST:event_PentagonoShapeActionPerformed

    private void TrianguloShapeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TrianguloShapeActionPerformed
        drawPanel.setFigura(6);
    }//GEN-LAST:event_TrianguloShapeActionPerformed

    private void ArcoShapeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ArcoShapeActionPerformed
        drawPanel.setFigura(5);
    }//GEN-LAST:event_ArcoShapeActionPerformed

    private void OvaloShapeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OvaloShapeActionPerformed
        drawPanel.setFigura(10);
    }//GEN-LAST:event_OvaloShapeActionPerformed

    private void CurvaCuadraticaSahpeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CurvaCuadraticaSahpeActionPerformed
        drawPanel.setFigura(9);
    }//GEN-LAST:event_CurvaCuadraticaSahpeActionPerformed

    private void ResistenciaShapeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResistenciaShapeActionPerformed
        drawPanel.setFigura(21);
    }//GEN-LAST:event_ResistenciaShapeActionPerformed

    private void TransformadorShapeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TransformadorShapeActionPerformed
        drawPanel.setFigura(22);
    }//GEN-LAST:event_TransformadorShapeActionPerformed

    private void DiodoShapeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DiodoShapeActionPerformed
        drawPanel.setFigura(23);
    }//GEN-LAST:event_DiodoShapeActionPerformed

    private void PilaShapeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PilaShapeActionPerformed
        drawPanel.setFigura(24);
    }//GEN-LAST:event_PilaShapeActionPerformed

    private void FuenteACShapeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FuenteACShapeActionPerformed
        drawPanel.setFigura(25);
    }//GEN-LAST:event_FuenteACShapeActionPerformed

    private void InterruptorShapeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InterruptorShapeActionPerformed
        drawPanel.setFigura(26);
    }//GEN-LAST:event_InterruptorShapeActionPerformed

    private void pulsadorShapeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pulsadorShapeActionPerformed
        drawPanel.setFigura(27);
    }//GEN-LAST:event_pulsadorShapeActionPerformed

    private void FusibleShapeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FusibleShapeActionPerformed
        drawPanel.setFigura(28);
    }//GEN-LAST:event_FusibleShapeActionPerformed

    private void TierraShapeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TierraShapeActionPerformed
        drawPanel.setFigura(29);
    }//GEN-LAST:event_TierraShapeActionPerformed

    private void MicrocontroladorShapeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MicrocontroladorShapeActionPerformed
        drawPanel.setFigura(30);
    }//GEN-LAST:event_MicrocontroladorShapeActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        popMenuEslilo.show(jLabel2, evt.getX(), evt.getY());
        valores_Default();
    }//GEN-LAST:event_jLabel2MouseClicked


    private void DiseñoLibreOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DiseñoLibreOpActionPerformed
        boolean modoS = drawPanel.isModoSeleccion();
        if (modoS) {
            jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/paintApp/iconDiseñoLibre.png")));
            jLabel2.setToolTipText("Diseño Libre");
            drawPanel.setModoPincel(true);
            ModoPincel.setEnabled(true);
            ShapesOpciones.setEnabled(false);
            drawPanel.setModoSeleccion(false);
            drawPanel.setModoMover(false);
        }
    }//GEN-LAST:event_DiseñoLibreOpActionPerformed

    private void SeleccionOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SeleccionOpActionPerformed
        boolean modoS = drawPanel.isModoSeleccion();
        if (!modoS) {
            jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/paintApp/iconSelection.png")));
            jLabel2.setToolTipText("Seleccion de Shapes");
            ModoPincel.setEnabled(false);
            drawPanel.setModoSeleccion(true);
            ShapesOpciones.setEnabled(true);
        }
    }//GEN-LAST:event_SeleccionOpActionPerformed

    private void ConfiguracionShapesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ConfiguracionShapesMouseClicked
        popMenuConfiguracionSP.show(ConfiguracionShapes, evt.getX(), evt.getY());
    }//GEN-LAST:event_ConfiguracionShapesMouseClicked

    private void ModoPincelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ModoPincelMouseClicked
        boolean modoS = drawPanel.isModoSeleccion();
        if (modoS) {
            drawPanel.setModoPincel(false);
            ModoPincel.setEnabled(false);
        } else {
            drawPanel.setModoPincel(true);
            ModoPincel.setEnabled(true);
        }

    }//GEN-LAST:event_ModoPincelMouseClicked

    private void ShapesOpcionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ShapesOpcionesMouseClicked
        if (ShapesOpciones.isEnabled()) {
            popMenuOpcionSelecccion.show(ShapesOpciones, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_ShapesOpcionesMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        //popMenuConfiguracionSP.show(jLabel2, evt.getX(), evt.getY());
    }//GEN-LAST:event_jLabel1MouseClicked

    private void MoverOPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MoverOPActionPerformed
        drawPanel.setModoMover(true);
    }//GEN-LAST:event_MoverOPActionPerformed

    private void RotarOPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RotarOPActionPerformed
        drawPanel.setModoRotar(true);
    }//GEN-LAST:event_RotarOPActionPerformed

    private void EscalarOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EscalarOpActionPerformed
        drawPanel.setModoEscalar(true);
    }//GEN-LAST:event_EscalarOpActionPerformed

    private void FusionarOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FusionarOpActionPerformed
        drawPanel.fusionarFigurasSeleccionadas();
    }//GEN-LAST:event_FusionarOpActionPerformed

    private void BorrarOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BorrarOpActionPerformed
        drawPanel.setModoGoma(true);
    }//GEN-LAST:event_BorrarOpActionPerformed

    private void EliminarOPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarOPActionPerformed
        drawPanel.eliminarFigurasSeleccionadas();
    }//GEN-LAST:event_EliminarOPActionPerformed

    private void CopiarOPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CopiarOPActionPerformed
        drawPanel.copiarFigurasSeleccionadas();
    }//GEN-LAST:event_CopiarOPActionPerformed

    private void PegarOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PegarOpActionPerformed
        drawPanel.pegarFigurasCopiadas();
    }//GEN-LAST:event_PegarOpActionPerformed

    private void TraerEnfrenteOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TraerEnfrenteOpActionPerformed
        drawPanel.traerAlFrente();
    }//GEN-LAST:event_TraerEnfrenteOpActionPerformed

    private void EnviarAtrasOPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnviarAtrasOPActionPerformed
        drawPanel.enviarAtras();
    }//GEN-LAST:event_EnviarAtrasOPActionPerformed

    private void AgruparOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgruparOpActionPerformed
        drawPanel.agruparFiguras();
    }//GEN-LAST:event_AgruparOpActionPerformed

    private void DesagruparOPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DesagruparOPActionPerformed
        drawPanel.desagruparFiguras();
    }//GEN-LAST:event_DesagruparOPActionPerformed

    private void RellenoOPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RellenoOPActionPerformed
        mensaje.RellenoAlert();
    }//GEN-LAST:event_RellenoOPActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem AgruparOp;
    private javax.swing.JMenuItem ArcoShape;
    private javax.swing.JMenuItem BorrarOp;
    private javax.swing.JMenuItem CirculoShape;
    private javax.swing.JLabel ConfiguracionShapes;
    private javax.swing.JMenuItem CopiarOP;
    private javax.swing.JMenuItem CurvaCShape;
    private javax.swing.JMenuItem CurvaCuadraticaSahpe;
    private javax.swing.JMenuItem DesagruparOP;
    private javax.swing.JMenuItem DiodoShape;
    private javax.swing.JMenuItem DiseñoLibreOp;
    private javax.swing.JMenuItem EliminarOP;
    private javax.swing.JMenuItem ElipseShape;
    private javax.swing.JMenuItem EnviarAtrasOP;
    private javax.swing.JMenuItem EscalarOp;
    private javax.swing.JMenuItem FuenteACShape;
    private javax.swing.JMenuItem FusibleShape;
    private javax.swing.JMenuItem FusionarOp;
    private javax.swing.JMenuItem InterruptorShape;
    private javax.swing.JMenuItem LineaSahpe;
    private javax.swing.JMenuItem MicrocontroladorShape;
    private javax.swing.JLabel ModoPincel;
    private javax.swing.JMenuItem MoverOP;
    private javax.swing.JMenuItem OvaloShape;
    private javax.swing.JMenuItem PegarOp;
    private javax.swing.JMenuItem PentagonoShape;
    private javax.swing.JMenuItem PilaShape;
    private javax.swing.JMenuItem RecatanguloShape;
    private javax.swing.JMenuItem RellenoOP;
    private javax.swing.JMenuItem ResistenciaShape;
    private javax.swing.JMenuItem RotarOP;
    private javax.swing.JMenuItem SeleccionOp;
    private javax.swing.JLabel ShapesOpciones;
    private javax.swing.JLabel ShapesSeleccion;
    private javax.swing.JLabel ShapesSeleccionE;
    private javax.swing.JMenuItem TierraShape;
    private javax.swing.JMenuItem TraerEnfrenteOp;
    private javax.swing.JMenuItem TransformadorShape;
    private javax.swing.JMenuItem TrazoOP;
    private javax.swing.JMenuItem TrianguloShape;
    private javax.swing.JPanel floatingPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu popMenuConfiguracionSP;
    private javax.swing.JPopupMenu popMenuEslilo;
    private javax.swing.JPopupMenu popMenuOpcionSelecccion;
    private javax.swing.JPopupMenu popMenuSahpe;
    private javax.swing.JPopupMenu popMenuShapeElec;
    private javax.swing.JMenuItem pulsadorShape;
    // End of variables declaration//GEN-END:variables
}
