package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.App;
import modelo.*;

public class AgregarPersonaje extends JDialog {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtVida;
	private JTextField txtArmadura;
	private JComboBox<String> comboTipo;
	private JComboBox<String> comboBando;
	private App controlador;
	private Runnable callbackActualizar;

	/**
	 * Constructor del dialogo
	 * 
	 * @param parent             ventana padre
	 * @param controlador        controlador de la aplicacion
	 * @param callbackActualizar callback para actualizar las listas despues de
	 *                           agregar
	 */
	public AgregarPersonaje(JFrame parent, App controlador, Runnable callbackActualizar) {
		super(parent, " ", true);
		this.controlador = controlador;
		this.callbackActualizar = callbackActualizar;

		inicializarComponentes();
		configurarListeners();

		setSize(400, 300);
		setLocationRelativeTo(parent);
	}

	/**
	 * Inicializa los componentes del dialogo
	 */
	private void inicializarComponentes() {
		getContentPane().setLayout(new BorderLayout(10, 10));
		getContentPane().setBackground(new Color(187, 173, 124));

		// Panel principal con el formulario
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new GridLayout(5, 2, 10, 10));
		panelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));
		panelPrincipal.setBackground(new Color(53, 53, 53));

		// Campos del formulario
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(new Color(187, 173, 124));
		txtNombre = new JTextField();
		txtNombre.setForeground(new Color(187, 173, 124));
		txtNombre.setBackground(new Color(53, 53, 53));

		JLabel lblVida = new JLabel("Vida:");
		lblVida.setForeground(new Color(187, 173, 124));
		txtVida = new JTextField("100");
		txtVida.setForeground(new Color(187, 173, 124));
		txtVida.setBackground(new Color(53, 53, 53));

		JLabel lblArmadura = new JLabel("Armadura:");
		lblArmadura.setForeground(new Color(187, 173, 124));
		txtArmadura = new JTextField("30");
		txtArmadura.setForeground(new Color(187, 173, 124));
		txtArmadura.setBackground(new Color(53, 53, 53));

		JLabel lblBando = new JLabel("Bando:");
		lblBando.setForeground(new Color(187, 173, 124));
		String[] bandos = { "Héroe", "Bestia" };
		comboBando = new JComboBox<>(bandos);
		comboBando.setForeground(new Color(187, 173, 124));
		comboBando.setBackground(new Color(53, 53, 53));
		comboBando.setBorder(null);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setForeground(new Color(187, 173, 124));
		String[] tipos = { "Elfo", "Humano", "Hobbit", "Orco", "Trasgo" };
		comboTipo = new JComboBox<>(tipos);
		comboTipo.setForeground(new Color(187, 173, 124));
		comboTipo.setBackground(new Color(53, 53, 53));
		comboTipo.setBorder(null);

		// Añadir componentes al panel
		panelPrincipal.add(lblNombre);
		panelPrincipal.add(txtNombre);
		panelPrincipal.add(lblVida);
		panelPrincipal.add(txtVida);
		panelPrincipal.add(lblArmadura);
		panelPrincipal.add(txtArmadura);
		panelPrincipal.add(lblBando);
		panelPrincipal.add(comboBando);
		panelPrincipal.add(lblTipo);
		panelPrincipal.add(comboTipo);
		

		// Panel de botones
		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		panelBotones.setBackground(new Color(53, 53, 53));

		JButton btnAceptar = new JButton("Añadir");
		btnAceptar.setForeground(new Color(187, 173, 124));
		btnAceptar.setBackground(new Color(53, 53, 53));
		btnAceptar.setBorder(null);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(new Color(187, 173, 124));
		btnCancelar.setBackground(new Color(53, 53, 53));
		btnCancelar.setBorder(null);

		panelBotones.add(btnAceptar);
		panelBotones.add(btnCancelar);

		// Añadir paneles al dialogo
		getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		getContentPane().add(panelBotones, BorderLayout.SOUTH);

		// Configurar listeners de botones
		btnAceptar.addActionListener(e -> agregarPersonaje());
		btnCancelar.addActionListener(e -> dispose());
	}

	/**
	 * Configura los listeners del dialogo
	 */
	private void configurarListeners() {
		// Listener para actualizar las opciones de tipo segun el bando
		comboBando.addActionListener(e -> actualizarTiposDisponibles());
	}

	/**
	 * Actualiza los tipos disponibles segun el bando seleccionado
	 */
	private void actualizarTiposDisponibles() {
		String bandoSeleccionado = (String) comboBando.getSelectedItem();
		comboTipo.removeAllItems();

		if (bandoSeleccionado.equals("Héroe")) {
			comboTipo.addItem("Elfo");
			comboTipo.addItem("Humano");
			comboTipo.addItem("Hobbit");
			comboTipo.addItem("Ent");
			comboTipo.addItem("Dunedain");

		} else {
			comboTipo.addItem("Orco");
			comboTipo.addItem("Trasgo");
			comboTipo.addItem("UrukHai");
			comboTipo.addItem("Nazgul");
			comboTipo.addItem("Troll");

		}
	}

	/**
	 * Agrega el personaje al ejercito correspondiente
	 */
	private void agregarPersonaje() {
		try {
			// Obtener datos del formulario
			String nombre = txtNombre.getText().trim();
			int vida = Integer.parseInt(txtVida.getText().trim());
			int armadura = Integer.parseInt(txtArmadura.getText().trim());
			String tipoSeleccionado = (String) comboTipo.getSelectedItem();
			boolean esHeroe = comboBando.getSelectedItem().equals("Héroe");

			// Validar nombre
			if (nombre.isEmpty()) {
				JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Validar valores numericos
			if (vida <= 0 || armadura < 0) {
				JOptionPane.showMessageDialog(this, "La vida debe ser mayor a 0 y la armadura no puede ser negativa",
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Crear el personaje
			Personaje nuevoPersonaje = crearPersonaje(tipoSeleccionado, nombre, vida, armadura, esHeroe);

			if (nuevoPersonaje != null) {
				// Añadir al ejercito correspondiente
				controlador.agregarPersonaje(nuevoPersonaje, esHeroe);

				// Actualizar las listas
				if (callbackActualizar != null) {
					callbackActualizar.run();
				}

				// Mostrar mensaje de exito
				JOptionPane.showMessageDialog(this,
						"¡" + nombre + " ha sido añadido al ejército de " + (esHeroe ? "Héroes" : "Bestias") + "!",
						"Éxito", JOptionPane.INFORMATION_MESSAGE);

				// Cerrar el dialogo
				dispose();
			}

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Los valores de Vida y Armadura deben ser números válidos", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Crea un personaje segun el tipo especificado
	 */
	private Personaje crearPersonaje(String tipo, String nombre, int vida, int armadura, boolean esHeroe) {
		// Validar que el tipo corresponda al bando
		boolean esTipoHeroe = tipo.equals("Elfo") || tipo.equals("Humano") || tipo.equals("Hobbit")|| tipo.equals("Ent")|| tipo.equals("Dunedain");
		boolean esTipoBestia = tipo.equals("Orco") || tipo.equals("Trasgo") || tipo.equals("UrukHai") || tipo.equals("Nazgul") || tipo.equals("Troll");

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

		// Crear el personaje segun el tipo
		switch (tipo) {
		case "Elfo":
			return new Elfo(nombre, vida, armadura);
		case "Humano":
			return new Humano(nombre, vida, armadura);
		case "Hobbit":
			return new Hobbit(nombre, vida, armadura);
		case "Ent":
			return new Ent(nombre, vida, armadura);
		case "Dunedain":
			return new Dunedain(nombre, vida, armadura);
		case "Orco":
			return new Orco(nombre, vida, armadura);
		case "Trasgo":
			return new Trasgo(nombre, vida, armadura);
		case "UrukHai":
			return new UrukHai(nombre, vida, armadura);
		case "Nazgul":
			return new Nazgul(nombre, vida, armadura);
		case "Troll":
			return new Troll(nombre, vida, armadura);
		default:
			return null;
		}
	}
}
