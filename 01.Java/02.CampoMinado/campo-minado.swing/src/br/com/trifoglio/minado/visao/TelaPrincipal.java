package br.com.trifoglio.minado.visao;

import javax.swing.JFrame;

import br.com.trifoglio.minado.modelo.Tabuleiro;

@SuppressWarnings("serial")
public class TelaPrincipal extends JFrame{
	
	public TelaPrincipal() {
		Tabuleiro tabuleiro = new Tabuleiro(16,30,50);
//		PainelTabuleiro painelTabuleiro = new PainelTabuleiro(tabuleiro); // alternativamente podemos chamar diretamente
		
		add(new PainelTabuleiro(tabuleiro));
		
		setTitle("Campo Minado - Veca");
		setSize(690,438);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		
	
	}
	
	public static void main(String[] args) {
		
		new TelaPrincipal();
	}

}
