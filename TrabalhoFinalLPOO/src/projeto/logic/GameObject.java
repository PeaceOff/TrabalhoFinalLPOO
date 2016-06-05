package projeto.logic;

/**
 * @author Joao e David
 * @version 1.0
 * @created 03-mai-2016 15:30:36
 */
public abstract class GameObject implements ICollider {

	protected Collider m_Collider;
	protected Input m_Input;
	protected Obj m_Obj;
	protected boolean destroy = false;

	/**
	 * Construtor da classe 
	 * @param c Collider do objecto
	 * @param i Input para o objecto
	 * @param o Obj do objeto
	 */
	public GameObject(Collider c, Input i, Obj o){
		m_Collider = c;
		if(m_Collider != null)
			m_Collider.setGameObj(this);
		m_Input = i;
		m_Obj = o;
	}
	
	/**
	 * Funcao para quando ocorre colisao
	 * @param c Collider com o qual colidiu
	 */
	public void onCollisionEnter(Collider c){
		
	}

	/**
	 * Funcao para quando ocorre colisao com um trigger
	 * @param c Collider com o qual colidiu
	 */
	public void onTriggerEnter(Collider c){
		
	}

	/**
	 * Funcao para atualizar o objecto
	 * @param timeLapsed tempo que decorreu desde a ultima atualizacao
	 */
	public abstract void update(float timeLapsed);

	/**
	 * Funcao get para o collider do objecto
	 * @return Collider do objecto
	 */
	public Collider getCollider() {
		return m_Collider;
	}
 
	/**
	 * Funcao get para o Obj do objecto
	 * @return Obj do objecto
	 */
	public Obj getObj() {
		if(m_Collider != null)
			m_Obj.setDimensions(m_Collider.getBoundingBox());
			
		return m_Obj; 
	}

	/**
	 * Funcao para destruir o objecto.
	 * A funcao ativa a flag de destroy, o objecto sera destruido
	 * na proxima passagem pela funcao de update
	 */
	public void destroy(){
		destroy = true;
	}
	
	/**
	 * Funcao para verificar se o objecto tem a flag destroy ativa
	 * @return se esta destruido ou nao
	 */
	public boolean isDestroyed(){
		return destroy;
	}
}