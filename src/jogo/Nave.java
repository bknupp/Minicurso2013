package jogo;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;



 public class Nave implements ObjetosJogo {
 private int x,y; 
 private int dx,dy;
 private Image imagem; 
 private int altura,largura;
 private List<Missil> misseis;
 private boolean isVisivel;
 private int VELOCIDADE = 3;
  


public Nave(){
	this.x=100;
	this.y=100;
	isVisivel = true;
	
	carregaImagem();
	altura = imagem.getHeight(null);
	largura = imagem.getWidth(null);
	misseis = new ArrayList<Missil>();
  } 

 public void mexer(){
	  x += dx;//1 e 418
	  y += dy;//1 e 340
	  
	  if(this.x<0)
	  { x=0; }
	  
	  if(this.x>980)
	  { x = 980; }
	  
	  if(this.y<0)
	  { y =0;}
	  
	  if(this.y>318)
	  { y = 318;}	  
}
	public void atira(){
		this.misseis.add(new Missil(x+largura, y + altura/2 )); 
	}
  
  public void keyPressed(KeyEvent tecla)
  { 
	  int codigo = tecla.getKeyCode();
	  
	  if(codigo == KeyEvent.VK_SPACE){
			atira();
		}
	  
	  if(codigo==KeyEvent.VK_UP)
	  { dy = -VELOCIDADE; } 
	  
	  if(codigo==KeyEvent.VK_DOWN)
	  { dy = VELOCIDADE; } 
	  
	  if(codigo==KeyEvent.VK_LEFT)
	  { dx = -VELOCIDADE; } 
	  
	  if(codigo==KeyEvent.VK_RIGHT)
	  { dx = VELOCIDADE;  }
	  
	  
  }
  
  public void keyReleased(KeyEvent tecla)
  {   int codigo = tecla.getKeyCode();
	  
	  if(codigo==KeyEvent.VK_UP)
	  { dy = 0; }
	  
	  if(codigo==KeyEvent.VK_DOWN)
	  { dy = 0; }  
	  
	  if(codigo==KeyEvent.VK_LEFT)
	  { dx = 0; }
	  
	  if(codigo==KeyEvent.VK_RIGHT)
	  { dx = 0;  }
  }
 
 
 
 public boolean isVisivel()
 { return isVisivel;}
 public void setVisivel(boolean isVisivel) 
 { this.isVisivel = isVisivel;}
 public int getX() 
 { return x; }
 public int getY() 
 { return y; }
 public Image getImagem() 
 { return imagem;}
 public List<Missil> getMisseis()
 { return misseis;}
 
 public Rectangle getBounds()
	{ return new Rectangle(x,y,altura,largura); }
 
 
public int getVELOCIDADE() {
	return VELOCIDADE;
}

public void setVELOCIDADE(int vELOCIDADE) {
	VELOCIDADE = vELOCIDADE;
}

@Override
public Image carregaImagem() {
	ImageIcon referencia = new ImageIcon("res\\nave.gif"); 
	imagem = referencia.getImage();
	return imagem;
}
}
