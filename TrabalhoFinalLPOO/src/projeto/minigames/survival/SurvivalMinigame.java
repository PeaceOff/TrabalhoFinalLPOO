package projeto.minigames.survival;

import java.util.ArrayList;
import java.util.Random;

import projeto.logic.ControllerInformationPacket;
import projeto.logic.Estatistica;
import projeto.logic.GameObject;
import projeto.logic.Input;
import projeto.logic.Minigame;
import projeto.logic.Parede;
import projeto.logic.Vector2;
import projeto.logic.IMinigameAlert;


public class SurvivalMinigame extends Minigame {

	private int scores[] = new int[]{1,1};
	private ArrayList<ControllerInformationPacket> packets = new ArrayList<ControllerInformationPacket>();
	
	private final int comprimento = 1000;
	private final int altura = 700;
	private final int espessura = 50;
	
	private Player[] players = new Player[8];
	
	private float time = 0;
	
	public SurvivalMinigame(Input i, IMinigameAlert estA) {
		super(i, estA);
		
	}

	@Override
	public void resetGame() {
		
	}

	@Override
	public void resetRound() {
		
	}
	
	private void SpawnAsteroid(){
		
		Random rnd = new Random();
		int raio = rnd.nextInt()%30 + 40;
		Vector2 pos = new Vector2();
		pos.x = rnd.nextInt( (int)(comprimento- espessura*2));
		pos.y = espessura+raio;
		
		Vector2 dir = new Vector2(rnd.nextInt(201)-100,rnd.nextInt(101));
		dir.multiply(1); 
		Asteroide asteroid = new Asteroide(pos, raio, dir,100);
		
		addGameObject(asteroid);
	}
	
	@Override
	public void update(float timeLapsed) {
		
		time+=timeLapsed;
		
		if(time > 3){
			time = 0;
			SpawnAsteroid();
		}
		
		super.update(timeLapsed);
	}
	
	@Override
	public void scoreReceived(int score, int entity_id) {
		scores[entity_id % 2] += score;
		
		if(scores[0] <= 0){
			i_EstAlert.gameEnded("Equipa Preta Ganhou!");
		}else if(scores[1] <= 0){  
			i_EstAlert.gameEnded("Equipa Branca Ganhou!");
		}
		
	}

	@Override
	public void sendEst(int id, Estatistica e) {
		
		e.setTipoJogo("Survival");
		i_EstAlert.receiveEstatistica(id, e);
		
		
	}

	@Override
	public void addPlayer(int id) {
		
		Player player = new Player(m_Input, id,new Vector2(espessura + 30 + 30*id ,altura/2),this);
		players[id]=player;
		addGameObject(player);
		
	}

	@Override
	public void removePlayer(int id) {
		if(players[id] == null) return;
		
		removeGameObject(players[id]);
		
	}

	@Override
	public ArrayList<ControllerInformationPacket> getControllPacket() {
		// TODO Auto-generated method stub
		return packets;
	}

	@Override
	public void initGame() {
		
		packets.add(new ControllerInformationPacket(0f, 0.5f, 0.5f, 0.5f, "<", (byte)0));
		packets.add(new ControllerInformationPacket(0.5f, 0.5f, 0.5f, 0.5f, ">", (byte)1));
		
		Parede c1 = new Parede(m_Input,new Vector2(0,0),new Vector2(comprimento,espessura));
		Parede c2 = new Parede(m_Input,new Vector2(0,altura-espessura),new Vector2(comprimento,espessura));
		
		Parede a1 = new Parede(m_Input,new Vector2(0,0),new Vector2(espessura,altura));
		Parede a2 = new Parede(m_Input,new Vector2(comprimento-espessura,0),new Vector2(espessura,altura));
		
		addGameObject(c1);
		addGameObject(c2);
		
		addGameObject(a1);
		addGameObject(a2);
		
		
	}

	@Override
	public int[] getScores() {
		return scores;
	}

	@Override
	public Vector2 getDim() {
		// TODO Auto-generated method stub
		return new Vector2(comprimento, altura);
	}

	@Override
	public String getDica() {
		return "Corre! Nao te deixes falecer\nUtiliza os botões para te movimentares para a esquerda e direita";
	}

	@Override
	public void resetGameObject(GameObject gO) {
	
		
	}

	
	
	
}
