package jogo;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Missil implements ObjetosJogo{
  
	private int x,y;
	private int altura,largura;
	private Image imagem;
	private boolean isVisivel;

private static final int LARGURADATELA =1000;
private static final int VELOCIDADE = 3;
	
	
	public Missil(int x, int y){
		this.x=x;
		this.y=y;
		isVisivel=true;
		
		carregaImagem();	
	    altura = imagem.getHeight(null);
	    largura =imagem.getWidth(null);	    
	}

	public void mexer()
	{  	this.x += VELOCIDADE;
	    if(this.x>LARGURADATELA)
		{ isVisivel=false;	}
	}
		

	public boolean isVisivel()
	{ return isVisivel;	}
	public void setVisivel(boolean isVisivel) 
	{ this.isVisivel = isVisivel;	}
	public Image getImagem()
	{ return imagem;   }
	public int getX()
	{ return x;	}
	public int getY() 
	{ return y; 	}
	
	public Rectangle getBounds()
	{ return new Rectangle(x,y,altura,largura); }

	@Override
	public Image carregaImagem() {
		ImageIcon referencia = new ImageIcon("res\\missil2.gif");
	    imagem = referencia.getImage();
	    return imagem;
	}

}
	
	
	
	

