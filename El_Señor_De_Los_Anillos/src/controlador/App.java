package controlador;
import java.util.ArrayList;
import modelo.*;

public class App {
public static void main(String[] args) {
	
	// Crear el ejército de héroes
	ArrayList<Personaje> heroes = new ArrayList<Personaje>();
	heroes.add(new Elfo("Legolas", 150, 30));
	heroes.add(new Humano("Aragorn", 150, 50));
	heroes.add(new Humano("Boromir", 100, 60));
	heroes.add(new Humano("Gandalf", 300, 30));
	heroes.add(new Hobbit("Frodo", 20, 10));
	
	Ejercito ejercitoHeroes = new Ejercito("Héroes", heroes);
	
	// Crear el ejército de bestias
	ArrayList<Personaje> bestias = new ArrayList<Personaje>();
	bestias.add(new Orco("Lurtz", 200, 60));
	bestias.add(new Orco("Shagrat", 220, 50));
	bestias.add(new Trasgo("Uglúk", 120, 30));
	bestias.add(new Trasgo("Mauhúr", 100, 30));
	
	Ejercito ejercitoBestias = new Ejercito("Bestias", bestias);
	
	// Crear y ejecutar la batalla
	Batalla batalla = new Batalla(ejercitoHeroes, ejercitoBestias);
	batalla.iniBatalla();
	System.out.println("\n=== LA BATALLA TERMINO ===");

	// Mostrar estadísticas finales
	System.out.println("\n=== ESTADÍSTICAS FINALES ===");
	System.out.println("Héroes supervivientes: " + ejercitoHeroes.getEjercito().size());
	for (Personaje p : ejercitoHeroes.getEjercito()) {
		System.out.println(" - " + p.getNombre() + " (" + p.getTipo() + 
				") - Vida restante: " + p.getP_Vida());
	}
	
	System.out.println("\nBestias supervivientes: " + ejercitoBestias.getEjercito().size());
	for (Personaje p : ejercitoBestias.getEjercito()) {
		System.out.println(" - " + p.getNombre() + " (" + p.getTipo() + 
				") - Vida restante: " + p.getP_Vida());
	}
}
}