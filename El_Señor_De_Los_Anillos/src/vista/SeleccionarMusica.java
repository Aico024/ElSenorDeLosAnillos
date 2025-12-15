package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;

import controlador.ControladorMusica;

public class SeleccionarMusica extends JDialog {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ControladorMusica controladorMusica;
    private ButtonGroup grupoMusica;
    private JSlider sliderVolumen;
    private JLabel lblPorcentaje;
    private JRadioButton rb1, rb2, rb3, rb4, rb5, rb6, rb7;
    
    /**
     * Constructor del dialogo
     * @param parent ventana padre
     * @param controladorMusica controlador de musica
     */
    public SeleccionarMusica(JFrame parent, ControladorMusica controladorMusica) {
        super(parent, "SELECCIONAR MÚSICA", true);
        this.controladorMusica = controladorMusica;
        
        inicializarComponentes();
        
        setSize(400, 450);
        setLocationRelativeTo(parent);
    }
    
    /**
     * Inicializa los componentes del dialogo
     */
    private void inicializarComponentes() {
        getContentPane().setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(53, 53, 53));
        
        // Panel principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));
        panelPrincipal.setBackground(new Color(53, 53, 53));
        
        // Titulo
        JLabel lblTitulo = new JLabel("Selecciona una canción:");
        lblTitulo.setForeground(new Color(187, 173, 124));
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        lblTitulo.setAlignmentX(CENTER_ALIGNMENT);
        panelPrincipal.add(lblTitulo);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // ButtonGroup para los JRadioButtons
        grupoMusica = new ButtonGroup();
        
        // Crear JRadioButtons con las opciones de música
        rb1 = new JRadioButton("The Shire - Howard Shore", true);
        rb1.setForeground(new Color(187, 173, 124));
        rb1.setBackground(new Color(53, 53, 53));
        rb1.setBorder(null);
        rb2 = new JRadioButton("Concerning Hobbits");
        rb2.setForeground(new Color(187, 173, 124));
        rb2.setBackground(new Color(53, 53, 53));
        rb2.setBorder(null);
        rb3 = new JRadioButton("The Breaking of the Fellowship");
        rb3.setForeground(new Color(187, 173, 124));
        rb3.setBackground(new Color(53, 53, 53));
        rb3.setBorder(null);
        rb4 = new JRadioButton("Minas Tirith");
        rb4.setForeground(new Color(187, 173, 124));
        rb4.setBackground(new Color(53, 53, 53));
        rb4.setBorder(null);
        rb5 = new JRadioButton("The Battle of the Pelennor Fields");
        rb5.setForeground(new Color(187, 173, 124));
        rb5.setBackground(new Color(53, 53, 53));
        rb5.setBorder(null);
        rb6 = new JRadioButton("Numb");
        rb6.setForeground(new Color(187, 173, 124));
        rb6.setBackground(new Color(53, 53, 53));
        rb6.setBorder(null);
        rb7 = new JRadioButton("Sin música");
        rb7.setForeground(new Color(187, 173, 124));
        rb7.setBackground(new Color(53, 53, 53));
        rb7.setBorder(null);
        
        // Añadir al grupo y al panel
        agregarRadioButton(panelPrincipal, rb1);
        agregarRadioButton(panelPrincipal, rb2);
        agregarRadioButton(panelPrincipal, rb3);
        agregarRadioButton(panelPrincipal, rb4);
        agregarRadioButton(panelPrincipal, rb5);
        agregarRadioButton(panelPrincipal, rb6);
        agregarRadioButton(panelPrincipal, rb7);
        
