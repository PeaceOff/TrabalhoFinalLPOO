package projeto.logic;

/**
 * @author David
 * @version 1.0
 * @created 03-mai-2016 15:30:38
 */
public class Input {
	
	public PlayerInput[] m_PlayerInput;

	public Input(int count){
		m_PlayerInput = new PlayerInput[count];
		for(int i = 0; i < count ; i++){
			m_PlayerInput[i] = new PlayerInput();
		}
		
	}


	/**
	 * 
	 * @param id
	 */
	public PlayerInput getPlayerInput(int id){
		if(id < m_PlayerInput.length) 
			return m_PlayerInput[id];
		
		return null;
	}

}