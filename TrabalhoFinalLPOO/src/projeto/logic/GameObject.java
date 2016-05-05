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

	public GameObject(Collider c, Input i, Obj o){
		m_Collider = c;
		m_Input = i;
		m_Obj = o;
	}

	public void onCollisionEnter(Collider c){

	}

	public void onTriggerEnter(Collider c){

	}

	public void start(){

	}

	public abstract void update(float timeLapsed);

}