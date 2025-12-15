package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Cursor;
import java.awt.Label;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JToggleButton;
import modelo.*;
import controlador.*;

public class MainGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton SubirHeroe, BajarHeroe, SubirBestia, BajarBestia;
	private JList<String> listaHeroes;
	private JList<String> listaBestias;
	private DefaultListModel<String> modeloHeroes;
	private DefaultListModel<String> modeloBestias;
	private JTextArea textArea;
	private App controlador;
	private JButton newPJ, luchar, mapas, musica, reiniciar;
	private JToggleButton velocidad;
	private ControladorMusica controladorMusica;
	private boolean modoLento = false;
	private static final int DELAY_RAPIDO = 2;
	private static final int DELAY_LENTO = 10;
	private JPanel panelMapa;
	private String rutaMapaActual = "";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI frame = new MainGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGUI() {
		
		
		// Inicializar el controlador
		controlador = new App();

		// Inicializar el controlador de musica
		controladorMusica = new ControladorMusica();
		
		// Cambiar logotipo
		ImageIcon icon = new ImageIcon("src/fondos/logo.jpg");
		Image img = icon.getImage().getScaledInstance(280, 230, Image.SCALE_SMOOTH);
		setIconImage(img);

		// Ventana
		setPreferredSize(new Dimension(700, 400));
		setTitle("EL SEÑOR DE LOS ANILLOS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 899, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		// Panel del Titulo
		JPanel panelTitulo = new JPanel();
		panelTitulo.setPreferredSize(new Dimension(10, 80));
		panelTitulo.setBackground(new Color(53, 53, 53));
		contentPane.add(panelTitulo, BorderLayout.NORTH);
		panelTitulo.setLayout(new GridLayout(1, 0, 0, 0));

		// Titulo
		JLabel Titulo = new JLabel("EL SEÑOR DE LOS ANILLOS");
		Titulo.setHorizontalAlignment(SwingConstants.CENTER);
		Titulo.setFont(new Font("Arial", Font.BOLD, 36));
		Titulo.setForeground(new Color(187, 173, 124));
		panelTitulo.add(Titulo);

		// Panel de la derecha
		JPanel panelDerecha = new JPanel();
		panelDerecha.setPreferredSize(new Dimension(250, 10));
		contentPane.add(panelDerecha, BorderLayout.EAST);
		panelDerecha.setLayout(new GridLayout(1, 0, 0, 0));
		panelDerecha.setBorder(new EmptyBorder(10, 5, 10, 5));
		panelDerecha.setBackground(new Color(187, 173, 124));


		// Panel auxiliar de la derecha
		JPanel panelAuxDerecha = new JPanel();
		panelAuxDerecha.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		panelAuxDerecha.setBackground(new Color(187, 173, 124));
		panelAuxDerecha.setLayout(new BoxLayout(panelAuxDerecha, BoxLayout.Y_AXIS));
		panelDerecha.add(panelAuxDerecha);

		// Panel para el manejo de ejercitos
		JPanel panelEjercitos = new JPanel();
		panelAuxDerecha.add(panelEjercitos);
		panelEjercitos.setLayout(new GridLayout(1, 0, 0, 0));
		panelEjercitos.setBorder(new EmptyBorder(20, 10, 10, 10));
		panelEjercitos.setBackground(new Color(187, 173, 124));

		// Panel que gestiona a los heroes
		JPanel panelHeroes = new JPanel();
		panelEjercitos.add(panelHeroes);
		panelHeroes.setLayout(new BorderLayout(0, 0));
		panelHeroes.setBackground(new Color(53, 53, 53));


		// Titulo de heroes
		Label tituloHeroes = new Label("HEROES:");
		panelHeroes.add(tituloHeroes, BorderLayout.NORTH);
		panelHeroes.setForeground(new Color(187, 173, 124));


		// Inicializar modelo de lista para heroes
		modeloHeroes = new DefaultListModel<>();
		listaHeroes = new JList<>(modeloHeroes);
		listaHeroes.setBackground(new Color(53, 53, 53));
		listaHeroes.setForeground(new Color(187, 173, 124));

		// JScrollPane para heroes
		JScrollPane scrollHeroes = new JScrollPane(listaHeroes);
		scrollHeroes.setPreferredSize(new Dimension(100, 100));
		panelHeroes.add(scrollHeroes, BorderLayout.CENTER);

		// Panel de botones y botones de subir y bajar para heroes
		JPanel botonesHeroes = new JPanel(new FlowLayout(FlowLayout.CENTER, 3, 3));
		botonesHeroes.setBackground(new Color(53, 53, 53));

		SubirHeroe = new JButton("↑");
		SubirHeroe.setForeground(new Color(187, 173, 124));
		SubirHeroe.setBackground(new Color(53, 53, 53));
		BajarHeroe = new JButton("↓");
		BajarHeroe.setForeground(new Color(187, 173, 124));
		BajarHeroe.setBackground(new Color(53, 53, 53));


		// Agregar botones de subir y bajar heroes
		botonesHeroes.add(SubirHeroe);
		botonesHeroes.add(BajarHeroe);
		panelHeroes.add(botonesHeroes, BorderLayout.SOUTH);

		// Panel que gestiona a las Bestias
		JPanel panelBestias = new JPanel();
		panelEjercitos.add(panelBestias);
		panelBestias.setLayout(new BorderLayout(0, 0));
		panelBestias.setBackground(new Color(53, 53, 53));


		// Titulo de bestias
		Label tituloBestias = new Label("BESTIAS:");
		panelBestias.add(tituloBestias, BorderLayout.NORTH);
		panelBestias.setForeground(new Color(187, 173, 124));

		

		// Inicializar modelo de lista para bestias
		modeloBestias = new DefaultListModel<>();
		listaBestias = new JList<>(modeloBestias);
		listaBestias.setBackground(new Color(53, 53, 53));
		listaBestias.setForeground(new Color(187, 173, 124));
		

		// JScrollPane de bestias
		JScrollPane scrollBestias = new JScrollPane(listaBestias);
		scrollBestias.setPreferredSize(new Dimension(100, 200));
		panelBestias.add(scrollBestias, BorderLayout.CENTER);


		// Panel de botones y botones de subir y bajar para heroes
		JPanel botonesBestias = new JPanel(new FlowLayout(FlowLayout.CENTER, 3, 3));
		botonesBestias.setBackground(new Color(53, 53, 53));

		SubirBestia = new JButton("↑");
		SubirBestia.setForeground(new Color(187, 173, 124));
		SubirBestia.setBackground(new Color(53, 53, 53));

		BajarBestia = new JButton("↓");
		BajarBestia.setForeground(new Color(187, 173, 124));
		BajarBestia.setBackground(new Color(53, 53, 53));



		// Agregar botones de subir y bajar Bestias
		botonesBestias.add(SubirBestia);
		botonesBestias.add(BajarBestia);
		panelBestias.add(botonesBestias, BorderLayout.SOUTH);

		// Panel de acciones
		JPanel panelAcciones = new JPanel();
		panelAcciones.setBackground(new Color(187, 173, 124));
		panelAuxDerecha.add(panelAcciones);
		panelAcciones.setBorder(new EmptyBorder(0, 20, 5, 20));
		panelAcciones.setLayout(new GridLayout(0, 1, 0, 0));

		// Boton que abrira una ventana para añadir un personaje
		newPJ = new JButton("Añadir PJ");
		newPJ.setPreferredSize(new Dimension(10, 20));
		newPJ.setForeground(new Color(187, 173, 124));
		newPJ.setBackground(new Color(53, 53, 53));
		panelAcciones.add(newPJ);

		// Boton que iniciara la batalla
		luchar = new JButton("¡¡¡ A LUCHAR !!!");
		luchar.setPreferredSize(new Dimension(10, 20));
		luchar.setForeground(new Color(187, 173, 124));
		luchar.setBackground(new Color(53, 53, 53));
		panelAcciones.add(luchar);

		// Panel auxiliar de la izquierda
		JPanel panelAuxIzquierda = new JPanel();
		contentPane.add(panelAuxIzquierda, BorderLayout.CENTER);
		panelAuxIzquierda.setLayout(new BorderLayout(0, 0));

		// Panel para opciones del juego
		JPanel panelOpcJuego = new JPanel();
		panelOpcJuego.setBackground(new Color(187, 173, 124));
		panelOpcJuego.setPreferredSize(new Dimension(10, 50));
		panelAuxIzquierda.add(panelOpcJuego, BorderLayout.NORTH);
		panelOpcJuego.setLayout(new GridLayout(1, 0, 0, 0));
		panelOpcJuego.setBorder(new EmptyBorder(5, 80, 5, 80));

		// Boton que abrira una ventana para camiar de mapa
		mapas = new JButton("Mapas");
		mapas.setBorder(null);
		mapas.setBackground(new Color(53, 53, 53));
		mapas.setForeground(new Color(187, 173, 124));
		panelOpcJuego.add(mapas);
		panelOpcJuego.add(Box.createRigidArea(new Dimension(10, 0)));


		// Pulsador que cambiara la velocidad de salida de la consola
		velocidad = new JToggleButton("X2");
		velocidad.setBackground(new Color(53, 53, 53));
		velocidad.setForeground(new Color(187, 173, 124));
		panelOpcJuego.add(velocidad);
		panelOpcJuego.add(Box.createRigidArea(new Dimension(10, 0)));

		// Boton que abrira una ventana para camiar de musica
		musica = new JButton("♫");
		musica.setBorder(null);
		musica.setBackground(new Color(53, 53, 53));
		musica.setForeground(new Color(187, 173, 124));
		panelOpcJuego.add(musica);
		panelOpcJuego.add(Box.createRigidArea(new Dimension(10, 0)));

		// Boton que reinicia los ejercitos
		reiniciar = new JButton("↻");
		reiniciar.setBorder(null);
		reiniciar.setBackground(new Color(53, 53, 53));
		reiniciar.setForeground(new Color(187, 173, 124));
		panelOpcJuego.add(reiniciar);

		// Panel donde se expondra el mapa
		panelMapa = new JPanel();
		panelMapa.setBackground(new Color(53, 53, 53));
		panelMapa.setLayout(new BorderLayout(0, 0));
		panelMapa.setBorder(new EmptyBorder(10, 70, 10, 70));
		panelAuxIzquierda.add(panelMapa, BorderLayout.CENTER);
		


		// Panel para la consola
		JPanel panelConsola = new JPanel();
		panelMapa.add(panelConsola, BorderLayout.CENTER);
		panelConsola.setLayout(new GridLayout(1, 0, 0, 0));

		// JTextArea para la salida de consola
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);

		// Añadir scroll al textArea
		JScrollPane scrollConsola = new JScrollPane(textArea);
		panelConsola.add(scrollConsola);

		// Manejador de listeners
		configurarListeners();

		// Cargar los ejercitos iniciales
		actualizarListas();
		textArea.setText("=== SISTEMA LISTO ===\n\nPrepara tus ejércitos y presiona '¡¡¡ A LUCHAR !!!' para comenzar la batalla.\n");

		// Cargar mapa por defecto
		cargarMapaPorDefecto();
	}

	/**
	 * Configura y asigna todos los ActionListeners a los componentes de la GUI.
	 */
	private void configurarListeners() {
		// Listener para el botón de Luchar
		luchar.addActionListener(e -> iniciarBatalla());
		
		// Listener para el toggle de velocidad
		velocidad.addActionListener(e -> {
		    if (velocidad.isSelected()) {
		        velocidad.setText("X1");
		        modoLento = true;
		    } else {
		        velocidad.setText("X2");
		        modoLento = false;
		    }
		});

		// Listeners para subir/bajar heroes
		SubirHeroe.addActionListener(e -> {
			int indice = listaHeroes.getSelectedIndex();
			if (indice > 0) {
				intercambiarPersonajes(controlador.getEjercitoHeroes().getEjercito(), indice, indice - 1);
				actualizarListas();
				listaHeroes.setSelectedIndex(indice - 1);
			}
		});

		
		BajarHeroe.addActionListener(e -> {
			int indice = listaHeroes.getSelectedIndex();
			if (indice >= 0 && indice < controlador.getEjercitoHeroes().getEjercito().size() - 1) {
				intercambiarPersonajes(controlador.getEjercitoHeroes().getEjercito(), indice, indice + 1);
				actualizarListas();
				listaHeroes.setSelectedIndex(indice + 1);
			}
		});

		// Listeners para subir/bajar bestias
		SubirBestia.addActionListener(e -> {
			int indice = listaBestias.getSelectedIndex();
			if (indice > 0) {
				intercambiarPersonajes(controlador.getEjercitoBestias().getEjercito(), indice, indice - 1);
				actualizarListas();
				listaBestias.setSelectedIndex(indice - 1);
			}
		});

		BajarBestia.addActionListener(e -> {
			int indice = listaBestias.getSelectedIndex();
			if (indice >= 0 && indice < controlador.getEjercitoBestias().getEjercito().size() - 1) {
				intercambiarPersonajes(controlador.getEjercitoBestias().getEjercito(), indice, indice + 1);
				actualizarListas();
				listaBestias.setSelectedIndex(indice + 1);
			}
		});

		// Listeners para añadir personajes
		newPJ.addActionListener(e -> abrirVentanaAñadirPersonaje());

		// Listeners para reiniciar ejercitos
		reiniciar.addActionListener(e -> reiniciarListas());

		// Listeners para abrir la ventana de musica
		musica.addActionListener(e -> abrirVentanaMusica());
		
		// Listeners para abrir la ventana de mapas
		mapas.addActionListener(e -> abrirVentanaMapas());
	}
	
	
	/**
	 * Abre una ventana para añadir un nuevo personaje
	 */
	private void abrirVentanaAñadirPersonaje() {
		// JDialog para añadir personaje
		JDialog dialogoNuevo = new JDialog(this, "CREAR NUEVO PERSONAJE", true);
		dialogoNuevo.setSize(400, 300);
		AgregarPersonaje dialogo = new AgregarPersonaje(this, controlador, this::actualizarListas);
	    dialogo.setVisible(true);
	}
	
	/**
	 * Intercambia dos personajes dentro de un ejercito
	 */
	private void intercambiarPersonajes(ArrayList<Personaje> ejercito, int indice1, int indice2) {
		Personaje temp = ejercito.get(indice1);
		ejercito.set(indice1, ejercito.get(indice2));
		ejercito.set(indice2, temp);
	}

	/**
	 * Actualiza las listas de heroes y bestias desde el controlador, utilizando
	 * streams para una carga mas concisa.
	 */
	private void actualizarListas() {
		// 1. Limpiar modelos
		modeloHeroes.clear();
		modeloBestias.clear();

		// 2. Cargar heroes usando stream
		controlador.getEjercitoHeroes().getEjercito().stream().map(this::formatearPersonaje)
				.forEach(modeloHeroes::addElement);

		// 3. Cargar bestias usando stream
		controlador.getEjercitoBestias().getEjercito().stream().map(this::formatearPersonaje)
				.forEach(modeloBestias::addElement);
	}

	/**
	 * Formatea un personaje para mostrarlo en la lista
	 */
	private String formatearPersonaje(Personaje p) {
		return p.getNombre() + " [" + p.getTipo() + "] - V:" + p.getP_Vida() + " A:" + p.getNivelArmadura();
	}

	/**
	 * Reinicia las listas de heroes y bestias
	 */
	private void reiniciarListas() {		
		// Limpiar modelos
		modeloHeroes.clear();
		modeloBestias.clear();

		// Inicializar ejercitos desde cero
		controlador.inicializarEjercitos();

		// Actualizar las listas
		actualizarListas();

		// reiniciar conlosa
		textArea.setText(
				"=== SISTEMA LISTO ===\n\nPrepara tus ejércitos y presiona '¡¡¡ A LUCHAR !!!' para comenzar la batalla.\n");

	}

	/**
	 * Abre una ventana para seleccionar la musica de fondo
	 */
	private void abrirVentanaMusica() {

		// JDialog para seleccionar musica
		JDialog dialogoMusica = new JDialog(this, "SELECCIONAR MÚSICA", true);
		dialogoMusica.setSize(400, 450);
		SeleccionarMusica dialogo = new SeleccionarMusica(this, controladorMusica);
	    dialogo.setVisible(true);
		}
		
	/**
	 * Abre una ventana para seleccionar el mapa de fondo
	 */
	private void abrirVentanaMapas() {
	    // JDialog para seleccionar mapa
		SeleccionarMapa dialogo = new SeleccionarMapa(this, () -> {
	        // Este callback se ejecuta ANTES de que se cierre el diálogo
	        // No podemos obtener la ruta aquí
	    });
	    dialogo.setVisible(true);
	    
	    // Después de que se cierra el diálogo, obtenemos la ruta
	    try {
	        String rutaMapa = dialogo.getRutaMapaSeleccionado();
	        aplicarMapaDeFondo(rutaMapa);
	        rutaMapaActual = rutaMapa;
	    } catch (Exception e) {
	        System.err.println("Error al aplicar el mapa: " + e.getMessage());
	    }
	}
	/**
	 * Aplica una imagen de fondo al panel del mapa
	 */
	private void aplicarMapaDeFondo(String rutaImagen) {
	    try {
	        // Crear un JLabel con la imagen de fondo
	        JLabel lblFondo = new JLabel() {
	            private javax.swing.ImageIcon icon = new javax.swing.ImageIcon(rutaImagen);
	            
	            @Override
	            protected void paintComponent(java.awt.Graphics g) {
	                super.paintComponent(g);
	                java.awt.Image img = icon.getImage();
	                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	            }
	        };
	        
	        lblFondo.setLayout(new BorderLayout());
	        
	        // Crear panel para la consola con transparencia
	        JPanel panelConsolaTransparente = new JPanel();
	        panelConsolaTransparente.setLayout(new GridLayout(1, 0, 0, 0));
	        panelConsolaTransparente.setOpaque(false);
	        
	        // Hacer el textArea semitransparente para ver el fondo
	        textArea.setOpaque(false);
	        textArea.setBackground(new Color(255, 255, 255, 200)); // Blanco con 200 de opacidad
	        textArea.setFont(new Font("Arial", Font.BOLD, 14));
	        textArea.setForeground(Color.white);
	        
	        
	        // Crear un nuevo JScrollPane
	        JScrollPane scrollConsola = new JScrollPane(textArea);
	        scrollConsola.setBorder(null);
	        scrollConsola.setOpaque(false);
	        scrollConsola.getViewport().setOpaque(false);
	        
	        panelConsolaTransparente.add(scrollConsola);
	        lblFondo.add(panelConsolaTransparente, BorderLayout.CENTER);
	        
	        // Limpiar y actualizar el panelMapa
	        panelMapa.removeAll();
	        panelMapa.setLayout(new BorderLayout());
	        panelMapa.add(lblFondo, BorderLayout.CENTER);
	        
	        panelMapa.revalidate();
	        
	    } catch (Exception e) {
	        throw new RuntimeException("Error al cargar la imagen: " + e.getMessage());
	    }
	}
	
	/**
	 * Carga un mapa por defecto al iniciar la aplicación
	 */
	private void cargarMapaPorDefecto() {
	    String rutaMapaDefecto = "src/fondos/default.jpg";
	    
	    try {
	        aplicarMapaDeFondo(rutaMapaDefecto);
	        rutaMapaActual = rutaMapaDefecto;
	    } catch (Exception e) {
	        System.err.println("No se pudo cargar el mapa por defecto: " + e.getMessage());
	        // Si falla, continuar sin mapa
	    }
	}
	
	

	/**
	 * Inicia la batalla
	 */
	private void iniciarBatalla() {
		textArea.setText("=== PREPARANDO BATALLA ===\n\n");

		// Deshabilitar botones durante la batalla
	    luchar.setEnabled(false);
	    newPJ.setEnabled(false);
	    SubirHeroe.setEnabled(false);
	    BajarHeroe.setEnabled(false);
	    SubirBestia.setEnabled(false);
	    BajarBestia.setEnabled(false);
	    velocidad.setEnabled(false);
	    reiniciar.setEnabled(false);
	    
	    // Ejecutar la batalla en un hilo separado
	    new Thread(() -> {
	        String resultadoBatalla = controlador.iniciarBatalla();
	        
	        // Mostrar el texto con animación
	        escribirTextoAnimado(resultadoBatalla);
	        
	        // Actualizar las listas después de la batalla
	        javax.swing.SwingUtilities.invokeLater(() -> {
	            actualizarListas();
	            textArea.setCaretPosition(textArea.getDocument().getLength());
	            
	            // Rehabilitar botones
	            luchar.setEnabled(true);
	    	    newPJ.setEnabled(true);
	    	    SubirHeroe.setEnabled(true);
	    	    BajarHeroe.setEnabled(true);
	    	    SubirBestia.setEnabled(true);
	    	    BajarBestia.setEnabled(true);
	    	    velocidad.setEnabled(true);
	    	    reiniciar.setEnabled(true);
	        });
	    }).start();
	}
	
	/**
	 * Escribe texto en el textArea con efecto de animación
	 */
	private void escribirTextoAnimado(String texto) {
	    textArea.setText("");
	    int delay = modoLento ? DELAY_LENTO : DELAY_RAPIDO;
	    
	    for (int i = 0; i < texto.length(); i++) {
	        final char c = texto.charAt(i);
	        javax.swing.SwingUtilities.invokeLater(() -> {
	            textArea.append(String.valueOf(c));
	            textArea.setCaretPosition(textArea.getDocument().getLength());
	        });
	        
	        try {
	            Thread.sleep(delay);
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	            break;
	        }
	    }
	}
}

