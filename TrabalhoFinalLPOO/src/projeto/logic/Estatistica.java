package projeto.logic;

import java.io.Serializable;

/**
 * @author Joao e David
 * @version 1.0
 * @created 03-mai-2016 15:30:34
 */
public class Estatistica implements Serializable {

	static final long serialVersionUID = 42L;
	
	private String tipoJogo;
	private String nome;
	private int valor;
	
	/**
	 * Construtor da classe
	 * @param n nome da estatistica 
	 * @param v valor da estatistica
	 */
	public Estatistica(String n, int v){
		tipoJogo = null;
		nome = n;
		valor = v;
	}
	
	/**
	 * Construtor da classe
	 * @param tJ nome do minijogo
	 * @param n nome da estatistica
	 * @param v valor da estatistica
	 */
	public Estatistica(String tJ,String n, int v){
		tipoJogo = tJ;
		nome = n;
		valor = v;
	}

	/**
	 * Funcao set para o nome do jogo
	 * @param n novo nome do jogo
	 */
	public void setTipoJogo(String n){
		tipoJogo = n;
	}

	/**
	 * Funcao get para o nome do jogo
	 * @return nome do jogo
	 */
	public String getTipoJogo() {
		return tipoJogo;
	}

	/**
	 * Funcao get para o nome da estatistica
	 * @return nome da estatistica
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Funcao get para o valor da estatistica
	 * @return o valor da estatistica
	 */
	public int getValor() {
		return valor;
	}
}
