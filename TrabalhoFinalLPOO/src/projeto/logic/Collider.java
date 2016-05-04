package projeto.logic;

/**
 * @author David
 * @version 1.0
 * @created 03-mai-2016 15:30:32
 */
public abstract class Collider {

	protected Vector2 drag;
	protected boolean movable;
	protected Vector2 position;//Centro geometrico do objeto
	protected String tag;
	/**
	 * Indicates if is trigger
	 */
	protected boolean trigger = true;
	protected Vector2 velocity;
	protected iCollider m_iCollider;

	public Collider(){
		drag = new Vector2();
		position = new Vector2();
		velocity = new Vector2();
	}	
	
	/**
	 * 
	 * @param iC
	 */
	public void addListener(iCollider iC){
		m_iCollider = iC;
	}

	/**
	 * 
	 * @param v
	 */
	public void addVelocity(Vector2 v){
		velocity.add(v);
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

	public Vector2 getDrag() {
		return drag;
	}

	public void setDrag(Vector2 drag) {
		this.drag = drag;
	}

	public boolean isMovable() {
		return movable;
	}

	public void setMovable(boolean movable) {
		this.movable = movable;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public boolean isTrigger() {
		return trigger;
	}

	public void setTrigger(boolean trigger) {
		this.trigger = trigger;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

}