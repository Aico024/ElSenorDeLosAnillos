package modelo;

public class Orco extends Bestia {
	private String ultimoMensaje = "";
	public Orco(String nombre, int p_Vida, int nivelArmadura) {
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
		int potencia = dado1 + fuerza(heroe);
		return potencia;
	}

	/**
	 * Añade +10% de la armadura del heroe
	 * 
	 * @param heroe
	 * @return 10% de la armadura del heroe
	 */
	public int fuerza(Personaje heroe) {
		int aux = 0;
		aux = (heroe.getNivelArmadura() / 10);
		ultimoMensaje =(" + " + aux + " (10% de P.Armadura del heroe) por fuerza bruta");
		return aux;
	}
	
	public String getUltimoMensaje() {
		return ultimoMensaje;
	}

}
