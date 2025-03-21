package br.com.trifoglio.calc.modelo;

import java.util.ArrayList;
import java.util.List;

public class Memoria {

	private enum TipoComando {
		ZERAR, NUMERO, DIV, MULT, SUB, SOMA, IGUAL, VIRGULA, PORCENT, INV;
	}

	private static final Memoria instancia = new Memoria();

	private TipoComando ultimaOperacao = null;
	private boolean substituir = false;
	private String textoAtual = "";
	private String textoBuffer = "";

	private final List<MemoriaObservador> observadores = new ArrayList<>();

	private Memoria() {

	}

	public static Memoria getInstancia() {
		return instancia;
	}

	public void adicionarObservador(MemoriaObservador observador) {
		observadores.add(observador);
	}

	public String getTextoAtual() {
		return textoAtual.isEmpty() ? "0" : textoAtual;
	}

	public void setTextoAtual(String textoAtual) {
		this.textoAtual = textoAtual;
	}

	public void processarComando(String texto) {

		TipoComando tipoComando = detectarTipoComando(texto);
//		System.out.println(tipoComando);
		if (tipoComando == null) {
			return;
		} else if(tipoComando == TipoComando.ZERAR) {
			textoAtual = "";
			textoBuffer = "";
			substituir = false;
			ultimaOperacao = null;
		}else if(tipoComando == TipoComando.NUMERO || tipoComando == TipoComando.VIRGULA) {
			textoAtual = substituir ? texto : textoAtual + texto;
			substituir = false;
		} else {
			if (tipoComando == TipoComando.PORCENT) {
				double resultado = 0;
				double numeroAtual = 
						Double.parseDouble(textoAtual.replace(",","."));
				resultado = numeroAtual / 100;
				textoAtual = Double.toString(resultado).replace(".",",");
				substituir = true;
				boolean inteiro = textoAtual.endsWith(",0");
				textoAtual = inteiro ? textoAtual.replace(",0", "") : textoAtual;
				
			} else if (tipoComando == TipoComando.INV) {
					double resultado = 0;
					double numeroAtual = 
							Double.parseDouble(textoAtual.replace(",","."));
					resultado = numeroAtual * -1;
					textoAtual = Double.toString(resultado).replace(".",",");
					substituir = true;
					boolean inteiro = textoAtual.endsWith(",0");
					textoAtual = inteiro ? textoAtual.replace(",0", "") : textoAtual;
				} else {
			
			substituir = true;
			textoAtual = obterResultadoOperacao();
			ultimaOperacao = tipoComando;
			textoBuffer = textoAtual;
			}
		}
		
		
		observadores.forEach(o -> o.valorAlterado(getTextoAtual()));

	}

	private String obterResultadoOperacao() {
		if(ultimaOperacao == null || ultimaOperacao ==TipoComando.IGUAL) {
			return textoAtual;
		}
		double numeroBuffer = 
				Double.parseDouble(textoBuffer.replace(",","."));
		double numeroAtual = 
				Double.parseDouble(textoAtual.replace(",","."));
		double resultado = 0;
		
		if (ultimaOperacao == TipoComando.SOMA) {
			resultado = numeroBuffer + numeroAtual;
		} else if (ultimaOperacao == TipoComando.SUB) {
			resultado = numeroBuffer - numeroAtual;
		}else if (ultimaOperacao == TipoComando.MULT) {
			resultado = numeroBuffer * numeroAtual;
		} else if (ultimaOperacao == TipoComando.DIV) {
			resultado = numeroBuffer / numeroAtual;
		} else if (ultimaOperacao == TipoComando.PORCENT) {
			
		}
		
		String texto = Double.toString(resultado).replace(".",",");
		boolean inteiro = texto.endsWith(",0");
		return inteiro ? texto.replace(",0", "") : texto;
	}

	private TipoComando detectarTipoComando(String texto) {
		if (textoAtual.isEmpty() && texto == "0") {
			return null;
		}

		try {
			Integer.parseInt(texto);
			return TipoComando.NUMERO;
		} catch (NumberFormatException e) {
//			quando não for números
			if ("C".equals(texto)) {
				return TipoComando.ZERAR;
			} else if ("/".equals(texto)) {
				return TipoComando.DIV;
			} else if ("X".equals(texto)) {
				return TipoComando.MULT;
			} else if ("+".equals(texto)) {
				return TipoComando.SOMA;
			} else if ("-".equals(texto)) {
				return TipoComando.SUB;
			} else if ("=".equals(texto)) {
				return TipoComando.IGUAL;
			} else if (",".equals(texto)&& !textoAtual.contains(",")) {
				return TipoComando.VIRGULA;
			} else if ("%".equals(texto)) {
				return TipoComando.PORCENT;
			} else if ("±".equals(texto) ) {
				return TipoComando.INV;
			}

		}

		return null;
	}

}