import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import clases.Casilla;
import clases.Juego;

import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Vista extends JFrame {

	private JPanel contentPane;

	private Principal controlador;
	private static int tmnCasilla = 40;
	/**
	 * Create the frame.
	 */
	/**
	 * @param controlador
	 */
	public Vista(Principal controlador) 
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.controlador = controlador;
		setTitle("Buscaminas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 962, 752);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

//		JButton btnCasilla = new JButton("1");
//		btnCasilla.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent arg0) {
//			}
//		});
//		btnCasilla.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//			}
//		});
//		btnCasilla.setMinimumSize(new Dimension(40, 40));
//		btnCasilla.setMaximumSize(new Dimension(40, 40));
//		btnCasilla.setPreferredSize(new Dimension(40, 40));
//		btnCasilla.setSize(new Dimension(40, 40));
//		btnCasilla.setFont(new Font("Tahoma", Font.BOLD, 10));
//		btnCasilla.setBounds(10, 11, 40, 40);
//		contentPane.add(btnCasilla);
	}

	protected void CrearTablero(Juego partida)
	{
		//hago un for para el nº total de casillas, creo una casilla y un jbutton, añado jbutton a la casilla, y la casilla a la partida
		for(int i=0;i<partida.getNumFilas();i++)
		{
			for(int j=0;j<partida.getNumColumnas();j++)
			{
				Casilla nuevaCasilla = new Casilla(false, 0, null);
				JButton btnCasilla = crearBoton(i, j, nuevaCasilla);
				nuevaCasilla.setBtnCasilla(btnCasilla);
				contentPane.add(btnCasilla);
				partida.getCasillas()[i][j] = nuevaCasilla;

				//System.out.println(partida.getCasillas()[i][j].getBtnCasilla().getName());
			}
		}
	}

	private JButton crearBoton(int fila, int col, Casilla nuevaCasilla)
	{
		JButton btnCasilla = new JButton("");
		btnCasilla.setName("casilla_F"+fila+"_C"+col);
		btnCasilla.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnCasilla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CasillaClicked(nuevaCasilla);
			}


		});
		btnCasilla.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				//System.out.println(e.getButton());
			    if (e.getButton() == MouseEvent.BUTTON3) 
			    {
			    	btnCasilla.setText("B");
			    }
			}
		});
		btnCasilla.setFocusPainted(false);
		btnCasilla.setFocusable(false);

		btnCasilla.setBounds(fila*tmnCasilla, col*tmnCasilla, tmnCasilla, tmnCasilla);

		return btnCasilla;
	}

	protected void CasillaClicked(Casilla casilla)
	{
		if(casilla.isBomba())
		{
			casilla.getBtnCasilla().setBackground(Color.RED);
			casilla.getBtnCasilla().setForeground(Color.RED);
			
			this.msg("You lost", this);

			new Principal();
			dispose(); //TODO: proper game reset
		}
		else
		{
			if(controlador.getPartida().isPrimerClick())
				ColocarBombas(casilla);

			Juego partida = controlador.getPartida();
			partida.calcularRodeadoCasilla(casilla);
			casilla.getBtnCasilla().setText(Integer.toString(casilla.getRodeado()));
			casilla.getBtnCasilla().setEnabled(false);
			casilla.setActivada(true);
			partida.setCasillasClicked(partida.getCasillasClicked()+1);
			System.out.println(partida.getCasillasClicked());
			System.out.println("Total Casillas necesarias: "+ (partida.getNumFilas()*partida.getNumColumnas()-partida.getNumMinas()));
			if(casilla.getRodeado()==0)
			{
				AutoActivarAlrededor(casilla);
			}
			
			if(partida.getCasillasClicked()  >= partida.getNumFilas()*partida.getNumColumnas()-partida.getNumMinas())
			{
				msg("Has ganado", this);
			}

		}

	}


	private void AutoActivarAlrededor(Casilla casilla) 
	{
		Juego partida = controlador.getPartida();
		for(int i=0;i<partida.getCasillas().length;i++)
		{
			for(int j=0;j<partida.getCasillas()[0].length;j++)
			{
				if(casilla == partida.getCasillas()[i][j])
				{
					//i > col 
					//j > fila
					int maxFila = partida.getCasillas().length-1;
					int maxCol = partida.getCasillas()[0].length-1;
					boolean maximoFila = j < maxFila;
					boolean maximoCol = i < maxCol;
					boolean minimoFila = j-1>=0;
					boolean minimoCol = i-1>=0;
//					System.out.println("pos: "+i+" - "+j);
//					System.out.println("maximoFila: "+maximoFila+" - maximoCol: "+maximoCol);
//					System.out.println("minimoFila: "+minimoFila+" - minimoCol: "+minimoCol);
//					System.out.println("maxFila: "+maxFila+" - maximoCol: "+maxCol);
					
					
					if( minimoFila && !partida.getCasillas()[i][j-1].getIsActivada())
					{
						partida.getCasillas()[i][j-1].getBtnCasilla().doClick();
					}
					if( maximoFila && !partida.getCasillas()[i][j+1].getIsActivada())
					{
						partida.getCasillas()[i][j+1].getBtnCasilla().doClick();
					}
					
					if( minimoFila && maximoCol && !partida.getCasillas()[i+1][j-1].getIsActivada())
					{
						partida.getCasillas()[i+1][j-1].getBtnCasilla().doClick();
					}
					if( maximoCol && !partida.getCasillas()[i+1][j].getIsActivada())
					{
						partida.getCasillas()[i+1][j].getBtnCasilla().doClick();
					}
					if( maximoCol && maximoFila && !partida.getCasillas()[i+1][j+1].getIsActivada())
					{
						partida.getCasillas()[i+1][j+1].getBtnCasilla().doClick();
					}
					if( minimoCol && minimoFila && !partida.getCasillas()[i-1][j-1].getIsActivada())
					{
						partida.getCasillas()[i-1][j-1].getBtnCasilla().doClick();
					}
					if( minimoCol && partida.getCasillas()[i-1][j].getIsActivada())
					{
						partida.getCasillas()[i-1][j].getBtnCasilla().doClick();
					}
					if( minimoCol && maximoFila && partida.getCasillas()[i-1][j+1].getIsActivada())
					{
						partida.getCasillas()[i-1][j+1].getBtnCasilla().doClick();
					}
					
				}
			}
		}

	}

	protected void ColocarBombas(Casilla casilla)
	{
		Juego partida = controlador.getPartida();
		int numMinas = partida.getNumMinas();
		int randomFila, randomColumna;

		if(numMinas >= partida.getNumCasillas())
		{
			this.error("More bombs than squares", this);
			return;
		}



		while(numMinas > 0)
		{
			randomFila = controlador.getRandomNumberInRange(0, partida.getNumFilas()-1);
			randomColumna = controlador.getRandomNumberInRange(0, partida.getNumColumnas()-1);
			//System.out.println("fila: "+randomFila+ " Col: "+randomColumna);
			if(partida.getCasillas()[randomFila][randomColumna].colocarBomba() && casilla != partida.getCasillas()[randomFila][randomColumna])
			{
				numMinas--;
				//partida.getCasillas()[randomFila][randomColumna].getBtnCasilla().setText("B"); //VER BOMBAS
			}
		}

		partida.setPrimerClick(false);

	}

	public void error(String string, Window padre) 
	{
		JOptionPane.showMessageDialog(padre, string, "Error", JOptionPane.ERROR_MESSAGE);
	}

	public void msg(String string, Window padre) 
	{
		JOptionPane.showMessageDialog(padre, string, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
	}
}
