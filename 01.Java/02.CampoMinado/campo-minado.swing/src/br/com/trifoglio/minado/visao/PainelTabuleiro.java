package br.com.trifoglio.minado.visao;

import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import br.com.trifoglio.minado.modelo.Tabuleiro;

@SuppressWarnings("serial")
public class PainelTabuleiro extends JPanel {

	public PainelTabuleiro(Tabuleiro tabuleiro) {

		setLayout(new GridLayout(tabuleiro.getLinhas(), tabuleiro.getColunas()));

//		somente para um teste 
//		int total = tabuleiro.getLinhas() * tabuleiro.getColunas();
//		
//		for (int i = 0; i < total; i++) {
//			add(new BotaoCampo(null));
//			
//			
//		}

		tabuleiro.paraCadaCampo(c -> add(new BotaoCampo(c)));

		tabuleiro.registrarObservador(e -> {

			SwingUtilities.invokeLater(() -> { // irá garantir que a interface seja atualizada antes de apresentar a
												// caixa de dialogo
				if (e.isGanhou()) {
					
					JOptionPane.showMessageDialog(this, "Ganhou :)");
				} else {
					JOptionPane.showMessageDialog(this, "Perdeu :)");

				}
				
				tabuleiro.reiniciar();

			});
		});

	}

}
