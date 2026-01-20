package modelo;

/**
 * Representa a un Humano dentro del ejército de los Héroes.
 *
 * Posee la habilidad especial "Valentía", que le permite infligir más daño
 *
 * cuando se enfrenta a oponentes formidables con mucha salud.
 */
public class Humano extends Heroe {
	private String ultimoMensaje = "";


	public Humano(String nombre, int p_Vida, int nivelArmadura) {
		super(nombre, p_Vida, nivelArmadura);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Calcula los puntos de daño del humano.
	 *
	 * Incluye el cálculo de dos dados de héroe más el bono por valentía.
	 *
	 * @param bestia El oponente al que se ataca.
	 * @return Puntos de daño totales.
	 */
	@Override
	public int atacar(Personaje bestia) {
		int dado1 = (int) (Math.random() * 101);
		int dado2 = (int) (Math.random() * 101);
		int potencia = dado1 + dado2 + valentia(bestia);
		return potencia;
	}

	/**
	 * Habilidad Especial: Valentía.
	 *
	 * Suma 15 de daño si los puntos de vida de la bestia son mayores o iguales a 80.
	 *
	 * @param bestia La bestia objetivo.
	 * @return 15 si se activa la valentía, de lo contrario 0.
	 */
	public int valentia(Personaje bestia) {
		if (bestia.getP_Vida() >= 80) {
			ultimoMensaje = ("El Humano inflige +15 de P.Daño por valentia ante fuertes");
			return 15;
		} else {
			ultimoMensaje = ("");
			return 0;
		}
	}

	public String getUltimoMensaje() {
		return ultimoMensaje;
	}
}
