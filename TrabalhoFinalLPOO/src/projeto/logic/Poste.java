package projeto.logic;

public class Poste extends GameObject {
	
	public Poste(Input i,Vector2 pos, int size, int mass){
		super(null, i, null);
		m_Collider =new CircleCollider(size, 0 ,pos, "", false, mass);
		
		m_Obj = new Obj(new Rectangulo(), "players.png", new Rectangulo(0*1/8f, 0, 1/8f ,1));
		m_Collider.addListener(this);
		
		
	}

	@Override
	public void update(float timeLapsed) {
		// TODO Auto-generated method stub
		
	}
	
}
