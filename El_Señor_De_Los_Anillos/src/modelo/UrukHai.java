package modelo;

public class UrukHai extends Bestia{
	private String ultimoMensaje = "";


	public UrukHai(String nombre, int p_Vida, int nivelArmadura) {
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
		int dado1 = (int) (Math.random() * 91);
		int potencia = dado1 + consumir();
		return potencia;
	}

	/**
	 * Añade +10% de la armadura del heroe
	 * 
	 * @param heroe
	 * @return 10% de la armadura del heroe
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
