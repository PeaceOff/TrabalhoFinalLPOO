package projeto.logic;

public class Bola extends GameObject {
	
	public Bola(Input i, Vector2 pos, int size){
		super(null,i,null);
		m_Collider = new CircleCollider(size, pos,"ball", true, 20); 
		m_Obj = new Obj(new Rectangulo(pos.x,pos.y, size,size), "colors-originals.png", new Rectangulo(0,0,1,1));
		m_Collider.addVelocity(new Vector2(102222,0));  
	}
	
	@Override
	public void update(float timeLapsed) {
		
	
		
	}

}
