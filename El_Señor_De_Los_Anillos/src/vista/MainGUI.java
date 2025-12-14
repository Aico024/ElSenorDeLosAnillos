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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
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
		musica = new JButton("♫");
		panelOpcJuego.add(musica);
		panelOpcJuego.add(Box.createRigidArea(new Dimension(10, 0)));

		// Boton que reinicia los ejercitos
		reiniciar = new JButton("↻");
		panelOpcJuego.add(reiniciar);

		// Panel donde se expondra el mapa
		panelMapa = new JPanel();
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
		textArea.setText(
				"=== SISTEMA LISTO ===\n\nPrepara tus ejércitos y presiona '¡¡¡ A LUCHAR !!!' para comenzar la batalla.\n");

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
		dialogoNuevo.setLocationRelativeTo(this);
		dialogoNuevo.getContentPane().setLayout(new BorderLayout(10, 10));

		// JPanel con padding
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new GridLayout(5, 2, 10, 10));
		panelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));

		// Campos del formulario
		JLabel lblNombre = new JLabel("Nombre:");
		JTextField txtNombre = new JTextField();

		JLabel lblVida = new JLabel("Vida:");
		JTextField txtVida = new JTextField("100");

		JLabel lblArmadura = new JLabel("Armadura:");
		JTextField txtArmadura = new JTextField("30");

		JLabel lblTipo = new JLabel("Tipo:");
		String[] tipos = { "Elfo", "Humano", "Hobbit", "Orco", "Trasgo" };
		JComboBox<String> comboTipo = new JComboBox<>(tipos);

		JLabel lblBando = new JLabel("Bando:");
		String[] bandos = { "Héroe", "Bestia" };
		JComboBox<String> comboBando = new JComboBox<>(bandos);

		// Añadir componentes al JPanel
		panelPrincipal.add(lblNombre);
		panelPrincipal.add(txtNombre);
		panelPrincipal.add(lblVida);
		panelPrincipal.add(txtVida);
		panelPrincipal.add(lblArmadura);
		panelPrincipal.add(txtArmadura);
		panelPrincipal.add(lblTipo);
		panelPrincipal.add(comboTipo);
		panelPrincipal.add(lblBando);
		panelPrincipal.add(comboBando);

		// JPanel para botones
		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		JButton btnAceptar = new JButton("Añadir");
		JButton btnCancelar = new JButton("Cancelar");

		panelBotones.add(btnAceptar);
		panelBotones.add(btnCancelar);

		// Añadir paneles al dialogo
		dialogoNuevo.getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		dialogoNuevo.getContentPane().add(panelBotones, BorderLayout.SOUTH);

		// Listener del boton Aceptar
		btnAceptar.addActionListener(ev -> {
			try {
				// Obtener datos
				String nombre = txtNombre.getText().trim();
				int vida = Integer.parseInt(txtVida.getText().trim());
				int armadura = Integer.parseInt(txtArmadura.getText().trim());
				String tipoSeleccionado = (String) comboTipo.getSelectedItem();
				boolean esHeroe = comboBando.getSelectedItem().equals("Héroe");

				// Validar nombre
				if (nombre.isEmpty()) {
					JOptionPane.showMessageDialog(dialogoNuevo, "El nombre no puede estar vacío", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// LLamar al metodo para crear el personaje segun el tipo
				Personaje nuevoPersonaje = crearPersonaje(tipoSeleccionado, nombre, vida, armadura, esHeroe);

				if (nuevoPersonaje != null) {
					// Añadir al ejercito correspondiente
					controlador.agregarPersonaje(nuevoPersonaje, esHeroe);

					// Actualizar las listas
					actualizarListas();

					// Mostrar mensaje de exito
					JOptionPane.showMessageDialog(dialogoNuevo,
							"¡" + nombre + " ha sido añadido al ejército de " + (esHeroe ? "Héroes" : "Bestias") + "!",
							"Éxito", JOptionPane.INFORMATION_MESSAGE);

					// Cerrar el dialogo
					dialogoNuevo.dispose();
				}

			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(dialogoNuevo, "Los valores de Vida y Armadura deben ser números", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		});

		// Listener del boton Cancelar
		btnCancelar.addActionListener(ev -> dialogoNuevo.dispose());

		// Mostrar el dialogo
		dialogoNuevo.setVisible(true);
	}

	/**
	 * Crea un personaje segun el tipo
	 */
	private Personaje crearPersonaje(String tipo, String nombre, int vida, int armadura, boolean esHeroe) {
		// Validar que el tipo corresponda al bando
		boolean esTipoHeroe = tipo.equals("Elfo") || tipo.equals("Humano") || tipo.equals("Hobbit");
		boolean esTipoBestia = tipo.equals("Orco") || tipo.equals("Trasgo");

		if (esHeroe && !esTipoHeroe) {
			JOptionPane.showMessageDialog(this,
					"El tipo " + tipo + " no puede ser un Héroe. Selecciona Elfo, Humano o Hobbit.", "Error de Tipo",
					JOptionPane.ERROR_MESSAGE);
			return null;
		}

		if (!esHeroe && !esTipoBestia) {
			JOptionPane.showMessageDialog(this,
					"El tipo " + tipo + " no puede ser una Bestia. Selecciona Orco o Trasgo.", "Error de Tipo",
					JOptionPane.ERROR_MESSAGE);
			return null;
		}

		// Crear el personaje
		switch (tipo) {
		case "Elfo":
			return new Elfo(nombre, vida, armadura);
		case "Humano":
			return new Humano(nombre, vida, armadura);
		case "Hobbit":
			return new Hobbit(nombre, vida, armadura);
		case "Orco":
			return new Orco(nombre, vida, armadura);
		case "Trasgo":
			return new Trasgo(nombre, vida, armadura);
		default:
			return null;
		}
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
		dialogoMusica.setLocationRelativeTo(this);
		dialogoMusica.getContentPane().setLayout(new BorderLayout(10, 10));

		// Panel principal
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
		panelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));

		// Titulo
		JLabel lblTitulo = new JLabel("Selecciona una canción:");
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
		lblTitulo.setAlignmentX(CENTER_ALIGNMENT);
		panelPrincipal.add(lblTitulo);
		panelPrincipal.add(Box.createRigidArea(new Dimension(0, 15)));

		// ButtonGroup para los JRadioButtons
		ButtonGroup grupoMusica = new ButtonGroup();

		// Crear JRadioButtons con rutas a archivos
		JRadioButton rb1 = new JRadioButton("The Shire - Howard Shore", true);
		JRadioButton rb2 = new JRadioButton("Concerning Hobbits");
		JRadioButton rb3 = new JRadioButton("The Breaking of the Fellowship");
		JRadioButton rb4 = new JRadioButton("Minas Tirith");
		JRadioButton rb5 = new JRadioButton("The Battle of the Pelennor Fields");
		JRadioButton rb6 = new JRadioButton("Numb");
		JRadioButton rb7 = new JRadioButton("Sin música");

		// Añadir al grupo y al panel
		grupoMusica.add(rb1);
		grupoMusica.add(rb2);
		grupoMusica.add(rb3);
		grupoMusica.add(rb4);
		grupoMusica.add(rb5);
		grupoMusica.add(rb6);
		grupoMusica.add(rb7);

		panelPrincipal.add(rb1);
		panelPrincipal.add(Box.createRigidArea(new Dimension(0, 5)));
		panelPrincipal.add(rb2);
		panelPrincipal.add(Box.createRigidArea(new Dimension(0, 5)));
		panelPrincipal.add(rb3);
		panelPrincipal.add(Box.createRigidArea(new Dimension(0, 5)));
		panelPrincipal.add(rb4);
		panelPrincipal.add(Box.createRigidArea(new Dimension(0, 5)));
		panelPrincipal.add(rb5);
		panelPrincipal.add(Box.createRigidArea(new Dimension(0, 5)));
		panelPrincipal.add(rb6);
		panelPrincipal.add(Box.createRigidArea(new Dimension(0, 5)));
		panelPrincipal.add(rb7);

		// Separador visual
		panelPrincipal.add(Box.createRigidArea(new Dimension(0, 15)));
		panelPrincipal.add(new javax.swing.JSeparator());
		panelPrincipal.add(Box.createRigidArea(new Dimension(0, 15)));

		// JPanel para el control de volumen
		JPanel panelVolumen = new JPanel();
		panelVolumen.setLayout(new BorderLayout(10, 5));
		panelVolumen.setMaximumSize(new Dimension(400, 80));

		// JLabel para volumen
		JLabel lblVolumen = new JLabel("Volumen:");
		lblVolumen.setFont(new Font("Arial", Font.BOLD, 12));
		panelVolumen.add(lblVolumen, BorderLayout.NORTH);

		// JPanel para slider y etiqueta de porcentaje
		JPanel panelSlider = new JPanel(new BorderLayout(5, 0));

		// JSlider para controlar el volumen (0-100)
		JSlider sliderVolumen = new JSlider(0, 100, 70);
		sliderVolumen.setMajorTickSpacing(25);
		sliderVolumen.setMinorTickSpacing(5);
		sliderVolumen.setPaintTicks(true);
		sliderVolumen.setPaintLabels(true);

		// Etiqueta que muestra el porcentaje actual
		JLabel lblPorcentaje = new JLabel("70%");
		lblPorcentaje.setFont(new Font("Arial", Font.BOLD, 14));
		lblPorcentaje.setPreferredSize(new Dimension(50, 20));

		// Listener para actualizar el volumen en tiempo real
		sliderVolumen.addChangeListener(e -> {
			int valor = sliderVolumen.getValue();
			lblPorcentaje.setText(valor + "%");

			// Ajustar volumen si hay musica reproduciendose
			if (controladorMusica.estaReproduciendo()) {
				float volumen = valor / 100.0f;
				controladorMusica.ajustarVolumen(volumen);
			}
		});

		panelSlider.add(sliderVolumen, BorderLayout.CENTER);
		panelSlider.add(lblPorcentaje, BorderLayout.EAST);

		panelVolumen.add(panelSlider, BorderLayout.CENTER);

		panelPrincipal.add(panelVolumen);

		// Panel para botones
		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		JButton btnAceptar = new JButton("Aceptar");
		JButton btnCancelar = new JButton("Cancelar");

		panelBotones.add(btnAceptar);
		panelBotones.add(btnCancelar);

		dialogoMusica.getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		dialogoMusica.getContentPane().add(panelBotones, BorderLayout.SOUTH);

		// Listener del boton Aceptar
		btnAceptar.addActionListener(ev -> {
			String cancionSeleccionada = "";
			String rutaArchivo = "";

			// Determinar que opcion fue seleccionada y asignar la ruta
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
				
				// Detener la musica actual
				controladorMusica.detener();
				JOptionPane.showMessageDialog(dialogoMusica, "Música detenida", "Sin música",
						JOptionPane.INFORMATION_MESSAGE);
				dialogoMusica.dispose();
				return;
			}

			// Reproducir la musica seleccionada
			try {
				controladorMusica.reproducir(rutaArchivo);
				JOptionPane.showMessageDialog(dialogoMusica, "Reproduciendo: " + cancionSeleccionada,
						"Música seleccionada", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(dialogoMusica,
						"Error al reproducir: " + cancionSeleccionada
								+ "\nAsegúrate de que el archivo existe en la carpeta 'musica'",
						"Error", JOptionPane.ERROR_MESSAGE);
			}

			dialogoMusica.dispose();

		});

		btnCancelar.addActionListener(ev -> dialogoMusica.dispose());

		dialogoMusica.setVisible(true);

	}

	/**
	 * Abre una ventana para seleccionar el mapa de fondo
	 */
	private void abrirVentanaMapas() {
		// JDialog para seleccionar mapa
		JDialog dialogoMapas = new JDialog(this, "SELECCIONAR MAPA", true);
		dialogoMapas.setSize(600, 400);
		dialogoMapas.setLocationRelativeTo(this);
		dialogoMapas.getContentPane().setLayout(new BorderLayout(10, 10));

		// Panel principal
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BorderLayout(10, 10));
		panelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));

		// Titulo
		JLabel lblTitulo = new JLabel("Selecciona un mapa:");
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
		panelPrincipal.add(lblTitulo, BorderLayout.NORTH);

		// Panel central con opciones y vista previa
		JPanel panelCentral = new JPanel(new BorderLayout(10, 10));

		// Panel izquierdo con radio buttons
		JPanel panelOpciones = new JPanel();
		panelOpciones.setLayout(new BoxLayout(panelOpciones, BoxLayout.Y_AXIS));
		panelOpciones.setBorder(new EmptyBorder(10, 10, 10, 10));

		// ButtonGroup para los JRadioButtons
		ButtonGroup grupoMapas = new ButtonGroup();

		// Crear JRadioButtons con rutas a imagenes
		JRadioButton rbMapa1 = new JRadioButton("Lord Of The Rings", true);
		JRadioButton rbMapa2 = new JRadioButton("La Comarca");
		JRadioButton rbMapa3 = new JRadioButton("Mordor");
		JRadioButton rbMapa4 = new JRadioButton("Bosque Negro");
		JRadioButton rbMapa5 = new JRadioButton("Minas Tirith");

		// Añadir al grupo
		grupoMapas.add(rbMapa1);
		grupoMapas.add(rbMapa2);
		grupoMapas.add(rbMapa3);
		grupoMapas.add(rbMapa4);
		grupoMapas.add(rbMapa5);

		panelOpciones.add(rbMapa1);
		panelOpciones.add(Box.createRigidArea(new Dimension(0, 10)));
		panelOpciones.add(rbMapa2);
		panelOpciones.add(Box.createRigidArea(new Dimension(0, 10)));
		panelOpciones.add(rbMapa3);
		panelOpciones.add(Box.createRigidArea(new Dimension(0, 10)));
		panelOpciones.add(rbMapa4);
		panelOpciones.add(Box.createRigidArea(new Dimension(0, 10)));
		panelOpciones.add(rbMapa5);

		// Panel derecho para vista previa
		JLabel lblVistaPrevia = new JLabel();
		lblVistaPrevia.setPreferredSize(new Dimension(300, 250));
		lblVistaPrevia.setHorizontalAlignment(SwingConstants.CENTER);
		lblVistaPrevia.setText("Selecciona un mapa");

		// Listeners para actualizar vista previa
		java.awt.event.ActionListener actualizarVista = e -> {
			String rutaImagen = "";
			if (rbMapa1.isSelected()) {
				rutaImagen = "src/fondos/default.jpg";
			} else if (rbMapa2.isSelected()) {
				rutaImagen = "src/fondos/la_comarca.jpg";
			} else if (rbMapa3.isSelected()) {
				rutaImagen = "src/fondos/mordor.jpg";
			} else if (rbMapa4.isSelected()) {
				rutaImagen = "src/fondos/bosque_negro.jpg";
			} else if (rbMapa5.isSelected()) {
				rutaImagen = "src/fondos/minas_tirith.jpg";
			}

			// Cargar y mostrar la imagen
			try {
				javax.swing.ImageIcon icon = new javax.swing.ImageIcon(rutaImagen);
				java.awt.Image img = icon.getImage().getScaledInstance(280, 230, java.awt.Image.SCALE_SMOOTH);
				lblVistaPrevia.setIcon(new javax.swing.ImageIcon(img));
				lblVistaPrevia.setText("");
			} catch (Exception ex) {
				lblVistaPrevia.setIcon(null);
				lblVistaPrevia.setText("Imagen no disponible");
			}
		};

		rbMapa1.addActionListener(actualizarVista);
		rbMapa2.addActionListener(actualizarVista);
		rbMapa3.addActionListener(actualizarVista);
		rbMapa4.addActionListener(actualizarVista);
		rbMapa5.addActionListener(actualizarVista);

		panelCentral.add(panelOpciones, BorderLayout.WEST);
		panelCentral.add(lblVistaPrevia, BorderLayout.CENTER);

		panelPrincipal.add(panelCentral, BorderLayout.CENTER);

		// Panel para botones
		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		JButton btnAceptar = new JButton("Aceptar");
		JButton btnCancelar = new JButton("Cancelar");

		panelBotones.add(btnAceptar);
		panelBotones.add(btnCancelar);

		dialogoMapas.getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		dialogoMapas.getContentPane().add(panelBotones, BorderLayout.SOUTH);

		// Listener del boton Aceptar
		btnAceptar.addActionListener(ev -> {
			String mapaSeleccionado = "";
			String rutaImagen = "";

			if (rbMapa1.isSelected()) {
				mapaSeleccionado = "Default";
				rutaImagen = "src/fondos/default.jpg";
			} else if (rbMapa2.isSelected()) {
				mapaSeleccionado = "La Comarca";
				rutaImagen = "src/fondos/la_comarca.jpg";
			} else if (rbMapa3.isSelected()) {
				mapaSeleccionado = "Mordor";
				rutaImagen = "src/fondos/mordor.jpg";
			} else if (rbMapa4.isSelected()) {
				mapaSeleccionado = "Bosque Negro";
				rutaImagen = "src/fondos/bosque_negro.jpg";
			} else if (rbMapa5.isSelected()) {
				mapaSeleccionado = "Minas Tirith";
				rutaImagen = "src/fondos/minas_tirith.jpg";
			}

			// Aplicar el mapa seleccionado
			try {
				aplicarMapaDeFondo(rutaImagen);
				rutaMapaActual = rutaImagen;
				JOptionPane.showMessageDialog(dialogoMapas, "Mapa aplicado: " + mapaSeleccionado, "Mapa seleccionado",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(dialogoMapas,
						"Error al aplicar el mapa: " + mapaSeleccionado
								+ "\nAsegúrate de que el archivo existe en la carpeta 'imagenes'",
						"Error", JOptionPane.ERROR_MESSAGE);
			}

			dialogoMapas.dispose();
		});

		btnCancelar.addActionListener(ev -> dialogoMapas.dispose());

		dialogoMapas.setVisible(true);
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
	 * Carga un mapa por defecto al iniciar la aplicacion
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
	 * Escribe texto en el textArea con efecto de animacion
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

			// Mostrar el texto con animacion
			escribirTextoAnimado(resultadoBatalla);

			// Actualizar las listas despues de la batalla
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

}
