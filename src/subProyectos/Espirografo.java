/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subProyectos;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

public class Espirografo extends JPanel {
    private static final int ANCHO = 800;
    private static final int ALTO = 600;
    
    // Parámetros del espirografo
    private int R = 150;  // Radio del círculo fijo
    private int r = 60;   // Radio del círculo móvil
    private int d = 80;   // Distancia del punto de dibujo al centro del círculo móvil
    private double incrementoAngulo = 0.05;
    private int iteraciones = 2000;
    
    // Colores y estilo
    private Color colorTrazo = new Color(50, 100, 200);
    private float grosorTrazo = 1.5f;
    
    // Componentes UI
    private JSlider sliderR;
    private JSlider sliderr;
    private JSlider sliderd;
    private JSlider sliderIteraciones;
    private JSlider sliderGrosor;
    
    // Almacenamiento de puntos
    private List<Point> puntos = new ArrayList<>();
    
    public Espirografo() {
        setLayout(new BorderLayout());
        
        // Panel de controles
        JPanel controlPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        
        // Slider para R (radio fijo)
        sliderR = crearSlider("Radio fijo (R):", 50, 300, R, 50);
        controlPanel.add(new JLabel("Radio fijo (R):"));
        controlPanel.add(sliderR);
        
        // Slider para r (radio móvil)
        sliderr = crearSlider("Radio móvil (r):", 10, 200, r, 10);
        controlPanel.add(new JLabel("Radio móvil (r):"));
        controlPanel.add(sliderr);
        
        // Slider para d (distancia)
        sliderd = crearSlider("Distancia (d):", 10, 200, d, 10);
        controlPanel.add(new JLabel("Distancia (d):"));
        controlPanel.add(sliderd);
        
        // Slider para iteraciones
        sliderIteraciones = crearSlider("Iteraciones:", 100, 5000, iteraciones, 500);
        controlPanel.add(new JLabel("Iteraciones:"));
        controlPanel.add(sliderIteraciones);
        
        // Slider para grosor del trazo
        sliderGrosor = crearSlider("Grosor trazo:", 1, 10, (int)grosorTrazo, 1);
        controlPanel.add(new JLabel("Grosor trazo:"));
        controlPanel.add(sliderGrosor);
        
        // Botón para cambiar color
        JButton colorButton = new JButton("Cambiar Color");
        colorButton.addActionListener(e -> {
            colorTrazo = JColorChooser.showDialog(this, "Elige un color", colorTrazo);
            repaint();
        });
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(colorButton);
        
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(controlPanel, BorderLayout.CENTER);
        southPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(southPanel, BorderLayout.SOUTH);
        
        // Configurar listeners
        ChangeListener sliderListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                R = sliderR.getValue();
                r = sliderr.getValue();
                d = sliderd.getValue();
                iteraciones = sliderIteraciones.getValue();
                grosorTrazo = sliderGrosor.getValue();
                calcularTrayectoria();
                repaint();
            }
        };
        
        sliderR.addChangeListener(sliderListener);
        sliderr.addChangeListener(sliderListener);
        sliderd.addChangeListener(sliderListener);
        sliderIteraciones.addChangeListener(sliderListener);
        sliderGrosor.addChangeListener(sliderListener);
        
        // Calcular la trayectoria inicial
        calcularTrayectoria();
    }
    
    private JSlider crearSlider(String label, int min, int max, int value, int majorTick) {
        JSlider slider = new JSlider(min, max, value);
        slider.setMajorTickSpacing(majorTick);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        return slider;
    }
    
    private void calcularTrayectoria() {
        puntos.clear();
        
        double x, y;
        double theta = 0;
        double centroX = ANCHO / 2.0;
        double centroY = ALTO / 2.0;
        
        for (int i = 0; i < iteraciones; i++) {
            theta += incrementoAngulo;
            
            // Fórmulas del espirografo
            double k = (double) r / R;
            double l = (double) d / r;
            
            x = centroX + (R - r) * Math.cos(theta) + d * Math.cos(((R - r) / r) * theta);
            y = centroY + (R - r) * Math.sin(theta) - d * Math.sin(((R - r) / r) * theta);
            
            puntos.add(new Point((int)x, (int)y));
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        // Configurar calidad de renderizado
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        
        // Dibujar la trayectoria
        if (!puntos.isEmpty()) {
            Path2D path = new Path2D.Double();
            path.moveTo(puntos.get(0).x, puntos.get(0).y);
            
            for (Point p : puntos) {
                path.lineTo(p.x, p.y);
            }
            
            g2d.setColor(colorTrazo);
            g2d.setStroke(new BasicStroke(grosorTrazo));
            g2d.draw(path);
        }
        
        // Dibujar información
        g2d.setColor(Color.BLACK);
        g2d.drawString("Espirografo - R=" + R + ", r=" + r + ", d=" + d, 10, 20);
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(ANCHO, ALTO);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Espirografo Interactivo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new Espirografo());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}