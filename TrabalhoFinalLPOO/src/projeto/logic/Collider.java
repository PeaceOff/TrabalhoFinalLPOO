package projeto.logic;

/**
 * @author João e David
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

	/**
	 * Construtor base de um collider que inicializar o drag, posicao e velocidade a zero
	 */
	public Collider(){
		drag = new Vector2();
		position = new Vector2();
		velocity = new Vector2();
	}
	
	/**
	 * Construtor de um collider
	 * @param pos posicao
	 * @param t tag
	 * @param m se e movel true se nao false
	 * @param ms massa
	 */
	public Collider(Vector2 pos, String t,boolean m, double ms){
		drag = new Vector2();
		position = pos;
		velocity = new Vector2();
		velCap = new Vector2();
		tag = t;
		movable = m;
		mass = ms;
	}
	/**
	 * Metodo que obter o limite de velocidade
	 * @return limite de velocidade
	 */
	public Vector2 getVelCap() {
		return velCap;
	}
	/**
	 * Metodo para adicionar o listener de colisoes
	 * @param iC listener
	 */
	public void addListener(ICollider iC){
		m_iCollider = iC;
	}
	
	/**
	 * Metodo para adicionar velocidade a um objeto
	 * @param v velocidade a adicionar
	 */
	public void addVelocity(Vector2 v){
		velocity.add(v);
	}
	/**
	 * Metodo que devolve o retangulo limitador do objeto
	 * @return rectangulo limitador
	 */
	public abstract Rectangulo getBoundingBox();
	
	/**
	 * Metodo chamado quando e detetada colisao
	 * @param c collider com quem colidiu
	 */
	public void onCollisionEnter(Collider c){
		if(m_iCollider != null)
			m_iCollider.onCollisionEnter(c);
	};
	/**
	 * Metodo chamado quando e detetada interseccao
	 * @param c collider com quem intersectou
	 */
	public void onTriggerEnter(Collider c){
		if(m_iCollider != null)
			m_iCollider.onTriggerEnter(c);
	}

	/**
	 * Metodo update que atualiza o objeto
	 * @param time tempo decorrido desde a ultima chamada
	 */
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

	/**
	 * Metodo que devolve o drag do collider
	 * @return drag
	 */
	public Vector2 getDrag() {
		return drag;
	}
	/**
	 * Metodo que define o drag do collider
	 * @param drag drag a ser defenido
	 */
	public void setDrag(Vector2 drag) {
		this.drag = drag;
	}
	/**
	 * Metodo que define se um objeto pode mover ou nao
	 * @return true se o objeto se move, false em caso contrario
	 */
	public boolean isMovable() {
		return movable;
	}
	
	/**
	 * Metodo que define se um objeto se pode mover ou nao
	 * @param movable true se o objeto se pode mover, false em caso contrario
	 */

	public void setMovable(boolean movable) {
		this.movable = movable;
	}
	/**
	 * Metodo que devolve a posicao do collider
	 * @return posicao do objeto
	 */
	public Vector2 getPosition() {
		return position.clone();
	}
	/**
	 * Metodo que define a posicao do collider
	 * @param position posicao a defenir
	 */
	public void setPosition(Vector2 position) {
		this.position = position;
	}
	/**
	 * Metodo que devolve o drag do collider
	 * @return drag do collider
	 */
	public String getTag() {
		return tag;
	}
	/**
	 * Metodo que define a nova tag do collider
	 * @param tag tag do collider
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}
	/**
	 * Metodo que verifica se o collider e atravessavel ou nao
	 * @return true se atravessavel, false caso contrario
	 */
	public boolean isTrigger() {
		return trigger;
	}
	/**
	 * Metodo de defenir se o collider e atravessavel ou nao
	 * @param trigger true se atravessavel, false caso contrario
	 */
	public void setTrigger(boolean trigger) {
		this.trigger = trigger;
	}
	/**
	 * Metodo que devolve a velocidade do colllider
	 * @return velocidade
	 */
	public Vector2 getVelocity() {
		return velocity;
	}
	/**
	 * Metodo que define a nova velocidade
	 * @param velocity nova velocidade
	 */
	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}
	/**
	 * Metodo que devolve o GameObjet associado
	 * @return GameObject associado
	 */
	public GameObject getGameObj() {
		return _GameObject;
	}
	/**
	 * Metodo que define o GameObject associado
	 * @param _GameObject GameObejct associado
	 */
	public void setGameObj(GameObject _GameObject) {
		this._GameObject = _GameObject;
	}
	/**
	 * Metodo que define o limite de velocidade
	 * @param v limite de velocidade
	 */
	public void setVelCap(Vector2 v){
		this.velCap = v;
	}

}