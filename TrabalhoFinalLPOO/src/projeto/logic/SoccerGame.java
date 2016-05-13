package projeto.logic;


/**
 * @author PeaceOff
 * @version 1.0
 * @created 03-mai-2016 15:30:45
 */
public class SoccerGame extends Minigame {

	public SoccerGame(Input i){
		super(i);
	} 

	public void finalize() throws Throwable {
		super.finalize();
	}

	public void initGame(){
		//Left Wall
		GameObject parede1 = new Parede(m_Input, new Vector2(2,150), new Vector2(300,4));
		GameObject parede2 = new Parede(m_Input, new Vector2(498,150), new Vector2(300,4));
		GameObject parede3 = new Parede(m_Input, new Vector2(250,298), new Vector2(4,500));
		GameObject parede4 = new Parede(m_Input, new Vector2(250,150), new Vector2(4,500));
		  
		addGameObject(parede1);
		addGameObject(parede2);
		addGameObject(parede3);
		addGameObject(parede4);
		
		GameObject bola = new Bola(m_Input, new Vector2(145,245),10); 
		addGameObject(bola); 
	}

	public void update(float timeLapsed){
		super.update(timeLapsed);
	}

}