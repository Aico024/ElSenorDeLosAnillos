package modelo;
import java.util.ArrayList;

public class Batalla {
	private Ejercito buenos;
	private Ejercito malos;

	public Batalla(Ejercito Buenos, Ejercito Malos) {
		super();
		this.buenos = Buenos;
		this.malos = Malos;
	}

	// Getters
	public Ejercito getBuenos() {
		return buenos;
	}

	public Ejercito getMalos() {
		return malos;
	}

	/**
	 * Simula la batalla completa
	 */
	public void iniBatalla() {
		int turno = 1;
		System.out.println("=== COMIENZA LA BATALLA ===\n");

		while (!buenos.getEjercito().isEmpty() && !malos.getEjercito().isEmpty()) {
			System.out.println("COMIENZA EL TURNO  " + turno + ":");
			iniTurno();
			System.out.println();
			turno++;
			
		}
	}

	/**
	 * Inicia un turno de batalla
	 */
	private void iniTurno() {
		ArrayList<Personaje> ejercitoBuenos = buenos.getEjercito();
		ArrayList<Personaje> ejercitoMalos = malos.getEjercito();

		// Determinar cuántos combates habrá este turno
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
		System.out.println();
	}

	/**
	 * Realiza un combate individual entre dos personajes
	 * 
	 * @param heroe  el héroe que combate
	 * @param bestia la bestia que combate
	 */
	private void realizarCombate(Personaje heroe, Personaje bestia) {
		System.out.println("\n Lucha entre " + heroe.getNombre() + " (Vida=" + heroe.getP_Vida() + " Armadura="
				+ heroe.getNivelArmadura() + ") y " + bestia.getNombre() + " (Vida=" + bestia.getP_Vida() + " Armadura="
				+ bestia.getNivelArmadura() + ")");

		// El héroe ataca
		System.out.println("--------------------------------------------");
		System.out.println(heroe.getNombre() + " ataca a " + bestia.getNombre());
		int potenciaHeroe = heroe.atacar(bestia);
		int danioHeroe = bestia.recibirDaño(potenciaHeroe);
		System.out.println(" " + heroe.getNombre() + " saca " + potenciaHeroe + " y le quita " + danioHeroe
				+ " de vida a " + bestia.getNombre());
		System.out.println("--------------------------------------------");


		// La bestia ataca (si aún está viva)
		System.out.println("--------------------------------------------");
		System.out.println(bestia.getNombre() + " ataca a " + heroe.getNombre());
		if (bestia.estaVivo()) {
			int potenciaBestia = bestia.atacar(heroe);
			int danioBestia = heroe.recibirDaño(potenciaBestia);
			System.out.println(" " + bestia.getNombre() + " saca " + potenciaBestia + " y le quita " + danioBestia
					+ " de vida a " + heroe.getNombre());
			System.out.println("--------------------------------------------");

		}
	}

	/**
	 * Método auxiliar que elimina los personajes muertos de un ejército
	 * 
	 * @param ejercito el ArrayList de personajes a revisar
	 */
	private void eliminarMuertosDeEjercito(ArrayList<Personaje> ejercito) {
		// Recorremos de atrás hacia adelante para evitar problemas al eliminar
		for (int i = ejercito.size() - 1; i >= 0; i--) {
			Personaje p = ejercito.get(i);
			if (!p.estaVivo()) {
				System.out.println(" ¡Muere " + p.getTipo() + " " + p.getNombre() + "!");
				ejercito.remove(i);
			}
		}
	}
}