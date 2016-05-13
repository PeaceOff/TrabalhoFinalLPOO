package projeto.logic;

public class Parede extends GameObject{
	
	public Parede(Input i, Vector2 pos, Vector2 size) {
		super(null, i, null);
		//pos.x +=size.x/2;
		//pos.y +=size.y/2;   
		m_Collider = new RectCollider(pos,size.x,size.y, "Parede" ,false,2000);
		m_Obj = new Obj(new Rectangulo(pos.x,pos.y,size.x,size.y)
				,"colors-originals.png" 
				, new Rectangulo(0,0.083*9+0.01,1,0.083 -0.01)); 
	} 

	@Override
	public void update(float timeLapsed) {
		// TODO Auto-generated method stub
		
	}
	
}
