
public abstract class Personaje {

	private String Nombre;
	private int P_Vida;
	private int NivelArmadura;

	public Personaje(String nombre, int p_Vida, int nivelArmadura) {
		super();
		Nombre = nombre;
		P_Vida = p_Vida;
		NivelArmadura = nivelArmadura;
	}

	// Getters
	public String getNombre() {
		return Nombre;
	}

	public int getP_Vida() {
		return P_Vida;
	}

	public int getNivelArmadura() {
		return NivelArmadura;
	}
	
	// Setter necesario para modificar la vida
	public void setP_Vida(int p_Vida) {
		P_Vida = p_Vida;
	}

	/**
	 * Calcula los puntos de daño
	 * 
	 * @return puntos de daño
	 */
	public abstract int atacar(Personaje objetivo);

	/**
	 * Recibe daño y reduce la vida del personaje
	 * 
	 * @param potenciaAtaque la potencia del ataque recibido
	 * @return el daño real infligido
	 */
	public int recibirDaño(int potenciaAtaque) {
		int dañoReal = potenciaAtaque - this.NivelArmadura;
		if (dañoReal < 0) {
			dañoReal = 0;
		}
		this.P_Vida -= dañoReal;
		return dañoReal;
	}

	/**
	 * Verifica si el personaje está vivo
	 * 
	 * @return true si el personaje está vivo, false si no
	 */
	public boolean estaVivo() {
		return this.P_Vida > 0;
	}

	/**
	 * Obtiene el tipo de personaje (para mostrar en batalla)
	 * 
	 * @return el nombre de la clase del personaje
	 */
	public String getTipo() {
		return this.getClass().getSimpleName();
	}
}