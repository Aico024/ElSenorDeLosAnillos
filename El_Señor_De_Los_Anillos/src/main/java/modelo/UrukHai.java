package modelo;

/**
 * Representa a un Uruk-Hai, una versión mejorada de los Orcos.
 *
 * Pueden consumir su propia vitalidad para potenciar sus ataques.
 */
public class UrukHai extends Bestia{
	private String ultimoMensaje = "";


	public UrukHai(String nombre, int p_Vida, int nivelArmadura) {
		super(nombre, p_Vida, nivelArmadura);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Calcula el daño sumando el dado y el bono por consumir vida.
	 *
	 * @return puntos de daño
	 */
	@Override
	public int atacar(Personaje heroe) {
		int dado1 = (int) (Math.random() * 91);
		int potencia = dado1 + consumir();
		return potencia;
	}

	/**
	 * Habilidad Consumir: Pierde 5 de vida para ganar +5 de potencia de ataque.
	 *
	 * @return +5 de potencia de ataque
	 */
	public int consumir() {
		if (this.getP_Vida() < 5) {
			this.recibirDaño(5);
			ultimoMensaje = "El Huruk-Hai consume 5 puntos de vida y los comvierte en + 5 de vida";
			return 5;
		} else {
			return 0;
		}
	}

	public String getUltimoMensaje() {
		return ultimoMensaje;
	}


}
