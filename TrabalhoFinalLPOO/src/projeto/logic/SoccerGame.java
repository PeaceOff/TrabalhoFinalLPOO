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
		GameObject parede4 = new Parede(m_Input, new Vector2(810,0), new Vector2(90,400)); 
		  
		addGameObject(parede1);
		addGameObject(parede2);
		addGameObject(parede3);
		addGameObject(parede4);  
		
		GameObject bola = new Bola(m_Input, new Vector2(400,215),20, new Vector2(100,0));
		GameObject bola2 = new Bola(m_Input, new Vector2(500,200),20, new Vector2(0,100));
		GameObject bola3 = new Bola(m_Input, new Vector2(550,200),10, new Vector2(-100,0));
		GameObject bola4 = new Bola(m_Input, new Vector2(400,100),50, new Vector2(200,-100));
		GameObject bola5 = new Bola(m_Input, new Vector2(150,150),80, new Vector2(0,0));
		GameObject bola6 = new Bola(m_Input, new Vector2(500,300),10, new Vector2(-100,-100));
		bola5.getCollider().setMovable(false);
		addGameObject(bola);  
		addGameObject(bola2);
		addGameObject(bola3);
		addGameObject(bola4);
		addGameObject(bola5);
		addGameObject(bola6);
	}

	public void update(float timeLapsed){
		super.update(timeLapsed);
	}

}