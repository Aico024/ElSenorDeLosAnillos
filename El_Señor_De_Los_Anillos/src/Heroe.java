
public abstract class Heroe extends Personaje {
	private String Nombre;
	private int P_Vida;
	private int NivelArmadura;
	private int P_Daño;

	public Heroe(String nombre, int p_Vida, int nivelArmadura, int p_Daño) {
		super(nombre, p_Vida, nivelArmadura, p_Daño);
		// TODO Auto-generated constructor stub
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
