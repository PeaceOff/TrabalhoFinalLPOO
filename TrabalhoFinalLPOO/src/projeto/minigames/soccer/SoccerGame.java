package projeto.minigames.soccer;

import projeto.logic.Estatistica;
import projeto.logic.GameObject;
import projeto.logic.GameObjectState;
import projeto.logic.Input;
import projeto.logic.Minigame;
import projeto.logic.Player;
import projeto.logic.PowerUp;
import projeto.logic.Vector2;
import projeto.logic.iEstatisticaAlert;

/**
 * @author PeaceOff
 * @version 1.0
 * @created 03-mai-2016 15:30:45
 */
public class SoccerGame extends Minigame {
	
	Player[] players;
	private double tempo = 0;
	private int gerar = 0;
	private final int c = 900;
	private final int l = 400;
	private final int b = 100;
	private final int e = 40;
	private final int ps = 10;
	
	private int scores[] = new int[2];
	
	public SoccerGame(Input i,iEstatisticaAlert estA){
		super(i,estA);
		players = new Player[8];  
	} 

	public void finalize() throws Throwable {
		super.finalize();
	}

	public void initGame(){  

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

		GameObject b1 = new Baliza(m_Input, new Vector2(0,(l/2) - (b / 2) + (e / 2)), new Vector2((e * 0.8),b),1,this);
		GameObject b2 = new Baliza(m_Input, new Vector2(c - (e * 0.8),(l/2) - (b / 2) + (e / 2)), new Vector2((e * 0.8),b),0,this);
		
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
		
		addGameObject(b1);
		addGameObject(b2);
		
		GameObject bola = new Bola(m_Input, new Vector2((c+e)/2,(l+e)/2),this);
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
		
		Player player = new Player(m_Input, id , new Vector2());
		addGameObject(player);
		resetPlayer(player);
		players[id] = player; 
	}
	
	public void resetPlayer(Player p){
		if(p == null)
			return;
		
		p.getCollider().setPosition(new Vector2( ((c)/2) + (((p.getId() % 2 == 0)? -1:1) * (c)/2 * 0.8)
											,(l/2) + p.getId()));
		//System.out.println("Pos:" + (((c+e)/2) + (((p.getId() % 2 == 0)? -1:1) * (c - e)/2 * 0.8)));
		p.getCollider().setVelocity(new Vector2()); 
		p.resetState();
	}

	@Override
	public void removePlayer(int id) {
		
		removeGameObject(players[id]);
		players[id] = null;
		
	}

	@Override
	public void resetRound() {
		//Reset Players
		for(Player p : players){
			resetPlayer(p);
		}
		
		for(GameObject g : game_objects){
			if(g.getCollider().getTag().contains("ball")){
				g.getCollider().setPosition(new Vector2((c+e)/2,(l+e)/2));
				g.getCollider().setVelocity(new Vector2());
				((GameObjectState)g).resetState();

			} else if (g.getCollider().getTag().contains("PowerUp")){
				g.getCollider().destroy = 1;
			}
		}
		
		tempo = 0; //Reiniciar a geracao de powerups
		gerar = 0;
		
	}

	@Override
	public void scoreReceived(int score, int entity_id) {
		if(entity_id < 0 || entity_id >= scores.length)
			return;
		scores[entity_id] += score;
	}

	@Override
	public void resetGame() {
	}

	@Override
	public int[] getScores() {
		return scores;
	}

	@Override
	public void sendEst(int id, Estatistica e) {
		e.setTipoJogo("SoccerGame");
		i_EstAlert.receiveEstatistica(id, e);
		
	}

}