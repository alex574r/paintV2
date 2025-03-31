package subProyectos;

import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class RandomEllipses extends JPanel {
    private WorkPanel workPanel;
    private ControlPanel controlPanel;
    private JSlider slider;
    private ChangeEventHandler changeEventHandler;
    
    private java.util.List<EllipseWithColor> ellipsesList = new ArrayList<>();
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SnippetFrame(new Ex3EllipsesSlider(), "Ellipses Slider"));
    }

    public RandomEllipses() {
        super(true);
        setLayout(new BorderLayout());

        changeEventHandler = new ChangeEventHandler();
        workPanel = new WorkPanel();
        controlPanel = new ControlPanel();

        add(workPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
    }

    private class ChangeEventHandler implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            int newValue = slider.getValue();
            if (newValue > ellipsesList.size()) {
                addRandomEllipse();
            } else if (newValue < ellipsesList.size()) {
                removeLastEllipse();
            }
            System.out.println("Valor del slider cambiado a: " + newValue);
            workPanel.repaint();
        }
    }

    private void addRandomEllipse() {
        Random rand = new Random();
        Color randomColor = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
        ellipsesList.add(new EllipseWithColor(randomColor));
    }

    private void removeLastEllipse() {
        if (!ellipsesList.isEmpty()) {
            ellipsesList.remove(ellipsesList.size() - 1);
        }
    }

    class WorkPanel extends JPanel {
        public WorkPanel() {
            setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.translate(getWidth() / 2, getHeight() / 2); // Centrar el dibujo

            for (int i = 0; i < ellipsesList.size(); i++) {
                g2.setColor(ellipsesList.get(i).color); // Color del borde
                AffineTransform transform = AffineTransform.getRotateInstance(2 * Math.PI * i / ellipsesList.size());
                Shape transformedEllipse = transform.createTransformedShape(ellipsesList.get(i).ellipse);
                g2.draw(transformedEllipse); // Dibuja solo el contorno
            }
        }
    }

    class ControlPanel extends JPanel {
        public ControlPanel() {
            setBackground(Color.LIGHT_GRAY);
            setLayout(new FlowLayout());

            slider = new JSlider(JSlider.HORIZONTAL, 1, 50, 10);
            slider.setMajorTickSpacing(10);
            slider.setMinorTickSpacing(1);
            slider.setPaintTicks(true);
            slider.setPaintLabels(true);
            slider.addChangeListener(changeEventHandler);

            add(new JLabel("Número de elipses: "));
            add(slider);
        }
    }

    class EllipseWithColor {
        Shape ellipse;
        Color color;

        public EllipseWithColor(Color color) {
            this.ellipse = new Ellipse2D.Double(-50, -25, 100, 50);
            this.color = color;
        }
    }
}