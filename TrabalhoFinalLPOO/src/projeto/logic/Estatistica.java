package projeto.logic;

import java.io.Serializable;

public class Estatistica implements Serializable {

	private String tipoJogo;
	private String nome;
	private int valor;
	
	Estatistica(String tJ,String n, int v){
		tipoJogo = tJ;
		nome = n;
		valor = v;
	}


	public String getTipoJogo() {
		return tipoJogo;
	}

	public String getNome() {
		return nome;
	}

	public int getValor() {
		return valor;
	}
}
