package projeto.minigames.soccer;

import projeto.logic.Collider;
import projeto.logic.GameObject;
import projeto.logic.Input;
import projeto.logic.Obj;
import projeto.logic.RectCollider;
import projeto.logic.Rectangulo;
import projeto.logic.Vector2;
import projeto.logic.IMinigameTools;

public class Baliza extends GameObject {

	private int _id;
	private IMinigameTools myTools;
	
	public Baliza(Input i,Vector2 pos, Vector2 size,int id, IMinigameTools t) {
		super(new RectCollider(pos,size.x,size.y,"Baliza" + id,false,1)
			, i
			, new Obj(new Rectangulo(pos.x,pos.y,size.x, size.y)
					,"balizas.png", new Rectangulo(0.5*(1-id),0,0.5,1)));
		_id = id;
		myTools = t;
		m_Collider.setTrigger(true);
		m_Collider.addListener(this);
	}

	@Override
	public void update(float timeLapsed) {
	}
	
	@Override
	public void onCollisionEnter(Collider c) {
		super.onCollisionEnter(c);
	}

	@Override
	public void onTriggerEnter(Collider c) {
		if(c.getTag().contains("ball")){
			myTools.scoreReceived(1, _id);
			myTools.resetRound();
		}
		super.onTriggerEnter(c);
	}
	
	public int getID(){
		return _id;
	}

}
