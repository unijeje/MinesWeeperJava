import java.util.Random;

import clases.Juego;

public class Principal {
	
	private static int casillasProvisional = 50;
	private static int minasProvisional = 5;
	private Juego partida;
	private Vista vista;

	public static void main(String[] args) {
		new Principal();
	}
	public Principal()
	{
		this.vista = new Vista(this);
		this.vista.setVisible(true);
		this.partida = new Juego(casillasProvisional, minasProvisional);
		this.vista.CrearTablero(this.partida);
	}
	
	public int getNumeroMinas()
	{
		return partida.getNumMinas();
	}
	
	public Juego getPartida()
	{
		return this.partida;
	}
	
	public int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

}
