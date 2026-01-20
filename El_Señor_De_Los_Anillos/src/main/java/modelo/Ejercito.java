package modelo;
import java.util.ArrayList;

/**
 * Representa un conjunto de personajes que forman un bando en la batalla.
 *
 * Puede ser un ejército de "Buenos" o de "Malos".
 */
public class Ejercito {
	private String tipo;
	private ArrayList<Personaje> ejercito = new ArrayList<>();

	/**
	 * Constructor del ejército.
	 *
	 * @param Tipo El bando del ejército (ej. "Buenos").
	 * @param Ejercito Lista inicial de personajes.
	 */
	public Ejercito(String Tipo, ArrayList<Personaje> Ejercito) {
		super();
		this.tipo = Tipo;
		this.ejercito = Ejercito;
	}

	// Getters
	public String getTipo() {
		return tipo;
	}

	public ArrayList<Personaje> getEjercito() {
		return ejercito;
	}
}
