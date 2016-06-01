package projeto.minigames.shooter;

import projeto.logic.CircleCollider;
import projeto.logic.Collider;
import projeto.logic.GameObject;
import projeto.logic.Input;
import projeto.logic.Obj;
import projeto.logic.PlayerInput;
import projeto.logic.PowerUp;
import projeto.logic.Rectangulo;
import projeto.logic.Vector2;
import projeto.logic.iMinigameTools;

/**
 * @author PeaceOff
 * @version 1.0
 * @created 03-mai-2016 15:30:42
 */
public class Player extends GameObject {

	private int id;
	private final static int size = 20;
	private final static double elast = 0.6;
	private int score = 0;
	private iMinigameTools myTools;
	private final Vector2 sDrag = new Vector2(1000,1000);
	private final Vector2 vCap = new Vector2(300,300);
	private Mira mira;
	private double tempo = 0;
	private Vector2 lastDir = new Vector2();
	
	public Player(Input i, int _id , Vector2 pos,iMinigameTools t,Mira m){
		super(new CircleCollider(size,elast, pos, "Player" + _id, true, 70),i,new Obj(new Rectangulo(), "players.png", new Rectangulo(_id*1/8f, 0, 1/8f ,1))); 
		id = _id;
		m_Collider.setVelCap(vCap);
		m_Collider.setDrag(sDrag);
		m_Collider.addListener(this);
		myTools = t;
		mira = m;
	}

	public void update(float timeLapsed){
		
		PlayerInput pIn = this.m_Input.getPlayerInput(id);
		
		if(pIn.getDirection(0).getNorm() > 0){
			synchronized(m_Collider){ 
				m_Collider.setVelocity(((Vector2.multiply(pIn.getDirection(0),m_Collider.getVelCap().x)))); 
			}
			pIn.setDirection(0,new Vector2());  
		}
		
		if(pIn.getDirection(1).getNorm() > 0){
			lastDir = pIn.getDirection(1);
			lastDir.normalize();
			if(tempo > 0.3){
				tempo = 0;
				shoot(lastDir.clone());
			}
			lastDir.multiply(this.size);
			pIn.setDirection(1,new Vector2());  
		} else {
			lastDir = new Vector2();
		}
		this.mira.setPosition(Vector2.add(lastDir, m_Collider.getPosition()));

		tempo += timeLapsed;
	}

	public void shoot(Vector2 dir){
		
		GameObject gO = new Bala(m_Collider.getPosition(),myTools,id);
		dir.multiply(500);
		gO.getCollider().setVelocity(dir);
		myTools.newGameObject(gO);
	}

	@Override
	public void onCollisionEnter(Collider c) {
		super.onCollisionEnter(c);
	}

	@Override
	public void onTriggerEnter(Collider c) {
		super.onTriggerEnter(c);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getScore() {
		return score;
	}

	public void score() {
		this.score ++;
	}

}