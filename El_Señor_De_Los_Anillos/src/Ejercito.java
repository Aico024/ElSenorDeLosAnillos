import java.util.ArrayList;

public class Ejercito {
	private String tipo;
	private ArrayList<Personaje> ejercito = new ArrayList<Personaje>();

	public Ejercito(String tipo, ArrayList<Personaje> ejercito) {
		super();
		this.tipo = tipo;
		this.ejercito = ejercito;
	}

	// Getters
	public String getTipo() {
		return tipo;
	}

	public ArrayList<Personaje> getEjercito() {
		return ejercito;
	}
}
