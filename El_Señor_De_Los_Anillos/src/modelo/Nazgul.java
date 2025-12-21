package modelo;

public class Nazgul extends Bestia {
	private String ultimoMensaje = "";

	public Nazgul(String nombre, int p_Vida, int nivelArmadura) {
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
		int potencia = dado1 + 5;
		ultimoMensaje =("-5 de nivel de Armadura del contrincante por miedo");//solo se aplica en este turno
		return potencia;
	}

	
	
	public String getUltimoMensaje() {
		return ultimoMensaje;
	}

}
