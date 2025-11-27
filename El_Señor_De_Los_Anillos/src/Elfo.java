
public class Elfo extends Heroe {

	public Elfo(String nombre, int p_Vida, int nivelArmadura, int p_Daño) {
		super(nombre, p_Vida, nivelArmadura, p_Daño);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Calcula los puntos de daño
	 * 
	 * @return puntos de daño
	 */
	@Override
	public int atacar(Personaje bestia) {
		int potencia = 0 + rabia(bestia);
		return potencia;

	}

	/**
	 * Añade +10 si la Bestia es un Orco
	 * 
	 * @param bestia
	 * @return 10 si la Bestia es un Orco, sino 0
	 */
	public int rabia(Personaje bestia) {
		return 0;
	}
}
