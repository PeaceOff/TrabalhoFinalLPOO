package projeto.logic;

/**
 * @author Joao e David
 * @version 1.0
 * @created 03-mai-2016 15:30:38
 */
public class Input {
	
	public PlayerInput[] m_PlayerInput;

	/**
	 * Construtor da classe de input
	 * @param count numero maximo de inputs que vao ser suportados
	 */
	public Input(int count){
		m_PlayerInput = new PlayerInput[count];
		for(int i = 0; i < count ; i++){
			m_PlayerInput[i] = new PlayerInput();
		}
		
	}


	/**
	 * Funcao get para os inputs dos jogadores
	 * @param id do jogador do qual se quer saber o input
	 */
	public PlayerInput getPlayerInput(int id){
		if(id < m_PlayerInput.length) 
			return m_PlayerInput[id];
		
		return null;
	}

}