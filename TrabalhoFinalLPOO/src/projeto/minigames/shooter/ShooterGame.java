package projeto.minigames.shooter;

import java.util.ArrayList;

import projeto.logic.ControllerInformationPacket;
import projeto.logic.Estatistica;
import projeto.logic.GameObject;
import projeto.logic.GameObjectState;
import projeto.logic.Input;
import projeto.logic.Minigame;
import projeto.logic.Player;
import projeto.logic.PowerUp;
import projeto.logic.Vector2;
import projeto.logic.iEstatisticaAlert;
import projeto.logic.ControllerInformationPacket.Type;

/**
 * @author PeaceOff
 * @version 1.0
 * @created 03-mai-2016 15:30:45
 */
public class ShooterGame extends Minigame {
	
	Player[] players;
	ArrayList<ControllerInformationPacket> controllerPackets = new ArrayList<ControllerInformationPacket>();
	private int scores[] = new int[2];
	
	public ShooterGame(Input i, iEstatisticaAlert estA) {
		super(i, estA);
	}
	
	public void finalize() throws Throwable {
		super.finalize();
	}

	public void initGame(){  

		controllerPackets.add(new ControllerInformationPacket(0f, 0.5f, 0.5f, 0.5f, Type.JOYSTICK));
		//TODO 
		
	}

	public void update(float timeLapsed){
	
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
	}

	@Override
	public void removePlayer(int id) {
		
		removeGameObject(players[id]);
		players[id] = null;
		
	}

	@Override
	public void resetRound() {
		//Reset Players
		for(Player p : players)
			resetPlayer(p);
		
		
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
		e.setTipoJogo("ShooterGame");
		i_EstAlert.receiveEstatistica(id, e);
		
	}

	@Override
	public Vector2 getDim() {
		return new Vector2();
	}
	
	@Override
	public String getDica() {
		return "BOOM! Headshot!";
	}

	@Override
	public ArrayList<ControllerInformationPacket> getControllPacket() {
		
		return controllerPackets;
	}
}

