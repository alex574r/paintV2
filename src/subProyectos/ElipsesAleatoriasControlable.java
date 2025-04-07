/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subProyectos;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class ElipsesAleatoriasControlable extends JPanel {
    private static final int ANCHO = 800;
    private static final int ALTO = 600;
    private static final int NUM_ELIPSES = 50; // Más elipses por defecto
    
    private Random random = new Random();
    private Ellipse2D.Double[] elipses;
    private Color[] colores;
    
    // Parámetros controlables
    private int transparencia = 180;
    private int minTamano = 10;
    private int maxTamano = 100;
    private int densidad = NUM_ELIPSES;
    
    // Componentes de la UI
    private JSlider sliderTransparencia;
    private JSlider sliderMinTamano;
    private JSlider sliderMaxTamano;
    private JSlider sliderDensidad;
    
    public ElipsesAleatoriasControlable() {
        setLayout(new BorderLayout());
        
        // Panel de control con sliders
        JPanel controlPanel = new JPanel(new GridLayout(4, 2));
        
        // Slider de transparencia
        sliderTransparencia = new JSlider(0, 255, transparencia);
        addSlider(controlPanel, "Transparencia:", sliderTransparencia);
        
        // Slider de tamaño mínimo
        sliderMinTamano = new JSlider(1, 50, minTamano);
        addSlider(controlPanel, "Tamaño mínimo:", sliderMinTamano);
        
        // Slider de tamaño máximo
        sliderMaxTamano = new JSlider(10, 200, maxTamano);
        addSlider(controlPanel, "Tamaño máximo:", sliderMaxTamano);
        
        // Slider de densidad (número de elipses)
        sliderDensidad = new JSlider(1, 200, densidad);
        addSlider(controlPanel, "Número de elipses:", sliderDensidad);
        
        add(controlPanel, BorderLayout.SOUTH);
        
        // Crear el panel de dibujo
        JPanel drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                                     RenderingHints.VALUE_ANTIALIAS_ON);
                
                for (int i = 0; i < elipses.length; i++) {
                    if (elipses[i] != null) {
                        g2d.setColor(colores[i]);
                        g2d.fill(elipses[i]);
                        g2d.setColor(Color.BLACK);
                        g2d.draw(elipses[i]);
                    }
                }
            }
        };
        
        drawingPanel.setPreferredSize(new Dimension(ANCHO, ALTO));
        drawingPanel.setBackground(Color.WHITE);
        add(drawingPanel, BorderLayout.CENTER);
        
        // Configurar listeners para los sliders
        ChangeListener sliderListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                transparencia = sliderTransparencia.getValue();
                minTamano = sliderMinTamano.getValue();
                maxTamano = sliderMaxTamano.getValue();
                densidad = sliderDensidad.getValue();
                
                // Asegurarnos que el mínimo no sea mayor que el máximo
                if (minTamano > maxTamano) {
                    minTamano = maxTamano;
                    sliderMinTamano.setValue(minTamano);
                }
                
                generarElipses();
                drawingPanel.repaint();
            }
        };
        
        sliderTransparencia.addChangeListener(sliderListener);
        sliderMinTamano.addChangeListener(sliderListener);
        sliderMaxTamano.addChangeListener(sliderListener);
        sliderDensidad.addChangeListener(sliderListener);
        
        // Generar las elipses iniciales
        generarElipses();
    }
    
    private void addSlider(JPanel panel, String label, JSlider slider) {
        panel.add(new JLabel(label));
        panel.add(slider);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(slider.getMaximum() / 4);
    }
    
    private void generarElipses() {
        elipses = new Ellipse2D.Double[densidad];
        colores = new Color[densidad];
        
        for (int i = 0; i < densidad; i++) {
            double x = random.nextDouble() * (ANCHO * 0.9);
            double y = random.nextDouble() * (ALTO * 0.9);
            double ancho = minTamano + random.nextDouble() * (maxTamano - minTamano);
            double alto = minTamano + random.nextDouble() * (maxTamano - minTamano);
            
            elipses[i] = new Ellipse2D.Double(x, y, ancho, alto);
            
            int r = random.nextInt(256);
            int g = random.nextInt(256);
            int b = random.nextInt(256);
            colores[i] = new Color(r, g, b, transparencia);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Elipses Aleatorias Controlables");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new ElipsesAleatoriasControlable());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}