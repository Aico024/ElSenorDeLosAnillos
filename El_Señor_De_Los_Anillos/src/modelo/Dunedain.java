package modelo;

public class Dunedain extends Heroe {
	private String mensajeRecibitHeroe = "";
	private boolean sobrevivio = false;

	public Dunedain(String nombre, int p_Vida, int nivelArmadura) {
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
		int dañoReal = potenciaAtaque - this.getNivelArmadura();
		if (dañoReal < 0) {
			dañoReal = 0;
		}
		
	    int vidaRestante = this.getP_Vida() - dañoReal;

	    
		if (vidaRestante <= 0 && sobrevivio == false) {
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
