package projeto.logic;

import java.util.ArrayList;

/**
 * @author David
 * @version 1.0
 * @created 03-mai-2016 15:30:40
 */
public abstract class Minigame {

	protected ArrayList<Integer> scores;
	protected Fisica m_Fisica;
	protected ArrayList<GameObject> game_objects;
	protected Input m_Input = new Input(8); 
	
	public Minigame(Input i){
		scores = new ArrayList<Integer>();
		m_Fisica = new Fisica() ; //Fisica.getInstance();
		game_objects = new ArrayList<GameObject>();
		m_Input = i; 
	}
	
	public abstract void addPlayer(int id);
	
	public abstract void removePlayer(int id);
	
	public void addGameObject(GameObject g){
		synchronized (game_objects) {
			game_objects.add(g);
			m_Fisica.addObject(g.m_Collider);
		}
	}
	public void removeGameObject(GameObject g){
		synchronized (game_objects) {
			game_objects.remove(g);
			m_Fisica.removeObject(g.m_Collider); 
		}
		
	}
	
	public abstract void initGame();
	
	public Input getInput(){
		return m_Input;
	}
	 
	
	public ArrayList<GameObject> getGame_objects() {
		synchronized (game_objects) {
			return game_objects;
		}
	}

	public void update(float timeLapsed){
		for(GameObject g : game_objects) 
			g.update(timeLapsed);
		m_Fisica.update(timeLapsed);
	}

}