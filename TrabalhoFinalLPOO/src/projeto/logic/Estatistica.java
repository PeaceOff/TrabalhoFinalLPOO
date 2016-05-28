package projeto.logic;

import java.io.Serializable;

public class Estatistica implements Serializable {

	private int id_Player;
	private String tipoJogo;
	private String nome;
	private int valor;
	
	Estatistica(String tJ,String n, int v,int id){
		tipoJogo = tJ;
		nome = n;
		valor = v;
		id_Player = id;
		System.out.println(n + " " + id);
	}

	public int getId_Player() {
		return id_Player;
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
