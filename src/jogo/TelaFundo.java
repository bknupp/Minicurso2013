package jogo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class TelaFundo extends JPanel{
	private Image fundo;
	
	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
			
		ImageIcon referencia = new ImageIcon("res\\inicio.jpg");
	  	fundo = referencia.getImage();
		graficos.drawImage(fundo,0,0, null);		
	}
	
	
}
