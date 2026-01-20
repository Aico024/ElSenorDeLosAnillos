package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * Diálogo para seleccionar el mapa de fondo
 */
public class SeleccionarMapa extends JDialog {


    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private ButtonGroup grupoMapas;
    private JLabel lblVistaPrevia;
    private String mapaSeleccionado;
    private Runnable callbackAplicar;
    private JRadioButton rbMapa1, rbMapa2, rbMapa3, rbMapa4, rbMapa5;



    /**
     * Constructor del dialogo
     * @param parent ventana padre
     * @param callbackAplicar callback para aplicar el mapa seleccionado
     */
    public SeleccionarMapa(JFrame parent, Runnable callbackAplicar) {
        super(parent, "SELECCIONAR MAPA", true);
        this.callbackAplicar = callbackAplicar;
        this.mapaSeleccionado = null;

        inicializarComponentes();

        setSize(600, 400);
        setLocationRelativeTo(parent);
    }

    /**
     * Inicializa los componentes del diálogo
     */
    private void inicializarComponentes() {
    	 getContentPane().setLayout(new BorderLayout(10, 10));
    	 getContentPane().setBackground(new Color(53, 53, 53));

    	    // Panel principal
    	    JPanel panelPrincipal = new JPanel();
    	    panelPrincipal.setLayout(new BorderLayout(10, 10));
    	    panelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));
    	    panelPrincipal.setBackground(new Color(53, 53, 53));


    	    // Titulo
    	    JLabel lblTitulo = new JLabel("Selecciona un mapa:");
    	    lblTitulo.setForeground(new Color(187, 173, 124));
    	    lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
    	    panelPrincipal.add(lblTitulo, BorderLayout.NORTH);


    	    // Panel central con opciones y vista previa
    	    JPanel panelCentral = new JPanel(new BorderLayout(10, 10));
    	    panelCentral.setBackground(new Color(53, 53, 53));


    	    // Panel derecho para vista previa
    	    lblVistaPrevia = new JLabel();
    	    lblVistaPrevia.setPreferredSize(new Dimension(300, 250));
    	    lblVistaPrevia.setHorizontalAlignment(SwingConstants.CENTER);
    	    lblVistaPrevia.setText("Selecciona un mapa");
    	    lblVistaPrevia.setForeground(new Color(187, 173, 124));


    	    // Panel izquierdo con radio buttons
    	    JPanel panelOpciones = crearPanelOpciones();

    	    panelCentral.add(panelOpciones, BorderLayout.WEST);
    	    panelCentral.add(lblVistaPrevia, BorderLayout.CENTER);

    	    panelPrincipal.add(panelCentral, BorderLayout.CENTER);

    	    // Panel de botones
    	    JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    	    panelBotones.setBackground(new Color(53, 53, 53));

    	    JButton btnAceptar = new JButton("Aceptar");
    	    btnAceptar.setForeground(new Color(187, 173, 124));
    	    btnAceptar.setBackground(new Color(53, 53, 53));


    	    JButton btnCancelar = new JButton("Cancelar");
    	    btnCancelar.setForeground(new Color(187, 173, 124));
    	    btnCancelar.setBackground(new Color(53, 53, 53));


    	    panelBotones.add(btnAceptar);
    	    panelBotones.add(btnCancelar);

    	    getContentPane().add(panelPrincipal, BorderLayout.CENTER);
    	    getContentPane().add(panelBotones, BorderLayout.SOUTH);

    	    // Configurar listeners de botones
    	    btnAceptar.addActionListener(e -> aplicarSeleccion());
    	    btnCancelar.addActionListener(e -> dispose());
    	}

    /**
     * Crea el panel de opciones con los radio buttons
     */
    private JPanel crearPanelOpciones() {
        JPanel panelOpciones = new JPanel();
        panelOpciones.setBackground(new Color(53, 53, 53));
        panelOpciones.setLayout(new BoxLayout(panelOpciones, BoxLayout.Y_AXIS));
        panelOpciones.setBorder(new EmptyBorder(10, 10, 10, 10));

        // ButtonGroup para los JRadioButtons
        grupoMapas = new ButtonGroup();

        // Crear JRadioButtons con las opciones de mapas
        rbMapa1 = new JRadioButton("Lord Of The Rings", true);
        rbMapa1.setForeground(new Color(187, 173, 124));
        rbMapa1.setBackground(new Color(53, 53, 53));
        rbMapa1.setBorder(null);

        rbMapa2 = new JRadioButton("La Comarca");
        rbMapa2.setForeground(new Color(187, 173, 124));
        rbMapa2.setBackground(new Color(53, 53, 53));
        rbMapa2.setBorder(null);

        rbMapa3 = new JRadioButton("Mordor");
        rbMapa3.setForeground(new Color(187, 173, 124));
        rbMapa3.setBackground(new Color(53, 53, 53));
        rbMapa3.setBorder(null);

        rbMapa4 = new JRadioButton("Bosque Negro");
        rbMapa4.setForeground(new Color(187, 173, 124));
        rbMapa4.setBackground(new Color(53, 53, 53));
        rbMapa4.setBorder(null);

        rbMapa5 = new JRadioButton("Minas Tirith");
        rbMapa5.setForeground(new Color(187, 173, 124));
        rbMapa5.setBackground(new Color(53, 53, 53));
        rbMapa5.setBorder(null);



        // Listener para actualizar vista previa
        ActionListener actualizarVista = e -> actualizarVistaPrevia();

        // Añadir al grupo, configurar listeners y añadir al panel
        agregarRadioButton(panelOpciones, rbMapa1, actualizarVista);
        agregarRadioButton(panelOpciones, rbMapa2, actualizarVista);
        agregarRadioButton(panelOpciones, rbMapa3, actualizarVista);
        agregarRadioButton(panelOpciones, rbMapa4, actualizarVista);
        agregarRadioButton(panelOpciones, rbMapa5, actualizarVista);

        // Mostrar vista previa inicial
        actualizarVistaPrevia();

        return panelOpciones;
    }

    /**
     * Añade un radio button al panel con su listener
     */
    private void agregarRadioButton(JPanel panel, JRadioButton radioButton, ActionListener listener) {
        grupoMapas.add(radioButton);
        radioButton.addActionListener(listener);
        panel.add(radioButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    /**
     * Actualiza la vista previa del mapa seleccionado
     */
    private void actualizarVistaPrevia() {
        URL rutaImagen = obtenerRutaMapa(obtenerMapaSeleccionado());

        try {
            if (rutaImagen != null) {
                ImageIcon icon = new ImageIcon(rutaImagen);
                Image img = icon.getImage().getScaledInstance(280, 230, Image.SCALE_SMOOTH);
                lblVistaPrevia.setIcon(new ImageIcon(img));
                lblVistaPrevia.setText("");
            } else {
                lblVistaPrevia.setIcon(null);
                lblVistaPrevia.setText("Imagen no disponible");
            }
        } catch (Exception ex) {
            lblVistaPrevia.setIcon(null);
            lblVistaPrevia.setText("Imagen no disponible");
        }
    }

    /**
     * Obtiene el nombre del mapa seleccionado
     */
    private String obtenerMapaSeleccionado() {
        if (rbMapa1.isSelected()) {
			return "Lord Of The Rings";
		}
        if (rbMapa2.isSelected()) {
			return "La Comarca";
		}
        if (rbMapa3.isSelected()) {
			return "Mordor";
		}
        if (rbMapa4.isSelected()) {
			return "Bosque Negro";
		}
        if (rbMapa5.isSelected()) {
			return "Minas Tirith";
		}
        return "Lord Of The Rings";
    }

    /**
     * Obtiene la ruta del archivo de imagen segun el nombre del mapa
     */
    private URL obtenerRutaMapa(String nombreMapa) {
        String rutaRecurso = "";
        switch (nombreMapa) {
            case "Lord Of The Rings":
                rutaRecurso = "fondos/default.jpg";
                break;
            case "La Comarca":
                rutaRecurso = "fondos/la_comarca.jpg";
                break;
            case "Mordor":
                rutaRecurso = "fondos/mordor.jpg";
                break;
            case "Bosque Negro":
                rutaRecurso = "fondos/bosque_negro.jpg";
                break;
            case "Minas Tirith":
                rutaRecurso = "fondos/minas_tirith.jpg";
                break;
            default:
                rutaRecurso = "fondos/default.jpg";
        }
        return getClass().getClassLoader().getResource(rutaRecurso);
    }

    /**
     * Aplica la seleccion del mapa
     */
    private void aplicarSeleccion() {
        mapaSeleccionado = obtenerMapaSeleccionado();
        URL rutaImagen = obtenerRutaMapa(mapaSeleccionado);

        try {
            if (rutaImagen != null) {
                // Ejecutar el callback para aplicar el mapa
                if (callbackAplicar != null) {
                    callbackAplicar.run();
                }

                JOptionPane.showMessageDialog(this,
                    "Mapa aplicado: " + mapaSeleccionado,
                    "Mapa seleccionado",
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                throw new Exception("No se pudo cargar el recurso del mapa");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error al aplicar el mapa: " + mapaSeleccionado +
                "\nAsegúrate de que el archivo existe en la carpeta 'fondos'",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }

        dispose();
    }

    /**
     * Obtiene la ruta del mapa seleccionado
     */
    public URL getRutaMapaSeleccionado() {
        return obtenerRutaMapa(mapaSeleccionado != null ? mapaSeleccionado : "Lord Of The Rings");
    }
}