package subProyectos;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import java.awt.geom.*;

/**
 * Clase que crea un JPanel que contiene diferentes formas como ShapeComponent.
 */


class ShapeComponent extends JComponent {
    private Shape shape;
    private Color color;

    public ShapeComponent(Shape shape, Color color) {
        this.shape = shape;
        this.color = color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.fill(shape);  // Fill the shape with the specified color
    }
}

public class ShapeClasses extends JPanel {

    public ShapeClasses() {
        // Configuración del layout para las formas
        setLayout(new GridLayout(3, 3, 20, 20)); // Aumentamos el espacio entre celdas
        Color paleBlue = new Color(174, 174, 232);

        // Línea
        Line2D line = new Line2D.Double(10., 10., 250., 250.); // Aumentamos el tamaño
        add(new ShapeComponent(line, paleBlue));

        // Rectángulo
        Rectangle rect = new Rectangle(10, 10, 250, 200); // Aumentamos el tamaño
        add(new ShapeComponent(rect, paleBlue));

        // Rectángulo redondeado
        RoundRectangle2D rect2 = new RoundRectangle2D.Double(10., 10., 250., 200., 60., 45.); // Aumentamos el tamaño
        JButton button = new JButton();
        button.add(new ShapeComponent(rect2, paleBlue));
        add(button);

        // Elipse
        Ellipse2D ellipse = new Ellipse2D.Double(10., 10., 250., 200.); // Aumentamos el tamaño
        button = new JButton();
        button.add(new ShapeComponent(ellipse, paleBlue));
        add(button);

        // Arco
        Arc2D arc = new Arc2D.Float(10.f, 10.f, 250.f, 200.f, 45.f, 105.f, Arc2D.PIE); // Aumentamos el tamaño
        add(new ShapeComponent(arc, paleBlue));

        // Curva cuadrática
        QuadCurve2D quad = new QuadCurve2D.Double(10., 10., 250., 80., 10., 250.); // Aumentamos el tamaño
        add(new ShapeComponent(quad, paleBlue));

        // Curva cúbica
        CubicCurve2D wave = new CubicCurve2D.Double(10., 30., 80., -60., 120., 250., 250., 30.); // Aumentamos el tamaño
        add(new ShapeComponent(wave, paleBlue));

        // Área
        Area cheese = new Area(rect);
        cheese.subtract(new Area(new Ellipse2D.Double(5., 15., 60., 60.))); // Aumentamos el tamaño
        cheese.subtract(new Area(new Ellipse2D.Double(30., 20., 60., 60.)));
        cheese.subtract(new Area(new Ellipse2D.Double(75., 35., 60., 60.)));
        add(new ShapeComponent(cheese, new Color(255, 236, 102)));

        // Camino general (Eiffel)
        GeneralPath eiffel = new GeneralPath();
        eiffel.moveTo(20.f, 0.f);
        eiffel.lineTo(60.f, 0.f);
        eiffel.quadTo(60.f, 120.f, 120.f, 240.f); // Aumentamos el tamaño
        eiffel.lineTo(100.f, 240.f);
        eiffel.curveTo(100.f, 180.f, 40.f, 180.f, 40.f, 240.f);
        eiffel.lineTo(0.f, 240.f);
        eiffel.quadTo(20.f, 120.f, 20.f, 0.f);
        add(new ShapeComponent(eiffel, paleBlue));

        // Polígono
        Polygon polygon = new Polygon();
        polygon.addPoint(100, 0);
        polygon.addPoint(200, 0);
        polygon.addPoint(300, 100);
        polygon.addPoint(300, 200);
        polygon.addPoint(200, 300);
        polygon.addPoint(100, 300);
        polygon.addPoint(0, 200);
        polygon.addPoint(0, 100);
        polygon.addPoint(100, 0);
        add(new ShapeComponent(polygon, paleBlue));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Shape types in Java2D");
        ShapeClasses panel = new ShapeClasses();
        frame.getContentPane().add(panel);

        // Set frame background to white
        frame.getContentPane().setBackground(Color.white);

        frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent evt){
                System.exit(0);
            }
        });

        frame.pack();
        frame.setSize(800, 800); // Aumentamos el tamaño de la ventana
        frame.setVisible(true);
    }
}