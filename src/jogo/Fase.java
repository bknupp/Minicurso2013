package jogo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener {

	private Image fundo;
	private Nave nave;
	private Timer timer;
	private int vidas = 3;
	private boolean emJogo, colidiu=false;
	private int quemColidiu;

	private List<Inimigo> inimigos;
	private int[][] coordenadas = { { 800, 29 }, { 800, 59 }, { 1380, 89 },
			{ 780, 109 }, { 580, 139 }, { 880, 239 }, { 790, 259 },
			{ 760, 50 }, { 790, 150 }, { 980, 209 }, { 560, 45 }, { 510, 70 },
			{ 930, 159 }, { 590, 80 }, { 530, 60 }, { 940, 59 }, { 990, 30 },
			{ 920, 200 }, { 900, 259 }, { 660, 50 }, { 540, 90 }, { 810, 220 },
			{ 860, 20 }, { 740, 180 }, { 820, 128 }, { 490, 170 }, { 700, 30 },
			{ 920, 300 }, { 856, 328 }, { 456, 320 } };

	public Fase() 
	{ this.inicializa(); }

	private void inicializa() {
		setFocusable(true);
		setDoubleBuffered(true);
		addKeyListener(new TecladoAdapter());

		ImageIcon referencia = new ImageIcon("res\\fundo.gif");
		fundo = referencia.getImage();

		emJogo = true;

		nave = new Nave();
		inicializaInimigos();

		timer = new Timer(5, this);
		timer.start();
	}

 public void inicializaInimigos(){
		inimigos = new ArrayList<Inimigo>();

		for (int i = 0; i < coordenadas.length; i++) 
		{inimigos.add(new Inimigo(coordenadas[i][0], coordenadas[i][1]));}
	}

 public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(fundo, 0, 0, null);

		if (emJogo) 
		{
			graficos.drawImage(nave.getImagem(), nave.getX(), nave.getY(), this);
			List<Missil> misseis = nave.getMisseis();

			for (int i = 0; i < misseis.size(); i++) {
				Missil m = misseis.get(i);
				graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
			}

			for (int i = 0; i < inimigos.size(); i++) {
				Inimigo in = inimigos.get(i);
				graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
			}

			graficos.setColor(Color.BLUE);
			graficos.drawString("INIMIGOS QUE RESTAM: ", 5, 15);
			graficos.setColor(Color.WHITE);
			graficos.drawString(" " + inimigos.size(), 139, 15);
			graficos.setColor(Color.RED);
			graficos.drawString("VIDAS:" + vidas, 5, 30);			
		} else {
			ImageIcon fimJogo = new ImageIcon("res\\game_over.jpg");
			graficos.drawImage(fimJogo.getImage(), 0, 0, null);
		}

		if (colidiu) {
			Inimigo in = inimigos.get(quemColidiu);
			ImageIcon colidiu = new ImageIcon("res\\Explosao.gif");
			graficos.drawImage(colidiu.getImage(), in.getX(), in.getY(), this);
		}


		
		
		if (inimigos.size() == 0) {
			colidiu = false;
			ImageIcon ganhei = new ImageIcon("res\\venceu.jpg");
			graficos.drawImage(ganhei.getImage(), 0, 0, null);
		}
		
				
		
		g.dispose();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	  colidiu = false;
	    
		if (inimigos.size() == 0) 
		{ emJogo = false;}

		List<Missil> misseis = nave.getMisseis();

		for (int i = 0; i < misseis.size(); i++){
			Missil m = (Missil) misseis.get(i);
		
			if (m.isVisivel()) {
				m.mexer();
			} else {
				misseis.remove(i);
			}
		}

		for (int i = 0; i < inimigos.size(); i++){
		   Inimigo in = inimigos.get(i);
			if (in.isVisivel()){
				in.mexer();
			} else {
				inimigos.remove(i);
			}
		}

		nave.mexer();
		checarColisao();
		repaint();
	}

	public void checarColisao() {
		Rectangle formaNave = nave.getBounds();
		Rectangle formaInimigo;
		Rectangle formaMissel;

		for (int i = 0; i < inimigos.size(); i++){
			Inimigo tempInimigo = inimigos.get(i);
			formaInimigo = tempInimigo.getBounds();

			if (formaNave.intersects(formaInimigo)){
				vidas--;
				tempInimigo.setVisivel(false);
                setQuem(i);
                colidiu = true;
                
				if (vidas == 0){
					nave.setVisivel(false);
					emJogo = false;
				}
			}
		 }

		List<Missil> misseis = nave.getMisseis();
		for (int i = 0; i < misseis.size(); i++){
			Missil tempMissel = misseis.get(i);
			formaMissel = tempMissel.getBounds();

			for (int j = 0; j < inimigos.size(); j++) {
				Inimigo tempInimigo = inimigos.get(j);
				formaInimigo = tempInimigo.getBounds();

				if (formaMissel.intersects(formaInimigo)) {
					tempInimigo.setVisivel(false);
					tempMissel.setVisivel(false);
					setQuem(j);
					colidiu = true;
					
					
					
				}
			}
		}
	
	}
	
	public int getQuem() 
	{ return quemColidiu;}

	public void setQuem(int i)
	{this.quemColidiu = i;}

	private class TecladoAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {

			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				emJogo = true;
			 	vidas = 3;
				nave = new Nave();
				inicializaInimigos();
			}
			
			if (e.getKeyCode() == KeyEvent.VK_T) {
				nave.setVELOCIDADE(4);
	
				
			}
			
			nave.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			nave.keyReleased(e);
		}

	}

}
