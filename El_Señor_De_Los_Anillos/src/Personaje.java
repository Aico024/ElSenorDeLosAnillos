
public abstract class Personaje {

	private String Nombre;
	private int P_Vida;
	private int NivelArmadura;
	private int P_Daño;

	public Personaje(String nombre, int p_Vida, int nivelArmadura, int p_Daño) {
		super();
		Nombre = nombre;
		P_Vida = p_Vida;
		NivelArmadura = nivelArmadura;
		P_Daño = p_Daño;
	}

	public String getNombre() {
		return Nombre;
	}

	public int getP_Vida() {
		return P_Vida;
	}

	public int getNivelArmadura() {
		return NivelArmadura;
	}

	public int getP_Daño() {
		return P_Daño;
	}

}