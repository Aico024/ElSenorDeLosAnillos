package modelo;

/**
 * Representa a un Orco, una Bestia que utiliza su fuerza bruta
 *
 * para aprovechar la armadura del enemigo en su contra.
 */
public class Orco extends Bestia {
	private String ultimoMensaje = "";
	public Orco(String nombre, int p_Vida, int nivelArmadura) {
		super(nombre, p_Vida, nivelArmadura);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Calcula el daño sumando el dado y el bono por fuerza bruta.
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
	 * Habilidad Fuerza: Añade el 10% de la armadura del héroe al daño.
	 *
	 * @param heroe
	 * @return 10% de la armadura del heroe
	 */
	public int fuerza(Personaje heroe) {
		int aux = 0;
		aux = (heroe.getNivelArmadura() / 10);
		ultimoMensaje =("El orco hace + " + aux + " (10% de P.Armadura del heroe) por fuerza bruta");//solo en este turno
		return aux;
	}

	public String getUltimoMensaje() {
		return ultimoMensaje;
	}

}
