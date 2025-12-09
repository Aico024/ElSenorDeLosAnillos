package modelo;
import java.util.ArrayList;

public class Ejercito {
	private String tipo;
	private ArrayList<Personaje> ejercito = new ArrayList<Personaje>();

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