        // Separador visual
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 15)));
        JSeparator separador = new JSeparator();
        separador.setForeground(new Color(187, 173, 124));
        panelPrincipal.add(separador);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // Panel para el control de volumen
        panelPrincipal.add(crearPanelVolumen());
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.setBackground(new Color(53, 53, 53));

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setForeground(new Color(187, 173, 124));
        btnAceptar.setBackground(new Color(53, 53, 53));
        btnAceptar.setBorder(null);
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setForeground(new Color(187, 173, 124));
        btnCancelar.setBackground(new Color(53, 53, 53));
        btnCancelar.setBorder(null);
        
        panelBotones.add(btnAceptar);
        panelBotones.add(btnCancelar);
        
        getContentPane().add(panelPrincipal, BorderLayout.CENTER);
        getContentPane().add(panelBotones, BorderLayout.SOUTH);
        
        // Configurar listeners de botones
        btnAceptar.addActionListener(e -> aplicarSeleccion());
        btnCancelar.addActionListener(e -> dispose());
    }
    
    /**
     * Añade un radio button al panel y al grupo
     */
    private void agregarRadioButton(JPanel panel, JRadioButton radioButton) {
        grupoMusica.add(radioButton);
        panel.add(radioButton);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
    }
    
    /**
     * Crea el panel de control de volumen
     */
    private JPanel crearPanelVolumen() {
        JPanel panelVolumen = new JPanel();
        panelVolumen.setBackground(new Color(53, 53, 53));
        panelVolumen.setLayout(new BorderLayout(10, 5));
        panelVolumen.setMaximumSize(new Dimension(400, 80));
        
        // Label para volumen
        JLabel lblVolumen = new JLabel("Volumen:");
        lblVolumen.setForeground(new Color(187, 173, 124));
        lblVolumen.setFont(new Font("Arial", Font.BOLD, 12));
        panelVolumen.add(lblVolumen, BorderLayout.NORTH);
        
        // Panel para slider y etiqueta de porcentaje
        JPanel panelSlider = new JPanel(new BorderLayout(5, 0));
        panelSlider.setBackground(new Color(53, 53, 53));
        
        // Slider para controlar el volumen (0-100)
        sliderVolumen = new JSlider(0, 100, 70);
        sliderVolumen.setBackground(new Color(53, 53, 53));
        sliderVolumen.setPaintTicks(true);
        sliderVolumen.setPaintLabels(true);

        
        // Etiqueta que muestra el porcentaje actual
        lblPorcentaje = new JLabel("70%");
        lblPorcentaje.setFont(new Font("Arial", Font.BOLD, 14));
        lblPorcentaje.setPreferredSize(new Dimension(50, 20));
        lblPorcentaje.setForeground(new Color(187, 173, 124));

        
        // Listener para actualizar el volumen en tiempo real
        sliderVolumen.addChangeListener(e -> {
            int valor = sliderVolumen.getValue();
            lblPorcentaje.setText(valor + "%");
            
            // Ajustar volumen si hay música reproduciendose
            if (controladorMusica.estaReproduciendo()) {
                float volumen = valor / 100.0f;
                controladorMusica.ajustarVolumen(volumen);
            }
        });
        
        panelSlider.add(sliderVolumen, BorderLayout.CENTER);
        panelSlider.add(lblPorcentaje, BorderLayout.EAST);
        
        panelVolumen.add(panelSlider, BorderLayout.CENTER);
        
        return panelVolumen;
    }
    
    /**
     * Aplica la seleccion de musica
     */
    private void aplicarSeleccion() {
        String cancionSeleccionada = "";
        String rutaArchivo = "";
        
        // Determinar que opcion fue seleccionada
        if (rb1.isSelected()) {
            cancionSeleccionada = "The Shire - Howard Shore";
            rutaArchivo = "src/musica/the_shire.wav";
        } else if (rb2.isSelected()) {
            cancionSeleccionada = "Concerning Hobbits";
            rutaArchivo = "src/musica/concerning_hobbits.wav";
        } else if (rb3.isSelected()) {
            cancionSeleccionada = "The Breaking of the Fellowship";
            rutaArchivo = "src/musica/breaking_fellowship.wav";
        } else if (rb4.isSelected()) {
            cancionSeleccionada = "Minas Tirith";
            rutaArchivo = "src/musica/minas_tirith.wav";
        } else if (rb5.isSelected()) {
            cancionSeleccionada = "The Battle of the Pelennor Fields";
            rutaArchivo = "src/musica/pelennor_fields.wav";
        } else if (rb6.isSelected()) {
            cancionSeleccionada = "Numb - Linkin park";
            rutaArchivo = "src/musica/Numb.wav";
        } else if (rb7.isSelected()) {
            // Detener la música actual
            controladorMusica.detener();
            JOptionPane.showMessageDialog(this, 
                "Música detenida", 
                "Sin música",
                JOptionPane.INFORMATION_MESSAGE);
            dispose();
            return;
        }
        
        // Reproducir la musica seleccionada
        try {
            controladorMusica.reproducir(rutaArchivo);
            
            // Aplicar el volumen configurado
            float volumen = sliderVolumen.getValue() / 100.0f;
            controladorMusica.ajustarVolumen(volumen);
            
            JOptionPane.showMessageDialog(this, 
                "Reproduciendo: " + cancionSeleccionada,
                "Música seleccionada", 
                JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error al reproducir: " + cancionSeleccionada + 
                "\nAsegúrate de que el archivo existe en la carpeta 'musica'",
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
        
        dispose();
    }
}