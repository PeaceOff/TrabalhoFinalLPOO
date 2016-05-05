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

	public void addGameObject(GameObject g){
		game_objects.add(g);
		m_Fisica.addObject(g.m_Collider);
	}
	
	public abstract void initGame();
	
	public void update(float timeLapsed){
		for(GameObject g : game_objects)
			g.update(timeLapsed);
		m_Fisica.update(timeLapsed);
	}

}