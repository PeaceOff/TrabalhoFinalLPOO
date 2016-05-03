package projeto.logic;

import java.util.ArrayList;

/**
 * @author David
 * @version 1.0
 * @created 03-mai-2016 15:30:40
 */
public abstract class Minigame {

	private ArrayList<Integer> scores;
	public Fisica m_Fisica;
	public GameObject m_GameObject;

	public Minigame(){
		scores = new ArrayList<Integer>();
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