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
	protected ICollider m_iCollider = null;
	protected double mass;
	protected Vector2 velCap;
	

	protected GameObject _GameObject = null;


	public Collider(){
		drag = new Vector2();
		position = new Vector2();
		velocity = new Vector2();
	}
	
	public Collider(Vector2 pos, String t,boolean m, double ms){
		drag = new Vector2();
		position = pos;
		velocity = new Vector2();
		velCap = new Vector2();
		tag = t;
		movable = m;
		mass = ms;
	}
	 
	public Vector2 getVelCap() {
		return velCap;
	}
	
	public void addListener(ICollider iC){
		m_iCollider = iC;
	}

	public void addVelocity(Vector2 v){
		velocity.add(v);
	}

	public abstract Rectangulo getBoundingBox();

	public void onCollisionEnter(Collider c){
		if(m_iCollider != null)
			m_iCollider.onCollisionEnter(c);
	};

	public void onTriggerEnter(Collider c){
		if(m_iCollider != null)
			m_iCollider.onTriggerEnter(c);
	}


	public void update(float time){
		
		if(!movable)
			return;
			
		Vector2 newVelocity = new Vector2();
		if(this.velocity.x != 0 || this.velocity.y != 0){
			newVelocity.x = (this.velocity.x > 0)? this.velocity.x - (this.drag.x  * time) : this.velocity.x + (this.drag.x  * time);
			newVelocity.y = (this.velocity.y > 0)? this.velocity.y - (this.drag.y * time) : this.velocity.y + (this.drag.y * time);
		}
		
		if(newVelocity.getNorm() > velCap.x){
			newVelocity.normalize();
			newVelocity.multiply(velCap.x);
		}
			/*
			if (newVelocity.x > velCap.x) newVelocity.x = velCap.x;
			if (newVelocity.x < -velCap.x) newVelocity.x = -velCap.x;
			
			if (newVelocity.y > velCap.y) newVelocity.y = velCap.y;
			if (newVelocity.y < -velCap.y) newVelocity.y = -velCap.y; 
			*/
		
		synchronized(this){ 
			this.setVelocity(newVelocity);
			
			this.position.x += this.velocity.x * time;
			this.position.y += this.velocity.y * time;
			
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
		return position.clone();
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

	public GameObject getGameObj() {
		return _GameObject;
	}

	public void setGameObj(GameObject _GameObject) {
		this._GameObject = _GameObject;
	}
	
	public void setVelCap(Vector2 v){
		this.velCap = v;
	}

}