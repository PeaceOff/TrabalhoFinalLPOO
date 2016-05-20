package projeto.logic;

/**
 * @author David
 * @version 1.0
 * @created 03-mai-2016 15:30:36
 */
public abstract class GameObject implements iCollider {

	protected Collider m_Collider;
	protected Input m_Input;
	protected Obj m_Obj;

	public GameObject(Collider c, Input i, Obj o){
		m_Collider = c;
		m_Input = i;
		m_Obj = o;
	}
	
	

	public void onCollisionEnter(Collider c){
		
	}

	public void onTriggerEnter(Collider c){

	}

	public abstract void update(float timeLapsed);



	public Collider getCollider() {
		return m_Collider;
	}
 
	public Obj getObj() { 
		m_Obj.setDimensions(m_Collider.getBoundingBox()); 
		return m_Obj; 
	}

}