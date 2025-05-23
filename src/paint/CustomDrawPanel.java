package paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class CustomDrawPanel extends JPanel {

    // Listas de figuras
    private List<ShapeAtributos> figuras = new ArrayList<>();
    private List<ShapeAtributos> figurasSeleccionadas = new ArrayList<>();
    private List<ShapeAtributos> portapapeles = new ArrayList<>();
    private List<Point> puntosPincel = new ArrayList<>();

    // Estados de dibujo
    private Point inicioArrastre;
    private Shape figuraActual;
    private Shape figuraTemporal;
    private int figuraSeleccionada = 1;
    private Point2D inicioArrastreTransformed;

    // Modos de operación
    private boolean modoPincel = false;
    private boolean modoGoma = false;
    private boolean modoSeleccion = false;
    private boolean modoMover = false;
    private boolean modoRotar = false;
    private boolean modoEscalar = false;
    private boolean seleccionMultiple = false;
    private float tamanoPincel = 5.0f;

    // Transformaciones y zoom
    private double zoomFactor = 1.0;
    private Point zoomOrigin = new Point(0, 0);
    private JLabel zoomLabel;
    private AffineTransform transform = new AffineTransform();
    private boolean isDragging = false;
    private Point dragStart = new Point();
    private Point puntoReferencia;

    // Atributos de dibujo
    private AtributosDibujo atributosActuales = new AtributosDibujo();
    private BufferedImage[] texturas = new BufferedImage[4];

    // Iconos para herramientas
    private ImageIcon iconSelect;
    private ImageIcon iconMove;
    private ImageIcon iconRotate;
    private ImageIcon iconScale;
    private ImageIcon iconPencil;
    private ImageIcon iconEraser;
    private ImageIcon iconMerge;
    private ImageIcon iconDelete;
    private ImageIcon iconCopy;
    private ImageIcon iconPaste;
    private ImageIcon iconBringToFront;
    private ImageIcon iconSendToBack;
    private ImageIcon iconGroup;
    private ImageIcon iconUngroup;

    // Clase interna para atributos de dibujo
    public class AtributosDibujo {

        private Color colorRelleno = Color.WHITE;
        private Color colorContorno = Color.BLACK;
        private boolean rellenoActivo = true;
        private boolean contornoActivo = true;
        private float anchoContorno = 1.0f;
        private float[] patronPunteado = null;
        private boolean strokePunteado = false;
        private BufferedImage imagenRelleno = null;
        private boolean rellenoImagenActivo = false;
        private boolean rellenoDegradado = false;
        private Color colorDegradadoInicio = Color.WHITE;
        private Color colorDegradadoFin = Color.BLUE;
        private int direccionDegradado = 0;
        private boolean esGrupo = false;
        private int texturaIndex = -1;

        public boolean isEsGrupo() {
            return esGrupo;
        }

        public void setEsGrupo(boolean esGrupo) {
            this.esGrupo = esGrupo;
        }

        public AtributosDibujo copiar() {
            AtributosDibujo copia = new AtributosDibujo();
            copia.colorRelleno = this.colorRelleno;
            copia.colorContorno = this.colorContorno;
            copia.rellenoActivo = this.rellenoActivo;
            copia.contornoActivo = this.contornoActivo;
            copia.anchoContorno = this.anchoContorno;
            copia.patronPunteado = this.patronPunteado != null ? this.patronPunteado.clone() : null;
            copia.strokePunteado = this.strokePunteado;
            copia.imagenRelleno = this.imagenRelleno;
            copia.rellenoImagenActivo = this.rellenoImagenActivo;
            copia.rellenoDegradado = this.rellenoDegradado;
            copia.colorDegradadoInicio = this.colorDegradadoInicio;
            copia.colorDegradadoFin = this.colorDegradadoFin;
            copia.direccionDegradado = this.direccionDegradado;
            copia.esGrupo = this.esGrupo;
            copia.texturaIndex = this.texturaIndex;
            return copia;
        }

        public Stroke crearStroke() {
            if (strokePunteado && patronPunteado != null) {
                return new BasicStroke(anchoContorno, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, patronPunteado, 0.0f);
            } else {
                return new BasicStroke(anchoContorno);
            }
        }

        // Getters y setters
        public Color getColorRelleno() {
            return colorRelleno;
        }

        public void setColorRelleno(Color colorRelleno) {
            this.colorRelleno = colorRelleno;
        }

        public Color getColorContorno() {
            return colorContorno;
        }

        public void setColorContorno(Color colorContorno) {
            this.colorContorno = colorContorno;
        }

        public boolean isRellenoActivo() {
            return rellenoActivo;
        }

        public void setRellenoActivo(boolean rellenoActivo) {
            this.rellenoActivo = rellenoActivo;
        }

        public boolean isContornoActivo() {
            return contornoActivo;
        }

        public void setContornoActivo(boolean contornoActivo) {
            this.contornoActivo = contornoActivo;
        }

        public float getAnchoContorno() {
            return anchoContorno;
        }

        public void setAnchoContorno(float anchoContorno) {
            this.anchoContorno = anchoContorno;
        }

        public float[] getPatronPunteado() {
            return patronPunteado;
        }

        public void setPatronPunteado(float[] patronPunteado) {
            this.patronPunteado = patronPunteado;
        }

        public boolean isStrokePunteado() {
            return strokePunteado;
        }

        public void setStrokePunteado(boolean strokePunteado) {
            this.strokePunteado = strokePunteado;
        }

        public BufferedImage getImagenRelleno() {
            return imagenRelleno;
        }

        public void setImagenRelleno(BufferedImage imagenRelleno) {
            this.imagenRelleno = imagenRelleno;
        }

        public boolean isRelleno() {
            return rellenoActivo;
        }

        public boolean isRellenoImagenActivo() {
            return rellenoImagenActivo;
        }

        public void setRellenoImagenActivo(boolean rellenoImagenActivo) {
            this.rellenoImagenActivo = rellenoImagenActivo;
        }

        public boolean isRellenoDegradado() {
            return this.rellenoDegradado;
        }

        // Setter correspondiente
        public void setRellenoDegradado(boolean rellenoDegradado) {
            this.rellenoDegradado = rellenoDegradado;
        }

        // Getter correcto para color inicial
        public Color getColorDegradadoInicio() {
            return this.colorDegradadoInicio;
        }

        // Setter correspondiente
        public void setColorDegradadoInicio(Color color) {
            this.colorDegradadoInicio = color;
        }

        // Getter para color final
        public Color getColorDegradadoFin() {
            return this.colorDegradadoFin;
        }

        // Setter para color final
        public void setColorDegradadoFin(Color color) {
            this.colorDegradadoFin = color;
        }

        public int getDireccionDegradado() {
            return direccionDegradado;
        }

        public void setDireccionDegradado(int direccionDegradado) {
            this.direccionDegradado = direccionDegradado;
        }

        public int getTexturaIndex() {
            return texturaIndex;
        }

        public void setTexturaIndex(int texturaIndex) {
            this.texturaIndex = texturaIndex;
        }
    }

    // Clase interna para figuras con atributos
    private class ShapeAtributos {

        Shape forma;
        AtributosDibujo atributos;
        Stroke stroke;
        TexturePaint textura;

        public ShapeAtributos(Shape forma, AtributosDibujo atributos) {
            this.forma = forma;
            this.atributos = atributos.copiar();
            this.stroke = atributos.crearStroke();

            if (atributos.getTexturaIndex() >= 0 && atributos.getTexturaIndex() < texturas.length && texturas[atributos.getTexturaIndex()] != null) {
                this.textura = new TexturePaint(
                        texturas[atributos.getTexturaIndex()],
                        new Rectangle2D.Double(0, 0,
                                texturas[atributos.getTexturaIndex()].getWidth(),
                                texturas[atributos.getTexturaIndex()].getHeight())
                );
            } else if (atributos.isRellenoImagenActivo() && atributos.getImagenRelleno() != null) {
                this.textura = new TexturePaint(
                        atributos.getImagenRelleno(),
                        new Rectangle2D.Double(0, 0,
                                atributos.getImagenRelleno().getWidth(),
                                atributos.getImagenRelleno().getHeight())
                );
            } else {
                this.textura = null;
            }
        }
    }

    // Constructor
    public CustomDrawPanel() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE);

        // Configuración inicial
        cargarIconos();
        configurarAtajosTeclado();
        crearTexturas();

        // Configurar etiqueta de zoom
        zoomLabel = new JLabel("100%");
        zoomLabel.setFont(new Font("Arial", Font.BOLD, 14));
        zoomLabel.setForeground(Color.RED);
        zoomLabel.setVisible(false);
        add(zoomLabel);

        // Listeners de mouse
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isMiddleMouseButton(e)) {
                    isDragging = true;
                    dragStart = e.getPoint();
                    setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                    return;
                }

                inicioArrastre = e.getPoint();
                puntoReferencia = e.getPoint();

                if (modoPincel) {
                    puntosPincel.clear();
                    puntosPincel.add(e.getPoint());
                } else if (modoGoma) {
                    eliminarFiguraEnPunto(e.getPoint());
                } else if (modoSeleccion) {
                    manejarSeleccion(e);
                } else if (SwingUtilities.isLeftMouseButton(e) && !modoSeleccion && !modoMover && !modoRotar && !modoEscalar) {
                    try {
                        inicioArrastreTransformed = transformPoint(e.getPoint());
                        inicioArrastre = e.getPoint();
                        figuraTemporal = crearFigura(
                                (int) inicioArrastreTransformed.getX(),
                                (int) inicioArrastreTransformed.getY(),
                                1, 1);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isMiddleMouseButton(e)) {
                    isDragging = false;
                    setCursor(Cursor.getDefaultCursor());
                    return;
                }

                if (figuraTemporal != null && SwingUtilities.isLeftMouseButton(e) && !modoSeleccion && !modoMover && !modoRotar && !modoEscalar) {
                    AtributosDibujo atributosCopia = atributosActuales.copiar();
                    atributosCopia.setStrokePunteado(atributosActuales.isStrokePunteado());
                    atributosCopia.setPatronPunteado(atributosActuales.getPatronPunteado());
                    figuras.add(new ShapeAtributos(figuraTemporal, atributosCopia));
                    figuraTemporal = null;
                    repaint();
                } else if (figuraActual != null && !modoSeleccion && !modoMover && !modoRotar && !modoEscalar && !modoPincel && !modoGoma) {
                    figuras.add(new ShapeAtributos(figuraActual, atributosActuales));
                    figuraActual = null;
                    repaint();
                } else if (modoPincel && puntosPincel.size() > 1) {
                    Path2D path = new Path2D.Double();
                    path.moveTo(puntosPincel.get(0).x, puntosPincel.get(0).y);
                    for (int i = 1; i < puntosPincel.size(); i++) {
                        path.lineTo(puntosPincel.get(i).x, puntosPincel.get(i).y);
                    }
                    figuras.add(new ShapeAtributos(path, atributosActuales));
                    puntosPincel.clear();
                    repaint();
                }
                puntoReferencia = null;
            }
        });

        // Listeners de movimiento del mouse
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isDragging) {
                    Point dragEnd = e.getPoint();
                    int dx = dragEnd.x - dragStart.x;
                    int dy = dragEnd.y - dragStart.y;
                    zoomOrigin.translate(dx, dy);
                    dragStart = dragEnd;
                    updateTransform();
                    repaint();
                } else if (modoMover && !figurasSeleccionadas.isEmpty()) {
                    moverFiguras(e);
                } else if (modoRotar && !figurasSeleccionadas.isEmpty()) {
                    rotarFiguras(e);
                } else if (modoEscalar && !figurasSeleccionadas.isEmpty()) {
                    escalarFiguras(e);
                } else if (modoPincel) {
                    puntosPincel.add(e.getPoint());
                    repaint();
                } else if (modoGoma) {
                    eliminarFiguraEnPunto(e.getPoint());
                } else if (SwingUtilities.isLeftMouseButton(e) && inicioArrastreTransformed != null && !modoSeleccion && !modoMover && !modoRotar && !modoEscalar) {
                    try {
                        Point2D currentTransformed = transformPoint(e.getPoint());
                        int width = (int) (currentTransformed.getX() - inicioArrastreTransformed.getX());
                        int height = (int) (currentTransformed.getY() - inicioArrastreTransformed.getY());

                        figuraTemporal = crearFigura(
                                (int) inicioArrastreTransformed.getX(),
                                (int) inicioArrastreTransformed.getY(),
                                width, height);
                        repaint();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else if (figuraActual != null && !modoSeleccion && !modoMover && !modoRotar && !modoEscalar) {
                    int width = e.getX() - inicioArrastre.x;
                    int height = e.getY() - inicioArrastre.y;
                    figuraActual = crearFigura(inicioArrastre.x, inicioArrastre.y, width, height);
                    repaint();
                }
            }
        });

        // Listener para zoom con rueda del mouse
        addMouseWheelListener(this::handleZoom);
    }

    // Métodos de zoom y transformación
    private void handleZoom(MouseWheelEvent e) {
        if ((e.getModifiersEx() & InputEvent.CTRL_DOWN_MASK) != 0) {
            double oldZoom = zoomFactor;
            double zoomAmount = e.getWheelRotation() < 0 ? 1.1 : 0.9;
            zoomFactor *= zoomAmount;
            zoomFactor = Math.max(0.1, Math.min(zoomFactor, 10.0));

            Point mousePos = e.getPoint();
            zoomOrigin.x = (int) (mousePos.x - (mousePos.x - zoomOrigin.x) * (zoomFactor / oldZoom));
            zoomOrigin.y = (int) (mousePos.y - (mousePos.y - zoomOrigin.y) * (zoomFactor / oldZoom));

            updateTransform();
            updateZoomLabel();
            repaint();
        }
    }

    public void resetZoom() {
        zoomFactor = 1.0;
        zoomOrigin = new Point(0, 0);
        updateTransform();
        updateZoomLabel();
        repaint();
    }

    private void updateTransform() {
        transform.setToIdentity();
        transform.translate(zoomOrigin.x, zoomOrigin.y);
        transform.scale(zoomFactor, zoomFactor);
        transform.translate(-zoomOrigin.x, -zoomOrigin.y);
    }

    private void updateZoomLabel() {
        int percent = (int) (zoomFactor * 100);
        zoomLabel.setText(percent + "%");
        zoomLabel.setLocation(10, 10);
        zoomLabel.setVisible(true);

        Timer timer = new Timer(2000, e -> zoomLabel.setVisible(false));
        timer.setRepeats(false);
        timer.start();
    }

    private Point2D transformPoint(Point p) {
        try {
            AffineTransform inverse = transform.createInverse();
            return inverse.transform(p, null);
        } catch (NoninvertibleTransformException e) {
            System.err.println("Error al invertir transformación: " + e.getMessage());
            return new Point2D.Double(p.x, p.y);
        }
    }

    // Métodos de carga de recursos
    private void cargarIconos() {
        try {
            iconSelect = new ImageIcon(ImageIO.read(getClass().getResource("/icons/select.png")));
            iconMove = new ImageIcon(ImageIO.read(getClass().getResource("/icons/move.png")));
            iconRotate = new ImageIcon(ImageIO.read(getClass().getResource("/icons/rotate.png")));
            iconScale = new ImageIcon(ImageIO.read(getClass().getResource("/icons/scale.png")));
            iconPencil = new ImageIcon(ImageIO.read(getClass().getResource("/icons/pencil.png")));
            iconEraser = new ImageIcon(ImageIO.read(getClass().getResource("/icons/eraser.png")));
            iconMerge = new ImageIcon(ImageIO.read(getClass().getResource("/icons/merge.png")));
            iconDelete = new ImageIcon(ImageIO.read(getClass().getResource("/icons/delete.png")));
            iconCopy = new ImageIcon(ImageIO.read(getClass().getResource("/icons/copy.png")));
            iconPaste = new ImageIcon(ImageIO.read(getClass().getResource("/icons/paste.png")));
            iconBringToFront = new ImageIcon(ImageIO.read(getClass().getResource("/icons/bring_to_front.png")));
            iconSendToBack = new ImageIcon(ImageIO.read(getClass().getResource("/icons/send_to_back.png")));
            iconGroup = new ImageIcon(ImageIO.read(getClass().getResource("/icons/group.png")));
            iconUngroup = new ImageIcon(ImageIO.read(getClass().getResource("/icons/ungroup.png")));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los iconos: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            iconSelect = (ImageIcon) UIManager.getIcon("FileView.directoryIcon");
            iconMove = (ImageIcon) UIManager.getIcon("FileView.directoryIcon");
            iconRotate = (ImageIcon) UIManager.getIcon("FileView.directoryIcon");
            iconScale = (ImageIcon) UIManager.getIcon("FileView.directoryIcon");
            iconPencil = (ImageIcon) UIManager.getIcon("FileView.directoryIcon");
            iconEraser = (ImageIcon) UIManager.getIcon("FileView.directoryIcon");
            iconMerge = (ImageIcon) UIManager.getIcon("FileView.directoryIcon");
            iconDelete = (ImageIcon) UIManager.getIcon("FileView.directoryIcon");
            iconCopy = (ImageIcon) UIManager.getIcon("FileView.directoryIcon");
            iconPaste = (ImageIcon) UIManager.getIcon("FileView.directoryIcon");
            iconBringToFront = (ImageIcon) UIManager.getIcon("FileView.directoryIcon");
            iconSendToBack = (ImageIcon) UIManager.getIcon("FileView.directoryIcon");
            iconGroup = (ImageIcon) UIManager.getIcon("FileView.directoryIcon");
            iconUngroup = (ImageIcon) UIManager.getIcon("FileView.directoryIcon");
        }
    }

    private void crearTexturas() {
        // Textura 1: Madera
        texturas[0] = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = texturas[0].createGraphics();
        g2d.setColor(new Color(139, 69, 19));
        g2d.fillRect(0, 0, 50, 50);
        g2d.setColor(new Color(160, 82, 45));
        for (int i = 0; i < 50; i += 5) {
            g2d.drawLine(i, 0, i, 50);
        }
        g2d.dispose();

        // Textura 2: Líneas diagonales
        texturas[1] = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
        g2d = texturas[1].createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, 50, 50);
        g2d.setColor(Color.BLACK);
        for (int i = -50; i < 50; i += 5) {
            g2d.drawLine(i, 0, i + 50, 50);
        }
        g2d.dispose();

        // Textura 3: Puntos
        texturas[2] = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
        g2d = texturas[2].createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, 50, 50);
        g2d.setColor(Color.BLUE);
        for (int y = 0; y < 50; y += 10) {
            for (int x = 0; x < 50; x += 10) {
                g2d.fillOval(x, y, 5, 5);
            }
        }
        g2d.dispose();

        // Textura 4: Cuadrícula
        texturas[3] = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
        g2d = texturas[3].createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, 50, 50);
        g2d.setColor(Color.GRAY);
        for (int i = 0; i <= 50; i += 10) {
            g2d.drawLine(i, 0, i, 50);
            g2d.drawLine(0, i, 50, i);
        }
        g2d.dispose();
    }

    // Configuración de atajos de teclado
    private void configurarAtajosTeclado() {
        InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();

        // Atajos para figuras (CTRL+1 a CTRL+9)
        for (int i = 1; i <= 9; i++) {
            final int figura = i;
            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_0 + i, InputEvent.CTRL_DOWN_MASK), "figura_" + i);
            actionMap.put("figura_" + i, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setFigura(figura);
                }
            });
        }

        // Atajos para herramientas
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK), "seleccion");
        actionMap.put("seleccion", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setModoSeleccion(true);
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_DOWN_MASK), "mover");
        actionMap.put("mover", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setModoMover(true);
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK), "rotar");
        actionMap.put("rotar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setModoRotar(true);
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK), "escalar");
        actionMap.put("escalar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setModoEscalar(true);
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK), "pincel");
        actionMap.put("pincel", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setModoPincel(true);
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK), "goma");
        actionMap.put("goma", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setModoGoma(true);
            }
        });

        // Atajos para operaciones
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK), "borrar");
        actionMap.put("borrar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarFigurasSeleccionadas();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK), "copiar");
        actionMap.put("copiar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                copiarFigurasSeleccionadas();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK), "pegar");
        actionMap.put("pegar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pegarFigurasCopiadas();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK), "deshacer");
        actionMap.put("deshacer", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrarUltimaFigura();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK), "frente");
        actionMap.put("frente", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                traerAlFrente();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_DOWN_MASK), "atras");
        actionMap.put("atras", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarAtras();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_J, InputEvent.CTRL_DOWN_MASK), "agrupar");
        actionMap.put("agrupar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agruparFiguras();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_DOWN_MASK), "desagrupar");
        actionMap.put("desagrupar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desagruparFiguras();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK), "fusionar");
        actionMap.put("fusionar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fusionarFigurasSeleccionadas();
            }
        });
    }

    // Métodos de operación con figuras
    private void manejarSeleccion(MouseEvent e) {
        boolean encontrada = false;
        ShapeAtributos figuraBajoCursor = null;

        for (int i = figuras.size() - 1; i >= 0; i--) {
            ShapeAtributos sa = figuras.get(i);
            if (sa.forma.contains(e.getPoint())) {
                figuraBajoCursor = sa;
                encontrada = true;
                break;
            }
        }

        if (encontrada) {
            if (!seleccionMultiple || !e.isShiftDown()) {
                figurasSeleccionadas.clear();
            }

            if (!figurasSeleccionadas.contains(figuraBajoCursor)) {
                figurasSeleccionadas.add(figuraBajoCursor);
            } else {
                figurasSeleccionadas.remove(figuraBajoCursor);
            }
        } else if (!e.isShiftDown()) {
            figurasSeleccionadas.clear();
        }
    }

    private void moverFiguras(MouseEvent e) {
        int dx = e.getX() - puntoReferencia.x;
        int dy = e.getY() - puntoReferencia.y;

        for (ShapeAtributos sa : figurasSeleccionadas) {
            AffineTransform at = AffineTransform.getTranslateInstance(dx, dy);
            sa.forma = at.createTransformedShape(sa.forma);
        }

        puntoReferencia = e.getPoint();
        repaint();
    }

    private void rotarFiguras(MouseEvent e) {
        Rectangle2D bounds = null;
        for (ShapeAtributos sa : figurasSeleccionadas) {
            if (bounds == null) {
                bounds = sa.forma.getBounds2D();
            } else {
                bounds = bounds.createUnion(sa.forma.getBounds2D());
            }
        }

        double centerX = bounds.getCenterX();
        double centerY = bounds.getCenterY();

        double angulo = Math.atan2(e.getY() - centerY, e.getX() - centerX)
                - Math.atan2(puntoReferencia.y - centerY, puntoReferencia.x - centerX);

        for (ShapeAtributos sa : figurasSeleccionadas) {
            AffineTransform at = AffineTransform.getRotateInstance(angulo, centerX, centerY);
            sa.forma = at.createTransformedShape(sa.forma);
        }

        puntoReferencia = e.getPoint();
        repaint();
    }

    private void escalarFiguras(MouseEvent e) {
        Rectangle2D bounds = null;
        for (ShapeAtributos sa : figurasSeleccionadas) {
            if (bounds == null) {
                bounds = sa.forma.getBounds2D();
            } else {
                bounds = bounds.createUnion(sa.forma.getBounds2D());
            }
        }

        double centerX = bounds.getCenterX();
        double centerY = bounds.getCenterY();

        double distanciaOriginal = Math.sqrt(
                Math.pow(puntoReferencia.x - centerX, 2)
                + Math.pow(puntoReferencia.y - centerY, 2));
        double distanciaActual = Math.sqrt(
                Math.pow(e.getX() - centerX, 2)
                + Math.pow(e.getY() - centerY, 2));

        double escala = distanciaActual / distanciaOriginal;

        for (ShapeAtributos sa : figurasSeleccionadas) {
            AffineTransform at = AffineTransform.getTranslateInstance(centerX, centerY);
            at.scale(escala, escala);
            at.translate(-centerX, -centerY);
            sa.forma = at.createTransformedShape(sa.forma);
        }

        puntoReferencia = e.getPoint();
        repaint();
    }

    private void eliminarFiguraEnPunto(Point p) {
        for (int i = figuras.size() - 1; i >= 0; i--) {
            ShapeAtributos sa = figuras.get(i);
            if (sa.forma.contains(p)) {
                figuras.remove(i);
                figurasSeleccionadas.remove(sa);
                repaint();
                break;
            }
        }
    }

    // Métodos de manipulación de figuras
    public void fusionarFigurasSeleccionadas() {
        if (figurasSeleccionadas.size() < 2) {
            return;
        }

        Area areaFusionada = new Area();
        AtributosDibujo atributosFusion = figurasSeleccionadas.get(0).atributos.copiar();

        for (ShapeAtributos sa : figurasSeleccionadas) {
            areaFusionada.add(new Area(sa.forma));
            figuras.remove(sa);
        }

        ShapeAtributos fusion = new ShapeAtributos(areaFusionada, atributosFusion);
        figuras.add(fusion);
        figurasSeleccionadas.clear();
        figurasSeleccionadas.add(fusion);
        repaint();
    }

    public void copiarFigurasSeleccionadas() {
        portapapeles.clear();
        for (ShapeAtributos sa : figurasSeleccionadas) {
            portapapeles.add(new ShapeAtributos(cloneShape(sa.forma), sa.atributos.copiar()));
        }
    }

    public void pegarFigurasCopiadas() {
        if (portapapeles.isEmpty()) {
            return;
        }

        figurasSeleccionadas.clear();
        for (ShapeAtributos sa : portapapeles) {
            AffineTransform at = AffineTransform.getTranslateInstance(10, 10);
            Shape formaDesplazada = at.createTransformedShape(sa.forma);

            ShapeAtributos nueva = new ShapeAtributos(formaDesplazada, sa.atributos.copiar());
            figuras.add(nueva);
            figurasSeleccionadas.add(nueva);
        }
        repaint();
    }

    public void traerAlFrente() {
        for (ShapeAtributos sa : figurasSeleccionadas) {
            figuras.remove(sa);
            figuras.add(sa);
        }
        repaint();
    }

    public void enviarAtras() {
        for (ShapeAtributos sa : figurasSeleccionadas) {
            figuras.remove(sa);
            figuras.add(0, sa);
        }
        repaint();
    }

    public void agruparFiguras() {
        if (figurasSeleccionadas.size() < 2) {
            return;
        }

        Rectangle2D bounds = null;
        for (ShapeAtributos sa : figurasSeleccionadas) {
            if (bounds == null) {
                bounds = sa.forma.getBounds2D();
            } else {
                bounds = bounds.createUnion(sa.forma.getBounds2D());
            }
        }

        Shape grupo = new Rectangle2D.Double(
                bounds.getX(), bounds.getY(),
                bounds.getWidth(), bounds.getHeight()
        );

        ShapeAtributos grupoAtributos = new ShapeAtributos(
                grupo,
                figurasSeleccionadas.get(0).atributos.copiar()
        );

        grupoAtributos.atributos.setEsGrupo(true);

        figuras.removeAll(figurasSeleccionadas);
        figuras.add(grupoAtributos);

        figurasSeleccionadas.clear();
        figurasSeleccionadas.add(grupoAtributos);

        repaint();
    }

    public void desagruparFiguras() {
        if (figurasSeleccionadas.isEmpty()) {
            return;
        }

        List<ShapeAtributos> paraDesagrupar = new ArrayList<>();
        List<ShapeAtributos> nuevasFiguras = new ArrayList<>();

        for (ShapeAtributos sa : figurasSeleccionadas) {
            if (sa.atributos.isEsGrupo()) {
                paraDesagrupar.add(sa);
            }
        }

        if (paraDesagrupar.isEmpty()) {
            return;
        }

        for (ShapeAtributos grupo : paraDesagrupar) {
            Rectangle2D bounds = grupo.forma.getBounds2D();

            ShapeAtributos nueva1 = new ShapeAtributos(
                    new Ellipse2D.Double(
                            bounds.getX(), bounds.getY(),
                            bounds.getWidth() / 2, bounds.getHeight() / 2
                    ),
                    grupo.atributos.copiar()
            );

            ShapeAtributos nueva2 = new ShapeAtributos(
                    new Rectangle2D.Double(
                            bounds.getX() + bounds.getWidth() / 2,
                            bounds.getY() + bounds.getHeight() / 2,
                            bounds.getWidth() / 2,
                            bounds.getHeight() / 2
                    ),
                    grupo.atributos.copiar()
            );

            nuevasFiguras.add(nueva1);
            nuevasFiguras.add(nueva2);

            figuras.remove(grupo);
        }

        figuras.addAll(nuevasFiguras);
        figurasSeleccionadas.clear();
        figurasSeleccionadas.addAll(nuevasFiguras);
        repaint();
    }

    public void eliminarFigurasSeleccionadas() {
        if (figurasSeleccionadas.isEmpty()) {
            return;
        }

        figuras.removeAll(figurasSeleccionadas);
        figurasSeleccionadas.clear();
        repaint();
    }

    public void escalarFigurasSeleccionadas(double escala) {
        if (figurasSeleccionadas.isEmpty()) {
            return;
        }

        Rectangle2D bounds = null;
        for (ShapeAtributos sa : figurasSeleccionadas) {
            if (bounds == null) {
                bounds = sa.forma.getBounds2D();
            } else {
                bounds = bounds.createUnion(sa.forma.getBounds2D());
            }
        }

        double centerX = bounds.getCenterX();
        double centerY = bounds.getCenterY();

        for (ShapeAtributos sa : figurasSeleccionadas) {
            AffineTransform at = AffineTransform.getTranslateInstance(centerX, centerY);
            at.scale(escala, escala);
            at.translate(-centerX, -centerY);
            sa.forma = at.createTransformedShape(sa.forma);
        }

        repaint();
    }

    private Shape cloneShape(Shape shape) {
        if (shape instanceof Line2D) {
            Line2D line = (Line2D) shape;
            return new Line2D.Double(line.getP1(), line.getP2());
        } else if (shape instanceof Rectangle2D) {
            Rectangle2D rect = (Rectangle2D) shape;
            return new Rectangle2D.Double(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
        } else if (shape instanceof Ellipse2D) {
            Ellipse2D ellipse = (Ellipse2D) shape;
            return new Ellipse2D.Double(ellipse.getX(), ellipse.getY(), ellipse.getWidth(), ellipse.getHeight());
        } else if (shape instanceof Path2D) {
            Path2D path = (Path2D) shape;
            return (Path2D) path.clone();
        }
        return null;
    }

    // Métodos de dibujo
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();

        // Aplicar transformación de zoom
        g2.transform(transform);

        // Mejorar calidad de renderizado
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // Dibujar todas las figuras
        for (ShapeAtributos sa : figuras) {
            dibujarFigura(g2, sa);

            // Resaltar figuras seleccionadas
            if (figurasSeleccionadas.contains(sa)) {
                g2.setColor(new Color(100, 100, 255, 100));
                g2.fill(sa.forma);

                Rectangle2D bounds = sa.forma.getBounds2D();
                g2.setColor(Color.BLUE);
                g2.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{5, 5}, 0));
                g2.draw(bounds);
            }
        }

        // Dibujar figura actual en proceso de creación
        if (figuraActual != null) {
            dibujarFigura(g2, new ShapeAtributos(figuraActual, atributosActuales));
        }

        // Dibujar figura temporal
        if (figuraTemporal != null) {
            dibujarFigura(g2, new ShapeAtributos(figuraTemporal, atributosActuales));
        }

        // Dibujar trazo del pincel
        if (modoPincel && puntosPincel.size() > 1) {
            g2.setColor(atributosActuales.getColorContorno());
            g2.setStroke(new BasicStroke(tamanoPincel, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

            Path2D path = new Path2D.Double();
            path.moveTo(puntosPincel.get(0).x, puntosPincel.get(0).y);
            for (int i = 1; i < puntosPincel.size(); i++) {
                path.lineTo(puntosPincel.get(i).x, puntosPincel.get(i).y);
            }
            g2.draw(path);
        }

        g2.dispose();

        // Dibujar etiqueta de zoom (en coordenadas de pantalla)
        zoomLabel.paint(g);
    }

    private void dibujarFigura(Graphics2D g2, ShapeAtributos sa) {
        if (sa.atributos.isRellenoActivo()) {
            if (sa.textura != null) {
                g2.setPaint(sa.textura);
            } else if (sa.atributos.isRellenoDegradado()) {
                Paint degradado = crearDegradado(sa.forma.getBounds2D(), sa.atributos);
                g2.setPaint(degradado);
            } else {
                g2.setColor(sa.atributos.getColorRelleno());
            }
            g2.fill(sa.forma);
        }

        if (sa.atributos.isContornoActivo()) {
            g2.setColor(sa.atributos.getColorContorno());
            g2.setStroke(sa.stroke);
            g2.draw(sa.forma);
        }
    }

    private Paint crearDegradado(Rectangle2D bounds, AtributosDibujo atributos) {
        Color color1 = atributos.getColorDegradadoInicio();
        Color color2 = atributos.getColorDegradadoFin();

        switch (atributos.getDireccionDegradado()) {
            case 0: // Horizontal
                return new GradientPaint(
                        (float) bounds.getX(), (float) bounds.getCenterY(),
                        color1,
                        (float) bounds.getMaxX(), (float) bounds.getCenterY(),
                        color2
                );
            case 1: // Vertical
                return new GradientPaint(
                        (float) bounds.getCenterX(), (float) bounds.getY(),
                        color1,
                        (float) bounds.getCenterX(), (float) bounds.getMaxY(),
                        color2
                );
            case 2: // Diagonal
                return new GradientPaint(
                        (float) bounds.getX(), (float) bounds.getY(),
                        color1,
                        (float) bounds.getMaxX(), (float) bounds.getMaxY(),
                        color2
                );
            case 3: // Circular
                return new RadialGradientPaint(
                        new Point2D.Double(bounds.getCenterX(), bounds.getCenterY()),
                        (float) Math.max(bounds.getWidth(), bounds.getHeight()) / 2,
                        new float[]{0.0f, 1.0f},
                        new Color[]{color1, color2}
                );
            default: // Radial
                return new RadialGradientPaint(
                        new Point2D.Double(bounds.getCenterX(), bounds.getCenterY()),
                        (float) Math.max(bounds.getWidth(), bounds.getHeight()),
                        new Point2D.Double(bounds.getCenterX() - bounds.getWidth() / 4, bounds.getCenterY() - bounds.getHeight() / 4),
                        new float[]{0.0f, 0.8f},
                        new Color[]{color1, color2},
                        MultipleGradientPaint.CycleMethod.NO_CYCLE
                );
        }
    }

    // Métodos para crear figuras
    private Shape crearFigura(int x, int y, int width, int height) {
        int xAjustado = x;
        int yAjustado = y;
        int widthAjustado = width;
        int heightAjustado = height;

        if (width < 0) {
            xAjustado = x + width;
            widthAjustado = -width;
        }
        if (height < 0) {
            yAjustado = y + height;
            heightAjustado = -height;
        }

        switch (figuraSeleccionada) {
            case 1:
                return new Ellipse2D.Double(xAjustado, yAjustado, widthAjustado, heightAjustado); // Círculo
            case 2:
                return new Rectangle2D.Double(xAjustado, yAjustado, widthAjustado, heightAjustado); // Rectángulo
            case 3:
                return new Ellipse2D.Double(xAjustado, yAjustado, widthAjustado, heightAjustado); // Elipse
            case 4:
                return crearLineaDiscontinua(xAjustado, yAjustado, widthAjustado, heightAjustado); // Línea discontinua
            case 5:
                return new Arc2D.Double(xAjustado, yAjustado, widthAjustado, heightAjustado, 45, 270, Arc2D.PIE); // Arco
            case 6:
                return new Polygon(new int[]{xAjustado, xAjustado + widthAjustado / 2, xAjustado + widthAjustado},
                        new int[]{yAjustado + heightAjustado, yAjustado, yAjustado + heightAjustado}, 3); // Triángulo
            case 7:
                return new Polygon(new int[]{xAjustado + widthAjustado / 2, xAjustado, xAjustado + widthAjustado / 4,
                    xAjustado + widthAjustado * 3 / 4, xAjustado + widthAjustado},
                        new int[]{yAjustado, yAjustado + heightAjustado / 2, yAjustado + heightAjustado,
                            yAjustado + heightAjustado, yAjustado + heightAjustado / 2}, 5); // Estrella
            case 8:
                return new QuadCurve2D.Double(xAjustado, yAjustado + heightAjustado, xAjustado + widthAjustado / 2,
                        yAjustado, xAjustado + widthAjustado, yAjustado + heightAjustado); // Curva cuadrática
            case 9:
                return new CubicCurve2D.Double(xAjustado, yAjustado + heightAjustado, xAjustado + widthAjustado / 4,
                        yAjustado, xAjustado + widthAjustado * 3 / 4, yAjustado,
                        xAjustado + widthAjustado, yAjustado + heightAjustado); // Curva cúbica
            case 10:
                return new Ellipse2D.Double(xAjustado, yAjustado, widthAjustado, heightAjustado); // Óvalo
            case 11:
                return crearCorazon(xAjustado, yAjustado, widthAjustado, heightAjustado); // Corazón
            case 12:
                return crearCuboSimple(xAjustado, yAjustado, widthAjustado, heightAjustado); // Cubo simple
            case 13:
                return crearDonRamon(xAjustado, yAjustado, widthAjustado, heightAjustado); // Don ramon
            case 14:
                return crearCaritaFeliz(xAjustado, yAjustado, widthAjustado, heightAjustado); // Carita feliz
            case 15:
                return crearCaritaTriste(xAjustado, yAjustado, widthAjustado, heightAjustado); // Carita triste
            case 16:
                return crearCorazonRoto(xAjustado, yAjustado, widthAjustado, heightAjustado); // Corazón roto
            case 17:
                return crearSol(xAjustado, yAjustado, widthAjustado, heightAjustado); // Sol
            case 18:
                return crearArbolSimple(xAjustado, yAjustado, widthAjustado, heightAjustado); // Árbol simple
            case 19:
                return crearOjoSimple(xAjustado, yAjustado, widthAjustado, heightAjustado); // Ojo simple
            case 20:
                return crearDiamante(xAjustado, yAjustado, widthAjustado, heightAjustado); // Diamante
            case 21:
                return crearResistencia(xAjustado, yAjustado, widthAjustado, heightAjustado); // Resistencia
            case 22:
                return crearTransformador(xAjustado, yAjustado, widthAjustado, heightAjustado); // Transformador
            case 23:
                return crearDiodo(xAjustado, yAjustado, widthAjustado, heightAjustado); // Diodo
            case 24:
                return crearFuenteAlimentacion(xAjustado, yAjustado, widthAjustado, heightAjustado); // Pila
            case 25:
                return crearFuenteAC(xAjustado, yAjustado, widthAjustado, heightAjustado); // Corriente alterna
            case 26:
                return crearInterruptor(xAjustado, yAjustado, widthAjustado, heightAjustado); // Interruptor
            case 27:
                return crearPulsador(xAjustado, yAjustado, widthAjustado, heightAjustado); // Pulsador
            case 28:
                return crearFusible(xAjustado, yAjustado, widthAjustado, heightAjustado); // Fusible
            case 29:
                return crearTierra(xAjustado, yAjustado, widthAjustado, heightAjustado); // Tierra
            case 30:
                return crearMicrocontrolador(xAjustado, yAjustado, widthAjustado, heightAjustado); // Microcontrolador
            default:
                return null;
        }
    }

    // Métodos para crear figuras específicas
    private Shape crearCorazon(int x, int y, int width, int height) {
        Path2D corazon = new Path2D.Double();
        corazon.moveTo(x + width / 2, y + height / 4);
        corazon.curveTo(x + width, y - height / 4, x + width, y + height / 2, x + width / 2, y + height);
        corazon.curveTo(x, y + height / 2, x, y - height / 4, x + width / 2, y + height / 4);
        corazon.closePath();
        return corazon;
    }

    private Shape crearCuboSimple(int x, int y, int width, int height) {
        Path2D cubo = new Path2D.Double();
        cubo.moveTo(x, y);
        cubo.lineTo(x + width, y);
        cubo.lineTo(x + width, y + height);
        cubo.lineTo(x, y + height);
        cubo.closePath();
        cubo.moveTo(x + width / 4, y - height / 4);
        cubo.lineTo(x + width * 5 / 4, y - height / 4);
        cubo.lineTo(x + width * 5 / 4, y + height * 3 / 4);
        cubo.lineTo(x + width / 4, y + height * 3 / 4);
        cubo.closePath();
        cubo.moveTo(x, y);
        cubo.lineTo(x + width / 4, y - height / 4);
        cubo.moveTo(x + width, y);
        cubo.lineTo(x + width * 5 / 4, y - height / 4);
        cubo.moveTo(x + width, y + height);
        cubo.lineTo(x + width * 5 / 4, y + height * 3 / 4);
        cubo.moveTo(x, y + height);
        cubo.lineTo(x + width / 4, y + height * 3 / 4);
        return cubo;
    }

    public static Shape crearDonRamon(int x, int y, int width, int height) {
        Path2D detective = new Path2D.Double();
        detective.moveTo(x + width * 0.2, y + height * 0.2);
        detective.lineTo(x + width * 0.8, y + height * 0.2);
        detective.lineTo(x + width * 0.7, y);
        detective.lineTo(x + width * 0.3, y);
        detective.closePath();
        detective.moveTo(x + width * 0.25, y + height * 0.2);
        detective.lineTo(x + width * 0.75, y + height * 0.2);
        detective.lineTo(x + width * 0.8, y + height * 0.65);
        detective.lineTo(x + width * 0.6, y + height * 0.8);
        detective.lineTo(x + width * 0.4, y + height * 0.8);
        detective.lineTo(x + width * 0.2, y + height * 0.65);
        detective.closePath();
        detective.moveTo(x + width * 0.4, y + height * 0.35);
        detective.lineTo(x + width * 0.42, y + height * 0.35);
        detective.lineTo(x + width * 0.42, y + height * 0.37);
        detective.lineTo(x + width * 0.4, y + height * 0.37);
        detective.closePath();
        detective.moveTo(x + width * 0.58, y + height * 0.35);
        detective.lineTo(x + width * 0.6, y + height * 0.35);
        detective.lineTo(x + width * 0.6, y + height * 0.37);
        detective.lineTo(x + width * 0.58, y + height * 0.37);
        detective.closePath();
        detective.moveTo(x + width * 0.2, y + height * 0.55);
        detective.lineTo(x + width * 0.8, y + height * 0.55);
        detective.lineTo(x + width * 0.5, y + height * 0.45);
        detective.closePath();
        return detective;
    }

    private Shape crearLineaDiscontinua(int x, int y, int width, int height) {
        Path2D lineaDiscontinua = new Path2D.Double();
        lineaDiscontinua.moveTo(x, y + height / 2);
        lineaDiscontinua.lineTo(x + width, y + height / 2);
        return lineaDiscontinua;
    }

    private Shape crearCaritaFeliz(int x, int y, int width, int height) {
        Path2D carita = new Path2D.Double();
        carita.append(new Ellipse2D.Double(x, y, width, height), false);
        carita.append(new Ellipse2D.Double(x + width / 4, y + height / 3, width / 6, height / 6), false);
        carita.append(new Ellipse2D.Double(x + width * 2 / 3, y + height / 3, width / 6, height / 6), false);
        carita.append(new Arc2D.Double(x + width / 4, y + height / 2, width / 2, height / 4, 0, -180, Arc2D.OPEN), false);
        return carita;
    }

    private Shape crearCaritaTriste(int x, int y, int width, int height) {
        Path2D carita = new Path2D.Double();
        carita.append(new Ellipse2D.Double(x, y, width, height), false);
        carita.append(new Ellipse2D.Double(x + width / 4, y + height / 3, width / 6, height / 6), false);
        carita.append(new Ellipse2D.Double(x + width * 2 / 3, y + height / 3, width / 6, height / 6), false);
        carita.append(new Arc2D.Double(x + width / 4, y + height * 2 / 3, width / 2, height / 4, 0, 180, Arc2D.OPEN), false);
        return carita;
    }

    private Shape crearCorazonRoto(int x, int y, int width, int height) {
        Path2D corazonRoto = new Path2D.Double();
        corazonRoto.moveTo(x + width / 2, y + height / 4);
        corazonRoto.curveTo(x + width, y, x + width, y + height / 2, x + width / 2, y + height);
        corazonRoto.curveTo(x, y + height / 2, x, y, x + width / 2, y + height / 4);
        corazonRoto.closePath();
        corazonRoto.moveTo(x + width / 2, y + height / 4);
        corazonRoto.lineTo(x + width / 2, y + height);
        return corazonRoto;
    }

    private Shape crearSol(int x, int y, int width, int height) {
        Path2D sol = new Path2D.Double();
        sol.append(new Ellipse2D.Double(x, y, width, height), false);
        for (int i = 0; i < 8; i++) {
            double angle = Math.PI * i / 4;
            sol.moveTo(x + width / 2 + Math.cos(angle) * width / 2, y + height / 2 + Math.sin(angle) * height / 2);
            sol.lineTo(x + width / 2 + Math.cos(angle) * width, y + height / 2 + Math.sin(angle) * height);
        }
        return sol;
    }

    private Shape crearArbolSimple(int x, int y, int width, int height) {
        Path2D arbol = new Path2D.Double();
        arbol.append(new Rectangle2D.Double(x + width / 3, y + height / 2, width / 3, height / 2), false);
        arbol.append(new Polygon(new int[]{x, x + width / 2, x + width}, new int[]{y + height / 2, y, y + height / 2}, 3), false);
        return arbol;
    }

    private Shape crearOjoSimple(int x, int y, int width, int height) {
        Path2D ojo = new Path2D.Double();
        ojo.append(new Ellipse2D.Double(x, y, width, height), false);
        ojo.append(new Ellipse2D.Double(x + width / 4, y + height / 4, width / 2, height / 2), false);
        return ojo;
    }

    private Shape crearDiamante(int x, int y, int width, int height) {
        Polygon diamante = new Polygon();
        diamante.addPoint(x + width / 2, y);
        diamante.addPoint(x, y + height / 2);
        diamante.addPoint(x + width / 2, y + height);
        diamante.addPoint(x + width, y + height / 2);
        return diamante;
    }

    // Métodos para crear símbolos electrónicos
    private Shape crearResistencia(int x, int y, int width, int height) {
        int alto = Math.min(width / 2, height);
        int ancho = 2 * alto;
        int xAjustado = x + (width - ancho) / 2;
        int yAjustado = y + (height - alto) / 2;

        Path2D resistencia = new Path2D.Double();
        resistencia.moveTo(xAjustado - ancho / 4, yAjustado + alto / 2);
        resistencia.lineTo(xAjustado, yAjustado + alto / 2);
        resistencia.append(new Rectangle2D.Double(xAjustado, yAjustado, ancho, alto), false);
        resistencia.moveTo(xAjustado + ancho, yAjustado + alto / 2);
        resistencia.lineTo(xAjustado + ancho + ancho / 4, yAjustado + alto / 2);
        return resistencia;
    }

    private Shape crearTransformador(int x, int y, int width, int height) {
        int alto = Math.min(width / 2, height);
        int ancho = 2 * alto;
        int xAjustado = x + (width - ancho) / 2;
        int yAjustado = y + (height - alto) / 2;

        Path2D transformador = new Path2D.Double();
        transformador.append(new Ellipse2D.Double(xAjustado, yAjustado, alto, alto), false);
        transformador.append(new Ellipse2D.Double(xAjustado + ancho - alto, yAjustado, alto, alto), false);
        transformador.moveTo(xAjustado - alto / 2, yAjustado + alto / 2);
        transformador.lineTo(xAjustado, yAjustado + alto / 2);
        transformador.moveTo(xAjustado + ancho, yAjustado + alto / 2);
        transformador.lineTo(xAjustado + ancho + alto / 2, yAjustado + alto / 2);
        return transformador;
    }

    private Shape crearDiodo(int x, int y, int width, int height) {
        int alto = Math.min(width / 2, height);
        int ancho = 2 * alto;
        int xAjustado = x + (width - ancho) / 2;
        int yAjustado = y + (height - alto) / 2;

        Path2D diodo = new Path2D.Double();
        int[] xPoints = {xAjustado, xAjustado + ancho, xAjustado};
        int[] yPoints = {yAjustado, yAjustado + alto / 2, yAjustado + alto};
        diodo.append(new Polygon(xPoints, yPoints, 3), false);
        diodo.moveTo(xAjustado - ancho / 4, yAjustado + alto / 2);
        diodo.lineTo(xAjustado, yAjustado + alto / 2);
        diodo.moveTo(xAjustado + ancho, yAjustado);
        diodo.lineTo(xAjustado + ancho, yAjustado + alto);
        return diodo;
    }

    private Shape crearFuenteAlimentacion(int x, int y, int width, int height) {
        int diametro = Math.min(width, height);
        int radio = diametro / 2;
        int xAjustado = x + (width - diametro) / 2;
        int yAjustado = y + (height - diametro) / 2;

        Path2D fuente = new Path2D.Double();
        fuente.append(new Ellipse2D.Double(xAjustado, yAjustado, diametro, diametro), false);
        fuente.moveTo(xAjustado + radio, yAjustado - radio / 2);
        fuente.lineTo(xAjustado + radio, yAjustado);
        fuente.moveTo(xAjustado + radio, yAjustado + diametro);
        fuente.lineTo(xAjustado + radio, yAjustado + diametro + radio / 2);

        int tamanoCruz = radio / 4;
        fuente.moveTo(xAjustado + radio - tamanoCruz, yAjustado + radio / 2);
        fuente.lineTo(xAjustado + radio + tamanoCruz, yAjustado + radio / 2);
        fuente.moveTo(xAjustado + radio, yAjustado + radio / 2 - tamanoCruz);
        fuente.lineTo(xAjustado + radio, yAjustado + radio / 2 + tamanoCruz);
        fuente.moveTo(xAjustado + radio - tamanoCruz, yAjustado + radio * 3 / 2);
        fuente.lineTo(xAjustado + radio + tamanoCruz, yAjustado + radio * 3 / 2);
        return fuente;
    }

    private Shape crearFuenteAC(int x, int y, int width, int height) {
        int diametro = Math.min(width, height);
        int radio = diametro / 2;
        int xAjustado = x + (width - diametro) / 2;
        int yAjustado = y + (height - diametro) / 2;

        Path2D fuenteAC = new Path2D.Double();
        fuenteAC.append(new Ellipse2D.Double(xAjustado, yAjustado, diametro, diametro), false);
        fuenteAC.moveTo(xAjustado + radio, yAjustado - radio / 2);
        fuenteAC.lineTo(xAjustado + radio, yAjustado);
        fuenteAC.moveTo(xAjustado + radio, yAjustado + diametro);
        fuenteAC.lineTo(xAjustado + radio, yAjustado + diametro + radio / 2);

        int amplitud = radio / 4;
        int longitudOnda = radio;
        int puntos = 10;
        for (int i = 0; i < puntos; i++) {
            double x1 = xAjustado + radio - longitudOnda / 2 + i * (longitudOnda / puntos);
            double y1 = yAjustado + radio + amplitud * Math.sin(2 * Math.PI * i / puntos);
            double x2 = xAjustado + radio - longitudOnda / 2 + (i + 1) * (longitudOnda / puntos);
            double y2 = yAjustado + radio + amplitud * Math.sin(2 * Math.PI * (i + 1) / puntos);
            fuenteAC.moveTo(x1, y1);
            fuenteAC.lineTo(x2, y2);
        }
        return fuenteAC;
    }

    private Shape crearInterruptor(int x, int y, int width, int height) {
        int alto = Math.min(width / 2, height);
        int ancho = 2 * alto;
        int xAjustado = x + (width - ancho) / 2;
        int yAjustado = y + (height - alto) / 2;

        Path2D interruptor = new Path2D.Double();
        interruptor.moveTo(xAjustado, yAjustado + alto / 2);
        interruptor.lineTo(xAjustado + ancho / 3, yAjustado + alto / 2);
        interruptor.moveTo(xAjustado + ancho * 2 / 3, yAjustado + alto / 2);
        interruptor.lineTo(xAjustado + ancho, yAjustado + alto / 2);

        int radioCirculo = alto / 8;
        interruptor.append(new Ellipse2D.Double(
                xAjustado + ancho / 3 - radioCirculo, yAjustado + alto / 2 - radioCirculo,
                radioCirculo * 2, radioCirculo * 2
        ), false);
        interruptor.append(new Ellipse2D.Double(
                xAjustado + ancho * 2 / 3 - radioCirculo, yAjustado + alto / 2 - radioCirculo,
                radioCirculo * 2, radioCirculo * 2
        ), false);
        return interruptor;
    }

    private Shape crearPulsador(int x, int y, int width, int height) {
        int alto = Math.min(width / 2, height);
        int ancho = 2 * alto;
        int xAjustado = x + (width - ancho) / 2;
        int yAjustado = y + (height - alto) / 2;

        Path2D pulsador = new Path2D.Double();
        pulsador.moveTo(xAjustado, yAjustado + alto / 2);
        pulsador.lineTo(xAjustado + ancho / 3, yAjustado + alto / 2);
        pulsador.moveTo(xAjustado + ancho * 2 / 3, yAjustado + alto / 2);
        pulsador.lineTo(xAjustado + ancho, yAjustado + alto / 2);

        int radioCirculo = alto / 8;
        pulsador.append(new Ellipse2D.Double(
                xAjustado + ancho / 3 - radioCirculo, yAjustado + alto / 2 - radioCirculo,
                radioCirculo * 2, radioCirculo * 2
        ), false);
        pulsador.append(new Ellipse2D.Double(
                xAjustado + ancho * 2 / 3 - radioCirculo, yAjustado + alto / 2 - radioCirculo,
                radioCirculo * 2, radioCirculo * 2
        ), false);

        int longitudPulsadorSuperior = alto / 8;
        int longitudPulsadorInferior = alto / 4;
        int xCentro = xAjustado + ancho / 2;
        int yCentro = yAjustado + alto / 2;

        pulsador.moveTo(xCentro, yCentro - longitudPulsadorSuperior);
        pulsador.lineTo(xCentro, yCentro + longitudPulsadorInferior);

        int longitudHorizontal = alto / 4;
        pulsador.moveTo(xCentro - longitudHorizontal / 2, yCentro + longitudPulsadorInferior);
        pulsador.lineTo(xCentro + longitudHorizontal / 2, yCentro + longitudPulsadorInferior);
        return pulsador;
    }

    private Shape crearFusible(int x, int y, int width, int height) {
        int alto = Math.min(width / 2, height);
        int ancho = 2 * alto;
        int xAjustado = x + (width - ancho) / 2;
        int yAjustado = y + (height - alto) / 2;

        Path2D fusible = new Path2D.Double();
        fusible.moveTo(xAjustado, yAjustado + alto / 2);
        fusible.lineTo(xAjustado + ancho, yAjustado + alto / 2);

        int anchoRectangulo = alto / 3;
        int altoRectangulo = alto / 4;
        fusible.append(new Rectangle2D.Double(
                xAjustado + ancho / 2 - anchoRectangulo / 2, yAjustado + alto / 2 - altoRectangulo / 2,
                anchoRectangulo, altoRectangulo
        ), false);
        return fusible;
    }

    private Shape crearTierra(int x, int y, int width, int height) {
        int alto = Math.min(width / 2, height);
        int ancho = 2 * alto;
        int xAjustado = x + (width - ancho) / 2;
        int yAjustado = y + (height - alto) / 2;

        Path2D tierra = new Path2D.Double();
        tierra.moveTo(xAjustado + ancho / 2, yAjustado);
        tierra.lineTo(xAjustado + ancho / 2, yAjustado + alto);

        int longitudLinea1 = ancho / 2;
        int longitudLinea2 = ancho / 3;
        int longitudLinea3 = ancho / 4;

        tierra.moveTo(xAjustado + ancho / 2 - longitudLinea1 / 2, yAjustado + alto / 3);
        tierra.lineTo(xAjustado + ancho / 2 + longitudLinea1 / 2, yAjustado + alto / 3);
        tierra.moveTo(xAjustado + ancho / 2 - longitudLinea2 / 2, yAjustado + alto * 2 / 3);
        tierra.lineTo(xAjustado + ancho / 2 + longitudLinea2 / 2, yAjustado + alto * 2 / 3);
        tierra.moveTo(xAjustado + ancho / 2 - longitudLinea3 / 2, yAjustado + alto);
        tierra.lineTo(xAjustado + ancho / 2 + longitudLinea3 / 2, yAjustado + alto);
        return tierra;
    }

    private Shape crearMicrocontrolador(int x, int y, int width, int height) {
        int alto = Math.min(width / 2, height);
        int ancho = 2 * alto;
        int xAjustado = x + (width - ancho) / 2;
        int yAjustado = y + (height - alto) / 2;

        Path2D microcontrolador = new Path2D.Double();
        microcontrolador.append(new Rectangle2D.Double(xAjustado, yAjustado, ancho, alto), false);

        int longitudPin = alto / 8;
        int separacionPin = alto / 6;

        for (int i = 1; i <= 4; i++) {
            microcontrolador.moveTo(xAjustado, yAjustado + i * separacionPin);
            microcontrolador.lineTo(xAjustado - longitudPin, yAjustado + i * separacionPin);
            microcontrolador.moveTo(xAjustado + ancho, yAjustado + i * separacionPin);
            microcontrolador.lineTo(xAjustado + ancho + longitudPin, yAjustado + i * separacionPin);
            microcontrolador.moveTo(xAjustado + i * separacionPin, yAjustado);
            microcontrolador.lineTo(xAjustado + i * separacionPin, yAjustado - longitudPin);
            microcontrolador.moveTo(xAjustado + i * separacionPin, yAjustado + alto);
            microcontrolador.lineTo(xAjustado + i * separacionPin, yAjustado + alto + longitudPin);
        }
        return microcontrolador;
    }

    // Métodos de gestión de archivos
    public void guardarComoImagen(File archivo) throws IOException {
        BufferedImage imagen = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = imagen.createGraphics();
        paint(g2d);
        g2d.dispose();

        String formato = archivo.getName().toLowerCase().endsWith(".png") ? "png" : "jpg";
        ImageIO.write(imagen, formato, archivo);
    }

    public void cargarImagenRelleno(File archivo) {
        try {
            BufferedImage imagen = ImageIO.read(archivo);
            atributosActuales.setImagenRelleno(imagen);
            atributosActuales.setRellenoImagenActivo(true);
            for (ShapeAtributos sa : figurasSeleccionadas) {
                sa.atributos.setImagenRelleno(imagen);
                sa.atributos.setRellenoImagenActivo(true);
                if (imagen != null) {
                    sa.textura = new TexturePaint(
                            imagen,
                            new Rectangle2D.Double(0, 0,
                                    imagen.getWidth(),
                                    imagen.getHeight())
                    );
                }
            }
            repaint();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar la imagen: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void guardarDibujo() {
        BufferedImage imagen = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = imagen.createGraphics();
        paint(g2);
        g2.dispose();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar dibujo");
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try {
                ImageIO.write(imagen, "png", fileToSave);
                JOptionPane.showMessageDialog(this, "Dibujo guardado correctamente!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar el dibujo: " + ex.getMessage());
            }
        }
    }

    // Métodos de configuración
    public void setFigura(int figura) {
        this.figuraSeleccionada = figura;
    }

    public void borrarUltimaFigura() {
        if (!figuras.isEmpty()) {
            figuras.remove(figuras.size() - 1);
            repaint();
        }
    }

    public void borrarTodo() {
        figuras.clear();
        figurasSeleccionadas.clear();
        repaint();
    }

    public void setColorRelleno(Color color) {
        atributosActuales.setColorRelleno(color);
        for (ShapeAtributos sa : figurasSeleccionadas) {
            sa.atributos.setColorRelleno(color);
        }
        repaint();
    }

    public Color getColorRelleno() {
        try {
            if (figurasSeleccionadas != null && !figurasSeleccionadas.isEmpty()) {
                Color c = figurasSeleccionadas.get(0).atributos.getColorRelleno();
                return c != null ? c : atributosActuales.getColorRelleno();
            }
            return atributosActuales.getColorRelleno();
        } catch (Exception e) {
            return Color.WHITE; // Color por defecto en caso de error
        }
    }

    public void setColorContorno(Color color) {
        atributosActuales.setColorContorno(color);
        for (ShapeAtributos sa : figurasSeleccionadas) {
            sa.atributos.setColorContorno(color);
        }
        repaint();
    }

    public Color getColorContorno() {
        if (!figurasSeleccionadas.isEmpty()) {
            // Devuelve el color de contorno de la primera figura seleccionada
            return figurasSeleccionadas.get(0).atributos.getColorContorno();
        }
        // Si no hay figuras seleccionadas, devuelve el color de los atributos actuales
        return atributosActuales.getColorContorno();
    }

    public void setRelleno(boolean activo) {
        atributosActuales.setRellenoActivo(activo);
        for (ShapeAtributos sa : figurasSeleccionadas) {
            sa.atributos.setRellenoActivo(activo);
        }
        repaint();
    }

    public boolean isRelleno() {
        // Devuelve el estado del relleno actual
        // Si hay figuras seleccionadas, devuelve el estado de la primera como referencia
        if (!figurasSeleccionadas.isEmpty()) {
            return figurasSeleccionadas.get(0).atributos.isRellenoActivo();
        }
        return atributosActuales.isRellenoActivo();
    }

    public void setContorno(boolean activo) {
        atributosActuales.setContornoActivo(activo);
        for (ShapeAtributos sa : figurasSeleccionadas) {
            sa.atributos.setContornoActivo(activo);
        }
        repaint();
    }

    public boolean isContornoActivo() {
        if (!figurasSeleccionadas.isEmpty()) {
            // Devuelve el estado del contorno de la primera figura seleccionada
            return figurasSeleccionadas.get(0).atributos.isContornoActivo();
        }
        // Si no hay figuras seleccionadas, devuelve el estado de los atributos actuales
        return atributosActuales.isContornoActivo();
    }

    public void setPatronPunteado(float[] patron) {
        atributosActuales.setPatronPunteado(patron);
        atributosActuales.setStrokePunteado(true);
        for (ShapeAtributos sa : figurasSeleccionadas) {
            sa.atributos.setPatronPunteado(patron);
            sa.atributos.setStrokePunteado(true);
            sa.stroke = sa.atributos.crearStroke();
        }
        repaint();
    }

    public void setAnchoContorno(float ancho) {
        atributosActuales.setAnchoContorno(ancho);
        for (ShapeAtributos sa : figurasSeleccionadas) {
            sa.atributos.setAnchoContorno(ancho);
            sa.stroke = sa.atributos.crearStroke();
        }
        repaint();
    }

    public void setModoSeleccion(boolean activo) {
        this.modoSeleccion = activo;
        if (activo) {
            this.modoMover = false;
            this.modoRotar = false;
            this.modoEscalar = false;
            this.modoPincel = false;
            this.modoGoma = false;
        }
        repaint();
    }

    public boolean isModoSeleccion() {
        return this.modoSeleccion;
    }

    public void setModoMover(boolean activo) {
        this.modoMover = activo;
        if (activo) {
            this.modoSeleccion = false;
            this.modoRotar = false;
            this.modoEscalar = false;
            this.modoPincel = false;
            this.modoGoma = false;
        }
        repaint();
    }

    public void setModoRotar(boolean activo) {
        this.modoRotar = activo;
        if (activo) {
            this.modoSeleccion = false;
            this.modoMover = false;
            this.modoEscalar = false;
            this.modoPincel = false;
            this.modoGoma = false;
        }
        repaint();
    }

    public void setModoEscalar(boolean activo) {
        this.modoEscalar = activo;
        if (activo) {
            this.modoSeleccion = false;
            this.modoMover = false;
            this.modoRotar = false;
            this.modoPincel = false;
            this.modoGoma = false;
        }
        repaint();
    }

    public void setModoPincel(boolean activo) {
        this.modoPincel = activo;
        if (activo) {
            this.modoSeleccion = false;
            this.modoMover = false;
            this.modoRotar = false;
            this.modoEscalar = false;
            this.modoGoma = false;
            puntosPincel.clear();
        }
        repaint();
    }

    public void setModoGoma(boolean activo) {
        this.modoGoma = activo;
        if (activo) {
            this.modoSeleccion = false;
            this.modoMover = false;
            this.modoRotar = false;
            this.modoEscalar = false;
            this.modoPincel = false;
        }
        repaint();
    }

    public void setTamanoPincel(float tamano) {
        this.tamanoPincel = tamano;
    }

    public void aplicarTextura(int index) {
        if (figurasSeleccionadas.isEmpty()) {
            return;
        }

        for (ShapeAtributos sa : figurasSeleccionadas) {
            sa.atributos.setTexturaIndex(index);
            if (index >= 0 && index < texturas.length && texturas[index] != null) {
                sa.textura = new TexturePaint(
                        texturas[index],
                        new Rectangle2D.Double(0, 0,
                                texturas[index].getWidth(),
                                texturas[index].getHeight())
                );
            } else {
                sa.textura = null;
            }
        }
        repaint();
    }

    public void setSeleccionMultiple(boolean multiple) {
        this.seleccionMultiple = multiple;
        if (!multiple && figurasSeleccionadas.size() > 1) {
            ShapeAtributos ultima = figurasSeleccionadas.get(figurasSeleccionadas.size() - 1);
            figurasSeleccionadas.clear();
            figurasSeleccionadas.add(ultima);
        }
        repaint();
    }

    public void clearTemporaryShape() {
        figuraTemporal = null;
        repaint();
    }

    // Getters
    public AtributosDibujo getAtributosActuales() {
        return atributosActuales;
    }

    public ImageIcon getIconSelect() {
        return iconSelect;
    }

    public ImageIcon getIconMove() {
        return iconMove;
    }

    public ImageIcon getIconRotate() {
        return iconRotate;
    }

    public ImageIcon getIconScale() {
        return iconScale;
    }

    public ImageIcon getIconPencil() {
        return iconPencil;
    }

    public ImageIcon getIconEraser() {
        return iconEraser;
    }

    public ImageIcon getIconMerge() {
        return iconMerge;
    }

    public ImageIcon getIconDelete() {
        return iconDelete;
    }

    public ImageIcon getIconCopy() {
        return iconCopy;
    }

    public ImageIcon getIconPaste() {
        return iconPaste;
    }

    public ImageIcon getIconBringToFront() {
        return iconBringToFront;
    }

    public ImageIcon getIconSendToBack() {
        return iconSendToBack;
    }

    public ImageIcon getIconGroup() {
        return iconGroup;
    }

    public ImageIcon getIconUngroup() {
        return iconUngroup;
    }

    void setColor(Color color) {
        atributosActuales.setColorContorno(color);
        repaint();
    }
}
