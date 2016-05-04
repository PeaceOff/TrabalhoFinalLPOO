package projeto.logic;

import java.util.ArrayList;

/**
 * @author David
 * @version 1.0
 * @created 03-mai-2016 15:30:40
 */
public abstract class Minigame {

	public ArrayList<Integer> scores;
	public Fisica m_Fisica;
	public ArrayList<GameObject> game_objects;
	
	public Minigame(){
		scores = new ArrayList<Integer>();
		m_Fisica = Fisica.getInstance();
		game_objects = new ArrayList<GameObject>();
	}

	public abstract void initGame();
	
	public void update(float timeLapsed){
		m_Fisica.update(timeLapsed);
	}

}