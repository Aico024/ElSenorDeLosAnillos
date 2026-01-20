package modelo;

/**
 * Representa a un Ent, un tipo de Héroe con gran fuerza física.
 *
 * Posee la habilidad especial "Aplastar", que aumenta su daño contra enemigos debilitados.
 */
public class Ent extends Heroe{
	private String ultimoMensaje = "";

	public Ent(String nombre, int p_Vida, int nivelArmadura) {
		super(nombre, p_Vida, nivelArmadura);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Calcula los puntos de daño del Ent.
	 *
	 * Suma dos lanzamientos de dados y añade un bono si la bestia está débil.
	 *
	 * @param bestia El oponente al que se ataca.
	 * @return Puntos de daño totales.
	 */
	@Override
	public int atacar(Personaje bestia) {
		int dado1 = (int) (Math.random() * 101);
		int dado2 = (int) (Math.random() * 101);
		int aplasta = aplastar(bestia);
		int potencia = dado1 + dado2 + aplasta;
		return potencia;
	}

	/**
	 * Habilidad Especial: Aplastar.
	 *
	 * Suma +20 de daño si la vida de la bestia es igual o inferior a 45.
	 *
	 * @param bestia La bestia objetivo.
	 * @return 20 si se aplica la habilidad, de lo contrario 0.
	 */
	public int aplastar(Personaje bestia) {
		if (bestia.getP_Vida() <= 45) {
			ultimoMensaje = ("El Ent inflige +20 de P.Daño por bestia débil");
			return 20;
		} else {
			ultimoMensaje = ("");
			return 0;
		}
	}

	public String getUltimoMensaje() {
		return ultimoMensaje;
	}
}
