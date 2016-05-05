package projeto.logic;


/**
 * @author PeaceOff
 * @version 1.0
 * @created 03-mai-2016 15:30:45
 */
public class SoccerGame extends Minigame {

	public SoccerGame(){
		super();
	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	public void initGame(){
		//Left Wall
		Vector2 pos1 = new Vector2();
		pos1.x = 2;
		pos1.y = 150;
		RectCollider r1 = new RectCollider(300,4,pos1,"Wall",false);
		this.m_Fisica.addObject(r1);
		
		//Right Wall
		Vector2 pos2 = new Vector2();
		pos2.x = 498;
		pos2.y = 150;
		RectCollider r2 = new RectCollider(300,4,pos2,"Wall",false);
		this.m_Fisica.addObject(r2);
		
		//Upper Wall
		Vector2 pos3 = new Vector2();
		pos3.x = 250;
		pos3.y = 2;
		RectCollider r3 = new RectCollider(4,500,pos3,"Wall",false);
		this.m_Fisica.addObject(r3);
		
		//Lower Wall
		Vector2 pos4 = new Vector2();
		pos4.x = 250;
		pos4.y = 298;
		RectCollider r4 = new RectCollider(4,500,pos4,"Wall",false);
		this.m_Fisica.addObject(r4);
		
		//One Ball
		Vector2 pos5 = new Vector2();
		pos5.x = 250;
		pos5.y = 150;
		CircleCollider cC = new CircleCollider(4,pos5,"Ball",true);
		Vector2 vel = new Vector2(5,6);
		cC.setVelocity(vel);
		this.m_Fisica.addObject(cC);
		
	}

	public void update(float timeLapsed){
		super.update(timeLapsed);
	}

}