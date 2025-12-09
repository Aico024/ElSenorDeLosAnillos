package modelo;

public abstract class Heroe extends Personaje {
    protected static final int MAX_DADO_HEROE = 101;

	public Heroe(String nombre, int p_Vida, int nivelArmadura) {
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
		int dado1 = (int) (Math.random() * MAX_DADO_HEROE);
		int dado2 = (int) (Math.random() * MAX_DADO_HEROE);
		int potencia = dado1 + dado2;
		return potencia;
	}

}
