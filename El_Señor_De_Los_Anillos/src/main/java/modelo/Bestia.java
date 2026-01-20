package modelo;

/**
 * Clase abstracta que representa a una Bestia en el juego.
 *
 * Define el comportamiento base de ataque para las criaturas del ejército del mal.
 *
 * Las bestias atacan lanzando un único dado de hasta 91 caras.
 */
public abstract class Bestia extends Personaje {
	protected static final int MAX_DADO_BESTIA = 91;

	public Bestia(String nombre, int p_Vida, int nivelArmadura) {
		super(nombre, p_Vida, nivelArmadura);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Calcula los puntos de daño generados por la bestia.
	 *
	 * @param heroe El personaje que recibirá el ataque (no afecta al cálculo base).
	 * @return La potencia del ataque generada aleatoriamente.
	 */
	@Override
	public int atacar(Personaje heroe) {
		int potencia = (int) (Math.random() * MAX_DADO_BESTIA);
		return potencia;

	}

}
