package modelo;

public class Troll extends Bestia {
	private String mensajeRecibitBestia = "";

	public Troll(String nombre, int p_Vida, int nivelArmadura) {
		super(nombre, p_Vida, nivelArmadura);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Recibe daño y reduce la vida del personaje 
	 * 
	 * @param potenciaAtaque la potencia del ataque recibido
	 * @return el daño real infligido
	 */
	@Override
	public int recibirDaño(int potenciaAtaque) {
		int dañoReal = potenciaAtaque - this.getNivelArmadura() +3;
		mensajeRecibitBestia = "El Troll recibe -3 de daño por armadura resistente";
		if (dañoReal < 0) {
			dañoReal = 0;
		}
		this.setP_Vida(this.getP_Vida() - dañoReal);
		return  dañoReal;
	}
	public String getUltimoMensaje() {
		return mensajeRecibitBestia;
	}
}
