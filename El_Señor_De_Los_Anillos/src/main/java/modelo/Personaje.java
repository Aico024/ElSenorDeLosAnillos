package modelo;

/**
 * Clase abstracta base que representa un personaje en el juego.
 *
 * <p>Esta clase define los atributos y comportamientos comunes a todos los
 * personajes del juego, tanto héroes como bestias. Cada personaje tiene
 * puntos de vida, nivel de armadura y puede atacar y recibir daño.</p>
 *
 * @author Andoni Carballo
 * @version 1.0.0
 * @since 2026-01-12
 */
public abstract class Personaje {

	/**
     * Nombre del personaje.
     */
	private String nombre;

	 /**
     * Puntos de vida del personaje.
     */
	private int p_Vida;

	 /**
     * Nivel de armadura del personaje que reduce el daño recibido.
     */
	private int nivelArmadura;

	/**
     * Constructor de la clase Personaje.
     *
     * @param Nombre El nombre del personaje
     * @param P_Vida Los puntos de vida iniciales del personaje
     * @param NivelArmadura El nivel de armadura del personaje
     */
	public Personaje(String Nombre, int P_Vida, int NivelArmadura) {
		super();
		nombre = Nombre;
		p_Vida = P_Vida;
		nivelArmadura = NivelArmadura;
	}

	 /**
     * Obtiene el nombre del personaje.
     *
     * @return El nombre del personaje
     */
	public String getNombre() {
		return nombre;
	}

	/**
     * Obtiene los puntos de vida actuales del personaje.
     *
     * @return Los puntos de vida del personaje
     */
	public int getP_Vida() {
		return p_Vida;
	}

	/**
     * Obtiene el nivel de armadura del personaje.
     *
     * @return El nivel de armadura
     */
	public int getNivelArmadura() {
		return nivelArmadura;
	}

	/**
     * Establece los puntos de vida del personaje.
     *
     * @param P_Vida Los nuevos puntos de vida
     */
	public void setP_Vida(int P_Vida) {
		p_Vida = P_Vida;
	}

	/**
     * Calcula el daño que inflige el personaje al atacar.
     *
     * <p>Este método debe ser implementado por las subclases para definir
     * el comportamiento específico de ataque de cada tipo de personaje.</p>
     *
     * @param objetivo El personaje que recibe el ataque
     * @return Los puntos de daño calculados
     */
	public abstract int atacar(Personaje objetivo);

	 /**
     * Procesa el daño recibido por el personaje.
     *
     * <p>El daño real se calcula restando el nivel de armadura a la potencia
     * del ataque. Si el resultado es negativo, no se inflige daño.</p>
     *
     * @param potenciaAtaque La potencia del ataque recibido
     * @return El daño real infligido despues de aplicar la armadura
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
     * Verifica si el personaje esta vivo.
     *
     * @return true si el personaje tiene puntos de vida mayores a 0, false en caso contrario
     */
	public boolean estaVivo() {
		return this.p_Vida > 0;
	}

	/**
     * Obtiene el tipo de personaje basado en su clase.
     *
     * @return El nombre simple de la clase del personaje
     */
	public String getTipo() {
		return this.getClass().getSimpleName();
	}
}