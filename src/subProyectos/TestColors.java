package subProyectos;

import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import javax.swing.event.*;

public class TestColors extends JPanel {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Colors");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear una instancia de Ex9TestColors y agregarla al JFrame
        TestColors panel = new TestColors();
        frame.getContentPane().add(panel);

        frame.pack();
        frame.setVisible(true);
    }

    private ColorPanel colorPanel;

    public TestColors() {
        // Configurar el layout y agregar componentes
        setLayout(new BorderLayout());

        colorPanel = new ColorPanel();
        add(colorPanel, BorderLayout.CENTER);

        JPanel sliderPanel = new JPanel();
        sliderPanel.setLayout(new GridLayout(1, 3, 30, 10));
        add(sliderPanel, BorderLayout.EAST);

        // Slider para el color rojo
        JSlider redSlider = new JSlider(JSlider.VERTICAL, 0, 255, 100);
        redSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent ev) {
                colorPanel.red = ((JSlider) (ev.getSource())).getValue();
                colorPanel.repaint();
            }
        });
        sliderPanel.add(redSlider);

        // Slider para el color verde
        JSlider greenSlider = new JSlider(JSlider.VERTICAL, 0, 255, 100);
        greenSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent ev) {
                colorPanel.green = ((JSlider) (ev.getSource())).getValue();
                colorPanel.repaint();
            }
        });
        sliderPanel.add(greenSlider);

        // Slider para el color azul
        JSlider blueSlider = new JSlider(JSlider.VERTICAL, 0, 255, 100);
        blueSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent ev) {
                colorPanel.blue = ((JSlider) (ev.getSource())).getValue();
                colorPanel.repaint();
            }
        });
        sliderPanel.add(blueSlider);
    }
}

class ColorPanel extends JPanel {
    int red = 100;
    int green = 100;
    int blue = 100;

    public ColorPanel() {
        setPreferredSize(new Dimension(500, 500));
        setBackground(Color.white);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Definir las formas de los círculos
        Shape rc = new Ellipse2D.Double(100, 113, 200, 200);
        Shape gc = new Ellipse2D.Double(50, 200, 200, 200);
        Shape bc = new Ellipse2D.Double(150, 200, 200, 200);

        // Crear áreas para las intersecciones
        Area ra = new Area(rc);
        Area ga = new Area(gc);
        Area ba = new Area(bc);

        Area rga = new Area(rc);
        rga.intersect(ga);

        Area gba = new Area(gc);
        gba.intersect(ba);

        Area bra = new Area(bc);
        bra.intersect(ra);

        Area rgba = new Area(rga);
        rgba.intersect(ba);

        // Restar las áreas superpuestas
        ra.subtract(rga);
        ra.subtract(bra);
        ga.subtract(rga);
        ga.subtract(gba);
        ba.subtract(bra);
        ba.subtract(gba);

        // Rellenar las regiones de color
        g2.setColor(new Color(red, 0, 0));
        g2.fill(ra);
        g2.setColor(new Color(0, green, 0));
        g2.fill(ga);
        g2.setColor(new Color(0, 0, blue));
        g2.fill(ba);
        g2.setColor(new Color(red, green, 0));
        g2.fill(rga);
        g2.setColor(new Color(0, green, blue));
        g2.fill(gba);
        g2.setColor(new Color(red, 0, blue));
        g2.fill(bra);
        g2.setColor(new Color(red, green, blue));
        g2.fill(rgba);

        // Dibujar los círculos
        g2.setColor(Color.black);
        g2.draw(rc);
        g2.draw(gc);
        g2.draw(bc);
    }
}