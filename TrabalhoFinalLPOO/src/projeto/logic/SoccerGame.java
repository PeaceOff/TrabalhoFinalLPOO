package projeto.logic;


/**
 * @author PeaceOff
 * @version 1.0
 * @created 03-mai-2016 15:30:45
 */
public class SoccerGame extends Minigame {
	
	Player[] players;
	private double tempo = 0;
	private int gerar = 0;
	private int c = 900;
	private int l = 400;
	
	public SoccerGame(Input i){
		super(i);
		players = new Player[8];  
	} 

	public void finalize() throws Throwable {
		super.finalize();
	}

	public void initGame(){ 
		//Left Wall
		//Generating Camp
		//x 

		int b = 100;
		int e = 40;
		int ps = 10;
		
		GameObject w1 = new Parede(m_Input, new Vector2(0,0), new Vector2(c, e));
		GameObject w2 = new Parede(m_Input, new Vector2(0,l), new Vector2(c,e));
		
		GameObject d11 = new Parede(m_Input, new Vector2(0,0), new Vector2(e,(l+e)/2 - b/2));
		GameObject d12 = new Parede(m_Input, new Vector2(0, (l+e) - (l+e)/2 + b/2), new Vector2(e,(l+e)/2 -b/2)); 
		GameObject d13 = new Parede(m_Input, new Vector2(0, (l+e)/2 - b/2), new Vector2(e/4,b)); 
		
		GameObject p1 = new Poste(m_Input, new Vector2(e , (l+e)/2 - b/2 ), ps, 200000);
		GameObject p2 = new Poste(m_Input, new Vector2(e , (l+e)/2 + b/2), ps, 200000);
		GameObject p3 = new Poste(m_Input, new Vector2(c-e , (l+e)/2 - b/2), ps, 200000);
		GameObject p4 = new Poste(m_Input, new Vector2(c-e , (l+e)/2 + b/2), ps, 200000);
		
		GameObject d21 = new Parede(m_Input, new Vector2(c-e,0), new Vector2(e,(l+e)/2 - b/2));
		GameObject d22 = new Parede(m_Input, new Vector2(c-e, (l+e) - (l+e)/2 + b/2), new Vector2(e,(l+e)/2 -b/2)); 
		GameObject d23 = new Parede(m_Input, new Vector2(c-e/4, (l+e)/2 - b/2), new Vector2(e/4,b));
		
		GameObject up1 = new PowerUp(c,l);
		
		
		addGameObject(w1);
		addGameObject(w2);
		addGameObject(d11);
		addGameObject(d12);  
		addGameObject(d13);
		
		addGameObject(d21);
		addGameObject(d22);  
		addGameObject(d23);
		
		addGameObject(p1);
		addGameObject(p2);
		addGameObject(p3);
		addGameObject(p4);
		
		addGameObject(up1);
		
		
		
		
		GameObject bola = new Bola(m_Input, new Vector2(400,215), new Vector2(100,0));
		addGameObject(bola); 
		
	}

	public void update(float timeLapsed){
		tempo += timeLapsed;
		if(((int)(tempo / 5)) > gerar){
			GameObject tmp = new PowerUp(c,l);
			addGameObject(tmp);
			gerar = (int)(tempo / 5);
		}
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