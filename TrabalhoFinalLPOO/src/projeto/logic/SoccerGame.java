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
		GameObject parede1 = new Parede(m_Input, new Vector2(0,0), new Vector2(900, 90));
		GameObject parede2 = new Parede(m_Input, new Vector2(0,400), new Vector2(900,90));
		GameObject parede3 = new Parede(m_Input, new Vector2(0,0), new Vector2(90,400));
		GameObject parede4 = new Parede(m_Input, new Vector2(900-90,0), new Vector2(90,400)); 
		  
		addGameObject(parede1);
		addGameObject(parede2);
		addGameObject(parede3);
		addGameObject(parede4);
		
		GameObject bola = new Bola(m_Input, new Vector2(450,200),10, new Vector2(100,0));
		GameObject bola2 = new Bola(m_Input, new Vector2(700,200),10, new Vector2(0,0));
		
		addGameObject(bola);  
		addGameObject(bola2); 
	}

	public void update(float timeLapsed){
		super.update(timeLapsed);
	}

}