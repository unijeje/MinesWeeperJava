package clases;

public class Juego {
	
	private boolean activo;
	private int numCasillas;
	private int numFilas;
	private int numColumnas;
	private int numMinas;
	private int tiempo;
	private boolean primerClick;
	private Casilla[][] casillas;
	private int casillasClicked;
	
	
	public Juego(int numCasillas, int numMinas)
	{
		//System.out.println("Constructor llamado");
		this.numCasillas = numCasillas;
		this.numMinas = numMinas;
		this.numFilas= (int) Math.sqrt(this.numCasillas);
		this.numColumnas = (int) Math.sqrt(this.numCasillas);
		tiempo = 0;
		activo = true;
		primerClick = true;
		casillas = new Casilla[this.numFilas][this.numColumnas];
		casillasClicked = 0;
	}


	public boolean isActivo() {
		return activo;
	}


	public void setActivo(boolean activo) {
		this.activo = activo;
	}


	public int getNumCasillas() {
		return numCasillas;
	}


	public void setNumCasillas(int numCasillas) {
		this.numCasillas = numCasillas;
	}


	public int getNumMinas() {
		return numMinas;
	}


	public void setNumMinas(int numMinas) {
		this.numMinas = numMinas;
	}


	public int getTiempo() {
		return tiempo;
	}


	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}


	public int getNumFilas() {
		return numFilas;
	}


	public int getNumColumnas() {
		return numColumnas;
	}


	public boolean isPrimerClick() {
		return primerClick;
	}


	public void setPrimerClick(boolean primerClick) {
		this.primerClick = primerClick;
	}


	public Casilla[][] getCasillas() {
		return casillas;
	}


	public void setCasillas(Casilla[][] casillas) {
		this.casillas = casillas;
	}


	public void setNumFilas(int numFilas) {
		this.numFilas = numFilas;
	}


	public void setNumColumnas(int numColumnas) {
		this.numColumnas = numColumnas;
	}


	public void calcularRodeadoCasilla(Casilla casilla) 
	{
		
		for(int i=0;i<this.casillas.length;i++)
		{
			for(int j=0;j<this.casillas[0].length;j++)
			{
				if(casilla == this.casillas[i][j])
				{
					
					
					
					int maxFila = this.casillas.length-1;
					int maxCol = this.casillas[0].length-1;
					boolean maximoFila = j < maxFila;
					boolean maximoCol = i < maxCol;
//					System.out.println("pos: "+i+" - "+j);
//					System.out.println("maximoFila: "+maximoFila+" - maximoCol: "+maximoCol);
//					System.out.println("maxFila: "+maxFila+" - maximoCol: "+maxCol);
					boolean minimoFila = j-1>=0;
					boolean minimoCol = i-1>=0;
					
					//i > col 
					//j > fila
					
					
					if( minimoFila && this.casillas[i][j-1].isBomba())
					{
						casilla.setRodeado(casilla.getRodeado()+1);
					}
					if( maximoFila && this.casillas[i][j+1].isBomba())
					{
						casilla.setRodeado(casilla.getRodeado()+1);
					}
					
					if( minimoFila && maximoCol &&  this.casillas[i+1][j-1].isBomba())
					{
						casilla.setRodeado(casilla.getRodeado()+1);
					}
					if( maximoCol && this.casillas[i+1][j].isBomba())
					{
						casilla.setRodeado(casilla.getRodeado()+1);
					}
					if( maximoCol && maximoFila && this.casillas[i+1][j+1].isBomba())
					{
						casilla.setRodeado(casilla.getRodeado()+1);
					}
					if( minimoCol && minimoFila && this.casillas[i-1][j-1].isBomba())
					{
						casilla.setRodeado(casilla.getRodeado()+1);
					}
					if( minimoCol && this.casillas[i-1][j].isBomba())
					{
						casilla.setRodeado(casilla.getRodeado()+1);
					}
					if( minimoCol && maximoFila && this.casillas[i-1][j+1].isBomba())
					{
						casilla.setRodeado(casilla.getRodeado()+1);
					}
					
					
				}
			}
		}
		
		
		
	}


	public int getCasillasClicked() {
		return casillasClicked;
	}


	public void setCasillasClicked(int casillasClicked) {
		this.casillasClicked = casillasClicked;
	}
	
	
	
	

}
