package jogo;

import java.awt.Image;
import java.awt.Rectangle;

public interface ObjetosJogo {
	public Image carregaImagem();
	public void mexer();
	public Rectangle getBounds();
}

