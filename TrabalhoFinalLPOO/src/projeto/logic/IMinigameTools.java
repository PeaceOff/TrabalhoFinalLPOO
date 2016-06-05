package projeto.logic;

/**
 * @author Joao e David
 * @version 1.0
 * @created 03-mai-2016 15:30:38
 */
public interface IMinigameTools {
	
	/**
	 * Funcao para reiniciar um jogo
	 */
	void resetGame();
	
	/**
	 * Funcao para reniniciar uma ronda
	 */
	void resetRound();
	
	/**
	 * Funcao para alertar uma alteracao no score
	 * @param score valor do score
	 * @param entity_id id da equipa relativa ao score
	 */
	void scoreReceived(int score, int entity_id);
	
	/**
	 * Funcao para enviar uma estatistica
	 * @param id id do jogador a que estatistica se refere
	 * @param e estatistica a ser enviada
	 */
	void sendEst(int id, Estatistica e);
	
	/**
	 * Funcao para adicionar um novo objecto de jogo
	 * @param gO novo objecto de jogo
	 */
	void newGameObject(GameObject gO);
	
	/**
	 * Funcao para dar reset a um game object
	 * @param gO objecto que se vai fazer reset
	 */
	void resetGameObject(GameObject gO);
	
}
