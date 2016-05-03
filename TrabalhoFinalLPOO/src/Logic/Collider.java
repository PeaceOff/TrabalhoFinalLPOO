package Logic;


/**
 * @author David
 * @version 1.0
 * @created 03-mai-2016 15:30:32
 */
public abstract class Collider {

	private Vector2 drag;
	private boolean movable;
	private Vector2 position;
	private String tag;
	/**
	 * Indicates if is trigger
	 */
	private boolean trigger = true;
	private Vector2 velocity;
	public iCollider m_iCollider;

	public Collider(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param iC
	 */
	public abstract void addListener(iCollider iC);

	/**
	 * 
	 * @param v
	 */
	public void addVelocity(Vector2 v){

	}

	public abstract Rectangulo getBoundingBox();

	/**
	 * 
	 * @param c
	 */
	public abstract void onCollisionEnter(Collider c);

	/**
	 * 
	 * @param c
	 */
	public abstract void onTriggerEnter(Collider c);

	/**
	 * 
	 * @param time
	 */
	public void update(float time){

	}

}