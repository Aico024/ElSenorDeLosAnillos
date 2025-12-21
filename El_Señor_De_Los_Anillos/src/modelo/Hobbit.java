package modelo;

public class Hobbit extends Heroe {
	private String ultimoMensaje = "";

	public Hobbit(String nombre, int p_Vida, int nivelArmadura) {
		super(nombre, p_Vida, nivelArmadura);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Calcula los puntos de daño
	 * 
	 * @return puntos de daño
	 */
	@Override
	public int atacar(Personaje bestia) {
		int dado1 = (int) (Math.random() * 101);
		int dado2 = (int) (Math.random() * 101);
		int potencia = dado1 + dado2 - miedo(bestia);
		return potencia;
	}

	/**
	 * Resta 5 de p_Daño si la bestia es un Trasgo
	 * 
	 * @param bestia
	 * @return 5 si la bestia es un Trasgo, sino 0
	 */
	public int miedo(Personaje bestia) {
		if (bestia instanceof Trasgo) {
			ultimoMensaje = ("El Hobbit inflige -5 de P.Daño por miedo a Trasgos");
			return 5;
		} else {
			ultimoMensaje = ("");
			return 0;
		}
	}

	public String getUltimoMensaje() {
		return ultimoMensaje;
	}
}
