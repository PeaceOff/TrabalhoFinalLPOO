package projeto.logic;

/**
 * @author David
 * @version 1.0
 * @created 03-mai-2016 15:30:36
 */
public abstract class GameObject implements iCollider {

	public Collider m_Collider;
	public Input m_Input;
	public Obj m_Obj;

	public GameObject(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param c
	 */
	public void onCollisionEnter(Collider c){

	}

	/**
	 * 
	 * @param c
	 */
	public void onTriggerEnter(Collider c){

	}

	public void start(){

	}

	/**
	 * 
	 * @param timeLapsed
	 */
	public void update(float timeLapsed){

	}

}