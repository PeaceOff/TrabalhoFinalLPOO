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
	protected boolean trigger = false;
	protected Vector2 velocity;
	protected iCollider m_iCollider;
	protected double mass;

	public Collider(){
		drag = new Vector2();
		position = new Vector2();
		velocity = new Vector2();
	}
	
	public Collider(Vector2 pos, String t,boolean m, double ms){
		drag = new Vector2();
		position = pos;
		velocity = new Vector2();
		tag = t;
		movable = m;
		mass = ms;
	}
	
	public void addListener(iCollider iC){
		m_iCollider = iC;
	}

	public void addVelocity(Vector2 v){
		velocity.add(v);
	}

	public abstract Rectangulo getBoundingBox();

	public abstract void onCollisionEnter(Collider c);

	public abstract void onTriggerEnter(Collider c);


	public void update(float time){
		
		if(!movable)
			return;
		
		this.position.x += this.velocity.x * time;
		this.position.y += this.velocity.y * time;
		Vector2 newVelocity = new Vector2();
		newVelocity.x = this.velocity.x - (this.drag.x  * time);
		newVelocity.y = this.velocity.y - (this.drag.y * time);
		synchronized(this){ 
			this.setVelocity(newVelocity);
		}
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