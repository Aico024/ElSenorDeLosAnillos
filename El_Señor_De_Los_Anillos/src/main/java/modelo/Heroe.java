package modelo;

/**
 * Clase abstracta que representa a un Héroe en el juego.
 *
 * Los héroes tienen la particularidad de atacar lanzando dos dados.
 */
public abstract class Heroe extends Personaje {
	protected static final int MAX_DADO_HEROE = 101;

	public Heroe(String nombre, int p_Vida, int nivelArmadura) {
		super(nombre, p_Vida, nivelArmadura);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Calcula los puntos de daño base de un héroe.
	 *
	 * Los héroes lanzan dos dados y se quedan con el valor más alto.
	 *
	 * @param bestia El oponente al que se ataca.
	 * @return El valor más alto entre los dos dados como potencia de ataque.
	 */
	@Override
	public int atacar(Personaje bestia) {
		int potencia = 0;
		int dado1 = (int) (Math.random() * MAX_DADO_HEROE);
		int dado2 = (int) (Math.random() * MAX_DADO_HEROE);
		if (dado1 > dado2) {
			potencia = dado1;
		}else {
			potencia = dado2;
		}
		return potencia;
	}

}
