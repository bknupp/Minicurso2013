package jogo;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Inimigo implements ObjetosJogo {
  
	private int x,y;
	private Image imagem;
	private int altura,largura;
	private boolean isVisivel;
    private static final int LARGURADATELA = 1000;
	private static final int VELOCIDADE = 2;
	private static int contador=0;
	
	
	public Inimigo(int x, int y){
		this.x=x;
		this.y=y;
		
	   carregaImagem();	    
	    altura = imagem.getHeight(null);
	    largura =imagem.getWidth(null);
	   
	    isVisivel = true;
	}

	public void mexer()
	{
		if(x<0){ 
			this.x = LARGURADATELA;
		}else{
			this.x -= VELOCIDADE;
		}

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
		 ImageIcon referencia;
			if(contador++ % 3 == 0){
				referencia = new ImageIcon("res\\inimigo.gif");
			
			} else {
				referencia = new ImageIcon("res\\inimigo0.png");  
			}
			
		
			if(contador++ % 7 == 0){
				referencia = new ImageIcon("res\\IN.gif");
			}
			
			imagem = referencia.getImage();
			return imagem;
	}
}
	
	
	
	
