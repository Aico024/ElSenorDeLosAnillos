
public class Trasgo extends Bestia {

	public Trasgo(String nombre, int p_Vida, int nivelArmadura, int p_Daño) {
		super(nombre, p_Vida, nivelArmadura, p_Daño);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Calcula los puntos de daño
	 * 
	 * @return puntos de daño
	 */
	@Override
	public int atacar(Personaje heroe) {
		int dado1 = (int) (Math.random() * 91);
		int potencia = dado1 + miedo(heroe);
		return potencia;
	}

	/**
	 * Añade +5 si el Heroe es un Hobbit
	 * 
	 * @param heroe
	 * @return 5 si el Heroe es un Hobbit, sino 0
	 */
	public int miedo(Personaje heroe) {
		return 0;
	}

}
