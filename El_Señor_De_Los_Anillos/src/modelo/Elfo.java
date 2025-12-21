package modelo;

public class Elfo extends Heroe {
	private String ultimoMensaje = "";

	public Elfo(String nombre, int p_Vida, int nivelArmadura) {
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
		int dado1 = (int) (Math.random() * 101);
		int dado2 = (int) (Math.random() * 101);
		int rabia = rabia(bestia);
		int potencia = dado1 + dado2 + rabia;
		return potencia;

	}

	/**
	 * Añade +10 si la Bestia es un Orco
	 * 
	 * @param bestia
	 * @return 10 si la Bestia es un Orco, sino 0
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
