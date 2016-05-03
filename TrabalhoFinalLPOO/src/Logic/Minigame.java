package Logic;

import java.util.ArrayList;

/**
 * @author David
 * @version 1.0
 * @created 03-mai-2016 15:30:40
 */
public abstract class Minigame {

	private ArrayList<Integer> scores = null;
	public Fisica m_Fisica;
	public GameObject m_GameObject;

	public Minigame(){

	}

	public void finalize() throws Throwable {

	}

	public void initGame(){

	}

	/**
	 * 
	 * @param timeLapsed
	 * @return 
	 */
	public void update(float timeLapsed){

	}

}