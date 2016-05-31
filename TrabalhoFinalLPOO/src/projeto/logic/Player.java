package projeto.logic;

/**
 * @author PeaceOff
 * @version 1.0
 * @created 03-mai-2016 15:30:42
 */
public class Player extends GameObjectState {

	private int id;
	private final static int size = 20;
	private final static double elast = 0.6;
	private int score = 0;
	private final Vector2 sDrag = new Vector2(1000,1000);
	private final Vector2 vCap = new Vector2(300,300);
	
	public Player(Input i, int _id , Vector2 pos){
		super(new CircleCollider(size,elast, pos, "Player" + _id, true, 70),i,new Obj(new Rectangulo(), "players.png", new Rectangulo(_id*1/8f, 0, 1/8f ,1))); 
		id = _id;
		m_Collider.setVelCap(vCap);
		m_Collider.setDrag(sDrag);
		m_Collider.addListener(this);
	}

	public void update(float timeLapsed){
		super.update(timeLapsed);
		
		PlayerInput pIn = this.m_Input.getPlayerInput(id);
		
		if(pIn.getDirection().getNorm() > 0){
			synchronized(m_Collider){ 
				this.m_Collider.velocity = ((Vector2.multiply(pIn.getDirection(),m_Collider.getVelCap().x))); 
			}
			pIn.setDirection(new Vector2());  
		}		
	}

	@Override
	public void onCollisionEnter(Collider c) {
		super.onCollisionEnter(c);
	}

	@Override
	public void onTriggerEnter(Collider c) {
		super.onTriggerEnter(c);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public final int getSize(){
		return size;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public Vector2 getCap(){
		return vCap;
	}
}