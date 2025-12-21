package modelo;

public class Ent extends Heroe{
	private String ultimoMensaje = "";

	public Ent(String nombre, int p_Vida, int nivelArmadura) {
		super(nombre, p_Vida, nivelArmadura);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Calcula los puntos de daño
	 * 
	 * @return puntos de daño
	 */
	@Override
	public int atacar(Personaje bestia) {
		int dado1 = (int) (Math.random() * 101);
		int dado2 = (int) (Math.random() * 101);
		int aplasta = aplastar(bestia);
		int potencia = dado1 + dado2 + aplasta;
		return potencia;
	}

	/**
	 * Suma +20 de p_Daño si los p_Vida de la bestia son menor o igual a 45
	 * 
	 * @param bestia
	 * @return 20, sino 0
	 */
	public int aplastar(Personaje bestia) {
		if (bestia.getP_Vida() <= 45) {
			ultimoMensaje = ("El Ent inflige +20 de P.Daño por bestia débil");
			return 20;
		} else {
			ultimoMensaje = ("");
			return 0;
		}
	}

	public String getUltimoMensaje() {
		return ultimoMensaje;
	}
}
