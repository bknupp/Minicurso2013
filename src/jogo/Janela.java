package jogo;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

public class Janela extends JFrame {

	
	public Janela() {
	
	
		JMenuBar barraMenu = new JMenuBar();
		JMenu menu = new JMenu("Menu");

		JMenuItem novoJogo = new JMenuItem("Novo Jogo");
		novoJogo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			   getContentPane().removeAll();
			   getContentPane().add(new Fase());
			   setVisible(true);
			   transferFocusBackward();
			}
		});

		JMenuItem sobre = new JMenuItem("Sobre");
		sobre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"Jogo desenvolvido por Kup.", "Informações",
						JOptionPane.INFORMATION_MESSAGE);
				 
			}
		});

		JMenuItem ajuda = new JMenuItem("Ajuda");
		ajuda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"Para recomeçar o jogo, aperte ENTER.", "Ajuda",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		JMenuItem sair = new JMenuItem("Sair");
		sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		menu.add(novoJogo);
		menu.add(new JSeparator());
		ajuda.setForeground(Color.red);
		menu.add(ajuda);
		menu.add(new JSeparator());
		menu.add(sobre);
		menu.add(new JSeparator());
		menu.add(sair);

		barraMenu.add(menu);
		setJMenuBar(barraMenu);
	
		add(new TelaFundo());
		
		//add(new Fase());
		setTitle("Mini-Curso: Macacos Atômicos");
		setSize(1000, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}

	public static void main(String[] args) {
		new Janela();
	}

	
	

		
}
