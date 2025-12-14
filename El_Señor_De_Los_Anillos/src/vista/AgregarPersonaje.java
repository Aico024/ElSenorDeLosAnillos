package vista;

import java.awt.BorderLayout;
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

public class AgregarPersonaje extends JDialog{
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
	     * @param parent ventana padre
	     * @param controlador controlador de la aplicacion
	     * @param callbackActualizar callback para actualizar las listas despues de agregar
	     */
	    public AgregarPersonaje(JFrame parent, App controlador, Runnable callbackActualizar) {
	        super(parent, "CREAR NUEVO PERSONAJE", true);
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
	        
	        // Panel principal con el formulario
	        JPanel panelPrincipal = new JPanel();
	        panelPrincipal.setLayout(new GridLayout(5, 2, 10, 10));
	        panelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));
	        
	        // Campos del formulario
	        JLabel lblNombre = new JLabel("Nombre:");
	        txtNombre = new JTextField();
	        
	        JLabel lblVida = new JLabel("Vida:");
	        txtVida = new JTextField("100");
	        
	        JLabel lblArmadura = new JLabel("Armadura:");
	        txtArmadura = new JTextField("30");
	        
	        JLabel lblTipo = new JLabel("Tipo:");
	        String[] tipos = {"Elfo", "Humano", "Hobbit", "Orco", "Trasgo"};
	        comboTipo = new JComboBox<>(tipos);
	        
	        JLabel lblBando = new JLabel("Bando:");
	        String[] bandos = {"Héroe", "Bestia"};
	        comboBando = new JComboBox<>(bandos);
	        
	        // Añadir componentes al panel
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
	        
	        // Panel de botones
	        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER ,10,10));
	        JButton btnAceptar = new JButton("Añadir");
	        JButton btnCancelar = new JButton("Cancelar");
	        
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
	     * Actualiza los tipos disponibles según el bando seleccionado
	     */
	    private void actualizarTiposDisponibles() {
	        String bandoSeleccionado = (String) comboBando.getSelectedItem();
	        comboTipo.removeAllItems();
	        
	        if (bandoSeleccionado.equals("Héroe")) {
	            comboTipo.addItem("Elfo");
	            comboTipo.addItem("Humano");
	            comboTipo.addItem("Hobbit");
	        } else {
	            comboTipo.addItem("Orco");
	            comboTipo.addItem("Trasgo");
	        }
	    }
	    
	    /**
	     * Agrega el personaje al ejército correspondiente
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
	                JOptionPane.showMessageDialog(this, 
	                    "El nombre no puede estar vacío", 
	                    "Error", 
	                    JOptionPane.ERROR_MESSAGE);
	                return;
	            }
	            
	            // Validar valores numericos
	            if (vida <= 0 || armadura < 0) {
	                JOptionPane.showMessageDialog(this, 
	                    "La vida debe ser mayor a 0 y la armadura no puede ser negativa", 
	                    "Error", 
	                    JOptionPane.ERROR_MESSAGE);
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
	                    "¡" + nombre + " ha sido añadido al ejército de " + 
	                    (esHeroe ? "Héroes" : "Bestias") + "!",
	                    "Éxito", 
	                    JOptionPane.INFORMATION_MESSAGE);
	                
	                // Cerrar el dialogo
	                dispose();
	            }
	            
	        } catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(this, 
	                "Los valores de Vida y Armadura deben ser números válidos", 
	                "Error", 
	                JOptionPane.ERROR_MESSAGE);
	        }
	    }
	    
	    /**
	     * Crea un personaje segun el tipo especificado
	     */
	    private Personaje crearPersonaje(String tipo, String nombre, int vida, int armadura, boolean esHeroe) {
	        // Validar que el tipo corresponda al bando
	        boolean esTipoHeroe = tipo.equals("Elfo") || tipo.equals("Humano") || tipo.equals("Hobbit");
	        boolean esTipoBestia = tipo.equals("Orco") || tipo.equals("Trasgo");
	        
	        if (esHeroe && !esTipoHeroe) {
	            JOptionPane.showMessageDialog(this,
	                "El tipo " + tipo + " no puede ser un Héroe. Selecciona Elfo, Humano o Hobbit.",
	                "Error de Tipo",
	                JOptionPane.ERROR_MESSAGE);
	            return null;
	        }
	        
	        if (!esHeroe && !esTipoBestia) {
	            JOptionPane.showMessageDialog(this,
	                "El tipo " + tipo + " no puede ser una Bestia. Selecciona Orco o Trasgo.",
	                "Error de Tipo",
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
	            case "Orco":
	                return new Orco(nombre, vida, armadura);
	            case "Trasgo":
	                return new Trasgo(nombre, vida, armadura);
	            default:
	                return null;
	        }
	    }
	}
