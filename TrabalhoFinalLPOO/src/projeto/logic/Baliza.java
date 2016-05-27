package projeto.logic;

public class Baliza extends GameObject {

	public Baliza(Input i,Vector2 pos, Vector2 size) {
		super(new RectCollider(pos,size.x,size.y,"Baliza",false,1)
			, i
			, new Obj(new Rectangulo(pos.x,pos.y,size.x, size.y)
					,"parede.png", new Rectangulo(0,0,1,1)));
		m_Collider.trigger = true;
		m_Collider.addListener(this);
	}

	@Override
	public void update(float timeLapsed) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void onCollisionEnter(Collider c) {
		super.onCollisionEnter(c);
	}

	@Override
	public void onTriggerEnter(Collider c) {
		// TODO Auto-generated method stub
		super.onTriggerEnter(c);
	}

}
