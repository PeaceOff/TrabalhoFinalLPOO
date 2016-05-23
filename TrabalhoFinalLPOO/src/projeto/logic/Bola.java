package projeto.logic;

public class Bola extends GameObjectState {
	
	private int lastID = -1; 
	
	public Bola(Input i, Vector2 pos, int size , Vector2 initV){
		super(null,i,null);
		m_Collider = new CircleCollider(size, pos,"ball", true, 30);   
		m_Collider.setDrag(new Vector2(20,20)); 
		m_Obj = new Obj(new Rectangulo(pos.x,pos.y, size,size), "circulo.png", new Rectangulo(0,0,1,1));
		m_Collider.addVelocity(initV);
	}
	  
	@Override
	public void update(float timeLapsed) {
		
	}
	
	@Override
	public void onCollisionEnter(Collider c) {
		
		if(c.tag.contains("Player")){
		}
		
		super.onCollisionEnter(c);
	}
	
}
