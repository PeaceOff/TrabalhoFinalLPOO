package projeto.logic;

/**
 * @author PeaceOff
 * @version 1.0
 * @created 03-mai-2016 15:30:42
 */
public class Player extends GameObject {

	private int id;
	private int size = 60;
	public Player(Input i, int _id , Vector2 pos){
		super(null,i,null); 
		id = _id; 
		  
		m_Collider = new CircleCollider(size, pos, "player", true, 30); 
		m_Collider.setDrag(new Vector2(10, 10));  
		m_Obj = new Obj(new Rectangulo(), "players.png", new Rectangulo(id*1/8f, 0, 1/8f ,1)); 
	}

	public void update(float timeLapsed){
		PlayerInput pIn = this.m_Input.getPlayerInput(id);
		this.m_Collider.velocity.add(pIn.getDirection());
		pIn.setDirection(new Vector2()); 
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}