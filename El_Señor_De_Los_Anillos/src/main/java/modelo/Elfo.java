package modelo;

/**
 * Representa a un Elfo, un Héroe que siente un odio ancestral por los Orcos.
 *
 * Este odio se traduce en una mayor efectividad en combate contra ellos.
 */
public class Elfo extends Heroe {
	private String ultimoMensaje = "";

	/**
	 * Representa a un Elfo, un Héroe que posee un odio ancestral hacia los Orcos,
	 *
	 * lo que le otorga un bono de daño cuando lucha contra ellos.
	 */
	public Elfo(String nombre, int p_Vida, int nivelArmadura) {
		super(nombre, p_Vida, nivelArmadura);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Calcula el daño del ataque del Elfo.
	 *
	 * Incluye el bono de "Rabia" si el enemigo es un Orco.
	 *
	 * @param bestia El oponente al que se ataca.
	 * @return Puntos de daño totales.
	 */
	@Override
	public int atacar(Personaje bestia) {
		int dado1 = (int) (Math.random() * 101);
		int dado2 = (int) (Math.random() * 101);
		int rabia = rabia(bestia);
		int potencia = dado1 + dado2 + rabia;
		return potencia;

	}

	/**
	 * Habilidad Especial: Rabia.
	 *
	 * Añade +10 de daño si el oponente es de tipo Orco.
	 *
	 * @param bestia La bestia objetivo.
	 * @return 10 si es un Orco, de lo contrario 0.
	 */
	public int rabia(Personaje bestia) {
		if (bestia instanceof Orco) {
			ultimoMensaje = "El Elfo inflige + 10 por odio ancestral hacia Orco";
			return 10;
		} else {
			ultimoMensaje = "";
			return 0;
		}
	}

	public String getUltimoMensaje() {
		return ultimoMensaje;
	}
}
