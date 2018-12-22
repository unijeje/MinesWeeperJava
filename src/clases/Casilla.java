package clases;

import javax.swing.JButton;

public class Casilla {
	private boolean bomba;
	private int rodeado;
	private JButton btnCasilla;
	private boolean activada;
	
	public Casilla(boolean bomba, int rodeado, JButton btnCasilla)
	{
		this.bomba = bomba;
		this.rodeado = rodeado;
		this.btnCasilla = btnCasilla;
		this.activada = false;
	}
	
	public boolean colocarBomba()
	{
		boolean res = false;
		if(!this.bomba)
		{
			this.bomba = true;
			res = true;
		}
		return res;
	}

	public boolean isBomba() {
		return bomba;
	}

	public void setBomba(boolean bomba) {
		this.bomba = bomba;
	}

	public int getRodeado() {
		return rodeado;
	}

	public void setRodeado(int rodeado) {
		this.rodeado = rodeado;
	}

	public JButton getBtnCasilla() {
		return btnCasilla;
	}

	public void setBtnCasilla(JButton btnCasilla) {
		this.btnCasilla = btnCasilla;
	}

	public boolean getIsActivada() {
		return activada;
	}

	public void setActivada(boolean activada) {
		this.activada = activada;
	}
	

}
