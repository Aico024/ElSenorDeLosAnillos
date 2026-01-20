package controlador;

import java.util.ArrayList;

import modelo.Batalla;
import modelo.Ejercito;
import modelo.Elfo;
import modelo.Hobbit;
import modelo.Humano;
import modelo.Orco;
import modelo.Personaje;
import modelo.Trasgo;

/**
 * Controlador principal de la aplicación.
 *
 * <p>Esta clase gestiona la lógica de negocio de la aplicación, incluyendo
 * la inicialización de los ejércitos, la gestión de personajes y la
 * ejecución de batallas.</p>
 *
 * @author Andoni Carballo
 * @version 1.0.0
 * @since 2026-01-12
 */
public class App {

	/**
     * Ejército de héroes.
     */
	private Ejercito ejercitoHeroes;

	/**
     * Ejército de bestias.
     */
	private Ejercito ejercitoBestias;

	/**
     * Instancia de la batalla actual.
     */
	private Batalla batalla;

	/**
     * Constructor que inicializa la aplicación con ejércitos predeterminados.
     */
	public App() {
		inicializarEjercitos();
	}

	/**
	 * Inicializa los ejercitos con personajes predeterminados
	 */
	public void inicializarEjercitos() {
		// Crear el ejercito de heroes
		ArrayList<Personaje> heroes = new ArrayList<>();
		heroes.add(new Elfo("Legolas", 150, 30));
		heroes.add(new Humano("Aragorn", 150, 50));
		heroes.add(new Humano("Boromir", 100, 60));
		heroes.add(new Humano("Gandalf", 300, 30));
		heroes.add(new Hobbit("Frodo", 20, 10));

		this.ejercitoHeroes = new Ejercito("Heroes", heroes);

		// Crear el ejercito de bestias
		ArrayList<Personaje> bestias = new ArrayList<>();
		bestias.add(new Orco("Lurtz", 200, 60));
		bestias.add(new Orco("Shagrat", 220, 50));
		bestias.add(new Trasgo("Uglúk", 120, 30));
		bestias.add(new Trasgo("Mauhúr", 100, 30));

		this.ejercitoBestias = new Ejercito("Bestias", bestias);
	}

	/**
	 * Obtiene el ejercito de heroes
	 */
	public Ejercito getEjercitoHeroes() {
		return ejercitoHeroes;
	}

	/**
	 * Obtiene el ejercito de bestias
	 */
	public Ejercito getEjercitoBestias() {
		return ejercitoBestias;
	}

	/**
	 * Inicia la batalla y retorna el resultado como String
	 */
	public String iniciarBatalla() {
		StringBuilder resultado = new StringBuilder();
		batalla = new Batalla(ejercitoHeroes, ejercitoBestias);
	    batalla.iniBatalla();
	    resultado.append(batalla.getLog());
	    resultado.append(obtenerEstadisticasFinales());

	    return resultado.toString();
	}

	/**
	 * Obtiene las estadisticas finales de la batalla
	 */
	public String obtenerEstadisticasFinales() {
		StringBuilder resultado = new StringBuilder();

		resultado.append("\n=== ESTADISTICAS FINALES ===\n");
		resultado.append("Heroes supervivientes: " + ejercitoHeroes.getEjercito().size() + "\n");
		for (Personaje p : ejercitoHeroes.getEjercito()) {
			resultado.append(" - " + p.getNombre() + " (" + p.getTipo() + ") - Vida restante: " + p.getP_Vida() + "\n");
		}

		resultado.append("\nBestias supervivientes: " + ejercitoBestias.getEjercito().size() + "\n");
		for (Personaje p : ejercitoBestias.getEjercito()) {
			resultado.append(" - " + p.getNombre() + " (" + p.getTipo() + ") - Vida restante: " + p.getP_Vida() + "\n");
		}

		return resultado.toString();
	}

	/**
	 * Añade un personaje al ejercito correspondiente
	 */
	public void agregarPersonaje(Personaje personaje, boolean esHeroe) {
		if (esHeroe) {
			ejercitoHeroes.getEjercito().add(personaje);
		} else {
			ejercitoBestias.getEjercito().add(personaje);
		}
	}

}
