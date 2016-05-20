package projeto.logic;


/**
 * @author PeaceOff
 * @version 1.0
 * @created 03-mai-2016 15:30:45
 */
public class SoccerGame extends Minigame {
	
	Player[] players;
	
	public SoccerGame(Input i){
		super(i);
		players = new Player[8];  
	} 

	public void finalize() throws Throwable {
		super.finalize();
	}

	public void initGame(){ 
		//Left Wall
		GameObject parede1 = new Parede(m_Input, new Vector2(0,0), new Vector2(900, 10));
		GameObject parede2 = new Parede(m_Input, new Vector2(0,400), new Vector2(900,10));
		GameObject parede3 = new Parede(m_Input, new Vector2(0,0), new Vector2(10,400));
		GameObject parede4 = new Parede(m_Input, new Vector2(890,0), new Vector2(10,400)); 
		  
		addGameObject(parede1);
		addGameObject(parede2);
		addGameObject(parede3);
		addGameObject(parede4);  
		
		
		GameObject bola = new Bola(m_Input, new Vector2(400,215),10, new Vector2(100,0));
		addGameObject(bola); 
		
	}

	public void update(float timeLapsed){
		super.update(timeLapsed);
	}

	@Override
	public void addPlayer(int id) {
		Player player = new Player(m_Input, id , new Vector2(200 + id * 40, 200));
		addGameObject(player);
		players[id] = player; 
	}

	@Override
	public void removePlayer(int id) {
		
		removeGameObject(players[id]);
		players[id] = null;
		
	}

}