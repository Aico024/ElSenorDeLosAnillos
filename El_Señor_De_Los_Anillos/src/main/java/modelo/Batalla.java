package modelo;
import java.util.ArrayList;


/**
 * Clase que gestiona el desarrollo de una batalla entre dos ejércitos.
 *
 * <p>Esta clase coordina los combates individuales entre personajes de dos
 * ejércitos (buenos y malos) y registra todos los eventos en un log detallado.
 * La batalla se desarrolla por turnos hasta que uno de los ejércitos es
 * completamente eliminado.</p>
 *
 * @author Andoni Carballo
 * @version 1.0.0
 * @since 2026-01-12
 */
public class Batalla {
	private Ejercito buenos;
	private Ejercito malos;
	private StringBuilder log;

	/**
     * Constructor de la clase Batalla.
     *
     * @param Buenos El ejército de los héroes
     * @param Malos El ejército de las bestias
     */
	public Batalla(Ejercito Buenos, Ejercito Malos) {

		/**
	     * Ejército de los héroes (buenos).
	     */
		this.buenos = Buenos;

		/**
	     * Ejército de las bestias (malos).
	     */
		this.malos = Malos;

		/**
	     * Registro de todos los eventos de la batalla.
	     */
		this.log = new StringBuilder();
	}

	/**
     * Obtiene el ejercito de heroes.
     *
     * @return El ejercito de heroes
     */
	public Ejercito getBuenos() {
		return buenos;
	}

	/**
     * Obtiene el ejercito de bestias.
     *
     * @return El ejercito de bestias
     */
	public Ejercito getMalos() {
		return malos;
	}

	/**
     * Obtiene el texto de la batalla.
     *
     * @return El texto de la batalla
     */
	public String getLog() {
		return log.toString();
	}

	/**
     * Inicia la batalla.
     *
     */
	public void iniBatalla() {
		int turno = 1;
		log.append("=== COMIENZA LA BATALLA ===\n\n");

		while (!buenos.getEjercito().isEmpty() && !malos.getEjercito().isEmpty()) {
			log.append("----------------------------------------------------------------------------------------------------------------\n");
			log.append("COMIENZA EL TURNO " + turno + ":\n");
			iniTurno();
			log.append("----------------------------------------------------------------------------------------------------------------\n");
			log.append("\n");
			turno++;

		}
		log.append("\n=== LA BATALLA HA TERMINADO ===\n");
	}

	/**
	 * Inicia un turno de batalla
	 */
	private void iniTurno() {
		ArrayList<Personaje> ejercitoBuenos = buenos.getEjercito();
		ArrayList<Personaje> ejercitoMalos = malos.getEjercito();

		// Determinar cuantos combates habra este turno
		int numCombates = Math.min(ejercitoBuenos.size(), ejercitoMalos.size());

		// Realizar todos los combates del turno
		for (int i = 0; i < numCombates; i++) {
			Personaje heroe = ejercitoBuenos.get(i);
			Personaje bestia = ejercitoMalos.get(i);

			realizarCombate(heroe, bestia);
		}

		// Eliminar personajes muertos
		eliminarMuertosDeEjercito(buenos.getEjercito());
		eliminarMuertosDeEjercito(malos.getEjercito());
		log.append("\n");
	}

