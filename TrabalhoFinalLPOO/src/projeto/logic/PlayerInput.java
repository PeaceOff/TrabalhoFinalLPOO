package projeto.logic;


/**
 * @author David
 * @version 1.0
 * @created 03-mai-2016 15:30:42
 */
public class PlayerInput {


	private Vector2 direction[] = new Vector2[2];
	
	public Vector2 getDirection(int i) {
		if(i < 0 || i >= direction.length)
			return null;
		return direction[i];
	}

	public void setDirection(int i, Vector2 d) {
		if(i < 0 || i >= direction.length)
			return;
		this.direction[i] = d;
	}


	public PlayerInput(){
		for(int i = 0; i < direction.length; i++){
			direction[i] = new Vector2();
		}
	}

	public void finalize() throws Throwable {

	}

}