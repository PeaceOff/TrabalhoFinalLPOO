package projeto.logic;

public class Parede extends GameObject{
	
	public Parede(Input i, Vector2 pos, Vector2 size) {
		super(new RectCollider(pos,size.x,size.y, "Parede" ,false,2000), i, new Obj(new Rectangulo(pos.x,pos.y,size.x,size.y)
				,"colors-originals.png" 
				, new Rectangulo(0,0.083*9+0.01,1,0.083 -0.01)));
		m_Collider.addListener(this);
	} 

	@Override
	public void update(float timeLapsed) {
		// TODO Auto-generated method stub
		
	}
	
}
