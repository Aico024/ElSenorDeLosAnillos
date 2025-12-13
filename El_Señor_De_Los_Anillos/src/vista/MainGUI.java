package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.Box;
import javax.swing.BoxLayout;
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
		textArea.setText(
				"=== SISTEMA LISTO ===\n\nPrepara tus ejércitos y presiona '¡¡¡ A LUCHAR !!!' para comenzar la batalla.\n");

	}

	/**
	 * Configura y asigna todos los ActionListeners a los componentes de la GUI.
	 */
	private void configurarListeners() {
		// Listener para el botón de Luchar
		luchar.addActionListener(e -> iniciarBatalla());

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
	}

	/**
	 * Abre una ventana para añadir un nuevo personaje
	 */
	private void abrirVentanaAñadirPersonaje() {
		// JDialog para añadir personaje
		JDialog dialogoNuevo = new JDialog(this, "CREAR NUEVO PERSONAJE", true);
		dialogoNuevo.setSize(400, 300);
		dialogoNuevo.setLocationRelativeTo(this);
		dialogoNuevo.setLayout(new BorderLayout(10, 10));

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
		dialogoNuevo.add(panelPrincipal, BorderLayout.CENTER);
		dialogoNuevo.add(panelBotones, BorderLayout.SOUTH);

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
