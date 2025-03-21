package br.com.trifoglio.calc.visao;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import br.com.trifoglio.calc.modelo.Memoria;
import br.com.trifoglio.calc.modelo.MemoriaObservador;

@SuppressWarnings("serial")
public class Display extends JPanel implements MemoriaObservador{

	private final JLabel label;

	public Display() {
		Memoria.getInstancia().adicionarObservador(this);
		setBackground(new Color(54, 48, 39));
		label = new JLabel(Memoria.getInstancia().getTextoAtual());
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Menlo", Font.PLAIN, 30));
		setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 15));
		add(label);
		
	}
	
	@Override
	public void valorAlterado(String novoValor) {
		label.setText(novoValor);
		
	}
	
	
	
}
