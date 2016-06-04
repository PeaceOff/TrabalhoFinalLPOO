package projeto.logic;

/**
 * @author Joao e David
 * @version 1.0
 * @created 03-mai-2016 15:30:46
 */
public interface State {
	
	public final int time = 5;
	
	/**
	 * Funcao update relativo ao estado
	 * @param timeLapsed tempo que passou desde da ultima atualizacao
	 * @param gO objecto que se vai atualizar
	 */
	public void update(float timeLapsed,GameObjectState gO);
}
