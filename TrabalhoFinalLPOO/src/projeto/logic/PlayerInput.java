package projeto.logic;


/**
 * @author Joao e David
 * @version 1.0
 * @created 03-mai-2016 15:30:42
 */
public class PlayerInput {


	private Vector2 direction[] = new Vector2[2];
	
	private byte keys[] = new byte[5];
	
	/**
	 * Funcao get para a key de posicao i
	 * @param i posicao da key que se pretende obter
	 * @return a key de posicao i no array keys
	 */
	public byte getKey(int i){
		if( i >= 0 || i < keys.length)
			return keys[i];
		return 0;
	}
	
	/**
	 * Funcao set para a key de posicao i
	 * @param i posicao da key no array
	 * @param key nova key para a dada posicao
	 */
	public void setKey(int i, byte key){
		
		if(i >= 0 || i < keys.length)
			keys[i] = key; 
	}
	
	/**
	 * Funcao get para a direction de um dado i
	 * @param i posicao da direction que se pretende obter
	 * @return direction de posicao i do vetor direction
	 */
	public Vector2 getDirection(int i) {
		if(i < 0 || i >= direction.length)
			return null;
		return direction[i];
	}

	/**
	 * Funcao set para a direction de um dado i
	 * @param i posicao que se vai alterar no array
	 * @param d nova direction para a posicao i
	 */
	public void setDirection(int i, Vector2 d) {
		 
		if(i < 0 || i >= direction.length)
			return;
		this.direction[i] = d;
	}


	/**
	 * Construtor da classe playerInput
	 */
	public PlayerInput(){
		for(int i = 0; i < direction.length; i++){
			direction[i] = new Vector2();
		}
	}
}