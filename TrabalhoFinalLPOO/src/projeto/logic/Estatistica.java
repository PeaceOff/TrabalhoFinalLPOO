package projeto.logic;

import java.io.Serializable;

public class Estatistica implements Serializable {

	static final long serialVersionUID = 42L;
	
	private String tipoJogo;
	private String nome;
	private int valor;
	
	public Estatistica(String n, int v){
		tipoJogo = null;
		nome = n;
		valor = v;
		System.out.println(n + " " + v);
	}

	public void setTipoJogo(String n){
		tipoJogo = n;
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