	/**
	 * Realiza un combate individual entre dos personajes
	 *
	 * @param heroe  el heroe que combate
	 * @param bestia la bestia que combate
	 */
	private void realizarCombate(Personaje heroe, Personaje bestia) {
		log.append("-----------------------------------------------------------\n");
		String combate = ("\n Lucha entre " + heroe.getNombre() + " (Vida=" + heroe.getP_Vida() + " Armadura="
				+ heroe.getNivelArmadura() + ") y " + bestia.getNombre() + " (Vida=" + bestia.getP_Vida() + " Armadura="
				+ bestia.getNivelArmadura() + ")\n");
		log.append(combate);

		// El heroe ataca
		log.append("\n");
		String combateH =(heroe.getNombre() + " ataca a " + bestia.getNombre());
		log.append(combateH);
		int potenciaHeroe = heroe.atacar(bestia);
		String mensajeEspecialHeroe = obtenerMensajeEspecial(heroe);
		int danioHeroe = bestia.recibirDaño(potenciaHeroe);
		String mensajeRecibirBestia = obtenerMensajeRecibir(bestia);
		String resultadoH = " " + heroe.getNombre() + " saca " + potenciaHeroe + " y le quita " + danioHeroe
				+ " de vida a " + bestia.getNombre() + "\n";

		if (!mensajeEspecialHeroe.isEmpty()) {
			resultadoH += " " + mensajeEspecialHeroe;
		}
		log.append(resultadoH);
		if(!mensajeRecibirBestia.isEmpty()) {
			log.append(mensajeRecibirBestia);
		}


		// La bestia ataca (si aun esta viva)
		log.append("\n");
		if (bestia.estaVivo()) {
			String combateB = (bestia.getNombre() + " ataca a " + heroe.getNombre());
			log.append(combateB);
			int potenciaBestia = bestia.atacar(heroe);
			String mensajeEspecialBestia = obtenerMensajeEspecial(bestia);
			int danioBestia = heroe.recibirDaño(potenciaBestia);
			String mensajeRecibitHeroe = obtenerMensajeRecibir(heroe);
			String resultadoBestia = " " + bestia.getNombre() + " saca " + potenciaBestia + " y le quita " + danioBestia
					+ " de vida a " + heroe.getNombre() + "\n";
			if (!mensajeEspecialBestia.isEmpty()) {
				resultadoBestia += " " + mensajeEspecialBestia;
			}
			log.append(resultadoBestia);
			if(!mensajeRecibitHeroe.isEmpty()) {
				log.append(mensajeRecibitHeroe);
			}

		}
	}

	/**
	 * Obtiene el mensaje especial de un personaje si tiene uno
	 *
	 * @param personaje el personaje del que obtener el mensaje
	 * @return el mensaje especial o una cadena vacia
	 */
	private String obtenerMensajeEspecial(Personaje personaje) {
		if (personaje instanceof Elfo) {
			return ((Elfo) personaje).getUltimoMensaje() + "\n";
		} else if (personaje instanceof Humano) {
			return ((Humano) personaje).getUltimoMensaje() + "\n";
		} else if (personaje instanceof Hobbit) {
			return ((Hobbit) personaje).getUltimoMensaje() + "\n";
		} else if (personaje instanceof Ent) {
			return ((Ent) personaje).getUltimoMensaje() + "\n";
		} else if (personaje instanceof Orco) {
			return ((Orco) personaje).getUltimoMensaje() + "\n";
		} else if (personaje instanceof UrukHai) {
			return ((UrukHai) personaje).getUltimoMensaje() + "\n";
		} else if (personaje instanceof Nazgul) {
			return ((Nazgul) personaje).getUltimoMensaje() + "\n";
		}
		return "";
	}

	/**
	 * Obtiene el mensaje al recibir daño de un personaje si tiene uno
	 *
	 * @param personaje el personaje del que obtener el mensaje
	 * @return el mensaje especial o una cadena vacia
	 */
	private String obtenerMensajeRecibir(Personaje personaje) {
		if (personaje instanceof Dunedain) {
			return ((Dunedain) personaje).getUltimoMensaje() + "\n";
		} else if (personaje instanceof Troll) {
			return ((Troll) personaje).getUltimoMensaje() + "\n";
		}
		return "";
	}

	/**
	 * Metodo auxiliar que elimina los personajes muertos de un ejercito
	 *
	 * @param ejercito el ArrayList de personajes a revisar
	 */
	private void eliminarMuertosDeEjercito(ArrayList<Personaje> ejercito) {
		// Recorremos de atras hacia adelante para evitar problemas al eliminar
		for (int i = ejercito.size() - 1; i >= 0; i--) {
			Personaje p = ejercito.get(i);
			if (!p.estaVivo()) {
				String muerte = (" ¡Muere " + p.getTipo() + " " + p.getNombre() + "!");
				log.append(muerte);
				ejercito.remove(i);
			}
		}
	}
}