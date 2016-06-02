package projeto.logic;

import java.util.ArrayList;

/**
 * @author David
 * @version 1.0
 * @created 03-mai-2016 15:30:40
 */
public abstract class Minigame implements iMinigameTools {

	protected ArrayList<Integer> scores;
	protected Fisica m_Fisica;
	protected ArrayList<GameObject> game_objects;
	protected Input m_Input = new Input(8);
	protected iMinigameAlert i_EstAlert;
	
	public Minigame(Input i, iMinigameAlert estA){
		scores = new ArrayList<Integer>();
		m_Fisica = new Fisica() ; //Fisica.getInstance();
		game_objects = new ArrayList<GameObject>();
		m_Input = i; 
		i_EstAlert = estA;
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
	
	public abstract ArrayList<ControllerInformationPacket> getControllPacket();
	
	public abstract void initGame();
	
	public abstract int[] getScores();
	
	public abstract Vector2 getDim();
	
	public abstract String getDica();
	
	public Input getInput(){
		return m_Input;
	}
	 
	
	public ArrayList<GameObject> getGame_objects() {
		synchronized (game_objects) {
			return game_objects;
		}
	}

	public void update(float timeLapsed){ 
		synchronized(game_objects){
			for(int i = 0; i < game_objects.size(); i++){
				if(game_objects.get(i).isDestroyed()){
					removeGameObject(game_objects.get(i));
					i--;
					continue;
				}
				game_objects.get(i).update(timeLapsed);
			}
		}
		m_Fisica.update(timeLapsed);
		
	}
	
	public void newGameObject(GameObject gO){
		addGameObject(gO);
	}

}