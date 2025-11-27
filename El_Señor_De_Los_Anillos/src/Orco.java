
public class Orco extends Bestia {

	public Orco(String nombre, int p_Vida, int nivelArmadura, int p_Daño) {
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
		int potencia = dado1 + fuerza(heroe);
		return potencia;
	}

	/**
	 * Añade +10% de la armadura del heroe
	 * 
	 * @param heroe
	 * @return 10% de la armadura del heroe
	 */
	public int fuerza(Personaje heroe) {
		return 0;
	}

}
