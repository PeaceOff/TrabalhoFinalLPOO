package projeto.minigames.shooter;

import projeto.logic.CircleCollider;
import projeto.logic.Collider;
import projeto.logic.Estatistica;
import projeto.logic.GameObject;
import projeto.logic.Obj;
import projeto.logic.Rectangulo;
import projeto.logic.Vector2;
import projeto.logic.iMinigameTools;

public class Bala extends GameObject {
	
	private int player_ID = -1;
	private int hit_ID = -1;
	private iMinigameTools myTools;
	private final static int size = 10;
	private final static double elast = 0.9;
	private final Vector2 sDrag = new Vector2();
	private final Vector2 vCap = new Vector2(7000,7000);
	
	public Bala(Vector2 pos,iMinigameTools t, int id){
		super(new CircleCollider(size,elast,pos,"bullet", true, 30),null,new Obj(new Rectangulo(pos.x,pos.y, size,size), "poste.png", new Rectangulo(0,0,1,1)));   
		m_Collider.setDrag(sDrag);
		m_Collider.setVelCap(vCap);
		m_Collider.setTrigger(true);
		m_Collider.addListener(this);
		myTools = t;
		player_ID = id;
	}

	public void setPlayer_ID(int id) {
		this.player_ID = id;
	}
	
	public int getPlayer_ID(){
		return player_ID;
	}
	
	public int getHit_ID() {
		return hit_ID;
	}
	
	public void setHit_ID(int id){
		if(hit_ID != -1)
			return;
		hit_ID = id;
	}

	@Override
	public void onTriggerEnter(Collider c) {
		if(c.getTag().contains("Player")){
			if(Character.getNumericValue(c.getTag().charAt(6)) == player_ID){
				super.onTriggerEnter(c);
				return;
			}
			setHit_ID(Character.getNumericValue(c.getTag().charAt(6)));
			myTools.sendEst(hit_ID, new Estatistica("Atingido", 1));
			if(((hit_ID + player_ID) % 2 == 0)){//Mesma equipa
				myTools.sendEst(player_ID, new Estatistica("Friendly Fire", 1));
			} else {
				myTools.sendEst(player_ID, new Estatistica("Tiros Acertados", 1));
			}
		}
		this.destroy();
		super.onTriggerEnter(c);
	}

	@Override
	public void update(float timeLapsed) {	
	}
}