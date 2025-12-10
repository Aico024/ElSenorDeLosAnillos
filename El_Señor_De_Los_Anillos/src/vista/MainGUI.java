package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Cursor;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;
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
	// Declaración de botones
    private JButton newPJ, luchar, mapas, musica, salir;
    private JToggleButton velocidad;

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
		panelTitulo.setBackground(new Color(255, 128, 128));
		contentPane.add(panelTitulo, BorderLayout.NORTH);
		panelTitulo.setLayout(new GridLayout(1, 0, 0, 0));

		// Titulo
		JLabel Titulo = new JLabel("EL SEÑOR DE LOS ANILLOS");
		Titulo.setHorizontalAlignment(SwingConstants.CENTER);
		Titulo.setFont(new Font("Arial", Font.BOLD, 36));
		panelTitulo.add(Titulo);

		// Panel de la derecha
		JPanel panelDerecha = new JPanel();
		panelDerecha.setBackground(new Color(0, 128, 192));
		panelDerecha.setPreferredSize(new Dimension(250, 10));
		contentPane.add(panelDerecha, BorderLayout.EAST);
		panelDerecha.setLayout(new GridLayout(1, 0, 0, 0));
		panelDerecha.setBorder(new EmptyBorder(10, 5, 10, 5));

		// Panel auxiliar de la derecha
		JPanel panelAuxDerecha = new JPanel();
		panelAuxDerecha.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		panelAuxDerecha.setBackground(new Color(0, 128, 64));
		panelDerecha.add(panelAuxDerecha);
		panelAuxDerecha.setLayout(new BoxLayout(panelAuxDerecha, BoxLayout.Y_AXIS));

		// Panel para el manejo de ejercitos
		JPanel panelEjercitos = new JPanel();
		panelEjercitos.setBackground(new Color(64, 128, 128));
		panelAuxDerecha.add(panelEjercitos);
		panelEjercitos.setLayout(new GridLayout(1, 0, 0, 0));
		panelEjercitos.setBorder(new EmptyBorder(20, 10, 20, 10));

		// Panel que gestiona a los heroes
		JPanel panelHeroes = new JPanel();
		panelEjercitos.add(panelHeroes);
		panelHeroes.setLayout(new BorderLayout(0, 0));

		// Titulo de heroes
		Label tituloHeroes = new Label("HEROES:");
		panelHeroes.add(tituloHeroes, BorderLayout.NORTH);

		// Inicializar modelo de lista para heroes
		modeloHeroes = new DefaultListModel<>();
		listaHeroes = new JList<>(modeloHeroes);

		// JScrollPane para heroes
		JScrollPane scrollHeroes = new JScrollPane(listaHeroes);
		scrollHeroes.setPreferredSize(new Dimension(100, 100));
		panelHeroes.add(scrollHeroes, BorderLayout.CENTER);

		// Panel de botones y botones de subir y bajar para heroes
		JPanel botonesHeroes = new JPanel(new FlowLayout(FlowLayout.CENTER, 3, 3));
		SubirHeroe = new JButton("↑");
		BajarHeroe = new JButton("↓");

		// Agregar botones de subir y bajar heroes
		botonesHeroes.add(SubirHeroe);
		botonesHeroes.add(BajarHeroe);
		panelHeroes.add(botonesHeroes, BorderLayout.SOUTH);

		// Panel que gestiona a las Bestias
		JPanel panelBestias = new JPanel();
		panelEjercitos.add(panelBestias);
		panelBestias.setLayout(new BorderLayout(0, 0));

		// Titulo de bestias
		Label tituloBestias = new Label("BESTIAS:");
		panelBestias.add(tituloBestias, BorderLayout.NORTH);

		// Inicializar modelo de lista para bestias
		modeloBestias = new DefaultListModel<>();
		listaBestias = new JList<>(modeloBestias);

		// JScrollPane de bestias
		JScrollPane scrollBestias = new JScrollPane(listaBestias);
		scrollBestias.setPreferredSize(new Dimension(100, 200));
		panelBestias.add(scrollBestias, BorderLayout.CENTER);

		// Panel de botones y botones de subir y bajar para heroes
		JPanel botonesBestias = new JPanel(new FlowLayout(FlowLayout.CENTER, 3, 3));
		SubirBestia = new JButton("↑");
		BajarBestia = new JButton("↓");

		// Agregar botones de subir y bajar Bestias
		botonesBestias.add(SubirBestia);
		botonesBestias.add(BajarBestia);
		panelBestias.add(botonesBestias, BorderLayout.SOUTH);

		// Panel de acciones
		JPanel panelAcciones = new JPanel();
		panelAcciones.setBackground(new Color(64, 128, 128));
		panelAuxDerecha.add(panelAcciones);
		panelAcciones.setBorder(new EmptyBorder(0, 20, 0, 20));
		panelAcciones.setLayout(new BorderLayout(0, 0));

		// Boton que abrira una ventana para añadir un personaje
		newPJ = new JButton("Añadir PJ");
		newPJ.setPreferredSize(new Dimension(10, 20));
		panelAcciones.add(newPJ, BorderLayout.NORTH);
		

		// Boton que iniciara la batalla
		luchar = new JButton("¡¡¡ A LUCHAR !!!");
		luchar.setPreferredSize(new Dimension(10, 20));
		panelAcciones.add(luchar, BorderLayout.SOUTH);

		// Panel auxiliar de la izquierda
		JPanel panelAuxIzquierda = new JPanel();
		panelAuxIzquierda.setBackground(new Color(192, 192, 192));
		contentPane.add(panelAuxIzquierda, BorderLayout.CENTER);
		panelAuxIzquierda.setLayout(new BorderLayout(0, 0));

		// Panel para opciones del juego
		JPanel panelOpcJuego = new JPanel();
		panelOpcJuego.setBackground(new Color(255, 255, 128));
		panelOpcJuego.setPreferredSize(new Dimension(10, 50));
		panelAuxIzquierda.add(panelOpcJuego, BorderLayout.NORTH);
		panelOpcJuego.setLayout(new GridLayout(1, 0, 0, 0));
		panelOpcJuego.setBorder(new EmptyBorder(5, 80, 5, 80));

		// Boton que abrira una ventana para camiar de mapa
		mapas = new JButton("Mapas");
		panelOpcJuego.add(mapas);
		panelOpcJuego.add(Box.createRigidArea(new Dimension(10, 0)));

		// Pulsador que cambiara la velocidad de salida de la consola
		velocidad = new JToggleButton("X2");
		panelOpcJuego.add(velocidad);
		panelOpcJuego.add(Box.createRigidArea(new Dimension(10, 0)));

		// Boton que abrira una ventana para camiar de musica
		musica = new JButton("Musica");
		panelOpcJuego.add(musica);
		panelOpcJuego.add(Box.createRigidArea(new Dimension(10, 0)));

		// Boton que hara volver a la pantalla de inicio
		salir = new JButton("Salir");
		panelOpcJuego.add(salir);

		// Panel donde se expondra el mapa
		JPanel panelMapa = new JPanel();
		panelMapa.setBackground(new Color(128, 255, 128));
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


	}
	
	/**
     * Configura y asigna todos los ActionListeners a los componentes de la GUI.
     */
    private void configurarListeners() {
        // Listener para el boton de Luchar
    	luchar.addActionListener(e -> iniciarBatalla());    }

	/**
	 * Actualiza las listas de heroes y bestias desde el controlador, 
	 * utilizando streams para una carga mas concisa.
	 */
	private void actualizarListas() {
	    // 1. Limpiar modelos
	    modeloHeroes.clear();
	    modeloBestias.clear();

	    // 2. Cargar heroes usando stream
	    controlador.getEjercitoHeroes().getEjercito().stream()
	        .map(this::formatearPersonaje)
	        .forEach(modeloHeroes::addElement);

	    // 3. Cargar bestias usando stream
	    controlador.getEjercitoBestias().getEjercito().stream()
	        .map(this::formatearPersonaje)
	        .forEach(modeloBestias::addElement);
	}

	/**
	 * Formatea un personaje para mostrarlo en la lista
	 */
	private String formatearPersonaje(Personaje p) {
		return p.getNombre() + " [" + p.getTipo() + "] - V:" + p.getP_Vida() + " A:" + p.getNivelArmadura();
	}

	/**
	 * Inicia la batalla
	 */
	private void iniciarBatalla() {
			textArea.setText("=== PREPARANDO BATALLA ===\n\n");
			
			// Ejecutar la batalla y obtener el resultado completo
			String resultadoBatalla = controlador.iniciarBatalla();
			textArea.setText(resultadoBatalla);

			// Actualizar las listas despues de la batalla
			actualizarListas();

			// Desplazar el scroll al final
			textArea.setCaretPosition(textArea.getDocument().getLength());
	}
}
