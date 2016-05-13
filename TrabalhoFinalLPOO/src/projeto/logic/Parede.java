package projeto.logic;

public class Parede extends GameObject{
	
	public Parede(Input i, Vector2 pos, Vector2 size) {
		super(null, i, null);
		m_Collider = new RectCollider(pos,size.y,size.x, "Parede",false,2000);
		m_Obj = new Obj(new Rectangulo(pos.x,pos.y,size.y,size.x),"colors-originals.png"
				, new Rectangulo(0,0,0.083,0.083));
	}

	@Override
	public void update(float timeLapsed) {
		// TODO Auto-generated method stub
		
	}
	
}
