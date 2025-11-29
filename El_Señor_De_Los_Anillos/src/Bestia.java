
public abstract class Bestia extends Personaje {
	protected static final int MAX_DADO_BESTIA = 91;
	
	public Bestia(String nombre, int p_Vida, int nivelArmadura) {
		super(nombre, p_Vida, nivelArmadura);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Calcula los puntos de daño
	 * 
	 * @return puntos de daño
	 */
	@Override
	public int atacar(Personaje heroe) {
		int potencia = (int) (Math.random() * MAX_DADO_BESTIA);
		return potencia;

	}

}
