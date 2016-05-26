package projeto.logic;

public class Bola extends GameObjectState {
	
	private int lastID = -1;
	private final static int size = 10;
	private final static double elast = 0.9;
	private final Vector2 sDrag = new Vector2(20,20);
	private final Vector2 vCap = new Vector2(5000,5000);
	
	public Bola(Input i, Vector2 pos, Vector2 initV){
		super(new CircleCollider(size,elast,pos,"ball", true, 30),i,new Obj(new Rectangulo(pos.x,pos.y, size,size), "bola.png", new Rectangulo(0,0,1,1)));   
		m_Collider.setDrag(sDrag);
		m_Collider.setVelCap(vCap);
		m_Collider.addListener(this);
	}
	  
	@Override
	public void update(float timeLapsed) {
		super.update(timeLapsed);
		
	}
	
	@Override
	public void onCollisionEnter(Collider c) {
		if(c.tag.contains("Player")){
			setLastID(Character.getNumericValue(c.tag.charAt(6)));
		}

		super.onCollisionEnter(c);
	}
	
	@Override
	public final int getSize(){
		return size;
	}

	public int getLastID() {
		return lastID;
	}

	public void setLastID(int lastID) {
		this.lastID = lastID;
	}

	@Override
	public void onTriggerEnter(Collider c) {
		super.onTriggerEnter(c);
	}
	
	public Vector2 getCap(){
		return vCap;
	}
}
