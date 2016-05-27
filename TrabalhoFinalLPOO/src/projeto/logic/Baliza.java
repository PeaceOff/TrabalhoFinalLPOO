package projeto.logic;

public class Baliza extends GameObject {

	private int _id;
	private iMinigameTools myTools;
	
	public Baliza(Input i,Vector2 pos, Vector2 size,int id, iMinigameTools t) {
		super(new RectCollider(pos,size.x,size.y,"Baliza",false,1)
			, i
			, new Obj(new Rectangulo(pos.x,pos.y,size.x, size.y)
					,"parede.png", new Rectangulo(0,0,1,1)));
		_id = id;
		myTools = t;
		m_Collider.trigger = true;
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
		if(c.tag.contains("ball")){
			myTools.scoreReceived(1, _id);
			myTools.resetRound();
		}
		super.onTriggerEnter(c);
	}

}
