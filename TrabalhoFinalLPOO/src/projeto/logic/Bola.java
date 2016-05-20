package projeto.logic;

public class Bola extends GameObject {
	
	private State _state;
	
	public Bola(Input i, Vector2 pos, int size , Vector2 initV){
		super(null,i,null);
		m_Collider = new CircleCollider(size, pos,"ball", true, size); 
		m_Obj = new Obj(new Rectangulo(pos.x,pos.y, size,size), "circulo.png", new Rectangulo(0,0,1,1));
		m_Collider.addVelocity(initV);
		_state = new NormalState();
	}
	  
	@Override
	public void update(float timeLapsed) {
		_state.update(timeLapsed,this);
	}

}
