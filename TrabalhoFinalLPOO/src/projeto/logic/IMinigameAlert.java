package projeto.logic;

/**
 * @author Joao e David
 * @version 1.0
 * @created 03-mai-2016 15:30:38
 */
public interface IMinigameAlert {

	/**
	 * Funcao para receber uma estatistica
	 * @param player_id id do jogador
	 * @param e estatistica
	 */
	void receiveEstatistica(int player_id, Estatistica e);
	
	/**
	 * Funcao para alertar que o jogo terminou
	 * @param vencedor string como o nome da equipa vencedora
	 */
	void gameEnded(String vencedor);
	
}
