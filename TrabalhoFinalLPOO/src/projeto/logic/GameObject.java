package projeto.logic;

/**
 * @author David
 * @version 1.0
 * @created 03-mai-2016 15:30:36
 */
public abstract class GameObject implements ICollider {

	protected Collider m_Collider;
	protected Input m_Input;
	protected Obj m_Obj;
	protected boolean destroy = false;

	public GameObject(Collider c, Input i, Obj o){
		m_Collider = c;
		if(m_Collider != null)
			m_Collider.setGameObj(this);
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
		if(m_Collider != null)
			m_Obj.setDimensions(m_Collider.getBoundingBox());
			
		return m_Obj; 
	}

	public void destroy(){
		destroy = true;
	}
	
	public boolean isDestroyed(){
		return destroy;
	}
}