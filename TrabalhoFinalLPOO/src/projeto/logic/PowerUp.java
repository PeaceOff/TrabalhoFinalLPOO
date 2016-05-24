package projeto.logic;

public class PowerUp extends GameObject {

	public PowerUp() {
		super(null,null,null);
		m_Collider = new CircleCollider(10,0,new Vector2(100,100),"PowerUp", false, 1);   
		m_Obj = new Obj(new Rectangulo(100,100, 10), "circulo.png", new Rectangulo(0,0,1,1));
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
