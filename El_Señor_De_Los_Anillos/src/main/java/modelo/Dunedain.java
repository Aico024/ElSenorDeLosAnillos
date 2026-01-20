package modelo;

/**
 * Representa a un Dúnedain, un tipo de Héroe con gran resistencia.
 *
 * Posee una habilidad de supervivencia única que le permite evitar la muerte una vez por batalla.
 */
public class Dunedain extends Heroe {
	private String mensajeRecibitHeroe = "";
	private boolean sobrevivio = false;


	public Dunedain(String nombre, int p_Vida, int nivelArmadura) {
		super(nombre, p_Vida, nivelArmadura);
		// TODO Auto-generated constructor stub
	}


	/**
	 * Recibe daño y reduce la vida del personaje.
	 *
	 * Implementa la habilidad de supervivencia: si el daño es mortal, sobrevive con 1 de vida una única vez.
	 *
	 * @param potenciaAtaque La potencia del ataque recibido.
	 * @return El daño real infligido después de aplicar armadura.
	 */
	@Override
	public int recibirDaño(int potenciaAtaque) {
		int dañoReal = potenciaAtaque - this.getNivelArmadura();

		// Habilidad de supervivencia: si va a morir y no ha usado la habilidad antes
		if (dañoReal < 0) {
			dañoReal = 0;
		}

	    int vidaRestante = this.getP_Vida() - dañoReal;


		if (vidaRestante <= 0 && !sobrevivio) {
	        this.setP_Vida(1);
	        sobrevivio = true;
	        mensajeRecibitHeroe = "El Dúnedain sobrevive con 1 de vida";
	        return dañoReal;
		} else {
			this.setP_Vida(this.getP_Vida() - dañoReal);
			mensajeRecibitHeroe = "";
		}
		this.setP_Vida(this.getP_Vida() - dañoReal);
		return dañoReal;
	}



	public String getUltimoMensaje() {
		return mensajeRecibitHeroe;
	}

}
