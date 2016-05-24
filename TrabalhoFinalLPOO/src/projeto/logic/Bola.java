package projeto.logic;

public class Bola extends GameObjectState {
	
	private int lastID = -1;
	private final static int size = 10;
	private final static double elast = 0.9;
	
	public Bola(Input i, Vector2 pos, Vector2 initV){
		super(null,i,null);
		m_Collider = new CircleCollider(size,elast,pos,"ball", true, 30);   
		m_Collider.setDrag(new Vector2(20,20)); 
		m_Obj = new Obj(new Rectangulo(pos.x,pos.y, size,size), "circulo.png", new Rectangulo(0,0,1,1));
		m_Collider.addVelocity(initV);
		m_Collider.addListener(this);
	}
	  
	@Override
	public void update(float timeLapsed) {
		//System.out.println("Last Player : " + lastID);
		if(this.m_State != null)
			this.m_State.update(timeLapsed, this);
	}
	
	@Override
	public void onCollisionEnter(Collider c) {
		
		if(c.tag.contains("Player")){
			setLastID(Character.getNumericValue(c.tag.charAt(6)));
			//this.setM_State(new BiggerState());
		}
		//System.out.println("Last Player : " + lastID);
		super.onCollisionEnter(c);
	}
	
	public final int getSize(){
		return size;
	}

	public int getLastID() {
		return lastID;
	}

	public void setLastID(int lastID) {
		this.lastID = lastID;
	}
}
