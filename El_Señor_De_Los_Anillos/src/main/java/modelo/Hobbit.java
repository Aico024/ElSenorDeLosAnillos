package modelo;

/**
 * Representa a un Hobbit. Es un Héroe que, debido a su naturaleza asustadiza,
 *
 * ve reducido su daño cuando se enfrenta específicamente a Trasgos.
 */
public class Hobbit extends Heroe {
	private String ultimoMensaje = "";

	public Hobbit(String nombre, int p_Vida, int nivelArmadura) {
		super(nombre, p_Vida, nivelArmadura);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Calcula los puntos de daño del Hobbit.
	 *
	 * A la suma de los dados se le resta el penalizador por miedo si corresponde.
	 *
	 * @param bestia El oponente al que se ataca.
	 * @return Puntos de daño finales.
	 */
	@Override
	public int atacar(Personaje bestia) {
		int dado1 = (int) (Math.random() * 101);
		int dado2 = (int) (Math.random() * 101);
		int potencia = dado1 + dado2 - miedo(bestia);
		return potencia;
	}

	/**
	 * Efecto Especial: Miedo.
	 *
	 * Resta 5 de daño si el oponente es un Trasgo.
	 *
	 * @param bestia La bestia objetivo.
	 * @return 5 si es un Trasgo, de lo contrario 0.
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
