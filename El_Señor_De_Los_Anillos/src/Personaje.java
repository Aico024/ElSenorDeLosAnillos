
public abstract class Personaje {

	private String nombre;
	private int p_Vida;
	private int nivelArmadura;

	public Personaje(String Nombre, int P_Vida, int NivelArmadura) {
		super();
		nombre = Nombre;
		p_Vida = P_Vida;
		nivelArmadura = NivelArmadura;
	}

	// Getters
	public String getNombre() {
		return nombre;
	}

	public int getP_Vida() {
		return p_Vida;
	}

	public int getNivelArmadura() {
		return nivelArmadura;
	}
	
	// Setter necesario para modificar la vida
	public void setP_Vida(int P_Vida) {
		p_Vida = P_Vida;
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
		int dañoReal = potenciaAtaque - this.nivelArmadura;
		if (dañoReal < 0) {
			dañoReal = 0;
		}
		this.p_Vida -= dañoReal;
		return dañoReal;
	}

	/**
	 * Verifica si el personaje está vivo
	 * 
	 * @return true si el personaje está vivo, false si no
	 */
	public boolean estaVivo() {
		return this.p_Vida > 0;
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